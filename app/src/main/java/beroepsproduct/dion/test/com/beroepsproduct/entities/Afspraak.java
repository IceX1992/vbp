package beroepsproduct.dion.test.com.beroepsproduct.entities;

/**
 * Created by Dion on 3/12/2016.
 */
public class Afspraak {

    private static String datum;
    private static String username;
    private static long afs_id;

    public Afspraak(long afs_id, String datum, String username) {
        Afspraak.afs_id = afs_id;
        Afspraak.datum = datum;
        Afspraak.username = username;
    }

    public static String getDatum() {
        return datum;
    }

    public static void setDatum(String datum) {
        Afspraak.datum = datum;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Afspraak.username = username;
    }

    public static long getAfs_id() {
        return afs_id;
    }

    public static void setAfs_id(long afs_id) {
        Afspraak.afs_id = afs_id;
    }
}
