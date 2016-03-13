package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import beroepsproduct.dion.test.com.beroepsproduct.database.AfspraakDAO;
import beroepsproduct.dion.test.com.beroepsproduct.entities.Afspraak;

import static android.widget.Toast.LENGTH_LONG;

public class AfspraakMaken extends AppCompatActivity {

    CalendarView afspraak;
    String testOutput = "";
    String notification = "";
    private AfspraakDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraak_maken);

        final Bundle username = getIntent().getExtras();
        String message = "";
        message = username.getString("username");
        Toast.makeText(this, message, LENGTH_LONG).show();


        db = new AfspraakDAO(this);
        searchAfspraak(message);

        afspraak = (CalendarView) findViewById(R.id.calendarView);

        //sets the listener to be notified upon selected date change.
        afspraak.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month  , int dayOfMonth) {
                String datum = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth);
                final String message = username.getString("username");
                //  afspraakMaken(message, datum);
                Toast.makeText(getApplicationContext(), datum, LENGTH_LONG).show();
                //  Toast.makeText(getApplicationContext(), dayOfMonth + "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void searchAfspraak(final String username) {


        Button invoer = (Button) findViewById(R.id.button3);
        invoer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Afspraak test = null;
                test = db.findAfs(username);

                if (test != null) {
                    testOutput = String.format("Afspraak op %s", Afspraak.getDatum());


                } else {
                    testOutput = "Geen afspraak";

                }
                Toast.makeText(v.getContext(), testOutput, Toast.LENGTH_LONG).show();

            }
        });

 /*       if (test != null){
            testOutput = String.format("Afspraak op %s",test.getDatum());


        }else{
            testOutput = "Geen afspraak";

        }
        Toast.makeText(this,testOutput,Toast.LENGTH_LONG).show();
*/
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

    public void afspraakMaken(final String username, final String datum) {
        ImageButton afspraak = (ImageButton) findViewById(R.id.Imagebutton);


        //insert afspraak wordt gedaan als afspraakMaken wordt opgeroepen in de onSelectedDayChange (line 44) op line 47


        afspraak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues afspraak = new ContentValues();
                afspraak.put(AfspraakDAO.AFS_USERNAME, username);
                afspraak.put(AfspraakDAO.AFS_DATUM, datum);
                long recordId = db.insertAfs(username, afspraak);
                notification = recordId > 0 ? "Data inserted" : "No data inserted";
                Toast.makeText(v.getContext(), notification, Toast.LENGTH_LONG).show();
            }
        });

  /*      final String extra = username;
        b2db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Dashboard.class);
                intent.putExtra("username", extra);
                startActivity(intent);
            }
        });
*/
    }
}
