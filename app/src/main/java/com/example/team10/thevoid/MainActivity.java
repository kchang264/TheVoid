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

public class MainActivity extends AppCompatActivity {

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
    }

    /**
     * Launches new activity
     */
    private void openVoid() {
        Intent intent = new Intent(this, VoidActivity.class);
        startActivity(intent);
    }
}
