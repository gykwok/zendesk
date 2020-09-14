package com.zendesk.loader;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;


/**
 * Created by Grace on 9/12/2020.
 */


@RunWith(MockitoJUnitRunner.class)
public class JsonFileLoaderTest {

    private JsonFileLoader loader;

    @Before
    public void before() throws Exception {
        loader = new JsonFileLoader();
        loader.loadData();

    }

    @Test
    public void loadOrganizationContentSuccessTest() throws IOException, URISyntaxException {
        String value = loader.getOrganizationJsonContent();
        assertTrue(value.contains("\"external_id\": \"9270ed79-35eb-4a38-a46f-35725197ea8d\""));
    }

    @Test
    public void loadTicketsContentSuccessTest() throws IOException, URISyntaxException {
        String value = loader.getTicketJsonContent();
        assertTrue(value.contains("\"status\": \"pending\""));
    }

    @Test
    public void loadUserContentSuccessTest() throws IOException, URISyntaxException {
        String value = loader.getUserJsonContent();
        assertTrue(value.contains( " \"email\": \"coffeyrasmussen@flotonic.com\""));
    }

}
