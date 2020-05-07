package com.example.galaxygenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    /**
     * @description Creates a standard XxY galaxy map for Android
     * @param savedInstanceState
     */
    private static final String TAG = "Galaxy Creator";
    private Button makePlanetBtn;
    private TextView output;
    private int dieRoll;

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

    /**
     * @description Rolls a die between max and min inclusive
     * @param max
     * @param min
     * @return
     */
    public int rollDie(int max, int min){
        return (int) (Math.random()*((max-min)+1))+min;
    }

    public class Planetoid{

        private static final String NAME = "Planet Name: ";
        private static final String STARPORTQUALITY = "\nStarport Quality: ";
        private static final String SIZE = "\nSize: ";
        private static final String ATMOTYPE = "\nAtmostphere Type: ";
        private static final String HYDROPER = "\nHydrographic Percentage: ";
        private static final String POP = "\nPopulation: ";
        private static final String GOVTYPE = "\nGovernment Type: ";
        private static final String LAW = "\nLaw Level: ";
        private static final String HYPHEN = "-";
        private static final String TECHLV = "\nTech Level: ";

        private static final String TEMP = "\nTemperature: ";

        private String Description = "\nPlanet Description: ";
        private String Name;
        private String Size;
        private String Gs;
        private String Atmo;
        private String Pressure;
        private String Temp;
        private String Hydro;
        private String Pop;
        private String Govt;
        private String Law;
        private String Starport;
        private String Tech;
        private String Bases;
        private String TravelCodes;
        private String SurvivalGearReq;
        private int SizeRoll;
        private int AtmoRoll;
        private String printOut;

        private Planetoid(){
            Log.d(TAG, "Planet starts being made!");
            createStarport(this);
            createPlanetSize(this);
            createPlanetAtmo(this);
            createPlanetTemp(this);
            createPlanetHydro(this);
            Log.d(TAG, Name);
            printOut = NAME+Name+Description+STARPORTQUALITY+Starport+SIZE+Size+ATMOTYPE+Atmo+TEMP+Temp+
                    HYDROPER+Hydro+POP+Pop+GOVTYPE+Govt+LAW+Law+TECHLV+Tech;
        }

        private void createStarport(Planetoid planetoid){
            dieRoll = rollDie(6, 2);
            planetoid.Name = ""+dieRoll;
        }

        private void createPlanetSize(Planetoid planetoid){
            Log.d(TAG, "createPlanetSize Called");
            planetoid.SizeRoll = rollDie(10,0);
            if(planetoid.SizeRoll == 10){
                planetoid.Name += "A";
            }else{
                planetoid.Name += planetoid.SizeRoll;
            }
            switch (planetoid.SizeRoll) {
                case 0:
                    Log.d(TAG, "Die roll is 0");
                    planetoid.Size = "800 km";
                    planetoid.Gs = "Negligible";
                    planetoid.Description += "Size of an Asteroid or orbital complex. ";
                    break;
                case 1:
                    Log.d(TAG, "Die roll is 1");
                    planetoid.Size = "1,600 km";
                    planetoid.Gs = "0.05";
                    break;
                case 2:
                    Log.d(TAG, "Die roll is 2");
                    planetoid.Size = "3,200 km";
                    planetoid.Gs = "0.15";
                    planetoid.Description += "Size of Triton, Luna, or Europa. ";
                    break;
                case 3:
                    Log.d(TAG, "Die roll is 3");
                    planetoid.Size = "4,800 km";
                    planetoid.Gs = "0.25";
                    planetoid.Description += "Size of Mercury or Ganymede. ";
                    break;
                case 4:
                    Log.d(TAG, "Die roll is 4");
                    planetoid.Size = "6,400 km";
                    planetoid.Gs = "0.35";
                    planetoid.Description += "Size of Mars. ";
                    break;
                case 5:
                    Log.d(TAG, "Die roll is 5");
                    planetoid.Size = "8,000 km";
                    planetoid.Gs = "0.45";
                    break;
                case 6:
                    Log.d(TAG, "Die roll is 6");
                    planetoid.Size = "9,600 km";
                    planetoid.Gs = "0.7";
                    break;
                case 7:
                    Log.d(TAG, "Die roll is 7");
                    planetoid.Size = "11,200 km";
                    planetoid.Gs = "0.9";
                    break;
                case 8:
                    Log.d(TAG, "Die roll is 8");
                    planetoid.Size = "12,800 km";
                    planetoid.Gs = "1.0";
                    planetoid.Description += "Size of Earth. ";
                    break;
                case 9:
                    Log.d(TAG, "Die roll is 9");
                    planetoid.Size = "14,400 km";
                    planetoid.Gs = "1.25";
                    break;
                case 10:
                    Log.d(TAG, "Die roll is 10");
                    planetoid.Size = "16,000 km";
                    planetoid.Gs = "1.4";
                    break;
                default:
                    Log.e(TAG, "Something went wrong in createPlanetSize");
                    break;
            }
        }

        private void createPlanetAtmo(Planetoid planetoid){
            Log.d(TAG, "createPlanetAtmo Called!");
            planetoid.AtmoRoll = rollDie(5, -5) + planetoid.SizeRoll;
            if(planetoid.AtmoRoll < 1){
                //No Atmo
                planetoid.Name += "0";
                planetoid.Atmo = "None";
                planetoid.Description += "Moon like atmosphere. ";
                planetoid.Pressure = "0.0";
                planetoid.SurvivalGearReq = "Vacc Suit";
            }else{
                if(planetoid.AtmoRoll<10){
                    planetoid.Name += AtmoRoll;
                }
                switch(planetoid.AtmoRoll){
                    case 1:
                        planetoid.Atmo = "Trace";
                        planetoid.Description += "Mars like atmosphere. ";
                        planetoid.Pressure = "0.001-0.09";
                        planetoid.SurvivalGearReq = "Vacc Suit";
                        break;
                    case 2:
                        planetoid.Atmo = "Very Thin, Tainted";
                        planetoid.Pressure = "0.1-0.42";
                        planetoid.SurvivalGearReq = "Respirator, Filter";
                        break;
                    case 3:
                        planetoid.Atmo = "Very Thin";
                        planetoid.Pressure = "0.1-0.42";
                        planetoid.SurvivalGearReq = "Respirator";
                        break;
                    case 4:
                        planetoid.Atmo = "Thin, Tainted";
                        planetoid.Pressure = "0.43-0.7";
                        planetoid.SurvivalGearReq = "Filter";
                        break;
                    case 5:
                        planetoid.Atmo = "Thin";
                        planetoid.Pressure = "0.43-0.7";
                        break;
                    case 6:
                        planetoid.Atmo = "Standard";
                        planetoid.Description += "Earth like atmosphere. ";
                        planetoid.Pressure = "0.71-1.49";
                        break;
                    case 7:
                        planetoid.Atmo = "Standard, Tainted";
                        planetoid.Pressure = "0.71-1.49";
                        planetoid.SurvivalGearReq = "Filter";
                        break;
                    case 8:
                        planetoid.Atmo = "Dense";
                        planetoid.Pressure = "1.5-2.49";
                        break;
                    case 9:
                        planetoid.Atmo = "Dense, Tainted";
                        planetoid.Pressure = "1.5-2.49";
                        planetoid.SurvivalGearReq = "Filter";
                        break;
                    case 10:
                        planetoid.Name += "A";
                        planetoid.Atmo = "Exotic";
                        planetoid.Description += "Atmosphere is unbreathable to humans but otherwise" +
                                " not hazardous. ";
                        planetoid.Pressure = "Varies";
                        planetoid.SurvivalGearReq = "Air Supply";
                        break;
                    case 11:
                        planetoid.Name += "B";
                        planetoid.Atmo = "Corrosive";
                        planetoid.Description += "Venus like atmosphere. Highly dangerous: anyone" +
                                " who breathes this corrosive atmosphere will suffer 1d6 damage " +
                                "per round. ";
                        planetoid.Pressure = "Varies";
                        planetoid.SurvivalGearReq = "Vacc Suit";
                        break;
                    case 12:
                        planetoid.Name += "C";
                        planetoid.Atmo = "Insidious";
                        planetoid.Pressure = "Varies";
                        planetoid.SurvivalGearReq = "Vacc Suit";
                        planetoid.Description += "Unsafe to be there for longer than "
                                + rollDie(12,2) + " hours due to degradation of equipment" +
                                " as it deals with the harsh atmosphere. This can be prevented with" +
                                " vigilant maintenance or advanced protective gear. ";
                        break;
                    case 13:
                        planetoid.Name += "D";
                        planetoid.Atmo = "Dense, High";
                        planetoid.Pressure = "2.5+";
                        break;
                    case 14:
                        planetoid.Name += "E";
                        planetoid.Atmo = "Thin, Low";
                        planetoid.Pressure = "0.5 or less";
                        break;
                    case 15:
                        planetoid.Name += "F";
                        planetoid.Atmo = "Unusual";
                        planetoid.Pressure = "Varies";
                        planetoid.SurvivalGearReq = "Varies";
                        break;
                    default:
                        Log.e(TAG, "Something went wrong in Atmo creation.");
                }
                if (planetoid.Description.contains("Tainted")){
                    planetoid.Description += "Breathing this atmosphere causes 1d6 damage every few minutes.";
                }
            }

    }

        private void createPlanetTemp(Planetoid planetoid){
            Log.d(TAG, "createPlanetTemp Called!");
            dieRoll = rollDie(12,2);
            Log.d(TAG, "Temp Die: "+dieRoll);
            Log.d(TAG, "Atmo Roll: "+planetoid.AtmoRoll);
            if(planetoid.AtmoRoll<2){
                planetoid.Description += "Temperatures swing wildly from roasting during the day " +
                        "to freezing at night.";
            }else if(planetoid.AtmoRoll == 2||planetoid.AtmoRoll == 3){
                dieRoll -= 2;
            }else if(planetoid.AtmoRoll == 4||planetoid.AtmoRoll == 5 || planetoid.AtmoRoll == 14) {
                dieRoll -= 1;
            }else if(planetoid.AtmoRoll == 8||planetoid.AtmoRoll == 9){
                dieRoll += 1;
            }else if(planetoid.AtmoRoll == 10||planetoid.AtmoRoll == 13||planetoid.AtmoRoll == 15){
                dieRoll += 2;
            }else if(planetoid.AtmoRoll == 11||planetoid.AtmoRoll == 12){
                dieRoll += 6;
            }
            Log.d(TAG, "Temp Prog: "+dieRoll);

            if(dieRoll <3){
                planetoid.Temp = "Frozen: >-51\u2103";
                planetoid.Description += "Frozen world. No liquid water, very dry atmostphere. ";
            }else if(dieRoll == 3||dieRoll == 4){
                planetoid.Temp = "Cold: -51-0\u2103";
                planetoid.Description += "Icy world. Little liquid water, extensive ice caps, few clouds. ";
            }else if(dieRoll<10){
                Log.d(TAG, "Planet Temperate");
                planetoid.Temp = "Temperate: 0-90\u2103";
                planetoid.Description += "Temperate world. Earthlike. Liquid and vaporised water are" +
                        " common, moderate ice caps. ";
            }else if(dieRoll == 10||dieRoll == 11){
                planetoid.Temp = "Hot: 31-80\u2103";
                planetoid.Description += "Hot world. Small or no ice caps, little liquid water." +
                        " Most water in the form of clouds. ";
            }else{
                planetoid.Temp = "Roasting: 81\u2103+";
                planetoid.Description += "Boiling world. No ice caps, little liquid water. ";
            }
            Log.d(TAG, "Planet Temp in Temp: "+planetoid.Temp);
        }

        private void createPlanetHydro(Planetoid planetoid){
            Log.d(TAG, "createPlanetHydro Called!");
            //2d6-7+Size
            dieRoll = rollDie(5,-5) + planetoid.SizeRoll;
            if(planetoid.SizeRoll<2){
                planetoid.Hydro = "0";
            }
            if(planetoid.AtmoRoll == 0||planetoid.AtmoRoll == 1||(planetoid.AtmoRoll>9&&planetoid.AtmoRoll<13)){
                dieRoll -=4;
            }
            if(planetoid.Temp.contains("Hot")){
                dieRoll -= 2;
            }else if(planetoid.Temp.contains("Roasting")){
                dieRoll -= 6;
            }

            switch(dieRoll){
                
            }
        }
    }
}
