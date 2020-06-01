package cat.urv.deim.asm.p2.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import cat.urv.deim.asm.p3.shared.EventsFragment;
import cat.urv.deim.asm.p3.shared.FAQSActivity;
import cat.urv.deim.asm.p3.shared.Global;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    static boolean fragmentTransaction = false;
    static Fragment fragment = null;
    static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean isAnonymous = prefs.getBoolean(Global.IS_ANONYMOUS, true);


        if (!isAnonymous) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_anonymous);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // The communication between fragments it is done via xml (mobile_navigation.xml)
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

                        fragmentTransaction = false;
                        fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.nav_news:
                                fragmentTransaction = true;
                                fragment = new NewsFragment();
                                index = 0;
                                break;
                            case R.id.nav_articles:
                                fragmentTransaction = true;
                                fragment = new ArticlesFragment();
                                index = 1;
                                break;
                            case R.id.nav_events:
                                fragmentTransaction = true;
                                fragment = new EventsFragment();
                                index = 2;
                                break;
                            case R.id.nav_calendar:
                                fragmentTransaction = true;
                                fragment = new CalendarFragment();
                                index = 3;
                                break;
                            case R.id.nav_profile:

                                if (!isAnonymous) {
                                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                                    intent.putExtra(Global.FROM_MENU, true);
                                    startActivity(intent);
                                }

                                break;
                            case R.id.nav_faqs:

                                Intent intent = new Intent(MainActivity.this, FAQSActivity.class);
                                startActivity(intent);
                                break;

                            default:
                                fragmentTransaction = true;
                                fragment = new NewsFragment();
                                index = 0;
                                break;
                        }

                        if (fragmentTransaction) {

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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(index).setChecked(true);

        switch (index) {
            case 0:
                fragmentTransaction = true;
                fragment = new NewsFragment();
                break;
            case 1:
                fragmentTransaction = true;
                fragment = new ArticlesFragment();
                break;
            case 2:
                fragmentTransaction = true;
                fragment = new EventsFragment();
                break;
            case 3:
                fragmentTransaction = true;
                fragment = new CalendarFragment();
                break;
        }

        if (fragmentTransaction) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();

            getSupportActionBar().setTitle(navigationView.getMenu().getItem(index).getTitle());

        }

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