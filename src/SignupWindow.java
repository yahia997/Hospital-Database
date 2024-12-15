import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupWindow {
    private JFrame signup;

    public SignupWindow() {
        signup = new JFrame();
        signup.setSize(400, 500);
        signup.setLayout(null);
        signup.setLocationRelativeTo(null);
        signup.setResizable(false);
        signup.setUndecorated(true);
        signup.setShape(new RoundRectangle2D.Double(0, 0, signup.getWidth(), signup.getHeight(), 30, 30));

        JPanel header = new JPanel(null);
        header.setBackground(new Color(30, 40, 50));
        JButton exit = new JButton("X");
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exit.setFont(new Font("Arial", Font.BOLD, 13));
        exit.setOpaque(true);
        exit.setForeground(Color.WHITE);
        exit.setBackground(new Color(30, 40, 50));
        exit.setBounds(signup.getWidth() - exit.getPreferredSize().width, 0, exit.getPreferredSize().width,
                exit.getPreferredSize().height);
        exit.addActionListener(e -> {
            signup.setVisible(false);
            new Authentication();
        });
        header.add(exit);
        header.setBounds(0, 0, signup.getWidth(), exit.getPreferredSize().height);
        signup.add(header);

        JPanel center = new JPanel(null);
        center.setBackground(new Color(53, 63, 74));
        center.setBounds(0, 26, signup.getWidth(), signup.getHeight() - 26);
        signup.add(center);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(87, 70, 60, 16);
        center.add(usernameLabel);
        JTextField usernameField = new JTextField();
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        usernameField.setBackground(center.getBackground());
        usernameField.setBounds(190, 70, 150, 20);
        usernameField.setForeground(new Color(200, 200, 200));
        center.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(88, 110, 58, 16);
        center.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBackground(center.getBackground());
        passwordField.setBounds(190, 110, 150, 20);
        passwordField.setForeground(new Color(200, 200, 200));
        center.add(passwordField);

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setBounds(104, 150, 26, 16);
        center.add(roleLabel);
        String[] roles = { "Doctor", "Nurse" };
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(242, 150, 66, 25);
        center.add(roleComboBox);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setBounds(86, 190, 62, 16);
        center.add(firstNameLabel);
        JTextField firstNameField = new JTextField();
        firstNameField.setHorizontalAlignment(JTextField.CENTER);
        firstNameField.setBackground(center.getBackground());
        firstNameField.setBounds(190, 190, 150, 20);
        firstNameField.setForeground(new Color(200, 200, 200));
        center.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setBounds(86, 230, 62, 16);
        center.add(lastNameLabel);
        JTextField lastNameField = new JTextField();
        lastNameField.setHorizontalAlignment(JTextField.CENTER);
        lastNameField.setBackground(center.getBackground());
        lastNameField.setBounds(190, 230, 150, 20);
        lastNameField.setForeground(new Color(200, 200, 200));
        center.add(lastNameField);

        JLabel degreeLabel = new JLabel("Degree");
        degreeLabel.setForeground(Color.WHITE);
        degreeLabel.setBounds(96, 270, 42, 16);
        center.add(degreeLabel);
        JTextField degreeField = new JTextField();
        degreeField.setHorizontalAlignment(JTextField.CENTER);
        degreeField.setBackground(center.getBackground());
        degreeField.setBounds(190, 270, 150, 20);
        degreeField.setForeground(new Color(200, 200, 200));
        center.add(degreeField);

        JLabel yearsOfExpLabel = new JLabel("Years of Experience");
        yearsOfExpLabel.setForeground(Color.WHITE);
        yearsOfExpLabel.setBounds(60, 310, 114, 16);
        center.add(yearsOfExpLabel);
        JTextField yearsOfExpField = new JTextField();
        yearsOfExpField.setHorizontalAlignment(JTextField.CENTER);
        yearsOfExpField.setBackground(center.getBackground());
        yearsOfExpField.setBounds(190, 310, 150, 20);
        yearsOfExpField.setForeground(new Color(200, 200, 200));
        center.add(yearsOfExpField);

        JLabel specializationLabel = new JLabel("Specialization");
        specializationLabel.setForeground(Color.WHITE);
        specializationLabel.setBounds(77, 350, 80, 16);
        center.add(specializationLabel);
        JTextField specializationField = new JTextField();
        specializationField.setHorizontalAlignment(JTextField.CENTER);
        specializationField.setBackground(center.getBackground());
        specializationField.setBounds(190, 350, 150, 20);
        specializationField.setForeground(new Color(200, 200, 200));
        center.add(specializationField);

        JLabel supervizorIdLabel = new JLabel("Supervisor ID");
        supervizorIdLabel.setForeground(Color.WHITE);
        supervizorIdLabel.setBounds(79, 270, 76, 16);
        center.add(supervizorIdLabel);
        JTextField supervizorIdField = new JTextField();
        supervizorIdField.setHorizontalAlignment(JTextField.CENTER);
        supervizorIdField.setBackground(center.getBackground());
        supervizorIdField.setBounds(190, 270, 150, 20);
        supervizorIdField.setForeground(new Color(200, 200, 200));
        center.add(supervizorIdField);

        supervizorIdField.setVisible(false);
        supervizorIdLabel.setVisible(false);

        roleComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleComboBox.getSelectedItem();
                boolean isDoctor = selectedRole.equals("Doctor");

                degreeLabel.setVisible(isDoctor);
                degreeField.setVisible(isDoctor);
                yearsOfExpLabel.setVisible(isDoctor);
                yearsOfExpField.setVisible(isDoctor);
                specializationLabel.setVisible(isDoctor);
                specializationField.setVisible(isDoctor);
                supervizorIdField.setVisible(!isDoctor);
                supervizorIdLabel.setVisible(!isDoctor);
            }
        });

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBorderPainted(false);
        signupButton.setFocusPainted(false);
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupButton.setFont(new Font("Arial", Font.PLAIN, 14));
        signupButton.setOpaque(true);
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(new Color(39, 174, 96));
        signupButton.setBounds(158, 400, 84, 27);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignup(usernameField, passwordField, roleComboBox,
                        firstNameField, lastNameField, degreeField, yearsOfExpField, specializationField,
                        supervizorIdField);
            }
        });
        center.add(signupButton);

        signup.setVisible(true);
    }

    private void handleSignup(JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox,
            JTextField firstNameField, JTextField lastNameField, JTextField degreeField, JTextField yearsOfExpField,
            JTextField specializationField, JTextField supervizorIdField) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            JOptionPane.showMessageDialog(signup, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = new DatabaseConnection().connect()) {
            String checkQuery = "SELECT COUNT(*) FROM UserAccounts WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(signup, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String insertQuery = "INSERT INTO UserAccounts (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            insertStmt.setString(3, role);
            insertStmt.executeUpdate();

            if (role.equals("Doctor")) {
                String insertDoctorQuery = "INSERT INTO Doctor (id, first_name, last_name, degree, years_of_exp, specialization) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertDoctorStmt = conn.prepareStatement(insertDoctorQuery);
                insertDoctorStmt.setLong(1, Long.parseLong(username));
                insertDoctorStmt.setString(2, firstName);
                insertDoctorStmt.setString(3, lastName);
                insertDoctorStmt.setString(4, degreeField.getText());
                insertDoctorStmt.setInt(5, Integer.parseInt(yearsOfExpField.getText()));
                insertDoctorStmt.setString(6, specializationField.getText());
                insertDoctorStmt.executeUpdate();
            } else if (role.equals("Nurse")) {
                String insertNurseQuery = "INSERT INTO Nurse (id, first_name, last_name, supervizor_id) VALUES (?, ?, ?, ?)";
                PreparedStatement insertNurseStmt = conn.prepareStatement(insertNurseQuery);
                insertNurseStmt.setLong(1, Long.parseLong(username));
                insertNurseStmt.setString(2, firstName);
                insertNurseStmt.setString(3, lastName);
                insertNurseStmt.setLong(4, Long.parseLong(supervizorIdField.getText()));
                insertNurseStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(signup, "Account Created Successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            signup.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(signup, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}