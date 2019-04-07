package com.example.team10.thevoid;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ai.api.AIListener;
import ai.api.android.AIService;
import ai.api.android.AIConfiguration;
import ai.api.model.AIError;
import ai.api.model.AIResponse;

public class VoidActivity extends AppCompatActivity implements AIListener {
    LinearLayout layout;
    AnimationDrawable animationDrawable;
    AIService aiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_void);

        /**
         * Setup exit button
         */
        Button exitButton = findViewById( R.id.exitButton );
        exitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * Setup background gradient transitions
         */
        layout = (LinearLayout) findViewById(R.id.layout);

        animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

        /**
         * Start vortex animation
         */
        ImageView vortexImage = findViewById(R.id.vortexImage);
        vortexImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));

        /**
         * Start THE VOID
         */
        final AIConfiguration config = new AIConfiguration("a607b2db7c04498da7670ea3c1c9c99a",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiService = AIService.getService(this, config);
        aiService.setListener(this);
        aiService.startListening();
    }

    @Override
    public void onResult(AIResponse result) {

    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }
}
