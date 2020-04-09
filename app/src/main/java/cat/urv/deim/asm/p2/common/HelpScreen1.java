package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class HelpScreen1 extends AppCompatActivity {
    private ProgressBar progressBar;
    private int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen_1);
        progresoBarra();
    }

    //Progreso de la barra
    public void progresoBarra(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
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
}
