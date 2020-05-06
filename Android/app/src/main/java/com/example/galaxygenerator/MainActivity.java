package com.example.galaxygenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * @description Creates a standard XxY galaxy map for Android
     * @param savedInstanceState
     */
    private static final String TAG = "Galaxy Creator";
    private Button makePlanetBtn;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.descriptionText);
        makePlanetBtn = findViewById(R.id.makePlanet);
        final Planetoid planetOne = new Planetoid();
        makePlanetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "Make Planet clicked!");
                output.setText("Changed!");
                output.setText(planetOne.description);
            }
        });
    }

    public class Planetoid{
        private String description = "Planet Description";
        private String name;
        private String size;
        private String atmo;
        private String temp;
        private String hydro;
        private String pop;
        private String govt;
        private String law;
        private String starport;
        private String tech;
        private String bases;
        private String travelCodes;
    }
}
