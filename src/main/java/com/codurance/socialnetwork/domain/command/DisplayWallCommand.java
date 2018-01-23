package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.infrastructure.ConsolePostPrinter;
import com.codurance.socialnetwork.domain.post.PostRepository;
import com.codurance.socialnetwork.domain.user.UserRepository;

import java.util.List;

class DisplayWallCommand implements Command {
    private final ConsolePostPrinter consolePostPrinter;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final String username;

    public DisplayWallCommand(ConsolePostPrinter consolePostPrinter, PostRepository postRepository, UserRepository userRepository, String username) {

        this.consolePostPrinter = consolePostPrinter;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.username = username;
    }

    @Override
    public void execute() {
        List<String> users = userRepository.getFollowers(username);
        users.add(username);

        List<Post> posts = postRepository.getPostsByUsers(users);
        posts.forEach(consolePostPrinter::printForWall);
    }
}
