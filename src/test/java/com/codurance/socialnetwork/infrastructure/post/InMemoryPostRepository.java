package com.codurance.socialnetwork.infrastructure.post;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.PostRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryPostRepository implements PostRepository {
    List<Post> posts = new ArrayList<>();

    @Override
    public void save(Post post) {
        posts.add(post);
        posts.sort(Comparator.comparing(Post::getCreationDate));
        Collections.reverse(posts);
    }

    @Override
    public List<Post> findByUserName(String userName) {
        return posts.stream().filter(post -> userName.equals(post.getAuthor())).collect(Collectors.toList());
    }

    @Override
    public List<Post> getPostsByUsers(List<String> userNames) {
        return posts
                .stream()
                .filter(post -> userNames.contains(post.getAuthor())).collect(Collectors.toList());
    }
}
