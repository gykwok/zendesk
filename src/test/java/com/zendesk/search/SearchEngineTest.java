package com.zendesk.search;

import com.zendesk.exception.KeyNotFoundException;
import com.zendesk.loader.DataCategory;
import com.zendesk.loader.JsonFileLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Grace on 9/13/2020.
 */

@RunWith(MockitoJUnitRunner.class)

public class SearchEngineTest {


    @Mock
    private SearchableKeyCache cache;


    @Mock
    private JsonFileLoader loader;


    @InjectMocks
    private SearchEngine searchEngine;

    @Before
    public void before() throws Exception {

        Set<String> cachedKeys = new HashSet<>();
        cachedKeys.add("key1");
        cachedKeys.add("key2");
        cachedKeys.add("key3");
        cachedKeys.add("key4");
        cachedKeys.add("key5");
        cachedKeys.add("key6");
        cachedKeys.add("key7");
        when(cache.getFirstLevelKeys(any())).thenReturn(cachedKeys);
        when(loader.getOrganizationJsonContent()).
                thenReturn("[{\"key1\": 101,\"key2\":\"abc\"},{\"key3\":false,\"key4\":5454, \"key2\":\"abc\", \"key5\":\"\", \"key6\":null, \"key7\":[trollery.com,datagen.com]}]"
                );
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void foundNumericEntrySuccessTest() throws IOException, URISyntaxException {
        assertEquals(1, searchEngine.search("key1", "101", DataCategory.ORGANIZATION).size());
    }

    @Test(expected = KeyNotFoundException.class)
    public void invalidKeyTest() throws IOException, URISyntaxException {
        assertEquals(0, searchEngine.search("keyDummy", "101", DataCategory.ORGANIZATION).size());

    }

    @Test
    public void foundTwoStringEntrySuccessTest() throws IOException, URISyntaxException {
        assertEquals(2, searchEngine.search("key2", "\"abc\"", DataCategory.ORGANIZATION).size());
    }

    @Test
    public void foundBooleanEntrySuccessTest() throws IOException, URISyntaxException {
        assertEquals(1, searchEngine.search("key3", "false", DataCategory.ORGANIZATION).size());
    }

    @Test
    public void foundEmptyStringEntrySuccessTest() throws IOException, URISyntaxException {
        assertEquals(1, searchEngine.search("key5", "", DataCategory.ORGANIZATION).size());
    }


    @Test
    public void foundNullValueEntrySuccessTest() throws IOException, URISyntaxException {
        assertEquals(1, searchEngine.search("key6", "null", DataCategory.ORGANIZATION).size());
    }

    @Test
    public void foundTagValueValueEntrySuccessTest() throws IOException, URISyntaxException {
        assertEquals(1, searchEngine.search("key7", "[trollery.com,datagen.com]", DataCategory.ORGANIZATION).size());
    }

}
