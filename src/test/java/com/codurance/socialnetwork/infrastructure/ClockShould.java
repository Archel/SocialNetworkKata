package com.codurance.socialnetwork.infrastructure;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(JUnitParamsRunner.class)
public class ClockShould {

    private static final LocalDateTime NOW = LocalDateTime.of(2018, 12, 17, 1, 50, 50);

    @Mock
    private Clock clock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Parameters({
            "1, 45, 0, 5 minutes ago",
            "1, 50, 40, 10 seconds ago",
            "1, 50, 49, 1 second ago",
            "1, 49, 40, 1 minute ago",
    })
    public void
    return_the_time_ago_from_a_date(int hour, int minute, int second, String timeAgoExpected) {
        LocalDateTime from = LocalDateTime.of(2018, 12, 17, hour, minute, second);
        given(clock.now()).willReturn(NOW);
        given(clock.ago(from)).willCallRealMethod();

        String timeAgo = clock.ago(from);

        assertEquals(timeAgoExpected, timeAgo);
    }
}
