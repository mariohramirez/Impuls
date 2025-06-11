package com.impuls.business_service.services;

import com.impuls.business_service.model.*;
import com.impuls.business_service.model.gateway.CityRepository;
import com.impuls.business_service.model.gateway.EntrepreneurshipRepository;
import com.impuls.business_service.services.interfaces.EntrepreneurshipService;
import com.impuls.business_service.services.request.AddressRequest;
import com.impuls.business_service.services.request.EntrepreneurshipRequest;
import com.impuls.business_service.services.request.SocialNetworkRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;

@org.springframework.stereotype.Service
public class EntrepreneurshipServiceImplementation implements EntrepreneurshipService {

    @Autowired
    EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public Entrepreneurship createEntrepreneurship(EntrepreneurshipRequest request) {
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
    public Entrepreneurship findEntrepreneurshipById(Long entrepreneurshipId) throws Exception {
        return null;
    }
}
