package com.impuls.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    //Definimos todas las reglas enrutadoras para el API Gateway
    //Functional endpoint programming model
    //En donde las funciones son usadas para enruta y manejar las requests
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        //Definimos la ruta para el servicio
        return GatewayRouterFunctions.route("users_service")
                //Definimos la regla para la ruta donde si el URL path es api/entrepreunership
                //Debemos enviarla al locaclhost:8082
                .route(RequestPredicates.path("api/v1/public/auth/register"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }
}