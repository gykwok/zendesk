package com.zendesk.menu;

import com.zendesk.enumeration.MenuType;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Grace on 9/13/2020.
 */
public class MainMenuTest {


    private MainMenu mainMenu = new MainMenu();

    @Test
    public void quitTest(){
        Scanner scanner = new Scanner("quit");
        assertEquals(MenuType.QUIT, mainMenu.process(scanner));
    }

    @Test
    public void mainMenuTest(){
        Scanner scanner = new Scanner("M");
        assertEquals(MenuType.MAIN, mainMenu.process(scanner));
    }

    @Test
    public void searchMenuTest(){
        Scanner scanner = new Scanner("1");
        assertEquals(MenuType.SEARCH, mainMenu.process(scanner));
    }

    @Test
    public void listFieldsTest(){
        Scanner scanner = new Scanner("2");
        assertEquals(MenuType.LIST_FIELDS, mainMenu.process(scanner));
    }
}
