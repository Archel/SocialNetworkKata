package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.user.UserRepository;

class FollowUserCommand implements Command{
    private final UserRepository userRepository;
    private final String follower;
    private final String followed;

    public FollowUserCommand(UserRepository userRepository, String follower, String followed) {
        this.userRepository = userRepository;
        this.follower = follower;
        this.followed = followed;
    }

    public void execute() {
        userRepository.addFollower(follower, followed);
    }
}
