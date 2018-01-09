package com.codurance.socialnetwork.infrastructure.user;

import com.codurance.socialnetwork.domain.user.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUsersRepository implements Users {
    Map<String, List<String>> followers = new HashMap<>();

    @Override
    public void addFollower(String follower, String followed) {
        List<String> followedFollowers = followers.get(followed);
        if (followedFollowers == null) {
            followedFollowers = new ArrayList<>();
        }

        followedFollowers.add(follower);
        followers.put(followed, followedFollowers);
    }

    @Override
    public List<String> getFollowers(String username) {
        List<String> followedFollowers = followers.get(username);

        if (followedFollowers == null) {
            return new ArrayList<>();
        }

        return followers.get(username);
    }
}
