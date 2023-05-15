package fhict.nl.infralabauthenticationservice.controllers;

import fhict.nl.infralabauthenticationservice.business.services.FHICTTokenExchangeService;
import fhict.nl.infralabauthenticationservice.domain.AuthToken;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AuthenticationController {
    private FHICTTokenExchangeService exchangeService;


    @GetMapping
    public ResponseEntity<String> authorize(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        String token = exchangeService.exchangeCodeForToken(code);
        response.addHeader("authorization", token);
        response.sendRedirect("http://localhost:3000/");
        return ResponseEntity.ok(token);
    }
}
