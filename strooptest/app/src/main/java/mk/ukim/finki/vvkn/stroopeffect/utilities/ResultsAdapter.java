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

        TextView txtViewErrorWarpedPracNeutral = (TextView) v.findViewById(R.id.results_fragment_error_warpedpracneutral);
        TextView txtViewTimeWarpedPracNeutral = (TextView) v.findViewById(R.id.results_fragment_time_warpedpracneutral);
        TextView txtViewErrorWarpedPracMixed = (TextView) v.findViewById(R.id.results_fragment_error_warpedpracmixed);
        TextView txtViewTimeWarpedPracMixed = (TextView) v.findViewById(R.id.results_fragment_time_warpedpracmixed);
        TextView txtViewErrorWarpedCongruent = (TextView) v.findViewById(R.id.results_fragment_error_warpedcongruent);
        TextView txtViewTimeWarpedCongruent = (TextView) v.findViewById(R.id.results_fragment_time_warpedcongruent);
        TextView txtViewErrorWarpedIncongruent = (TextView) v.findViewById(R.id.results_fragment_error_warpedincongruent);
        TextView txtViewTimeWarpedIncongruent = (TextView) v.findViewById(R.id.results_fragment_time_warpedincongruent);
        TextView txtViewErrorWarpedMixed = (TextView) v.findViewById(R.id.results_fragment_error_warpedmixed);
        TextView txtViewTimeWarpedMixed = (TextView) v.findViewById(R.id.results_fragment_time_warpedmixed);

        TextView txtViewErrorEmotionPracNeutral = (TextView) v.findViewById(R.id.results_fragment_error_emotionpracneutral);
        TextView txtViewTimeEmotionPracNeutral = (TextView) v.findViewById(R.id.results_fragment_time_emotionpracneutral);
        TextView txtViewErrorEmotionPracMixed = (TextView) v.findViewById(R.id.results_fragment_error_emotionpracmixed);
        TextView txtViewTimeEmotionPracMixed = (TextView) v.findViewById(R.id.results_fragment_time_emotionpracmixed);
        TextView txtViewErrorEmotionCongruent = (TextView) v.findViewById(R.id.results_fragment_error_emotioncongruent);
        TextView txtViewTimeEmotionCongruent = (TextView) v.findViewById(R.id.results_fragment_time_emotioncongruent);
        TextView txtViewErrorEmotionIncongruent = (TextView) v.findViewById(R.id.results_fragment_error_emotionincongruent);
        TextView txtViewTimeEmotionIncongruent = (TextView) v.findViewById(R.id.results_fragment_time_emotionincongruent);
        TextView txtViewErrorEmotionMixed = (TextView) v.findViewById(R.id.results_fragment_error_emotionmixed);
        TextView txtViewTimeEmotionMixed = (TextView) v.findViewById(R.id.results_fragment_time_emotionmixed);

        TextView participantInfo = (TextView) v.findViewById(R.id.participantInfo);

        txtViewErrorWarpedPracNeutral.setText("Error%: " + String.format("%.2f", result.getErrorPercentageWarpedPracNeutral()));
        txtViewTimeWarpedPracNeutral.setText("Response time (ms): " + result.getElapsedTimeWarpedPracNetural());

        txtViewErrorWarpedPracMixed.setText("Error%: " + String.format("%.2f", result.getErrorPercentageWarpedPracMixed()));
        txtViewTimeWarpedPracMixed.setText("Response time (ms): " + result.getElapsedTimeWarpedPracMixed());

        txtViewErrorWarpedCongruent.setText("Error%: " + String.format("%.2f", result.getErrorPercentageWarpedCongruent()));
        txtViewTimeWarpedCongruent.setText("Response time (ms): " + result.getElapsedTimeWarpedCongruent());

        txtViewErrorWarpedIncongruent.setText("Error%: " + String.format("%.2f", result.getErrorPercentageWarpedIncongruent()));
        txtViewTimeWarpedIncongruent.setText("Response time (ms): " + result.getElapsedTimeWarpedIncongruent());

        txtViewErrorWarpedMixed.setText("Error%: " + String.format("%.2f", result.getErrorPercentageWarpedMixed()));
        txtViewTimeWarpedMixed.setText("Response time (ms): " + result.getElapsedTimeWarpedMixed());

        txtViewErrorEmotionPracNeutral.setText("Error%: " + String.format("%.2f", result.getErrorPercentageEmotionPracNeutral()));
        txtViewTimeEmotionPracNeutral.setText("Response time (ms): " + result.getElapsedTimeEmotionPracNeutral());

        txtViewErrorEmotionPracMixed.setText("Error%: " + String.format("%.2f", result.getErrorPercentageEmotionPracMixed()));
        txtViewTimeEmotionPracMixed.setText("Response time (ms): " + result.getElapsedTimeEmotionPracMixed());

        txtViewErrorEmotionCongruent.setText("Error%: " + String.format("%.2f", result.getErrorPercentageEmotionCongruent()));
        txtViewTimeEmotionCongruent.setText("Response time (ms): " + result.getElapsedTimeEmotionCongruent());

        txtViewErrorEmotionIncongruent.setText("Error%: " + String.format("%.2f", result.getErrorPercentageEmotionIncongruent()));
        txtViewTimeEmotionIncongruent.setText("Response time (ms): " + result.getElapsedTimeEmotionIncongruent());

        txtViewErrorEmotionMixed.setText("Error%: " + String.format("%.2f", result.getErrorPercentageEmotionMixed()));
        txtViewTimeEmotionMixed.setText("Response time (ms): " + result.getElapsedTimeEmotionMixed());

        participantInfo.setText(String.format("Gender: %s ,Age: %s, Code: %s",result.getGender(),result.getAge(),result.getCode()));

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
