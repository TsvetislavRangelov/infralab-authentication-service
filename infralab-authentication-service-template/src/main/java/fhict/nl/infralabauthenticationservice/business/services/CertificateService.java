package fhict.nl.infralabauthenticationservice.business.services;

import fhict.nl.infralabauthenticationservice.domain.Certificate;
import org.springframework.boot.configurationprocessor.json.JSONException;

import javax.net.ssl.SSLException;
import java.util.List;


public interface CertificateService{
       Certificate getCertificate (String name) throws SSLException, JSONException;
       List<Certificate> test ();
}
