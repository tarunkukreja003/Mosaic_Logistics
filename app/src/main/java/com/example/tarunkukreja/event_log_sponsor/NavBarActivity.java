package com.example.tarunkukreja.event_log_sponsor;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class NavBarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView ;

    String url = "http://eventsmosaic.in/App_Assets/" ;
    String png = ".png" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayScreenSelected(R.id.nav_event_setup);


        listView = (ListView)findViewById(R.id.list_view) ;

        ArrayList<CustomClass> list = new ArrayList<>() ;
        list.add(new CustomClass("Event Setup", url + "event_setup_vendor" + png)) ;
        list.add(new CustomClass("Transportation", url + "transport_vendor" + png)) ;
        list.add(new CustomClass("Food & Beverages", url +"food_beverage_vendor"+ png)) ;
        list.add(new CustomClass("Merchandise", url +"merchandise_vendor"+ png)) ;
        list.add(new CustomClass("Security", url +"security_vendor"+ png)) ;
        list.add(new CustomClass("Venue Booking",  url +"venue_vendor"+ png)) ;
        list.add(new CustomClass("Event Planners & Management", url +"event_planning"+ png)) ;
        list.add(new CustomClass("Photography & Videography", url +"photography_vendor"+ png)) ;
        list.add(new CustomClass("Media Buying & Radio", url +"media_buying_vendor"+ png)) ;
        list.add(new CustomClass("Parties", url +"parties_vendor"+ png)) ;
        list.add(new CustomClass("Event Staffing", url +"anchor_vendor"+ png)) ;

        SubCategoryAdapter categoryAdapter = new SubCategoryAdapter(NavBarActivity.this, list) ;
        listView.setAdapter(categoryAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displayScreenSelected(item.getItemId());


        return true;
    }

    private void displayScreenSelected(int itemId) {
        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_event_setup:
//                fragment = new EventSetupFragment();
                break;
            case R.id.nav_food_beverage:
//                fragment = new FoodBevFragment();
                break;
            case R.id.nav_merchandise:
 //               fragment = new MerchandiseFrag();
                break;
            case R.id.nav_security:
//                fragment = new SecurityFrag();
                break;
            case R.id.nav_transportation:
//                fragment = new TransportationFragment();
                break;
            case R.id.nav_venue_booking:
//                fragment = new VenueBookingFragment();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame_layout, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}