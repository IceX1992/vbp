package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class VerzekeringOverzicht extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verzekering_overzicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    public void afspraakMaken(View view) {
        onClickAfspraak();
    }


    public void back2DB(View view) {
        onClickDashboard();
    }


    public void onClickDashboard() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }


    public void onClickAfspraak() {
        Intent intent = new Intent(this, AfspraakMaken.class);
        startActivity(intent);
    }
}
