package com.vg.catalogue.dao.utils;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.vg.catalogue.dao.entries.ProductEntry;
import com.vg.catalogue.model.Product;

import java.util.UUID;

public class ProductCursorWrapper extends CursorWrapper {

    public ProductCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Product getProduct(){
        String uuidStr = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_ENTRY_ID));
        String name = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_NAME));
        String allNames = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_ALL_NAMES));
        String consumptionRate = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_CONSUMPTION_RATE));
        String processedCultures = getString(
                getColumnIndex(ProductEntry.COLUMN_NAME_PROCESSED_CULTURES));
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

        Product product = new Product(UUID.fromString(uuidStr));
        product.setName(name);
        product.setAllNames(allNames);
        product.setConsumptionRate(consumptionRate);
        product.setProcessedCultures(processedCultures);
        product.setHarmfulOrganismOrDisease(harmfulOrganismDisease);
        product.setOperatingPrinciple(operatingPrinciple);
        product.setDaysTillLastHarvest(daysTillLastHarvest);
        product.setTreatmentsMultiplicity(treatmentsMultiplicity);
        product.setActiveSubstanceId(activeSubstanceId);

        return product;
    }
}