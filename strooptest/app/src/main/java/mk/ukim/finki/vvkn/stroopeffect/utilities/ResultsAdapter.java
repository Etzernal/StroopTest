package mk.ukim.finki.vvkn.stroopeffect.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import mk.ukim.finki.vvkn.stroopeffect.R;
import mk.ukim.finki.vvkn.stroopeffect.models.Result;

public class ResultsAdapter extends ArrayAdapter<Result> {
    private List<Result> results;

    public ResultsAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
        results = objects;
    }

    @Override
    public Result getItem(int position) {
        return results.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listview_item_results, null);
        }

        Result result = results.get(position);

        TextView txtViewLettersCongruentError = (TextView) v.findViewById(R.id.results_fragment_letters_error_congruent);
        TextView txtViewLettersCongruentTime = (TextView) v.findViewById(R.id.results_fragment_letters_time_congruent);
        TextView txtViewLettersIncongruentError = (TextView) v.findViewById(R.id.results_fragment_letters_error_incongruent);
        TextView txtViewLettersIncongruentTime = (TextView) v.findViewById(R.id.results_fragment_letters_time_incongruent);

        TextView txtViewEmotionCongruentError = (TextView) v.findViewById(R.id.results_fragment_emotion_error_congruent);
        TextView txtViewEmotionCongruentTime = (TextView) v.findViewById(R.id.results_fragment_emotion_time_congruent);
        TextView txtViewEmotionIncongruentError = (TextView) v.findViewById(R.id.results_fragment_emotion_error_incongruent);
        TextView txtViewEmotionIncongruentTime = (TextView) v.findViewById(R.id.results_fragment_emotion_time_incongruent);

        TextView participantInfo = (TextView) v.findViewById(R.id.participantInfo);

        txtViewLettersCongruentError.setText("Error%: " + String.format("%.2f", result.getErrorPercentageLettersCongruent()));
        txtViewLettersCongruentTime.setText("Response time (ms): " + result.getElapsedTimeLettersCongruent());
        txtViewLettersIncongruentError.setText("Error%: " + String.format("%.2f", result.getErrorPercentageLettersIncongruent()));
        txtViewLettersIncongruentTime.setText("Response time (ms): " + result.getElapsedTimeLettersIncongruent());

        txtViewEmotionCongruentError.setText("Error%: " + String.format("%.2f", result.getErrorPercentageEmotionCongruent()));
        txtViewEmotionCongruentTime.setText("Response time (ms): " + result.getElapsedTimeEmotionCongruent());
        txtViewEmotionIncongruentError.setText("Error%: " + String.format("%.2f", result.getErrorPercentageEmotionIncongruent()));
        txtViewEmotionIncongruentTime.setText("Response time (ms): " + result.getElapsedTimeEmotionIncongruent());

        participantInfo.setText(String.format("Gender: %s ,Age: %s",result.getGender(),result.getAge()));

        LinearLayout containerLayout = (LinearLayout)v.findViewById(R.id.results_fragment_layout_container);
        if (position % 2 == 1)
        {
            containerLayout.setBackgroundColor(getContext().getResources().getColor(R.color.row_striping_odd));
        }
        else
        {
            containerLayout.setBackgroundColor(getContext().getResources().getColor(R.color.row_striping_even));
        }

        return v;
    }
}
