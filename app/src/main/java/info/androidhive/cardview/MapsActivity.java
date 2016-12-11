package info.androidhive.cardview;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static info.androidhive.cardview.R.layout.gif;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GPSTracker gps;
    private double longitude,latitude;
    Geocoder mGeoCoder;
    Toolbar toolbar ;
    Integer val;
    String origin;
    Integer[] ppm = {295,534,290,270,340,999,420,275,496,29,594};
    //Values from aqicn.org
    Integer[] ppmSort = ppm;

    ArrayList<String> url_list;
    static List<Integer> list_dist;
    ArrayList<LatLng> coordinates = new ArrayList<>();
    String[] places = {"DTU","Punjabi Bagh","Shadipur",
            "RK Puram","Mandir Marg","Anand Vihar",
            "Siri Fort","Dwarka","Greater Kailash",
            "Saket", "Lodhi Colony"};
    Integer[] distanceSort = new Integer[places.length];
    String[] sortPlaces = places;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        init();
        setSupportActionBar(toolbar);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void init() {
        gps = new GPSTracker(MapsActivity.this);
        mGeoCoder = new Geocoder(MapsActivity.this);
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        url_list = new ArrayList<>();
        list_dist = new ArrayList<>();
        coordinates.add(new LatLng(28.7500749,77.1176652));
        coordinates.add(new LatLng(28.673751,77.1273376));
        coordinates.add(new LatLng(28.651027,77.1562196));
        coordinates.add(new LatLng(28.5660075,77.1767435));
        coordinates.add(new LatLng(28.6597298,77.2827525));
        coordinates.add(new LatLng(28.650218,77.3027059));
        coordinates.add(new LatLng(28.5511631,77.2277579));
        coordinates.add(new LatLng(28.5921401,77.0460481));
        coordinates.add(new LatLng(28.5428238,77.2395479));
        coordinates.add(new LatLng(28.5245787,77.206615));
        coordinates.add(new LatLng(28.5862742,77.224306));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.next) {
            changeActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeActivity() {
        Intent i = new Intent(this,Gif.class);
        i.putExtra("AQI",val);
        startActivity(i);
    }


    private List<Address> getAddress(double lat, double lon)
    {
        try {
            List<Address> address = new ArrayList<>();
            address = mGeoCoder.getFromLocation(lat,lon,5);
          //  Log.i("SubAdminArea",address.get(0).getSubAdminArea());
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }



    private void CalculateAQI(double latitude, double longitude) {
        List<Address> address = getAddress(latitude,longitude);
        url_list.clear();
        for (int i = 0; i < coordinates.size(); i++) {
            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+coordinates.get(i).latitude+","+coordinates.get(i).longitude+"&destinations="+latitude+","+longitude;
            url_list.add(url);
            Log.e("getDistance", url_list.get(i));
        }
        new getDistance().execute(url_list);
    }


    private void calculateVal()
    {
        int i,j;
        Log.e("Size", Integer.toString(list_dist.size()));
        for(i=0;i<list_dist.size();i++) {
            distanceSort[i] = list_dist.get(i);
        }

        for(i = 0; i < coordinates.size();i++)
        {
            for(j = 0; j<coordinates.size()-i-1;j++)
            {

                if(distanceSort[j] > distanceSort[j+1])
                {
                    Integer temp = distanceSort[j];
                    distanceSort[j] = distanceSort[j+1];
                    distanceSort[j+1] = temp;

                    String x = sortPlaces[j];
                    sortPlaces[j] = sortPlaces[j+1];
                    sortPlaces[j+1] = x;

                    Integer temp2 = ppmSort[j];
                    ppmSort[j] = ppmSort[j+1];
                    ppmSort[j+1] = temp2;
                }
            }
        }
        val = (ppmSort[0]*distanceSort[0] + ppmSort[1]*distanceSort[1] + ppmSort[2]*distanceSort[2])/(distanceSort[0] + distanceSort[1] + distanceSort[2]);
        sortPlaces = places;
        ppmSort = ppm;
        val = val - 300;
        Log.e("PPM", Integer.toString(val));
        TextView aqi_text = (TextView) findViewById(R.id.aqi);
        aqi_text.setText("AQI = "+ Integer.toString(val));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(gps.canGetLocation()){
            longitude = gps.getLongitude();
            latitude = gps.getLatitude();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else {

            gps.showSettingsAlert();
        }
        // Add a marker in Current Location and move the camera
        List<Address> address = getAddress(latitude,longitude);
        LatLng currentLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Your Location").draggable(true));
        mMap.setMaxZoomPreference(20);
        mMap.setMinZoomPreference(14);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
               latitude =  marker.getPosition().latitude;
                longitude = marker.getPosition().longitude;
                CalculateAQI(latitude,longitude);
            }
        });
        CalculateAQI(latitude,longitude);
    }



    private class getDistance extends AsyncTask<ArrayList<String>,Void ,Void>
    {
        int dist;
        String response;
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            calculateVal();
        }
        @Override
        protected Void doInBackground(ArrayList<String>...url) {

            HttpHandler sh = new HttpHandler();
            list_dist.clear();
            for (int i = 0; i <coordinates.size(); i++) {
                response = sh.makeServiceCall(url[0].get(i));
                if (response != null) {
                    try {
                        JSONObject jObj = new JSONObject(response);
                        JSONArray rows = jObj.getJSONArray("rows");
                        JSONObject element = rows.getJSONObject(0);
                        JSONArray o = element.getJSONArray("elements");
                        dist = o.getJSONObject(0).getJSONObject("distance").getInt("value");
                        MapsActivity.list_dist.add(dist);
                        Log.e("Distance:", Integer.toString(dist));
                    } catch (final Exception e) {
                        Log.e("Do in Background", "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                    }
                } else {
                    Log.e("Do in Background", "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            }

            return null;
        }
    }



}
