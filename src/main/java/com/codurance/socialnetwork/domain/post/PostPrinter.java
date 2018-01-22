package com.codurance.socialnetwork.domain.post;

import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

public class PostPrinter {
    private final Console console;
    private final Clock clock;

    public PostPrinter(Console console, Clock clock) {
        this.console = console;
        this.clock = clock;
    }

    public void printForTimeLine(Post post) {
        console.printLine(post.getMessage() +" ("+ clock.ago(post.getCreationDate()) + ")");
    }

    public void printForWall(Post post) {
        console.printLine(post.getAuthor() + " - " +post.getMessage() +" ("+ clock.ago(post.getCreationDate()) + ")");
    }
}
