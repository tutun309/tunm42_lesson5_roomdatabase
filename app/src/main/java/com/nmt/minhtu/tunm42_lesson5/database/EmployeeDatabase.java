package com.nmt.minhtu.tunm42_lesson5.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nmt.minhtu.tunm42_lesson5.Employee;

@Database(entities = Employee.class, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "employee_db";
    private static EmployeeDatabase instance;

    public static synchronized EmployeeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),EmployeeDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract EmployeeDAO employeeDAO();
}
