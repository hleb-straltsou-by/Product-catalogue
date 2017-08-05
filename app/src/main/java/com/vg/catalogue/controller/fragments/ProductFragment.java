package com.vg.catalogue.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.vg.catalogue.R;
import com.vg.catalogue.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
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

    /** Group names for expandable list view*/
    private static String[] groups;

    private static final String GROUP_ATTRIBUTE_NAME = "groupName";

    private static final String GROUP_ITEM_ATTRIBUTE_NAME = "productAttribute";

    private Product mProduct;

    private ExpandableListView mExpandableListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        groups = new String[] {
                getString(R.string.product_all_names),
                getString(R.string.product_consumption_rate),
                getString(R.string.product_processed_cultures),
                getString(R.string.product_harmful_organism_and_disease),
                getString(R.string.product_operating_principle),
                getString(R.string.product_days_till_last_harvest),
                getString(R.string.product_treatments_multiplicity)
        };

        initializeGroupsForExpandableListView();

        // list of group attributes for reading
        String groupFrom[] = {GROUP_ATTRIBUTE_NAME};

        // list of view elements ID, in which will be put group attributeMap
        int groupTo[] = new int[] {android.R.id.text1};

        initializeGroupItemsForExpandableListView();

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

        return view;
    }

    private void initializeGroupsForExpandableListView(){
        groupData = new ArrayList<>();
        for(int i = 0; i < groups.length; i++){
            // Fill list of attributeMap for each group
            attributeMap = new HashMap<>();
            attributeMap.put(GROUP_ATTRIBUTE_NAME, groups[i]);
            groupData.add(attributeMap);
        }
    }

    private void initializeGroupItemsForExpandableListView(){
        // create collection for collections of elements
        childData = new ArrayList<>(groups.length);

        // getValues for groups
        String[] values = getValuesForGroups();

        // fill list of attributeMap for each element
        for(int i = 0; i < groups.length; i++){
            attributeMap = new HashMap<>(1);
            attributeMap.put(GROUP_ITEM_ATTRIBUTE_NAME, values[i]);

            // create collection of elements for each group
            childDataItem = new ArrayList<>(1);
            childDataItem.add(attributeMap);
            childData.add(childDataItem);
        }
    }

    private String[] getValuesForGroups(){
        // Creating of test product
        mProduct = new Product();
        mProduct.setName("Test name");
        mProduct.setConsumptionRate("Test consumption rate");
        mProduct.setCategory("Test category");
        mProduct.setProcessedCultures("Test processed cultures");
        mProduct.setAllNames("Test all names");
        mProduct.setHarmfulOrganismOrDisease("Test harmful organism or disease");
        mProduct.setOperatingPrinciple("Test operating principle");
        mProduct.setDaysTillLastHarvest(4);
        mProduct.setTreatmentsMultiplicity(7);

        // Attribute values
        String[] values = {
                mProduct.getAllNames(),
                mProduct.getConsumptionRate(),
                mProduct.getProcessedCultures(),
                mProduct.getHarmfulOrganismOrDisease(),
                mProduct.getOperatingPrinciple(),
                String.valueOf(mProduct.getDaysTillLastHarvest()),
                String.valueOf(mProduct.getTreatmentsMultiplicity())
        };

        return values;
    }
}
