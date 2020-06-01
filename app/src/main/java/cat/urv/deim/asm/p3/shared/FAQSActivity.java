package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Faq;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

public class FAQSActivity extends AppCompatActivity {

    ExpandableListViewAdapter listViewAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    ExpandableListView expandableListView;
    List<String> questionList;
    HashMap<String, List<String>> answerList;

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

        expandableListView = findViewById(R.id.eListView);
        showList();

        listViewAdapter = new ExpandableListViewAdapter(this, questionList, answerList);
        expandableListView.setAdapter(listViewAdapter);


    }

    private void showList() {
        questionList = new ArrayList<String>();
        answerList = new HashMap<String, List<String>>();

        try{
            DataProvider dataProvider = DataProvider.getInstance(this.getApplicationContext());
            List<Faq> faqs = dataProvider.getFaqs();
            int i = 0;
            while(i < faqs.size()) {
                String title = faqs.get(i).getTitle();
                String body = faqs.get(i).getBody();
                questionList.add(title);
                List<String> topic1 = new ArrayList<>();
                topic1.add(body);
                answerList.put(questionList.get(i), topic1);
                i++;
            }
        }
        catch (NullPointerException exception){
            Log.e(TAG,Global.ERROR_MESSAGE);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(FAQSActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }
}