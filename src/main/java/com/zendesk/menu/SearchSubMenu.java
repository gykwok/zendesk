package com.zendesk.menu;

import com.google.gson.Gson;
import com.zendesk.loader.DataCategory;
import com.zendesk.enumeration.MenuType;
import com.zendesk.search.SearchEngine;
import com.zendesk.search.SearchableKeyCache;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.logging.Logger;

/**
 * Created by Grace on 9/12/2020.
 */
public class SearchSubMenu extends Menu {


    private SearchableKeyCache searchableKeyCache;
    private SearchEngine searchEngine;

    private Logger logger = Logger.getLogger(SearchSubMenu.class.getName());


    public SearchSubMenu(SearchableKeyCache searchableKeyCache, SearchEngine searchEngine) {
        this.searchableKeyCache = searchableKeyCache;
        this.searchEngine = searchEngine;
    }


    /**
     * Process searching. We need a valid data category, a valid search key, and value to do the search.
     * @param scanner reading the user input
     * @return next menu type to process to.
     */
    @Override
    public MenuType process(Scanner scanner) throws IOException, URISyntaxException {

        Set<String> dataCategoryAllOptions = new HashSet<>();

        String dataCategoryChoiceMessage = "Select ";

        for(DataCategory dataCategory : DataCategory.values()) {
            dataCategoryChoiceMessage = dataCategoryChoiceMessage + " "
                    + dataCategory.getOptionValueOnMenu() + ") " + dataCategory.getName();
            dataCategoryAllOptions.add(dataCategory.getOptionValueOnMenu());
        }

        System.out.println(dataCategoryChoiceMessage);

        DataCategory dataCategory;
        String searchKey;
        String searchValue;

        MenuTypeOrValidStringValue result = getDataCategory(scanner, dataCategoryAllOptions);
        if(result.getMenuType() != null){
            return result.getMenuType();
        }
        else {
          dataCategory = DataCategory.getCategoryByMenuOption(result.getValue());
        }
        result = getSearchKey(scanner, searchableKeyCache.getFirstLevelKeys(dataCategory));

        if(result.getMenuType() != null){
            return result.getMenuType();
        }
        else {
            searchKey = result.getValue();
        }

        result = getSearchValue(scanner);

        if(result.getMenuType() != null){
            return result.getMenuType();
        }
        else {
            searchValue = result.getValue();
        }

        List<Map<String, Object>> searchResult = searchEngine.search(searchKey, searchValue, dataCategory);

        if(searchResult.isEmpty()) {
            System.out.println("Unable to find for " + dataCategory.getName() + " with key: " + searchKey + " Value:" + searchValue + ". If the value is a string, please use double quote on the input.");
        }
        else {

            for (Map<String, Object> res : searchResult) {
                Set<String> keySet = res.keySet();
                for (String key : keySet) {
                    Object value = res.get(key);
                    System.out.println(key + "           " + new Gson().toJson(value));
                }
                System.out.println("**********************************");
            }
        }

        return getNextMenu(scanner);

    }

    private MenuTypeOrValidStringValue getDataCategory(Scanner scanner, Set<String> dataCategoryAllOptions) {

        while (scanner.hasNext()) {
            String option = scanner.next().trim();
            if (OPTION_TO_QUIT.equals(option)) {
                return new MenuTypeOrValidStringValue(MenuType.QUIT, null);
            } else if (OPTION_TO_ENTER_MAIN_MENU.equals(option)) {
                return new MenuTypeOrValidStringValue(MenuType.MAIN, null);
            } else {
                if (!dataCategoryAllOptions.contains(option)) {
                    System.out.print("Unsupported option: " + option);
                } else {
                    return new MenuTypeOrValidStringValue(null, option);
                }
            }
        }
        logger.info("Not scanning any input.  Quiting.");
        return new MenuTypeOrValidStringValue(MenuType.QUIT, null);
    }

    private MenuTypeOrValidStringValue getSearchKey(Scanner scanner, Set<String> searchKeys) {

        System.out.println("Enter search term");

        while (scanner.hasNext()) {
            String searchKey = scanner.next().trim();
            if (OPTION_TO_QUIT.equals(searchKey)) {
                return new MenuTypeOrValidStringValue(MenuType.QUIT, null);
            } else if (OPTION_TO_ENTER_MAIN_MENU.equals(searchKey)) {
                return new MenuTypeOrValidStringValue(MenuType.MAIN, null);
            } else {
                if (!searchKeys.contains(searchKey)) {
                    System.out.print("Search key doesn't exist: " + searchKey);
                } else {
                    return new MenuTypeOrValidStringValue(null, searchKey);
                }
            }
        }
        logger.info("Not scanning any input.  Quiting.");
        return new MenuTypeOrValidStringValue(MenuType.QUIT, null);

    }

    private MenuTypeOrValidStringValue getSearchValue(Scanner scanner) {

        System.out.println("Enter search value");

        String searchValue = scanner.next().trim();
        if (OPTION_TO_QUIT.equals(searchValue)) {
            return new MenuTypeOrValidStringValue(MenuType.QUIT, null);
        } else if (OPTION_TO_ENTER_MAIN_MENU.equals(searchValue)) {
            return new MenuTypeOrValidStringValue(MenuType.MAIN, null);
        } else {
            return new MenuTypeOrValidStringValue(null, searchValue);
        }
    }


    private static class MenuTypeOrValidStringValue {
        private MenuType menuType;
        private String value;

        private MenuTypeOrValidStringValue(MenuType menuType, String value) {
            this.menuType = menuType;
            this.value = value;
        }

        public MenuType getMenuType() {
            return this.menuType;
        }

        public String getValue() {
            return this.value;
        }
    }

}
