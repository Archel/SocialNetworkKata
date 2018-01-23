package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.command.CommandFactory;
import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.infrastructure.ConsolePostPrinter;
import com.codurance.socialnetwork.domain.post.PostRepository;
import com.codurance.socialnetwork.domain.user.User;
import com.codurance.socialnetwork.domain.user.UserRepository;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SocialNetworkShould {

    private static final String ALICE_MESSAGE = "I love the weather today";
    private static final String ALICE_USERNAME = "Alice";
    private static final LocalDateTime ALICE_POST_DATE = LocalDateTime.of(2018, 1, 1, 0, 0);
    private static final String EXIT_COMMAND = "exit";
    private static final String CHARLIE_USERNAME = "Charlie";
    private static final String ALICE_POST_COMMAND = "Alice -> I love the weather today";
    private static final Post ALICE_POST = new Post(ALICE_MESSAGE, ALICE_USERNAME, ALICE_POST_DATE);
    private static final String FOLLOW_COMMAND = "Alice follows Charlie";
    private static final String INVALID_COMMAND = "asdf asdf asdf asfd asf";
    private static final String INVALID_COMMAND_OUTPUT = "Invalid command.";
    private static final String CHARLIE_POST_COMMAND = "Charlie -> I'm in New York today! Anyone wants to have a coffee?";
    private static final String ALICE_WALL_COMMAND = "Alice wall";
    private static final LocalDateTime NOW = LocalDateTime.of(2018, 1, 1, 0, 5, 0);
    private static final Post CHARLIE_POST = new Post("I'm in New York today! Anyone wants to have a coffee?", "Charlie", LocalDateTime.of(2018, 1, 1, 0, 4, 45));

    @Mock
    private Console console;

    @Mock
    private Clock clock;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConsolePostPrinter consolePostPrinter;

    private SocialNetwork socialNetwork;

    @Before
    public void setUp() {
        CommandFactory commandFactory = new CommandFactory(clock, consolePostPrinter, postRepository, userRepository);
        socialNetwork = new SocialNetwork(console, commandFactory);
    }

    @Test
    public void
    save_a_post_when_receive_post_command() {
        given(console.readLine())
                .willReturn(ALICE_POST_COMMAND)
                .willReturn(EXIT_COMMAND);

        given(clock.now())
            .willReturn(ALICE_POST_DATE);

        socialNetwork.run();

        verify(postRepository).save(ALICE_POST);
    }

    @Test
    public void
    list_a_user_timeline_when_receive_the_username() {
        given(console.readLine())
                .willReturn(ALICE_POST_COMMAND)
                .willReturn(ALICE_USERNAME)
                .willReturn(EXIT_COMMAND);

        given(clock.now())
                .willReturn(ALICE_POST_DATE)
                .willReturn(ALICE_POST_DATE.plusMinutes(5));

        given(userRepository.getOrCreateBy(ALICE_USERNAME))
                .willReturn(new User(ALICE_USERNAME));

        List<Post> userList = new ArrayList<>();
        userList.add(ALICE_POST);

        given(postRepository.findByUserName(ALICE_USERNAME))
                .willReturn(userList);

        socialNetwork.run();

        verify(postRepository).findByUserName(ALICE_USERNAME);
        verify(consolePostPrinter).printForTimeLine(ALICE_POST);
    }

    @Test
    public void
    save_the_new_follower_of_a_user() {
        given(console.readLine())
                .willReturn(FOLLOW_COMMAND)
                .willReturn(EXIT_COMMAND);

        socialNetwork.run();

        verify(userRepository).addFollower(CHARLIE_USERNAME, ALICE_USERNAME);
    }

    @Test
    public void
    print_invalid_command_when_the_command_doesnt_exist() {
        given(console.readLine())
                .willReturn(INVALID_COMMAND)
                .willReturn(EXIT_COMMAND);

        socialNetwork.run();

        verify(console).printLine(INVALID_COMMAND_OUTPUT);
    }
    
    @Test
    public void
    list_a_user_wall_when_receive_the_show_wall_command() {
        given(console.readLine())
                .willReturn(ALICE_POST_COMMAND)
                .willReturn(CHARLIE_POST_COMMAND)
                .willReturn(FOLLOW_COMMAND)
                .willReturn(ALICE_WALL_COMMAND)
                .willReturn(EXIT_COMMAND);

        given(clock.now())
                .willReturn(NOW);

        given(userRepository.getFollowers(ALICE_USERNAME))
                .willReturn(new ArrayList<>(Collections.singletonList(CHARLIE_USERNAME)));

        given(postRepository.getPostsByUsers(new ArrayList<>(asList(CHARLIE_USERNAME, ALICE_USERNAME))))
                .willReturn(asList(CHARLIE_POST, ALICE_POST));

        socialNetwork.run();

        verify(userRepository).getFollowers(ALICE_USERNAME);
        verify(postRepository).getPostsByUsers(new ArrayList<>(asList(CHARLIE_USERNAME, ALICE_USERNAME)));

        InOrder inOrder = Mockito.inOrder(consolePostPrinter);
        inOrder.verify(consolePostPrinter).printForWall(CHARLIE_POST);
        inOrder.verify(consolePostPrinter).printForWall(ALICE_POST);
    }
}
