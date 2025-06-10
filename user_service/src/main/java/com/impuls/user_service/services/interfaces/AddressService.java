package com.impuls.user_service.services.interfaces;

import com.impuls.user_service.model.Address;
import com.impuls.user_service.model.UserProfile;
import com.impuls.user_service.services.request.AddressRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public Address createAddress(UserProfile userProfile, AddressRequest addressRequest);

    public Address updateAddress(Long id, AddressRequest addressRequest) throws Exception;

    public void deleteAddress(Long id)throws Exception;

    public List<Address> getAllAddress();

    public Address  findAddressId(Long id) throws Exception;
}
