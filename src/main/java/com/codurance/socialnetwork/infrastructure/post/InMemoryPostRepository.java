package com.codurance.socialnetwork.infrastructure.post;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.Posts;

import java.util.List;

public class InMemoryPostRepository implements Posts {
    @Override
    public void save(Post post) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Post> findByUserName(String userName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Post> getPostsByUsers(List<String> userNames) {
        throw new UnsupportedOperationException();
    }
}
