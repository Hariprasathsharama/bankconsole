import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class BankDatabase {
    Scanner input=new Scanner(System.in);
    public void updatetoDatabase(String name, String mobileNumber, String password, String accountNumber) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bankapplication", "root",
                "Chrisevans@2309")) {
            String sql = "Insert into customerdata" + "(username,mobileNumber,accountNumber,password) values"
                    + "(?,?,?,?);";
            PreparedStatement preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setString(1, name);
            preparedstatement.setString(2, mobileNumber);
            preparedstatement.setString(3, accountNumber);
            preparedstatement.setString(4, password);
            preparedstatement.addBatch();
            int[] count = preparedstatement.executeBatch();
            System.out.println(Arrays.toString(count));
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isaccountVerify(String name,String password){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bankapplication", "root", "Chrisevans@2309");
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from bank");
            int flag = 0;
            while (resultset.next()) {
                if (resultset.getString(1).equals(name) && resultset.getString(2).equals(password)==true) {
                    return true;

                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public void checkBalance() throws SQLException  {

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Bankapplication", "root", "Chrisevans@2309");
        java.sql.Statement statement = con.createStatement();
        System.out.println("Enter the account number");
        String accountNumber=input.next();
        ResultSet resultSet = statement.executeQuery("select accountNumber from bank");
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(accountNumber)==true) {
                ResultSet resultSet2=statement.executeQuery("select balance from bank");
                System.out.println("The balance amount is"+resultSet2.getString(1));
                new ManageUser().bankprocess();
            }

        }
        System.out.println("Your account number not exsits");
        new ManageUser().login();

    } 

    public void withdraw() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bankapplication", "root", "Chrisevans@2309");
            java.sql.Statement statement = con.createStatement();
            System.out.println("Enter the account number");
            String accountNumber=input.next();
            ResultSet resultSet = statement.executeQuery("select accountNumber from bank");
            int withdrawamount = 0;
            while (resultSet.next()) {

                if (accountNumber .equals(resultSet.getInt(1))) {
                    System.out.print("Enter the amount to withdraw :");
                    int amount = input.nextInt();

                    int currentamount = resultSet.getInt(5);

                    if (amount < currentamount) {
                        withdrawamount = currentamount - amount;
                        System.out.println("Your new balance is " + withdrawamount);
                        statement.executeUpdate(
                                "update bank set balance=" + " " + withdrawamount + " " + "where accountnumber="
                                        + accountNumber + " ");
                        new ManageUser().bankprocess();
                        
                    } else {
                        System.out.println("Insufficient balance");
                        new ManageUser().login();
                    }
                }
            }

            System.out.println("No account found");
            withdraw();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
        

