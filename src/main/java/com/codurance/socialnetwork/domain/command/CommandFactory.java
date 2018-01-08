package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

import java.util.regex.Pattern;

public class CommandFactory {
    private final Clock clock;
    private final Console console;
    private final Posts postRepository;
    private final Users userRepository;

    public CommandFactory(Clock clock, Console console, Posts postRepository, Users userRepository) {
        this.clock = clock;
        this.console = console;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Command create(String input) {
        if (Pattern.matches("^[A-z]+", input)) {
            return new DisplayTimeLineCommand(console);
        }

        if (Pattern.matches("^[A-z]+ follows [A-z]+", input)) {
            String[] commandDetails = input.split(" follows ");
            return new FollowUserCommand(userRepository, commandDetails[0], commandDetails[1]);
        }

        String[] commandDetails = input.split(" -> ");
        return new PostCommand(postRepository, commandDetails[1], commandDetails[0], clock.now());
    }
}
