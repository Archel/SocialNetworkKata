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
public class DisplayWallFeature {
    private static final String ALICE_POST_COMMAND = "Alice -> I love the weather today";
    private static final String CHARLIE_POST_COMMAND = "Charlie -> I'm in New York today! Anyone wants to have a coffee?";
    private static final String CHARLIE_FOLLOWS_ALICE_COMMAND = "Charlie follows Alice";
    private static final String DISPLAY_CHARLIE_WALL_COMMAND = "Charlie wall";
    private static final String EXIT_COMMAND = "exit";
    private static final LocalDateTime ALICE_POST_DATE = LocalDateTime.of(2018, 1, 1, 0, 0);
    private static final LocalDateTime CHARLIE_POST_DATE = LocalDateTime.of(2018, 1, 1, 0, 4, 58);
    private static final LocalDateTime NOW = LocalDateTime.of(2018, 1, 1, 0, 5);
    private static final String CHARLIE_WALL_POST_BY_CHARLIE = "Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)";
    private static final String CHARLIE_WALL_POST_BY_ALICE = "Alice - I love the weather today (5 minutes ago)";

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
    display_the_posts_posted_previously_by_a_user_and_his_followers() {
        given(console.readLine())
                .willReturn(ALICE_POST_COMMAND)
                .willReturn(CHARLIE_POST_COMMAND)
                .willReturn(CHARLIE_FOLLOWS_ALICE_COMMAND)
                .willReturn(DISPLAY_CHARLIE_WALL_COMMAND)
                .willReturn(EXIT_COMMAND);

        given(clock.now())
                .willReturn(ALICE_POST_DATE)
                .willReturn(CHARLIE_POST_DATE)
                .willReturn(NOW);

        given(clock.ago(any(LocalDateTime.class))).willCallRealMethod();

        socialNetwork.run();

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine(CHARLIE_WALL_POST_BY_CHARLIE);
        inOrder.verify(console).printLine(CHARLIE_WALL_POST_BY_ALICE);
    }
}
