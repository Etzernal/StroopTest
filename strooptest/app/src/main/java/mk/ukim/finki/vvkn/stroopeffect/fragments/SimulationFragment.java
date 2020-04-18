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
    public static final String TESTER_GENDER = "male or female";
    public static final String TRIALTYPE = "trialtype";
    public static Result currentResult;
    public static final int LETTERSNEUTRALWORD = 0;
    public static final int LETTERSNEUTRALCOLOR = 1;
    public static final int LETTERSCONGRUENT = 2;
    public static final int LETTERSINCONGRUENT = 3;
    public static final int EMOTIONNEUTRALWORD = 4;
    public static final int EMOTIONNEUTRALCOLOR = 5;
    public static final int EMOTIONCONGRUENT = 6;
    public static final int EMOTIONINCONGRUENT= 7;
    public static final int MAX_SIMULATIONS = 15;
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

    private int mSimulationType;
    private int mCurrentSimulationNumber;
    private int mTotalTries;

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
        mCurrentSimulationNumber = 0;
        mTotalTries = 0;
        mSimulationType = getArguments().getInt(TRIALTYPE);

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
        mTotalTries++;

        if (mCorrectAnswer == optionClicked) {
            mCurrentSimulationNumber++;

            if (mCurrentSimulationNumber == MAX_SIMULATIONS)
            {
                long elapsedTime = stopWatch.getElapsedMilliseconds();
                System.out.println(String.format("Simulation Type: %d", mSimulationType));
                System.out.println(String.format("Time: %d",elapsedTime));
                currentResult.setElapsedTime(mSimulationType, elapsedTime/ MAX_SIMULATIONS);
                currentResult.setErrorPercentage(mSimulationType, 100 - 100 * MAX_SIMULATIONS / mTotalTries);
                stopWatch.restart();
                // Change simulation type
                //mSimulationType++;
                if (mSimulationType == EMOTIONINCONGRUENT) {
                    String message = "Thanks for participating.";
                    mSimulationType = LETTERSNEUTRALWORD;
                    InstructionFragment.mSimulationType = LETTERSNEUTRALWORD-1;
                    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    ((MainActivity)getActivity()).insertResultIntoDatabase(currentResult);
                    ((MainActivity)getActivity()).startHomeFragment();
                }
                else {
                    System.out.println(mSimulationType);
                    ((MainActivity)getActivity()).startInstructionFragment(InstructionFragment.gender,InstructionFragment.age);
                }
            }
            simulate(mSimulationType);
        }
        else
        {
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
    private void simulate(int type)
    {
        // Randomly select color
        int correctColorId = random.nextInt(COLOR_BACKGROUNDS.length);
        mCorrectAnswer = correctColorId;

        if (type == LETTERSNEUTRALWORD) {
            textViewInstruction.setText("Select the color that corresponds to the word!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsLettersNeutralWord(correctColorId);
            setWidgetOptions();
        }
        else if (type == LETTERSNEUTRALCOLOR)
        {
            textViewInstruction.setText("Select the color that corresponds to the color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsLettersNeutralColor(correctColorId);
            setWidgetOptions();;
        }
        else if (type == LETTERSCONGRUENT)
        {
            textViewInstruction.setText("Select the color that corresponds to the color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsLettersCongruent(correctColorId);
            setWidgetOptions();;
        }
        else if (type == LETTERSINCONGRUENT)
        {
            textViewInstruction.setText("Select the color that corresponds to the color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsLettersIncongruent(correctColorId);
            setWidgetOptions();;
        }
        else if (type == EMOTIONNEUTRALWORD)
        {
            textViewInstruction.setText("Select the color that corresponds to the emotion!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsEmotionNeutralWord(correctColorId);
            setWidgetOptions();;
        }
        else if (type == EMOTIONNEUTRALCOLOR)
        {
            textViewInstruction.setText("Select the color that corresponds to the word color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsEmotionNeutralColor(correctColorId);
            setWidgetOptions();;
        }
        else if (type == EMOTIONCONGRUENT)
        {
            textViewInstruction.setText("Select the color that corresponds to the word color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsEmotionCongruent(correctColorId);
            setWidgetOptions();;
        }
        else if (type == EMOTIONINCONGRUENT)
        {
            textViewInstruction.setText("Select the color that corresponds to the word color!");
            textViewQuestion.setTextColor(Color.parseColor(BLACK_CODE));
            setWidgetsEmotionIncongruent(correctColorId);
            setWidgetOptions();;
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

    private void setWidgetsLettersNeutralColor(int correctColorId){
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

    private void setWidgetsEmotionNeutralWord(int correctColorId){
        // RANDOM COLOR in same color. i.e. Word Anger in Black color.
        textViewQuestion.setText(EMOTIONS[correctColorId]);
    }

    private void setWidgetsEmotionNeutralColor(int correctColorId){
        // RANDOM COLOR in same color. i.e. Word **** in blue color
        textViewQuestion.setText("****");
        textViewQuestion.setTextColor(Color.parseColor(COLOR_CODE[correctColorId]));
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
