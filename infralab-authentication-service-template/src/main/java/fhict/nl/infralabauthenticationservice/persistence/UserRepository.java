package fhict.nl.infralabauthenticationservice.persistence;

import fhict.nl.infralabauthenticationservice.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String>{

}
