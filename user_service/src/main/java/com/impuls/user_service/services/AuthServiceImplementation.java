package com.impuls.user_service.services;

import com.impuls.user_service.security.JwtProvider;
import com.impuls.user_service.model.*;
import com.impuls.user_service.model.gateway.*;
import com.impuls.user_service.services.interfaces.AddressService;
import com.impuls.user_service.services.interfaces.AuthService;
import com.impuls.user_service.services.request.*;
import com.impuls.user_service.services.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    CityRepository cityRepository;

    @Override
    public AuthResponse registerBaseUser
            (RegisterRequest registerRequest) throws Exception {

        if(userRepository.findByEmail(registerRequest.getEmail())!=null){
            throw new Exception("El email ya existe");
        }

        Role defaultRole = roleRepository.findByName(RoleName.USUARIO);

        //Crea al usuario

        User userCreated = createAndSaveUser(registerRequest, defaultRole);
        createUserProfile(userCreated, registerRequest.getProfileRequest());

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userCreated.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registro exitoso");

        return authResponse;
    }

    private User createAndSaveUser(RegisterRequest registerRequest, Role role){
        User userCreated = new User();
        userCreated.setEmail(registerRequest.getEmail());
        userCreated.setUserNumber(UUID.randomUUID().toString());
        userCreated.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userCreated.setCreatedAt(LocalDateTime.now());
        userCreated.setUpdatedAt(LocalDateTime.now());
        userCreated.setActive(true);
        userCreated.setRole(role);

        return userRepository.save(userCreated);
    }

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication=authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login exitoso");
        authResponse.setStatus(true);

        return authResponse;
    }

    private void createUserProfile(User userCreated, ProfileRequest profileRequest){
        UserProfile profileCreated = new UserProfile();
        profileCreated.setFirstName(profileRequest.getFirstName());
        profileCreated.setLastName(profileRequest.getLastName());
        profileCreated.setPhone(profileRequest.getPhone());
        profileCreated.setAvatarUrl(profileRequest.getAvatarUrl());
        profileCreated.setUser(userCreated);
        if(profileRequest.getSocialNetworkRequests()!=null){
            createSocialNetwork(profileCreated, profileRequest.getSocialNetworkRequests());
        }
        if(profileRequest.getAddressRequests()!=null) {
            createAddress(profileCreated, profileRequest.getAddressRequests());
        }
        userProfileRepository.save(profileCreated);
    }

    private void createSocialNetwork(UserProfile userProfile, List<SocialNetworkRequest> socialNetworkRequests){
        Set<SocialNetwork> socialNetworks = userProfile.getSocialNetworks();
        for (SocialNetworkRequest socialNetworkRequest : socialNetworkRequests) {
            SocialNetwork socialNetwork = new SocialNetwork();
            socialNetwork.setName(socialNetworkRequest.getName());
            socialNetwork.setUrl(socialNetworkRequest.getUrl());
            socialNetwork.setUserProfile(userProfile); // Set the bidirectional relationship
            socialNetworks.add(socialNetwork);
        }
    }

    private void createAddress(UserProfile userProfile, List<AddressRequest> addressRequests){
        Set<Address> addresses = userProfile.getAddresses();
        for (AddressRequest addressRequest : addressRequests) {
            Address address = new Address();
            address.setStreet(addressRequest.getStreet());
            address.setNeighborhood(addressRequest.getNeighborhood());
            address.setUserProfile(userProfile);
            if (addressRequest.getCity()!=null) {
                Optional<City> city = cityRepository.findById(addressRequest.getCity().getId());
                city.ifPresent(address::setCity);
            }
            address.setIsPrimary(addressRequest.getIsPrimary());
            addresses.add(address);
        }
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("Nombre de usuario invalido...");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Contrasena invalida...");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
