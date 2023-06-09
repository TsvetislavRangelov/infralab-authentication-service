package fhict.nl.infralabauthenticationservice.business.services;

import fhict.nl.infralabauthenticationservice.domain.InfralabCertificate;



public interface CertificateService{
       InfralabCertificate getCertForUser(String email);
}
