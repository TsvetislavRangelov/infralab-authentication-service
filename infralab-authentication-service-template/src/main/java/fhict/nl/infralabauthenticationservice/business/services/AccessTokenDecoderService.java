package fhict.nl.infralabauthenticationservice.business.services;

import fhict.nl.infralabauthenticationservice.domain.AuthToken;

public interface AccessTokenDecoderService {
     AuthToken decode(String encodedToken);
}
