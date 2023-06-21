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
@Table(name ="ca")
@AllArgsConstructor
@NoArgsConstructor

public class CAEntity{

    @Id
    private String refid;
    private String descr;
    private String serial;
    private String cert;
    private String prvkey;
    private String trust;
    private String randomserial;

}
