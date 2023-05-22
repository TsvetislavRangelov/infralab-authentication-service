package fhict.nl.infralabauthenticationservice.controllers;

import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> getCertificateForStudent(){
        System.out.println("shoot");
        System.out.println(certificateService.getCertificates());

        //this is where we request the certificate after we exchange the auth code for a token
        return ResponseEntity.ok().body(null);
    }

}
