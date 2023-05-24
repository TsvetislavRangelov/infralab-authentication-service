package fhict.nl.infralabauthenticationservice.business.impl;

import fhict.nl.infralabauthenticationservice.business.services.CertificateService;
import fhict.nl.infralabauthenticationservice.domain.Certificate;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;


@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService{

    //Web client to bypass ssl verification
    public WebClient createWebClient () throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

    @Override
    public  String getCertificate (String name) throws SSLException {
        String endpoint = "https://172.16.1.1/api/v1/system/certificate";
        WebClient client = createWebClient();

        //sends request to pfSense
        String certificates = client.get()
                .uri(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", System.getenv("PFSENSE_AUTH_TOKEN"))
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //Temporary way of getting the cert
        int start =certificates.indexOf(name);
        String startString = certificates.substring(start);
        int end = startString.indexOf("}");
        String finalString = startString.substring(0,end);
        int crtStart = finalString.indexOf("crt");
        String crtStartString = finalString.substring(crtStart+5);
        int crtEnd = crtStartString.indexOf(",");
        return crtStartString.substring(0,crtEnd);


//        //Convert response to JSON Object
//        JSONObject response = new JSONObject(certificates);
//        //get only the certificates from the response
//        JSONArray jsonArray = response.getJSONArray("data");
//
//        JSONArray secondArray = new JSONObject(jsonArray.toString()).getJSONArray("cert");
//        System.out.println(secondArray);
        //        return filterCertificate(name, secondArray);

    }

    private Certificate filterCertificate (String name, JSONArray jsonArray) throws JSONException {
        //should find a better way to do it
        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).getString("descr").equals(name)) {
                //will probably only need the crt, if so return as string.
                return Certificate.builder().
                        caref(Long.parseLong(jsonArray.getJSONObject(i).getString("caref")))
                        .crt(jsonArray.getJSONObject(i).getString("crt"))
                        .descr(jsonArray.getJSONObject(i).getString("descr"))
                        .prv(jsonArray.getJSONObject(i).getString("prv"))
                        .refid(Long.parseLong(jsonArray.getJSONObject(i).getString("refid")))
                        .type(jsonArray.getJSONObject(i).getString("type"))
                        .build();
            }
        }
        return null;
    }
}

