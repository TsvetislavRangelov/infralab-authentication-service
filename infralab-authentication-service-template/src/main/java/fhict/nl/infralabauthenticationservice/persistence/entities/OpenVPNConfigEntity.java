package fhict.nl.infralabauthenticationservice.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name ="openvpn_config")
@AllArgsConstructor
@NoArgsConstructor

public class OpenVPNConfigEntity{
    @Id
    private String vpnid;
    @JoinColumn(name = "description")
    @ManyToOne
    private CertificateEntity description;
    @JoinColumn(name="caref")
    @ManyToOne
    private CAEntity caref;
    private String certref;
    private String data_ciphers;
    private String tls;
    private String data_ciphers_fallback;
    private String digest;
    private String dev_mode;
    private String protocol;
    private String localport;
}
