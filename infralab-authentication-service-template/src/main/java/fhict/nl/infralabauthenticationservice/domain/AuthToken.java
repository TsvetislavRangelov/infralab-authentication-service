package fhict.nl.infralabauthenticationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    //this is the data class used to map the JWT token from FHICT to a Java Object.
    private String iss;
    private String aud;
    private Long exp;
    private Long nbf;
    private Long iat;
    private String sid;
    private String sub;
    private Long authTime;
    private String idp;
    private String name;
    private String familyName;
    private String givenName;
    private String preferredUsername;
    private ArrayList<String> role;
    private ArrayList<String> amr;
}
