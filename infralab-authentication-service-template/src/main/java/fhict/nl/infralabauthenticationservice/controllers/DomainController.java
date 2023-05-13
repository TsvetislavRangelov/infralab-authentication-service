package fhict.nl.infralabauthenticationservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class DomainController {
    //this controller is just to verify that we're getting responses from the api

    @GetMapping
    public ResponseEntity<String> returnResponse(){
        return ResponseEntity.status(HttpStatus.OK).body("it works man");
    }

}
