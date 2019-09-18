package com.example.roomdatabaseschoolinfo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class},version = 2)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase db;

    // method is abstract.
    public abstract StudentDAO getStudentDao();


    // method declare.
    public static StudentDatabase getInstance(Context  context){

        if(db!=null){
            return db;
        }


//        db = Room.databaseBuilder(context, StudentDatabase.class,"student_db")
//                .allowMainThreadQueries()
//                .build();

        db = Room.databaseBuilder(context,
                StudentDatabase.class, "student_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        return db;
    }

}
