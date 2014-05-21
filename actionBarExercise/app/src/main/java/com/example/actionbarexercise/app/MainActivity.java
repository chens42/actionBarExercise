package com.example.actionbarexercise.app;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;

import com.example.actionbarexercise.app.db.DatabaseHelper;
import com.example.actionbarexercise.app.model.Spin;
import com.example.actionbarexercise.app.model.Spins;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
    private static String url = "https://raw.githubusercontent.com/chens42/android-practise/master/puppy/app/tumblr.json";
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private String[] tabs = {"Tab 1", "Tab 2", "Tab 3"};
    private List<Spin> navSpin;
    private TitleNavigationAdapter adapter;
    private Spins spins= null;
    DatabaseHelper helper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new DatabaseHelper(getApplicationContext());

        ActionBar action = getActionBar();
        action.setCustomView(R.layout.actionbar_top); //load your layout
        action.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_CUSTOM);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(mWifi.isConnected()){
            new getContent().execute();
        }else{
            navSpin=helper.getPostDAO().queryForAll();
            adapter = new TitleNavigationAdapter(getApplicationContext(), navSpin);
            Spinner spinner = (Spinner) findViewById(R.id.display);
            spinner.setAdapter(adapter);
        }



        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                actionBar.setSelectedNavigationItem(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }


    public class getContent extends AsyncTask<Void, Void, Spins> {


        @Override
        protected Spins doInBackground(Void... params) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse;
            HttpEntity httpEntity;
            String jsonStr = null;

            try {
                httpResponse = httpClient.execute(httpGet);
                httpEntity = httpResponse.getEntity();
                jsonStr = EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("size:",""+(jsonStr==null));
            if (jsonStr != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                try {
                    spins = objectMapper.readValue(jsonStr, Spins.class);
                    for ( Spin spin:spins.getSpins()){
                        if(helper.getPostDAO().queryForId(spin.getId())==null){
                            helper.getPostDAO().create(spin);
                        }
                    }
                    return spins;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Spins spins) {
            super.onPostExecute(spins);
            navSpin=spins.getSpins();
            adapter = new TitleNavigationAdapter(getApplicationContext(), navSpin);
            Spinner spinner = (Spinner) findViewById(R.id.display);
            spinner.setAdapter(adapter);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

}
