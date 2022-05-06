package com.automation.api.pojo.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum AccountType {
    CURRENT ("current"),
    SAVINGS ("savings");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

}
