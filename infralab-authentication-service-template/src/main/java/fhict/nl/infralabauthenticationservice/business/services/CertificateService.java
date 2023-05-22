package fhict.nl.infralabauthenticationservice.business.services;

import fhict.nl.infralabauthenticationservice.domain.Certificate;
import org.springframework.boot.configurationprocessor.json.JSONException;

import javax.net.ssl.SSLException;

public interface CertificateService{
       String getCertificate (String name) throws SSLException, JSONException;

}
