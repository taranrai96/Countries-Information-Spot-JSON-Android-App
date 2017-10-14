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
        String selectedCountry_name = getIntent().getExtras().getString("countrySelected_name");
        String selectedCountry_capital = getIntent().getExtras().getString("countrySelected_capital");
        String selectedCountry_region = getIntent().getExtras().getString("countrySelected_region");
        String selectedCountry_population = getIntent().getExtras().getString("countrySelected_population");
        String selectedCountry_area = getIntent().getExtras().getString("countrySelected_area");
        String selectedCountry_borders = getIntent().getExtras().getString("countrySelected_borders");
        String selectedCountry_flag = getIntent().getExtras().getString("countrySelected_flag");

        ImageView flag = (ImageView) findViewById(R.id.flag);

        new ImageDownloaderTask(flag).execute(selectedCountry_flag);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText("Name: " + selectedCountry_name);

        TextView capital = (TextView) findViewById(R.id.capital);
        capital.setText("Capital: " + selectedCountry_capital);

        TextView region = (TextView) findViewById(R.id.region);
        region.setText("Region: " + selectedCountry_region);

        TextView population = (TextView) findViewById(R.id.population);
        population.setText("Population: " + selectedCountry_population);

        TextView area = (TextView) findViewById(R.id.area);
        area.setText("Area: " + selectedCountry_area);

        TextView borders = (TextView) findViewById(R.id.borders);
        borders.setText("Borders: " + selectedCountry_borders);

    }
}
