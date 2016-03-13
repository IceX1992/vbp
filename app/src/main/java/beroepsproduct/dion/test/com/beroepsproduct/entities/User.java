package beroepsproduct.dion.test.com.beroepsproduct.entities;

/**
 * Created by Dion on 3/10/2016.
 */
public class User {

    private static String user_name;
    private static String password;
    private static String voornaam;
    private static String achternaam;

    public User(String user_name, String password, String voornaam, String achternaam) {
        User.user_name = user_name;
        User.password = password;
        User.voornaam = voornaam;
        User.achternaam = achternaam;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        User.user_name = user_name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getVoornaam() {
        return voornaam;
    }

    public static void setVoornaam(String voornaam) {
        User.voornaam = voornaam;
    }

    public static String getAchternaam() {
        return achternaam;
    }

    public static void setAchternaam(String achternaam) {
        User.achternaam = achternaam;
    }

    public boolean comparePassword(String passwordIn) {
        return password.equals(passwordIn);
    }
}
