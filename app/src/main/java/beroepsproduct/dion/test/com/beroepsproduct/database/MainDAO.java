package beroepsproduct.dion.test.com.beroepsproduct.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dion on 3/10/2016.
 */
public class MainDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "verzekeringen_users.db";
    private static final int DATABASE_VERSION = 1;

    //hier zijn 2 tabellen, kiezen we daarvoor en dan foreign key gedoe of maken we gewoon 1 tabel wat veel makkelyker gaat zijn


    private static final String VERZ_TABLE = "Verzekering";
    private static final String VERZ_TYPE = "Verzekerinings type";
    private static final String VERZ_BEGIN = "Begin datum";
    private static final String VERZ_END = "Eind datum";
    private static final String VOORNAAM = "Voornaam";


    private static final String USER_TABLE = "User";
    private static final String USER_USERNAME = "Username";
    private static final String USER_PASSWORD = "Password";
    private static final String USER_FIRSTNAME = "Voornaam";
    private static final String USER_LASTNAME = "Achternaam";

    private static final String SQL_VERZ_TABLE_QUERY = "create table Verzekering (id INTEGER PRIMARY KEY, Verzekerings type STRING NOT NULL, Begin Datum DATE NOT NULL, Eind datum DATE NOT NULL, FOREIGN KEY (Achternaam) REFERENCES User(Achternaam))";

    private static final String SQL_USER_TABLE_QUERY = "create table User(id INTEGER PRIMARY KEY, Username STRING NOT NULL, Password STRING NOT NULL, Voornaam STRING NOT NULL, Achternaam STRING NOT NULL)";

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
        verzekering.put(VERZ_BEGIN, 2012 - 15 - 11);
        verzekering.put(VERZ_END, 2016 - 15 - 11);
        verzekering.put(VOORNAAM, "Dion");
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