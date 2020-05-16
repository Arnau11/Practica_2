package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

public class FAQSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Window window = FAQSActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(FAQSActivity.this, R.color.colorPrimaryDark));

        setContentView(R.layout.activity_faqs);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.FAQS_title);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




    }

    public boolean onOptionsItemSelected(MenuItem item){
        final Boolean isAnonymous = getIntent().getExtras().getBoolean("isAnonymous");
        Intent intent = new Intent(FAQSActivity.this, MainActivity.class);

        if (isAnonymous)
            intent.putExtra("isAnonymous", true);
        else
            intent.putExtra("isAnonymous", false);

        startActivityForResult(intent, 0);
        return true;
    }
}