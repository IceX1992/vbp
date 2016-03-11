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
import android.widget.Toast;

public class LoginScherm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_scherm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onClickLogin(View view){
        EditText username =(EditText) findViewById(R.id.username);
        String usernameValue = String.valueOf(username.getText());

        EditText password = (EditText) findViewById(R.id.password);
        String passwordValue = String.valueOf(password.getText());

        String notification = "";

        //change to !usernameValue.isEmpty() && !passwordValue.isEmpty()
        if (usernameValue.equals("dion") && passwordValue.equals("dion")){
        //db authentication
            onClick(usernameValue);
            notification = "Welkom " + usernameValue;
        }else{
            notification = "Foutieve inlog gegevens, probeert U het nog een keer";
        }
        Toast.makeText(this,notification,Toast.LENGTH_SHORT).show();
    }

    public void onClick(String username){
        Intent intent = new Intent(this,Dashboard.class);
        String welcomeMessage = "Welcome " + username;
        intent.putExtra("message", welcomeMessage);
        startActivity(intent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_scherm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
