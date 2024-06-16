package org.example.model;

public enum Role {
    WRITE("write"),
    READ("read");

    private final String roleType;

    Role(String roleType) {
        this.roleType = roleType;
    }

}


