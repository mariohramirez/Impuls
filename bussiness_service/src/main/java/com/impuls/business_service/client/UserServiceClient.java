package com.impuls.business_service.client;

import com.impuls.business_service.client.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//En valor definimos el servicio con el que se va a conectar
//La url es definida por donde esta ejecutandose el servicio
@FeignClient(value = "Users", url = "http://localhost:8080")
public interface UserServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/user/me")
    ResponseEntity<UserResponse> getCurrentUser(@RequestHeader("Authorization")String token);

}
