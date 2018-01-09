package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.infrastructure.Console;

import java.util.List;

public class DisplayTimeLineCommand implements Command {
    private final Console console;
    private Posts postRepository;
    private String username;

    public DisplayTimeLineCommand(Console console, Posts postRepository, String username) {
        this.console = console;
        this.postRepository = postRepository;
        this.username = username;
    }

    public void execute() {
        List<Post> userPosts = postRepository.findByUserName(username);
        userPosts.forEach(userPost -> console.printLine("I love the weather today (5 minutes ago)"));
    }
}
