package com.codurance.socialnetwork.infrastructure.user;

import com.codurance.socialnetwork.domain.user.Users;

public class InMemoryUsersRepository implements Users {
    @Override
    public void addFollower(String follower, String followed) {
        throw new UnsupportedOperationException();
    }
}
