package fhict.nl.infralabauthenticationservice.business.impl;
import fhict.nl.infralabauthenticationservice.business.services.AccessTokenValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class AccessTokenValidationServiceImpl implements AccessTokenValidationService{

    @Override
    public String validateToken (String token) {
        //Will be fixed later
        int token1 = token.indexOf("access_token");
        String beginning = token.substring(token1+15);
        int token2 = beginning.indexOf(",");
        String end = beginning.substring(0, token2-1);

        String validationEndpoint = "https://identity.fhict.nl/connect/accesstokenvalidation";
        WebClient client = WebClient.create();
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        System.out.println(end);
        param.add("token",end);

        return client.post()
                .uri(validationEndpoint)
                .body(BodyInserters.fromFormData(param))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
