package com.tingshulien.spring.session.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AuthorityType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return type == authority.type && Objects.equals(user, authority.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, user);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", type=" + type +
                ", user=" + user.getId() +
                '}';
    }

    public static Authority newInstance(User user, AuthorityType type) {
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setType(type);
        return authority;
    }

}
