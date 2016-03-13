package beroepsproduct.dion.test.com.beroepsproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import beroepsproduct.dion.test.com.beroepsproduct.database.MainDAO;
import beroepsproduct.dion.test.com.beroepsproduct.entities.User;

public class LoginScherm extends AppCompatActivity {

    private MainDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_scherm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new MainDAO(this);
    }

    //login wordt hier uitgevoerd
    public void onClickLogin(View view){

        //de username en password worden opgeslagen in een string
        EditText username =(EditText) findViewById(R.id.username);
        String usernameValue = String.valueOf(username.getText());

        EditText password = (EditText) findViewById(R.id.password);
        String passwordValue = String.valueOf(password.getText());

        String notification = "";
        User login = null;

        //de database method waarmee er ingelogd wordt, wordt opgeroepen met als parameters
        //de username en password
        login = db.login(usernameValue, passwordValue);

        //toekomst mischien een encryption voor password
        if (login != null) {
            boolean isPasswordValid = login.comparePassword(passwordValue);
            if (isPasswordValid) {
                Intent intent = new Intent(this, Dashboard.class);
                intent.putExtra("username", User.getUser_name());
                startActivity(intent);
            } else {
                notification = "Foutieve login, probeert U het nogmaals";
                Toast.makeText(this,notification,Toast.LENGTH_SHORT).show();
            }
        }else{
            notification = "Foutieve login probeert U het nogmaals";
            Toast.makeText(this,notification,Toast.LENGTH_SHORT).show();
        }
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

    public void go2Create(View view) {
        Intent intent = new Intent(this, CreateNew.class);
        startActivity(intent);
    }
}
