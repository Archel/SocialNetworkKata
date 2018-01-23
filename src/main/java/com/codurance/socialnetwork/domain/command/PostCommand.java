package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.PostRepository;
import com.codurance.socialnetwork.infrastructure.Clock;

class PostCommand implements Command {

    private PostRepository postRepository;
    private final Clock clock;
    private final String message;
    private final String author;


    public PostCommand(PostRepository postRepository, Clock clock, String message, String author) {
        this.postRepository = postRepository;
        this.clock = clock;
        this.message = message;
        this.author = author;
    }

    public void execute() {
        postRepository.save(new Post(message, author, clock.now()));
    }
}
