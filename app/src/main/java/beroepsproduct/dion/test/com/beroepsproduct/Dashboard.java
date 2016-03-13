package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();


        //ARRAY NIET WERKEND
/*
        ArrayList<Verzekering> verzekeringen = db.showMultiVerz(message);
        if (verzekeringen !=null){
            ListView listView = (ListView) findViewById(R.id.listView);
            ListIterator<Verzekering> iterator1 = verzekeringen.listIterator();

            ArrayList<String> helperList = new ArrayList<>();
            ArrayList<Verzekering> uniqueVerz = new ArrayList<>();

            for (Verzekering verzekering : verzekeringen){
                if (!helperList.contains(verzekering.toString())){
                    helperList.add(verzekering.toString());
                    uniqueVerz.add(verzekering);
                }
            }

            ArrayAdapter<Verzekering> adapter = new ArrayAdapter<Verzekering>(this, android.R.layout.simple_selectable_list_item, uniqueVerz);

            listView.setAdapter(adapter);

        }

*/
        //text met verzekeringen
        getVerz(message);
        //stuur de username naar de methode sendMessageAfspraakMaken, daar gebruikt omt het in die activity te krijgen
        sendMessageAfspraakMaken(message);
        //stuur de username naar de methode sendMessageOverzicht, daar gebruikt om het in die activity te krijgen
        sendMessageOverzicht(message);


    }

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

    public void sendMessageAfspraakMaken(String username) {
        //afspraak maken intent
        ImageButton test = (ImageButton) findViewById(R.id.button);

        final String extra = username;
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AfspraakMaken.class);
                intent.putExtra("username", extra);
                startActivity(intent);
            }
        });
    }

    public void sendMessageOverzicht(String username) {
        //verz overzicht intent
        ImageButton test = (ImageButton) findViewById(R.id.button2);

        final String extra = username;
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VerzekeringOverzicht.class);
                intent.putExtra("username", extra);

                startActivity(intent);
            }
        });
    }
}
