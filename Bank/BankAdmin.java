import java.util.Random;

public class BankAdmin {
    public void createAccountnumber(String name,String mobileNumber,String password){
        String accountNumber;
        Random random=new Random();
        int length=6;
            accountNumber="";
            for (int i = 0; i < length; i++) {
                accountNumber+=((Integer)random.nextInt(10)).toString();
            }
         new BankDatabase().updatetoDatabase(name, mobileNumber, password, accountNumber);
    }

    
}

