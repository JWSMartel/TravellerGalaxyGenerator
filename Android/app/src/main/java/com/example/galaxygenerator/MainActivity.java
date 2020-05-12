package com.example.galaxygenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Galaxy Creator";
    private TextView output;
    private int dieRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.descriptionText);
        Button makePlanetBtn = findViewById(R.id.makePlanet);
        makePlanetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Planetoid newPlanet = new Planetoid();
                Log.d(TAG, "Make Planet clicked!");
                output.setText(newPlanet.printOut);
            }
        });
        Button makeGalaxyBtn = findViewById(R.id.makeGalaxy);
        makeGalaxyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Galaxy Clicked!");
                output.setText(makeGalaxy());
            }
        });
        Button clearBtn = findViewById(R.id.ClearBtn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clear Clicked!");
                output.setText("");
            }
        });
    }

    /**
     * @param max - Highest value possible
     * @param min - Lowest value possible
     * @return - Result of die roll
     */
    public int rollDie(int max, int min){
        if(min>=max){
            Log.e(TAG, "Min is greater than max!!");
            return 10000;
        }
        return (int) (Math.random()*((max-min)+1))+min;
    }

    private class Planetoid{

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
        private int LawRoll;
        private String Starport;
        private int Tech;
        private String Bases;
        private String TradeCodes;
        private String SurvivalGearReq;
        private int SizeRoll;
        private int AtmoRoll;
        private int HydroRoll;
        private int PopRoll;
        private int GovRoll;
        private int CultureTensRoll;
        private int CultureOnesRoll;
        private String printOut;

        private Planetoid(){
            Log.d(TAG, "Planet starts being made!");
            createStarport();
            createPlanetSize();
            createPlanetAtmo();
            createPlanetTemp();
            createPlanetHydro();
            createPlanetPop();
            createPlanetGov();
            createPlanetLaw();
            createPlanetTech();
            Name += Bases;
            checkTradeCodes();
            Log.d(TAG, Name);
            printOut = NAME+Name+STARPORTQUALITY+Starport+SIZE+Size+ATMOTYPE+Atmo+TEMP+
                    Temp+HYDROPER+Hydro+POP+Pop+GOVTYPE+Govt+LAW+LawRoll+TECHLV+Tech+Description;
        }

        private void createStarport(){
            dieRoll = rollDie(12, 2);
            Log.d(TAG, "Starport: "+dieRoll);
            if(dieRoll <= 2){
                Starport = "X";
            }else if(dieRoll <5){
                Starport = "E";
            }else if(dieRoll<7){
                Starport = "D";
            }else if(dieRoll<9){
                Starport = "C";
            }else if(dieRoll<11){
                Starport = "B";
            }else{
                Starport = "A";
            }
            rollForBases();
            Name = Starport;
        }

        private void rollForBases( ){
            Bases = "   ";
            switch (Starport){
                case "A":
                    if(checkRollStd(8)){
                        Bases  += "N";
                    }
                    if(checkRollStd(10)){
                        Bases  += "S";
                    }
                    if(checkRollStd(8)){
                        Bases  += "R";
                    }
                    if(checkRollStd(4)){
                        Bases  += "T";
                    }
                    if(checkRollStd(6)){
                        Bases  += "I";
                    }
                    break;
                case "B":
                    if(checkRollStd(8)){
                        Bases  += "N";
                    }
                    if(checkRollStd(8)){
                        Bases  += "S";
                    }
                    if(checkRollStd(10)){
                        Bases  += "R";
                    }
                    if(checkRollStd(6)){
                        Bases  += "T";
                    }
                    if(checkRollStd(8)){
                        Bases  += "I";
                    }
                    if(checkRollStd(12)){
                        Bases  += "P";
                    }
                    break;
                case "C":
                    if(checkRollStd(8)){
                        Bases  += "S";
                    }
                    if(checkRollStd(10)){
                        Bases  += "R";
                    }
                    if(checkRollStd(10)){
                        Bases  += "T";
                    }
                    if(checkRollStd(10)){
                        Bases  += "I";
                    }
                    if(checkRollStd(10)){
                        Bases  += "P";
                    }
                    break;
                case "D":
                    if(checkRollStd(7)){
                        Bases  += "S";
                    }
                    if(checkRollStd(12)){
                        Bases  += "P";
                    }
                    break;
                case "E":
                    if(checkRollStd(12)){
                        Bases  += "P";
                    }
                    break;
            }
        }

        private boolean checkRollStd(int target){
            return rollDie(12, 2) >= target;
        }

        private void createPlanetSize( ){
            Log.d(TAG, "createPlanetSize Called");
            SizeRoll = rollDie(10,0);
            if(SizeRoll == 10){
                Name += "A";
            }else{
                Name += SizeRoll;
            }
            switch (SizeRoll) {
                case 0:
                    Log.d(TAG, "Die roll is 0");
                    Size = "800 km";
                    Gs = "Negligible";
                    Description += "Size of an Asteroid or orbital complex ";
                    break;
                case 1:
                    Log.d(TAG, "Die roll is 1");
                    Size = "1,600 km";
                    Gs = "005";
                    break;
                case 2:
                    Log.d(TAG, "Die roll is 2");
                    Size = "3,200 km";
                    Gs = "015";
                    Description += "Size of Triton, Luna, or Europa ";
                    break;
                case 3:
                    Log.d(TAG, "Die roll is 3");
                    Size = "4,800 km";
                    Gs = "025";
                    Description += "Size of Mercury or Ganymede ";
                    break;
                case 4:
                    Log.d(TAG, "Die roll is 4");
                    Size = "6,400 km";
                    Gs = "035";
                    Description += "Size of Mars ";
                    break;
                case 5:
                    Log.d(TAG, "Die roll is 5");
                    Size = "8,000 km";
                    Gs = "045";
                    break;
                case 6:
                    Log.d(TAG, "Die roll is 6");
                    Size = "9,600 km";
                    Gs = "07";
                    break;
                case 7:
                    Log.d(TAG, "Die roll is 7");
                    Size = "11,200 km";
                    Gs = "09";
                    break;
                case 8:
                    Log.d(TAG, "Die roll is 8");
                    Size = "12,800 km";
                    Gs = "10";
                    Description += "Size of Earth ";
                    break;
                case 9:
                    Log.d(TAG, "Die roll is 9");
                    Size = "14,400 km";
                    Gs = "125";
                    break;
                case 10:
                    Log.d(TAG, "Die roll is 10");
                    Size = "16,000 km";
                    Gs = "14";
                    break;
                default:
                    Log.e(TAG, "Something went wrong in createPlanetSize");
                    break;
            }
        }

        private void createPlanetAtmo( ){
            Log.d(TAG, "createPlanetAtmo Called!");
            AtmoRoll = rollDie(5, -5) + SizeRoll;
            if(AtmoRoll < 1){
                //No Atmo
                Name += "0";
                Atmo = "None";
                Description += "Moon like atmosphere ";
                Pressure = "00";
                SurvivalGearReq = "Vacc Suit";
            }else{
                if(AtmoRoll<10){
                    Name += AtmoRoll;
                }
                switch(AtmoRoll){
                    case 1:
                        Atmo = "Trace";
                        Description += "Mars like atmosphere ";
                        Pressure = "0001-009";
                        SurvivalGearReq = "Vacc Suit";
                        break;
                    case 2:
                        Atmo = "Very Thin, Tainted";
                        Pressure = "01-042";
                        SurvivalGearReq = "Respirator, Filter";
                        break;
                    case 3:
                        Atmo = "Very Thin";
                        Pressure = "01-042";
                        SurvivalGearReq = "Respirator";
                        break;
                    case 4:
                        Atmo = "Thin, Tainted";
                        Pressure = "043-07";
                        SurvivalGearReq = "Filter";
                        break;
                    case 5:
                        Atmo = "Thin";
                        Pressure = "043-07";
                        break;
                    case 6:
                        Atmo = "Standard";
                        Description += "Earth like atmosphere ";
                        Pressure = "071-149";
                        break;
                    case 7:
                        Atmo = "Standard, Tainted";
                        Pressure = "071-149";
                        SurvivalGearReq = "Filter";
                        break;
                    case 8:
                        Atmo = "Dense";
                        Pressure = "15-249";
                        break;
                    case 9:
                        Atmo = "Dense, Tainted";
                        Pressure = "15-249";
                        SurvivalGearReq = "Filter";
                        break;
                    case 10:
                        Name += "A";
                        Atmo = "Exotic";
                        Description += "Atmosphere is unbreathable to humans but otherwise" +
                                " not hazardous ";
                        Pressure = "Varies";
                        SurvivalGearReq = "Air Supply";
                        break;
                    case 11:
                        Name += "B";
                        Atmo = "Corrosive";
                        Description += "Venus like atmosphere Highly dangerous: anyone" +
                                " who breathes this corrosive atmosphere will suffer 1d6 damage " +
                                "per round ";
                        Pressure = "Varies";
                        SurvivalGearReq = "Vacc Suit";
                        break;
                    case 12:
                        Name += "C";
                        Atmo = "Insidious";
                        Pressure = "Varies";
                        SurvivalGearReq = "Vacc Suit";
                        Description += "Unsafe to be there for longer than "
                                + rollDie(12,2) + " hours due to degradation of equipment" +
                                " as it deals with the harsh atmosphere This can be prevented with" +
                                " vigilant maintenance or advanced protective gear ";
                        break;
                    case 13:
                        Name += "D";
                        Atmo = "Dense, High";
                        Pressure = "25+";
                        break;
                    case 14:
                        Name += "E";
                        Atmo = "Thin, Low";
                        Pressure = "05 or less";
                        break;
                    case 15:
                        Name += "F";
                        Atmo = "Unusual";
                        Pressure = "Varies";
                        SurvivalGearReq = "Varies";
                        break;
                    default:
                        Log.e(TAG, "Something went wrong in Atmo creation");
                }
                if (Description.contains("Tainted")){
                    Description += "Breathing this atmosphere causes 1d6 damage every few minutes";
                }
            }

    }

        private void createPlanetTemp( ){
            Log.d(TAG, "createPlanetTemp Called!");
            dieRoll = rollDie(12,2);
            Log.d(TAG, "Temp Die: "+dieRoll);
            Log.d(TAG, "Atmo Roll: "+AtmoRoll);
            if(AtmoRoll<2){
                Description += "Temperatures swing wildly from roasting during the day " +
                        "to freezing at night ";
            }else if(AtmoRoll == 2||AtmoRoll == 3){
                dieRoll -= 2;
            }else if(AtmoRoll == 4||AtmoRoll == 5 || AtmoRoll == 14) {
                dieRoll -= 1;
            }else if(AtmoRoll == 8||AtmoRoll == 9){
                dieRoll += 1;
            }else if(AtmoRoll == 10||AtmoRoll == 13||AtmoRoll == 15){
                dieRoll += 2;
            }else if(AtmoRoll == 11||AtmoRoll == 12){
                dieRoll += 6;
            }
            Log.d(TAG, "Temp Prog: "+dieRoll);

            if(dieRoll <3){
                Temp = "Frozen: >-51\u2103";
                Description += "Frozen world No liquid water, very dry atmostphere ";
            }else if(dieRoll == 3||dieRoll == 4){
                Temp = "Cold: -51-0\u2103";
                Description += "Icy world Little liquid water, extensive ice caps, few clouds ";
            }else if(dieRoll<10){
                Log.d(TAG, "Planet Temperate");
                Temp = "Temperate: 0-90\u2103";
                Description += "Temperate world Earthlike Liquid and vaporised water are" +
                        " common, moderate ice caps ";
            }else if(dieRoll == 10||dieRoll == 11){
                Temp = "Hot: 31-80\u2103";
                Description += "Hot world Small or no ice caps, little liquid water" +
                        " Most water in the form of clouds ";
            }else{
                Temp = "Roasting: 81\u2103+";
                Description += "Boiling world No ice caps, little liquid water ";
            }
            Log.d(TAG, "Planet Temp in Temp: "+Temp);
        }

        private void createPlanetHydro( ){
            Log.d(TAG, "createPlanetHydro Called!");
            if(SizeRoll<2){
                Log.d(TAG, " to small for water!");
                Hydro = "0";
                HydroRoll = 0;
            }else{
                //2d6-7+Size
                HydroRoll = rollDie(5,-5) + SizeRoll;
                Log.d(TAG, "Die Roll in Hydro: "+dieRoll);
                if(AtmoRoll == 0||AtmoRoll == 1||(AtmoRoll>9&&AtmoRoll<13)){
                    HydroRoll -=4;
                }
                if(Temp.contains("Hot")){
                    HydroRoll -= 2;
                }else if(Temp.contains("Roasting")){
                    HydroRoll -= 6;
                }

                if(HydroRoll<1){
                    Name += "0";
                    Hydro = "0-5%";
                    Description += "Desert world ";
                }else if(HydroRoll>10){
                    Name += "A";
                    Hydro = "96-100%";
                    Description += "Almost entirely water ";
                }else{
                    Name += HydroRoll;
                    switch (HydroRoll){
                        case 1:
                            Hydro = "6-15%";
                            Description += "Dry world ";
                            break;
                        case 2:
                            Hydro = "16-25%";
                            Description += "A few small seas ";
                            break;
                        case 3:
                            Hydro = "26-35%";
                            Description += "Small seas and oceans ";
                            break;
                        case 4:
                            Hydro = "36-45%";
                            Description += "Wet world ";
                            break;
                        case 5:
                            Hydro = "46-55%";
                            Description += "Large oceans ";
                            break;
                        case 6:
                            Hydro = "56-65%";
                            break;
                        case 7:
                            Hydro = "66-75%";
                            Description += "Earthlike amount of water ";
                            break;
                        case 8:
                            Hydro = "76-85%";
                            Description += "Water world ";
                            break;
                        case 9:
                            Hydro = "86-95%";
                            Description += "Only a few small islands and archipelagos ";
                            break;
                        default:
                            Log.e(TAG, "Something went wrong in the Hydro Switch");
                            break;
                    }
                }
            }
        }

        private void createPlanetPop( ){
            Log.d(TAG, "createPlanetPop Called!");
            PopRoll = rollDie(10,0);
            Log.d(TAG, "Pop Roll: "+PopRoll);
            if(PopRoll == 10){
                Name += "A";
                Pop = "Tens of billions";
                Description += "Population of tens of billions ";
            }else{
                Name += PopRoll;
                switch (PopRoll){
                    case 0:
                        Pop = "None";
                        Description += "Uninhabited ";
                        break;
                    case 1:
                        Pop = "Few";
                        Description += "Population consisting of a tiny farmstead" +
                                " or a large family ";
                        break;
                    case 2:
                        Pop = "Hundreds";
                        Description += "Population consisting of a village ";
                        break;
                    case 3:
                        Pop = "Thousands";
                        Description += "Population of thousands ";
                        break;
                    case 4:
                        Pop = "Tens of thousands";
                        Description += "Population consisting of a small town ";
                        break;
                    case 5:
                        Pop = "Hundreds of thousands";
                        Description += "Population consisting of an average city ";
                        break;
                    case 6:
                        Pop = "Millions";
                        Description += "Population of millions ";
                        break;
                    case 7:
                        Pop = "Tens of millions";
                        Description += "Population consisting of a large city ";
                        break;
                    case 8:
                        Pop = "Hundreds of millions";
                        Description += "Population of hundreds of millions ";
                        break;
                    case 9:
                        Pop = "Billions";
                        Description += "Population similar to present day Earth ";
                        break;
                    default:
                        Log.e(TAG, "Something went wrong in planet pop generator");
                        break;
                }
            }
        }

        private void createPlanetGov( ){
            Log.d(TAG, "createPlanetGov Called!");
            if(PopRoll == 0){
                Govt = "None";
                Description += "No government structure In many cases, family bonds" +
                        "predominate Examples include a Family, Clan, or total Anarchy Little" +
                        " could be considered illegal here ";
            }else{
                GovRoll = rollDie(5,-5) + PopRoll;
                Log.d(TAG, "GovRoll: "+ GovRoll);
                if(GovRoll <= 0){
                    Govt = "None";
                    Description += "No government structure In many cases, family bonds" +
                            "predominate Examples include a Family, Clan, or total Anarchy Little" +
                            " could be considered illegal here ";
                    Name += "0";
                }else if(GovRoll >= 13){
                    Govt = "Religious Dictatorship";
                    Description += "Ruling functions are " +
                            "performed by a religious organisation without regard to the specific " +
                            "individual needs of the citizenry Examples include cults, transcendent" +
                            " philosophy, or psionic group minds Who is to say what they do or do " +
                            "not want in their sectors ";
                    Name += "D";
                }else{
                    switch (GovRoll){
                        case 1:
                            Govt = "Company/Corporation";
                            Description += "Ruling functions are assumed by a company " +
                                    "managerial elite, and most citizenry are company employees " +
                                    "or dependants Examples include a corporate outpost, asteroid " +
                                    "mine, or a feudal domain Likely would not appreciate weapons, " +
                                    "drugs, or Travellers in their space ";
                            break;
                        case 2:
                            Govt = "Participating Democracy";
                            Description += "Ruling functions are reached by the advise " +
                                    "and consent of the citizenry directly Examples include a " +
                                    "collective, tribal council, or comm-linked consensus Such " +
                                    "civilizations tend to be weary of drugs ";
                            break;
                        case 3:
                            Govt = "Self-perpetuating Oligarchy";
                            Description += "Ruling functions are preformed by a restricted" +
                                    " minority, with little to no input from the mass of the " +
                                    "citizenry Exmaples include a plutocracy or a hereditary ruling" +
                                    " caste They do no tend to like weapons, technology, or Travellers ";
                            break;
                        case 4:
                            Govt = "Represenative Democracy";
                            Description += "Ruling functions are preformed by elected " +
                                    "representatives Examples include a republic or democracy " +
                                    "Likely would not appreciate drugs, weapons, or psionics";
                            break;
                        case 5:
                            Govt = "Feudal Techocracy";
                            Description += "Ruling functions are preformed by specific " +
                                    "individuals for persons who agree to be ruled by them " +
                                    "Relationships are based on the performance of technical " +
                                    "activities which are mutually beneficialThey do not tend to " +
                                    "like outside weapons, technology, or computers ";
                            break;
                        case 6:
                            Govt = "Captive Government";
                            Description += "Ruling functions are performed by an imposed " +
                                    "leadership answerable to an outside group Examples include a " +
                                    "colony or conquered area Contraband here may include weapons, " +
                                    "technology, or Travellers ";
                            break;
                        case 7:
                            Govt = "Balkanisation";
                            Description += "No central authority exists rival governments" +
                                    " complete for control Law Level refers to the government " +
                                    "nearest the starport Examples include multiple governments " +
                                    "involved in a civil war They could have an aversion to anything ";
                            break;
                        case 8:
                            Govt = "Civil Service Bureaucracy";
                            Description += "Ruling functions are performed by government " +
                                    "agencies employing individuals selected for their expertise " +
                                    "Examples include a technocracy or communism They likely look " +
                                    "down on drugs and weapons ";
                            break;
                        case 9:
                            Govt = "Impersonal Bureaucracy";
                            Description += "Ruling functions are performed by agencies " +
                                    "which have become insulated from the governed citizens Examples " +
                                    "include entrenched castes of bureaucrats or a decaying empire " +
                                    "They do like technology, weapons, drugs, Travellers, or " +
                                    "psionics typically ";
                            break;
                        case 10:
                            Govt = "Charismatic Dictator";
                            Description += "Ruling functions are performed by agencies " +
                                    "directed by a single leader who enjoys the overwhelming " +
                                    "confidence of the citizens Examples include a revolutionary " +
                                    "leader, messiah, or emperor Only the dictator can decide what " +
                                    "is illegal here ";
                            Name += "A";
                            break;
                        case 11:
                            Govt = "Non-charismatic Leader";
                            Description += "A previous charismatic dictator " +
                                    "has been replaced by a leader through normal channels Examples include" +
                                    " military dictatorship or hereditary kingship They do not appreciate " +
                                    "weapons, technology, or computers from other lands ";
                            Name += "B";
                            break;
                        case 12:
                            Govt = "Charismatic Oligarchy";
                            Description += "Ruling functions are performed" +
                                    " by a select group of members of an organisation or class which enjoys " +
                                    "the overwhelming confidence of the citizenry Examples include junta, " +
                                    "or a revolutionary council They tend not to appreciate weapons around them ";
                            Name += "C";
                            break;
                        default:
                            Log.e(TAG, "Something went wrong in Primary Gov Assign Switch");
                    }
                    if(GovRoll<10){
                        Name += GovRoll;
                    }
                    rollCulture();

                    //Decide number of factions
                    int factionRoll = rollDie(3,1);
                    if(GovRoll == 0||GovRoll == 7){
                         factionRoll += 1;
                    }else if(GovRoll > 10){
                        factionRoll -= 1;
                    }

                    //Make a faction for each
                    if(factionRoll > 0){
                        Description += "\n\nThere are "+factionRoll+" other factions on the " +
                                "planet as well ";
                        for(int f=1;f<=factionRoll;f++){
                            Description += "\n\nSplinter faction #"+f+": ";
                            rollFactionGov();
                        }
                    }
                }
            }
        }

        private void rollFactionGov( ){
            int factionGovRoll = rollDie(13,0);
            switch (factionGovRoll){
                case 1:
                    Description += "Company/Corporation: Ruling functions are assumed by a company " +
                            "managerial elite, and most citizenry are company employees " +
                            "or dependants Examples include a corporate outpost, asteroid " +
                            "mine, or a feudal domain Likely would not appreciate weapons, " +
                            "drugs, or Travellers in their space ";
                    break;
                case 2:
                    Description += "Participating Democracy: " +
                            "Ruling functions are reached by the advise " +
                            "and consent of the citizenry directly Examples include a " +
                            "collective, tribal council, or comm-linked consensus Such " +
                            "civilizations tend to be weary of drugs ";
                    break;
                case 3:
                    Description += "Self-perpetuating Oligarchy: " +
                            "Ruling functions are preformed by a restricted" +
                            " minority, with little to no input from the mass of the " +
                            "citizenry Exmaples include a plutocracy or a hereditary ruling" +
                            " caste They do no tend to like weapons, technology, or Travellers ";
                    break;
                case 4:
                    Description += "Representative Democracy: " +
                            "Ruling functions are preformed by elected " +
                            "representatives Examples include a republic or democracy " +
                            "Likely would not appreciate drugs, weapons, or psionics";
                    break;
                case 5:
                    Description += "Feudal Technocracy: " +
                            "Ruling functions are preformed by specific " +
                            "individuals for persons who agree to be ruled by them " +
                            "Relationships are based on the performance of technical " +
                            "activities which are mutually beneficialThey do not tend to " +
                            "like outside weapons, technology, or computers ";
                    break;
                case 6:
                    Description += "Captive Government: " +
                            "Ruling functions are performed by an imposed " +
                            "leadership answerable to an outside group Examples include a " +
                            "colony or conquered area Contraband here may include weapons, " +
                            "technology, or Travellers ";
                    break;
                case 7:
                    Description += "Balkanisation: No central authority exists rival governments" +
                            " complete for control Law Level refers to the government " +
                            "nearest the starport Examples include multiple governments " +
                            "involved in a civil war They could have an aversion to anything ";
                    break;
                case 8:
                    Description += "Civil Service Bureaucracy: " +
                            "Ruling functions are performed by government " +
                            "agencies employing individuals selected for their expertise " +
                            "Examples include a technocracy or communism They likely look " +
                            "down on drugs and weapons ";
                    break;
                case 9:
                    Description += "Impersonal Bureaucracy: " +
                            "Ruling functions are performed by agencies " +
                            "which have become insulated from the governed citizens Examples " +
                            "include entrenched castes of bureaucrats or a decaying empire " +
                            "They do like technology, weapons, drugs, Travellers, or " +
                            "psionics typically ";
                    break;
                case 10:
                    Description += "Charismatic Dictator : " +
                            "Ruling functions are performed by agencies " +
                            "directed by a single leader who enjoys the overwhelming " +
                            "confidence of the citizens Examples include a revolutionary " +
                            "leader, messiah, or emperor Only the dictator can decide what " +
                            "is illegal here ";
                    break;
                case 11:
                    Description += "Non-charismatic Leader : A previous charismatic dictator" +
                            " has been replaced by a leader through normal channels Examples include" +
                            " military dictatorship or hereditary kingship They do not appreciate " +
                            "weapons, technology, or computers from other lands ";
                    break;
                case 12:
                    Description += "Charismatic oligarchy : Ruling functions are performed" +
                            " by a select group of members of an organisation or class which enjoys " +
                            "the overwhelming confidence of the citizenry Examples include junta, " +
                            "or a revolutionary council They tend not to appreciate weapons around them ";
                    break;
                default:
                    Description += "Religious Dictatorship : Ruling functions are " +
                            "performed by a religious organisation without regard to the specific " +
                            "individual needs ot the citizenry Examples include cults, transcendent" +
                            " philosophy, or psionic group minds Who is to say what they do or do " +
                            "not want in their sectors ";
                    break;
            }
            if(factionGovRoll == GovRoll){
                Description += "This is a splinter faction within the ruling government ";
            }
            int factionStrengthRoll = rollDie(12,1);
            Description += "\n\nFaction strength: "+factionStrengthRoll+" - ";
            if(factionStrengthRoll < 4){
                Description += "This faction is an obscure group Few have heard of them " +
                        "and they have no public support ";
            }else if(factionStrengthRoll < 6){
                Description += "This is a fringe group There are few supporters throughout" +
                        " the populace ";
            }else if(factionStrengthRoll < 8){
                Description += "This is a minor group with some supporters among the populace ";
            }else if(factionStrengthRoll < 10){
                Description += "This is a notable group here with a few significant and " +
                        "well known members among its members, openly or otherwise ";
            }else if(factionStrengthRoll < 12){
                Description += "This faction is significant and could rival the primary government ";
            }else{
                Description += "This is an overwhelmingly popular faction, certainly with " +
                        "more, if less than official, power than the primary government ";
            }
        }

        private void rollCulture( ){
            Description += "\n\n";
            CultureTensRoll = rollDie(6,1);
            CultureOnesRoll = rollDie(6,1);
            switch (CultureTensRoll){
                case 1:
                    switch(CultureOnesRoll){
                        case 1:
                            Description += "Sexist - one gender is considered subservient or inferior to the other ";
                            break;
                        case 2:
                            Description += "Religious - culture is heavily influenced by a religion or belief system, possibly one unique to this world ";
                            break;
                        case 3:
                            Description += "Artistic - art and culture are highly prized Aesthetic design is important in all artifacts produced onworld ";
                            break;
                        case 4:
                            Description += "Ritualised - social interaction and trade is highly formalised Politeness and adherence to traditional forms is considered very important ";
                            break;
                        case 5:
                            Description += "Conservative - the culture resists change and outside influences ";
                            break;
                        case 6:
                            Description += "Xenophobic - the culture distrusts outsiders and alien influences Offworlders will face considerable prejudice ";
                            break;
                    }
                    break;
                case 2:
                    switch(CultureOnesRoll){
                        case 1:
                            Description += "Taboo - a particular topic is forbidden and " +
                                    "cannot be discussed Characters who unwittingly mention this " +
                                    "topic  will be ostracised ";
                            break;
                        case 2:
                            Description += "Deceptive - trickery and equivocation are " +
                                    "considered acceptable Honesty is a sign of weakness ";
                            break;
                        case 3:
                            Description += "Liberal - the culture welcomes change and " +
                                    "offworld influence Characters who bring new and strange ideas " +
                                    "will be welcomed ";
                            break;
                        case 4:
                            Description += "Honourable - one's word is one's bond in the " +
                                    "culture Lying is both rare and despised ";
                            break;
                        case 5:
                            Description += "Influenced - the cultrue is heavily influenced" +
                                    " by another, neighbouring world If you have the details for " +
                                    "the neighbouring world, choose a cultural quirk that this world" +
                                    " has adopted If not roll for one ";
                            //TODO: If adjacent to another planet, use their major culture
                            break;
                        case 6:
                            Description += "Fusion - the culture is heavily influenced by " +
                                    "another, neighbouring world If  you have the details for the " +
                                    "neighbouring world, choose a cultural quick that this world has " +
                                    "adopted If not, roll for one ";
                            //TODO: Neighbor detection or roll again?
                            break;
                    }
                    break;
                case 3:
                    switch(CultureOnesRoll){
                        case 1:
                            Description += "Barbaric - physical strength and combat prowess" +
                                    " are highly valued in the culture Characters may be challenged " +
                                    "to a fight, or dismissed if they seem incapable of defending " +
                                    "themselves Sports tend towards bloody and violent ";
                            break;
                        case 2:
                            Description += "Remnant - the culture is surviving remnant of " +
                                    "a once-great and vibrant civilisation, clinging to its former glory The world " +
                                    "is filled with crumbling ruins, and every story revolves around " +
                                    "the good old days ";
                            break;
                        case 3:
                            Description += "Degenerate – the culture is falling apart and is on the brink of " +
                                    "war or economic collapse Violent protests are common and " +
                                    "the social order is decaying ";
                            break;
                        case 4:
                            Description += "Progressive – the culture is expanding and vibrant Fortunes" +
                                    " are being made in trade; science is forging bravely ahead ";
                            break;
                        case 5:
                            Description += "Recovering – a recent trauma, such as a plague, war, disaster or " +
                                    "despotic regime has left scars on the culture ";
                            break;
                        case 6:
                            Description += "Nexus – members of many different cultures and species visit " +
                                    "here ";
                            break;
                    }
                    break;
                case 4:
                    switch(CultureOnesRoll){
                        case 1:
                            Description += "Tourist Attraction – some aspect of the culture or the planet " +
                                    "draws visitors from all over charted space ";
                            break;
                        case 2:
                            Description += "Violent – physical conflict is common, taking the form of duels, " +
                                    "brawls or other contests Trial by combat is a part of their " +
                                    "judicial system ";
                            break;
                        case 3:
                            Description += "Peaceful – physical conflict is almost unheard-of The culture " +
                                    "produces few soldiers and diplomacy reigns supreme Forceful " +
                                    "characters will be ostracised ";
                            break;
                        case 4:
                            Description += "Obsessed – everyone is obsessed with or addicted to a " +
                                    "substance, personality, act or item This monomania pervades " +
                                    "every aspect of the culture ";
                            break;
                        case 5:
                            Description += "Fashion – fi ne clothing and decoration are considered vitally " +
                                    "important in the culture Underdressed characters have no " +
                                    "standing here ";
                            break;
                        case 6:
                            Description += "At war – the culture is at war, either with another planet or " +
                                    "polity, or is troubled by terrorists or rebels ";
                            break;
                    }
                    break;
                case 5:
                    switch(CultureOnesRoll){
                        case 1:
                            Description += "Unusual Custom: Offworlders – space travellers hold a unique " +
                                    "position in the culture’s mythology or beliefs, and travellers will " +
                                    "be expected to live up to these myths ";
                            break;
                        case 2:
                            Description += "Unusual Custom: Starport – the planet’s starport is more than " +
                                    "a commercial centre; it might be a religious temple, or be seen " +
                                    "as highly controversial and surrounded by protestors ";
                            break;
                        case 3:
                            Description += "Unusual Custom: Media – news agencies and " +
                                    "telecommunications channels are especially strange here " +
                                    "Getting accurate information may be difficult ";
                            break;
                        case 4:
                            Description += "Unusual Customs: Technology – the culture interacts with " +
                                    "technology in an unusual way Telecommunications might " +
                                    "be banned, robots might have civil rights, cyborgs might be " +
                                    "property ";
                            break;
                        case 5:
                            Description += "Unusual Customs: Lifecycle – there might be a mandatory age " +
                                    "of termination, or anagathics might be widely used Family " +
                                    "units might be different, with children being raised by the state " +
                                    "or banned in favour of cloning ";
                            break;
                        case 6:
                            Description += "Unusual Customs: Social Standings – the culture has a distinct " +
                                    "caste system Characters of a low social standing who do not " +
                                    "behave appropriately will face punishment ";
                            break;
                    }
                    break;
                case 6:
                    switch(CultureOnesRoll){
                        case 1:
                            Description += "Unusual Customs: Trade – the culture has an odd attitude " +
                                    "towards some aspect of commerce, which may interfere with " +
                                    "trade at the spaceport For example, merchants might expect " +
                                    "a gift as part of a deal, or some goods may only be handled by " +
                                    "certain families ";
                            break;
                        case 2:
                            Description += "Unusual Customs: Nobility – those of high social standing " +
                                    "have a strange custom associated with them; perhaps nobles " +
                                    "are blinded, or must live in gilded cages, or only serve for a " +
                                    "single year before being exiled ";
                            break;
                        case 3:
                            Description += "Unusual Customs: Sex – the culture has an unusual attitude " +
                                    "towards intercourse and reproduction Perhaps cloning is used " +
                                    "instead, or sex is used to seal commercial deals ";
                            break;
                        case 4:
                            Description += "Unusual Customs: Eating – food and drink occupies an " +
                                    "unusual place in the culture Perhaps eating is a private affair, " +
                                    "or banquets and formal dinners are seen as the highest form " +
                                    "of politeness ";
                            break;
                        case 5:
                            Description += "Unusual Customs: Travel – travellers may be distrusted or " +
                                    "feted, or perhaps the culture frowns on those who leave their " +
                                    "homes ";
                            break;
                        case 6:
                            Description += "Unusual Custom: Conspiracy – something strange is going " +
                                    "on The government is being subverted by another group or " +
                                    "agency ";
                            break;
                    }
                    break;
            }
        }

        private void createPlanetLaw( ){
            Log.d(TAG, "createPlanetLaw Called!");
            if(PopRoll == 0){
                LawRoll = 0;
            }else{
                LawRoll = rollDie(5,-5) + GovRoll;
                Log.d(TAG, "Law Roll: "+LawRoll);
                if(LawRoll<=0){
                    LawRoll = 0;
                    Description += "There are no legal restrictions here ";
                }
                Name += LawRoll;
            }
        }

        private void createPlanetTech( ){
            Log.d(TAG, "createPlanetTech Called!");
            if(PopRoll == 0){
                Tech = 0;
            }else{
                dieRoll = rollDie(6,1);
                switch (Starport){
                    case "A":
                        dieRoll += 6;
                        break;
                    case "B":
                        dieRoll += 4;
                        break;
                    case "C":
                        dieRoll += 2;
                        break;
                    case "X":
                        dieRoll -= 4;
                        break;
                    default:
                        Log.d(TAG, "Starport is not ABCX");
                        break;
                }
                if(SizeRoll < 2){
                    dieRoll += 2;
                }else if(SizeRoll < 5){
                    dieRoll += 1;
                }else{
                    Log.d(TAG, "Size Roll was greater than 4");
                }
                if(AtmoRoll<4||AtmoRoll>9){
                    dieRoll += 1;
                }
                if(HydroRoll <= 0||HydroRoll == 9){
                    dieRoll += 1;
                }else if(HydroRoll == 10){
                    dieRoll += 2;
                }
                if((PopRoll>0&&PopRoll<6)||PopRoll == 9){
                    dieRoll += 1;
                }else if(PopRoll > 9){
                    switch (PopRoll){
                        case 10:
                            dieRoll += 2;
                            break;
                        case 11:
                            dieRoll += 3;
                            break;
                        case 12:
                            dieRoll += 4;
                            break;
                    }
                }
                if(GovRoll == 0 || GovRoll == 5){
                    dieRoll += 1;
                }else if(GovRoll == 7){
                    dieRoll += 2;
                }else if(GovRoll == 13||GovRoll == 14){
                    dieRoll -= 2;
                }
                Log.d(TAG, "Die Roll after first Tech mods: "+dieRoll);

                //Minimum Tech levels for certain atmos
                if(dieRoll<11){
                    if(AtmoRoll == 12){
                        dieRoll = 10;
                    }else if(dieRoll < 9 &&AtmoRoll == 11){
                        dieRoll = 9;
                    }else if(dieRoll < 8 &&
                            (AtmoRoll < 2||
                                    AtmoRoll == 10||
                                    AtmoRoll == 15)){
                            dieRoll = 8;
                    }else if(dieRoll<5 &&AtmoRoll < 4){
                        dieRoll = 5;
                    }
                    else if(dieRoll < 3 &&
                            (AtmoRoll == 4||
                                    AtmoRoll == 7||
                                    AtmoRoll == 9)){
                        dieRoll = 3;
                    }
                }

                Tech = dieRoll;
                Name += "-"+Tech;
            }
        }

        private void checkTradeCodes( ){
            Name += "  ";
            if((AtmoRoll>=4&&AtmoRoll<=9)&&(HydroRoll>=4&&HydroRoll<=8)&&(PopRoll>=5&&PopRoll<=7)){
                Name += "Ag";
                Description += "\n\nAgricultural worlds are dedicated " +
                        "to farming and food production. " +
                        "Often, they are divided into vast " +
                        "semi-feudal estates. ";
            }
            if(SizeRoll == 0 && AtmoRoll == 0 && HydroRoll == 0){
                Name += "As";
                Description += "\n\nAsteroids are usually mining " +
                        "colonies, but can also be orbital " +
                        "factories or colonies. ";
            }
            if(PopRoll == 0 && GovRoll == 0 && LawRoll == 0){
                Name += "Ba";
                Description += "\n\nBarren worlds are uncolonised and empty. ";
            }
            if(AtmoRoll>=2&&HydroRoll==0){
                Name += "De";
                Description += "\n\nDesert worlds are dry and barely habitable. ";
            }
            if(AtmoRoll>=10&&HydroRoll>=1){
                Name += "Fl";
                Description += "\n\nFluid Oceans are worlds where the surface liquid is something " +
                        "other than water, and so are incompatible with Earth-derived life. ";
            }
            if(SizeRoll>=5&&(AtmoRoll>=4&&AtmoRoll<=9)&&(HydroRoll>=4&&HydroRoll<=8)){
                Name += "Ga";
                Description += "Garden worlds are Earth-like.\n\n";
            }
            if(PopRoll>=9){
                Name += "Hi";
                Description += "\n\nHigh Population worlds have a population in the billions. ";
            }
            if(Tech>=12){
                Name += "Ht";
                Description += "\n\nHigh Technology worlds are among the most technologically " +
                        "advanced in the Imperium. ";
            }
            if(AtmoRoll<2&&HydroRoll>=1){
                Name += "IC";
                Description += "\n\nIce-Capped worlds have most of their surface liquid frozen in polar " +
                        "ice caps, and are cold and dry. ";
            }
            if((AtmoRoll<3||AtmoRoll==4||AtmoRoll==7||AtmoRoll==9)&&PopRoll>=9){
                Name += "In";
                Description += "\n\nIndustrial worlds are dominated by factories and cities. ";
            }
            if(PopRoll>0&&PopRoll<4){
                Name += "Lo";
                Description += "\n\nLow Population worlds have a population of only a few " +
                        "thousand or less. ";
            }
            if(Tech<=5){
                Name += "Lt";
                Description += "\n\nLow Technology worlds are preindustrial and cannot produce " +
                        "advanced goods.";
            }
            if(AtmoRoll<4&&HydroRoll<4&&PopRoll>=6){
                Name += "Na";
                Description += "\n\nNon-Agricultural worlds are too dry or barren to support their " +
                        "populations using conventional food production. ";
            }
            if(PopRoll>=4&&PopRoll<=6){
                Name += "NI";
                Description += "\n\nNon-Industrial worlds are too low-population to maintain an " +
                        "industrial base. ";
            }
            if((AtmoRoll>=2&&AtmoRoll<=5)&&HydroRoll<4){
                Name += "Po";
                Description += "\n\nPoor worlds lack resources, viable land or sufficient population to " +
                        "be anything other than marginal colonies. ";
            }
            if((AtmoRoll==6||AtmoRoll==8)&&(PopRoll>5&&PopRoll<9)){
                Name += "RI";
                Description += "\n\nRich worlds are blessed with a stable government and " +
                        "viable biosphere, making them economic powerhouses. ";
            }
            if(AtmoRoll==0){
                Name += "Va";
                Description += "\n\nVacuum worlds have no atmosphere. ";
            }
            if(HydroRoll == 10){
                Name += "Wa";
                Description += "\n\nWater Worlds are nearly entirely water-ocean. ";
            }
        }
    }

    public String makeGalaxy(){
        int columns = 8;
        int rows = 10;
        int hexes = columns*rows;
        String output = "";
        String hexPoint;
        for(int c = 1;c<=columns;c++){
            for(int r = 1;r<=rows;r++){
                if(rollDie(1,0) == 1){
                    //Something
                    if(r<10){
                        hexPoint = "0"+c+"0"+r;
                    }else{
                        hexPoint = "0"+c+""+r;
                    }
                    //Make planet
                    Planetoid newPlanet = new Planetoid();
                    output += hexPoint + "    " + newPlanet.Name +"\n";
                }
            }
        }
        return output;
    }
}
