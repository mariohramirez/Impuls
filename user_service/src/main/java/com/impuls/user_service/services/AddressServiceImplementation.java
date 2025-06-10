package com.impuls.user_service.services;

import com.impuls.user_service.model.Address;
import com.impuls.user_service.model.City;
import com.impuls.user_service.model.UserProfile;
import com.impuls.user_service.model.gateway.AddressRepository;
import com.impuls.user_service.model.gateway.CityRepository;
import com.impuls.user_service.services.interfaces.AddressService;
import com.impuls.user_service.services.request.AddressRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService {

    private CityRepository cityRepository;

    private AddressRepository addressRepository;

    @Override
    public Address createAddress(UserProfile userProfile, AddressRequest addressRequest) {
        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setNeighborhood(addressRequest.getNeighborhood());
        address.setUserProfile(userProfile);
        Optional<City> city = cityRepository.findById(addressRequest.getCity().getId());
        city.ifPresent(address::setCity);
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
