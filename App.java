package numbers;

import java.util.List;

public class App {
    static RequestHandler requestHandler = new RequestHandler();

    public static void main(String[] args) {
        startUp();
    }

    private static void startUp() {
        AppUI.welcomeMessage();
        appLogic();
    }
    private static void appLogic() {
        List<AmazingNumber> appResults;
        do {
            appResults = requestHandler.fetchRequest();
            if (appResults != null) {
                if (requestHandler.getRepetitions() > 0) {
                    for (AmazingNumber number : appResults) {
                        AppUI.showCompactProperties(number);
                    }
                } else {
                    AppUI.singleNumberProperties(appResults.get(0));
                }
            }
        } while (appResults != null);
    }
}
