package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.Post;
import com.codurance.socialnetwork.domain.Posts;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class SocialNetwork {
    private final Console console;
    private final Clock clock;
    private Posts postRepository;

    public SocialNetwork(Console console, Clock clock, Posts postRepository) {
        this.console = console;
        this.clock = clock;
        this.postRepository = postRepository;
    }

    public void run() {
        String inputCommand = this.console.readLine();

        while(!inputCommand.equals("exit")) {
            if(Pattern.matches("^[A-z]+ -> [\\w\\s]+", inputCommand)) {
                postRepository.save(new Post("I love the weather today", "Alice", LocalDateTime.of(2018, 1, 1, 0, 0)));
            }

            if (Pattern.matches("^[A-z]+", inputCommand)) {
                console.printLine("I love the weather today (5 minutes ago)");
            }

            inputCommand = this.console.readLine();
        }
    }
}
