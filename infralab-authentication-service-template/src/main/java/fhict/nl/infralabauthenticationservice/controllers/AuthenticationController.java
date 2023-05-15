package fhict.nl.infralabauthenticationservice.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


import java.io.IOException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    Environment env;


    @GetMapping
    public ResponseEntity<String> returnResponse(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        String tokenEndpoint = "https://identity.fhict.nl/connect/token";
        WebClient client = WebClient.create();
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        //this is really fucking bad but for now im leaving it here cuz im tired,
        //gonna divide the logic into business classes next time, but this works and gets the token - Tsvetislav
        bodyValues.add("grant_type", "authorization_code");
        bodyValues.add("code", code);
        bodyValues.add("redirect_uri", "https://localhost:8080/");
        bodyValues.add("client_id", "i476232-infralabau");
        bodyValues.add("client_secret",env.getProperty("client_secret"));


        String res = client.post()
                .uri(tokenEndpoint)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(bodyValues))
                .retrieve()
                .bodyToMono(String.class)
                .block();


        response.addHeader("authorization", "ACCESS_TOKEN_GOES_HERE");
        System.out.println(res);
        response.sendRedirect("http://localhost:3000/");
        return ResponseEntity.ok(null);
    }
}
