package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import beroepsproduct.dion.test.com.beroepsproduct.database.MainDAO;

public class CreateNew extends AppCompatActivity {

    private MainDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new MainDAO(this);


    }

    public void createUser(View view) {

        //aantal edittexts en een spinner om data te krijgen van de gebruiker om een nieuwe account
        //plus verzekering in te voeren.
        EditText username = (EditText) findViewById(R.id.username);
        String usernameValue = String.valueOf(username.getText());

        EditText password = (EditText) findViewById(R.id.password);
        String passwordValue = String.valueOf(password.getText());

        EditText voornaam = (EditText) findViewById(R.id.voornaam);
        String voornaamValue = String.valueOf(voornaam.getText());

        EditText achternaam = (EditText) findViewById(R.id.achternaam);
        String achternaamValue = String.valueOf(achternaam.getText());

        Spinner verzekeringType = (Spinner) findViewById(R.id.verzekeringType);
        String verzekeringTypeValue = String.valueOf(verzekeringType.getSelectedItem());

        EditText beginDatum = (EditText) findViewById(R.id.beginDate);
        String beginDatumValue = String.valueOf(beginDatum.getText());

        EditText endDatum = (EditText) findViewById(R.id.endDate);
        String endDatumValue = String.valueOf(endDatum.getText());

        String notificationUser = "";
        String notificationVerz = "";
        String notification = "";

        if (!usernameValue.isEmpty() &&
                !passwordValue.isEmpty() &&
                !voornaamValue.isEmpty() &&
                !achternaamValue.isEmpty() &&
                !verzekeringTypeValue.isEmpty() &&
                !beginDatumValue.isEmpty() &&
                !endDatumValue.isEmpty()) {

            //de userdata wordt insert in de db
            ContentValues user = new ContentValues();
            user.put(MainDAO.USER_USERNAME, usernameValue);
            user.put(MainDAO.USER_PASSWORD, passwordValue);
            user.put(MainDAO.USER_FIRSTNAME, voornaamValue);
            user.put(MainDAO.USER_LASTNAME, achternaamValue);
            long recordId = db.insertUser(usernameValue, user);
            notificationUser = recordId > 0 ? "User" : "Geen user ";

            //de verzekerings data wordt opgeslagen in de db
            ContentValues verz = new ContentValues();
            verz.put(MainDAO.VERZ_TYPE, verzekeringTypeValue);
            verz.put(MainDAO.VERZ_BEGIN, beginDatumValue);
            verz.put(MainDAO.VERZ_END, endDatumValue);
            verz.put(MainDAO.VERZ_USERNAME, usernameValue);
            long recordId2 = db.insertVerz(usernameValue, verz);
            notificationVerz = recordId2 > 0 ? "Verzekering " : "geen verzekering ";

            notification = notificationUser + " " + " en " + notificationVerz + " inserted";

            Toast.makeText(this, notification, Toast.LENGTH_LONG).show();

        } else {
            notification = "U heeft een veld open gelaten, probeert U het nog een keer";
            Toast.makeText(this, notification, Toast.LENGTH_LONG).show();
        }
    }

    public void go2Login(View view) {
        Intent intent = new Intent(this, LoginScherm.class);
        startActivity(intent);
    }
}
