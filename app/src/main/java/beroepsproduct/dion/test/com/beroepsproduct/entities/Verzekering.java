package beroepsproduct.dion.test.com.beroepsproduct.entities;

/**
 * Created by Dion on 3/10/2016.
 */
public class Verzekering {

/*
    private static final String VERZ_TABLE = "verzekering";
    private static final String VERZ_ID = "verz_id";
    private static final String VERZ_TYPE = "verzekering_type";
    private static final String VERZ_BEGIN = "begin_datum";
    private static final String VERZ_END = "eind_datum";
    private static final String VERZ_USERNAME = "user_name";
 */

    private static String verzekering_type;
    private static String begin_datum;
    private static String eind_datum;
    private static String user_name;
    private long id;

    public Verzekering(long id, String verzekering_type, String begin_datum, String eind_datum, String user_name) {
        this.id = id;
        Verzekering.verzekering_type = verzekering_type;
        Verzekering.begin_datum = begin_datum;
        Verzekering.eind_datum = eind_datum;
        Verzekering.user_name = user_name;
    }

    public static String getVerzekering_type() {
        return verzekering_type;
    }

    public static void setVerzekering_type(String verzekering_type) {
        Verzekering.verzekering_type = verzekering_type;
    }

    public static String getBegin_datum() {
        return begin_datum;
    }

    public static void setBegin_datum(String begin_datum) {
        Verzekering.begin_datum = begin_datum;
    }

    public static String getEind_datum() {
        return eind_datum;
    }

    public static void setEind_datum(String eind_datum) {
        Verzekering.eind_datum = eind_datum;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        Verzekering.user_name = user_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}