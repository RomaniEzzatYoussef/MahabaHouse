package com.romani.mahabahouse.models;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {Resident.class , User.class} , version =  1)
public abstract class MDB extends RoomDatabase
{
    public abstract UserDao getUserDao();

    public abstract ResidentDao getResidentDao();


    private final static String DB_NAME = "MHDB.db";

    private static MDB instance;

    public static synchronized MDB getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),MDB.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

/*
    public static MDB getInstance(Context context)
    {
        File dbFile = context.getDatabasePath(DB_NAME);

        if(!dbFile.exists())
        {
            copyDatabaseFile(context , "/data/data/com.romani.mahabahouse/databases/MHDB.db");
        }

        return Room.databaseBuilder(context.getApplicationContext(),MDB.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    private static void copyDatabaseFile(Context context, String destinationPath)
    {
        InputStream inputStream = null;
        OutputStream output = null;
        byte[] buffer = new byte[8192];
        int length = 0;



        try {
            inputStream = context.getAssets().open("databases/" + DB_NAME);
            output = new FileOutputStream(destinationPath);

            while (true)
            {

                if (!((length = inputStream.read(buffer, 0, 8192)) > 0))
                    break;

                output.write(buffer, 0, length);
            }

        } catch (IOException e)

        {
            e.printStackTrace();
        }

    }


 */

}

