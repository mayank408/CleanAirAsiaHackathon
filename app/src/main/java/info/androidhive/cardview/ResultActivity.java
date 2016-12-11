package info.androidhive.cardview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    String val = "AQI: ";
    View v;
    TextView value;
    TextView subTitle;
    TextView Info1;
    TextView Info2;
    TextView Info3;
    TextView Info4;
    int IntegerValue;
    String subT;
    String info1;
    String info2;
    String info3;
    String info4;
    Button carPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        v = findViewById(R.id.value);
        value = (TextView)v;
        Bundle bundle = getIntent().getExtras();
        String val2 = (bundle.getString("Value"));
        val+=val2;
        value.setText(val);
        IntegerValue = Integer.parseInt(val2);
        v = findViewById(R.id.subTitle);
        subTitle = (TextView)v;
        if(IntegerValue>=0 && IntegerValue<=50)
        {
            ((TextView) v).setTextColor(Color.parseColor("green"));
            subT = "Good Air Quality";
            info1 = "Perfectly Ideal For Outdoor Activity.";
            info2 = "No issues in travelling by your own vehicle.";
            info3 = "Fit for heavy outdoor exercises.";
            info4 = "Minimal Impact";
        }
        else if(IntegerValue <= 100)
        {
            ((TextView) v).setTextColor(Color.parseColor("#a4c639"));
            subT = "Satisfactory Air Quality";
            info1 = "Fit For Most Outdoor Activity.";
            info2 = "Prefer a vehicle running on CNG.";
            info3 = "Prefer light outdoor exercises.";
            info4 = "Minor breathing discomfort to sensitive people.";
        }
        else if(IntegerValue <= 200)
        {
            ((TextView) v).setTextColor(Color.parseColor("yellow"));
            subT = "Moderately Polluted";
            info1 = "Prefer Minimal Outdoor Activities.";
            info2 = "Avoid travelling by your own vehicle, if possible.";
            info3 = "Prefer indoor exercises.";
            info4 = "Breathing discomfort to people with lung disease, and discomfort to people with heart disease, children and older adults.";
        }
        else if(IntegerValue <= 300)
        {
            ((TextView) v).setTextColor(Color.parseColor("#FFA500"));
            subT = "Poor Air Quality";
            info1 = "Only Go Outdoors For Urgent Activities.";
            info2 = "Prefer Car Pool.";
            info3 = "Light Indoor exercises.";
            info4 = "Breathing discomfort to people on prolonged exposure, and discomfort to people with heart disease.\n";
        }
        else if(IntegerValue <= 400)
        {
            ((TextView) v).setTextColor(Color.parseColor("red"));
            subT = "Very Poor Air Quality";
            info1 = "Avoid Most of the Outdoor Activities.";
            info2 = "Prefer Public Transport.";
            info3 = "Minimum Indoor exercises.";
            info4 = "Respiratory illness to the people on prolonged exposure.";
        }
        else {
            ((TextView) v).setTextColor(Color.parseColor("maroon"));
            subT = "Severe Air Quality";
            info1 = "Avoid All Outdoor Activities.";
            info2 = "Acoid All Transportation. Use only Public Transports, if required.";
            info3 = "Zero exercise. Avoid all kinds of physical exertion";
            info4 = "Respiratory impact even on healthy people, and serious health impacts on people with lung/heart disease.";
        }
        subTitle.setText(subT);
        v = findViewById(R.id.info1);
        Info1 = (TextView)v;
        Info1.setText(info1);
        v = findViewById(R.id.info2);
        Info2 = (TextView)v;
        Info2.setText(info2);
        v = findViewById(R.id.info3);
        Info3 = (TextView)v;
        Info3.setText(info3);
        v = findViewById(R.id.info4);
        Info4 = (TextView)v;
        Info4.setText(info4);


    }




}
