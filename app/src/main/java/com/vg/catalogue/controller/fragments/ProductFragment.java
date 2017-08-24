package com.vg.catalogue.controller.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.vg.catalogue.R;
import com.vg.catalogue.dao.implementations.SQLiteProductDao;
import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.ActiveSubstance;
import com.vg.catalogue.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductFragment extends Fragment {

    /** Collection for group */
    private ArrayList<Map<String, String>> groupData;

    /** Collection for items from one group */
    private ArrayList<Map<String, String>> childDataItem;

    /** Common collection for collections of elements */
    private ArrayList<ArrayList<Map<String, String>>> childData;

    /** List of attributeMap for element in group */
    private Map<String, String> attributeMap;

    private static final String GROUP_ATTRIBUTE_NAME = "groupName";

    private static final String GROUP_ITEM_ATTRIBUTE_NAME = "productAttribute";

    private static final String KEY_SELECTED_PRODUCT = "selected_product";

    private Product mProduct;

    private ProductDao mProductDao = SQLiteProductDao.getInstance(getActivity());

    private ExpandableListView mExpandableListView;

    private static final int MAIN_GROUP_COUNT = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        mProduct = (Product) getActivity().getIntent().getSerializableExtra(KEY_SELECTED_PRODUCT);

        List<Product> products = mProductDao.getProduct(mProduct.getName(),
                getCurrentProductCategory());

        initializeGroupsForExpandableListView(products);

        // list of group attributes for reading
        String groupFrom[] = {GROUP_ATTRIBUTE_NAME};

        // list of view elements ID, in which will be put group attributeMap
        int groupTo[] = new int[] {android.R.id.text1};

        initializeGroupItemsForExpandableListView(products);

        // list of attributeMap of elements for reading
        String childFrom[] = {GROUP_ITEM_ATTRIBUTE_NAME};

        // list of view elements ID, in which will be put attributeMap
        int childTo[] = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getActivity(),
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo
        );

        mExpandableListView = (ExpandableListView) view.findViewById(
                R.id.product_expandable_list_view);
        mExpandableListView.setAdapter(adapter);

        mExpandableListView.setGroupIndicator(getResources()
                .getDrawable(R.drawable.ic_product_group));
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(groupPosition == 1) {
                    return true;
                } else
                    return false;
            }
        });

        mExpandableListView.expandGroup(0);

        return view;
    }

    private void initializeGroupsForExpandableListView(List<Product> products){
        int groupCount = MAIN_GROUP_COUNT + products.size();
        groupData = new ArrayList<>(groupCount);

        // init of groups names
        String[] groupNames = new String[groupCount];
        groupNames[0] = getString(R.string.product_all_names);
        groupNames[1] = getString(R.string.product_processed_cultures);

        String[] cultures = parseCultures(products);
        for(int i = MAIN_GROUP_COUNT; i < groupCount; i++){
            groupNames[i] = cultures[i-MAIN_GROUP_COUNT];
        }

        for(int i = 0; i < groupCount; i++){
            // Fill list of attributeMap for each group
            attributeMap = new HashMap<>();
            attributeMap.put(GROUP_ATTRIBUTE_NAME, groupNames[i]);
            groupData.add(attributeMap);
        }
    }

    private void initializeGroupItemsForExpandableListView(List<Product> products){
        int groupCount = MAIN_GROUP_COUNT + products.size();

        // create collection for collections of elements
        childData = new ArrayList<>(groupCount);

        // init info for main groups

        // init all names group
        attributeMap = new HashMap<>(1);
        attributeMap.put(GROUP_ITEM_ATTRIBUTE_NAME, products.get(0).getAllNames() + "\n");
        childDataItem = new ArrayList<>(1);
        childDataItem.add(attributeMap);
        childData.add(childDataItem);

        // init culture group
        String[] cultures = parseCultures(products);
        childDataItem = new ArrayList<>(products.size());
        for(String culture : cultures) {
            attributeMap = new HashMap<>(1);
            attributeMap.put(GROUP_ITEM_ATTRIBUTE_NAME, culture);
            childDataItem.add(attributeMap);
        }
        childData.add(childDataItem);

        // get values for groups
        String[] values = getValuesForCultures(products);

        // fill list of attributeMap for each culture
        for(int i = 0; i < products.size(); i++){
            attributeMap = new HashMap<>(1);
            attributeMap.put(GROUP_ITEM_ATTRIBUTE_NAME, values[i]);

            // create collection of elements for each group
            childDataItem = new ArrayList<>(1);
            childDataItem.add(attributeMap);
            childData.add(childDataItem);
        }
    }

    private String[] getValuesForCultures(List<Product> products){
        // Get value of active substance
        long activeSubstanceId = products.get(0).getActiveSubstanceId();
        ActiveSubstance substance = mProductDao.getActiveSubstance(activeSubstanceId,
                getCurrentProductCategory());

        // create view of all cultures of product
        String[] values = new String[products.size()];

        for(int i = 0; i < values.length; i++){
            // add active substance
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.product_active_substance))
                    .append(":\n").append(substance.getName()).append("\n\n");

            // add consumption rate info
            sb.append(getString(R.string.product_consumption_rate)).append(": ").append
                    (parseConsumptionRate(products.get(i).getConsumptionRateAndProcessedCultures()));

            // add harmful organism and disease info
            sb.append("\n\n").append(getString(R.string.product_harmful_organism_and_disease)).append(":\n");
            sb.append(products.get(i).getHarmfulOrganismOrDisease()).append("\n\n");

            // add operating principle
            sb.append(getString(R.string.product_operating_principle)).append(":\n");
            sb.append(products.get(i).getOperatingPrinciple()).append("\n\n");

            // add days till last harvest
            sb.append(getString(R.string.product_days_till_last_harvest)).append(": ");
            if(products.get(i).getDaysTillLastHarvest() == 0) {
                sb.append("  -\n\n");
            } else {
                sb.append(products.get(i).getDaysTillLastHarvest()).append("\n\n");
            }

            // add treatments multiplicity
            sb.append(getString(R.string.product_treatments_multiplicity)).append(": ");
            sb.append(products.get(i).getTreatmentsMultiplicity()).append("\n");
            values[i] = sb.toString();
        }

        return values;
    }

    private String[] parseCultures(List<Product> products){
        String[] cultures = new String[products.size()];
        for(int i = 0; i < products.size(); i++){
            int bracketPosition = products.get(i)
                    .getConsumptionRateAndProcessedCultures().indexOf("(");
            cultures[i] = products.get(i)
                    .getConsumptionRateAndProcessedCultures().substring(0, bracketPosition);
        }
        return cultures;
    }

    @NonNull
    private String parseConsumptionRate(String value){
        int beginPos = value.lastIndexOf("(");
        int endPos = value.lastIndexOf(")");
        return value.substring(beginPos+1, endPos);
    }

    private ProductCategoryEnum getCurrentProductCategory(){
        return ProductCategoryEnum.HERBICIDES;
    }
}
