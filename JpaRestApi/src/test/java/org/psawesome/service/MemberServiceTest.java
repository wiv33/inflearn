package org.psawesome.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.psawesome.domain.Address;
import org.psawesome.domain.Member;
import org.psawesome.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Autowired
    MemberRepository repository;

    @Test
    void testRepositoryJoin() {
        // given
        Member member = Member.builder().memberId(1L).name("state").address(new Address()).build();

        repository.save(member).subscribe(System.out::println);

        this.service.join(Member.builder().memberId(1L).name("state").address(new Address()).build())
                .log()
                .as(StepVerifier::create)
                .consumeErrorWith(consume ->
                        assertTrue(consume.getMessage().contains("이미 존재하는 계정입니다.")))
                .verify();
    }
}