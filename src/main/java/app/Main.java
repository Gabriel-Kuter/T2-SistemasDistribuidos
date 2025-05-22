package app;

import javax.swing.SwingUtilities;
import view.Index;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Index().setVisible(true);
        });
    }
}