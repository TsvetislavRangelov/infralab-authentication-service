package fhict.nl.infralabauthenticationservice.business.impl;

import fhict.nl.infralabauthenticationservice.business.CertificateConverter;
import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import fhict.nl.infralabauthenticationservice.domain.Certificate;
import fhict.nl.infralabauthenticationservice.persistence.CertificateRepository;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService{
    private final CertificateRepository repository;

    @Override
    public List<Certificate> test () {
        return repository.findAll().stream().map(CertificateConverter::convert)
                .collect(Collectors.toList());
    }

    //Web client that bypasses SSL verification
    public WebClient createWebClient () throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

    @Override
    public Certificate getCertificate (String name) throws SSLException, JSONException {

        String endpoint = "https://172.16.1.1/api/v1/system/certificate";
        WebClient client = createWebClient();

        //Sends request to pfSense
        String response = client.get()
                .uri(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", System.getenv("PFSENSE_AUTH_TOKEN"))
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //Convert response to JSONArray which contains only the certificates
        JSONObject responseObject = new JSONObject(response);
        String certificates = responseObject.optString("data");
        JSONObject certificatesObject = new JSONObject(certificates);
        String allCertificates = certificatesObject.optString("cert");
        JSONArray allCertificatesArray = new JSONArray(allCertificates);
        Certificate result = filterCertificate(name, allCertificatesArray);

        return result;
    }




    private Certificate filterCertificate (String name, JSONArray jsonArray) throws JSONException {
        //Filter the certificates by name
        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).getString("descr").equals(name)) {

                return Certificate.builder()
                        .cert(jsonArray.getJSONObject(i).getString("crt"))
                        .descr(jsonArray.getJSONObject(i).getString("descr"))
                        .prvkey(jsonArray.getJSONObject(i).getString("prv"))
                        .refid(jsonArray.getJSONObject(i).getString("refid"))
                        .type(jsonArray.getJSONObject(i).getString("type"))
                        .build();
            }
        }
        return null;
    }
}

