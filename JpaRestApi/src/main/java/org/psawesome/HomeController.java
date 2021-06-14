package org.psawesome;

import lombok.RequiredArgsConstructor;
import org.psawesome.domain.Member;
import org.psawesome.repo.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    @RequestMapping("/hello/{memberId}")
    public Mono<String> homePage(Model model, @PathVariable Long memberId) {
        model.addAttribute("data", memberRepository.findById(memberId)
                                                   .log("find Member :: ")
                                                   .defaultIfEmpty(Member.builder()
                                                                         .id(3L)
                                                                         .name("grade")
                                                                         .build()));
        return Mono.just("hello");
    }
}
