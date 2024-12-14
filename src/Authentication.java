import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private static final Color HEADER_COLOR = new Color(30, 40, 50);
    private static final Color BUTTON_COLOR = new Color(52, 152, 219);
    private static final Color SIGNUP_BUTTON_COLOR = new Color(39, 174, 96);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 13);

    private Map<String, UserAccount> accounts;
    private JFrame sign;

    public Authentication() {
        accounts = new HashMap<>();
        sign = new JFrame();
        sign.setSize(384, 288);
        sign.setLayout(null);
        sign.setUndecorated(true);
        sign.setLocationRelativeTo(null);
        sign.setResizable(false);
        sign.setShape(new RoundRectangle2D.Double(0, 0, sign.getWidth(), sign.getHeight(), 30, 30));

        JPanel header = createHeaderPanel();
        JPanel center = createCenterPanel();

        sign.add(header);
        sign.add(center);
        sign.setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(null);
        header.setBackground(HEADER_COLOR);
        JButton exit = createButton("X", HEADER_FONT, Color.WHITE, HEADER_COLOR);
        exit.setBounds(sign.getWidth() - exit.getPreferredSize().width, 0, exit.getPreferredSize().width, exit.getPreferredSize().height);
        exit.addActionListener(e -> System.exit(0));
        header.add(exit);
        header.setBounds(0, 0, sign.getWidth(), exit.getPreferredSize().height);
        return header;
    }

    private JPanel createCenterPanel() {
        JPanel center = new JPanel(null);
        center.setBackground(new Color(53, 63, 74));
        center.setBounds(0, 26, sign.getWidth(), sign.getHeight() - 26);

        JLabel usernameLabel = createLabel("Username", 65, 60);
        JTextField usernameField = createTextField(center.getBackground(), 149, 53);
        JLabel passwordLabel = createLabel("Password", 69, 110);
        JPasswordField passwordField = createPasswordField(center.getBackground(), 149, 103);

        String[] roles = {"Doctor", "Nurse"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(159, 160, 66, 25);
        center.add(roleComboBox);

        JButton loginButton = createButton("Login", BUTTON_FONT, Color.WHITE, BUTTON_COLOR);
        loginButton.setBounds(106, 210, 69, 27);
        loginButton.addActionListener(e -> handleLogin(usernameField, passwordField, roleComboBox));
        center.add(loginButton);

        JButton signupButton = createButton("Sign Up", BUTTON_FONT, Color.WHITE, SIGNUP_BUTTON_COLOR);
        signupButton.setBounds(195, 210, 83, 27);
        signupButton.addActionListener(e -> handleSignup(usernameField, passwordField, roleComboBox));
        center.add(signupButton);

        center.add(usernameLabel);
        center.add(usernameField);
        center.add(passwordLabel);
        center.add(passwordField);

        return center;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBounds(x, y, 64, 17);
        return label;
    }

    private JTextField createTextField(Color background, int x, int y) {
        JTextField textField = new JTextField();
        textField.setForeground(new Color(200, 200, 200));
        textField.setBackground(background);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBounds(x, y, 170, 30);
        return textField;
    }

    private JPasswordField createPasswordField(Color background, int x, int y) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(new Color(200, 200, 200));
        passwordField.setBackground(background);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBounds(x, y, 170, 30);
        return passwordField;
    }

    private JButton createButton(String text, Font font, Color textColor, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(font);
        button.setOpaque(true);
        button.setForeground(textColor);
        button.setBackground(backgroundColor);
        return button;
    }

    private void handleLogin(JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(sign, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserAccount account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            // Check if the role matches
            if (account.getRole().equals(selectedRole)) {
                sign.setVisible(false);
                JOptionPane.showMessageDialog(sign, "Login successful as " + account.getRole(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(sign, "Role mismatch. You cannot log in as " + selectedRole, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(sign, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }

    private void handleSignup(JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();
        usernameField.setText(null);
        passwordField.setText(null);

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(sign, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (accounts.containsKey(username)) {
            JOptionPane.showMessageDialog(sign, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            accounts.put(username, new UserAccount(username, password, role));
            JOptionPane.showMessageDialog(sign, "Account Created Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Authentication();
    }
}

class UserAccount {
    private String username;
    private String password;
    private String role;

    public UserAccount(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}