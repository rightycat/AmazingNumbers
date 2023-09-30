package numbers;

import java.util.ArrayList;

public class RequestValidator {
    RequestHandler requestHandler;

    public RequestValidator(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }
    public boolean validateAllProperties() {
        boolean allPresent = areAllPresent();
        if (allPresent) {
            return allNotExclusive();
        } else AppUI.printPropertyError(requestHandler.getWrongProperties());
        return false;
    }

    private boolean areAllPresent() {
        ArrayList<String> wrongProperties = new ArrayList<>();
        boolean allPresent = true;
        for (String property : requestHandler.getProperties()) {
            if (!isPropertyPresent(property.toUpperCase())) {
                wrongProperties.add(property.toUpperCase());
                if (allPresent) {
                    allPresent = false;
                }
            }
        }
        requestHandler.setWrongProperties(wrongProperties);
        return allPresent;
    }

    private boolean isPropertyPresent(String propertyName) {
        try {
            AvailableProperties myEnum = AvailableProperties.valueOf(propertyName);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean allNotExclusive() {
        String[] properties = requestHandler.getProperties();
        for (int i = 0; i < properties.length; i++) {
            for (int k = i + 1; k < properties.length; k++) {
                if (!notExclusive(properties[i], properties[k])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean notExclusive(String firstProperty, String secondProperty) {
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

    private boolean noExclusivity(String property, String comparisonProperty, String firstProperty, String secondProperty) {
        if (firstProperty.equalsIgnoreCase(property)) {
            return !secondProperty.equalsIgnoreCase(comparisonProperty);
        }else if (secondProperty.equalsIgnoreCase(property)) {
            return !firstProperty.equalsIgnoreCase(comparisonProperty);
        } else if (firstProperty.equalsIgnoreCase(comparisonProperty)) {
            return !secondProperty.equalsIgnoreCase(property);
        } else return !firstProperty.equalsIgnoreCase(property);
    }
}
