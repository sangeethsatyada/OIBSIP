import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
public class Register extends PnrFrame implements ActionListener{
    private JTextField nameField, ageField, trainNumberField, trainNameField, dateField, fromAddressField, toAddressField;
    private JComboBox<String> classTypeComboBox;
    private Connection conn;
    private JButton cancelbutton;
    public void registraionForm() {
        setTitle("Passenger Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 2));

        JLabel nameLabel = new JLabel("Name of Passenger:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel trainNumberLabel = new JLabel("Train Number:");
        JLabel trainNameLabel = new JLabel("Train Name:");
        JLabel dateLabel = new JLabel("Date of Journey:");
        JLabel fromAddressLabel = new JLabel("From Address:");
        JLabel toAddressLabel = new JLabel("To Address:");
        JLabel classTypeLabel = new JLabel("Class Type:");

        nameField = new JTextField();
        ageField = new JTextField();
        trainNumberField = new JTextField();
        trainNameField = new JTextField();
        dateField = new JTextField();
        fromAddressField = new JTextField();
        toAddressField = new JTextField();
        classTypeComboBox = new JComboBox<>(new String[]{"First Class", "Second Class", "Third Class"});

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = "jdbc:mysql://127.0.0.1:3306/oasis";
                    String username = "root";
                    String password = "sangeeth7674@";
                    conn = DriverManager.getConnection(url, username, password);
                    if (conn != null) {
                        System.out.println("Success");
                    } else {
                        System.out.println("NO");
                    }

                    String sql = "INSERT INTO details (name, age, train_num, train_name, da_of_jrny, f_add, t_add, ctype, pnr) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = conn.prepareStatement(sql);

                    String name = nameField.getText();
                    String age = ageField.getText();
                    String trainNumber = trainNumberField.getText();
                    String trainName = trainNameField.getText();
                    String date = dateField.getText();
                    String fromAddress = fromAddressField.getText();
                    String toAddress = toAddressField.getText();
                    String classType = (String) classTypeComboBox.getSelectedItem();
                    Random random = new Random();

                    // Generate and print a random 8-digit number
                    int randomNumber = 10000000 + random.nextInt(90000000);
                    System.out.println("Random 8-digit number: " + randomNumber);

                    // Set parameters for the prepared statement
                    statement.setString(1, name);
                    statement.setString(2, age);
                    statement.setString(3, trainNumber);
                    statement.setString(4, trainName);
                    statement.setString(5, date);
                    statement.setString(6, fromAddress);
                    statement.setString(7, toAddress);
                    statement.setString(8, classType);
                    statement.setInt(9, randomNumber);

                    // Execute the statement
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(Register.this, "Name : "+name+"\n"+"Age : "+age+"\n"+"TrainNumber : "+trainNumber+"\n"+"TrainName : "+trainName+"\n"+"Date : "+date+"\n"+"From : "+fromAddress+"\n"+"To : "+toAddress+"\n"+"ClaasType : "+classType+"\n"+"PNR : "+randomNumber+"\n", "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("A new record was inserted successfully!");
                        dispose();
                        Railway r2=new Railway();
                        r2.rail();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelbutton = new JButton("Cancel");
        cancelbutton.addActionListener(this);
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(trainNumberLabel);
        add(trainNumberField);
        add(trainNameLabel);
        add(trainNameField);
        add(dateLabel);
        add(dateField);
        add(fromAddressLabel);
        add(fromAddressField);
        add(toAddressLabel);
        add(toAddressField);
        add(classTypeLabel);
        add(classTypeComboBox);
        add(new JLabel()); // Placeholder for alignment
        add(submitButton);
        add(cancelbutton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==cancelbutton){
        PnrFrame p=new PnrFrame();
        p.pnr();
       }
    }
}
