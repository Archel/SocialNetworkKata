package com.codurance.socialnetwork.domain.post;

public interface PostPrinter {
    void printForTimeLine(Post post);

    void printForWall(Post post);
}
