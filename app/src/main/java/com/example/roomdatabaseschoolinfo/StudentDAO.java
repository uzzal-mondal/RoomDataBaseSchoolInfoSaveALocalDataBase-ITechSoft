package com.example.roomdatabaseschoolinfo;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert  // collection akadhik object declare korar somoy eta pass korbo.
    // ... this is collection
    long inserNewStudent(Student student);

    // Query
    @Query("select * from tbl_student")
    List<Student> getAllStudents();


    //Edit
    @Query(" select * from tbl_student where studentID like:id")
    Student getStudentById(long id);



    // Delete
    @Delete
    int deleteStudent (Student student);

    // update data
    @Update
    int updateStudent(Student student);



}
