package ca.bcit.ass2.rai_park;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CountrySelectActivity extends ListActivity {
    String selectedContinent;
    private String TAG = CountrySelectActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    private static String SERVICE_URL = "https://restcountries.eu/rest/v2/region/";
    ArrayList<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedContinent = getIntent().getExtras().getString(getResources().getString(R.string.intent_continentName));
        countryList = new ArrayList<Country>();
        lv = getListView();
        new GetCountries().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetCountries extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(CountrySelectActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(SERVICE_URL + selectedContinent);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    // Getting JSON Array node
                    JSONArray countryJsonArray = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < countryJsonArray.length(); i++) {
                        JSONObject c = countryJsonArray.getJSONObject(i);

                        String name = c.getString("name");
                        String capital = c.getString("capital");
                        String region = c.getString("region");
                        String population = c.getString("population");
                        String area = c.getString("area");
                        String borders = c.getString("borders");
                        String flag = c.getString("flag");

                        // tmp hash map for single contact
                        Country country = new Country();
                        country.setName(name);
                        country.setCapital(capital);
                        country.setRegion(region);
                        country.setPopulation(population);
                        country.setArea(area);
                        country.setBorders(borders);
                        country.setFlag(flag);

                        // adding each child node to HashMap key => value

                        // adding contact to contact list
                        countryList.add(country);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
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
                Log.e(TAG, "Couldn't get json from server.");
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            ArrayAdapter<Country> arrayAdapter = new ArrayAdapter<Country>(
                    CountrySelectActivity.this,
                    android.R.layout.simple_list_item_1,
                    countryList);

            // Attach the adapter to a ListView
            lv.setAdapter(arrayAdapter);
        }

    }

    protected void onListItemClick(ListView I, View v, int position, long id) {
        Intent i = new Intent(CountrySelectActivity.this, CountryDetailActivity.class);
        i.putExtra(getResources().getString(R.string.intent_countrySelected_name),countryList.get((int)id).getName());
        i.putExtra(getResources().getString(R.string.intent_countrySelected_capital),countryList.get((int)id).getCapital());
        i.putExtra(getResources().getString(R.string.intent_countrySelected_region),countryList.get((int)id).getRegion());
        i.putExtra(getResources().getString(R.string.intent_countrySelected_population),countryList.get((int)id).getPopulation());
        i.putExtra(getResources().getString(R.string.intent_countrySelected_area),countryList.get((int)id).getArea());
        i.putExtra(getResources().getString(R.string.intent_countrySelected_borders),countryList.get((int)id).getBorders());
        i.putExtra(getResources().getString(R.string.intent_countrySelected_flag),countryList.get((int)id).getFlag());
        startActivity(i);
    }
}
