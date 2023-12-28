package com.tingshulien.spring.session.service;

import com.tingshulien.spring.session.model.Authority;
import com.tingshulien.spring.session.model.AuthorityType;
import com.tingshulien.spring.session.model.User;
import com.tingshulien.spring.session.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("User already exists!");
        }

        User user = User.newInstance(username, passwordEncoder.encode(password));

        Set<Authority> authorities = new HashSet<>();
        for (AuthorityType authorityType : AuthorityType.values()) {
            if (!authorityType.isEnableByDefault()) {
                continue;
            }

            authorities.add(Authority.newInstance(user, authorityType));
        }

        user.setAuthorities(authorities);

        return userRepository.save(user);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

}
