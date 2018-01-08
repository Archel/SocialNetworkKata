package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

import java.util.regex.Pattern;

public class CommandFactory {
    private final Clock clock;
    private final Console console;
    private final Posts postRepository;

    public CommandFactory(Clock clock, Console console, Posts postRepository) {
        this.clock = clock;
        this.console = console;
        this.postRepository = postRepository;
    }

    public Command create(String input) {
        if (Pattern.matches("^[A-z]+", input)) {
            return new DisplayTimeLineCommand(console);
        }

        String[] commandDetails = input.split(" -> ");
        return new PostCommand(postRepository, commandDetails[1], commandDetails[0], clock.now());
    }
}
