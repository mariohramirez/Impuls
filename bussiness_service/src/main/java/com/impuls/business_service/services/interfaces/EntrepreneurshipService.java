package com.impuls.business_service.services.interfaces;

import com.impuls.business_service.model.Entrepreneurship;
import com.impuls.business_service.services.request.EntrepreneurshipRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntrepreneurshipService {

    public Entrepreneurship createEntrepreneurship(EntrepreneurshipRequest request);

    public Entrepreneurship updateEntrepreneurship(Long id, EntrepreneurshipRequest request) throws Exception;

    public void deleteEntrepreneurship(Long id)throws Exception;

    public List<Entrepreneurship> getAllEntrepreneurship();

    public Entrepreneurship findEntrepreneurshipById(Long entrepreneurshipId) throws Exception;
}
