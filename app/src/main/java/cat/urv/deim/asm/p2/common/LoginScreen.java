package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        setContentView(R.layout.activity_login_screen);

        Intent intent = this.getIntent();

        if (intent.getExtras() == null) {
            Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
            anonymousButton.setText(getString(R.string.login_button_A));
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().hide();
        } else {
            Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
            anonymousButton.setText(getString(R.string.login_button_C));
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Login");
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
                    intent.putExtra("email",usersEmail);
                    intent.putExtra("pass",usersPassword);
                    intent.putExtra("isAnonymous", false);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginScreen.this, ErrorScreen.class);
                    startActivity(intent);
                }
            }
       });

        Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                intent.putExtra("isAnonymous", true);
                startActivity(intent);
            }
        });

    }

    public boolean isUser(String email, String pass) {
        if(email.equals("sandra.adams@email.com") && pass.equals("123456")) {
            return true;
        } else return false;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}
