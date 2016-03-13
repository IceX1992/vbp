package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import beroepsproduct.dion.test.com.beroepsproduct.database.MainDAO;
import beroepsproduct.dion.test.com.beroepsproduct.entities.Verzekering;

public class VerzekeringOverzicht extends AppCompatActivity {

    private MainDAO db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verzekering_overzicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle username = getIntent().getExtras();
        String message = "";
        message = username.getString("username");

        afspraakMaken(message);
        back2DB(message);

        db = new MainDAO(this);

        showVerzNaam(message);
        showverzVerval(message);
        showStart(message);
    }

    public void showVerzNaam(String username) {
        Verzekering verz1 = null;
        TextView naam = (TextView) findViewById(R.id.SoortVerzekering);

        verz1 = db.showVerz(username);

        if (verz1 != null) {
            String output = String.format("Type verzekering: %s", Verzekering.getVerzekering_type());
            naam.setText(output);
        } else {
            String output = "Geen verzekering";
            naam.setText(output);
        }
    }

    public void showverzVerval(String username) {
        Verzekering verz1 = null;
        TextView datumVerval = (TextView) findViewById(R.id.VervaldatumVerzekering);
        verz1 = db.showVerz(username);

        if (verz1 != null) {
            String output = String.format("Vervaldatum: %s", Verzekering.getEind_datum());
            datumVerval.setText(output);
        } else {
            String output = "Geen verzekering";
            datumVerval.setText(output);
        }
    }

    public void showStart(String username) {
        Verzekering verz1 = null;
        TextView datumStart = (TextView) findViewById(R.id.StartVerz);
        verz1 = db.showVerz(username);

        if (verz1 != null) {
            String output = String.format("Begindatum: %s", Verzekering.getBegin_datum());
            datumStart.setText(output);
        } else {
            String output = "Geen verzekering";
            datumStart.setText(output);
        }
    }

    public void afspraakMaken(String username) {
        ImageButton b2db = (ImageButton) findViewById(R.id.imageButton);

        final String extra = username;
        b2db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AfspraakMaken.class);
                intent.putExtra("username", extra);
                startActivity(intent);
            }
        });
    }

    public void back2DB(String username) {
        ImageButton b2db = (ImageButton) findViewById(R.id.imageButton2);

        final String extra = username;
        b2db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Dashboard.class);
                intent.putExtra("username", extra);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logoutUser) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logoutUser() {
        Intent intent = new Intent(this, LoginScherm.class);
        startActivity(intent);
    }
}
