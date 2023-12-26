package com.tingshulien.spring.session.repository;

import com.tingshulien.spring.session.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    List<Authority> findByUserId(Integer userId);

}
