package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();


        message = String.valueOf(username.getString("username"));

        Verzekering verz1 = null;
        TextView outputVerz = (TextView) findViewById(R.id.textView);
        verz1 = db.showVerz(message);

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


    public void findVerz(View view) {

        Intent intent = new Intent(this, VerzekeringOverzicht.class);
        startActivity(intent);
    }

    public void verzInfo(View view) {
        Intent intent = new Intent(this, AfspraakMaken.class);
        startActivity(intent);
    }
}
