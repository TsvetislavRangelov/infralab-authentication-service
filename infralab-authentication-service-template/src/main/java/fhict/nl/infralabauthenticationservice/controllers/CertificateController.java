package fhict.nl.infralabauthenticationservice.controllers;

import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import fhict.nl.infralabauthenticationservice.domain.Certificate;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLException;

@RestController
@RequestMapping("/certificates")
@AllArgsConstructor
@CrossOrigin(origins = "*")

public class CertificateController {
    private CertificateService certificateService;

    //The client application initiates the flow by directing the resource owner to the authentication server (identity.fhict.nl).
    //The resource owner (the user) authenticates with the auth server through their user-agent. NOTE: the client app plays no part in the authentication process and never sees the user’s credentials
    //The auth server returns an authorization code to the client app, via a redirect. NOTE: This is not an access token and does NOT provide access to the resource server
    //The client application shows the authentication server the authorization code and the authentication server returns an access token
    //The client application can now access the resource server (api.fhict.nl) with the access token
    @GetMapping
    public ResponseEntity<Object> getCertificateForStudent() throws SSLException, JSONException {
        //the input comes after checking the corresponding certificate to the authenticated user from the db
        Certificate response = certificateService.getCertificate("Test certificate");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Object> getCertificateByStudentEmail(@Validated String email) {
        String response = certificateService.getCertForUser(email);
        return ResponseEntity.ok().body(response);
    }

}
