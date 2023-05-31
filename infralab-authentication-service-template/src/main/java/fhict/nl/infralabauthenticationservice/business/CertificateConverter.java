package fhict.nl.infralabauthenticationservice.business;

import fhict.nl.infralabauthenticationservice.domain.Certificate;
import fhict.nl.infralabauthenticationservice.persistence.entities.CertificateEntity;

public class CertificateConverter{

    public static Certificate convert (CertificateEntity certEntity){
        return Certificate.builder()
                .type(certEntity.getType())
                .prvkey(certEntity.getPrvkey())
                .descr(certEntity.getDescr())
                .cert(certEntity.getCert())
                .refid(certEntity.getRefid())
                .build();
    }
}
