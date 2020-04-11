package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class HelpScreen1 extends AppCompatActivity {
    private ProgressBar progressBar;
    private int i =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen1);
        progressBar = findViewById(R.id.progressBar);
        new Thread(new Runnable(){
            public void run(){
                while(i<100){
                    i++;
                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(HelpScreen1.this, HelpScreen2.class);
                startActivity(intent);
            }
        }).start();
    }

    //Metodo saltar tutorial
    public void siguiente(View view){
        Intent siguiente = new Intent(HelpScreen1.this,LoginScreen.class);
        startActivity(siguiente);
    }
}
