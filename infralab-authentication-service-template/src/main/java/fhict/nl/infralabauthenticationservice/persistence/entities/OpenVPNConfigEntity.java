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
    private String description;
    @JoinColumn()
    @ManyToOne
    private CAEntity caref;
    @JoinColumn()
    @ManyToOne
    private CertificateEntity certref;
    private String data_ciphers;
    private String tls;
    private String data_ciphers_fallback;
    private String digest;
    private String dev_mode;
    private String protocol;
    private String localport;
}
