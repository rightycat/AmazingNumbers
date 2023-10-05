package numbers;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    RequestHandler requestHandler;

    public RequestValidator(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }
    public boolean validateAllProperties() {
        boolean allPresent = areAllPresent();
        if (allPresent) {
            return targetsNotExclusive() && excludedNotExclusive();
        } else AppUI.printPropertyError(requestHandler.getWrongProperties());
        return false;
    }

    private boolean areAllPresent() {
        ArrayList<String> wrongProperties = new ArrayList<>();
        boolean allPresent = true;
        for (String property : requestHandler.getAllProperties()) {
            if (!isPropertyPresent(property.toUpperCase())) {
                wrongProperties.add(property.toUpperCase());
                if (allPresent) {
                    allPresent = false;
                }
            }
        }

        if (!wrongProperties.isEmpty()) {
            requestHandler.setWrongProperties(wrongProperties);
        }
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

    private boolean targetsNotExclusive() {
        List<String> targetedProperties = requestHandler.getTargetedProperties();
        List<String> excludedProperties = requestHandler.getExcludedProperties();
        int targetedAmount = targetedProperties.size();
        int excludedAmount = excludedProperties.size();
        String firstProperty;
        String comparisonProperty;

        for (int i = 0; i < targetedAmount; i++) {
            firstProperty = targetedProperties.get(i);
            for (int k = i + 1; k < targetedAmount; k++) {
                comparisonProperty = targetedProperties.get(k);
                if (areExclusive(firstProperty, comparisonProperty)) {
                    AppUI.mutuallyExclusiveError(firstProperty, comparisonProperty);
                    return false;
                }
            }

            for (int k = 0; k < excludedAmount; k++) {
                comparisonProperty = excludedProperties.get(k);
                if (firstProperty.equalsIgnoreCase(comparisonProperty)) {
                    AppUI.mutuallyExclusiveError(firstProperty, '-' + comparisonProperty);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean excludedNotExclusive() {
        List<String> excludedProperties = requestHandler.getExcludedProperties();
        int excludedAmount = excludedProperties.size();
        String firstProperty;
        String comparisonProperty;

        for (int i = 0; i < excludedAmount; i++) {
            firstProperty = excludedProperties.get(i);
            for (int k = i + 1; k < excludedAmount; k++) {
                comparisonProperty = excludedProperties.get(k);
                if (areExclusive(firstProperty, comparisonProperty)) {
                    AppUI.mutuallyExclusiveError('-' + firstProperty, '-' + comparisonProperty);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areExclusive(String firstProperty, String secondProperty) {
        if (noExclusivity("even", "odd", firstProperty, secondProperty)) {
            if (noExclusivity("duck", "spy", firstProperty, secondProperty)) {
                if (noExclusivity("square", "sunny", firstProperty, secondProperty)) {
                    return !noExclusivity("happy", "sad", firstProperty, secondProperty);
                }
            }
        }
        return true;
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
