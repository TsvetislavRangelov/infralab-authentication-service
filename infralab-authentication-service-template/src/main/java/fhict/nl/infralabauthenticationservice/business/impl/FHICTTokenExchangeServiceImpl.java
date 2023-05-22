package fhict.nl.infralabauthenticationservice.business.impl;

import fhict.nl.infralabauthenticationservice.business.services.FHICTTokenExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class FHICTTokenExchangeServiceImpl implements FHICTTokenExchangeService{

    private static final String REDIRECT_URI = "https://localhost:8080/";
    private static final String CLIENT_ID = "i476232-infralabau";


    @Override
    public String exchangeCodeForToken(String code) {
        String tokenEndpoint = "https://identity.fhict.nl/connect/token";
        WebClient client = WebClient.create();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", System.getenv("INFRALAB_CLIENT_SECRET"));

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
