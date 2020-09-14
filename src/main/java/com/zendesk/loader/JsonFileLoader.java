package com.zendesk.loader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Created by Grace on 9/12/2020.
 */


public class JsonFileLoader {

    private final String userJsonName = "users.json";
    private final String ticketJsonName = "tickets.json";
    private final String organizationJsonName = "organizations.json";

    private String userJsonContent;
    private String ticketJsonContent;
    private String organizationsJsonContent;

    private Logger logger = Logger.getLogger(JsonFileLoader.class.getName());

    public JsonFileLoader() {}

    public void loadData() throws IOException, URISyntaxException {

        if (userJsonContent == null) {
            URL userJsonResource = getClass().getClassLoader().getResource(userJsonName);
            if (userJsonResource == null) {
                logger.log(Level.SEVERE, "Unable to find: " + userJsonName);
                throw new UnsupportedOperationException("unable to find: " + userJsonName);
            }
            userJsonContent = new String(Files.readAllBytes(Paths.get(userJsonResource.toURI())));
        }

        if (ticketJsonContent == null) {
            URL ticketJsonResource = getClass().getClassLoader().getResource(ticketJsonName);
            if (ticketJsonResource == null) {
                logger.log(Level.SEVERE, "Unable to find: " + ticketJsonName);
                throw new UnsupportedOperationException("Unable to find: " + ticketJsonName);
            }
            ticketJsonContent = new String(Files.readAllBytes(Paths.get(ticketJsonResource.toURI())));
        }

        if (organizationsJsonContent == null) {
            URL organizationsJsonResource = getClass().getClassLoader().getResource(organizationJsonName);
            if (organizationsJsonResource == null) {
                logger.log(Level.SEVERE, "Unable to find: " + organizationJsonName);
                throw new UnsupportedOperationException("Unable to find: " + organizationJsonName);
            }
            organizationsJsonContent = new String(Files.readAllBytes(Paths.get(organizationsJsonResource.toURI())));
        }

    }

    public String getUserJsonContent() throws IOException, URISyntaxException {
        loadData();
        return this.userJsonContent;
    }

    public String getTicketJsonContent() throws IOException, URISyntaxException {
        loadData();
        return this.ticketJsonContent;
    }

    public String getOrganizationJsonContent() throws IOException, URISyntaxException {
        loadData();
        return this.organizationsJsonContent;
    }

}
