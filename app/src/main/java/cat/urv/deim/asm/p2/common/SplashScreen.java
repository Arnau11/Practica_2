package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Obtenemos el valor del flag tutorial
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        final boolean needTutorial= prefs.getBoolean("NEED_TUTORIAL", true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(needTutorial){
                    editor.putBoolean("NEED_TUTORIAL", false);
                    editor.commit();
                    Intent intent = new Intent(SplashScreen.this, HelpScreen1.class);
                    startActivity(intent);
                    finish();
                } else{
                    Intent intent = new Intent(SplashScreen.this, LoginScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);

    }
}
