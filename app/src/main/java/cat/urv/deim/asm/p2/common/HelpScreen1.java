package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class HelpScreen1 extends AppCompatActivity {
    private ProgressBar progressBar;
    private int i =0;
    private Thread mThread;
    private boolean skipTutorial = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = HelpScreen1.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(HelpScreen1.this,R.color.colorPrimary));

        setContentView(R.layout.activity_help_screen1);
        progressBar = findViewById(R.id.progressBar1);
        this.mThread = new Thread(new Runnable(){
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
                if(skipTutorial){
                    Intent intent = new Intent(HelpScreen1.this, HelpScreen2.class);
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
        Intent siguiente = new Intent(HelpScreen1.this,LoginScreen.class);
        startActivity(siguiente);
        finish();
    }
}