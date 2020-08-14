package mk.ukim.finki.vvkn.stroopeffect;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mk.ukim.finki.vvkn.stroopeffect.db.ResultsDao;
import mk.ukim.finki.vvkn.stroopeffect.fragments.HomeFragment;
import mk.ukim.finki.vvkn.stroopeffect.fragments.ResultsFragment;
import mk.ukim.finki.vvkn.stroopeffect.fragments.SimulationFragment;
import mk.ukim.finki.vvkn.stroopeffect.fragments.InstructionFragment;
import mk.ukim.finki.vvkn.stroopeffect.models.Result;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    private ResultsDao dao;
    private List<Result> results;

    public static boolean feedback = false;

    public static final int WARPED = 0;
    public static final int EMOTION = 1;

    public static final int PRACTICE = 0;
    public static final int ACTUAL = 1;

    public static final int NEUTRAL = 0;
    public static final int CONGRUENT = 1;
    public static final int INCONGRUENT = 2;
    public static final int MIXED = 3;

    public static final int PRAC_ROUNDS = 2;
    public static final int ACT_ROUNDS = 3;

    public static Random rando = new Random();

    // 0,0,0 ; 0,0,3 ; 0,1,1 ; 0,1,2 ; 0,1,3 ;
    // 1,0,0 ; 1,0,3 ; 1,1,1 ; 1,1,2 ; 1,1,3
//    public static int STROOP_MODE; warped or emotions
//    public static int TRIALSSET_MODE; prac or actual
//    public static int TRIAL_MODE; congruent, neutral or mixed

    public static int mode = 0;
    public static int[][] MODES = new int[10][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //player = MediaPlayer.create(getApplicationContext(), R.raw.background_music);
        //player.setLooping(true);
        dao = new ResultsDao(getApplicationContext());
        results = new ArrayList<>();
        initModes();
        initHomeFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //player.start();
        try {
            dao.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //player.pause();
        dao.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //player.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void initModes(){
        // randomly select stroop mode first
        // randomly select congruent or incongruent first
        int stroop_mode = rando.nextInt(2);
        int trial_mode = rando.nextInt(2)+1;
        // 0,0,0 ; 0,0,3 ; 0,1,1 ; 0,1,2 ; 0,1,3 ;
        // 1,0,0 ; 1,0,3 ; 1,1,1 ; 1,1,2 ; 1,1,3

        // Populates 0th column
        for (int i=0; i<10;i++){
            if (stroop_mode == 0){
                if (i<5){ MODES[i][0] = stroop_mode; }
                else { MODES[i][0] = 1; }
            }
            else if (stroop_mode == 1){
                if (i<5){ MODES[i][0] = stroop_mode; }
                else { MODES[i][0] = 0; }
            }
            else {
                System.out.println("Something is wrong");
            }
        }

        // Populates 1st column
        for (int i=0; i<10; i++){
            if (i == 0 || i == 1 || i == 5 || i == 6){
                MODES[i][1] = 0;
            }
            else {
                MODES[i][1] = 1;
            }
        }

        // Populates 2nd column
        for (int i=0; i<10; i++){
            if (i == 0 || i == 5){
                MODES[i][2] = 0;
            }
            else if (i == 1 || i == 6 || i == 4 || i == 9){
                MODES[i][2] = 3;
            }
            else {

                MODES[2][2] = trial_mode;
                MODES[7][2] = trial_mode;
                if (trial_mode == 1){
                    MODES[3][2] = 2;
                    MODES[8][2] = 2;
                }
                else{
                    MODES[3][2] = 1;
                    MODES[8][2] = 1;
                }
            }
        }

        System.out.println(Arrays.deepToString(MODES));
    }

    public void initHomeFragment()
    {
        Fragment home = new HomeFragment();
        // Begin fragment manager
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Adds homepage to main page
        transaction.add(R.id.container, home);
        transaction.commit();
    }

    public void startHomeFragment()
    {
        Fragment home = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Switch to homepage
        transaction.replace(R.id.container, home);
        transaction.commit();
    }

    public void startInstructionFragment(String gender, String age)
    {

//        String modes = "" + MODES[mode][0] + MODES[mode][1] + MODES[mode][2];
        Fragment instructionFragment = new InstructionFragment();
        Bundle arguments = new Bundle();
        arguments.putString(InstructionFragment.GENDER, gender);
        arguments.putString(InstructionFragment.AGE, age);
        arguments.putInt(InstructionFragment.MODE, mode);
        instructionFragment.setArguments(arguments);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Switch to instructions page
        transaction.replace(R.id.container, instructionFragment);
        transaction.show(instructionFragment);
        transaction.commit();
    }

    public void startSimulationFragment(String gender, String simulationMode)
    {
        Fragment simulateTest = new SimulationFragment();
        Bundle arguments = new Bundle();
        arguments.putString(SimulationFragment.GENDER, gender);
        arguments.putString(SimulationFragment.MODE, simulationMode);
        simulateTest.setArguments(arguments);

//        System.out.println(Arrays.deepToString(MODES));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Switch to quiz page
        transaction.replace(R.id.container, simulateTest);
        transaction.commit();
    }

    public void startResultsFragment()
    {
        readResultsFromDatabase();

        Fragment resultsFragment = new ResultsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        // Switch to results page
        transaction.replace(R.id.container, resultsFragment);
        transaction.commit();
    }

    private void readResultsFromDatabase()
    {
        results = dao.getAllResults();
    }

    public List<Result> getResults()
    {
        return results;
    }

    public void insertResultIntoDatabase(Result result)
    {
        dao.insert(result);
    }
}
