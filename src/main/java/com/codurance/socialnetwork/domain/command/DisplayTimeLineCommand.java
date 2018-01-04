package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.infrastructure.Console;

public class DisplayTimeLineCommand {
    private final Console console;

    public DisplayTimeLineCommand(Console console) {
        this.console = console;
    }

    public void execute() {
        console.printLine("I love the weather today (5 minutes ago)");
    }
}
