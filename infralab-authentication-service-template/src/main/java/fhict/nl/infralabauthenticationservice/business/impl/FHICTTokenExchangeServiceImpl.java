package fhict.nl.infralabauthenticationservice.business.impl;

import fhict.nl.infralabauthenticationservice.business.services.FHICTTokenExchangeService;
import fhict.nl.infralabauthenticationservice.domain.AuthToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class FHICTTokenExchangeServiceImpl implements FHICTTokenExchangeService{
    @Autowired
    Environment env;

    @Override
    public String exchangeCodeForToken(String code) {
        String tokenEndpoint = "https://identity.fhict.nl/connect/token";
        WebClient client = WebClient.create();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", "https://localhost:8080/");
        params.add("client_id", "i476232-infralabau");
        params.add("client_secret",env.getProperty("client_secret"));

        return client.post()
                .uri(tokenEndpoint)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(params))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
