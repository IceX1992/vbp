package beroepsproduct.dion.test.com.beroepsproduct;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;

public class AfspraakMaken extends AppCompatActivity {

    CalendarView afspraak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraak_maken);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        afspraak = (CalendarView) findViewById(R.id.calendarView);

   /*     afspraak.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(),
                        "Year: " + year + "\n" +
                                "Month: " + month + "\n" +
                                "Day of Month: " + dayOfMonth,
                        Toast.LENGTH_LONG).show();
            }
        });
        */


    }

}
