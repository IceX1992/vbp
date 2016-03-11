package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import beroepsproduct.dion.test.com.beroepsproduct.database.MainDAO;
import beroepsproduct.dion.test.com.beroepsproduct.entities.Verzekering;

public class Dashboard extends AppCompatActivity {

    //entity verzekering wordt opgeroepen
    Verzekering verzekering = null;
    //db wordt opgeroepen
    private MainDAO db;

    /*
        private void onArrive() {
            Intent intent = getIntent();
            String message = intent.getStringExtra("message");
            TextView textView = (TextView) findViewById(R.id.welcomeMessage);
            textView.setText(message);
        }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //db wordt gemaakt
        db = new MainDAO(this);
        // onArrive();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    public void findVerz(View view) {
        EditText username = (EditText) findViewById(R.id.editText);
        String usernameValue = String.valueOf(username.getText());

        //findVerzByUserName wordt opgeroepen en de gevonden dingen worden gezet in een String
        db.findVerzByUserName(usernameValue);
        String output = String.format("Verzekering type : %s \n Verz begint op : %s \n Verz eindigt op: %s \n", verzekering.getVerzType(), verzekering.getVerzBegin(), verzekering.getVerzEnd());

        //output wordt gezet in de multiline
        EditText outputView = (EditText) findViewById(R.id.editText2);
        outputView.setText(output);


    }

    public void onClick(View view) {
        onClickVerzOverz();
    }

    public void onClickVerzOverz() {
        Intent intent = new Intent(this, VerzekeringOverzicht.class);
        startActivity(intent);
    }



}
