package com.impuls.business_service.services;

import com.impuls.business_service.model.Entrepreneurship;
import com.impuls.business_service.model.SocialNetwork;
import com.impuls.business_service.model.gateway.SocialNetworkRepository;
import com.impuls.business_service.services.interfaces.SocialNetworkService;
import com.impuls.business_service.services.request.SocialNetworkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialNetworkServiceImplementation implements SocialNetworkService {

    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    @Override
    public SocialNetwork createSocialNetwork(Entrepreneurship entrepreneurship, SocialNetworkRequest request) {
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.setName(request.getName());
        socialNetwork.setUrl(request.getUrl());
        socialNetwork.setEntrepreneurship(entrepreneurship);
        return socialNetworkRepository.save(socialNetwork);
    }

    @Override
    public SocialNetwork updateSocialNetwork(Long id, SocialNetworkRequest request) throws Exception {
        SocialNetwork socialNetwork = findSocialNetworkById(id);
        if(request.getName()!=null){
            socialNetwork.setName(request.getName());
        }
        if(request.getName()!=null){
            socialNetwork.setUrl(request.getUrl());
        }
        return socialNetworkRepository.save(socialNetwork);
    }

    @Override
    public void deleteSocialNetwork(Long id) throws Exception {
        SocialNetwork socialNetwork = findSocialNetworkById(id);
        socialNetworkRepository.delete(socialNetwork);
    }

    @Override
    public List<SocialNetwork> getAllSocialNetworks() {
        return socialNetworkRepository.findAll();
    }

    @Override
    public SocialNetwork findSocialNetworkById(Long id) throws Exception {
        Optional<SocialNetwork> opt = socialNetworkRepository.findById(id);
        if(opt.isEmpty())
        {
            throw  new Exception("No se ha encontrado el elemento");
        }
        return opt.get();
    }
}
