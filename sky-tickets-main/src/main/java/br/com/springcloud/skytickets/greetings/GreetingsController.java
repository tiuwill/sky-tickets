package br.com.springcloud.skytickets.greetings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    @Value("${hello.message}")
    private String message;


    @GetMapping
    public GreetingsDTO getGreetings() {
        return new GreetingsDTO(message);
    }


}
