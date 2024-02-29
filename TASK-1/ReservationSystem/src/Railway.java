import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Railway extends Register
{
    private JTextField usernameField;
    private JPasswordField passwordField;
    public void rail()
    {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Perform login validation here
                if (username.equals("sangeethsatyada") && password.equals("sangeeth7674@")) {
                    JOptionPane.showMessageDialog(Railway.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    Register r=new Register();
                    r.registraionForm();
                } else {
                    JOptionPane.showMessageDialog(Railway.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Placeholder for alignment
        add(loginButton);

        setVisible(true);
    }
}