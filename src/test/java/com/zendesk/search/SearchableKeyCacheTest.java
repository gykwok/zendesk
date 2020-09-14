package com.zendesk.search;

import com.zendesk.loader.DataCategory;
import com.zendesk.loader.JsonFileLoader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

/**
 * Created by Grace on 9/13/2020.
 */


@RunWith(MockitoJUnitRunner.class)

public class SearchableKeyCacheTest {

    @Mock
    private JsonFileLoader loader;

    @InjectMocks
    private SearchableKeyCache searchableKeyCache;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void jsonEntriesWithDifferentKeys()  throws IOException, URISyntaxException {
        when(loader.getOrganizationJsonContent()).thenReturn("[{\"key1\": 101,\"key2\":122},{\"key3\":222,\"key4\":5454}]");
        Set<String> keys = searchableKeyCache.getFirstLevelKeys(DataCategory.ORGANIZATION);
        assertTrue(keys.size() == 4 && keys.contains("key1") && keys.contains("key2") && keys.contains("key3") && keys.contains("key4"));
    }

    @Test
    public void jsonEntriesWithNoKey()  throws IOException, URISyntaxException {
        when(loader.getOrganizationJsonContent()).thenReturn("[{},{}]");
        Set<String> keys = searchableKeyCache.getFirstLevelKeys(DataCategory.ORGANIZATION);
        assertEquals(0, keys.size());
    }


}
