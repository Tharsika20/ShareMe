package com.example.shareme.Controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.shareme.R;

public class SignupDone extends AppCompatActivity {

    Button start;
    ImageView shareme;
    ObjectAnimator animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_done);

        shareme = findViewById(R.id.sharemelogo);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(0f, 0f, 1200f, 700f, 230f, -100f, true);
            animation = ObjectAnimator.ofFloat(shareme, View.X, View.Y, path);
            animation.setDuration(2000);
            animation.start();
        } else {
            animation = ObjectAnimator.ofFloat(shareme, "translationX", 0f,0f,500f,0f);
            animation.setDuration(4000);
            animation.start();
        }

        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });


        start = findViewById(R.id.getStarted);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

    }

    private void openActivity() {
            Intent i= new Intent(this,FrontPage.class);
            startActivity(i);
        }


    }
