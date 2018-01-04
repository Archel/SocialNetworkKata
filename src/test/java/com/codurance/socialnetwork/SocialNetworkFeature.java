package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.Posts;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;
import com.codurance.socialnetwork.infrastructure.post.InMemoryPostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SocialNetworkFeature {
    @Mock
    private Console console;

    @Mock
    private Clock clock;

    @Test
    public void
    interact_through_console_with_the_social_network() {
        InOrder inOrder = Mockito.inOrder(console);
        given(console.readLine())
                .willReturn("Alice -> I love the weather today")
                .willReturn("Alice")
                .willReturn("Bob -> Damn! We lost!")
                .willReturn("Bob -> Good game though.")
                .willReturn("Bob")
                .willReturn("Charlie -> I'm in New York today! Anyone wants to have a coffee?")
                .willReturn("Charlie follows Alice")
                .willReturn("Charlie wall")
                .willReturn("exit");

        given(clock.now())
                .willReturn(LocalDateTime.of(2018, 1, 1, 0, 0))
                .willReturn(LocalDateTime.of(2018, 1, 1, 0, 5));

        Posts postRepository = new InMemoryPostRepository();

        SocialNetwork socialNetwork = new SocialNetwork(console, clock, postRepository);
        socialNetwork.run();

        inOrder.verify(console).printLine("I love the weather today (5 minutes ago)");
        inOrder.verify(console).printLine("Good game though. (1 minute ago)");
        inOrder.verify(console).printLine("Damn! We lost! (2 minutes ago)");
        inOrder.verify(console).printLine("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)");
        inOrder.verify(console).printLine("Alice - I love the weather today (5 minutes ago)");

        inOrder.verifyNoMoreInteractions();
    }

}
