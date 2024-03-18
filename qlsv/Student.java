
package qlsv;


public class Student {
    int studentID;
    String studentName;
    String address;
    int phone;
    int teacherID;

    public Student() {
    }

    
    public Student(int studentID, String studentName, String address, int phone, int teacherID) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.address = address;
        this.phone = phone;
        this.teacherID = teacherID;
    }

    
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String name) {
        this.studentName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", name=" + studentName + ", Address=" + address + ", phone=" + phone + ", teacherID=" + teacherID + '}';
    }
    
    
    
}
