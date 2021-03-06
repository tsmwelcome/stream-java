package io.getstream.client.service;

import io.getstream.client.exception.StreamClientException;
import io.getstream.client.model.activities.AggregatedActivity;
import io.getstream.client.model.activities.SimpleActivity;
import io.getstream.client.model.beans.StreamResponse;
import io.getstream.client.model.feeds.BaseFeed;
import io.getstream.client.model.filters.FeedFilter;
import io.getstream.client.repo.StreamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlatActivityServiceImplTest {

    @Mock
    private BaseFeed feed;

    @Mock
    private StreamRepository repository;

    @Mock
    private StreamResponse response;

    private FlatActivityService<SimpleActivity> flatActivityService;

    @Before
    public void setUp() throws IOException, StreamClientException {
        when(repository.getActivities(any(BaseFeed.class), eq(SimpleActivity.class), any(FeedFilter.class))).thenReturn(response);
        flatActivityService = new FlatActivityServiceImpl<>(feed, SimpleActivity.class, repository);
    }

    @Test
    public void shouldGetActivities() throws Exception {
        StreamResponse<SimpleActivity> activities = flatActivityService.getActivities();
        verify(repository).getActivities(any(BaseFeed.class), eq(SimpleActivity.class), any(FeedFilter.class));
        assertThat(activities, notNullValue());
    }

    @Test
    public void shouldGetActivitiesWithFilter() throws Exception {
        FeedFilter filter = new FeedFilter.Builder().build();
        StreamResponse<SimpleActivity> activities = flatActivityService.getActivities(filter);
        verify(repository).getActivities(any(BaseFeed.class), eq(SimpleActivity.class), eq(filter));
        assertThat(activities, notNullValue());
    }
}