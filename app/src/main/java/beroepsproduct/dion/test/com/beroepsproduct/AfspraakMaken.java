package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class AfspraakMaken extends AppCompatActivity {

    CalendarView afspraak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraak_maken);




        afspraak = (CalendarView) findViewById(R.id.calendarView);

        //sets the listener to be notified upon selected date change.
        afspraak.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month  , int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void back2DB2(View view) {
        String notification= "Uw afspraak is gemaakt";
        Toast.makeText(this,notification,Toast.LENGTH_SHORT).show();
        intentDB();
    }

    public void intentDB() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);

    }

}
