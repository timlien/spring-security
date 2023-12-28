package com.tingshulien.spring.session.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @JsonIgnore
    @Column(name="username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createDate=" + createDate +
                ", authorities=" + authorities +
                '}';
    }

    public static User newInstance(String email, String username) {
        User user = new User();
        user.setUsername(email);
        user.setPassword(username);
        user.setCreateDate(LocalDateTime.now());
        return user;
    }

}
