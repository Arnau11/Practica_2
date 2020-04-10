package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginScreen extends AppCompatActivity {
    public final static String USERS_NAME = "";
    public final static String USERS_PASSWORD = "";
    public final static boolean isAnonymous = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }
}
