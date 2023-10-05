package numbers;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class RequestHandler {

    // Store the main request number
    private long request;
    private int repetitions;
    private final ArrayList<String> allProperties = new ArrayList<>();
    private final ArrayList<String> targetedProperties = new ArrayList<>();
    private final ArrayList<String> excludedProperties = new ArrayList<>();
    private ArrayList<String> wrongProperties;

    // Store all request arguments for processing
    private String[] requestArguments;
    private List<AmazingNumber> requestResults = new ArrayList<>();
    private boolean endFlag = false;
    private final Scanner scanner = new Scanner(System.in);

    // Handles logic for storing requests
    public List<AmazingNumber> fetchRequest() {
        if (!requestResults.isEmpty()) {
            requestResults.clear();
        }
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
                requestResults = null;
            } else {
                try {
                    processRequest();
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return requestResults;
            }
        } while (!endFlag);
        scanner.close();
        return requestResults;
    }

    // Handles logic for processing requests
    private void processRequest() throws NoSuchFieldException, IllegalAccessException {
        RequestValidator requestValidator = new RequestValidator(this);

        if (requestArguments.length == 1) {
            requestResults = AmazingNumberGenerator.generateSingleNumber(request);
        } else {
            repetitions = Integer.parseInt(requestArguments[1]);
            if (requestArguments.length == 2) {
                requestResults = AmazingNumberGenerator.generateMultipleNumbers(request, repetitions);
            } else {
                setProperties();
                if (requestValidator.validateAllProperties()) {
                    requestResults = AmazingNumberGenerator.generateSelectedNumbersDEV(request, repetitions, targetedProperties, excludedProperties);
                }
            }
        }
    }

    private void setProperties() {
        if (!allProperties.isEmpty()) {
            allProperties.clear();
            excludedProperties.clear();
            targetedProperties.clear();
        }

        for (int i = 2; i < requestArguments.length; i++) {
            String property;
            if ((requestArguments[i].charAt(0) == '-')) {
                property = requestArguments[i].substring(1);
                excludedProperties.add(property);
            } else {
                property = requestArguments[i];
                targetedProperties.add(property);
            }
            allProperties.add(property);
        }
    }

    public ArrayList<String> getAllProperties() {
        return allProperties;
    }
    public ArrayList<String> getTargetedProperties() {
        return targetedProperties;
    }
    public ArrayList<String> getExcludedProperties() {
        return excludedProperties;
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

    public int getRepetitions() {
        return repetitions;
    }

}
