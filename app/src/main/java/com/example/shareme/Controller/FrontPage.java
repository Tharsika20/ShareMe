package com.example.shareme.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.shareme.R;


public class FrontPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button borrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        borrow = findViewById(R.id.btn_borrow);
        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontPage.this,Borrow_page.class);
                startActivity(i);
            }
        });

    }

   @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.borrow_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.user_account) {
            openBlank();
        } else if (id == R.id.lent) {
            openBlank();
        } else if (id == R.id.borrow) {
            openBorrow();

        } else if (id == R.id.messages) {
            openMessages();

        } else if (id == R.id.friends) {
            openBlank();

        } else if (id == R.id.search) {
            openBlank();

        } else if (id == R.id.about) {
            openAbout();

        } else if(id==R.id.creditcard){

            openCreditcard();

        } else if (id == R.id.logout) {
            openLogin();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openLogin() {
        Intent i = new Intent(FrontPage.this,LoginActivity.class);
        startActivity(i);
    }

    private void openMessages() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"240249@via.dk"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ShareMe");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Test from android studio");
        startActivity(emailIntent);
    }

    private void openBlank() {
        Intent i = new Intent(FrontPage.this, Blank.class);
        startActivity(i);
    }


    private void openBorrow() {
        Intent i = new Intent(FrontPage.this, Borrow_page.class);
        startActivity(i);
    }

    private void openAbout() {
        Intent i = new Intent(FrontPage.this, About.class);
        startActivity(i);
    }

    private void openCreditcard() {
        Intent i = new Intent(FrontPage.this, Creditcard.class);
        startActivity(i);
    }


}
