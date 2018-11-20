package com.example.shareme.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shareme.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.util.Calendar;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.model.MarkerOptions;


public class Item extends FragmentActivity implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener {

    private GoogleMap mMap;
    Button btn1;
    Button btn2;
    Button btn3;
    TextView desc;
    TextView pr;

    Button date;
    TextView time;
    int currentHour;
    int currentMinute;
    TimePickerDialog tpd;
    Button timebtn;
    int integer1 = 1;

    TextView num;
    TextView dur;
    ImageButton inc;
    ImageButton dec;
    FloatingActionMenu fab;
    FloatingActionButton fab1, fab2;
    ViewPager viewPager;

    ImageButton left;
    ImageButton right;
    Intent emailIntent;
    ImageButton back;
    Intent parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        viewPager = findViewById(R.id.images);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        desc=findViewById(R.id.description);
        pr=findViewById(R.id.price);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }});

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        date=findViewById(R.id.datebtn);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment chooseDate = new DateFragment();
                chooseDate.show(getSupportFragmentManager(), "choose date");
            }
        });

        time = findViewById(R.id.time);
        timebtn = findViewById(R.id.timebtn);
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                currentHour=c.get(Calendar.HOUR_OF_DAY);
                currentMinute = c.get(Calendar.MINUTE);

                tpd = new TimePickerDialog(Item.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minutes) {
                        time.setText(String.format("%02d:%02d",hourOfDay, minutes));
                }
                }, currentHour, currentMinute, true);
            tpd.show();
            }

        });
        num = findViewById(R.id.number);
        dur = findViewById(R.id.duration);
        inc = findViewById(R.id.plus);
        dec = findViewById(R.id.minus);
        inc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                integer1 = integer1 + 1;
                num.setText("" + integer1);
            }
        });
        dec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(integer1 >=2)
                {
                integer1 = integer1 - 1;
                num.setText("" + integer1);
                }
                else{
                }
            }
        });

        fab =findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               Toast.makeText(Item.this,"Item saved to wish-list",Toast.LENGTH_LONG).show();

            }
        });

        fab2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                fab2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.setType("message/rfc822");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"240249@via.dk"});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ShareMe:Regarding item 123456");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Test from android studio");
                        startActivity(emailIntent);

                    }
                });
            }});

        left = findViewById(R.id.left);
        right =findViewById(R.id.right);


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i= viewPager.getCurrentItem();
                if (i > 0) {
                    i--;
                    viewPager.setCurrentItem(i);
                } else if (i == 0) {
                    viewPager.setCurrentItem(i);
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = viewPager.getCurrentItem();
                i++;
                viewPager.setCurrentItem(i);
            }
        });

        back= findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }

    private void openActivity() {
        parent=new Intent(Item.this,Borrow_page.class);
        startActivity(parent);
    }

    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Location = new LatLng(55.51475, 9.50124);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Location));
        mMap.setMinZoomPreference(7);
        mMap.addMarker(new MarkerOptions().position(Location).title("Location"));

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView tv = findViewById(R.id.date);
        tv.setText(currentDateString);
    }

}