package org.psawesome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psawesome.domain.Member;
import org.psawesome.repo.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RestApiMainApplication {

    private final MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestApiMainApplication.class, args);
    }

    @Bean
    CommandLineRunner initMemberRepository() {
        return args -> {
            log.info("start insert all");
            memberRepository.saveAll(
                    List.of(Member.builder().name("john").build(),
                            Member.builder().name("matilda").build()))
                            .log()
                            .subscribe();
        };
    }
}
