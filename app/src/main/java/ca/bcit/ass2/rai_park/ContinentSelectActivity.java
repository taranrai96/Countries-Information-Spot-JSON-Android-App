package ca.bcit.ass2.rai_park;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ContinentSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent_select);
        final ListView list_continents = (ListView)findViewById(R.id.list_continents);
        list_continents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Clicked::::" + i + "\n");
                Intent intent = new Intent(ContinentSelectActivity.this, CountrySelectActivity.class);
                String selectedContinent = list_continents.getItemAtPosition(i).toString();
                //System.out.println("____----____---___ " + selectedContinent + "   000000   ");
                intent.putExtra("id",i);
                intent.putExtra("continentName", selectedContinent);

                startActivity(intent);
            }
        });
    }
}
