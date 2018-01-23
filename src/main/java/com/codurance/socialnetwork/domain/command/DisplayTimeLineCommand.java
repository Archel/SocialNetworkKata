package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.infrastructure.ConsolePostPrinter;
import com.codurance.socialnetwork.domain.post.PostRepository;
import com.codurance.socialnetwork.domain.user.User;

import java.util.List;

class DisplayTimeLineCommand implements Command {
    private final ConsolePostPrinter consolePostPrinter;
    private PostRepository postRepository;
    private final User user;

    public DisplayTimeLineCommand(ConsolePostPrinter consolePostPrinter, PostRepository postRepository, User user) {
        this.consolePostPrinter = consolePostPrinter;
        this.postRepository = postRepository;
        this.user = user;
    }

    public void execute() {
        List<Post> userPosts = postRepository.findByUserName(user.getUserName());
        userPosts.forEach(consolePostPrinter::printForTimeLine);
    }
}
