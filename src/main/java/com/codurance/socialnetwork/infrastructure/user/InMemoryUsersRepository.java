package com.codurance.socialnetwork.infrastructure.user;

import com.codurance.socialnetwork.domain.user.Users;

import java.util.List;

public class InMemoryUsersRepository implements Users {
    @Override
    public void addFollower(String follower, String followed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getFollowers(String username) {
        throw new UnsupportedOperationException();
    }
}
