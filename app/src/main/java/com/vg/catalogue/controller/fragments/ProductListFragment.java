package com.vg.catalogue.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vg.catalogue.controller.activities.MainActivity;
import com.vg.catalogue.dao.implementations.EmbeddedProductDao;
import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.model.Product;
import com.vg.catalogue.R;

import java.util.List;

public class ProductListFragment extends Fragment {

    private RecyclerView mProductRecycleView;

    private ProductAdapter mAdapter;

    private ProductDao mMomentDao;

    public static ProductListFragment newInstance() {
        // Initializing of bundle for fragment
        Bundle args = new Bundle();

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
        mMomentDao = EmbeddedProductDao.getInstance();

        updateUI();

        return view;
    }

    private void updateUI() {
        List<Product> products = mMomentDao.getAllProducts();

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
        updateUI();
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
            Intent intent = MainActivity.newIntent(getActivity(), ProductFragment.class);
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