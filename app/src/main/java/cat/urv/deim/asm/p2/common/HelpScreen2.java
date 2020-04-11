package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class HelpScreen2 extends AppCompatActivity {
    private ProgressBar progressBar;
    private int i =30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen2);
        progresoBarra();
        Intent intent = new Intent(HelpScreen2.this, HelpScreen3.class);
        startActivity(intent);
    }

    //Progreso de la barra
    public void progresoBarra(){
        progressBar = findViewById(R.id.progressBar2);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask(){
            @Override
            public void run() {
                i++;
                progressBar.setProgress(i);
                if(i==100){
                    t.cancel();
                }
            }
        };
        t.schedule(tt, 0, 100);
    }

    //Metodo saltar tutorial
    public void siguiente(View view){
        Intent siguiente = new Intent(HelpScreen2.this,LoginScreen.class);
        startActivity(siguiente);
    }
}
