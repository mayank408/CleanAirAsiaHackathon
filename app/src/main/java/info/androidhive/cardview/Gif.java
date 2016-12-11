package info.androidhive.cardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static info.androidhive.cardview.R.id.webview;


/**
 * Created by mayank on 10-12-2016.
 */

public class Gif extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif);
        TextView aqi_level = (TextView)findViewById(R.id.aqi_level);
        Intent parentIntent = getIntent();
        int AQI_Level = parentIntent.getIntExtra("AQI",350);
        aqi_level.setText(Integer.toString(AQI_Level));
        WebView webview = (WebView)findViewById(R.id.webview);
        if(webview!=null) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("http://909sickle.net/s/pollution/pollution.gif");
        }
        Button news = (Button) findViewById(R.id.news);
if(news!=null)
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gif.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
