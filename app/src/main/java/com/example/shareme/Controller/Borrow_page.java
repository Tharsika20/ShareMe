package com.example.shareme.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.shareme.Model.Items;
import com.example.shareme.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Borrow_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private FirebaseDatabase database;
    private DatabaseReference db;
    private MyAdapter adapter;
    private RecyclerView rv;
    ArrayList<Items> items;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton fab3;
    ChildEventListener mChildEventListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //SETUP RECYCLER
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<Items>();


        database = FirebaseDatabase.getInstance();
        db = database.getReference();
       /* db.child("items").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    image1 = ds.child("image").getValue().toString();
                    name1 = ds.child("name").getValue().toString();
                    description1 =
                    price1 = Float.parseFloat(ds.child("price").getValue().toString());
                    location1 = ds.child("address").getValue().toString();

                }
                Items i = new Items(image1, name1, price1, location1);
                adapter = new MyAdapter(Borrow_page.this, items);
                rv.setAdapter(adapter);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Borrow_page.this, "Opssss.... something is wrong", Toast.LENGTH_SHORT);
            }
        });*/
        db.child("items").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Items item = dataSnapshot.getValue(Items.class);
                items.add(item);
                adapter = new MyAdapter(Borrow_page.this,items);
                rv.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fab3 = findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {
              Intent intent = new Intent(Borrow_page.this, AddItem.class);
              startActivity(intent);
            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
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
        Intent i = new Intent(Borrow_page.this,LoginActivity.class);
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
        Intent i = new Intent(Borrow_page.this, Blank.class);
        startActivity(i);
    }


    private void openBorrow() {
        Intent i = new Intent(Borrow_page.this, Borrow_page.class);
        startActivity(i);
    }

    private void openAbout() {
        Intent i = new Intent(Borrow_page.this, About.class);
        startActivity(i);
    }

    private void openCreditcard() {
        Intent i = new Intent(Borrow_page.this, Creditcard.class);
        startActivity(i);
    }


}
