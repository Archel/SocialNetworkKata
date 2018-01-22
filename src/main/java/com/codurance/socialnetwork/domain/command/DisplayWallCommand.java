package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.PostPrinter;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;

import java.util.List;

public class DisplayWallCommand implements Command {
    private final PostPrinter postPrinter;
    private final Posts postRepository;
    private final Users userRepository;
    private final String username;

    public DisplayWallCommand(PostPrinter postPrinter, Posts postRepository, Users userRepository, String username) {

        this.postPrinter = postPrinter;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.username = username;
    }

    @Override
    public void execute() {
        List<String> users = userRepository.getFollowers(username);
        users.add(username);

        List<Post> posts = postRepository.getPostsByUsers(users);
        posts.forEach(postPrinter::printForWall);
    }
}
