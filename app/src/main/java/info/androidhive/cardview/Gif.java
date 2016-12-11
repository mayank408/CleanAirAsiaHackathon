package info.androidhive.cardview;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;

import android.widget.TextView;

import org.w3c.dom.Text;


import android.widget.ImageView;



/**
 * Created by mayank on 10-12-2016.
 */

public class Gif extends AppCompatActivity {
int AQI_Level;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif);

        TextView aqi_level = (TextView)findViewById(R.id.textView);
        Intent parentIntent = getIntent();
        AQI_Level = parentIntent.getIntExtra("AQI",350);
        aqi_level.setText(Integer.toString(AQI_Level));

        Button news = (Button) findViewById(R.id.news);
        Button adv = (Button) findViewById(R.id.adv);

if(news!=null)
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gif.this, MainActivity.class);
                startActivity(i);
            }
        });

        adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gif.this, advisory.class);
                startActivity(i);
            }
        });


    }

    public void share(View v)
    {

        Intent sendMailIntent = new Intent(Intent.ACTION_SEND);
        sendMailIntent.putExtra(Intent.EXTRA_SUBJECT, "Car Pool");
        sendMailIntent.putExtra(Intent.EXTRA_TEXT, "Do you want to Car Pool?");
        sendMailIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendMailIntent, "Share Using"));
        Log.e("Car Pool: ", "Clicked CP");
    }

    public void adv(View v)
    {
        Intent intent = new Intent(this, ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Value", Integer.toString(AQI_Level));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
