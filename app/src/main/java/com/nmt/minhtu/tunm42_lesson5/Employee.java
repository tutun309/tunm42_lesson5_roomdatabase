package com.nmt.minhtu.tunm42_lesson5;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity(tableName = "employee")
public class Employee{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int imgAvatar;
    private String name;
    private String phoneNumber;
    private String dateOfBirth;
    private String department;
    private int gender;

    public Employee() {
    }

    public Employee(int imgAvatar, String name, String phoneNumber, String birthDay, String department, int gender) {
        this.imgAvatar = imgAvatar;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = birthDay;
        this.department = department;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(int imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getGender() {
        return gender;
    }
    public String getGenderString() {
        if(gender == 0) return "Nam";
        else return "Nữ";
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge(){
        try {
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            return currentYear - Integer.parseInt(dateOfBirth.split("/")[2]);
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", imgAvatar=" + imgAvatar +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", department='" + department + '\'' +
                ", gender=" + gender +
                '}';
    }
}
