package com.codurance.socialnetwork.domain.user;

import java.util.Objects;

public class User {
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUserName() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username);
    }
}
