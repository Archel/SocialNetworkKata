package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.command.Command;
import com.codurance.socialnetwork.domain.command.CommandFactory;
import com.codurance.socialnetwork.domain.command.exception.InvalidCommandException;
import com.codurance.socialnetwork.infrastructure.Console;

public class SocialNetwork {
    public static final String EXIT_COMMAND = "exit";
    private final Console console;
    private final CommandFactory commandFactory;

    public SocialNetwork(Console console, CommandFactory commandFactory) {
        this.console = console;
        this.commandFactory = commandFactory;
    }

    public void run() {
        String inputCommand = this.console.readLine();

        while(isExit(inputCommand)) {
            executeCommand(inputCommand);
            inputCommand = this.console.readLine();
        }
    }

    private boolean isExit(String inputCommand) {
        return !inputCommand.equals(EXIT_COMMAND);
    }

    private void executeCommand(String inputCommand) {
        try {
            Command command = commandFactory.create(inputCommand);
            command.execute();
        } catch (InvalidCommandException e) {
            console.printLine("Invalid command.");
        }
    }
}
