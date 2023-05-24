package fhict.nl.infralabauthenticationservice.controllers;

import fhict.nl.infralabauthenticationservice.business.services.AccessTokenValidationService;
import fhict.nl.infralabauthenticationservice.business.services.FHICTTokenExchangeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.io.IOException;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AuthenticationController{
    private FHICTTokenExchangeService exchangeService;
    private AccessTokenValidationService accessTokenValidationService;

    @GetMapping
    public ResponseEntity<String> authorize (@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        String token = exchangeService.exchangeCodeForToken(code);
        System.out.println(token);

        // If the response is 200 = redirect to localhost and save the claims,
        // If 400 - token was not validated =  show error page
        try {
            String validated = accessTokenValidationService.validateToken(token);
            System.out.println(validated);
            response.addHeader("authorization", token);
            response.sendRedirect("http://localhost:3000/");
            return ResponseEntity.ok(token);
        } catch (WebClientResponseException e) {
            return ResponseEntity.ok("INVALID_TOKEN");
        }
    }
}
