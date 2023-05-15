package fhict.nl.infralabauthenticationservice.business.services;

import fhict.nl.infralabauthenticationservice.domain.AuthToken;

public interface FHICTTokenExchangeService {
    String exchangeCodeForToken(String code);
}
