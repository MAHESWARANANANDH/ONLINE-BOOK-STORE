package onlinebookstore;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Run the OnlineBookstoreApp in the Swing event dispatch thread
        SwingUtilities.invokeLater(() -> {
            OnlineBookstoreApp bookstoreApp = new OnlineBookstoreApp();
            bookstoreApp.setVisible(true);  // Display the app window
        });
    }
}
