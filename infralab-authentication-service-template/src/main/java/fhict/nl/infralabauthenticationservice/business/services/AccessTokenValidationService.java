package fhict.nl.infralabauthenticationservice.business.services;

import org.springframework.boot.configurationprocessor.json.JSONException;

public interface AccessTokenValidationService{
    String validateToken(String token) throws JSONException;
}
