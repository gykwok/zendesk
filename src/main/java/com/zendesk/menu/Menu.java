package com.zendesk.menu;

import com.zendesk.enumeration.MenuType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Grace on 9/12/2020.
 */
public abstract class Menu {

    protected static final String OPTION_TO_QUIT = "quit";
    protected static final String OPTION_TO_ENTER_MAIN_MENU = "M"; //enter creates a new line.

    protected static final String MENU_INSTRUCTION = "Type 'quit' to exit at any time, Type 'M' to go back to main menu";
    private Logger logger = Logger.getLogger(Menu.class.getName());

    protected MenuType getNextMenu(Scanner scanner) {
        System.out.println(MENU_INSTRUCTION);
        while (scanner.hasNext()) {
            String option = scanner.next().trim();
            switch (option) {
                case OPTION_TO_QUIT:
                    return MenuType.QUIT;
                case OPTION_TO_ENTER_MAIN_MENU:
                    return MenuType.MAIN;
                default: {
                    System.out.println("Unsupported option: " + option);
                }
            }
        }
        logger.info("Not scanning any input.  Quiting.");
        return MenuType.QUIT;
    }

    /**
     *
     * @param scanner reading the user input
     * @return  The next menu type to show after processing
     */
    public abstract MenuType process(Scanner scanner) throws IOException, URISyntaxException;
}
