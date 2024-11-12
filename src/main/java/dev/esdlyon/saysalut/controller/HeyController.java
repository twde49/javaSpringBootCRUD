package dev.esdlyon.saysalut.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeyController {

    public static final String greeting = "bonjour à toi, %s!";

    @GetMapping("/hey")
    public String sayHey() {
        return "Hey You my friend!";
    }

    @GetMapping("/name")
    public Salute getName(
            @RequestParam(value = "name", defaultValue = "Michmich") String name
    ) {
        return new Salute(String.format(greeting, name));
    }

    public record Salute(String content) {
    }
}
