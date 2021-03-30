package org.psawesome.service;

import lombok.RequiredArgsConstructor;
import org.psawesome.domain.Member;
import org.psawesome.repo.MemberRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     *
     * @return member
     */
    public Mono<Member> join(Member member) {
        return this.memberRepository.existsById(member.getMemberId())
                .flatMap(hasEl -> hasEl ?
                        Mono.error(new IllegalArgumentException("이미 존재하는 계정입니다.")) :
                        this.memberRepository.save(member))
                ;
    }

}
