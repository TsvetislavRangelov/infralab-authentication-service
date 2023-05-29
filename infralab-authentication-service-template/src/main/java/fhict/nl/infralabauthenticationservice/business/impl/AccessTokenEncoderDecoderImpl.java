package fhict.nl.infralabauthenticationservice.business.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhict.nl.infralabauthenticationservice.business.exception.InvalidAccessTokenException;
import fhict.nl.infralabauthenticationservice.business.services.AccessTokenDecoder;
import fhict.nl.infralabauthenticationservice.domain.AccessToken;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenDecoder {

    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            System.out.println(accessTokenEncoded);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(accessTokenEncoded);
            JsonNode classNode = jsonNode.get("urn:nl.fhict:schedule");
            JsonNode roleNode = jsonNode.get("role");
            JsonNode emailNode = jsonNode.get("email");
            JsonNode subNode = jsonNode.get("sub");
            JsonNode nameNode = jsonNode.get("name");

            List<String> roles = objectMapper.convertValue(roleNode, new TypeReference<List<String>>() {});

            return AccessToken.builder()
                    .classroom(classNode.asText())
                    .name(nameNode.asText())
                    .email(emailNode.asText())
                    .subject(subNode.asText())
                    .roles(roles).build();
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
