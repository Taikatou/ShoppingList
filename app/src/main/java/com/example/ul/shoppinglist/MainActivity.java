package com.example.ul.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private int limit;
    private ProgressBar mProgress;
    public int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        // ADD HERE
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("Bread");
        items.add("Milk");
        limit = 2;
        amount = 20;
        mProgress.setProgress((100 / amount) * limit);
        // Setup remove listener method call
        setupListViewListener();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                settingsIntent.putExtra( "amount", "" + amount );
                startActivity(settingsIntent);
                return true;

            default:
                return false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            amount = Integer.parseInt(extras.getString("amount"));
            if(amount <= 0)
            {
                amount = 20;
            }
            mProgress.setProgress((100 / amount) * limit);
        }
        else
        {
            //..oops!
        }
        mProgress.setProgress((100 / amount) * limit);

    }

    // Attaches a long click listener to the listview
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        limit -= 1;
                        mProgress.setProgress((100 / amount) * limit);
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }

                });

    }

    public void onAddItem(View v) {

        if(limit < amount) {
            EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
            String itemText = etNewItem.getText().toString();
            if(itemText.length() > 0) {
                itemsAdapter.add(itemText);
                etNewItem.setText("");
                limit++;
                mProgress.setProgress((100 / amount) * limit);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "Reached maximum of " + amount + " in list", Toast.LENGTH_LONG).show();
        }


    }

}