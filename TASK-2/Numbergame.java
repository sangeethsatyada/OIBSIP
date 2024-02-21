import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class Numbergame implements ActionListener
{
    JFrame frame=new JFrame("NumberGuessingGame");
    JLabel l1=new JLabel("Guess the number between 1-100");
    static JTextField t1=new JTextField();
    JButton b1=new JButton("submit");
    static int score=0;
    static JLabel scorel2=new JLabel("your score is : "+score);
    static JLabel message=new JLabel("These are your messages");
    static int rand_number;
    static int chance=5;
    static JLabel chancesl3=new JLabel("your chances are : "+chance);
    JButton reset=new JButton("Reset");
    JButton quit=new JButton("Quit");
    Numbergame(){
        rand_number = generateRandom();
        frame.setVisible(true);
        frame.setSize(300,300);
        frame.setLayout(null);
        l1.setBounds(50,0,200,100);
        t1.setBounds(100,60,100,20);
        b1.setBounds(100,100,100,20);
        scorel2.setBounds(150,-30,100,100);
        chancesl3.setBounds(100,100,400,100);
        message.setBounds(30,120,500,100);
        reset.setBounds(10,190,100,20);
        quit.setBounds(10, 220, 100, 20);
        scorel2.setForeground(Color.BLUE);
        frame.add(l1);
        frame.add(t1);
        frame.add(b1);
        frame.add(scorel2);
        frame.add(chancesl3);
        frame.add(message);
        frame.add(reset);
        frame.add(quit);
        b1.addActionListener(this);
        reset.addActionListener(this);
        quit.addActionListener(this);
    }
    public static int generateRandom(){
        Random rand=new Random();
        int randnumb=rand.nextInt(100);
        return randnumb;
    }
    public static void verifyResults(int userNumber){

        System.out.println(rand_number);
        if(rand_number==userNumber){
            score++;
            message.setForeground(Color.GREEN);
            t1.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            message.setText("Success !! You guessed the number");
            scorel2.setText("your score is : "+score);
            int nrand = generateRandom();
            rand_number=nrand;
            System.out.println(nrand);
            chance=5;
            chancesl3.setText("your chances are : "+chance);
            t1.setText("");
            t1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        else{
            t1.setBorder(BorderFactory.createLineBorder(Color.RED));
            chance--;
            message.setForeground(Color.RED);
            chancesl3.setText("your chances are : "+chance);
            if(chance==0){
                chancesl3.setText("gameOver.!! Restart the game");
                t1.setEditable(false);
            }
            if(userNumber<rand_number){
                if((rand_number-userNumber)<10){
                    message.setText("Your number is smaill, but you are colse");
                }
                else{
                    message.setText("Your number is too small");
                }
            }
            else{
                if((userNumber-rand_number)<10){
                    message.setText("Your number is large..!, But you are close");
                }
                else{
                    message.setText("Your number is too large");
                }
            }
        }
    }
    public static void main(String args[]){
        new Numbergame();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (!t1.getText().isEmpty()) {
                int userNumber = Integer.parseInt(t1.getText());
                verifyResults(userNumber);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a number.");
            }
        }
        if(e.getSource()==reset){
            int result = JOptionPane.showConfirmDialog(frame, "Do you want to Reset?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                chance=5;
                score=0;
                scorel2.setText("your score is : "+score);
                chancesl3.setText("your chances are : "+chance);
                message.setText("These are your messages");
            } else {
                JOptionPane.showMessageDialog(frame, "You clicked NO or closed the dialog.");
            }
        }
        if(e.getSource()==quit){
            frame.dispose();
        }
    }
}