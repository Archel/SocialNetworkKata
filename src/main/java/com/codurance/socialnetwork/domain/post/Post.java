package com.codurance.socialnetwork.domain.post;

import java.time.LocalDate;
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

    public String getMessageWithUserAt(LocalDateTime now) {
        return author + " - " + message + " (" + ago(now)+ ")";
    }

    private String ago(LocalDateTime now) {
        long timediff = now.atZone(ZoneOffset.UTC).toInstant().toEpochMilli() - creationDate.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();

        if (timediff > (1000 * 60)) {
            return (timediff / 1000) / 60 + " minutes ago";
        }

        return (timediff / 1000) +" seconds ago";
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

}
