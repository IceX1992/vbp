package beroepsproduct.dion.test.com.beroepsproduct.entities;

/**
 * Created by Dion on 3/10/2016.
 */
public class User {


    private long user_id;
    private String user_name;
    private String pw;
    private String voornaam;
    private String achternaam;

    public User(long user_id, String user_name, String pw, String voornaam, String achternaam) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.pw = pw;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
}
