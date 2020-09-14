package com.zendesk.search;

import com.google.gson.Gson;
import com.zendesk.loader.DataCategory;
import com.zendesk.loader.JsonFileLoader;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grace on 9/12/2020.
 */
public class SearchableKeyCache {

    private Map<DataCategory, Set<String>> dataCategoryToFirstLevelKey = new HashMap<>();
    private JsonFileLoader jsonFileLoader;
    private Logger logger = Logger.getLogger(SearchableKeyCache.class.getName());



    public SearchableKeyCache(JsonFileLoader jsonFileLoader) {
        this.jsonFileLoader = jsonFileLoader;
    }

    public Set<String> getFirstLevelKeys(DataCategory dataCategory) throws IOException, URISyntaxException {
        if(dataCategoryToFirstLevelKey.get(dataCategory) == null) {
            final Set<String> newKeys = new TreeSet<>();
            String jsonContent = dataCategory.getJsonData(jsonFileLoader);
            Gson gson = new Gson();
            List myUserDataList = gson.fromJson(jsonContent, ArrayList.class);
            if(myUserDataList != null) {
                myUserDataList.forEach(userData -> {
                    if (userData instanceof Map) for (Object key : ((Map) userData).keySet()) {
                        newKeys.add(key.toString());
                    }
                    else {
                        logger.log(Level.SEVERE, "Expecting user data to be a map but found: " + userData.getClass().getName());
                    }
                });
            }
            dataCategoryToFirstLevelKey.put(dataCategory, newKeys);
        }

        return dataCategoryToFirstLevelKey.get(dataCategory);
    }




}
