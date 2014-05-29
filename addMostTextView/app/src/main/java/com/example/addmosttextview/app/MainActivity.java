package com.example.addmosttextview.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String[] array = {"worhhhd1 ","word 1","word 1","word 1","word 1","word 1","word 2","wotd 1","word 1","word 1","word 1","word 1"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmartLayout smartLayout = (SmartLayout) findViewById(R.id.mainLayout);
        for(String element:array){
            TextView textView=new TextView(getApplicationContext());
            textView.setText(element);
            smartLayout.addView(textView);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
