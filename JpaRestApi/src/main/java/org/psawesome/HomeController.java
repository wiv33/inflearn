package org.psawesome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    @RequestMapping("/hello")
    public Mono<Rendering> homePage() {
        return Mono.just(Rendering.view("hello")
                .modelAttribute("data", "Hello World!!")
                .build()
        );
    }
}
