package com.sreeramu.spinner;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.sreeramu.spinner.customspinner.CustomSpinner;

public class MainActivity extends AppCompatActivity {
    private static final String[] ANDROID_VERSIONS = {
            "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat",
            "Lollipop", "Marshmallow"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomSpinner spinner = (CustomSpinner) findViewById(R.id.spinner);
        spinner.setItems(ANDROID_VERSIONS);
        spinner.setOnItemSelectedListener(new CustomSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(CustomSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner.setOnNothingSelectedListener(new CustomSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(CustomSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
