package com.mitocode.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    private Integer count = 0;

    @PostMapping("/validate/{token}")
    public ResponseEntity<Void> validateToken(@PathVariable String token) {
        log.info("Validating token: {}", token);

        count++;

        if (count % 5 == 0) {
            log.warn("Simulated token validation failure for token: {}", token);
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    }
}
