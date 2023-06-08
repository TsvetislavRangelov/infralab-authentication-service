package fhict.nl.infralabauthenticationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenVPNConfig{
    private String vpnid;
    private String descr;
    private String data_ciphers;
    private String tls;
    private String data_ciphers_fallback;
    private String digest;
    private String dev_mode;
    private String protocol;
    private String localport;
}
