package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.command.Command;
import com.codurance.socialnetwork.domain.command.CommandFactory;
import com.codurance.socialnetwork.domain.command.InvalidCommandException;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

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
            executeCommand(inputCommand);
            inputCommand = this.console.readLine();
        }
    }

    private void executeCommand(String inputCommand) {
        CommandFactory commandFactory = new CommandFactory(clock, console, postRepository, userRepository);

        try {
            Command command = commandFactory.create(inputCommand);
            command.execute();
        } catch (InvalidCommandException e) {
            console.printLine("Invalid command.");
        }
    }
}
