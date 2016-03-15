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
import android.widget.Toast;

import beroepsproduct.dion.test.com.beroepsproduct.database.MainDAO;
import beroepsproduct.dion.test.com.beroepsproduct.entities.Verzekering;

public class Dashboard extends AppCompatActivity {

    private MainDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new MainDAO(this);

        Bundle username = getIntent().getExtras();
        String message = "";
        message = username.getString("username");

        //text met verzekeringen
        getVerz(message);
        //stuur de username naar de methode sendMessageAfspraakMaken, daar gebruikt omt het in die activity te krijgen
        sendMessageAfspraakMaken(message);
        //stuur de username naar de methode sendMessageOverzicht, daar gebruikt om het in die activity te krijgen
        sendMessageOverzicht(message);
    }

    //hiermee wordt de verzekeringtype en einddatum opgehaald
    public void getVerz(String username) {
        Verzekering verz1 = null;
        TextView outputVerz = (TextView) findViewById(R.id.textView);
        verz1 = db.showVerz(username);

        if (verz1 != null) {
            String output = String.format("Uw %s vervalt op %s", Verzekering.getVerzekering_type(), Verzekering.getEind_datum());
            outputVerz.setText(output);
        } else {
            String notification = "Geen verzekering gevonden";
            Toast.makeText(this, notification, Toast.LENGTH_LONG).show();
        }
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

    //username wordt meegestuurd in de onclick naar de afspraakmaken activity
    public void sendMessageAfspraakMaken(String username) {
        //afspraak maken intent
        ImageButton test = (ImageButton) findViewById(R.id.button);

        final String extra = username;
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, AfspraakMaken.class);
                intent.putExtra("username", extra);
                startActivity(intent);
            }
        });
    }

    //username wordt meegestuurd in de onclick naar de verzekering overzicht activity
    public void sendMessageOverzicht(String username) {
        //verz overzicht intent
        ImageButton test = (ImageButton) findViewById(R.id.button2);

        final String extra = username;
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, VerzekeringOverzicht.class);
                intent.putExtra("username", extra);

                startActivity(intent);
            }
        });
    }
}
