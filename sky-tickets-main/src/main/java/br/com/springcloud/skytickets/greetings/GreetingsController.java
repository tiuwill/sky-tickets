package br.com.springcloud.skytickets.greetings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    private static final Logger log = LoggerFactory.getLogger(GreetingsController.class);

    @Value("${hello.message}")
    private String message;


    @GetMapping
    public GreetingsDTO getGreetings() {
        log.info("Calling greetings");
        return new GreetingsDTO(message);
    }


}
