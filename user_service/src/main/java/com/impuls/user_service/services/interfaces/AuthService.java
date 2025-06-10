package com.impuls.user_service.services.interfaces;

import com.impuls.user_service.services.request.LoginRequest;
import com.impuls.user_service.services.request.RegisterRequest;
import com.impuls.user_service.services.response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public AuthResponse registerBaseUser(RegisterRequest registerRequest) throws Exception;

    public AuthResponse loginUser(LoginRequest loginRequest);

}
