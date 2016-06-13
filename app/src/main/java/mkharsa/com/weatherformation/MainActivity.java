package mkharsa.com.weatherformation;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    EditText edtCity;
    ImageView searchButton ;
    MaterialDialog progressDialog;
    private RequestQueue mRequestQueue;

    /**
     * Weather information
     */
    String currentCity;
    String city;
    String currentCountry;
    int currentCode;
    static int currentTemp;
    Date currentDate;
    WeatherInformation weatherInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCity=(EditText)findViewById(R.id.edt_city);
        weatherInformation=new WeatherInformation();
        searchButton=(ImageView)findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtCity.getText().toString().isEmpty()) {
                    showProgressPopup();

                } else {
                    Toast.makeText(getApplicationContext(), "Veuillez saisir une ville!", Toast.LENGTH_SHORT).show();
                }



                MyApplication app = (MyApplication) getApplication();
                mRequestQueue = app.getVolleyRequestQueue();
                city=edtCity.getText().toString();
                new WeatherRequest().execute();

            }
        });
    }


    public void showProgressPopup() {
        progressDialog = new MaterialDialog.Builder(this)
                .content("Recherche en cours")
                .progress(true, 0)
                .widgetColorRes(R.color.colorPrimary)
                .build();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    class WeatherRequest extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * getting All fonction from url
         */
        protected String doInBackground(String... args) {

            String urlString = "http://benews.noads.biz/mob/WeatherHttp.php?city="+city;

            StringRequest request = new StringRequest(urlString,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                Log.i("JSON "," JSONResponse "+jsonResponse.toString());
                                currentCity = jsonResponse.getString("city");
                                currentCountry = jsonResponse.getString("country");
                                currentCode = jsonResponse.getInt("condition_code");

                                String  currentDatestring = jsonResponse.getString("date");

                                try {
                                    currentDate=new SimpleDateFormat("dd-MM-yyyy").parse(currentDatestring);
                                } catch (java.text.ParseException e) {
                                    e.printStackTrace();
                                }

                                currentTemp = jsonResponse.getInt("temp");
                                Log.d("Values", currentCountry + " " + currentCity + " " + currentCode + " " + currentTemp+ " "+ currentDate);


                                weatherInformation.setCurrentCity(currentCity);
                                weatherInformation.setCurrentCountry(currentCountry);
                                weatherInformation.setCurrentCode(currentCode);
                                weatherInformation.setCurrentTemp(currentTemp);
                                weatherInformation.setCurrentDate(currentDate);



                                Intent inte = new Intent(MainActivity.this, ResultActivity.class);

                                // Create a Bundle and Put Bundle in to it
                                //Bundle bundleObject = new Bundle();

                                // Put Bundle in to Intent and call start Activity
                                //inte.putExtras(bundleObject);
                                // inte.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(inte);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.i("OnError "," responseData "+volleyError.getMessage());
                }
            });
            mRequestQueue.add(request);
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {

        }
    }
}
