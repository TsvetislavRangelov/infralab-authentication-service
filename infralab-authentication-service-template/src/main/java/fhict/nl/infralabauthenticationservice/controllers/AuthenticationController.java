package fhict.nl.infralabauthenticationservice.controllers;
import fhict.nl.infralabauthenticationservice.business.services.AccessTokenValidationService;
import fhict.nl.infralabauthenticationservice.business.services.FHICTTokenExchangeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.io.IOException;

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

            System.out.println(code);
            response.sendRedirect("http://localhost:3000/auth/?auth=" + validatedToken);
            return "";
        } catch (WebClientResponseException e) {
            return "error";
        }
    }
}
