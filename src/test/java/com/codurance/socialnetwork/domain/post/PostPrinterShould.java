package com.codurance.socialnetwork.domain.post;

import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostPrinterShould {
    private static final LocalDateTime ALICE_POST_DATE = LocalDateTime.of(2018, 1, 1, 0, 1);

    @Mock
    private Console console;

    @Mock
    private Clock clock;

    @Test
    public void
    print_a_post_in_timeline_format() {
        Post post = new Post("I love the weather today", "Alice", ALICE_POST_DATE);
        PostPrinter postPrinter = new PostPrinter(console, clock);
        given(clock.ago(post.getCreationDate())).willReturn("5 minutes ago");

        postPrinter.printForTimeLine(post);

        verify(console).printLine("I love the weather today (5 minutes ago)");
    }

    @Test
    public void
    print_a_post_in_wall_format() {
        Post post = new Post("I love the weather today", "Alice", ALICE_POST_DATE);
        PostPrinter postPrinter = new PostPrinter(console, clock);
        given(clock.ago(post.getCreationDate())).willReturn("5 minutes ago");

        postPrinter.printForWall(post);

        verify(console).printLine("Alice - I love the weather today (5 minutes ago)");
    }
}
