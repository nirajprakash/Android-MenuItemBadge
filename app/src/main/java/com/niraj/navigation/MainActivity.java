package com.niraj.navigation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    //TextView textView;

    private FrameLayout alertCircle;
    private TextView alertCountTextView;
    private int alertIconCount = 0;
    private int navInboxCount = 0;
    private TextView inboxCountTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if (navigationView != null) {

            View inboxView = navigationView.getMenu().findItem(R.id.item_navigation_drawer_inbox).getActionView();
            initNavigationInboxBadge(inboxView);
            setupNavigationDrawerContent(navigationView);
        }


        setupNavigationDrawerContent(navigationView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_main_alert_item:
                alertIconCount = 0;
                alertCircle.setVisibility((alertIconCount > 0) ? View.VISIBLE : View.GONE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem alertMenuItem = menu.findItem(R.id.menu_main_alert_item);

        initAlertBadge(alertMenuItem);


        return super.onPrepareOptionsMenu(menu);
    }

    public void initNavigationInboxBadge(View view){
        inboxCountTextView = (TextView) view.findViewById(R.id.textview_inbox_count);
    }

    public void initAlertBadge(final MenuItem menuItem){
        FrameLayout view = (FrameLayout) menuItem.getActionView();
        alertCircle = (FrameLayout) view.findViewById(R.id.alert_circle);
        alertCountTextView = (TextView) view.findViewById(R.id.textview_alert_count);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

    }



    public void updateAlertCount(View view) {
        // if alert count extends into two digits, just show the red circle
        alertIconCount = (alertIconCount + 1) % 20; // cycle through 0 - 10
        if (0 < alertIconCount) {
            alertCountTextView.setText(String.valueOf(alertIconCount));
        } else {
            alertCountTextView.setText("");
        }

        alertCircle.setVisibility((alertIconCount > 0) ? View.VISIBLE : View.GONE);
    }

    public void updateInboxCount(View view){
        navInboxCount = (navInboxCount + 1) % 20; // cycle through 0 - 10
        if (0 < navInboxCount) {
            inboxCountTextView.setText(String.valueOf(navInboxCount)+ " new");
        } else {
            inboxCountTextView.setText("");
        }

        inboxCountTextView.setVisibility((navInboxCount > 0) ? View.VISIBLE : View.GONE);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //textView = (TextView) findViewById(R.id.textView);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_inbox:
                                menuItem.setChecked(true);
                                //textView.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_starred:
                                menuItem.setChecked(true);
                                //textView.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_sent_mail:
                                menuItem.setChecked(true);
                                //textView.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_drafts:
                                menuItem.setChecked(true);
                                //textView.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);
                                //textView.setText(menuItem.getTitle());
                                Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                //Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                //startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_help_and_feedback:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }
}
