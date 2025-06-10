package com.impuls.user_service.controllers.user;

import com.impuls.user_service.services.interfaces.UserService;
import com.impuls.user_service.services.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

import static com.impuls.user_service.controllers.ApiPaths.USER_PATH;

@RestController
@RequestMapping(USER_PATH + "me" )
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<UserResponse> getCurrentUser() throws AccessDeniedException {
        return ResponseEntity.ok(userService.getCurrentUserProfile());
    }

}
