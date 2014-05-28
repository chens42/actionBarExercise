package com.example.brands.app.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.brands.app.R;
import com.example.brands.app.db.DatabaseHelper;
import com.example.brands.app.fragment.DescriptionFragment;
import com.example.brands.app.fragment.ProductListFragment;
import com.example.brands.app.model.Product;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.brands.app.fragment.ProductListFragment.FRAGMENT_ARGUMENT_PRODUCT_LIST;
import static com.example.brands.app.fragment.ProductListFragment.state;

public class MainActivity extends Activity {
    List<String> list = Arrays.asList("watch", "car");
    private List<String> drawerListViewItems = new ArrayList<String>();
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DatabaseHelper helper = null;
    FrameLayout displayFragmentLayout;
    Fragment listFragment;
    private ArrayAdapter adapter;
    Spinner spinner;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_main);

        displayFragmentLayout = (FrameLayout) findViewById(R.id.fragment_container);
//        descriptionFragment = getFragmentManager().findFragmentById(R.id.descriptionFragment);
//        new GetContacts().execute();
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.action_bar_top);
        spinner = (Spinner) actionBar.getCustomView().findViewById(R.id.typeSpinner);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RuntimeExceptionDao<Product, Long> postDAO = helper.getPostDAO();
                Bundle bundle = new Bundle();
                ProductListFragment productListFragment = new ProductListFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        bundle.putParcelableArray(FRAGMENT_ARGUMENT_PRODUCT_LIST, getProductParcelableArray(postDAO.queryForEq("type", "watch")));
                        productListFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.fragment_container, productListFragment,"listState");
//                        fragmentTransaction.addToBackStack("listState");
                        fragmentTransaction.commit();

                        break;
                    case 1:
                        bundle.putParcelableArray(FRAGMENT_ARGUMENT_PRODUCT_LIST, getProductParcelableArray(postDAO.queryForEq("type", "car")));
                        productListFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.fragment_container, productListFragment,"listState");
//                        fragmentTransaction.addToBackStack("listState");
                        fragmentTransaction.commit();

                        break;
                }

            }
            private Parcelable[] getProductParcelableArray(List<Product> products) {
                Parcelable[] result = new Parcelable[products.size()];
                for (int i = 0; i < products.size(); i++) {
                    result[i] = products.get(i);
                }
                return result;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

/*        private ImageView logo = (ImageView) getFragmentManager().findFragmentById(R.id.imageFragment).getView().findViewById(R.id.logoImage);

        private TextView descriptionTextView = (TextView) getFragmentManager().findFragmentById(R.id.articleFragment).getView().findViewById(R.id.article);
        private Product product;*/


        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
/*            ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.articleFragment);
            articleFragment.setText("");
            ImageFragment imageFragment = (ImageFragment) getFragmentManager().findFragmentById(R.id.imageFragment);
            product = listOfProduct.get(position);
            imageFragment.setProduct(product);
            store = listOfProduct.get(position);
            if (listOfProduct.get(0).getType().equals("watch")) {
                switch (position) {
                    case 0:

                        logo.setImageResource(R.drawable.omega);

                        break;
                }
            } else {
                switch (position) {
                    case 0:
                        logo.setImageResource(R.drawable.ford);
                        break;
                }
            }
            descriptionTextView.setText("");
            drawerLayout.closeDrawer(drawerListView);*/

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
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }

}