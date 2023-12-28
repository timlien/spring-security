package com.tingshulien.spring.session.repository;

import com.tingshulien.spring.session.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
