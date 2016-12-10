package info.androidhive.cardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;


/**
 * Created by mayank on 10-12-2016.
 */

public class Gif extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif);

        WebView webview = (WebView)findViewById(R.id.webview);
        if(webview!=null) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("http://909sickle.net/s/pollution/pollution.gif");
        }
        Button news = (Button) findViewById(R.id.news);
<<<<<<< HEAD
        Button adv = (Button) findViewById(R.id.adv);

=======
>>>>>>> 6fb36679c79d3ba560af9ac214f079eae7d292b8
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
}
