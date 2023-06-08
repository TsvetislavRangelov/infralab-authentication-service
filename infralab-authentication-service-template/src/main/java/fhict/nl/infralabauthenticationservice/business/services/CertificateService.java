package fhict.nl.infralabauthenticationservice.business.services;

import fhict.nl.infralabauthenticationservice.domain.Certificate;
import fhict.nl.infralabauthenticationservice.domain.InfralabCertificate;
import fhict.nl.infralabauthenticationservice.domain.OpenVPNConfig;
import org.springframework.boot.configurationprocessor.json.JSONException;

import javax.net.ssl.SSLException;
import java.util.List;


public interface CertificateService{
       InfralabCertificate getCertForUser(String email);
}
