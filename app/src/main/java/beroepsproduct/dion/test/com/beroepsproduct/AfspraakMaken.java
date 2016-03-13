package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import beroepsproduct.dion.test.com.beroepsproduct.database.AfspraakDAO;
import beroepsproduct.dion.test.com.beroepsproduct.entities.Afspraak;

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

        db = new AfspraakDAO(this);

        searchAfspraak(message);

        afspraak = (CalendarView) findViewById(R.id.calendarView);
        //sets the listener to be notified upon selected date change.
        afspraak.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month  , int dayOfMonth) {
                //de datum waarop geklikt wordt, wordt opgeslagen in een string genaamd datum
                String datum = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth);
                //message wordt final gemaakt zodat het gebruikt kan worden in afspraakmaken
                final String message = username.getString("username");
                //de username en de datum worden gebruikt als parameters bij de onclick voor
                //een afspraak maken
                afspraakMaken(message, datum);
                Toast.makeText(getApplicationContext(), datum, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void searchAfspraak(final String username) {
        ImageButton invoer = (ImageButton) findViewById(R.id.button3);
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
                notification = recordId > 0 ? "Afspraak gemaakt" : "Geen afspraak gemaakt";
                Toast.makeText(v.getContext(), notification, Toast.LENGTH_LONG).show();
            }
        });
    }
}
