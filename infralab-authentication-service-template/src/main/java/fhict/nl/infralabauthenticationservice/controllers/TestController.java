package fhict.nl.infralabauthenticationservice.controllers;

import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TestController{

    private CertificateService service;

    @GetMapping
    public ResponseEntity<Object> getCertificateForStudent () {
        service.test();
        return null;
    }
}
