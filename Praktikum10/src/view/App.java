package view;
import view.MainFramee;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFramee().setVisible(true));
    }
}
