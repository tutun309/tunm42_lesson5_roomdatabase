package com.nmt.minhtu.tunm42_lesson5.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nmt.minhtu.tunm42_lesson5.Employee;

import java.util.List;

@Dao
public interface EmployeeDAO {
    @Insert
    void addEmployee(Employee employee);

    @Query("SELECT * FROM employee")
    List<Employee> getListEmployee();

    @Delete
    void removeEmployee(Employee employee);

    @Query("SELECT * FROM employee WHERE id = :id")
    Employee getEmployeeById(int id);

    @Update
    int updateEmployee(Employee employee);

}
