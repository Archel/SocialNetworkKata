package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.command.DisplayTimeLineCommand;
import com.codurance.socialnetwork.domain.command.FollowUserCommand;
import com.codurance.socialnetwork.domain.command.PostCommand;
import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class SocialNetwork {
    private final Console console;
    private final Clock clock;
    private Posts postRepository;
    private Users userRepository;

    public SocialNetwork(Console console, Clock clock, Posts postRepository, Users userRepository) {
        this.console = console;
        this.clock = clock;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void run() {
        String inputCommand = this.console.readLine();

        while(!inputCommand.equals("exit")) {
            commandExecutor(inputCommand);
            inputCommand = this.console.readLine();
        }
    }

    private void commandExecutor(String inputCommand) {
        if(Pattern.matches("^[A-z]+ -> [\\w\\s]+", inputCommand)) {
            PostCommand postCommand = new PostCommand(postRepository, "I love the weather today", "Alice", LocalDateTime.of(2018, 1, 1, 0, 0));
            postCommand.execute();
        }

        if (Pattern.matches("^[A-z]+", inputCommand)) {
            DisplayTimeLineCommand displayTimeLineCommand = new DisplayTimeLineCommand(console);
            displayTimeLineCommand.execute();
        }

        if (Pattern.matches("^[A-z]+ follows [A-z]+", inputCommand)) {
            FollowUserCommand followUserCommand = new FollowUserCommand(userRepository, "Charlie", "Alice");
            followUserCommand.execute();
        }
    }
}
