package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

import java.util.List;

public class DisplayTimeLineCommand implements Command {
    private final Console console;
    private Posts postRepository;
    private String username;
    private Clock clock;

    public DisplayTimeLineCommand(Console console, Posts postRepository, String username, Clock clock) {
        this.console = console;
        this.postRepository = postRepository;
        this.username = username;
        this.clock = clock;
    }

    public void execute() {
        List<Post> userPosts = postRepository.findByUserName(username);
        userPosts.forEach(post -> console.printLine(post.getMessageAt(clock.now())));
    }
}
