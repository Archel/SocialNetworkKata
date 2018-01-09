package com.codurance.socialnetwork.domain.post;

import java.util.List;

public interface Posts {
    void save(Post post);

    List<Post> findByUserName(String userName);

    List<Post> getPostsByUsers(List<String> userNames);
}
