package fhict.nl.infralabauthenticationservice.persistence;

import fhict.nl.infralabauthenticationservice.persistence.entities.ServerCertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerCertificateRepository extends JpaRepository<ServerCertificateEntity, String>{
}
