package org.example.model;

public class User {

     private Role role;

     private Long userId;

    public Role getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }

    public User(Role role, Long userId) {
        this.role = role;
        this.userId = userId;
    }
}
