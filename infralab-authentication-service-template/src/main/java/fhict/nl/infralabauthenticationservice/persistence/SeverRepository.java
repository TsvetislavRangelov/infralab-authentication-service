package fhict.nl.infralabauthenticationservice.persistence;

import fhict.nl.infralabauthenticationservice.persistence.entities.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeverRepository extends JpaRepository<ServerEntity, String>{
}
