package org.psawesome.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.psawesome.domain.Address;
import org.psawesome.domain.Member;
import org.psawesome.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.Disposable;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Autowired
    MemberRepository repository;

    @Test
    @DisplayName("동일 이름으로 저장할 때 오류 출력")
    void testRepositoryJoin() {
        // given
        Member member = Member.builder().name("state").build();

        Disposable saved = repository.save(member).subscribe();

        this.service.join(Member.builder().name("state").build())
                .log()
                .as(StepVerifier::create)
                .consumeErrorWith(consume ->
                        assertTrue(consume.getMessage().contains("이미 존재하는 계정입니다.")))
                .verify();

        saved.dispose();
    }
}