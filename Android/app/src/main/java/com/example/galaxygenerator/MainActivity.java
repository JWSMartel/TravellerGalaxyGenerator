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
        if(min>=max){
            return 10000;
        }
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
        private static final String TECHLV = "\nTech Level: ";

        private static final String TEMP = "\nTemperature: ";

        private String Description = "\n\nPlanet Description: ";
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
        private int PopRoll;
        private int GovRoll;
        private int CultureTensRoll;
        private int CultureOnesRoll;
        private int LawRoll;
        private String printOut;

        private Planetoid(){
            Log.d(TAG, "Planet starts being made!");
            createStarport(this);
            createPlanetSize(this);
            createPlanetAtmo(this);
            createPlanetTemp(this);
            createPlanetHydro(this);
            createPlanetPop(this);
            createPlanetGov(this);
            createPlanetLaw(this);
            createPlanetTech(this);
            Log.d(TAG, Name);
            printOut = NAME+Name+STARPORTQUALITY+Starport+SIZE+Size+ATMOTYPE+Atmo+TEMP+
                    Temp+HYDROPER+Hydro+POP+Pop+GOVTYPE+Govt+LAW+Law+TECHLV+Tech+Description;
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
                        "to freezing at night. ";
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

            if(dieRoll<1){
                planetoid.Name += "0";
                planetoid.Hydro = "0-5%";
                planetoid.Description += "Desert world. ";
            }else if(dieRoll>10){
                planetoid.Name += "A";
                planetoid.Hydro = "96-100%";
                planetoid.Description += "Almost entirely water. ";
            }else{
                planetoid.Name += dieRoll;
                switch (dieRoll){
                    case 1:
                        planetoid.Hydro = "6-15%";
                        planetoid.Description += "Dry world. ";
                        break;
                    case 2:
                        planetoid.Hydro = "16-25%";
                        planetoid.Description += "A few small seas. ";
                        break;
                    case 3:
                        planetoid.Hydro = "26-35%";
                        planetoid.Description += "Small seas and oceans. ";
                        break;
                    case 4:
                        planetoid.Hydro = "36-45%";
                        planetoid.Description += "Wet world. ";
                        break;
                    case 5:
                        planetoid.Hydro = "46-55%";
                        planetoid.Description += "Large oceans. ";
                        break;
                    case 6:
                        planetoid.Hydro = "56-65%";
                        break;
                    case 7:
                        planetoid.Hydro = "66-75%";
                        planetoid.Description += "Earthlike amount of water. ";
                        break;
                    case 8:
                        planetoid.Hydro = "76-85%";
                        planetoid.Description += "Water world. ";
                        break;
                    case 9:
                        planetoid.Hydro = "86-95%";
                        planetoid.Description += "Only a few small islands and archipelagos. ";
                        break;
                    default:
                        Log.e(TAG, "Something went wrong in the Hydro Switch.");
                        break;
                }
            }
        }

        private void createPlanetPop(Planetoid planetoid){
            Log.d(TAG, "createPlanetPop Called!");
            planetoid.PopRoll = rollDie(0,10);
            if(planetoid.PopRoll == 10){
                planetoid.Name += "A";
                Pop = "Tens of billions";
                planetoid.Description += "Population of tens of billions. ";
            }else{
                planetoid.Name += planetoid.PopRoll;
                switch (planetoid.PopRoll){
                    case 0:
                        Pop = "None";
                        planetoid.Description += "Uninhabited. ";
                        break;
                    case 1:
                        Pop = "Few";
                        planetoid.Description += "Population consisting of a tiny farmstead" +
                                " or a large family. ";
                        break;
                    case 2:
                        Pop = "Hundreds";
                        planetoid.Description += "Population consisting of a village. ";
                        break;
                    case 3:
                        Pop = "Thousands";
                        planetoid.Description += "Population of thousands. ";
                        break;
                    case 4:
                        Pop = "Tens of thousands";
                        planetoid.Description += "Population consisting of a small town. ";
                        break;
                    case 5:
                        Pop = "Hundreds of thousands";
                        planetoid.Description += "Population consisting of an average city. ";
                        break;
                    case 6:
                        Pop = "Millions";
                        planetoid.Description += "Population of millions. ";
                        break;
                    case 7:
                        Pop = "Tens of millions";
                        planetoid.Description += "Population consisting of a large city. ";
                        break;
                    case 8:
                        Pop = "Hundreds of millions";
                        planetoid.Description += "Population of hundreds of millions. ";
                        break;
                    case 9:
                        Pop = "Billions";
                        planetoid.Description += "Population similar to present day Earth. ";
                        break;
                    default:
                        Log.e(TAG, "Something went wrong in planet pop generator.");
                        break;
                }
            }
        }

        private void createPlanetGov(Planetoid planetoid){
            Log.d(TAG, "createPlanetGov Called!");
            if(planetoid.PopRoll == 0){
                planetoid.Govt = "None";
                planetoid.Description += "No government structure. In many cases, family bonds" +
                        "predominate. Examples include a Family, Clan, or total Anarchy. Little" +
                        " could be considered illegal here. ";
            }else{
                planetoid.GovRoll = rollDie(5,-5) + planetoid.PopRoll;
                Log.d(TAG, "GovRoll: "+ planetoid.GovRoll);
                if(planetoid.GovRoll <= 0){
                    planetoid.Govt = "None";
                    planetoid.Description += "No government structure. In many cases, family bonds" +
                            "predominate. Examples include a Family, Clan, or total Anarchy. Little" +
                            " could be considered illegal here. ";
                }else{
                    switch (planetoid.GovRoll){
                        case 1:
                            planetoid.Govt = "Company/Corporation";
                            planetoid.Description += "Ruling functions are assumed by a company " +
                                    "managerial elite, and most citizenry are company employees " +
                                    "or dependants. Examples include a corporate outpost, asteroid " +
                                    "mine, or a feudal domain. Likely would not appreciate weapons, " +
                                    "drugs, or Travellers in their space. ";
                            break;
                        case 2:
                            planetoid.Govt = "Participating Democracy";
                            planetoid.Description += "Ruling functions are reached by the advise " +
                                    "and consent of the citizenry directly. Examples include a " +
                                    "collective, tribal council, or comm-linked consensus. Such " +
                                    "civilizations tend to be weary of drugs. ";
                            break;
                        case 3:
                            planetoid.Govt = "Self-perpetuating Oligarchy";
                            planetoid.Description += "Ruling functions are preformed by a restricted" +
                                    " minority, with little to no input from the mass of the " +
                                    "citizenry. Exmaples include a plutocracy or a hereditary ruling" +
                                    " caste. They do no tend to like weapons, technology, or Travellers. ";
                            break;
                        case 4:
                            planetoid.Govt = "Represenative Democracy";
                            planetoid.Description += "Ruling functions are preformed by elected " +
                                    "representatives. Examples include a republic or democracy. " +
                                    "Likely would not appreciate drugs, weapons, or psionics.";
                            break;
                        case 5:
                            planetoid.Govt = "Feudal Techocracy";
                            planetoid.Description += "Ruling functions are preformed by specific " +
                                    "individuals for persons who agree to be ruled by them. " +
                                    "Relationships are based on the performance of technical " +
                                    "activities which are mutually beneficial.They do not tend to " +
                                    "like outside weapons, technology, or computers. ";
                            break;
                        case 6:
                            planetoid.Govt = "Captive Government";
                            planetoid.Description += "Ruling functions are performed by an imposed " +
                                    "leadership answerable to an outside group. Examples include a " +
                                    "colony or conquered area. Contraband here may include weapons, " +
                                    "technology, or Travellers. ";
                            break;
                        case 7:
                            planetoid.Govt = "Balkanisation";
                            planetoid.Description += "No central authority exists rival governments" +
                                    " complete for control. Law Level refers to the government " +
                                    "nearest the starport. Examples include multiple governments " +
                                    "involved in a civil war. They could have an aversion to anything. ";
                            break;
                        case 8:
                            planetoid.Govt = "Civil Service Bureaucracy";
                            planetoid.Description += "Ruling functions are performed by government " +
                                    "agencies employing individuals selected for their expertise. " +
                                    "Examples include a technocracy or communism. They likely look " +
                                    "down on drugs and weapons. ";
                            break;
                        case 9:
                            planetoid.Govt = "Impersonal Bureaucracy";
                            planetoid.Description += "Ruling functions are performed by agencies " +
                                    "which have become insulated from the governed citizens. Examples " +
                                    "include entrenched castes of bureaucrats or a decaying empire. " +
                                    "They do like technology, weapons, drugs, Travellers, or " +
                                    "psionics typically. ";
                            break;
                        case 10:
                            planetoid.Govt = "Charismatic Dictator";
                            planetoid.Description += "Ruling functions are performed by agencies " +
                                    "directed by a single leader who enjoys the overwhelming " +
                                    "confidence of the citizens. Examples include a revolutionary " +
                                    "leader, messiah, or emperor. Only the dictator can decide what " +
                                    "is illegal here. ";
                            break;
                        case 11:
                            planetoid.Govt = "Non-charismatic Leader";
                            planetoid.Description += "A previous charismatic dictator " +
                                    "has been replaced by a leader through normal channels. Examples include" +
                                    " military dictatorship or hereditary kingship. They do not appreciate " +
                                    "weapons, technology, or computers from other lands. ";
                            break;
                        case 12:
                            planetoid.Govt = "Charismatic Oligarchy";
                            planetoid.Description += "Ruling functions are performed" +
                                    " by a select group of members of an organisation or class which enjoys " +
                                    "the overwhelming confidence of the citizenry. Examples include junta, " +
                                    "or a revolutionary council. They tend not to appreciate weapons around them. ";
                            break;
                        default:
                            planetoid.Govt = "Religious Dictatorship";
                            planetoid.Description += "Ruling functions are " +
                                    "performed by a religious organisation without regard to the specific " +
                                    "individual needs ot the citizenry. Examples include cults, transcendent" +
                                    " philosophy, or psionic group minds. Who is to say what they do or do " +
                                    "not want in their sectors. ";
                            break;
                    }
                    rollCulture(planetoid);

                    //Decide number of factions
                    int factionRoll = rollDie(3,1);
                    if(planetoid.GovRoll == 0||planetoid.GovRoll == 7){
                         factionRoll += 1;
                    }else if(planetoid.GovRoll > 10){
                        factionRoll -= 1;
                    }

                    //Make a faction for each
                    if(factionRoll > 0){
                        planetoid.Description += "\n\nThere are "+factionRoll+" other factions on the " +
                                "planet as well. ";
                        for(int f=1;f<=factionRoll;f++){
                            planetoid.Description += "\n\nSplinter faction #"+f+": ";
                            rollFactionGov(planetoid);
                        }
                    }
                }
            }
        }

        private void rollFactionGov(Planetoid planetoid){
            int factionGovRoll = rollDie(13,0);
            switch (factionGovRoll){
                case 1:
                    planetoid.Description += "Company/Corporation: Ruling functions are assumed by a company " +
                            "managerial elite, and most citizenry are company employees " +
                            "or dependants. Examples include a corporate outpost, asteroid " +
                            "mine, or a feudal domain. Likely would not appreciate weapons, " +
                            "drugs, or Travellers in their space. ";
                    break;
                case 2:
                    planetoid.Description += "Participating Democracy: " +
                            "Ruling functions are reached by the advise " +
                            "and consent of the citizenry directly. Examples include a " +
                            "collective, tribal council, or comm-linked consensus. Such " +
                            "civilizations tend to be weary of drugs. ";
                    break;
                case 3:
                    planetoid.Description += "Self-perpetuating Oligarchy: " +
                            "Ruling functions are preformed by a restricted" +
                            " minority, with little to no input from the mass of the " +
                            "citizenry. Exmaples include a plutocracy or a hereditary ruling" +
                            " caste. They do no tend to like weapons, technology, or Travellers. ";
                    break;
                case 4:
                    planetoid.Description += "Representative Democracy: " +
                            "Ruling functions are preformed by elected " +
                            "representatives. Examples include a republic or democracy. " +
                            "Likely would not appreciate drugs, weapons, or psionics.";
                    break;
                case 5:
                    planetoid.Description += "Feudal Technocracy: " +
                            "Ruling functions are preformed by specific " +
                            "individuals for persons who agree to be ruled by them. " +
                            "Relationships are based on the performance of technical " +
                            "activities which are mutually beneficial.They do not tend to " +
                            "like outside weapons, technology, or computers. ";
                    break;
                case 6:
                    planetoid.Description += "Captive Government: " +
                            "Ruling functions are performed by an imposed " +
                            "leadership answerable to an outside group. Examples include a " +
                            "colony or conquered area. Contraband here may include weapons, " +
                            "technology, or Travellers. ";
                    break;
                case 7:
                    planetoid.Description += "Balkanisation: No central authority exists rival governments" +
                            " complete for control. Law Level refers to the government " +
                            "nearest the starport. Examples include multiple governments " +
                            "involved in a civil war. They could have an aversion to anything. ";
                    break;
                case 8:
                    planetoid.Description += "Civil Service Bureaucracy: " +
                            "Ruling functions are performed by government " +
                            "agencies employing individuals selected for their expertise. " +
                            "Examples include a technocracy or communism. They likely look " +
                            "down on drugs and weapons. ";
                    break;
                case 9:
                    planetoid.Description += "Impersonal Bureaucracy: " +
                            "Ruling functions are performed by agencies " +
                            "which have become insulated from the governed citizens. Examples " +
                            "include entrenched castes of bureaucrats or a decaying empire. " +
                            "They do like technology, weapons, drugs, Travellers, or " +
                            "psionics typically. ";
                    break;
                case 10:
                    planetoid.Description += "Charismatic Dictator : " +
                            "Ruling functions are performed by agencies " +
                            "directed by a single leader who enjoys the overwhelming " +
                            "confidence of the citizens. Examples include a revolutionary " +
                            "leader, messiah, or emperor. Only the dictator can decide what " +
                            "is illegal here. ";
                    break;
                case 11:
                    planetoid.Description += "Non-charismatic Leader : A previous charismatic dictator" +
                            " has been replaced by a leader through normal channels. Examples include" +
                            " military dictatorship or hereditary kingship. They do not appreciate " +
                            "weapons, technology, or computers from other lands. ";
                    break;
                case 12:
                    planetoid.Description += "Charismatic oligarchy : Ruling functions are performed" +
                            " by a select group of members of an organisation or class which enjoys " +
                            "the overwhelming confidence of the citizenry. Examples include junta, " +
                            "or a revolutionary council. They tend not to appreciate weapons around them. ";
                    break;
                default:
                    planetoid.Govt = "Religious Dictatorship";
                    planetoid.Description += "Ruling functions are " +
                            "performed by a religious organisation without regard to the specific " +
                            "individual needs ot the citizenry. Examples include cults, transcendent" +
                            " philosophy, or psionic group minds. Who is to say what they do or do " +
                            "not want in their sectors. ";
                    break;
            }
            if(factionGovRoll == planetoid.GovRoll){
                planetoid.Description += "This is a splinter faction within the ruling government. ";
            }
            int factionStrengthRoll = rollDie(12,1);
            planetoid.Description += "\n\nFaction strength: "+factionStrengthRoll+" - ";
            if(factionStrengthRoll < 4){
                planetoid.Description += "This faction is an obscure group. Few have heard of them " +
                        "and they have no public support. ";
            }else if(factionStrengthRoll < 6){
                planetoid.Description += "This is a fringe group. There are few supporters throughout" +
                        " the populace. ";
            }else if(factionStrengthRoll < 8){
                planetoid.Description += "This is a minor group with some supporters among the populace. ";
            }else if(factionStrengthRoll < 10){
                planetoid.Description += "This is a notable group here with a few significant and " +
                        "well known members among its members, openly or otherwise. ";
            }else if(factionStrengthRoll < 12){
                planetoid.Description += "This faction is significant and could rival the primary government. ";
            }else{
                planetoid.Description += "This is an overwhelmingly popular faction, certainly with " +
                        "more, if less than official, power than the primary government. ";
            }
        }

        private void rollCulture(Planetoid planetoid){
            planetoid.Description += "\n\n";
            int tensDie = rollDie(6,1);
            dieRoll = rollDie(6,1);
            switch (tensDie){
                case 1:
                    switch(dieRoll){
                        case 1:
                            planetoid.Description += "Sexist - one gender is considered subservient or inferior to the other. ";
                            break;
                        case 2:
                            planetoid.Description += "Religious - culture is heavily influenced by a religion or belief system, possibly one unique to this world. ";
                            break;
                        case 3:
                            planetoid.Description += "Artistic - art and culture are highly prized. Aesthetic design is important in all artifacts produced onworld. ";
                            break;
                        case 4:
                            planetoid.Description += "Ritualised - social interaction and trade is highly formalised. Politeness and adherence to traditional forms is considered very important. ";
                            break;
                        case 5:
                            planetoid.Description += "Conservative - the culture resists change and outside influences. ";
                            break;
                        case 6:
                            planetoid.Description += "Xenophobic - the culture distrusts outsiders and alien influences. Offworlders will face considerable prejudice. ";
                            break;
                    }
                    break;
                case 2:
                    switch(dieRoll){
                        case 1:
                            planetoid.Description += "Taboo - a particular topic is forbidden and " +
                                    "cannot be discussed. Characters who unwittingly mention this " +
                                    "topic  will be ostracised. ";
                            break;
                        case 2:
                            planetoid.Description += "Deceptive - trickery and equivocation are " +
                                    "considered acceptable. Honesty is a sign of weakness. ";
                            break;
                        case 3:
                            planetoid.Description += "Liberal - the culture welcomes change and " +
                                    "offworld influence. Characters who bring new and strange ideas " +
                                    "will be welcomed. ";
                            break;
                        case 4:
                            planetoid.Description += "Honourable - one's word is one's bond in the " +
                                    "culture. Lying is both rare and despised. ";
                            break;
                        case 5:
                            planetoid.Description += "Influenced - the cultrue is heavily influenced" +
                                    " by another, neighbouring world. If you have the details for " +
                                    "the neighbouring world, choose a cultural quirk that this world" +
                                    " has adopted. If not roll for one. ";
                            //TODO: If adjacent to another planet, use their major culture.
                            break;
                        case 6:
                            planetoid.Description += "Fusion - the culture is heavily influenced by " +
                                    "another, neighbouring world. If  you have the details for the " +
                                    "neighbouring world, choose a cultural quick that this world has " +
                                    "adopted. If not, roll for one. ";
                            //TODO: Neighbor detection or roll again?
                            break;
                    }
                    break;
                case 3:
                    switch(dieRoll){
                        case 1:
                            planetoid.Description += "Barbaric - physical strength and combat prowess" +
                                    " are highly valued in the culture. Characters may be challenged " +
                                    "to a fight, or dismissed if they seem incapable of defending " +
                                    "themselves. Sports tend towards bloody and violent. ";
                            break;
                        case 2:
                            planetoid.Description += "Remnant - the culture is surviving remnant of " +
                                    "a once-great and vibrant civilisation, clinging to its former glory. The world " +
                                    "is filled with crumbling ruins, and every story revolves around " +
                                    "the good old days. ";
                            break;
                        case 3:
                            planetoid.Description += "Degenerate – the culture is falling apart and is on the brink of " +
                                    "war or economic collapse. Violent protests are common and " +
                                    "the social order is decaying. ";
                            break;
                        case 4:
                            planetoid.Description += "Progressive – the culture is expanding and vibrant. Fortunes" +
                                    " are being made in trade; science is forging bravely ahead. ";
                            break;
                        case 5:
                            planetoid.Description += "Recovering – a recent trauma, such as a plague, war, disaster or " +
                                    "despotic regime has left scars on the culture. ";
                            break;
                        case 6:
                            planetoid.Description += " ";
                            break;
                    }
                    break;
                case 4:
                    switch(dieRoll){
                        case 1:
                            planetoid.Description += " ";
                            break;
                        case 2:
                            planetoid.Description += " ";
                            break;
                        case 3:
                            planetoid.Description += " ";
                            break;
                        case 4:
                            planetoid.Description += " ";
                            break;
                        case 5:
                            planetoid.Description += " ";
                            break;
                        case 6:
                            planetoid.Description += " ";
                            break;
                    }
                    break;
                case 5:
                    switch(dieRoll){
                        case 1:
                            planetoid.Description += " ";
                            break;
                        case 2:
                            planetoid.Description += " ";
                            break;
                        case 3:
                            planetoid.Description += " ";
                            break;
                        case 4:
                            planetoid.Description += " ";
                            break;
                        case 5:
                            planetoid.Description += " ";
                            break;
                        case 6:
                            planetoid.Description += " ";
                            break;
                    }
                    break;
                case 6:
                    switch(dieRoll){
                        case 1:
                            planetoid.Description += " ";
                            break;
                        case 2:
                            planetoid.Description += " ";
                            break;
                        case 3:
                            planetoid.Description += " ";
                            break;
                        case 4:
                            planetoid.Description += " ";
                            break;
                        case 5:
                            planetoid.Description += " ";
                            break;
                        case 6:
                            planetoid.Description += " ";
                            break;
                    }
                    break;
            }
        }

        private void createPlanetLaw(Planetoid planetoid){
            Log.d(TAG, "createPlanetLaw Called!");
            if(planetoid.PopRoll == 0){
                planetoid.Law = "None";
                planetoid.Description += "\n";
            }else{
                //2d6-7+GovRoll
                dieRoll = rollDie(5,-5) + GovRoll;
            }
        }

        private void createPlanetTech(Planetoid planetoid){
            Log.d(TAG, "createPlanetTech Called!");
            if(planetoid.PopRoll == 0){
                planetoid.Tech = "None";
                planetoid.Description += "\n";
            }else{

            }

        }
    }
}
