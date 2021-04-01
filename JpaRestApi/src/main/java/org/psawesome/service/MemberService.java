package org.psawesome.service;

import lombok.RequiredArgsConstructor;
import org.psawesome.domain.Member;
import org.psawesome.repo.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     *
     * @return member
     */
    @Transactional(readOnly = false)
    public Mono<Member> join(Member member) {
        return this.memberRepository.findByName(member.getName())
                .hasElements()
                .flatMap(hasEl -> hasEl ?
                        Mono.error(new IllegalArgumentException("이미 존재하는 계정입니다.")) :
                        this.memberRepository.save(member.setAsNew()));
    }

    /**
     *
     * @return Flux Member
     */
    public Flux<Member> findMembers() {
        return this.memberRepository.findAll();
    }

    public Mono<Member> findOne(Long id) {
        return this.memberRepository.findById(id);
    }

}
