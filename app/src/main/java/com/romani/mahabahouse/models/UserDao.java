package com.romani.mahabahouse.models;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao
{
    @Insert
    public void insert(User user);

    @Update
    public void update(User user);

    @Insert
    public void insert(ArrayList<User> users);

    @Query("SELECT * FROM users")
    public List<User> getAllUsers();

    @Query("SELECT userName FROM users")
    public List<String> getAllUsersUsername();

    @Query("SELECT password FROM users")
    public List<String> getAllUsersPassword();

    @Query("SELECT * FROM users where userName like '%'||:bName||'%'")
    public List<User> getUserByName(String bName);

    @Query("SELECT * FROM users where userID = :BID")
    public User getUserByID(String BID);

    @Query("SELECT count(userID) FROM users")
    public int getSizeOFUsers();

    @Delete
    public void delete(ArrayList<User> users);

    @Delete
    public void delete(User user);

}
