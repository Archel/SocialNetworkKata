package com.codurance.socialnetwork.domain.post;

import com.codurance.socialnetwork.domain.user.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class Post {
    private final String message;
    private final LocalDateTime creationDate;
    private final User author;

    public Post(String message, String author, LocalDateTime creationDate) {
        this.message = message;
        this.author = new User(author);
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
                Objects.equals(creationDate, post.creationDate) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {

        return Objects.hash(message, creationDate, author);
    }

    public String getAuthor() {
        return author.getUserName();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
