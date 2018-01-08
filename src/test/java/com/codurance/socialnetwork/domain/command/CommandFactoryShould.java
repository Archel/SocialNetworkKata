package com.codurance.socialnetwork.domain.command;


import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CommandFactoryShould {

    @Mock
    private Clock clock;

    @Mock
    private Console console;

    @Mock
    private
    Posts postRepository;

    @Mock
    Users userRepository;

    @Test
    public void
    returns_a_post_command_when_receives_the_post_command_represented_as_string() {
        CommandFactory commandFactory = new CommandFactory(clock, console, postRepository, userRepository);
        Command postCommand = commandFactory.create("Alice -> Hello world!");

        assertThat(postCommand, instanceOf(PostCommand.class));
    }

    @Test
    public void
    returns_a_display_timeline_command_when_receives_the_display_timeline_command_represented_as_string() {
        CommandFactory commandFactory = new CommandFactory(clock, console, postRepository, userRepository);
        Command displayTimeLineCommand = commandFactory.create("Alice");

        assertThat(displayTimeLineCommand, instanceOf(DisplayTimeLineCommand.class));
    }

    @Test
    public void
    returns_a_follow_user_command_when_receives_the_follow_user_command_represented_as_string() {
        CommandFactory commandFactory = new CommandFactory(clock, console, postRepository, userRepository);
        Command followUserCommand = commandFactory.create("Charlie follows Alice");

        assertThat(followUserCommand, instanceOf(FollowUserCommand.class));
    }
}