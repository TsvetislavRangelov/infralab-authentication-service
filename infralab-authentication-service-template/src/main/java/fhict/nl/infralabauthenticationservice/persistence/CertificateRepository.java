package fhict.nl.infralabauthenticationservice.persistence;

import fhict.nl.infralabauthenticationservice.persistence.entities.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<CertificateEntity, String>{

}
