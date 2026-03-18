package com.mitecode.case5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Profile("case5")
public class DefaultFallbackController {

    @RequestMapping("/fallback")
    public String fallback() {
        log.info("Dentro de fallback");
        return "The service is temporarily unavailable. Please try again later.";
    }
}
