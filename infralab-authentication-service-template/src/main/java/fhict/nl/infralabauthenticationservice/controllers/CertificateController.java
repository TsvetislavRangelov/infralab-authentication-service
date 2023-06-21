package fhict.nl.infralabauthenticationservice.controllers;

import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import fhict.nl.infralabauthenticationservice.domain.InfralabCertificate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/certificates")
@AllArgsConstructor
@CrossOrigin(origins = {"https://infralab.fontysict.nl", "https://infralab.fontysict.nl"})

public class CertificateController {
    private CertificateService certificateService;

    @PostMapping
    public ResponseEntity<Object> getCertificateByStudentEmail(@RequestBody String email) {
        InfralabCertificate response = certificateService.getCertForUser(email);
        return ResponseEntity.ok().body(response);
    }
}
