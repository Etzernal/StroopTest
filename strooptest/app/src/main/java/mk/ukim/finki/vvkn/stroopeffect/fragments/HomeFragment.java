package mk.ukim.finki.vvkn.stroopeffect.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.EditText;

import mk.ukim.finki.vvkn.stroopeffect.MainActivity;
import mk.ukim.finki.vvkn.stroopeffect.R;

public class HomeFragment extends Fragment {

    private RadioGroup radioGroup;
    private Button btnStart;
    private Button btnResults;
    private EditText ageInput;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        radioGroup = (RadioGroup)(view.findViewById(R.id.home_fragment_radio_buttons_group));
        btnStart = (Button)(view.findViewById(R.id.home_fragment_button_start_test));
        btnResults = (Button)(view.findViewById(R.id.home_fragment_button_show_results));
        ageInput  = (EditText)(view.findViewById(R.id.age));


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!btnStart.isEnabled()) btnStart.setEnabled(true);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = (String) (view.findViewById(radioGroup.getCheckedRadioButtonId()).getTag());
                String age =  ageInput.getText().toString();
                System.out.println(age);
                if (gender == null || gender.isEmpty()) gender = "m";

                ((MainActivity) getActivity()).startInstructionFragment(gender,age);
            }
        });

        btnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startResultsFragment();
            }
        });

        return view;

    }
}
