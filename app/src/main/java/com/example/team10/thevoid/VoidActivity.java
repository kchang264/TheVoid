package com.example.team10.thevoid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import com.google.gson.JsonElement;
import java.util.Map;

public class VoidActivity extends AppCompatActivity implements AIListener {
    LinearLayout layout;
    AnimationDrawable animationDrawable;
    AIService aiService;
    private String TAG = "VoidActivity: ";

    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;

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
         * a607b2db7c04498da7670ea3c1c9c99a
         * de7fd5f4168246c78e8a039eebc0fa50
         */




        final AIConfiguration config = new AIConfiguration("de7fd5f4168246c78e8a039eebc0fa50",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiService = AIService.getService(this, config);
        aiService.setListener(this);
        requestAudioPermissions();
        //aiService.startListening();
        Log.d(TAG, "onCreate: Started Listening");
    }

    private void requestAudioPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            //When permission is not granted by user, show them message why this permission is needed.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this, "Please grant permissions to record audio", Toast.LENGTH_LONG).show();

                //Give user option to still opt-in the permissions
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);

            } else {
                // Show user dialog to grant permission to record audio
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);
            }
        }
        //If permission is granted, then go ahead recording audio
        else if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {

            //Go ahead with recording audio now
            aiService.startListening();

        }
    }

    //Handling callback
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    //start recording
                    aiService.startListening();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permissions Denied to record audio", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onResult(AIResponse response ) {
        Log.d(TAG, "onResult: Recieved response");
        Result result = response.getResult();

        // Get parameters
        String parameterString = "";
        if (result.getParameters() != null && !result.getParameters().isEmpty()) {
            for (final Map.Entry<String, JsonElement> entry : result.getParameters().entrySet()) {
                parameterString += "(" + entry.getKey() + ", " + entry.getValue() + ") ";
            }
        }

        // Log results.
        Log.d(TAG, "onResult: Query:" + result.getResolvedQuery() +
                "\nAction: " + result.getAction() +
                "\nParameters: " + parameterString);

    }

    @Override
    public void onError(AIError error) {
        Log.d(TAG, "onError: " + error.toString());
        Log.d(TAG, "onError: ERROR");
    }

    @Override
    public void onAudioLevel(float level) {
        Log.d(TAG, "onAudioLevel: DO SOMETHING ABOUT AUDIO LEVEL? ");
    }

    @Override
    public void onListeningStarted() {
        Log.d(TAG, "onListeningStarted: LISTENING STARTED");
    }

    @Override
    public void onListeningCanceled() {
        Log.d(TAG, "onListeningCanceled: LISTENING CANCELED ");
    }

    @Override
    public void onListeningFinished() {
        Log.d(TAG, "onListeningFinished: LISTENING FINISHED");
    }
}
