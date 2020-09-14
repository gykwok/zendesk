package com.zendesk;

import com.zendesk.enumeration.MenuType;
import com.zendesk.loader.JsonFileLoader;
import com.zendesk.menu.ListFieldsSubMenu;
import com.zendesk.menu.MainMenu;
import com.zendesk.menu.Menu;
import com.zendesk.menu.SearchSubMenu;
import com.zendesk.search.SearchEngine;
import com.zendesk.search.SearchableKeyCache;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {


        JsonFileLoader fileLoader = new JsonFileLoader();
        fileLoader.loadData();

        SearchableKeyCache keyCache = new SearchableKeyCache(fileLoader);
        SearchEngine searchEngine = new SearchEngine(fileLoader, keyCache);

        Menu mainMenu = new MainMenu();
        Menu listFieldsSubMenu = new ListFieldsSubMenu(keyCache);
        Menu searchSubMenu = new SearchSubMenu(keyCache, searchEngine);

        Scanner scanner = new Scanner(System.in);
        MenuType nextMenuType = MenuType.MAIN;


        while (MenuType.QUIT != nextMenuType) {

            if (nextMenuType == MenuType.MAIN) {
                nextMenuType = mainMenu.process(scanner);
            } else if (nextMenuType == MenuType.SEARCH) {
                nextMenuType = searchSubMenu.process(scanner);
            } else if (nextMenuType == MenuType.LIST_FIELDS) {
                nextMenuType = listFieldsSubMenu.process(scanner);
            } else {
                throw new UnsupportedOperationException("Unsupported menu type: " + nextMenuType);
            }
        }


    }
}
