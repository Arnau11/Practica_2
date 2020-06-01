package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.Preferences;

import cat.urv.deim.asm.p3.shared.Global;

public class LoginScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = prefs.edit();


        Window window = LoginScreen.this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        setContentView(R.layout.activity_login_screen);

        final Intent intentIn = this.getIntent();

        if (intentIn.getExtras() == null) {
            window.setStatusBarColor(ContextCompat.getColor(LoginScreen.this,R.color.colorGray));
            Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
            anonymousButton.setText(getString(R.string.login_button_A));
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().hide();
        } else {
            window.setStatusBarColor(ContextCompat.getColor(LoginScreen.this,R.color.colorPrimaryDark));
            Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
            anonymousButton.setText(getString(R.string.login_button_C));
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.login_title);
        }


       Button signInButton = (Button) findViewById(R.id.signInButton);
       signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText emailText = (EditText)findViewById(R.id.loginEmail);
                String usersEmail = emailText.getText().toString();
                EditText passwordText = (EditText)findViewById(R.id.loginPassword);
                String usersPassword = passwordText.getText().toString();

                if(isUser(usersEmail, usersPassword)) {
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    intent.putExtra(Global.EMAIL,usersEmail);
                    intent.putExtra(Global.PASSWORD,usersPassword);
                    editor.putBoolean(Global.IS_ANONYMOUS, false);
                    editor.apply();
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginScreen.this, ErrorScreen.class);
                    intent.putExtras(intentIn);
                    startActivity(intent);
                }
            }
       });

        Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                editor.putBoolean(Global.IS_ANONYMOUS, true);
                editor.apply();
                startActivity(intent);
            }
        });

    }

    public boolean isUser(String email, String pass) {
        if(email.equals(Global.CORRECT_EMAIL) && pass.equals(Global.CORRECT_PASSWORD)) {
            return true;
        } else return false;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
        startActivity(intent);
        return true;
    }


}
