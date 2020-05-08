package cat.urv.deim.asm.p2.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import cat.urv.deim.asm.p2.common.ui.articles.articlesFragment;
import cat.urv.deim.asm.p2.common.ui.calendar.calendarFragment;
import cat.urv.deim.asm.p2.common.ui.events.eventsFragment;
import cat.urv.deim.asm.p2.common.ui.faqs.faqsFragment;
import cat.urv.deim.asm.p2.common.ui.news.newsFragment;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Boolean isAnonymous = getIntent().getExtras().getBoolean("isAnonymous");

        if(!isAnonymous){
            setContentView(R.layout.activity_main);
        }
        else{
            setContentView(R.layout.activity_anonymous);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // The communication between fragments it is done via xml (mobile_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_news, R.id.nav_articles, R.id.nav_events, R.id.nav_calendar, R.id.nav_profile, R.id.nav_favorites, R.id.nav_bookmarks, R.id.nav_faqs, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Code to go to ProfileActivity or LoginScreen depending on the boolean isAnonymous

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch(menuItem.getItemId()){
                            case R.id.nav_news:
                                fragmentTransaction = true;
                                fragment = new newsFragment();
                                break;
                            case R.id.nav_articles:
                                fragmentTransaction = true;
                                fragment = new articlesFragment();
                                break;
                            case R.id.nav_events:
                                fragmentTransaction = true;
                                fragment = new eventsFragment();
                                break;
                            case R.id.nav_calendar:
                                fragmentTransaction = true;
                                fragment = new calendarFragment();
                                break;
                            case R.id.nav_faqs:
                                fragmentTransaction = true;
                                fragment = new faqsFragment();
                                break;
                            case R.id.nav_profile:

                                if(!isAnonymous){
                                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                                    intent.putExtra("fromMenu", true);
                                    startActivity(intent);
                                }

                                break;
                        }

                        if(fragmentTransaction) {

                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.nav_host_fragment, fragment)
                                    .commit();

                            menuItem.setChecked(false);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawer.closeDrawers();

                        return true;
                    }

                });

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}