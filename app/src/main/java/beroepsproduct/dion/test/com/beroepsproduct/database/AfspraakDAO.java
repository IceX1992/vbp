package beroepsproduct.dion.test.com.beroepsproduct.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import beroepsproduct.dion.test.com.beroepsproduct.entities.Afspraak;

/**
 * Created by Dion on 3/12/2016.
 */
public class AfspraakDAO extends SQLiteOpenHelper {

    public static final String AFS_TABLE = "afspraak";
    public static final String AFS_ID = "afs_id";
    public static final String AFS_DATUM = "datum";
    public static final String AFS_USERNAME = "username";
    private static final String DATABASE_NAME = "afspraken.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_AFS_TABLE_QUERY = ("CREATE TABLE afspraak (afs_id INTEGER PRIMARY KEY, datum TEXT , username TEXT UNIQUE);");


    public AfspraakDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        insertDefault();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_AFS_TABLE_QUERY);
    }

    public void insertDefault() {
        ContentValues afspraak1 = new ContentValues();
        afspraak1.put(AFS_DATUM, "2015-3-18");
        afspraak1.put(AFS_USERNAME, "ice");
        insertAfs(AFS_TABLE, afspraak1);

        ContentValues afspraak2 = new ContentValues();
        afspraak2.put(AFS_DATUM, "2015-3-18");
        afspraak2.put(AFS_USERNAME, "ice");
        insertAfs(AFS_TABLE, afspraak2);

        ContentValues afspraak3 = new ContentValues();
        afspraak3.put(AFS_DATUM, "2015-3-18");
        afspraak3.put(AFS_USERNAME, "ice");
        insertAfs(AFS_TABLE, afspraak3);

        ContentValues afspraak4 = new ContentValues();
        afspraak4.put(AFS_DATUM, "2015-3-18");
        afspraak4.put(AFS_USERNAME, "ice");
        insertAfs(AFS_TABLE, afspraak4);

        ContentValues afspraak5 = new ContentValues();
        afspraak5.put(AFS_DATUM, "2015-3-18");
        afspraak5.put(AFS_USERNAME, "ice");
        insertAfs(AFS_TABLE, afspraak5);
    }

    public long insertAfs(String name, ContentValues verzekering) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(AFS_TABLE, null, verzekering);
        db.close();
        return rowId;
    }

    public Afspraak findAfs(String username) {
        Afspraak test = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s';", AFS_TABLE, AFS_USERNAME, username);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            test = new Afspraak(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return test;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
