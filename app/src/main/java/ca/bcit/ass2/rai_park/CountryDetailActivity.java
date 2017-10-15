package ca.bcit.ass2.rai_park;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);
        String selectedCountry_name = getIntent().getExtras().getString(getResources().getString(R.string.intent_countrySelected_name));
        String selectedCountry_capital = getIntent().getExtras().getString(getResources().getString(R.string.intent_countrySelected_capital));
        String selectedCountry_region = getIntent().getExtras().getString(getResources().getString(R.string.intent_countrySelected_region));
        String selectedCountry_population = getIntent().getExtras().getString(getResources().getString(R.string.intent_countrySelected_population));
        String selectedCountry_area = getIntent().getExtras().getString(getResources().getString(R.string.intent_countrySelected_area));
        String selectedCountry_borders = getIntent().getExtras().getString(getResources().getString(R.string.intent_countrySelected_borders));
        String selectedCountry_flag = getIntent().getExtras().getString(getResources().getString(R.string.intent_countrySelected_flag));

        ImageView flag = (ImageView) findViewById(R.id.flag);
        new ImageDownloaderTask(flag).execute(selectedCountry_flag);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(getResources().getString(R.string.country_name) + selectedCountry_name);

        TextView capital = (TextView) findViewById(R.id.capital);
        capital.setText(getResources().getString(R.string.country_capital) + selectedCountry_capital);

        TextView region = (TextView) findViewById(R.id.region);
        region.setText(getResources().getString(R.string.country_region) + selectedCountry_region);

        TextView population = (TextView) findViewById(R.id.population);
        population.setText(getResources().getString(R.string.country_population) + selectedCountry_population);

        TextView area = (TextView) findViewById(R.id.area);
        area.setText(getResources().getString(R.string.country_area) + selectedCountry_area);

        TextView borders = (TextView) findViewById(R.id.borders);
        borders.setText(getResources().getString(R.string.country_borders) + selectedCountry_borders);
    }
}
