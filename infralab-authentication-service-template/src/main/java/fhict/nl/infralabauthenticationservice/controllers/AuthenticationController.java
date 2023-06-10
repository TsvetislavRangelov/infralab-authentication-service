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
@CrossOrigin(origins = {"https://infralab.fontysict.nl", "https://infralab.fontysict.nl"})
public class AuthenticationController{

    //The client application initiates the flow by directing the resource owner to the authentication server (identity.fhict.nl).
    //The resource owner (the user) authenticates with the auth server through their user-agent. NOTE: the client app plays no part in the authentication process and never sees the user’s credentials
    //The auth server returns an authorization code to the client app, via a redirect. NOTE: This is not an access token and does NOT provide access to the resource server
    //The client application shows the authentication server the authorization code and the authentication server returns an access token
    //The client application can now access the resource server (api.fhict.nl) with the access token
    private FHICTTokenExchangeService fhictTokenExchangeService;
    private AccessTokenValidationService accessTokenValidationService;
    @GetMapping
    public String authorize (@RequestParam("code") String code, HttpServletResponse response, HttpServletRequest request) throws IOException, JSONException {
        try {
            String token = fhictTokenExchangeService.exchangeCodeForToken(code);
            //if response is 200, the claims of the user will be returned, if 400 - the token can not be verified
            String validatedToken = accessTokenValidationService.validateToken(token);
            response.sendRedirect("https://infralab.fontysict.nl/auth/?auth=" + validatedToken);
            return "";
        } catch (WebClientResponseException e) {
            return "error";
        }
    }
}
