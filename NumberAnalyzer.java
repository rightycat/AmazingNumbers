package numbers;

import java.util.Scanner;

public class NumberAnalyzer {

    // Store the main request number
    private static long request;

    private static long repetitions;
    private static String firstProperty;
    private static String secondProperty;


    // Store all request arguments for processing
    private static String[] requestArguments;
    private static final Scanner scanner = new Scanner(System.in);

    // Handles logic for storing requests
    public static void requestHandler(AmazingNumber amazingNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        showOptions();
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
        scClose();
    }

    // Handles logic for processing requests
    private static void processRequest(AmazingNumber amazingNumber) {
        amazingNumber.setNumber(request);

        if (requestArguments.length == 1) {
            amazingNumber.showNumberProperties();
            return;
        }

        repetitions = Long.parseLong(requestArguments[1]);
        if (repetitions <= 0) {
            System.out.println("\nThe second parameter should be a natural number.");
        }

        if (requestArguments.length >= 3) {
            firstProperty = requestArguments[2];
            if (!isPropertyPresent(firstProperty)) {
                return;
            }

            if (requestArguments.length == 4) {
                secondProperty = requestArguments[3];
                if (validateBothProperties()) {
                    amazingNumber.showSelectedNumbersProperties(repetitions, firstProperty, secondProperty);
                }
            } else {
                amazingNumber.showSelectedNumbersProperties(repetitions, firstProperty);
            }
        } else amazingNumber.showMultipleNumbersProperties(repetitions);
    }

    private static boolean validateBothProperties() {
        if (isPropertyPresent(secondProperty)) {
            if (!arePropertyOpposites()) {
                if (arePropertiesDifferent()) {
                    return true;
                }
            }
        }
        return false;

    }

    private static boolean arePropertiesDifferent() {
        if (firstProperty.equalsIgnoreCase(secondProperty)) {
            printPropertyError();
            return false;
        } else return true;
    }

    public static void printPropertyError() {
        if (requestArguments.length == 4) {
            System.out.printf("The property [%s, %s] is wrong.\n", firstProperty.toUpperCase(), requestArguments[3].toUpperCase());
        } else System.out.printf("The property [%s] is wrong.\n", firstProperty.toUpperCase());
        printAvailableProperties();
    }

    private static boolean isPropertyPresent(String propertyName) {
        try {
            AvailableProperties myEnum = AvailableProperties.valueOf(propertyName.toUpperCase());
        } catch (IllegalArgumentException e) {
            printPropertyError();
            return false;
        }
        return true;
    }

    private static boolean arePropertyOpposites() {
        if (firstProperty.equalsIgnoreCase("even") || secondProperty.equalsIgnoreCase("even")) {
            return firstProperty.equalsIgnoreCase("odd") || secondProperty.equalsIgnoreCase("odd");
        } else if (firstProperty.equalsIgnoreCase("duck") || secondProperty.equalsIgnoreCase("duck")) {
            return firstProperty.equalsIgnoreCase("spy") || secondProperty.equalsIgnoreCase("spy");
        } else if (firstProperty.equalsIgnoreCase("sunny") || secondProperty.equalsIgnoreCase("sunny")) {
            return firstProperty.equalsIgnoreCase("square") || secondProperty.equalsIgnoreCase("square");
        } else {
            printPropertyError();
            return true;
        }
    }

    static void showOptions() {
        System.out.println("Supported requests: ");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit");
        System.out.println();
    }

    static void printAvailableProperties() {
        StringBuilder allEnums = new StringBuilder("Available properties: [");
        for (AvailableProperties property : AvailableProperties.values()) {
            allEnums.append(property.name()).append(", ");
        }
        System.out.println(allEnums.append("]"));
    }

    private static void scClose() {
        scanner.close();
    }
}
