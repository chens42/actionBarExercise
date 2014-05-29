package com.example.brands.app.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.brands.app.R;
import com.example.brands.app.adpter.ProductAdapter;
import com.example.brands.app.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {


    public static final String FRAGMENT_ARGUMENT_PRODUCT_LIST = "productList";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        Bundle arguments = getArguments();
        Parcelable[] parcelableArray = arguments.getParcelableArray(FRAGMENT_ARGUMENT_PRODUCT_LIST);

        final List<Product> products = new ArrayList<Product>(parcelableArray.length);
        for (Parcelable parcelable : parcelableArray) {
            products.add((Product) parcelable);
        }
        listView.setAdapter(new ProductAdapter(getActivity(), products));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private FragmentTransaction fragmentTransaction;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), position + ":" + id, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putParcelable(DescriptionFragment.FRAGMENT_ARGUMENT_PRODUCT, products.get(position));
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                descriptionFragment.setArguments(bundle);
                fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, descriptionFragment, "descriptionState");
                fragmentTransaction.addToBackStack("descriptionState");
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
