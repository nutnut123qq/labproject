

package com.mycompany.adsasd;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Adsasd {
private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
        +"databaseName=Northwind;"
        +"encrypt=true;"
        + "trustServerCertificate=true";
        private static String USER_NAME = "nutnut";
        private static String PASSWORD = "Anhyeuem123123";
        
        
    
        
        public static void main(String[] args) {
        try {
            //crate statement
            try ( // connect to database 'nutnut'
                    Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD)) {
                //crate statement
                Statement stmt = conn.createStatement();
                //get data from table 'student'
                ResultSet  rs  = stmt.executeQuery("select * from [dbo].[Orders]"); //select
                //show data
                while (rs.next()) {
//                    System.out.println(rs.getInt(1)+ " " + rs.getString(2)
//                            + " " + rs.getString(3));
                       System.out.println(rs.getString(1) + " " + rs.getString(2));

                }
                //close connection
            }
        } catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }
       public static Connection getConnection(String dbURL,String userName,String password)
        {
            Connection conn = null;
            try{
                conn = DriverManager.getConnection(dbURL, userName, password);
                System.out.println("connect successfully");
            } catch(SQLException ex)
            {
                System.out.println("connet failure!");
                ex.printStackTrace();
    }
            return conn;
        }
}
