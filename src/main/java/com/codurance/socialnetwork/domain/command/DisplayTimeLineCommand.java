package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.PostPrinter;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

import java.util.List;

public class DisplayTimeLineCommand implements Command {
    private final PostPrinter postPrinter;
    private Posts postRepository;
    private String username;

    public DisplayTimeLineCommand(PostPrinter postPrinter, Posts postRepository, String username) {
        this.postPrinter = postPrinter;
        this.postRepository = postRepository;
        this.username = username;
    }

    public void execute() {
        List<Post> userPosts = postRepository.findByUserName(username);
        userPosts.forEach(postPrinter::printForTimeLine);
    }
}
