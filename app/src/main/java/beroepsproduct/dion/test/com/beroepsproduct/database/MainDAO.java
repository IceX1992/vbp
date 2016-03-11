package beroepsproduct.dion.test.com.beroepsproduct.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import beroepsproduct.dion.test.com.beroepsproduct.entities.Verzekering;

/**
 * Created by Dion on 3/10/2016.
 */
public class MainDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "verzekeringen_users.db";
    private static final int DATABASE_VERSION = 1;

    //hier zijn 2 tabellen, kiezen we daarvoor en dan foreign key gedoe of maken we gewoon 1 tabel wat veel makkelyker gaat zijn


    private static final String VERZ_TABLE = "Verzekering";
    private static final String VERZ_ID = "verz_id";
    private static final String VERZ_TYPE = "verzekerining_type";
    private static final String VERZ_BEGIN = "begin_datum";
    private static final String VERZ_END = "eind_datum";
    private static final String VERZ_USERNAME = "user_name";


    private static final String USER_TABLE = "User";
    private static final String USER_ID = "user_id";
    private static final String USER_USERNAME = "user_name";
    private static final String USER_PASSWORD = "pw";
    private static final String USER_FIRSTNAME = "voornaam";
    private static final String USER_LASTNAME = "achternaam";

    private static final String SQL_VERZ_TABLE_QUERY = "CREATE TABLE Verzekering (verz_id INTEGER PRIMARY KEY,verzekering_type varchar(20), begin_datum DATE, eind_datum DATE,user_name varchar(20),FOREIGN KEY (user_name) REFERENCES User(user_name)))";


    private static final String SQL_USER_TABLE_QUERY = "CREATE TABLE User(user_id INTEGER PRIMARY KEY,user_name varchar(20) NOT NULL, pw varchar(20) NOT NULL,voornaam varchar(20) NOT NULL, achternaam varchar(20) NOT NULL))";


    public MainDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertDefaultData() {

        ContentValues user = new ContentValues();
        user.put(USER_USERNAME, "ice");
        user.put(USER_PASSWORD, "ice");
        user.put(USER_FIRSTNAME, "Dion");
        user.put(USER_LASTNAME, "Loor");
        insertUser(USER_TABLE, user);

        ContentValues verzekering = new ContentValues();
        verzekering.put(VERZ_TYPE, "Brandverzekering");
        verzekering.put(VERZ_BEGIN, 2012);
        verzekering.put(VERZ_END, 2016);
        verzekering.put(VERZ_USERNAME, "ice");
        insertVerz(VERZ_TABLE, verzekering);
    }

    public long insertVerz(String name, ContentValues verzekering) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(VERZ_TABLE, null, verzekering);
        db.close();
        return rowId;
    }

    public long insertUser(String name, ContentValues user) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(USER_TABLE, null, user);
        db.close();
        return rowId;
    }


    public Verzekering findVerzByUserName(String username) {
        Verzekering verzekering = null;
        SQLiteDatabase db = getReadableDatabase();

        //code van kman
        /*
        String whereClause = String.format("%s = ?", VERZ_USERNAME);
        String [] whereArgs = {username};
        Cursor cursor = null;
        cursor = db.query(VERZ_TABLE, new String[]{"verz_id", "verzekerining_type", "begin_datum", "eind_datum", "user_name"},whereClause,whereArgs,null,null,null);
        */

        String sql = String.format("select * from %s where %s = '%s'", VERZ_TABLE, VERZ_USERNAME, username);
        Cursor cursor = db.rawQuery(sql, null);
        //de cursor gaat door bevindingen en ze worden gezet in een nieuwe Verzekering entiteit die wij willen operoepen erna
        if (cursor.moveToFirst()) {
            verzekering = new Verzekering(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getLong(3), cursor.getString(4));
        }
        db.close();
        return verzekering;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_VERZ_TABLE_QUERY);
        db.execSQL(SQL_USER_TABLE_QUERY);
        insertDefaultData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
