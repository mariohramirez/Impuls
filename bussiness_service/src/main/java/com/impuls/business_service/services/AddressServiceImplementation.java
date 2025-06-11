package com.impuls.business_service.services;

import com.impuls.business_service.model.Address;
import com.impuls.business_service.model.City;
import com.impuls.business_service.model.Entrepreneurship;
import com.impuls.business_service.model.gateway.AddressRepository;
import com.impuls.business_service.model.gateway.CityRepository;
import com.impuls.business_service.services.interfaces.AddressService;
import com.impuls.business_service.services.request.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Entrepreneurship entrepreneurship, AddressRequest addressRequest) {
        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setNeighborhood(addressRequest.getNeighborhood());
        address.setEntrepreneurship(entrepreneurship);
        if (addressRequest.getCity()!=null) {
            Optional<City> city = cityRepository.findById(addressRequest.getCity().getId());
            city.ifPresent(address::setCity);
        }
        address.setIsPrimary(addressRequest.getIsPrimary());
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, AddressRequest socialNetworkRequest) throws Exception {
        return null;
    }

    @Override
    public void deleteAddress(Long id) throws Exception {
        Address address = findAddressId(id);
        addressRepository.delete(address);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address findAddressId(Long id) throws Exception {
        Optional<Address> opt = addressRepository.findById(id);
        if(opt.isEmpty())
        {
            throw  new Exception("No se ha encontrado el elemento");
        }
        return opt.get();
    }
}
