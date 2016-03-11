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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeCalender();

    }


    public void initializeCalender() {
        afspraak = (CalendarView) findViewById(R.id.calendarView);

        //sets whether to show the week number
        afspraak.setShowWeekNumber(false);

        //sets the first day of the week according to Calendar
        //here we set Monday as the first day of the Calendar
        afspraak.setFirstDayOfWeek(2);

        //the backgroundcolor for the selected week
        afspraak.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));

        //sets the color for the dates of an unfocused month
        afspraak.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));

        //sets the color for the separator line between weeks.
        afspraak.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        afspraak.setSelectedDateVerticalBar(R.color.darkgreen);

        //sets the listener to be notified upon selected date change.
        afspraak.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void back2DB2(View view) {
        intentDB();
    }

    public void intentDB() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);

    }
}
