package com.romani.mahabahouse.models;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ResidentDao
{
    @Insert
    public void insert(Resident resident);

    @Update
    public void update(Resident resident);

    @Insert
    public void insert(ArrayList<Resident> residents);

    @Query("SELECT * FROM residents")
    public List<Resident> getAllResidents();

    @Query("SELECT * FROM residents where residentName like '%'||:bName||'%'")
    public List<Resident> getResidentByName(String bName);
    @Query("SELECT * FROM residents where room like '%'||:room||'%'")
    public List<Resident> getResidentByRoom(String room);

    @Query("SELECT * FROM residents where residentID = :BID")
    public Resident getResidentByID(String BID);

    @Query("SELECT count(residentID) FROM residents")
    public int getSizeOFResidents();

    @Delete
    public void delete(ArrayList<Resident> residents);

    @Delete
    public void delete(Resident resident);

}
