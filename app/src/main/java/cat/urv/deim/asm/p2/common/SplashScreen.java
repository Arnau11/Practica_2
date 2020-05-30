package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import cat.urv.deim.asm.p3.shared.Global;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Obtenemos el valor del flag tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = prefs.edit();
        final boolean needTutorial= prefs.getBoolean(Global.NEED_TUTORIAL, true);
        final boolean isAnonymous = prefs.getBoolean(Global.IS_ANONYMOUS, true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(needTutorial){
                    editor.putBoolean(Global.NEED_TUTORIAL, false);
                    editor.commit();
                    Intent intent = new Intent(SplashScreen.this, HelpScreen1.class);
                    startActivity(intent);
                    finish();
                } else{
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);

    }
}
