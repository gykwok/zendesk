package com.zendesk.menu;

import com.zendesk.enumeration.MenuType;
import com.zendesk.search.SearchableKeyCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Created by Grace on 9/13/2020.
 */


@RunWith(MockitoJUnitRunner.class)
public class ListFieldsSubMenuTest {

    @Mock
    private SearchableKeyCache cache;

    @InjectMocks
    private ListFieldsSubMenu listFieldsSubMenu;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void quitTest(){
        Scanner scanner = new Scanner("quit");
        assertEquals(MenuType.QUIT, listFieldsSubMenu.getNextMenu(scanner));
    }

    @Test
    public void mainMenuTest(){
        Scanner scanner = new Scanner("M");
        assertEquals(MenuType.MAIN, listFieldsSubMenu.getNextMenu(scanner));
    }

    @Test
    public void processTest() throws IOException, URISyntaxException {
        Scanner scanner = new Scanner("quit");
        assertEquals(MenuType.QUIT, listFieldsSubMenu.process(scanner));
    }

}
