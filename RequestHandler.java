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
    private ArrayList<AmazingNumber> requestResults = new ArrayList<>();
    private static boolean endFlag = false;
    private static final Scanner scanner = new Scanner(System.in);

    public String[] getProperties() {
        return properties;
    }

    public ArrayList<String> getWrongProperties() {
        return wrongProperties;
    }
    public boolean getEndFlag() {
        return endFlag;
    }

    public void setWrongProperties(ArrayList<String> wrongProperties) {
        if (this.wrongProperties != null) {
            this.wrongProperties.clear();
        }
        this.wrongProperties = wrongProperties;
    }

    // Handles logic for storing requests
    public ArrayList<AmazingNumber> fetchRequest() {
        ArrayList<AmazingNumber> requestedNumbers = new ArrayList<>();
        do {
            AppUI.askRequest();

            // Input may be one number, two numbers or two numbers and a word(property)
            String input = scanner.nextLine();
            requestArguments = input.split(" ");
            request = Long.parseLong(requestArguments[0]);

            if (request < 0) {
                AppUI.firstParamError();
            } else if (requestArguments.length > 1 && Integer.parseInt(requestArguments[1]) <= 0) {
                AppUI.secondParamError();
            } else if (request == 0) {
                endFlag = true;
                System.out.println("Goodbye!");
                return null; // Exit the loop when request is zero
            } else {
                processRequest();
                return requestResults;
            }
        } while (!endFlag);
        scanner.close();
        return requestedNumbers;
    }

    // Handles logic for processing requests
    private void processRequest() {
        AmazingNumber amazingNumber = new AmazingNumber(request);
        RequestValidator requestValidator = new RequestValidator(this);

        if (requestArguments.length == 1) {
            requestResults = amazingNumber.singleNumberProperties();
        } else {
            int repetitions = Integer.parseInt(requestArguments[1]);
            if (requestArguments.length == 2) {
                requestResults = amazingNumber.multipleNumbersProperties(repetitions);
            } else {
                properties = Arrays.copyOfRange(requestArguments, 2, requestArguments.length);
                if (requestValidator.validateAllProperties()) {
                    requestResults = amazingNumber.selectedNumbersProperties(repetitions, properties);
                }
            }
        }
    }
}
