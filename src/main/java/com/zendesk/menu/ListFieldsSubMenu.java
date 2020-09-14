package com.zendesk.menu;

import com.zendesk.loader.DataCategory;
import com.zendesk.enumeration.MenuType;
import com.zendesk.search.SearchableKeyCache;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Grace on 9/12/2020.
 */
public class ListFieldsSubMenu extends Menu {

    private Logger logger = Logger.getLogger(ListFieldsSubMenu.class.getName());

    private SearchableKeyCache searchableKeyCache;

    public ListFieldsSubMenu(SearchableKeyCache searchableKeyCache) {
        this.searchableKeyCache = searchableKeyCache;
    }

    /**
     * List the searchable keys for each data type.
     *
     * @param scanner reading the user input
     * @return next menu type to process to.
     */

    @Override
    public MenuType process(Scanner scanner) throws IOException, URISyntaxException {

        for (DataCategory dataCategory : DataCategory.values()) {
            System.out.println("***** Data type: " + dataCategory.getName());
            Set<String> allKeys = this.searchableKeyCache.getFirstLevelKeys(dataCategory);
            allKeys.forEach(System.out::println);
        }

        return getNextMenu(scanner);
    }

}
