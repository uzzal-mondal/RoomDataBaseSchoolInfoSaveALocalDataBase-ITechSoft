package com.example.roomdatabaseschoolinfo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "tbl_student")
public class Student implements Serializable {

    // this is student properties.
    @Ignore
    private int image;
    @PrimaryKey(autoGenerate = true) // this is my id, doing the process data update and deleted.
    private long studentID;
    @ColumnInfo(name = "col_studentname")
    private String studentName;
    private int studentRoll;
    private int studentTotal;
    private String studentClass;
    private String studentAddress;
    private String studentClassSelectSpin;

    // create a constructor with setter and getter method.

     @Ignore
    public Student(int image, String studentName, int studentRoll, int studentTotal, String studentClass, String studentAddress,String studentClassSelectSpin ) {
        this.image = image;
        this.studentName = studentName;
        this.studentRoll = studentRoll;
        this.studentTotal = studentTotal;
        this.studentClass = studentClass;
        this.studentAddress = studentAddress;
        this.studentClassSelectSpin = studentClassSelectSpin;
    }

    @Ignore
    public Student(long studentID, String studentName, int studentRoll, int studentTotal, String studentClass, String studentAddress,String studentClassSelectSpin ) {

        this.studentID = studentID;
        this.studentName = studentName;
        this.studentRoll = studentRoll;
        this.studentTotal = studentTotal;
        this.studentClass = studentClass;
        this.studentAddress = studentAddress;
        this.studentClassSelectSpin = studentClassSelectSpin;
    }

    public Student(String studentName, int studentRoll, int studentTotal, String studentClass, String studentAddress,String studentClassSelectSpin ) {
        this.studentName = studentName;
        this.studentRoll = studentRoll;
        this.studentTotal = studentTotal;
        this.studentClass = studentClass;
        this.studentAddress = studentAddress;
       this.studentClassSelectSpin = studentClassSelectSpin;
    }



    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(int studentRoll) {
        this.studentRoll = studentRoll;
    }

    public int getStudentTotal() {
        return studentTotal;
    }

    public void setStudentTotal(int studentTotal) {
        this.studentTotal = studentTotal;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentClassSelectSpin() {
        return studentClassSelectSpin;
    }

    public void setStudentClassSelectSpin(String studentClassSelectSpin) {
        this.studentClassSelectSpin = studentClassSelectSpin;
    }

    // create a dami method for the student list
    // then return to collection.
    public static List<Student> getStudentList(){

        // create a collection.
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(R.drawable.school,"Uzzal",1,20,"Class 1","khulna","Class 9"));





        return  studentList;

    }
}
