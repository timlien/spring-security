package com.tingshulien.spring.session.service;

import com.tingshulien.spring.session.model.User;

public interface AuthService {

    User register(String username, String password);

    boolean changePassword(String username, String oldPassword, String newPassword);

}
