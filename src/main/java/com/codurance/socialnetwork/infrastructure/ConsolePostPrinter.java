package com.codurance.socialnetwork.infrastructure;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.PostPrinter;

public class ConsolePostPrinter implements PostPrinter {
    private final Console console;
    private final Clock clock;

    public ConsolePostPrinter(Console console, Clock clock) {
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
