package beroepsproduct.dion.test.com.beroepsproduct.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import beroepsproduct.dion.test.com.beroepsproduct.entities.User;
import beroepsproduct.dion.test.com.beroepsproduct.entities.Verzekering;

/**
 * Created by Dion on 3/10/2016.
 */
public class MainDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "verzekeringen_users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String VERZ_TABLE = "verzekering";
    private static final String VERZ_ID = "verz_id";
    private static final String VERZ_TYPE = "verzekering_type";
    private static final String VERZ_BEGIN = "begin_datum";
    private static final String VERZ_END = "eind_datum";
    private static final String VERZ_USERNAME = "user_name";


    private static final String USER_TABLE = "user";
    private static final String USER_USERNAME = "user_name";
    private static final String USER_PASSWORD = "password";
    private static final String USER_FIRSTNAME = "voornaam";
    private static final String USER_LASTNAME = "achternaam";

    private static final String SQL_VERZ_TABLE_QUERY = "CREATE TABLE verzekering (verz_id INTEGER PRIMARY KEY,verzekering_type TEXT, begin_datum TEXT, eind_datum TEXT,user_name TEXT,FOREIGN KEY (user_name) REFERENCES user(user_name));";


    private static final String SQL_USER_TABLE_QUERY = "CREATE TABLE user(user_name TEXT PRIMARY KEY, password TEXT NOT NULL,voornaam TEXT NOT NULL, achternaam TEXT NOT NULL);";


    public MainDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        insertDefaultDataVerzekering();
        insertDefaultDataUser();
    }

    public void insertDefaultDataUser() {
        ContentValues user = new ContentValues();
        user.put(USER_USERNAME, "admin");
        user.put(USER_PASSWORD, "admin");
        user.put(USER_FIRSTNAME, "Neville");
        user.put(USER_LASTNAME, " Kemble");
        insertUser(USER_TABLE, user);

        ContentValues user1 = new ContentValues();
        user1.put(USER_USERNAME, "Ice");
        user1.put(USER_PASSWORD, "Ice");
        user1.put(USER_FIRSTNAME, "Dion");
        user1.put(USER_LASTNAME, " Loor");
        insertUser(USER_TABLE, user1);

        ContentValues user2 = new ContentValues();
        user2.put(USER_USERNAME, "Fire");
        user2.put(USER_PASSWORD, "Fire");
        user2.put(USER_FIRSTNAME, "Justin");
        user2.put(USER_LASTNAME, " Lo-Ning-Hing");
        insertUser(USER_TABLE, user2);

        ContentValues user3 = new ContentValues();
        user3.put(USER_USERNAME, "Wind");
        user3.put(USER_PASSWORD, "Wind");
        user3.put(USER_FIRSTNAME, "Kewish");
        user3.put(USER_LASTNAME, " Fagoe");
        insertUser(USER_TABLE, user3);

        ContentValues user4 = new ContentValues();
        user4.put(USER_USERNAME, "user");
        user4.put(USER_PASSWORD, "user");
        user4.put(USER_FIRSTNAME, "Test");
        user4.put(USER_LASTNAME, " Test");
        insertUser(USER_TABLE, user4);

    }

    public void insertDefaultDataVerzekering() {
        ContentValues verz = new ContentValues();
        verz.put(VERZ_TYPE, "Brandverzekering");
        verz.put(VERZ_BEGIN, "2015-11-16");
        verz.put(VERZ_END, "2020-11-16");
        verz.put(VERZ_USERNAME, "Ice");
        insertVerz(VERZ_TABLE, verz);

        ContentValues verz1 = new ContentValues();
        verz1.put(VERZ_TYPE, "Woningverzekering");
        verz1.put(VERZ_BEGIN, "2010-11-16");
        verz1.put(VERZ_END, "2015-11-16");
        verz1.put(VERZ_USERNAME, "Ice");
        insertVerz(VERZ_TABLE, verz1);

        ContentValues verz2 = new ContentValues();
        verz2.put(VERZ_TYPE, "Brandverzekering");
        verz2.put(VERZ_BEGIN, "2012-11-16");
        verz2.put(VERZ_END, "2017-11-16");
        verz2.put(VERZ_USERNAME, "Fire");
        insertVerz(VERZ_TABLE, verz2);

        ContentValues verz3 = new ContentValues();
        verz3.put(VERZ_TYPE, "Inboedelverzekering");
        verz3.put(VERZ_BEGIN, "2014-11-16");
        verz3.put(VERZ_END, "2029-11-16");
        verz3.put(VERZ_USERNAME, "admin");
        insertVerz(VERZ_TABLE, verz3);

        ContentValues verz4 = new ContentValues();
        verz4.put(VERZ_TYPE, "Brandverzekering");
        verz4.put(VERZ_BEGIN, "2015-11-16");
        verz4.put(VERZ_END, "2020-11-16");
        verz4.put(VERZ_USERNAME, "Wind");
        insertVerz(VERZ_TABLE, verz4);

        ContentValues verz5 = new ContentValues();
        verz5.put(VERZ_TYPE, "Brandverzekering");
        verz5.put(VERZ_BEGIN, "2010-11-16");
        verz5.put(VERZ_END, "2015-11-16");
        verz5.put(VERZ_USERNAME, "user");
        insertVerz(VERZ_TABLE, verz5);
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

    public User login(String username, String password) {
        User loginTry = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s' AND %s = '%s';", USER_TABLE, USER_USERNAME, username, USER_PASSWORD, password);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            loginTry = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        db.close();
        return loginTry;
    }

    //multi verz vinden en zetten in array NIET WERKEND
/*
    public ArrayList<Verzekering> showMultiVerz (String username){
        ArrayList<Verzekering> verzekeringen = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s';", VERZ_TABLE, VERZ_USERNAME, username);
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) verzekeringen = new ArrayList<>();
            Verzekering verzekering = null;
            while (!cursor.isAfterLast()){
                verzekering = new Verzekering(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
           //     assert verzekeringen != null;
                verzekeringen.add(verzekering);
            }
            db.close();
            return verzekeringen;
    }
*/
    public Verzekering showVerz(String username) {
        Verzekering num1 = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s';", VERZ_TABLE, VERZ_USERNAME, username);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            num1 = new Verzekering(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        db.close();
        return num1;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_VERZ_TABLE_QUERY);
        db.execSQL(SQL_USER_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
