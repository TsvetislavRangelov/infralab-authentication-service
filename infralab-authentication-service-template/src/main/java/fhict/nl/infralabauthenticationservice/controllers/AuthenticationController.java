package fhict.nl.infralabauthenticationservice.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhict.nl.infralabauthenticationservice.business.services.AccessTokenValidationService;
import fhict.nl.infralabauthenticationservice.business.services.FHICTTokenExchangeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController{
    private FHICTTokenExchangeService exchangeService;
    private AccessTokenValidationService accessTokenValidationService;

    @GetMapping
    public ResponseEntity<String> authorize (@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {



        String token = exchangeService.exchangeCodeForToken(code);

        System.out.println(token);

        // If the response is 200 = redirect to localhost and save the claims,
        // If 400 - token was not validated =  show error page
        try {
            String claimsToken = accessTokenValidationService.validateToken(token);
            System.out.println(claimsToken);


            // get the role node from the json
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(claimsToken);
            JsonNode roleNode = jsonNode.get("role");
            JsonNode emailNode = jsonNode.get("email");

            // check role
            // teacher -> we check if the email is Peter's -> redirect to admin page
            // student -> redirect to certificate page
            // else -> access denied

            // need to return role and token
            for (JsonNode element : roleNode) {
                if (Objects.equals(element.asText(), "teacher")) {
                    if (Objects.equals(emailNode.asText(), "p.hoogenberg@fontys.nl")) {
                        response.sendRedirect("http://localhost:3000/admin");
                    } else {
                        response.sendRedirect("http://localhost:3000/error");
                    }
                    break;
                } else if (Objects.equals(element.asText(), "student")) {
                    // response.setStatus(HttpServletResponse.SC_ACCEPTED);
                    response.sendRedirect("http://localhost:3000/certificates");
                    break;
                }
            }
            return ResponseEntity.ok().build();
        } catch (WebClientResponseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
