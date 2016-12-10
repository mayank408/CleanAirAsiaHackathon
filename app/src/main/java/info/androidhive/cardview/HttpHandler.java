package info.androidhive.cardview;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by Dell on 07-Dec-16.
 */

public class HttpHandler {
    HttpHandler()
    {

    }
    public String makeServiceCall(String reqUrl)
    {
        String response = null;
        try
        {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(is);
        }
        catch (Exception e)
        {
            Log.e(TAG, "makeServiceCall: " + "Error in make service Call" );
        }
        return  response;
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
