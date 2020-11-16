package mk.ukim.finki.vvkn.stroopeffect.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;

import android.text.Html;
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
import mk.ukim.finki.vvkn.stroopeffect.models.Record;
import mk.ukim.finki.vvkn.stroopeffect.models.Result;
import mk.ukim.finki.vvkn.stroopeffect.utilities.StopWatch;

import com.makeramen.roundedimageview.RoundedImageView;


public class InstructionFragment extends Fragment {
    public static final String MODE = "mode";
    public static final String GENDER = "male or female";
    public static final String AGE = "age";
    public static String gender = "male";
    public static String age = "20";
    public static int mode = 0;
    public static String inst_mode;
    private TextView textViewInstruction;
    private Button nextButton;
    private RoundedImageView[] imgViewsArray;
    private static final int [] COLOR_BACKGROUNDS = { R.drawable.color_red, R.drawable.color_blue,
            R.drawable.color_yellow, R.drawable.color_pink };

    public InstructionFragment(){
        imgViewsArray = new RoundedImageView[4];
    }

    public void setInstructions(){
        inst_mode = "" + MainActivity.MODES[MainActivity.mode][0]+ MainActivity.MODES[MainActivity.mode][1] + MainActivity.MODES[MainActivity.mode][2];
        if (inst_mode.equals(""+ MainActivity.MODES[0][0] + MainActivity.MODES[0][1] + MainActivity.MODES[0][2])){
            SimulationFragment.currentRecord = new Record();
            SimulationFragment.currentResult = new Result();
            SimulationFragment.currentResult.setGender(gender);
            SimulationFragment.currentResult.setAge(age);
        }
        if (inst_mode.equals("000")) {
            // Practice Round 1 Neutral
            textViewInstruction.setText(Html.fromHtml("TRIAL 1.1<br></br>PRACTICE ROUND<br></br><br></br>Please select the color that corresponds to the COLOR of the astericks. I.e. <font color=#0000FF>****</font>. The blue square should be selected."));
        }
        else if (inst_mode.equals("003")) {
            // Practice Round 2 Mixed
            textViewInstruction.setText(Html.fromHtml("TRIAL 1.2<br></br>PRACTICE ROUND<br></br><br></br>Please select the color that corresponds to the COLOR of the word. I.e. <font color=#0000FF>B</font>LU<font color=#0000FF>E</font>. The blue square should be selected."));
        }
        else if (inst_mode.equals("011")) {
            // Actual Round 1 Congruent/Incongruent
            textViewInstruction.setText(Html.fromHtml("TRIAL 1.3<br></br>ACTUAL ROUND<br></br><br></br>Please select the color that corresponds to the COLOR of the word. I.e. <font color=#0000FF>B</font>LU<font color=#0000FF>E</font>. The blue square should be selected."));
        }
        else if (inst_mode.equals("012")) {
            // Actual Round 2 Congruent/Incongruent
            textViewInstruction.setText(Html.fromHtml("TRIAL 1.4<br></br>ACTUAL ROUND<br></br><br></br>Please select the color that corresponds to the COLOR of the word. I.e. <font color=#0000FF>B</font>LU<font color=#0000FF>E</font>. The blue square should be selected."));
        }
        else if (inst_mode.equals("013")) {
            // Actual Round 3 Mixed
            textViewInstruction.setText(Html.fromHtml("TRIAL 1.5<br></br>ACTUAL ROUND<br></br><br></br>Please select the color that corresponds to the COLOR of the word. I.e. <font color=#0000FF>B</font>LU<font color=#0000FF>E</font>. The blue square should be selected."));
        }
        else if (inst_mode.equals("100")) {
            // Practice Round 1 Neutral
            textViewInstruction.setText(Html.fromHtml("TRIAL 2.1<br></br>PRACTICE ROUND<br></br><br></br>Please select the color that corresponds to the COLOR of the astericks. I.e. <font color=#0000FF>****</font>. The blue square should be selected."));        }
        else if (inst_mode.equals("103")) {
            // Practice Round 2 Mixed
            textViewInstruction.setText(Html.fromHtml("TRIAL 2.2<br></br>PRACTICE ROUND<br></br><br></br>Please select the color that corresponds to the COLOR. Here are the emotions associated with the color:<br></br><font color=#FF0000>ANGER</font> - <font color=#FF0000>RED</font><br></br><font color=#0000FF>SAD</font> - <font color=#0000FF>BLUE</font><br></br><font color=#FF00FF>LOVE</font> - <font color=#FF00FF>PINK</font><br></br><font color=#FFCC00>HAPPY</font> - <font color=#FFCC00>YELLOW</font>"));
        }
        else if (inst_mode.equals("111")) {
            // Actual Round 1 Congruent/Incongruent
            textViewInstruction.setText(Html.fromHtml("TRIAL 2.3<br></br>ACTUAL ROUND<br></br><br></br>Please select the color that corresponds to the COLOR. Here are the emotions associated with the color:<br></br><font color=#FF0000>ANGER</font> - <font color=#FF0000>RED</font><br></br><font color=#0000FF>SAD</font> - <font color=#0000FF>BLUE</font><br></br><font color=#FF00FF>LOVE</font> - <font color=#FF00FF>PINK</font><br></br><font color=#FFCC00>HAPPY</font> - <font color=#FFCC00>YELLOW</font>"));
        }
        else if (inst_mode.equals("112")) {
            // Actual Round 2 Congruent/Incongruent
            textViewInstruction.setText(Html.fromHtml("TRIAL 2.4<br></br>ACTUAL ROUND<br></br><br></br>Please select the color that corresponds to the COLOR. Here are the emotions associated with the color:<br></br><font color=#FF0000>ANGER</font> - <font color=#FF0000>RED</font><br></br><font color=#0000FF>SAD</font> - <font color=#0000FF>BLUE</font><br></br><font color=#FF00FF>LOVE</font> - <font color=#FF00FF>PINK</font><br></br><font color=#FFCC00>HAPPY</font> - <font color=#FFCC00>YELLOW</font>"));
        }
        else if (inst_mode.equals("113")) {
            // Actual Round 3 Mixed
            textViewInstruction.setText(Html.fromHtml("TRIAL 2.5<br></br>ACTUAL ROUND<br></br><br></br>Please select the color that corresponds to the COLOR. Here are the emotions associated with the color:<br></br><font color=#FF0000>ANGER</font> - <font color=#FF0000>RED</font><br></br><font color=#0000FF>SAD</font> - <font color=#0000FF>BLUE</font><br></br><font color=#FF00FF>LOVE</font> - <font color=#FF00FF>PINK</font><br></br><font color=#FFCC00>HAPPY</font> - <font color=#FFCC00>YELLOW</font>"));
        }
        MainActivity.mode++;
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

        mode = getArguments().getInt(MODE);
        gender = getArguments().getString(GENDER);
        age = getArguments().getString(AGE);

//        mSimulationType++;
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
