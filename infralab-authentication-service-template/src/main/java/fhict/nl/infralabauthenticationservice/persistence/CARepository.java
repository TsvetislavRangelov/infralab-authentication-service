package fhict.nl.infralabauthenticationservice.persistence;

import fhict.nl.infralabauthenticationservice.persistence.entities.CAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CARepository extends JpaRepository<CAEntity, String>{
}
