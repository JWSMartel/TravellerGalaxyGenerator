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
    private int dieRoll;

    private static final String NAME = "Planet Name: ";
    private static final String STARPORTQUALITY = "\nStarport Quality: ";
    private static final String SIZE = "\nSize: ";
    private static final String ATMOTYPE = "\nAtmostphere Type: ";
    private static final String HYDROPER = "\nHydrographic percentage: ";
    private static final String POP = "\nPopulation: ";
    private static final String GOVTYPE = "\nGovernment Type: ";
    private static final String LAW = "\nLaw Level: ";
    private static final String HYPEGN = "-";
    private static final String TECHLV = "\nTech Level: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.descriptionText);
        makePlanetBtn = findViewById(R.id.makePlanet);
        makePlanetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final Planetoid newPlanet = new Planetoid();
                Log.d(TAG, "Make Planet clicked!");
                output.setText(newPlanet.printOut);
            }
        });
    }

    public class Planetoid{
        private String Description = "Planet Description: ";
        private String Name;
        private String Size;
        private String Gs;
        private String Atmo;
        private String Temp;
        private String Hydro;
        private String Pop;
        private String Govt;
        private String Law;
        private String Starport;
        private String Tech;
        private String Bases;
        private String TravelCodes;
        private int SizeRoll;
        private int AtmoRoll;
        private String printOut;

        public Planetoid(){
            Log.d(TAG, "Planet starts being made!");
            createStarport(6,2,this);
            createPlanetSize(10,0,this);
            createPlanetAtmo(5,-5,this);
            Log.d(TAG, Name);
            printOut = NAME+Name+STARPORTQUALITY+Starport+SIZE+Size;
        }

    }

    /**
     * @description Rolls a die between max and min inclusive
     * @param max
     * @param min
     * @return
     */
    public int rollDie(int max, int min){
        return (int) (Math.random()*((max-min)+1))+min;
    }

    public void createStarport(int max, int min, Planetoid planetoid){
        dieRoll = rollDie(max, min);
        planetoid.Name = ""+dieRoll;
    }

    public void createPlanetSize(int max, int min, Planetoid planetoid){
        Log.d(TAG, "createPlanetSize Called");
        planetoid.SizeRoll = rollDie(max,min);
        Log.d(TAG, "Die Result: "+planetoid.SizeRoll);
        switch (planetoid.SizeRoll) {
            case 0:
                Log.d(TAG, "Die roll is 0");
                planetoid.Size = "World Size: 800 km";
                planetoid.Gs = "Negligible";
                planetoid.Description += "Size of an Asteroid or orbital complex. ";
                planetoid.Name += planetoid.SizeRoll;
                break;
            case 1:
                Log.d(TAG, "Die roll is 1");
                planetoid.Size = "World Size: 1,600 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "0.05";
                break;
            case 2:
                Log.d(TAG, "Die roll is 2");
                planetoid.Size = "World Size: 3,200 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "0.15";
                planetoid.Description += "Size of Triton, Luna, or Europa. ";
                break;
            case 3:
                Log.d(TAG, "Die roll is 3");
                planetoid.Size = "World Size: 4,800 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "0.25";
                planetoid.Description += "Size of Mercury or Ganymede. ";
                break;
            case 4:
                Log.d(TAG, "Die roll is 4");
                planetoid.Size = "World Size: 6,400 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "0.35";
                planetoid.Description += "Size of Mars. ";
                break;
            case 5:
                Log.d(TAG, "Die roll is 5");
                planetoid.Size = "World Size: 8,000 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "0.45";
                break;
            case 6:
                Log.d(TAG, "Die roll is 6");
                planetoid.Size = "World Size: 9,600 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "0.7";
                break;
            case 7:
                Log.d(TAG, "Die roll is 7");
                planetoid.Size = "World Size: 11,200 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "0.9";
                break;
            case 8:
                Log.d(TAG, "Die roll is 8");
                planetoid.Size = "World Size: 12,800 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "1.0";
                planetoid.Description += "Size of Earth. ";
                break;
            case 9:
                Log.d(TAG, "Die roll is 9");
                planetoid.Size = "World Size: 14,400 km";
                planetoid.Name += planetoid.SizeRoll;
                planetoid.Gs = "1.25";
                break;
            case 10:
                Log.d(TAG, "Die roll is 10");
                planetoid.Size = "World Size: 16,000 km";
                planetoid.Name = "A" ;
                planetoid.Gs = "1.4";
                break;
            default:
                Log.e(TAG, "Something went wrong in createPlanetSize");
                break;
        }
    }

    public void createPlanetAtmo(int max, int min, Planetoid planetoid){
        Log.d(TAG, "createPlanetAtmo Called!");
        planetoid.AtmoRoll = rollDie(max, min) + planetoid.SizeRoll;
        Log.d(TAG, "Atmo Roll: "+planetoid.AtmoRoll);
        if(planetoid.AtmoRoll < 1){

        }
        switch(planetoid.AtmoRoll){

        }
    }
}
