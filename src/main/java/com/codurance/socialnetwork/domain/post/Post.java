package com.codurance.socialnetwork.domain.post;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class Post {
    private final String message;
    private final String author;
    private final LocalDateTime creationDate;

    public Post(String message, String author, LocalDateTime creationDate) {
        this.message = message;
        this.author = author;
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(message, post.message) &&
                Objects.equals(author, post.author) &&
                Objects.equals(creationDate, post.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, author, creationDate);
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
