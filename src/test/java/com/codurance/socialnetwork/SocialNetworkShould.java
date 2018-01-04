package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.Post;
import com.codurance.socialnetwork.domain.Posts;
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
public class SocialNetworkShould {
    @Mock
    private Console console;

    @Mock
    private Clock clock;

    @Mock
    private Posts postRepository;
    private Post ALICE_POST = new Post("I love the weather today", "Alice", LocalDateTime.of(2018, 1, 1, 0, 0));;

    @Test
    public void
    save_a_post_when_receive_post_command() {
        given(console.readLine())
                .willReturn("Alice -> I love the weather today");

        given(clock.now())
                .willReturn(LocalDateTime.of(2018, 1, 1, 0, 5));

        SocialNetwork socialNetwork = new SocialNetwork(console, clock, postRepository);
        socialNetwork.run();

        verify(postRepository).save(ALICE_POST);
    }
}
