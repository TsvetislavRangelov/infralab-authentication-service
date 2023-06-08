package fhict.nl.infralabauthenticationservice.business.impl;
import fhict.nl.infralabauthenticationservice.business.exception.InvalidUserException;
import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import fhict.nl.infralabauthenticationservice.domain.InfralabCertificate;
import fhict.nl.infralabauthenticationservice.persistence.CARepository;
import fhict.nl.infralabauthenticationservice.persistence.CertificateRepository;
import fhict.nl.infralabauthenticationservice.persistence.OpenVPNConfigRepository;
import fhict.nl.infralabauthenticationservice.persistence.UserRepository;
import fhict.nl.infralabauthenticationservice.persistence.entities.CAEntity;
import fhict.nl.infralabauthenticationservice.persistence.entities.CertificateEntity;
import fhict.nl.infralabauthenticationservice.persistence.entities.OpenVPNConfigEntity;
import fhict.nl.infralabauthenticationservice.persistence.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService{
    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;
    private final CARepository caRepository;
    private final OpenVPNConfigRepository openVPNConfigRepository;

    @Override
    public InfralabCertificate getCertForUser (String email) {
        Optional<UserEntity> optionalUser =  userRepository.findById(email);

        if (optionalUser.isEmpty()){throw new InvalidUserException();}
        UserEntity user = optionalUser.get();

        if(user.getCertificate_descr() == null || user.getCertificate_descr() == ""){
            return null;
        }
        //get data for infralab certificate
        CertificateEntity certificate = certificateRepository.findById(user.getCertificate_descr()).get();
        OpenVPNConfigEntity openVPNConfig = openVPNConfigRepository.findOpenVPNConfigEntityByDescr(user.getCertificate_descr());
        CAEntity ca = caRepository.findCAEntitiesByDescr(user.getCertificate_descr());

       return  InfralabCertificate.builder()
                .ca_cert(ca.getCert())
                .data_ciphers(openVPNConfig.getData_ciphers())
                .data_ciphers_fallback(openVPNConfig.getData_ciphers_fallback())
                .tls(openVPNConfig.getTls())
                .digest(openVPNConfig.getDigest())
                .dev_mode(openVPNConfig.getDev_mode())
                .protocol(openVPNConfig.getProtocol())
                .local_port(openVPNConfig.getLocalport())
                .cert(certificate.getCert())
                .prvkey(certificate.getPrvkey()).build();
    }
}

