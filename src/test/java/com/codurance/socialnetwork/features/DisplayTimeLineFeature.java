package com.codurance.socialnetwork.features;

import com.codurance.socialnetwork.SocialNetwork;
import com.codurance.socialnetwork.domain.command.CommandFactory;
import com.codurance.socialnetwork.infrastructure.ConsolePostPrinter;
import com.codurance.socialnetwork.domain.post.PostRepository;
import com.codurance.socialnetwork.domain.user.UserRepository;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;
import com.codurance.socialnetwork.infrastructure.post.InMemoryPostRepository;
import com.codurance.socialnetwork.infrastructure.user.InMemoryUserRepositoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DisplayTimeLineFeature {
    private static final LocalDateTime NOW = LocalDateTime.of(2018, 1, 1, 0, 2);
    private static final LocalDateTime BOB_SECOND_POST_DATE = LocalDateTime.of(2018, 1, 1, 0, 1);
    private static final LocalDateTime BOB_FIRST_POST_DATE = LocalDateTime.of(2018, 1, 1, 0, 0);
    private static final String BOB_FIRST_POST_COMMAND = "Bob -> Damn! We lost!";
    private static final String BOB_SECOND_POST_COMMAND = "Bob -> Good game though.";
    private static final String DISPLAY_BOB_TIMELINE_COMMAND = "Bob";
    private static final String EXIT_COMMAND = "exit";
    private static final String BOB_TIMELINE_POST_TWO = "Good game though. (1 minute ago)";
    private static final String BOB_TIMELINE_POST_ONE = "Damn! We lost! (2 minutes ago)";

    @Mock
    private Console console;

    @Mock
    private Clock clock;

    private SocialNetwork socialNetwork;

    @Before
    public void setUp() {
        CommandFactory commandFactory = initializeCommandFactory();
        socialNetwork = new SocialNetwork(console, commandFactory);
    }

    private CommandFactory initializeCommandFactory() {
        PostRepository postRepository = new InMemoryPostRepository();
        UserRepository userRepository = new InMemoryUserRepositoryRepository();
        ConsolePostPrinter consolePostPrinter = new ConsolePostPrinter(console, clock);
        return new CommandFactory(clock, consolePostPrinter, postRepository, userRepository);
    }

    @Test
    public void
    display_the_posts_posted_previously_by_an_user() {
        given(console.readLine())
                .willReturn(BOB_FIRST_POST_COMMAND)
                .willReturn(BOB_SECOND_POST_COMMAND)
                .willReturn(DISPLAY_BOB_TIMELINE_COMMAND)
                .willReturn(EXIT_COMMAND);

        given(clock.now())
                .willReturn(BOB_FIRST_POST_DATE)
                .willReturn(BOB_SECOND_POST_DATE)
                .willReturn(NOW);

        given(clock.ago(any(LocalDateTime.class))).willCallRealMethod();

        socialNetwork.run();

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine(BOB_TIMELINE_POST_TWO);
        inOrder.verify(console).printLine(BOB_TIMELINE_POST_ONE);
    }

}
