package numbers;

import java.util.Scanner;

public class NumberAnalyzer {

    // Store the main request number
    private static long request;

    // Store all request arguments for processing
    private static String[] requestArguments;
    private static final Scanner scanner = new Scanner(System.in);

    // Handles logic for storing requests
    public static void requestHandler(AmazingNumber amazingNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        AmazingNumber.showOptions();
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
        if (requestArguments.length > 1) {
            long secondArgument = Long.parseLong(requestArguments[1]);
            if (secondArgument > 0) {
                if (requestArguments.length == 3) {
                    try {
                        AvailableProperties argument = AvailableProperties.valueOf(requestArguments[2].toUpperCase());
                        amazingNumber.showSelectedNumbersProperties(secondArgument, String.valueOf(argument));
                    } catch (IllegalArgumentException e) {
                        System.out.printf("The property [%s] is wrong.\n", requestArguments[2].toUpperCase());
                        AmazingNumber.showAvailableProperties();
                    }
                } else amazingNumber.showMultipleNumbersProperties(secondArgument);
            } else {
                System.out.println("\nThe second parameter should be a natural number.");
            }
        } else {
            amazingNumber.showNumberProperties();
        }
    }

    private static void scClose() {
        scanner.close();
    }
}
