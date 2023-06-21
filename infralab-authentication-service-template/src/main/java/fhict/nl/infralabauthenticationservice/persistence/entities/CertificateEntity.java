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
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="certificates")
public class CertificateEntity{
    private String refid;
    @Id
    private String descr;
    private String type;
    private String cert;
    private String prvkey;
}
