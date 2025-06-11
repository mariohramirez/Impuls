package com.impuls.business_service.services;

import com.impuls.business_service.model.Service;
import com.impuls.business_service.model.gateway.ServiceRepository;
import com.impuls.business_service.services.interfaces.ServiceEntrepreneurshipService;
import com.impuls.business_service.services.request.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceEntrepreneurshipImplementation implements ServiceEntrepreneurshipService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Service createService(ServiceRequest request) {
        Service createdService = new Service();
        createdService.setName(request.getName());
        createdService.setDescription(request.getDescription());

        return serviceRepository.save(createdService);
    }

    @Override
    public Service updateService(Integer id, ServiceRequest request) throws Exception {
        Service service = findServiceById(id);
        if(request.getDescription()!=null){
            service.setDescription(request.getDescription());
        }
        return serviceRepository.save(service);
    }

    @Override
    public void deleteService(Integer id) throws Exception {
        Service service = findServiceById(id);
        serviceRepository.delete(service);
    }

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service findServiceById(Integer id) throws Exception {
        Optional<Service> opt = serviceRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("No se ha encontrado la categoria con el id");
        }
        return opt.get();
    }


}
