package mk.ukim.finki.vvkn.stroopeffect.fragments;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import com.makeramen.roundedimageview.RoundedImageView;

import org.w3c.dom.Text;

import java.util.Random;

import mk.ukim.finki.vvkn.stroopeffect.MainActivity;
import mk.ukim.finki.vvkn.stroopeffect.R;
import mk.ukim.finki.vvkn.stroopeffect.models.Result;
import mk.ukim.finki.vvkn.stroopeffect.utilities.StopWatch;

public class SimulationFragment extends Fragment {
    public static final String GENDER = "male or female";
    public static final String MODE = "mode";
    public static Result currentResult;

    public static final int TRIALDURATION = 4000;

//    public static final int LETTERSNEUTRALWORD = 0;
//    public static final int LETTERSNEUTRALCOLOR = 1;
//    public static final int LETTERSCONGRUENT = 2;
//    public static final int LETTERSINCONGRUENT = 3;
//    public static final int EMOTIONNEUTRALWORD = 4;
//    public static final int EMOTIONNEUTRALCOLOR = 5;
//    public static final int EMOTIONCONGRUENT = 6;
//    public static final int EMOTIONINCONGRUENT= 7;

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

    public static final int MAX_TRIALS = 16;
    public static final int TOAST_DURATION = 300;


    // Colors required - Red, Blue, Pink, Yellow.

    private static final int blackBackground = R.drawable.color_black;
    private static final String blackName = "Black";
    private static final String BLACK_CODE = "#000000";

    private static final String[] EMOTIONS = {"ANGER","SAD","HAPPY","LOVE"};
    private static final String[] COLOR_CODE = {"#FF0000","#0000FF","#FFCC00","#FF00FF"};
    private static final int [] COLOR_BACKGROUNDS = { R.drawable.color_red, R.drawable.color_blue,
                                                        R.drawable.color_yellow, R.drawable.color_pink };

    private static final String [] COLOR_NAMES = { "RED", "BLUE", "YELLOW", "PINK" };

    private String mSimulationType;
    private int mCurrentTrials;
    private int mCorrectTrials;

    private final StopWatch stopWatch;
    private final Random random;

    private TextView textViewQuestion;
    private TextView textViewInstruction;

    private RoundedImageView imgViewCircle;
    private RoundedImageView[] imgViewsArray;

    private Toast errorToast;

    private int mCorrectAnswer;


    public SimulationFragment() {
        imgViewsArray = new RoundedImageView[4];
        stopWatch = new StopWatch();
        random = new Random();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simulation, container, false);
        initializeWidgets(view);
        mCurrentTrials = 0;
        mCorrectTrials = 0;
        mSimulationType = getArguments().getString(MODE);

        stopWatch.start();

        simulate(mSimulationType);

        for (int i = 0; i < imgViewsArray.length; ++i)
        {
            final int index = i;
            imgViewsArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    processClick(index);
                }
            });
        }

        return view;
    }

    private void initializeWidgets(View view)
    {
        textViewQuestion = (TextView) view.findViewById(R.id.simulation_fragment_text_view_question);
        textViewInstruction = (TextView) view.findViewById(R.id.simulation_fragment_text_view_instructions);
        imgViewCircle = (RoundedImageView) view.findViewById(R.id.simulation_fragment_image_view_circle);

        imgViewsArray[0] = (RoundedImageView)view.findViewById(R.id.simulation_fragment_image_view_option1_rounded);
        imgViewsArray[1] = (RoundedImageView)view.findViewById(R.id.simulation_fragment_image_view_option2_rounded);
        imgViewsArray[2] = (RoundedImageView)view.findViewById(R.id.simulation_fragment_image_view_option3_rounded);
        imgViewsArray[3] = (RoundedImageView)view.findViewById(R.id.simulation_fragment_image_view_option4_rounded);
        for(RoundedImageView roundedImageView : imgViewsArray)
        {
            roundedImageView.setCornerRadius((float)10);
        }

        View toastView = getActivity().getLayoutInflater().inflate(R.layout.toast_error, null);
        errorToast = new Toast(getActivity().getApplicationContext());
        errorToast.setGravity(Gravity.BOTTOM, 0, 0);
        errorToast.setView(toastView);
    }

    private void processClick(int optionClicked)
    {
        mCurrentTrials++;
        if (mCorrectAnswer == optionClicked) {
            mCorrectTrials++;
            if (mCurrentTrials == MAX_TRIALS) {
                long elapsedTime = stopWatch.getElapsedMilliseconds();
                System.out.println("MODE: " + InstructionFragment.inst_mode);
                System.out.println(String.format("Time: %d",elapsedTime));
                currentResult.setElapsedTime(InstructionFragment.inst_mode, elapsedTime/ MAX_TRIALS);
                currentResult.setErrorPercentage(InstructionFragment.inst_mode, 100 - 100 * MAX_TRIALS / mCorrectTrials);
                stopWatch.restart();
                // Change simulation type
                //mSimulationType++;
                if (InstructionFragment.inst_mode.equals("" + MainActivity.MODES[9][0] + MainActivity.MODES[9][1] + MainActivity.MODES[9][2])) {
                    String message = "Thanks for participating.";
                    InstructionFragment.inst_mode = "" + MainActivity.MODES[0][0] + MainActivity.MODES[0][1] + MainActivity.MODES[0][2];
                    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    ((MainActivity)getActivity()).insertResultIntoDatabase(currentResult);
                    ((MainActivity)getActivity()).startHomeFragment();
                }
                else {
//                    System.out.println(mSimulationType);
                    ((MainActivity)getActivity()).startInstructionFragment(InstructionFragment.gender,InstructionFragment.age);
                }
            }
            simulate(mSimulationType);
        }
        else {
            errorToast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    errorToast.cancel();
                }
            }, TOAST_DURATION);
        }
    }

    // Selection of the options.
    private void simulate(String mode)
    {
        // Randomly select color
        int correctColorId = random.nextInt(COLOR_BACKGROUNDS.length);
        mCorrectAnswer = correctColorId;

        // For each mode, set the correct question.
        ///////////////////////////////////////////
        // prac round neutral
        if (mode.equals("" + MainActivity.MODES[0][0] + MainActivity.MODES[0][1] + MainActivity.MODES[0][2])) {
//            System.out.println("PRAC NEUTRAL " + MainActivity.MODES[0][0]);
            textViewInstruction.setText("Select the color that corresponds to the word!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsNeutral(correctColorId);
            setWidgetOptions();
        }

        // prac round mixed
        else if (mode.equals("" + MainActivity.MODES[1][0] + MainActivity.MODES[1][1] + MainActivity.MODES[1][2])) {
//            System.out.println("PRAC MIXED " + MainActivity.MODES[1][0]);
            textViewInstruction.setText("Select the color that corresponds to the color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            // check for question number
            if (mCurrentTrials <= 16 && mCurrentTrials > 8){
                // check for stroop type (emotion/letters)
                if (MainActivity.MODES[1][0] == WARPED) {
                    setWidgetsLettersCongruent(correctColorId);
                }
                else {
                    setWidgetsEmotionCongruent(correctColorId);
                }
            }
            else {
                if (MainActivity.MODES[1][0] == WARPED) {
                    setWidgetsLettersIncongruent(correctColorId);
                }
                else {
                    setWidgetsEmotionIncongruent(correctColorId);
                }
            }
            setWidgetOptions();;
        }

        // actual congruent/incongruent
        else if (mode.equals("" + MainActivity.MODES[2][0] + MainActivity.MODES[2][1] + MainActivity.MODES[2][2])) {
//            System.out.println("CONGRUENT " + MainActivity.MODES[2][0]);
            textViewInstruction.setText("Select the color that corresponds to the color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            // check for stroop type (emotion/letters)
            if (MainActivity.MODES[2][0] == WARPED) {
                setWidgetsLettersCongruent(correctColorId);
            }
            else {
                setWidgetsEmotionCongruent(correctColorId);
            }
            setWidgetOptions();
        }

        // actual incongurent/congruent
        else if (mode.equals("" + MainActivity.MODES[3][0] + MainActivity.MODES[3][1] + MainActivity.MODES[3][2])) {
//            System.out.println("INCONGRUENT " + MainActivity.MODES[3][0]);
            textViewInstruction.setText("Select the color that corresponds to the color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            if (MainActivity.MODES[3][0] == WARPED) {
                setWidgetsLettersIncongruent(correctColorId);
            }
            else {
                setWidgetsEmotionIncongruent(correctColorId);
            }
            setWidgetOptions();;
        }

        // actual mixed
        else if (mode.equals("" + MainActivity.MODES[4][0] + MainActivity.MODES[4][1] + MainActivity.MODES[4][2])) {
//            System.out.println("MIXED " + MainActivity.MODES[4][0]);
            textViewInstruction.setText("Select the color that corresponds to the emotion!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            // set congruent questions
            if (mCurrentTrials == 0 || mCurrentTrials == 3 || mCurrentTrials == 4 || mCurrentTrials == 7 || mCurrentTrials == 8 || mCurrentTrials == 9 || mCurrentTrials == 13 || mCurrentTrials == 15){
                if (MainActivity.MODES[4][0] == WARPED) {
                    setWidgetsLettersCongruent(correctColorId);
                }
                else {
                    setWidgetsEmotionCongruent(correctColorId);
                }
            }
            else {
                if (MainActivity.MODES[4][0] == WARPED) {
                    setWidgetsLettersIncongruent(correctColorId);
                }
                else {
                    setWidgetsEmotionIncongruent(correctColorId);
                }
            }
            setWidgetOptions();;
        }

        // prac neutral
        else if (mode.equals("" + MainActivity.MODES[5][0] + MainActivity.MODES[5][1] + MainActivity.MODES[5][2])) {
//            System.out.println("PRAC NEUTRAL " + MainActivity.MODES[5][0]);
            textViewInstruction.setText("Select the color that corresponds to the word color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsNeutral(correctColorId);
            setWidgetOptions();;
        }

        // prac mixed
        else if (mode.equals( "" + MainActivity.MODES[6][0] + MainActivity.MODES[6][1] + MainActivity.MODES[6][2])) {
//            System.out.println("PRAC MIXED " + MainActivity.MODES[6][0]);
            textViewInstruction.setText("Select the color that corresponds to the word color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            if (mCurrentTrials <= 16 && mCurrentTrials > 8){
                // check for stroop type (emotion/letters)
                if (MainActivity.MODES[6][0] == WARPED) {
                    setWidgetsLettersCongruent(correctColorId);
                }
                else {
                    setWidgetsEmotionCongruent(correctColorId);
                }
            }
            else {
                if (MainActivity.MODES[6][0] == WARPED) {
                    setWidgetsLettersIncongruent(correctColorId);
                }
                else {
                    setWidgetsEmotionIncongruent(correctColorId);
                }
            }
            setWidgetOptions();;
        }

        // actual incongruent/congruent
        else if (mode.equals("" + MainActivity.MODES[7][0] + MainActivity.MODES[7][1] + MainActivity.MODES[7][2])) {
//            System.out.println("CONGRUENT " + MainActivity.MODES[7][0]);
            textViewInstruction.setText("Select the color that corresponds to the word color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            if (MainActivity.MODES[7][0] == WARPED) {
                setWidgetsLettersCongruent(correctColorId);
            }
            else {
                setWidgetsEmotionCongruent(correctColorId);
            }
            setWidgetOptions();;
        }

        // actual congruent/incongruent
        else if (mode.equals("" + MainActivity.MODES[8][0] + MainActivity.MODES[8][1] + MainActivity.MODES[8][2])) {
//            System.out.println("INCONGRUENT " + MainActivity.MODES[8][0]);
            textViewInstruction.setText("Select the color that corresponds to the word color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            if (MainActivity.MODES[8][0] == WARPED) {
                setWidgetsLettersIncongruent(correctColorId);
            }
            else {
                setWidgetsEmotionIncongruent(correctColorId);
            }
            setWidgetOptions();;
        }

        // actual mixed
        else if (mode.equals("" + MainActivity.MODES[9][0] + MainActivity.MODES[9][1] + MainActivity.MODES[9][2])) {
//            System.out.println("MIXED " + MainActivity.MODES[9][0]);
            textViewInstruction.setText("Select the color that corresponds to the word color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            //need to mix the questions
            if (mCurrentTrials == 0 || mCurrentTrials == 3 || mCurrentTrials == 4 || mCurrentTrials == 7 || mCurrentTrials == 8 || mCurrentTrials == 9 || mCurrentTrials == 13 || mCurrentTrials == 15){
                if (MainActivity.MODES[9][0] == WARPED) {
                    setWidgetsLettersCongruent(correctColorId);
                }
                else {
                    setWidgetsEmotionCongruent(correctColorId);
                }
            }
            else {
                if (MainActivity.MODES[9][0] == WARPED) {
                    setWidgetsLettersIncongruent(correctColorId);
                }
                else {
                    setWidgetsEmotionIncongruent(correctColorId);
                }
            }
            setWidgetOptions();
        }
    }

    private void setWidgetOptions(){
        // Options are set here
        imgViewsArray[0].setImageDrawable(getResources().getDrawable(COLOR_BACKGROUNDS[0]));
        imgViewsArray[1].setImageDrawable(getResources().getDrawable(COLOR_BACKGROUNDS[1]));
        imgViewsArray[2].setImageDrawable(getResources().getDrawable(COLOR_BACKGROUNDS[2]));
        imgViewsArray[3].setImageDrawable(getResources().getDrawable(COLOR_BACKGROUNDS[3]));
    }

    private void setWidgetsLettersNeutralWord(int correctColorId){
        // RANDOM COLOR in black color. i.e. Word Blue in black color - test reading of word
        textViewQuestion.setText(COLOR_NAMES[correctColorId]);
    }

    private void setWidgetsNeutral(int correctColorId){
        // ASTERICKS in same color. i.e. Word **** in blue color - test reading of color
        textViewQuestion.setText("****");
        textViewQuestion.setTextColor(Color.parseColor(COLOR_CODE[correctColorId]));

    }

    private void setWidgetsLettersCongruent(int correctColorId){
        // RANDOM COLOR in same color. i.e. Word Blue in blue color for first and last character
        char[] newText = COLOR_NAMES[correctColorId].toCharArray();
        String newString = "";
        for (int i=0; i<newText.length;i++){
            if (i == 0 | i == newText.length-1){
                newString += "<font color=" + COLOR_CODE[correctColorId] + ">" + String.valueOf(newText[i]) + "</font>";
            }
            else {
                newString += newText[i];
            }
        }
        textViewQuestion.setText(Html.fromHtml(newString));

    }

    private void setWidgetsLettersIncongruent(int correctColorId){
        // RANDOM COLOR in different color. i.e. Word Blue, First and last letter is not blue, the remaining colors are blue.
        int mismatchWord = random.nextInt(4);
        while (mismatchWord == correctColorId){
            mismatchWord = random.nextInt(4);
        }
        char[] newText = COLOR_NAMES[mismatchWord].toCharArray();
        String newString = "";
        for (int i=0; i<newText.length;i++){
            if (i == 0 | i == newText.length-1){
                newString += "<font color=" + COLOR_CODE[correctColorId] + ">" + String.valueOf(newText[i]) + "</font>";
            }
            else {
                newString += newText[i];
            }
        }
        textViewQuestion.setText(Html.fromHtml(newString));
    }

    private void setWidgetsEmotionCongruent(int correctColorId){
        // RANDOM COLOR in same color. i.e. Word Anger in red color
        textViewQuestion.setText(EMOTIONS[correctColorId]);
        textViewQuestion.setTextColor(Color.parseColor(COLOR_CODE[correctColorId]));
    }

    private void setWidgetsEmotionIncongruent(int correctColorId){
        // RANDOM COLOR in same color. i.e. Word Anger in blue color
        int mismatchWord = random.nextInt(4);
        while (mismatchWord == correctColorId){
            mismatchWord = random.nextInt(4);
        }
        textViewQuestion.setText(EMOTIONS[mismatchWord]);
        textViewQuestion.setTextColor(Color.parseColor(COLOR_CODE[correctColorId]));
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
