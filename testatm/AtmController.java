
package testatm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.rsa.RSACore;

public class AtmController extends ArrayList<Atm>{
    
    Atm currentAtm = null;
    
   private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Atm;"
            + "encrypt=true;"
            + "trustServerCertificate=true";
    private static String USER_NAME = "nutnut";
    private static String PASSWORD = "Anhyeuem123123";
    
    public static Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlsever.jdbc.SQLSeverDriver").newInstance();
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("connet failure!");
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            Logger.getLogger(AtmManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AtmManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    Connection connection = getConnection(DB_URL, USER_NAME, PASSWORD);

    //1.rut tien
    public void withdraw(Connection connection) throws SQLException {
    if (currentAtm == null) {
        System.out.println("Please login");
        return;
    }

    String accountNumber = currentAtm.getAccountNumber();

    // **Retrieve current balance from database:**
    String sql_read_balance = "SELECT balance FROM [Atm].[dbo].[ATM_USERS] WHERE Account = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql_read_balance);
    preparedStatement.setString(1, accountNumber);
    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
        long currentBalance = resultSet.getLong("balance");

        Scanner sc = new Scanner(System.in);
        long amount;

        System.out.println("Enter the amount you want to withdraw: ");
        amount = sc.nextLong();

        if (currentBalance < 50000) {
            System.out.println("Your account balance is less than 50000");
            return;
        }

        if (amount < 50000 || amount > 5000000) {
            System.out.println("Invalid amount. Enter again");
            return;
        }

        if (currentBalance < amount) {
            System.out.println("Your account balance is not enough. Enter again");
            return;
        }

        // **Update balance after validation:**
        currentBalance -= amount;
        currentAtm.setAccountBalance(currentBalance);

        String sql_update = "UPDATE [Atm].[dbo].[ATM_USERS] SET balance = ? WHERE Account = ?";
        preparedStatement = connection.prepareStatement(sql_update);
        preparedStatement.setLong(1, currentBalance);
        preparedStatement.setString(2, accountNumber);
        preparedStatement.executeUpdate();

        System.out.println("Withdrawal successful. Your current balance is " + currentBalance);
    } else {
        System.out.println("Error retrieving balance. Please try again.");
    }
}

    //2. so du tk
    public void checkBalance(Connection connection) {
    if (currentAtm == null) {
        System.out.println("Please login");
        return;
    }

    String accountNumber = currentAtm.getAccountNumber();

    try {
        String sql = "SELECT balance FROM [Atm].[dbo].[ATM_USERS] WHERE Account = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            System.out.println("Your account balance: " + balance);
        } else {
            System.out.println("Error retrieving balance. Please try again.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error retrieving balance. Please try again.");
    }
}

    //3. chuyen khoan
    public void transfer(Connection connection) {
        
    Scanner sc = new Scanner(System.in);
    if (currentAtm == null) {
        System.out.println("Please login");
        return;
    }
    System.out.println("5 account you can transfer:");
    for (int i = 0; i < 6; i++) {
        if(this.get(i).getAccountNumber()==currentAtm.getAccountNumber());
        else
        System.out.println(i + 1 + ". " + this.get(i).getAccountNumber());
    }

    int accountNumberIndex;
    while(true)
    {
    System.out.println("Enter number that you want to transfer: ");
     accountNumberIndex = sc.nextInt() - 1;
    
    if (accountNumberIndex < 0 || accountNumberIndex >= 6) {
        System.out.println("Choose again");
    }
    else break;
    }
    
    //number account to transfer
    String recipientAccountNumber = this.get(accountNumberIndex).getAccountNumber();

    System.out.println("Amount to transfer: ");
    long amount = sc.nextLong();

    if (currentAtm.getAccountBalance() < amount) {
        System.out.println("Insufficient balance for transfer.");
        return;
    }

    currentAtm.setAccountBalance(currentAtm.getAccountBalance() - amount);
    this.get(accountNumberIndex).setAccountBalance(this.get(accountNumberIndex).getAccountBalance() + amount);
    System.out.println("Transfer successful");
}
    
    //4. nap tien
    public void recharger(Connection connection) {
        Scanner sc = new Scanner(System.in);
    if (currentAtm == null) {
        System.out.println("Please login");
        return;
    }
    
    System.out.println("Amount to deposit: ");
    long amount = sc.nextLong();

    currentAtm.setAccountBalance(currentAtm.getAccountBalance() + amount);

    System.out.println("Deposit successful. Your current balance is " + currentAtm.getAccountBalance());
}

 
    
    public boolean login(Connection connection, String accountNumber, String password) {
    try {
        String sql = "SELECT * FROM dbo.ATM_USERS WHERE Account = ? AND Password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, accountNumber);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Tìm thấy người dùng trong database
            System.out.println("Dang nhap thanh cong");

            // Tạo đối tượng Atm từ dữ liệu database
            Atm atm = new Atm();
            atm.setAccountNumber(resultSet.getString("Account"));
            atm.setPassword(resultSet.getString("Password"));
            // Thêm các thuộc tính khác nếu cần (ví dụ: balance)

            currentAtm = atm;
            return true;
        } else {
            System.out.println("Wrong account or password.");
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public void exit()
    {
        currentAtm = null;
    }

    
 
}

