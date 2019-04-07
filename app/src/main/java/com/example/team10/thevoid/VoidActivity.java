package com.example.team10.thevoid;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class VoidActivity extends AppCompatActivity {
    LinearLayout layout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_void);

        //setup exit button
        Button exitButton = findViewById( R.id.exitButton );
        exitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //setup background gradient animation
        layout = (LinearLayout) findViewById(R.id.layout);

        animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

        //setup vortex spinning
        ImageView vortexImage = findViewById(R.id.vortexImage);
        vortexImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
    }

}
