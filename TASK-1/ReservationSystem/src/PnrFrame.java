import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PnrFrame extends JFrame implements ActionListener {
    private JTextField pnrTextField;
    private JButton submitButton;

    public void pnr() {
        setTitle("PNR Number Lookup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JLabel pnrLabel = new JLabel("Enter PNR Number:");
        pnrTextField = new JTextField();
        submitButton = new JButton("Submit");

        add(pnrLabel);
        add(pnrTextField);
        add(submitButton);
        setVisible(true);
        submitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int pnrnumber = Integer.parseInt(pnrTextField.getText());
        if (e.getSource() == submitButton) {
            String url = "jdbc:mysql://127.0.0.1:3306/oasis";
            String username = "root";
            String password = "sangeeth7674@";
            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                String query = "SELECT * FROM details WHERE pnr=?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(1, pnrnumber);
                ResultSet resultSet = statement.executeQuery();
                String name, train_name, da_of_jrny, f_add, t_add, ctype;
                int age, train_num, pnr;
                while (resultSet.next()) {
                    name = resultSet.getString("name");
                    age = resultSet.getInt("age");
                    train_num = resultSet.getInt("train_num");
                    train_name = resultSet.getString("train_name");
                    da_of_jrny = resultSet.getString("da_of_jrny");
                    f_add = resultSet.getString("f_add");
                    t_add = resultSet.getString("t_add");
                    ctype = resultSet.getString("ctype");
                    pnr = resultSet.getInt("pnr");

                    // Display details in JOptionPane
                    String message = "Name: " + name + "\n" +
                            "Age: " + age + "\n" +
                            "Train Number: " + train_num + "\n" +
                            "Train Name: " + train_name + "\n" +
                            "Date of Journey: " + da_of_jrny + "\n" +
                            "From Address: " + f_add + "\n" +
                            "To Address: " + t_add + "\n" +
                            "Class Type: " + ctype + "\n" +
                            "PNR: " + pnr + "\n";

                    int option = JOptionPane.showConfirmDialog(PnrFrame.this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

                    // Check the user's choice
                    if (option == JOptionPane.YES_OPTION) {
                        String deleteSql = "DELETE FROM details WHERE pnr=?";
                        PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);
                        deleteStatement.setInt(1, pnrnumber);
                        int rowsDeleted = deleteStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(PnrFrame.this, "Ticket with PNR " + pnrnumber + " deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    } else if (option == JOptionPane.NO_OPTION) {
                        System.out.println("User clicked No");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PnrFrame frame = new PnrFrame();
        frame.pnr();
    }
}
