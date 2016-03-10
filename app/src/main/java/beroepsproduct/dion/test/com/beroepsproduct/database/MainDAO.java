package beroepsproduct.dion.test.com.beroepsproduct.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dion on 3/10/2016.
 */
public class MainDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "verzekeringen_users.db";
    private static final int DATABASE_VERSION = 1;


    public MainDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
