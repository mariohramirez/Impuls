package com.impuls.business_service.services.interfaces;

import com.impuls.business_service.model.Service;
import com.impuls.business_service.services.request.ServiceRequest;

import java.util.List;

@org.springframework.stereotype.Service
public interface ServiceEntrepreneurshipService {

    public Service createService(ServiceRequest request);

    public Service updateService(Integer id, ServiceRequest request) throws Exception;

    public void deleteService(Integer id)throws Exception;

    public List<Service> getAllServices();

    public Service  findServiceById(Integer id) throws Exception;

}
