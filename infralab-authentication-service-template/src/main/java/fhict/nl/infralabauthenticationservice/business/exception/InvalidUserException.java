package fhict.nl.infralabauthenticationservice.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserException extends ResponseStatusException{
    public InvalidUserException(){ super(HttpStatus.NOT_FOUND, "USER_NOT_FOUND");
    }
}
