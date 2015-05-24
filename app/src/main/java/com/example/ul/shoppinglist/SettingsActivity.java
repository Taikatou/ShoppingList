package com.example.ul.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;


public class SettingsActivity extends Activity {

    protected int amount;
    protected NumberPicker np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMaxValue(120);
        np.setMinValue(5);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            amount = Integer.parseInt(extras.getString("amount"));
        }
        np.setValue(amount);
    }

    public void addListenerOnButton(View v) {
        Intent returnActivity = new Intent(this, MainActivity.class);
        returnActivity.putExtra("amount", "" + np.getValue());
        startActivity(returnActivity);

    }
}

