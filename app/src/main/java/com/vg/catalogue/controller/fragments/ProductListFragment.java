package com.vg.catalogue.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vg.catalogue.controller.activities.MainActivity;
import com.vg.catalogue.dao.implementations.SQLiteProductDao;
import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.Product;
import com.vg.catalogue.R;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private RecyclerView mProductRecycleView;

    private ProductAdapter mAdapter;

    private ProductDao mProductDao;

    private String mFilterPatternName;

    private static final String KEY_FILTER_PATTERN_NAME = "filter_pattern_name";

    private static final String DEFAULT_FILTER_PATTERN_NAME = "%";

    private static final String TAG = "ProductListFragment";

    public static boolean isFullSearchPerform = false;

    public static ProductListFragment newInstance(String filterPatternName) {
        // Initializing of bundle for fragment
        Bundle args = new Bundle();
        args.putString(KEY_FILTER_PATTERN_NAME, filterPatternName);

        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        mProductRecycleView = (RecyclerView) view.findViewById(R.id.product_recycler_view);

        // RecycleView delegates LayoutManager management of widgets
        // LinearLayoutManager dispose elements using vertical list
        mProductRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize MomentDao object
        mProductDao = SQLiteProductDao.getInstance(getActivity().getApplicationContext());

        if (savedInstanceState == null) {
            mFilterPatternName = getArguments().getString(KEY_FILTER_PATTERN_NAME,
                    DEFAULT_FILTER_PATTERN_NAME);
        } else {
            mFilterPatternName = savedInstanceState.getString(KEY_FILTER_PATTERN_NAME,
                    DEFAULT_FILTER_PATTERN_NAME);
        }

        return view;
    }

    public static void setFullSearchPerformed(boolean isPerformed){
        isFullSearchPerform = isPerformed;
    }

    private void updateUI(String filterPatternName) {
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;
        List<Product> products = filterProducts(mProductDao.findProducts(filterPatternName, categoryEnum));
        Log.d(TAG, "Count of products after query: " + products.size());

        if (mAdapter == null) {
            mAdapter = new ProductAdapter(products);
            mProductRecycleView.setAdapter(mAdapter);
        } else {
            mAdapter.mProducts = products;
            mAdapter.notifyDataSetChanged();
        }
    }

    private void updateUI(String culture, String harmfulOrganism, String allNames,
                          String activeSubstance){
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        int[] activeSubstancesIds = mProductDao.findActiveSubstanceIdsByName(
                activeSubstance, ProductCategoryEnum.ACTIVE_SUBSTANCES_HERBICIDES);

        List<Product> products = new ArrayList<>();
        for(int i = 0; i < activeSubstancesIds.length; i++){
            products.addAll(mProductDao.findProducts(culture, harmfulOrganism,
                    allNames, activeSubstancesIds[i], categoryEnum));
        }
        products = filterProducts(products);
        Log.d(TAG, "Count of products after query: " + products.size());

        if(products.size() == 0){
            Product product = new Product();
            product.setName(getString(R.string.no_items_found_label));
            products.add(product);
        }
        if (mAdapter == null) {
            mAdapter = new ProductAdapter(products);
            mProductRecycleView.setAdapter(mAdapter);
        } else {
            mAdapter.mProducts = products;
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isFullSearchPerform){
            updateUI(FullSearchFragment.culture, FullSearchFragment.harmfulOrganism,
                    FullSearchFragment.allNames, FullSearchFragment.activeSubstance);
            FullSearchFragment.culture = "";
            FullSearchFragment.harmfulOrganism = "";
            FullSearchFragment.allNames = "";
            FullSearchFragment.activeSubstance = "";
            setFullSearchPerformed(false);

        } else {
            updateUI(mFilterPatternName);
        }

        deleteIllegalProducts();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FILTER_PATTERN_NAME, mFilterPatternName);
    }

    private List<Product> filterProducts(List<Product> products){
        List<Product> resultList = new ArrayList<>();
        String tempName = "";
        for(Product product : products){
            if(!product.getName().equals(tempName)){
                resultList.add(product);
                tempName = product.getName();
            }
        }
        return resultList;
    }

    private void deleteIllegalProducts(){
        mProductDao.deleteProduct("", ProductCategoryEnum.HERBICIDES);
    }

    private class ProductHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Product mProduct;

        private TextView mTitleTextView;

        public ProductHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(
                    R.id.list_item_product_title_text_view);

            itemView.setOnClickListener(this);
        }

        public void bindProduct(Product product) {
            mProduct = product;
            mTitleTextView.setText(mProduct.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = MainActivity.newIntent(getActivity(), ProductFragment.class, mProduct);
            startActivity(intent);
        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {

        private List<Product> mProducts;

        public ProductAdapter(List<Product> products) {
            mProducts = products;
        }

        @Override
        public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_product, parent, false);
            return new ProductHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductHolder holder, int position) {
            Product product = mProducts.get(position);
            holder.bindProduct(product);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }
    }
}