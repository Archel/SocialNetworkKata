package com.codurance.socialnetwork.domain.command;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.PostRepository;

class PostCommand implements Command {

    private final Post post;
    private PostRepository postRepository;


    public PostCommand(PostRepository postRepository, Post post) {
        this.postRepository = postRepository;
        this.post = post;
    }

    public void execute() {
        postRepository.save(post);
    }
}
