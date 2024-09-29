package com.mires.common.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    NGO(0),
    FIRM(1),
    USER(2),
    INVESTOR(3),
    ;

    private final int id;

    AccountType(final int id) {
        this.id = id;
    }
}
