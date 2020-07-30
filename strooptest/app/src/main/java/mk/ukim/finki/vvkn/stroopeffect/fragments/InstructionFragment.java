package mk.ukim.finki.vvkn.stroopeffect.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Random;

import mk.ukim.finki.vvkn.stroopeffect.MainActivity;
import mk.ukim.finki.vvkn.stroopeffect.R;
import mk.ukim.finki.vvkn.stroopeffect.models.Result;
import mk.ukim.finki.vvkn.stroopeffect.utilities.StopWatch;

import com.makeramen.roundedimageview.RoundedImageView;


public class InstructionFragment extends Fragment {
    public static final String MODE = "mode";
    public static final String GENDER = "male or female";
    public static final String AGE = "age";
    public static String gender = "male";
    public static String age = "20";
    public static String inst_mode = "000";
    private TextView textViewInstruction;
    private Button nextButton;
    private RoundedImageView[] imgViewsArray;
    private static final int [] COLOR_BACKGROUNDS = { R.drawable.color_red, R.drawable.color_blue,
            R.drawable.color_yellow, R.drawable.color_pink };

//    public static final int LETTERSNEUTRALWORD = 0;
//    public static final int LETTERSNEUTRALCOLOR = 1;
//    public static final int LETTERSCONGRUENT = 2;
//    public static final int LETTERSINCONGRUENT = 3;
//    public static final int EMOTIONNEUTRALWORD = 4;
//    public static final int EMOTIONNEUTRALCOLOR = 5;
//    public static final int EMOTIONCONGRUENT = 6;
//    public static final int EMOTIONINCONGRUENT= 7;

//    public static int mSimulationType = 0;

    public InstructionFragment(){
        imgViewsArray = new RoundedImageView[4];
    }

    public void setInstructions(){
        if (inst_mode == "" + MainActivity.MODES[0][0] + MainActivity.MODES[0][1] + MainActivity.MODES[0][2] ) {
            // Practice Round 1 Neutral
            SimulationFragment.currentResult = new Result();
            textViewInstruction.setText("PRACTICE ROUND\nPlease select the color that corresponds to the word. I.e. if the word is 'BLUE', the correct option is blue color as shown below.");
        }
        else if (inst_mode == "" + MainActivity.MODES[1][0] + MainActivity.MODES[1][1] + MainActivity.MODES[1][2] ) {
            // Practice Round 2 Mixed
            textViewInstruction.setText("PRACTICE ROUND\nPlease select the color that corresponds to the COLOR of the word. I.e. if the word is blue in color, the correct option is blue color as shown below.");
        }
        else if (inst_mode == "" + MainActivity.MODES[2][0] + MainActivity.MODES[2][1] + MainActivity.MODES[2][2] ) {
            // Actual Round 1 Congruent/Incongruent
            textViewInstruction.setText("ACTUAL ROUND\nPlease select the color that corresponds to the COLOR of the FIRST and LAST characters. I.e. if the FIRST and LAST characters are blue in color, the correct option is blue color as shown below.");
        }
        else if (inst_mode == "" + MainActivity.MODES[3][0] + MainActivity.MODES[3][1] + MainActivity.MODES[3][2] ) {
            // Actual Round 2 Congruent/Incongruent
            textViewInstruction.setText("ACTUAL ROUND\nPlease select the color that corresponds to the COLOR of the FIRST and LAST characters. I.e. if the FIRST and LAST characters are blue in color, the correct option is blue color as shown below.");
        }
        else if (inst_mode == "" + MainActivity.MODES[4][0] + MainActivity.MODES[4][1] + MainActivity.MODES[4][2] ) {
            // Actual Round 3 Mixed
            textViewInstruction.setText("ACTUAL ROUND\nPlease select the color that corresponds to the EMOTION.\n Here are the emotions associated with the color:\n ANGER - RED\nSAD - BLUE\nLOVE - PINK\nHAPPY - YELLOW");
        }
        else if (inst_mode == "" + MainActivity.MODES[5][0] + MainActivity.MODES[5][1] + MainActivity.MODES[5][2] ) {
            // Practice Round 1 Neutral
            textViewInstruction.setText("PRACTICE ROUND\nPlease select the color that corresponds to the COLOR of the word. I.e. if the word is blue in color, the correct option is blue color as shown below.");
        }
        else if (inst_mode == "" + MainActivity.MODES[6][0] + MainActivity.MODES[6][1] + MainActivity.MODES[6][2] ) {
            // Practice Round 2 Mixed
            textViewInstruction.setText("PRACTICE ROUND\nPlease select the color that corresponds to the EMOTION. Here are the emotions associated with the color:\n ANGER - RED\nSAD - BLUE\nLOVE - PINK\nHAPPY - YELLOW");
        }
        else if (inst_mode == "" + MainActivity.MODES[7][0] + MainActivity.MODES[7][1] + MainActivity.MODES[7][2] ) {
            // Actual Round 1 Congruent/Incongruent
            textViewInstruction.setText("ACTUAL ROUND\nPlease select the color that corresponds to the EMOTION. Here are the emotions associated with the color:\n ANGER - RED\nSAD - BLUE\nLOVE - PINK\nHAPPY - YELLOW");
        }
        else if (inst_mode == "" + MainActivity.MODES[8][0] + MainActivity.MODES[8][1] + MainActivity.MODES[8][2] ) {
            // Actual Round 2 Congruent/Incongruent
            textViewInstruction.setText("ACTUAL ROUND\nPlease select the color that corresponds to the EMOTION. Here are the emotions associated with the color:\n ANGER - RED\nSAD - BLUE\nLOVE - PINK\nHAPPY - YELLOW");
        }
        else if (inst_mode == "" + MainActivity.MODES[9][0] + MainActivity.MODES[9][1] + MainActivity.MODES[9][2] ) {
            // Actual Round 3 Mixed
            textViewInstruction.setText("ACTUAL ROUND\nPlease select the color that corresponds to the EMOTION. Here are the emotions associated with the color:\n ANGER - RED\nSAD - BLUE\nLOVE - PINK\nHAPPY - YELLOW");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruction, container, false);
        textViewInstruction = (TextView) view.findViewById(R.id.simulation_fragment_text_view_instructions);
        textViewInstruction.setTextColor(Color.BLACK);
        nextButton = (Button) view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processClick();
            }
        });
        imgViewsArray[0] = (RoundedImageView)view.findViewById(R.id.simulation_fragment_image_view_option1_rounded);
        imgViewsArray[1] = (RoundedImageView)view.findViewById(R.id.simulation_fragment_image_view_option2_rounded);
        imgViewsArray[2] = (RoundedImageView)view.findViewById(R.id.simulation_fragment_image_view_option3_rounded);
        imgViewsArray[3] = (RoundedImageView)view.findViewById(R.id.simulation_fragment_image_view_option4_rounded);
        for(RoundedImageView roundedImageView : imgViewsArray)
        {
            roundedImageView.setCornerRadius((float)10);
        }
        imgViewsArray[0].setImageDrawable(getResources().getDrawable(COLOR_BACKGROUNDS[0]));
        imgViewsArray[1].setImageDrawable(getResources().getDrawable(COLOR_BACKGROUNDS[1]));
        imgViewsArray[2].setImageDrawable(getResources().getDrawable(COLOR_BACKGROUNDS[2]));
        imgViewsArray[3].setImageDrawable(getResources().getDrawable(COLOR_BACKGROUNDS[3]));

//        mSimulationType++;
        gender = getArguments().getString(GENDER);
        age = getArguments().getString(AGE);
        inst_mode = getArguments().getString(MODE);
        SimulationFragment.currentResult.setGender(gender);
        SimulationFragment.currentResult.setAge(age);
        setInstructions();
        return view;
    }

    private void processClick()
    {
        ((MainActivity)getActivity()).startSimulationFragment(gender,inst_mode);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
