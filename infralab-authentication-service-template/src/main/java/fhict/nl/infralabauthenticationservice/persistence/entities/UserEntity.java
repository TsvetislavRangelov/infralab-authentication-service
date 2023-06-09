package fhict.nl.infralabauthenticationservice.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name ="users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity{
    @Id
    private String email;
    @JoinColumn
    @OneToOne
    private OpenVPNConfigEntity vpnid;
}
