package com.codurance.socialnetwork.domain.command;


import com.codurance.socialnetwork.domain.command.exception.InvalidCommandException;
import com.codurance.socialnetwork.infrastructure.ConsolePostPrinter;
import com.codurance.socialnetwork.domain.post.PostRepository;
import com.codurance.socialnetwork.domain.user.UserRepository;
import com.codurance.socialnetwork.infrastructure.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CommandFactoryShould {

    private static final String POST_COMMAND = "Alice -> Hello world";
    private static final String DISPLAY_TIMELINE_COMMAND = "Alice";
    private static final String FOLLOW_USER_COMMAND = "Charlie follows Alice";
    private static final String AN_INVALID_COMMAND = "zxcvxzcv zvxcczxv xzcvzxc v adfsdf";
    private static final String DISPLAY_WALL_COMMAND = "Alice wall";

    @Mock
    private Clock clock;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConsolePostPrinter consolePostPrinter;

    private CommandFactory commandFactory;

    @Before
    public void setUp() {
        commandFactory = new CommandFactory(clock, consolePostPrinter, postRepository, userRepository);
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

    @Test
    public void
    returns_a_display_wall_when_receives_the_display_wall_command_represented_as_string() throws InvalidCommandException {
        Command displayWallCommand = commandFactory.create(DISPLAY_WALL_COMMAND);

        assertThat(displayWallCommand, instanceOf(DisplayWallCommand.class));
    }

    @Test(expected = InvalidCommandException.class)
    public void
    throws_an_exception_if_the_command_represented_as_string_is_not_correct() throws InvalidCommandException {
        commandFactory.create(AN_INVALID_COMMAND);
    }
}