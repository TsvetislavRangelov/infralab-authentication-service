package fhict.nl.infralabauthenticationservice.business.impl;
import fhict.nl.infralabauthenticationservice.business.services.AccessTokenValidationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class AccessTokenValidationServiceImpl implements AccessTokenValidationService{
    private static final String validationEndpoint = "https://identity.fhict.nl/connect/accesstokenvalidation";

    @Override
    public String validateToken (String token) throws JSONException {
        //Get access token from input
        JSONObject tokenObject = new JSONObject(token);
        String accessToken = tokenObject.optString("access_token");

        //Setup web client
        WebClient client = WebClient.create();
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("token",accessToken);

        return client.post()
                .uri(validationEndpoint)
                .body(BodyInserters.fromFormData(param))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
