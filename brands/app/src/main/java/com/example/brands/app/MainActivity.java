package com.example.brands.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.brands.app.db.DatabaseHelper;
import com.example.brands.app.model.Product;
import com.example.brands.app.model.Products;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity implements ImageFragment.OnItemSelectedListener{
    List<String> list = Arrays.asList("watch", "car");
    private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DatabaseHelper helper = null;
    private List<Product> listOfProduct;
    private Products products;
    private Product store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_main);

        new GetContacts().execute();
    }

    @Override
    public void onRssItemSelected(String link) {
        ArticleFragment fragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.articleFragment);
        fragment.setText(link);
    }

    @Override
    public Product passObject() {
        return store;
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        private ImageView logo= (ImageView) getFragmentManager().findFragmentById(R.id.imageFragment).getView().findViewById(R.id.logoImage);
        private TextView descriptionTextView= (TextView) getFragmentManager().findFragmentById(R.id.articleFragment).getView().findViewById(R.id.article);
        private Product product;


        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
/*            ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.articleFragment);
            articleFragment.setText("");
            ImageFragment imageFragment = (ImageFragment) getFragmentManager().findFragmentById(R.id.imageFragment);*/
            product = listOfProduct.get(position);
//            imageFragment.setProduct(product);
            store=listOfProduct.get(position);
            if(listOfProduct.get(0).getType().equals("watch")){
                switch (position){
                    case 0 :
                        logo.setImageResource(R.drawable.omega);
                        break;
                }
            }else{
                switch (position){
                    case 0 :
                        logo.setImageResource(R.drawable.ford);
                        break;
                }
            }
            descriptionTextView.setText("");
            drawerLayout.closeDrawer(drawerListView);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
/*
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                products = objectMapper.readValue(getAssets().open("Json.json"), Products.class);
                listOfProduct = products.getProducts();

                for (Product product : listOfProduct) {
                    helper.getPostDAO().create(product);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ActionBar actionBar = getActionBar();
            actionBar.setCustomView(R.layout.action_bar_top);
            Spinner spinner = (Spinner) actionBar.getCustomView().findViewById(R.id.typeSpinner);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, list);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    RuntimeExceptionDao<Product, Long> postDAO = helper.getPostDAO();
                    switch (position) {
                        case 0:
                            listOfProduct = postDAO.queryForEq("type", "watch");
                            break;
                        case 1:
                            listOfProduct = postDAO.queryForEq("type", "car");
                            break;
                    }
                    drawerListViewItems = new String[listOfProduct.size()];
                    for (int i = 0; i < listOfProduct.size(); i++) {
                        drawerListViewItems[i] = listOfProduct.get(i).getName();
                    }
                    drawerListView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                            R.layout.drawer_listview_item, drawerListViewItems));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


            drawerListViewItems = getResources().getStringArray(R.array.items);
            drawerListView = (ListView) findViewById(R.id.left_drawer);
            drawerListView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                    R.layout.drawer_listview_item, drawerListViewItems));
            // 2. App Icon
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(
                    MainActivity.this,                  /* host Activity */
                    drawerLayout,         /* DrawerLayout object */
                    R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                    R.string.drawer_open,  /* "open drawer" description */
                    R.string.drawer_close  /* "close drawer" description */
            );
            drawerLayout.setDrawerListener(actionBarDrawerToggle);
            drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
            drawerListView.setOnItemClickListener(new DrawerItemClickListener());
        }
    }
}
