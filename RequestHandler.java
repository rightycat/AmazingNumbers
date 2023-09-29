package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RequestHandler {

    // Store the main request number
    private static long request;
    private static String[] properties;
    private ArrayList<String> wrongProperties;

    // Store all request arguments for processing
    private static String[] requestArguments;
    private static final Scanner scanner = new Scanner(System.in);

    public String[] getProperties() {
        return properties;
    }

    public ArrayList<String> getWrongProperties() {
        return wrongProperties;
    }

    public void setWrongProperties(ArrayList<String> wrongProperties) {
        if (this.wrongProperties != null) {
            this.wrongProperties.clear();
        }
        this.wrongProperties = wrongProperties;
    }


    void startupMessage() {
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
    public void requestHandler() {
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
                processRequest();
            }
        } while (true);
        scanner.close();
    }

    // Handles logic for processing requests
    private void processRequest() {
        AmazingNumber amazingNumber = new AmazingNumber(request);
        RequestValidator requestValidator = new RequestValidator(this);

        if (requestArguments.length == 1) {
            amazingNumber.showNumberProperties();
            return;
        }

        long repetitions = Long.parseLong(requestArguments[1]);
        if (repetitions <= 0) {
            System.out.println("\nThe second parameter should be a natural number.");
            return; // Exit early to prevent further processing
        }

        if (requestArguments.length == 2) {
            amazingNumber.showMultipleNumbersProperties(repetitions);
        } else {
            properties = Arrays.copyOfRange(requestArguments, 2, requestArguments.length);
            if (requestValidator.validateAllProperties()) {
                amazingNumber.showSelectedNumbersProperties(repetitions, properties);
            }
        }
    }
}
