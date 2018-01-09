package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

import java.util.List;

public class DisplayWallCommand implements Command {
    private Console console;
    private final Posts postRepository;
    private final Users userRepository;
    private final String userName;
    private Clock clock;

    public DisplayWallCommand(Console console, Posts postRepository, Users userRepository, String userName, Clock clock) {
        this.console = console;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userName = userName;
        this.clock = clock;
    }

    @Override
    public void execute() {
        List<String> users = userRepository.getFollowers(userName);
        users.add(userName);

        List<Post> posts = postRepository.getPostsByUsers(users);
        posts.forEach(post -> console.printLine(post.getMessageWithUserAt(clock.now())));
    }
}
