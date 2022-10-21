import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageUser {
    Scanner input = new Scanner(System.in);

    public void addCustomer() {
        System.out.println("Enter your name");
        String name = new UserInputValidation().validName();
        System.out.println("Enter your Mobilenumber");
        String mobile = new UserInputValidation().validPhonenumber();
        System.out.println("Enter your password");
        String password = input.nextLine();
        new BankAdmin().createAccountnumber(name, mobile, password);
        login();

    }

    public void login() {
        System.out.println("```````````````````````````````");
        System.out.println("LOGIN PAGE");
        System.out.println("```````````````````````````````");
        System.out.println("Please enter your login details:");
        System.out.println("Enter your username: ");
        String name = input.next();
        System.out.println("Enter your password");
        String passwords = input.next();
        if (new BankDatabase().isaccountVerify(name, passwords)==true) {
            bankprocess();
        } else {
            System.out.println("------------------------------");
            System.out.println("USERNAME OR PASSWORD IS WRONG");
            System.out.println("------------------------------");
            System.out.println("Enter your Details Correctly");
            login();
        }

    }

    public void bankprocess() {
        new UserDetails().toString();//show account number
        byte selection = 0;
        System.out.println("Please select an option");
        System.out.println("1) Balance Enquiry");
        System.out.println("2) WITHDRAWAL");
        System.out.println("3) Deposit");
        System.out.println("4) Exit");
        System.out.print("Your selection: ");
        while (true) {

            try {
                 input = new Scanner(System.in);
                selection = input.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Enter valid option");

            }

            switch (selection) {
                case 1: {
                    // checkBalance();
                    break;
                }
                case 2: {
                    // withDraw();
                    break;
                }
                case 3: {
                    // deposit();
                    break;
                }
                case 4: {
                    return;
                }
                default:
                    System.out.println("Enter the correct option");
            }
        }
    }
}
