package com.zendesk.menu;

import com.zendesk.loader.DataCategory;
import com.zendesk.search.SearchEngine;
import com.zendesk.search.SearchableKeyCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

/**
 * Created by Grace on 9/13/2020.
 */


@RunWith(MockitoJUnitRunner.class)
public class SearchSubMenuTest {

    private SearchEngine engine;

    private SearchSubMenu searchSubMenu;

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

        SearchableKeyCache cache = mock(SearchableKeyCache.class);
        when(cache.getFirstLevelKeys(DataCategory.USER)).thenReturn(cachedKeys);

        engine = mock(SearchEngine.class);

        searchSubMenu = new SearchSubMenu(cache, engine);
    }

    @Test
    public void invokeSearchTest()throws IOException, URISyntaxException {
        Scanner scanner = new Scanner("1 key1 123 quit");
        searchSubMenu.process(scanner);
        Mockito.verify(engine, Mockito.times(1)).search(any(), any(), any());
    }

    @Test
    public void invokeSearchFirstInvalidKeyThenValidKeyTest()throws IOException, URISyntaxException {
        Scanner scanner = new Scanner("1 keyDummy key1 123 quit");
        searchSubMenu.process(scanner);
        Mockito.verify(engine, Mockito.times(1)).search(any(), any(), any());
    }

}
