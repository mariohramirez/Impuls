package com.impuls.business_service.controllers.entrepeneur;

import com.impuls.business_service.model.Entrepreneurship;
import com.impuls.business_service.services.interfaces.EntrepreneurshipService;
import com.impuls.business_service.services.request.EntrepreneurshipRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.impuls.business_service.controllers.ApiPaths.PUBLIC_PATH;

@RestController
@RequestMapping(PUBLIC_PATH + "entrepreneurship")
public class EntrepreneurshipController {

    @Autowired
    EntrepreneurshipService entrepreneurshipService;

    @PostMapping("/register")
    public ResponseEntity<Entrepreneurship> createEntrepreneurshipHandler(
            @RequestBody EntrepreneurshipRequest request,
            @RequestHeader("Authorization") String token) throws Exception{
        Entrepreneurship entrepreneurship = entrepreneurshipService.createEntrepreneurship(request, token);
        return new ResponseEntity<>(entrepreneurship, HttpStatus.CREATED);
    }

}
