import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        new CreateDatabase();
        SwingUtilities.invokeLater(() -> new Authentication());
    }
}