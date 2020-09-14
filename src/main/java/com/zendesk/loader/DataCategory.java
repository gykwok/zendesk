package com.zendesk.loader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grace on 9/12/2020.
 */

public enum DataCategory {


    USER("users", "1") {
        @Override
        public String getJsonData(JsonFileLoader fileLoader) throws IOException, URISyntaxException {
            return fileLoader.getUserJsonContent();
        }
    },
    TICKET("tickets", "2") {
        @Override
        public String getJsonData(JsonFileLoader fileLoader) throws IOException, URISyntaxException {
            return fileLoader.getTicketJsonContent();
        }
    },
    ORGANIZATION("organizations", "3") {
        @Override
        public String getJsonData(JsonFileLoader fileLoader) throws IOException, URISyntaxException {
            return fileLoader.getOrganizationJsonContent();
        }
    };

    /**
     *
     * @param name - displayed during list keys.
     * @param optionValueOnMenu - corresponding option value 1, 2, 3 etc.
     */

    private DataCategory(String name, String optionValueOnMenu) {
        this.name = name;
        this.optionValueOnMenu = optionValueOnMenu;
    }

    private String name;
    private String optionValueOnMenu;

    public String getName() {
        return name;
    }

    public String getOptionValueOnMenu() {
        return optionValueOnMenu;
    }

    private static final Map<String, DataCategory> OPTION_VALUE_TO_DATA_CATEGORY = new HashMap<>();

    static {
        for (DataCategory dataCategory : DataCategory.values()) {
            OPTION_VALUE_TO_DATA_CATEGORY.put(dataCategory.getOptionValueOnMenu(), dataCategory);
        }
    }

    public static DataCategory getCategoryByMenuOption(String option) {
        return OPTION_VALUE_TO_DATA_CATEGORY.get(option);
    }

    public abstract String getJsonData(JsonFileLoader fileLoader) throws IOException, URISyntaxException;
}
