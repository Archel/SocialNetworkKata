package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.PostPrinter;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.User;

import java.util.List;

public class DisplayTimeLineCommand implements Command {
    private final PostPrinter postPrinter;
    private Posts postRepository;
    private final User user;

    public DisplayTimeLineCommand(PostPrinter postPrinter, Posts postRepository, User user) {
        this.postPrinter = postPrinter;
        this.postRepository = postRepository;
        this.user = user;
    }

    public void execute() {
        List<Post> userPosts = postRepository.findByUserName(user.getUserName());
        userPosts.forEach(postPrinter::printForTimeLine);
    }
}
