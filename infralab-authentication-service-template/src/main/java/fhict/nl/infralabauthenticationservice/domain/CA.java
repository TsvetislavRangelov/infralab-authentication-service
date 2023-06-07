package fhict.nl.infralabauthenticationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CA{
    private String refid;
    private String descr;
    private String serial;
    private String cert;
    private String prvkey;
    private String trust;
    private String randomserial;
}
