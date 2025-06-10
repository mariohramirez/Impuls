package com.impuls.user_service.services.interfaces;

import com.impuls.user_service.services.response.UserResponse;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public interface UserService {

    public UserResponse getProfile(Long id, String userNumber) throws AccessDeniedException;

    public UserResponse getCurrentUserProfile() throws AccessDeniedException;

}
