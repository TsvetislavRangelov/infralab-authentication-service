package fhict.nl.infralabauthenticationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfralabCertificate{
    //openvpn
    private String dev_mode;
    private String data_ciphers;
    private String name; 
    private String data_ciphers_fallback;
    private String digest;
    private String local_port;
    private String protocol;
    private String tls;

    //ca
    private String ca_cert;

    //certificates
    private String cert;
    private String prvkey;
}
