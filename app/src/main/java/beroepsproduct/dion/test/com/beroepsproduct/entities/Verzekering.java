package beroepsproduct.dion.test.com.beroepsproduct.entities;

/**
 * Created by Dion on 3/10/2016.
 */
public class Verzekering {


    private long verz_id;
    private String verzekering_type;
    private Number begin_datum;
    private Number eind_datum;
    private String user_name;

    public Verzekering(long verz_id, String verzekering_type, Number begin_datum, Number eind_datum, String user_name) {
        this.verz_id = verz_id;
        this.verzekering_type = verzekering_type;
        this.begin_datum = begin_datum;
        this.eind_datum = eind_datum;
        this.user_name = user_name;
    }


    public long getVerz_id() {
        return verz_id;
    }

    public void setVerz_id(long verz_id) {
        this.verz_id = verz_id;
    }

    public String getVerzekering_type() {
        return verzekering_type;
    }

    public void setVerzekering_type(String verzekering_type) {
        this.verzekering_type = verzekering_type;
    }

    public Number getBegin_datum() {
        return begin_datum;
    }

    public void setBegin_datum(Number begin_datum) {
        this.begin_datum = begin_datum;
    }

    public Number getEind_datum() {
        return eind_datum;
    }

    public void setEind_datum(Number eind_datum) {
        this.eind_datum = eind_datum;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}