package numbers;

import java.util.ArrayList;

public class AppUI {
    static void singleNumberProperties(AmazingNumber number) {
        System.out.printf("Properties of %d\n", number.getNumber());
        System.out.printf("\tbuzz: %b\n", number.getBuzz());
        System.out.printf("\tduck: %b\n", number.getDuck());
        System.out.printf("\tpalindromic: %b\n", number.getPalindromic());
        System.out.printf("\tgapful: %b\n", number.getGapful());
        System.out.printf("\tspy: %b\n", number.getSpy());
        System.out.printf("\tsquare: %b\n", number.getSquare());
        System.out.printf("\tsunny: %b\n", number.getSunny());
        System.out.printf("\tjumping: %b\n", number.getJumping());
        System.out.printf("\teven: %b\n", number.getEven());
        System.out.printf("\todd: %b\n", number.getOdd());
    }

    static void showCompactProperties(AmazingNumber amazingNumber) {
        System.out.printf("\t%d is ", amazingNumber.getNumber());
        if (amazingNumber.getBuzz()) System.out.print(" buzz,");
        if (amazingNumber.getDuck()) System.out.print(" duck,");
        if (amazingNumber.getPalindromic()) System.out.print(" palindromic,");
        if (amazingNumber.getGapful()) System.out.print(" gapful,");
        if (amazingNumber.getSpy()) System.out.print(" spy,");
        if (amazingNumber.getSquare()) System.out.print(" square,");
        if (amazingNumber.getSunny()) System.out.print(" sunny,");
        if (amazingNumber.getJumping()) System.out.print(" jumping,");
        if (amazingNumber.getEven()) System.out.print(" even");
        else System.out.print(" odd");
        System.out.print("\n");
    }

    static void welcomeMessage() {
        System.out.print("Welcome to Amazing Numbers!\n\n");
        System.out.println("Supported requests: ");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.print("- enter 0 to exit\n");
    }

    static void askRequest() {
        System.out.print("\nEnter a request: ");
    }

    static void firstParamError() {
        System.out.println("\nThe first parameter should be a natural number or zero.");
    }

    static void secondParamError() {
        System.out.println("\nThe second parameter should be a natural number or zero.");
    }

    static void printAvailableProperties() {
        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
    }

    static void printPropertyError(ArrayList<String> wrongProperties) {
        // Store the properties to display inside square brackets and divided by commas, e.g. [PROP] or [PROP, PROP (...)]
        StringBuilder errorProperties = buildErrorList(wrongProperties);
        int errorCount = wrongProperties.size();

        if (errorCount == 1) {
            System.out.printf("The property %s is wrong.\n", errorProperties);
        } else System.out.printf("The properties %s are wrong.\n", errorProperties);
        printAvailableProperties();
    }

    private static StringBuilder buildErrorList(ArrayList<String> wrongProperties) {
        StringBuilder errorProperties = new StringBuilder("[");
        int errorCount = wrongProperties.size();
        for (int i = 0; i < errorCount; i++) {
            if (i == errorCount - 1) {
                errorProperties.append(wrongProperties.get(i)).append("]"); // Close brackets without commas for the last property
            } else errorProperties.append(wrongProperties.get(i)).append(", ");
        }
        return errorProperties;
    }
}
