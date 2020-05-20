package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

public class EventsDetailActivity extends AppCompatActivity {

    private List<EventList> eventsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Window window = EventsDetailActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(EventsDetailActivity.this,R.color.colorPrimaryDark));

        setContentView(R.layout.activity_events_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("Position");
        }

        DataProvider dataProvider = DataProvider.getInstance(this.getApplicationContext());
        List<Event> event = dataProvider.getEvents();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.event_detail_title);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView shortDesc = this.findViewById(R.id.text);
        shortDesc.setText(event.get(position).getDescription());

        ImageView imageView = this.findViewById(R.id.imageView);
        Picasso.with(this).load(event.get(position).getImageURL()).into(imageView);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(EventsDetailActivity.this, MainActivity.class);
        intent.putExtra("isAnonymous", false);
        startActivityForResult(intent, 0);
        return true;
    }
}
