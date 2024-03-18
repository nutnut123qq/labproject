/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv;

    import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author PC
 */
public class StudentController {
   

    private Connection connection;

    public StudentController() {
        try {
            // Thay đổi thông tin kết nối database
            String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsv";
            String username = "nutnut";
            String password = "Anhyeuem123123";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm thực thi câu lệnh INSERT, UPDATE, DELETE
    public void executeNonQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm thực thi câu lệnh SELECT
    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hàm đăng nhập
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM GiaoVien WHERE Username = ? AND Password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hàm lấy quyền hạn tài khoản
    public int getRole(String username) {
        String sql = "SELECT Role FROM TaiKhoan WHERE Username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Hàm thêm sinh viên
    public void addStudent(Student student) {
        String sql = "INSERT INTO SinhVien (MaSinhVien, HoTen, DiaChi, SoDienThoai, MaGiaoVien) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getStudentID());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setInt(4, student.getPhone());
            preparedStatement.setInt(5, student.getTeacherID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm sửa thông tin sinh viên
    public void updateStudent(Student student) {
        String sql = "UPDATE SinhVien SET HoTen = ?, DiaChi = ?, SoDienThoai = ?, MaGiaoVien = ? WHERE MaSinhVien = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setInt(3, student.getPhone());
            preparedStatement.setInt(4, student.getTeacherID());
            preparedStatement.setInt(5, student.getStudentID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm xóa sinh viên
    
    public void deleteStudent(int studentID) {
    String sql = "DELETE FROM SinhVien WHERE MaSinhVien = ?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, studentID);
        preparedStatement.executeUpdate();
        System.out.println("Xóa sinh viên thành công!");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Xóa sinh viên thất bại!");
    }
}

    // Hàm thêm giáo viên
    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO GiaoVien (MaGiaoVien, HoTen, DiaChi, MatKhau, SoDienThoai, MonHoc) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teacher.getTeacherID());
            preparedStatement.setString(2, teacher.getName());
            preparedStatement.setString(3, teacher.getAddress());
            preparedStatement.setString(4, teacher.getPassword());
            preparedStatement.setInt(5, teacher.getPhone());
            preparedStatement.setString(6, teacher.getSubject());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm sửa thông tin giáo viên
    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE GiaoVien SET HoTen = ?, DiaChi = ?, MatKhau = ?, SoDienThoai = ?, MonHoc = ? WHERE MaGiaoVien = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getAddress());
            preparedStatement.setString(3, teacher.getPassword());
            preparedStatement.setInt(4, teacher.getPhone());
            preparedStatement.setString(5, teacher.getSubject());
            preparedStatement.setInt(6, teacher.getTeacherID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm xóa giáo viên
    public void deleteTeacher(int teacherID) {
        String sql = "DELETE FROM GiaoVien WHERE MaGiaoVien = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teacherID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm tìm kiếm sinh viên
    public ResultSet findStudent(String keyword) {
        String sql = "SELECT * FROM SinhVien WHERE MaSinhVien LIKE ? OR HoTen LIKE ? OR MaGiaoVien LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
    
    