package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.command.exception.InvalidCommandException;
import com.codurance.socialnetwork.infrastructure.ConsolePostPrinter;
import com.codurance.socialnetwork.domain.post.PostRepository;
import com.codurance.socialnetwork.domain.user.User;
import com.codurance.socialnetwork.domain.user.UserRepository;
import com.codurance.socialnetwork.infrastructure.Clock;

import java.util.regex.Pattern;

public class CommandFactory {
    private final Clock clock;
    private final ConsolePostPrinter consolePostPrinter;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommandFactory(Clock clock, ConsolePostPrinter consolePostPrinter, PostRepository postRepository, UserRepository userRepository) {
        this.clock = clock;
        this.consolePostPrinter = consolePostPrinter;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Command create(String input) throws InvalidCommandException {
        if (Pattern.matches("^[A-z]+", input)) {
            User user = userRepository.getOrCreateBy(input);
            return new DisplayTimeLineCommand(consolePostPrinter, postRepository, user);
        }

        if (Pattern.matches("^[A-z]+ follows [A-z]+", input)) {
            String[] commandDetails = input.split(" follows ");
            return new FollowUserCommand(userRepository, commandDetails[1], commandDetails[0]);
        }

        if(Pattern.matches("^[A-z]+ -> [\\w\\s!?'.]+", input)) {
            String[] commandDetails = input.split(" -> ");
            return new PostCommand(postRepository, clock, commandDetails[1], commandDetails[0]);
        }

        if(Pattern.matches("[A-z]+ wall", input)) {
            String username = input.replace(" wall", "");
            return new DisplayWallCommand(consolePostPrinter, postRepository, userRepository, username);
        }

        throw new InvalidCommandException();
    }
}
