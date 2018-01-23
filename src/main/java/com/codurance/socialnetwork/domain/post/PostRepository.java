package com.codurance.socialnetwork.domain.post;

import java.util.List;

public interface PostRepository {
    void save(Post post);

    List<Post> findByUserName(String userName);

    List<Post> getPostsByUsers(List<String> userNames);
}
