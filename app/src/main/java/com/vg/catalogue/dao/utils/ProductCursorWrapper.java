package com.vg.catalogue.dao.utils;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.vg.catalogue.dao.entries.ProductEntry;
import com.vg.catalogue.model.Product;

public class ProductCursorWrapper extends CursorWrapper {

    public ProductCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Product getProduct(){
        String name = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_NAME));
        String allNames = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_ALL_NAMES));
        String consumptionRateAndProcessedCultures = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_CONSUMPTION_RATE_AND_PROCESSED_CULTURES));
        String harmfulOrganismDisease = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_HARMFUL_ORGANISM_DISEASE));
        String operatingPrinciple = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_OPERATING_PRINCIPLE));
        int daysTillLastHarvest = getInt(
                getColumnIndex(ProductEntry.COLUMN_NAME_DAYS_TILL_LAST_HARVEST));
        int treatmentsMultiplicity = getInt(
                getColumnIndex(ProductEntry.COLUMN_NAME_TREATMENTS_MULTIPLICITY));
        int activeSubstanceId = getInt(
                getColumnIndex(ProductEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_ID));

        Product product = new Product();
        product.setName(name);
        product.setAllNames(allNames);
        product.setConsumptionRateAndProcessedCultures(consumptionRateAndProcessedCultures);
        product.setHarmfulOrganismOrDisease(harmfulOrganismDisease);
        product.setOperatingPrinciple(operatingPrinciple);
        product.setDaysTillLastHarvest(daysTillLastHarvest);
        product.setTreatmentsMultiplicity(treatmentsMultiplicity);
        product.setActiveSubstanceId(activeSubstanceId);

        return product;
    }
}