
package qlsv;

import java.util.Scanner;

public class Teacher {
    private int teacherID;
    private String teacherName;
    private String address;
    private String password;
    private int phone;
    private String subject;

    public Teacher() {
    }

    public Teacher(int teacherID, String name, String address, String password, int phone, String subject) {
        this.teacherID = teacherID;
        this.teacherName = name;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.subject = subject;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return teacherName;
    }

    public void setName(String name) {
        this.teacherName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teacherID=" + teacherID + ", name=" + teacherName + ", address=" + address + ", password=" + password + ", phone=" + phone + ", subject=" + subject + '}';
    }

}
