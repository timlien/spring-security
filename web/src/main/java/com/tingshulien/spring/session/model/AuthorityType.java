package com.tingshulien.spring.session.model;

import lombok.Getter;

@Getter
public enum AuthorityType {

    ADMIN           ("ADMIN",           false),

    SUPPORT         ("PASSWORD",        true);

    private final String name;

    private final boolean enableByDefault;

    AuthorityType(String name, boolean enableByDefault) {
        this.name = name;
        this.enableByDefault = enableByDefault;
    }

}
