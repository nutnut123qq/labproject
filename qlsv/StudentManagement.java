
package qlsv;

import atm.AtmManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class StudentManagement{

    
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=qlsv;"
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

//        ArrayList<AtmController> array = new ArrayList<>();
                    String ac, ps;
                    boolean login = false;

                    Scanner sc = new Scanner(System.in);
        StudentController st = new StudentController();
        int choice;
        
                    System.out.println("4.login");
                    
//                    Connection connection = null;
//        try {
//            // Establish secure connection
//            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return;
//        }
         Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from [dbo].[Menu]");
            
            if(!login)
            {
                while(rs.next()){
                    if(rs.getString("teacherID").equals("2"))
                    System.out.println(rs.getString("menu_name"));
                }
            }
            else{
            while(rs.next()){
                System.out.println(rs.getString("menu_name"));}
            }
            
        
//        while (true) {
//            System.out.println("=================");
//            System.out.println("Menu");
//            System.out.println("1.add student");
//            System.out.println("2.edit student");
//            System.out.println("3.delete student");
//            System.out.println("4.logout");
//            System.out.println("0.Exit");
//            System.out.println("Enter your choice");
//
//            choice = sc.nextInt();
//            switch (choice) {
//                case 0: {
//                    return;
//                }
//                case 1: {
//                    System.out.println("1.add student");
//                    Student st1 = new Student();
//                    System.out.print("student name:");
//                    sc.nextLine();
//                    st1.studentName = sc.nextLine();
//                    System.out.println("Student address");
//                    st1.address = sc.nextLine();
//                    System.out.println("student phone");
//                    st1.phone = sc.nextInt();
//                    System.out.println("teacher id");
//                    st1.teacherID = sc.nextInt();
//                    st.addStudent(st1);
//                    break;
//                }
//                case 2: {
//                    System.out.println("2.edit student");
//                    System.out.println("Enter Student that you need to edit:");
//                    Student st2 = new Student();
//                    System.out.print("student name:");
//                    sc.nextLine();
//                    st2.studentName = sc.nextLine();
//                    System.out.println("Student address");
//                    st2.address = sc.nextLine();
//                    System.out.println("student phone");
//                    st2.phone = sc.nextInt();
//                    System.out.println("teacher id");
//                    st2.teacherID = sc.nextInt();
//                    
//                    st.updateStudent(st2);
//                    break;
//                }
//                case 3: {
//                    System.out.println("3.delete student");
//                    System.out.println("Enter student id:");
//                    Student st3 = new Student();
//                    st3.studentID = sc.nextInt();
//                    st.deleteStudent(st3.studentID);
//                    break;
//                }
//                
//                
//                case 5:{
//                    System.out.println("5.log out");
//                    ac =null;
//                    ps=null;
//                    
//                }
//                default:
//                    System.out.println("Enter again(0~4)");
//                    break;
//            }
//        }
    }
}
