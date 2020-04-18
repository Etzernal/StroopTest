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
import java.util.List;

import mk.ukim.finki.vvkn.stroopeffect.db.ResultsDao;
import mk.ukim.finki.vvkn.stroopeffect.fragments.HomeFragment;
import mk.ukim.finki.vvkn.stroopeffect.fragments.ResultsFragment;
import mk.ukim.finki.vvkn.stroopeffect.fragments.SimulationFragment;
import mk.ukim.finki.vvkn.stroopeffect.fragments.InstructionFragment;
import mk.ukim.finki.vvkn.stroopeffect.models.Result;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    private ResultsDao dao;
    private List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(getApplicationContext(), R.raw.background_music);
        player.setLooping(true);
        dao = new ResultsDao(getApplicationContext());
        results = new ArrayList<>();
        initHomeFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.start();
        try {
            dao.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
        dao.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
        Fragment instructionFragment = new InstructionFragment();

        Bundle arguments = new Bundle();
        arguments.putString(InstructionFragment.GENDER, gender);
        arguments.putString(InstructionFragment.AGE, age);
        instructionFragment.setArguments(arguments);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Switch to instructions page
        transaction.replace(R.id.container, instructionFragment);
        transaction.show(instructionFragment);
        transaction.commit();
    }

    public void startSimulationFragment(String gender, int simulationType)
    {
        Fragment simulateTest = new SimulationFragment();
        Bundle arguments = new Bundle();
        arguments.putString(SimulationFragment.TESTER_GENDER, gender);
        arguments.putInt(SimulationFragment.TRIALTYPE, simulationType);
        simulateTest.setArguments(arguments);

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
