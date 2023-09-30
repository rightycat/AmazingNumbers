package numbers;

import java.util.ArrayList;

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
        ArrayList<AmazingNumber> appResults;
        do {
            appResults = requestHandler.fetchRequest();
            if (appResults != null) {
                if (appResults.size() == 1) {
                    AppUI.singleNumberProperties(appResults.get(0));
                } else {
                    for (AmazingNumber number : appResults) {
                        AppUI.showCompactProperties(number);
                    }
                }
            }
        } while (appResults != null);
    }
}
