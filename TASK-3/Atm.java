import java.util.ArrayList;
import java.util.Scanner;

public class Atm {
    private Scanner scanner;
    private ArrayList<String> transactionHistory;
    private int accountBalance;

    public Atm() {
        this.scanner = new Scanner(System.in);
        this.transactionHistory = new ArrayList<>();
        this.accountBalance = 1000;
    }

    public void transaction() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void withdraw() {
        System.out.println("Enter amount to be withdrawn: ");
        int withdrawalAmount = scanner.nextInt();
        
        if (withdrawalAmount > accountBalance) {
            System.out.println("Insufficient Funds");
        } else {
            accountBalance -= withdrawalAmount;
            transactionHistory.add("Withdrawal: " + withdrawalAmount);
            System.out.println("Amount withdrawl successfully");
        }
    }

    public void deposit() {
       System.out.println("Enter amaount to be deposit : ");
       int namount=scanner.nextInt();
       accountBalance+=namount;
       transactionHistory.add("Deposit: "+namount);
       System.out.println("Amount deposited successfully");
    }

    public void transfer() {
        System.out.println("Enter account Number : ");
        int accno=scanner.nextInt();
        System.out.println("Enter amount to be transfer: ");
        int transferamount = scanner.nextInt();
        
        if (transferamount > accountBalance) {
            System.out.println("Insufficient Funds");
        } else {
            accountBalance -= transferamount;
            transactionHistory.add("Transferred: " + transferamount);
            System.out.println("Amount transferred successfully");
        }
    }

    public static void main(String[] args) {
        Atm atm = new Atm();
        int userId = 123456;
        int pin = 4495;

        System.out.println("Enter userId:");
        int tempUserId = atm.scanner.nextInt();
        System.out.println("Enter Pin:");
        int tempPin = atm.scanner.nextInt();

        if (userId == tempUserId && pin == tempPin) {
            while(true){
            System.out.print("\n1.Transaction History\n2.Withdraw\n3.Deposit\n4.Transfer\n5.Quit");
            System.out.println("\nEnter your Choice : ");
            int choice = atm.scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.transaction();
                    break;
                case 2:
                    atm.withdraw();
                    break;
                case 3:
                    atm.deposit();
                    break;
                case 4:
                    atm.transfer();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
        } else {
            System.out.println("Invalid userId and Pin");
        }

    }
}
