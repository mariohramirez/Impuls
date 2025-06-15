package com.impuls.business_service.services.interfaces;

import com.impuls.business_service.model.Entrepreneurship;
import com.impuls.business_service.services.request.EntrepreneurshipRequest;
import com.impuls.business_service.services.response.EntrepreneurshipResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntrepreneurshipService {

    public Entrepreneurship createEntrepreneurship(EntrepreneurshipRequest request, String authToken);

    public Entrepreneurship updateEntrepreneurship(Long id, EntrepreneurshipRequest request) throws Exception;

    public void deleteEntrepreneurship(Long id)throws Exception;

    public List<Entrepreneurship> getAllEntrepreneurship();

    public List<EntrepreneurshipResponse> getAllEntrepreneurshipResponse();

    public Page<EntrepreneurshipResponse> search(String query, Pageable pageable);

    public Page<EntrepreneurshipResponse> getAllEntrepreneurship(Pageable pageable);

    Page<EntrepreneurshipResponse> getByCategory(String categoryName, Pageable pageable);

    Page<EntrepreneurshipResponse> getByCountry(String countryName, Pageable pageable);

    public Entrepreneurship findEntrepreneurshipById(Long entrepreneurshipId) throws Exception;
}
