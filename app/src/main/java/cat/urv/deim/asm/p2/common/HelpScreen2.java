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
    private int i =50;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Intent intent = new Intent(HelpScreen2.this, HelpScreen3.class);
                startActivity(intent);
            }
        });
        mThread.start();
    }

    //Metodo saltar tutorial
    public void siguiente(View view){
        Intent siguiente = new Intent(HelpScreen2.this,LoginScreen.class);
        mThread.stop();
        startActivity(siguiente);
    }
}
