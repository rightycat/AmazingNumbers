package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NumberAnalyzer {

    // Store the main request number
    private static long request;
    private static String[] properties;
    private static final ArrayList<String> wrongProperties = new ArrayList<>();


    // Store all request arguments for processing
    private static String[] requestArguments;
    private static final Scanner scanner = new Scanner(System.in);

    static void startupMessage() {
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

    // Handles logic for storing requests
    public static void requestHandler(AmazingNumber amazingNumber) {
        startupMessage();
        do {
            System.out.print("\nEnter a request: ");

            // Input may be one number, two numbers or two numbers and a word(property)
            String input = scanner.nextLine();
            requestArguments = input.split(" ");
            request = Long.parseLong(requestArguments[0]);

            if (request < 0) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            } else if (request == 0) {
                System.out.println("Goodbye!");
                break; // Exit the loop when request is zero
            } else {
                processRequest(amazingNumber);
            }
        } while (true);
        scanner.close();
    }

    // Handles logic for processing requests
    private static void processRequest(AmazingNumber amazingNumber) {
        amazingNumber.setNumber(request);

        if (requestArguments.length == 1) {
            amazingNumber.showNumberProperties();
            return;
        }

        long repetitions = Long.parseLong(requestArguments[1]);
        if (repetitions <= 0) {
            System.out.println("\nThe second parameter should be a natural number.");
        }

        if (requestArguments.length == 2) {
            amazingNumber.showMultipleNumbersProperties(repetitions);
        } else {
            properties = Arrays.copyOfRange(requestArguments, 2, requestArguments.length);
            if (validateAllProperties()) {
                System.out.println("we got it!");
                amazingNumber.showSelectedNumbersProperties(repetitions, properties);
            } else System.out.println("we did get here at least");
        }

        // TODO: ABOVE HERE /\/\/\/\
    }

    private static boolean validateAllProperties() {
        boolean allPresent = areAllPresent();
        if (allPresent) {
            System.out.println("well, they are present indeed ?");
            if (allNotExclusive()) {

                return allNotExclusive();
            } else System.out.println("yep found it");
        } else printPropertyError2();
        return false;
    }

    private static boolean areAllPresent() {
        wrongProperties.clear();
        boolean allPresent = true;
        for (String property : properties) {
            if (!isPropertyPresent(property.toUpperCase())) {
                wrongProperties.add(property.toUpperCase());
                if (allPresent) {
                    allPresent = false;
                }
            }
        }
        return allPresent;
    }

    private static boolean isPropertyPresent(String propertyName) {
        try {
            AvailableProperties myEnum = AvailableProperties.valueOf(propertyName);
        } catch (IllegalArgumentException e) {
            //System.out.println("not present");
            return false;
        }
        return true;
    }

    private static boolean allNotExclusive() {
        for (int i = 0; i < properties.length; i++) {
            for (int k = i + 1; k < properties.length; k++) {
                if (!notExclusive(properties[i], properties[k])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean notExclusive(String firstProperty, String secondProperty) {
        if (noExclusivity("even", "odd", firstProperty, secondProperty)) {
            if (noExclusivity("duck", "spy", firstProperty, secondProperty)) {
                if (noExclusivity("square", "sunny", firstProperty, secondProperty)) {
                    return true;
                }
            }
        }
        System.out.printf("The request contains mutually exclusive properties: [%s,%s]\n", firstProperty.toUpperCase(), secondProperty.toUpperCase());
        System.out.println("There are no numbers with these properties.");
        return false;
    }

    private static boolean noExclusivity(String property, String comparisonProperty, String firstProperty, String secondProperty) {
        if (firstProperty.equalsIgnoreCase(property)) {
            return !secondProperty.equalsIgnoreCase(comparisonProperty);
        }else if (secondProperty.equalsIgnoreCase(property)) {
            return !firstProperty.equalsIgnoreCase(comparisonProperty);
        } else if (firstProperty.equalsIgnoreCase(comparisonProperty)) {
            return !secondProperty.equalsIgnoreCase(property);
        } else return !firstProperty.equalsIgnoreCase(property);
    }

    public static void printPropertyError2() {
        // Store the properties to display inside square brackets and divided by commas, e.g. [PROP] or [PROP, PROP (...)]
        StringBuilder errorProperties = new StringBuilder("[");
        int errorCount = wrongProperties.size();
        for (int i = 0; i < errorCount; i++) {
            if (i == errorCount - 1) {
                errorProperties.append(wrongProperties.get(i)).append("]"); // Close brackets without commas for the last property
            } else errorProperties.append(wrongProperties.get(i)).append(", ");
        }

        if (errorCount == 1) {
            System.out.printf("The property %s is wrong.\n", errorProperties);
        } else System.out.printf("The properties %s are wrong.\n", errorProperties);
        printAvailableProperties();
    }

    static void printAvailableProperties() {
        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
    }
}
