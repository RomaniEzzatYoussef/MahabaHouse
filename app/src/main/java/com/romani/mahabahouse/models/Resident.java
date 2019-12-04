package com.romani.mahabahouse.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "residents")
public class Resident
{
    @PrimaryKey(autoGenerate = true)
    private long residentID;

    @ColumnInfo(name = "residentName")
    private String residentName;

    @ColumnInfo(name = "mobile")
    private String mobile;

    @ColumnInfo(name = "room")
    private String room;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "nationalNumber")
    private String nationalNumber;

    @ColumnInfo(name = "birthDate")
    private String birthDate;

    @ColumnInfo(name = "mail")
    private String mail;

    @ColumnInfo(name = "parentMobile")
    private String parentMobile;

    @ColumnInfo(name = "college")
    private String college;

    @ColumnInfo(name = "university")
    private String university;

    @ColumnInfo(name = "collegeYear")
    private String collegeYear;

    @ColumnInfo(name = "checkIn")
    private String checkIn;

    @ColumnInfo(name = "checkOut")
    private String checkOut;


    public long getResidentID() {
        return residentID;
    }

    public void setResidentID(long residentID) {
        this.residentID = residentID;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getParentMobile() {
        return parentMobile;
    }

    public void setParentMobile(String parentMobile) {
        this.parentMobile = parentMobile;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCollegeYear() {
        return collegeYear;
    }

    public void setCollegeYear(String collegeYear) {
        this.collegeYear = collegeYear;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
