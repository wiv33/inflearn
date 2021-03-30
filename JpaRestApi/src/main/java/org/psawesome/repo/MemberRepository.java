package org.psawesome.repo;

import org.psawesome.domain.Member;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MemberRepository extends ReactiveCrudRepository<Member, Long> {

    @Query("select * from Member where name = :name")
    Flux<Member> findByName(String name);

}