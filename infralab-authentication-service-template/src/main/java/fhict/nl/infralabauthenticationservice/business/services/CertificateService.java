package fhict.nl.infralabauthenticationservice.business.services;

import javax.net.ssl.SSLException;

public interface CertificateService{
        String getCertificates() throws SSLException;

}
