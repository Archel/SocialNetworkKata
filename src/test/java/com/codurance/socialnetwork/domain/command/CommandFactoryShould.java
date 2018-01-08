package com.codurance.socialnetwork.domain.command;


import com.codurance.socialnetwork.domain.post.Posts;
import com.codurance.socialnetwork.domain.user.Users;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CommandFactoryShould {

    public static final String POST_COMMAND = "Alice -> Hello world";
    public static final String DISPLAY_TIMELINE_COMMAND = "Alice";
    public static final String FOLLOW_USER_COMMAND = "Charlie follows Alice";
    public static final String AN_INVALID_COMMAND = "zxcvxzcv zvxcczxv xzcvzxc v adfsdf";
    
    @Mock
    private Clock clock;

    @Mock
    private Console console;

    @Mock
    private Posts postRepository;

    @Mock
    private Users userRepository;

    private CommandFactory commandFactory;

    @Before
    public void setUp() {
        commandFactory = new CommandFactory(clock, console, postRepository, userRepository);
    }

    @Test
    public void
    returns_a_post_command_when_receives_the_post_command_represented_as_string() throws InvalidCommandException {
        Command postCommand = commandFactory.create(POST_COMMAND);

        assertThat(postCommand, instanceOf(PostCommand.class));
    }

    @Test
    public void
    returns_a_display_timeline_command_when_receives_the_display_timeline_command_represented_as_string() throws InvalidCommandException {
        Command displayTimeLineCommand = commandFactory.create(DISPLAY_TIMELINE_COMMAND);

        assertThat(displayTimeLineCommand, instanceOf(DisplayTimeLineCommand.class));
    }

    @Test
    public void
    returns_a_follow_user_command_when_receives_the_follow_user_command_represented_as_string() throws InvalidCommandException {
        Command followUserCommand = commandFactory.create(FOLLOW_USER_COMMAND);

        assertThat(followUserCommand, instanceOf(FollowUserCommand.class));
    }

    @Test(expected = InvalidCommandException.class)
    public void
    throws_an_exception_if_the_command_represented_as_string_is_not_correct() throws InvalidCommandException {
        commandFactory.create(AN_INVALID_COMMAND);
    }
}