package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.post.Post;
import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
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
public class SocialNetworkShould {

    private static final String ALICE_MESSAGE = "I love the weather today";
    private static final String ALICE_USERNAME = "Alice";
    private static final LocalDateTime ALICE_POST_DATE = LocalDateTime.of(2018, 1, 1, 0, 0);
    private static final String EXIT_COMMAND = "exit";
    private static final String CHARLIE_USERNAME = "Charlie";
    private static final String ALICE_POST_PRINTED = "I love the weather today (5 minutes ago)";
    private static final String ALICE_POST_COMMAND = "Alice -> I love the weather today";
    private static final Post ALICE_POST = new Post(ALICE_MESSAGE, ALICE_USERNAME, ALICE_POST_DATE);

    @Mock
    private Console console;

    @Mock
    private Clock clock;

    @Mock
    private Posts postRepository;

    @Mock
    private Users userRepository;

    private SocialNetwork socialNetwork;

    @Before
    public void setUp() {
        socialNetwork = new SocialNetwork(console, clock, postRepository, userRepository);
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
                .willReturn(ALICE_POST_DATE);

        socialNetwork.run();

        verify(console).printLine(ALICE_POST_PRINTED);
    }

    @Test
    public void
    save_the_new_follower_of_a_user() {
        given(console.readLine())
                .willReturn("Charlie follows Alice")
                .willReturn(EXIT_COMMAND);

        socialNetwork.run();

        verify(userRepository).addFollower(CHARLIE_USERNAME, ALICE_USERNAME);
    }

    @Test
    public void
    print_invalid_command_when_the_command_doesnt_exist() {
        given(console.readLine())
                .willReturn("asdf asdf asdf asfd asf")
                .willReturn(EXIT_COMMAND);

        socialNetwork.run();

        verify(console).printLine("Invalid command.");
    }
}
