package com.zendesk.menu;

import com.zendesk.enumeration.MenuType;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Grace on 9/12/2020.
 */
public class MainMenu extends Menu {

    private static final String OPTION_TO_SEARCH = "1";
    private static final String OPTION_TO_LIST_FIELDS = "2";
    private Logger logger = Logger.getLogger(MainMenu.class.getName());

    /**
     * There are four valid options for the main menu.  1, 2, "quit", and "M" to go back to main menu.
     * @param scanner reading the user input
     * @return The next menu type based on input.
     */
    @Override
    public MenuType process(Scanner scanner) {

        System.out.println("Welcome to Zendesk Search");
        System.out.println(MENU_INSTRUCTION);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("     Select search options:");
        System.out.println("      * Press 1 to search Zendesk");
        System.out.println("      * Press 2 to view a list of searchable fields");
        System.out.println("      * Type 'quit' to exit");



        while (scanner.hasNext()) {

            String option = scanner.next().trim();

            switch (option) {
                case OPTION_TO_SEARCH:
                    return MenuType.SEARCH;
                case OPTION_TO_LIST_FIELDS:
                    return MenuType.LIST_FIELDS;
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

}
