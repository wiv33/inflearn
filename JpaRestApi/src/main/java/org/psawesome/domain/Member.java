package org.psawesome.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Persistable<Long> {

    @Id
    private Long id;
    private String name;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return newProduct || id == null;
    }

    public Member setAsNew() {
        this.newProduct = true;
        return this;
    }
}
