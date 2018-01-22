package com.codurance.socialnetwork.domain.post;

import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;
import org.junit.Before;
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
    public static final Post ALICE_POST = new Post("I love the weather today", "Alice", ALICE_POST_DATE);
    private PostPrinter postPrinter;

    @Before
    public void setUp() {
        postPrinter = new PostPrinter(console, clock);
        given(clock.ago(ALICE_POST.getCreationDate())).willReturn("5 minutes ago");
    }

    @Test
    public void
    print_a_post_in_timeline_format() {
        postPrinter.printForTimeLine(ALICE_POST);

        verify(console).printLine("I love the weather today (5 minutes ago)");
    }

    @Test
    public void
    print_a_post_in_wall_format() {
        postPrinter.printForWall(ALICE_POST);

        verify(console).printLine("Alice - I love the weather today (5 minutes ago)");
    }
}
