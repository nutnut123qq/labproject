
package testatm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtmManagement {
    
    
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

    public static void main(String[] args) throws SQLException {
        boolean login = false;
        int menu_type = 1;
        Scanner sc = new Scanner(System.in);
        AtmController at = new AtmController();

        Connection connection = null;
        try {
            // Establish secure connection
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);


        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String createTableSql = "IF OBJECT_ID(N'dbo.Atm', N'U') IS NULL CREATE TABLE Atm ("
                + "accountNumber VARCHAR(20) PRIMARY KEY,"
                + "password VARCHAR(20) NOT NULL,"
                + "accountBalance BIGINT NOT NULL CHECK (accountBalance >= 0))";

        try {
            connection.createStatement().executeUpdate(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //read 
        String sql_read_menu = "SELECT * FROM [Atm].[dbo].[ATM_MENU]";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_read_menu);

        String sql_read_users = "SELECT * FROM [Atm].[dbo].[ATM_USERS]";
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement.executeQuery(sql_read_users);

        // Main menu loop
        int choice;
        while (true) {

            Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from [dbo].[ATM_MENU]");
            
            if(!login)
            {
                while(rs.next()){
                    if(rs.getString("menu_type").equals("2"))
                    System.out.println(rs.getString("menu_index")+" "+rs.getString("menu_name"));
                }
            }
            else{
            while(rs.next()){
                System.out.println(rs.getString("menu_index")+" "+rs.getString("menu_name"));}
            }
            
            
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Thoat thanh cong!");
                    return;
                case 1:
                    at.withdraw(connection);
                    break;
                case 2:
                    at.checkBalance(connection);
                    break;
                case 3:
                    at.transfer(connection);
                    break;
                case 4:
                    at.recharger(connection);
                    break;
                case 5:
                    String ac,
                     ps;
                    sc.nextLine();
                    System.out.println("So tai khoan: ");
                    ac = sc.nextLine();
                    System.out.println("Mat khau: ");
                    ps = sc.nextLine();
                    at.login(connection, ac, ps);
                    if(at.login(connection, ac, ps)){
                        login = true;
                    }
                    else login = false;
                    break;
                default:
                    System.out.println("Wrong choice. Please choose 0-5.");
            }
        }
    }
}
