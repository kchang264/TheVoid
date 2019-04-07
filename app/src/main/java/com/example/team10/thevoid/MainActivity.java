package com.example.team10.thevoid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import ai.api.AIListener;
//import ai.api.AIConfiguration;
import ai.api.AIServiceContext;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import ai.api.android.AIConfiguration;
import com.google.gson.JsonElement;
import java.util.Map;



public class MainActivity extends AppCompatActivity implements AIListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterVoid = findViewById( R.id.enterButton );
        enterVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVoid();
            }
        });

        final AIConfiguration config = new AIConfiguration("CLIENT_ACCESS_TOKEN",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
    }

    /**
     * Launches new activity
     */
    private void openVoid() {
        Intent intent = new Intent(this, VoidActivity.class);
        startActivity(intent);
    }

    /**
     * Event fires when entire process finished successfully, and returns result object
     *
     * @param result the result object, contains server answer
     */
    @Override
    public void onResult(AIResponse result) {

    }

    /**
     * Event fires if something going wrong while recognition or access to the AI server
     *
     * @param error the error description object
     */
    @Override
    public void onError(AIError error) {

    }

    /**
     * Event fires every time sound level changed. Use it to create visual feedback. There is no guarantee that this method will
     * be called.
     *
     * @param level the new RMS dB value
     */
    @Override
    public void onAudioLevel(float level) {

    }

    /**
     * Event fires when recognition engine start listening
     */
    @Override
    public void onListeningStarted() {

    }

    /**
     * Event fires when recognition engine cancel listening
     */
    @Override
    public void onListeningCanceled() {

    }

    /**
     * Event fires when recognition engine finish listening
     */
    @Override
    public void onListeningFinished() {

    }
}
