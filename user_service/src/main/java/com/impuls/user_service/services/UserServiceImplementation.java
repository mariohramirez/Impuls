package com.impuls.user_service.services;

import com.impuls.user_service.model.User;
import com.impuls.user_service.model.UserProfile;
import com.impuls.user_service.model.gateway.UserRepository;
import com.impuls.user_service.security.CustomUserPrincipal;
import com.impuls.user_service.services.interfaces.UserService;
import com.impuls.user_service.services.response.ProfileResponse;
import com.impuls.user_service.services.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse getProfile(Long userId, String uuid) throws AccessDeniedException {
        // Obtiene el usuario autenticado principal
        CustomUserPrincipal principal = (CustomUserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        //  Válida la propiedad sobre el eprfil
        if (!principal.id().equals(userId) || !principal.userNumber().equals(uuid)) {
            throw new AccessDeniedException("Solo puedes acceder a tu propio perfil");
        }

        // Obtiene el usuario de la base de datos
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + userId));

        // Verifica el número del usuario
        if (!user.getUserNumber().equals(uuid)) {
            throw new AccessDeniedException("Invalid UUID for user");
        }

        UserProfile userProfile = user.getProfile();

        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setFirstName(userProfile.getFirstName());
        profileResponse.setLastName(userProfile.getLastName());
        profileResponse.setPhone(userProfile.getPhone());
        profileResponse.setAvatarUrl(userProfile.getAvatarUrl());

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getEmail());
        userResponse.setProfileResponse(profileResponse);

        // Retorna el perfil
        return userResponse;
    }

    @Override
    public UserResponse getCurrentUserProfile() throws AccessDeniedException {
        CustomUserPrincipal principal = (CustomUserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        return getProfile(principal.id(), principal.userNumber());
    }

}
