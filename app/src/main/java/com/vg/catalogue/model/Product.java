package com.vg.catalogue.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class Product {

    private UUID mId;

    private String mName;

    private String mAllNames;

    private String mCategory;

    private String mConsumptionRate;

    private String mProcessedCultures;

    private String mHarmfulOrganismOrDisease;

    private String mOperatingPrinciple;

    private int mDaysTillLastHarvest;

    private int mTreatmentsMultiplicity;

    public Product(){
        mId = UUID.randomUUID();
        mName = "";
        mAllNames = "";
        mConsumptionRate = "";
        mProcessedCultures = "";
        mCategory = "";
        mHarmfulOrganismOrDisease = "";
        mOperatingPrinciple = "";
        mDaysTillLastHarvest = 0;
        mTreatmentsMultiplicity = 0;
    }

    public Product(UUID id){
        mId = id;
        mName = "";
        mAllNames = "";
        mConsumptionRate = "";
        mProcessedCultures = "";
        mCategory = "";
        mHarmfulOrganismOrDisease = "";
        mOperatingPrinciple = "";
        mDaysTillLastHarvest = 0;
        mTreatmentsMultiplicity = 0;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAllNames() {
        return mAllNames;
    }

    public void setAllNames(String allNames) {
        mAllNames = allNames;
    }

    public String getConsumptionRate() {
        return mConsumptionRate;
    }

    public void setConsumptionRate(String consumptionRate) {
        mConsumptionRate = consumptionRate;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getProcessedCultures() {
        return mProcessedCultures;
    }

    public void setProcessedCultures(String processedCultures) {
        mProcessedCultures = processedCultures;
    }

    public String getHarmfulOrganismOrDisease() {
        return mHarmfulOrganismOrDisease;
    }

    public void setHarmfulOrganismOrDisease(String harmfulOrganismOrDisease) {
        mHarmfulOrganismOrDisease = harmfulOrganismOrDisease;
    }

    public String getOperatingPrinciple() {
        return mOperatingPrinciple;
    }

    public void setOperatingPrinciple(String operatingPrinciple) {
        mOperatingPrinciple = operatingPrinciple;
    }

    public int getDaysTillLastHarvest() {
        return mDaysTillLastHarvest;
    }

    public void setDaysTillLastHarvest(int daysTillLastHarvest) {
        mDaysTillLastHarvest = daysTillLastHarvest;
    }

    public int getTreatmentsMultiplicity() {
        return mTreatmentsMultiplicity;
    }

    public void setTreatmentsMultiplicity(int treatmentsMultiplicity) {
        mTreatmentsMultiplicity = treatmentsMultiplicity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(mDaysTillLastHarvest, product.mDaysTillLastHarvest)
                .append(mTreatmentsMultiplicity, product.mTreatmentsMultiplicity)
                .append(mId, product.mId)
                .append(mName, product.mName)
                .append(mAllNames, product.mAllNames)
                .append(mCategory, product.mCategory)
                .append(mConsumptionRate, product.mConsumptionRate)
                .append(mProcessedCultures, product.mProcessedCultures)
                .append(mHarmfulOrganismOrDisease, product.mHarmfulOrganismOrDisease)
                .append(mOperatingPrinciple, product.mOperatingPrinciple)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(mId)
                .append(mName)
                .append(mAllNames)
                .append(mCategory)
                .append(mConsumptionRate)
                .append(mProcessedCultures)
                .append(mHarmfulOrganismOrDisease)
                .append(mOperatingPrinciple)
                .append(mDaysTillLastHarvest)
                .append(mTreatmentsMultiplicity)
                .toHashCode();
    }
}
