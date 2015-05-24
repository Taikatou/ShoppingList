package com.example.ul.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        np.setValue(20);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}

