package fhict.nl.infralabauthenticationservice.persistence;

import fhict.nl.infralabauthenticationservice.persistence.entities.OpenVPNConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenVPNConfigRepository extends JpaRepository<OpenVPNConfigEntity, String>{
}
