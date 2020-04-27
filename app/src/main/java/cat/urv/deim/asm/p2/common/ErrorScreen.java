package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ErrorScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = ErrorScreen.this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(ErrorScreen.this,R.color.colorGray));


        setContentView(R.layout.activity_error_screen);
        final Intent intentIn = this.getIntent();
        Button tryAgainButton = (Button) findViewById(R.id.tryAgainButton);
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ErrorScreen.this, LoginScreen.class);
                intent.putExtras(intentIn);
                startActivity(intent);
                finish();
            }
        });
    }
}
