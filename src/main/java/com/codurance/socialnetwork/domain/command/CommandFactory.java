package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.PostPrinter;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
import com.codurance.socialnetwork.infrastructure.Clock;

import java.util.regex.Pattern;

public class CommandFactory {
    private final Clock clock;
    private final PostPrinter postPrinter;
    private final Posts postRepository;
    private final Users userRepository;

    public CommandFactory(Clock clock, PostPrinter postPrinter, Posts postRepository, Users userRepository) {
        this.clock = clock;
        this.postPrinter = postPrinter;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Command create(String input) throws InvalidCommandException {
        if (Pattern.matches("^[A-z]+", input)) {
            return new DisplayTimeLineCommand(postPrinter, postRepository, input);
        }

        if (Pattern.matches("^[A-z]+ follows [A-z]+", input)) {
            String[] commandDetails = input.split(" follows ");
            return new FollowUserCommand(userRepository, commandDetails[1], commandDetails[0]);
        }

        if(Pattern.matches("^[A-z]+ -> [\\w\\s!?'.]+", input)) {
            String[] commandDetails = input.split(" -> ");
            return new PostCommand(postRepository, commandDetails[1], commandDetails[0], clock.now());
        }

        if(Pattern.matches("[A-z]+ wall", input)) {
            String username = input.replace(" wall", "");
            return new DisplayWallCommand(postPrinter, postRepository, userRepository, username);
        }

        throw new InvalidCommandException();
    }
}
