package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.Posts;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostCommand {

    private Posts postRepository;
    private final String postMessage;
    private final String postAuthor;
    private final LocalDateTime postCreationDate;

    public PostCommand(Posts postRepository, String postMessage, String postAuthor, LocalDateTime postCreationDate) {
        this.postRepository = postRepository;
        this.postMessage = postMessage;
        this.postAuthor = postAuthor;
        this.postCreationDate = postCreationDate;
    }

    public void execute() {
        postRepository.save(new Post(postMessage, postAuthor, postCreationDate));
    }
}
