package fhict.nl.infralabauthenticationservice.controllers;

import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import fhict.nl.infralabauthenticationservice.domain.Certificate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/test")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TestController{
    private CertificateService service;
    @GetMapping
    public ResponseEntity<Object> getCertificateForStudent () {
       List<Certificate> response = service.test();
       return ResponseEntity.ok(response);
    }
}
