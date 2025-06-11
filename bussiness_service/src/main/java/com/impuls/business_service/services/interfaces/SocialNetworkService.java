package com.impuls.business_service.services.interfaces;

import com.impuls.business_service.model.Entrepreneurship;
import com.impuls.business_service.model.SocialNetwork;
import com.impuls.business_service.services.request.SocialNetworkRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocialNetworkService {

    public SocialNetwork createSocialNetwork(Entrepreneurship entrepreneurship, SocialNetworkRequest socialNetworkRequest);

    public SocialNetwork updateSocialNetwork(Long id, SocialNetworkRequest socialNetworkRequest) throws Exception;

    public void deleteSocialNetwork(Long id)throws Exception;

    public List<SocialNetwork> getAllSocialNetworks();

    public SocialNetwork  findSocialNetworkById(Long id) throws Exception;
}
