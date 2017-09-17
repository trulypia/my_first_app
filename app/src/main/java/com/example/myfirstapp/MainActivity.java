package com.example.myfirstapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);

        // ------------ SEARCH ------------
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);

        // Configure the search info and add any event listeners...

        // Define the listener
        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                String message = "onMenuItemActionCollapse";
                TextView textView = (TextView) findViewById(R.id.textViewMain);
                textView.setText(message);
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                String message = "onMenuItemActionExpand";
                TextView textView = (TextView) findViewById(R.id.textViewMain);
                textView.setText(message);
                return true;  // Return true to expand action view
            }
        };

        // Assign the listener to that action item
        MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);

        // Get the search string
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                String message = "onQueryTextChange: " + newText;
                TextView textView = (TextView) findViewById(R.id.textViewMain);
                textView.setText(message);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                String message = "onQueryTextSubmit: " + query;
                TextView textView = (TextView) findViewById(R.id.textViewMain);
                textView.setText(message);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        // Any other things you have to do when creating the options menu…

        return true;
    }

    public void onGroupItemClick(MenuItem item) {
        // One of the group items (using the onClick attribute) was clicked
        // The item parameter passed here indicates which item it is
        // All other menu item clicks are handled by onOptionsItemSelected()
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        Resources res = getResources();
        String text;
        switch (item.getItemId()) {
            case R.id.action_favorite:
                text = res.getString(R.string.action_favorite);
                intent.putExtra(EXTRA_MESSAGE, text);
                startActivity(intent);
                return true;

            case R.id.action_share:
                text = res.getString(R.string.action_share);
                intent.putExtra(EXTRA_MESSAGE, text);
                startActivity(intent);
                return true;

            case R.id.action_settings:
                text = res.getString(R.string.action_settings);
                intent.putExtra(EXTRA_MESSAGE, text);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
