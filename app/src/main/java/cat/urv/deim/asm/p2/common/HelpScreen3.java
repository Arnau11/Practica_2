package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class HelpScreen3 extends AppCompatActivity {
    private ProgressBar progressBar;
    private int i =66;
    private Thread mThread;
    private boolean skipTutorial = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen3);
        progressBar = findViewById(R.id.progressBar3);
        this.mThread = new Thread(new Runnable(){
            public void run(){
                while(i<100){
                    i++;
                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(88);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(skipTutorial){
                    Intent intent = new Intent(HelpScreen3.this, LoginScreen.class);
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
        Intent siguiente = new Intent(HelpScreen3.this,LoginScreen.class);
        startActivity(siguiente);
        finish();
    }
}
