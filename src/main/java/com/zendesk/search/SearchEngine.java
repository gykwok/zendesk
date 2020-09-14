package com.zendesk.search;

import com.jayway.jsonpath.JsonPath;
import com.zendesk.loader.DataCategory;
import com.zendesk.loader.JsonFileLoader;
import com.zendesk.exception.KeyNotFoundException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grace on 9/12/2020.
 */
public class SearchEngine {

    private final JsonFileLoader jsonFileLoader;
    private final SearchableKeyCache searchableKeyCache;
    private final Logger logger = Logger.getLogger(SearchEngine.class.getName());
    private final String emptyValue = "\"\"";

    public SearchEngine(JsonFileLoader jsonFileLoader, SearchableKeyCache searchableKeyCache) {
        this.jsonFileLoader = jsonFileLoader;
        this.searchableKeyCache = searchableKeyCache;

    }

    public List<Map<String, Object>> search(String id, String value, DataCategory dataCategory) throws IOException, URISyntaxException {
        if(value == null || value.isEmpty()) {
            value = emptyValue;
        }
        Set<String> keySet = this.searchableKeyCache.getFirstLevelKeys(dataCategory);
        if(keySet == null || !keySet.contains(id)) {
            logger.log(Level.INFO, "Key not found: " + id);
            throw new KeyNotFoundException("key not available: " + id + " DataCategory: " + dataCategory);
        }
        else {
            String searchString = "$[?(@." + id + " == " + value + ")]";
            String jsonContent = dataCategory.getJsonData(jsonFileLoader);
            try {
                return JsonPath.parse(jsonContent).read(searchString);
            }
            catch (Exception e) {
                logger.info("Unable to search: key: " + id + " Value:" + value);
                System.out.println("Unable to find key: " + id + " Value:" + value + " .If it's a string, please use double quote on the input.");
                return new ArrayList<>();
            }
        }
    }
}
