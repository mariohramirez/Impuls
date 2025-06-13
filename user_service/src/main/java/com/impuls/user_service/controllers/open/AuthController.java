package com.impuls.user_service.controllers.open;

import com.impuls.user_service.services.interfaces.AuthService;
import com.impuls.user_service.services.request.LoginRequest;
import com.impuls.user_service.services.request.RegisterRequest;
import com.impuls.user_service.services.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.impuls.user_service.controllers.ApiPaths.PUBLIC_PATH;

@RestController
@RequestMapping(PUBLIC_PATH + "auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUserHandler(
            @RequestBody RegisterRequest registerRequest) throws Exception{
        AuthResponse response = authService.registerBaseUser(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse>logIn
            (@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.loginUser(loginRequest);
        return  new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}