package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class HelpScreen2 extends AppCompatActivity {
    private ProgressBar progressBar;
    private int i =50;
    private Thread mThread;
    private boolean skipTutorial = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = HelpScreen2.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(HelpScreen2.this,R.color.colorPrimary));

        setContentView(R.layout.activity_help_screen2);
        progressBar = findViewById(R.id.progressBar2);
        this.mThread = new Thread(new Runnable(){
            public void run(){
                while(i<100){
                    i++;
                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(skipTutorial){
                    Intent intent = new Intent(HelpScreen2.this, HelpScreen3.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mThread.start();
    }

    //Metodo saltar tutorial
    public void skipTutorial(View view){
        skipTutorial=false;
        Intent siguiente = new Intent(HelpScreen2.this,LoginScreen.class);
        startActivity(siguiente);
        finish();
    }
}
