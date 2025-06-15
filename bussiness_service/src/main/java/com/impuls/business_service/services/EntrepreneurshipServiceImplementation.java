package com.impuls.business_service.services;

import com.impuls.business_service.client.UserServiceClient;
import com.impuls.business_service.client.response.UserResponse;
import com.impuls.business_service.model.*;
import com.impuls.business_service.model.gateway.CityRepository;
import com.impuls.business_service.model.gateway.EntrepreneurshipRepository;
import com.impuls.business_service.services.interfaces.EntrepreneurshipService;
import com.impuls.business_service.services.request.AddressRequest;
import com.impuls.business_service.services.request.EntrepreneurshipRequest;
import com.impuls.business_service.services.request.SocialNetworkRequest;
import com.impuls.business_service.services.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

@org.springframework.stereotype.Service
public class EntrepreneurshipServiceImplementation implements EntrepreneurshipService {

    @Autowired
    EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public Entrepreneurship createEntrepreneurship(EntrepreneurshipRequest request, String authToken) {
        // 1. Obtener el usuario autenticado desde user-service
        ResponseEntity<UserResponse> userResponse = userServiceClient.getCurrentUser(authToken);
        UserResponse user = userResponse.getBody();
        System.out.println(user);
        // 2. Validar que el usuario existe
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("Usuario no válido o no autenticado");
        }

        // 3. Asignar el ownerId con el ID del usuario
        request.setOwnerId(user.getId());

        return createAndSaveEntrepreneurship(request);
    }

    private Entrepreneurship createAndSaveEntrepreneurship(EntrepreneurshipRequest request){
        Entrepreneurship entrepreneurshipCreated = new Entrepreneurship();
        entrepreneurshipCreated.setName(request.getName());
        entrepreneurshipCreated.setEmail(request.getEmail());
        entrepreneurshipCreated.setEntrepreneurshipNumber(UUID.randomUUID().toString());
        entrepreneurshipCreated.setOwnerId(request.getOwnerId());
        entrepreneurshipCreated.setCreatedAt(LocalDateTime.now());
        entrepreneurshipCreated.setUpdatedAt(LocalDateTime.now());
        entrepreneurshipCreated.setCompanyName(request.getCompanyName());
        entrepreneurshipCreated.setCompanySize(request.getCompanySize());
        entrepreneurshipCreated.setBannerUrl(request.getBannerUrl());
        entrepreneurshipCreated.setLogoUrl(request.getLogoUrl());
        entrepreneurshipCreated.setDescription(request.getDescription());
        entrepreneurshipCreated.setShortDescription(request.getShortDescription());
        entrepreneurshipCreated.setNit(request.getNit());
        entrepreneurshipCreated.setPhone(request.getPhone());
        entrepreneurshipCreated.setWebsiteUrl(request.getWebsiteUrl());
        entrepreneurshipCreated.setIsVerified(false);
        entrepreneurshipCreated.setIsActive(true);
        entrepreneurshipCreated.setRegisterDate(request.getRegisterDate());
        entrepreneurshipCreated.setStartDate(request.getStartDate());
        entrepreneurshipCreated.setIncorporationDate(request.getIncorporationDate());
        entrepreneurshipCreated.setFormalized(request.getFormalized());
        if(request.getSocialNetworkRequests()!=null){
            createSocialNetwork(entrepreneurshipCreated, request.getSocialNetworkRequests());
        }
        if(request.getAddressRequests()!=null) {
            createAddress(entrepreneurshipCreated, request.getAddressRequests());
        }
        return entrepreneurshipRepository.save(entrepreneurshipCreated);
    }

    private void createSocialNetwork(Entrepreneurship entrepreneurship, List<SocialNetworkRequest> socialNetworkRequests){
        Set<SocialNetwork> socialNetworks = new HashSet<>();
        for (SocialNetworkRequest socialNetworkRequest : socialNetworkRequests) {
            SocialNetwork socialNetwork = new SocialNetwork();
            socialNetwork.setName(socialNetworkRequest.getName());
            socialNetwork.setUrl(socialNetworkRequest.getUrl());
            socialNetwork.setEntrepreneurship(entrepreneurship); // Set the bidirectional relationship
            socialNetworks.add(socialNetwork);
        }
        entrepreneurship.setSocialNetworks(socialNetworks); // Set the collection on the entrepreneurship

    }

    private void createAddress(Entrepreneurship entrepreneurship, List<AddressRequest> addressRequests){
         Set<Address> addresses = new HashSet<>();
        for (AddressRequest addressRequest : addressRequests) {
            Address address = new Address();
            address.setStreet(addressRequest.getStreet());
            address.setNeighborhood(addressRequest.getNeighborhood());
            address.setEntrepreneurship(entrepreneurship);
            if (addressRequest.getCity()!=null) {
                Optional<City> city = cityRepository.findById(addressRequest.getCity().getId());
                city.ifPresent(address::setCity);
            }
            address.setIsPrimary(addressRequest.getIsPrimary());
            addresses.add(address);
        }    entrepreneurship.setAddresses(addresses); // Set the collection on the entrepreneurship

    }

    @Override
    public Entrepreneurship updateEntrepreneurship(Long id, EntrepreneurshipRequest request) throws Exception {
        return null;
    }

    @Override
    public void deleteEntrepreneurship(Long id) throws Exception {

    }

    @Override
    public List<Entrepreneurship> getAllEntrepreneurship() {
        return null;
    }

    @Override
    public List<EntrepreneurshipResponse> getAllEntrepreneurshipResponse() {
        List<Entrepreneurship> entrepreneurships = entrepreneurshipRepository.findAll();
        List<EntrepreneurshipResponse> entrepreneurshipResponses = new ArrayList<>();
        for(int i=0; i<entrepreneurships.size(); i++){
            entrepreneurshipResponses.add(convertToDto(entrepreneurships.get(i)));
        }
        return entrepreneurshipResponses;
    }

    @Override
    public Page<EntrepreneurshipResponse> search(String query, Pageable pageable) {
        Page<Entrepreneurship> results = entrepreneurshipRepository.search(query, pageable);

        return results.map (this::convertToDto);
    }

    @Override
    public Page<EntrepreneurshipResponse> getAllEntrepreneurship(Pageable pageable) {

        //Trae todos los datos
        Page<Entrepreneurship> entrepreneurships = entrepreneurshipRepository.findAll(pageable);
        return entrepreneurships.map(this::convertToDto);
    }

    @Override
    public Page<EntrepreneurshipResponse> getByCategory(String categoryName, Pageable pageable) {
        Page<Entrepreneurship> entrepreneurships = entrepreneurshipRepository
                .findByCategoryName(categoryName, pageable);
        return entrepreneurships.map(this::convertToDto);
    }

    @Override
    public Page<EntrepreneurshipResponse> getByCountry(String countryName, Pageable pageable) {
        Page<Entrepreneurship> entrepreneurships = entrepreneurshipRepository
                .findByCountryName(countryName, pageable);
        return entrepreneurships.map(this::convertToDto);
    }

    private EntrepreneurshipResponse convertToDto(Entrepreneurship entrepreneurship) {
        EntrepreneurshipResponse response = new EntrepreneurshipResponse();

        // Mapeo de campos básicos
        response.setOwnerId(entrepreneurship.getOwnerId());
        response.setName(entrepreneurship.getName());
        response.setEmail(entrepreneurship.getEmail());
        response.setPhone(entrepreneurship.getPhone());
        response.setLogoUrl(entrepreneurship.getLogoUrl());
        response.setWebsiteUrl(entrepreneurship.getWebsiteUrl());
        response.setShortDescription(entrepreneurship.getShortDescription());
        response.setBannerUrl(entrepreneurship.getBannerUrl());
        response.setDescription(entrepreneurship.getDescription());

        // Mapeo de direcciones
        if (entrepreneurship.getAddresses() != null) {
            List<AddressResponse> addressResponses = entrepreneurship.getAddresses().stream()
                    .map(address -> {
                        AddressResponse addrResponse = new AddressResponse();
                        addrResponse.setStreet(address.getStreet());
                        addrResponse.setNeighborhood(address.getNeighborhood());
                        addrResponse.setZipCode(address.getZipCode());

                        return addrResponse;
                    }).toList();
            response.setAddressResponses(addressResponses); // Usar setAddressResponses (no Requests)
        }

        // Mapeo de redes sociales
        if (entrepreneurship.getSocialNetworks() != null) {
            List<SocialNetworkResponse> socialNetworkResponses = entrepreneurship.getSocialNetworks().stream()
                    .map(socialNetwork -> {
                        SocialNetworkResponse snResponse = new SocialNetworkResponse();
                        snResponse.setName(socialNetwork.getName());
                        snResponse.setUrl(socialNetwork.getUrl());
                        return snResponse;
                    }).toList();
            response.setSocialNetworkResponses(socialNetworkResponses);
        }

        // Mapeo de categorías
        if (entrepreneurship.getCategories() != null) {
            List<CategoryResponse> categoryResponses = entrepreneurship.getCategories().stream()
                    .map(category -> {
                        CategoryResponse catResponse = new CategoryResponse();
                        catResponse.setName(category.getCategory().getName());
                        catResponse.setDescription(category.getCategory().getDescription());
                        catResponse.setIcon(category.getCategory().getIcon());
                        return catResponse;
                    }).toList();
            response.setCategoryResponses(categoryResponses);
        }

        // Mapeo de servicios
        if (entrepreneurship.getServices() != null) {
            List<ServiceResponse> serviceResponses = entrepreneurship.getServices().stream()
                    .map(service -> {
                        ServiceResponse svcResponse = new ServiceResponse();
                        svcResponse.setName(service.getService().getName());
                        svcResponse.setDescription(service.getService().getDescription());
                        return svcResponse;
                    }).toList();
            response.setServiceResponses(serviceResponses);
        }

        return response;
    }


    @Override
    public Entrepreneurship findEntrepreneurshipById(Long entrepreneurshipId) throws Exception {
        return null;
    }
}
