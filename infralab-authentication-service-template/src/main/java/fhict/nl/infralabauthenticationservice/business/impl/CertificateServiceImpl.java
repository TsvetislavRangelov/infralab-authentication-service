package fhict.nl.infralabauthenticationservice.business.impl;

import fhict.nl.infralabauthenticationservice.business.exception.InvalidUserException;
import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import fhict.nl.infralabauthenticationservice.domain.InfralabCertificate;
import fhict.nl.infralabauthenticationservice.persistence.UserRepository;
import fhict.nl.infralabauthenticationservice.persistence.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService{
    private final UserRepository userRepository;

    @Override
    public InfralabCertificate getCertForUser (String email) {
        //fix input
        email = email.replace("%40", "@").substring(0,email.length()-1);

        Optional<UserEntity> optionalUser = userRepository.findById(email);

        if (optionalUser.isEmpty()) {throw new InvalidUserException();}
        UserEntity user = optionalUser.get();

        if (user.getVpnid() == null) {return null; }

               return InfralabCertificate.builder()
                .ca_cert(user.getVpnid().getCaref().getCert())
                .data_ciphers(user.getVpnid().getData_ciphers())
                .data_ciphers_fallback(user.getVpnid().getData_ciphers_fallback())
                .tls(user.getVpnid().getTls())
                .digest(user.getVpnid().getDigest())
                .dev_mode(user.getVpnid().getDev_mode())
                .protocol(user.getVpnid().getProtocol())
                .local_port(user.getVpnid().getLocalport())
                .cert(user.getVpnid().getCertref().getCert())
                .prvkey(user.getVpnid().getCertref().getPrvkey())
                .build();
    }
}

