package fhict.nl.infralabauthenticationservice.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name ="server_certs")
@AllArgsConstructor
@NoArgsConstructor
public class ServerCertificateEntity{
    @Id
    private String refid;
    private String descr;

}
