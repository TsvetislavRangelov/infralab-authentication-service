package fhict.nl.infralabauthenticationservice.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhict.nl.infralabauthenticationservice.business.services.AccessTokenValidationService;
import fhict.nl.infralabauthenticationservice.business.services.FHICTTokenExchangeService;
import fhict.nl.infralabauthenticationservice.configuration.security.isauthenticated.IsAuthenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController{
    private FHICTTokenExchangeService fhictTokenExchangeService;
    private AccessTokenValidationService accessTokenValidationService;
    @GetMapping
    public String authorize (@RequestParam("code") String code, HttpServletResponse response, HttpServletRequest request) throws IOException, JSONException {
        // If the response is 200 = redirect to localhost and save the claims,
        // If 400 - token was not validated =  show error page
        try {

            // check role
            // teacher -> we check if the email is Peter's -> redirect to admin page
            // student -> redirect to certificate page
            // else -> access denied

            String token = fhictTokenExchangeService.exchangeCodeForToken(code);
            String validatedToken = accessTokenValidationService.validateToken(token);

//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication != null && authentication.isAuthenticated()) {
//                for (GrantedAuthority authority : authentication.getAuthorities()) {
//                    String role = authority.getAuthority();
//                    // Check if the user has a specific role
//                    if ("role_student".equals(role)) {
//                        response.sendRedirect("http://localhost:3000/certificates");
//                        break;
//                    } else if ("role_teacher".equals(role)){
//                        response.sendRedirect("http://localhost:3000/admin");
//                        break;
//                    }
//                }
//            }

            System.out.println(code);
            response.sendRedirect("http://localhost:3000/auth/?auth=" + validatedToken);
            return "";
        } catch (WebClientResponseException e) {
            return "xd";
        }
    }

//    private final FHICTTokenExchangeService fhictTokenExchangeService;
//
//    @GetMapping
//    public ResponseEntity<String> exchangeToken(@RequestParam(value = "code", required = false) String code) {
//        // get the code and return the access token
//        String token = fhictTokenExchangeService.exchangeCodeForToken(code);
//        return ResponseEntity.ok(token);
//    }
}
