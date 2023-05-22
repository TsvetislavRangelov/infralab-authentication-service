package fhict.nl.infralabauthenticationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Certificate{
    private long refid;
    private String descr;
    private String type;
    private long caref;
    private String crt;
    private String prv;

}
