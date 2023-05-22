package fhict.nl.infralabauthenticationservice.business.impl;

import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService{

    @Override
    public String getCertificates () {
        String endpoint = "https://172.16.1.1/api/v1/system/certificate";
        WebClient client = WebClient.create();

        return client.get()
                .uri(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization" ,"61646d696e 8904905528bec8d123b7a6d502a4b3ae")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    }

