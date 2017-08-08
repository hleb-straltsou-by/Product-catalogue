package com.vg.catalogue.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class Product {

    private String mName;

    private String mAllNames;

    private String mConsumptionRateAndProcessedCultures;

    private String mHarmfulOrganismOrDisease;

    private String mOperatingPrinciple;

    private int mDaysTillLastHarvest;

    private int mTreatmentsMultiplicity;

    private int mActiveSubstanceId;

    public Product(){
        mName = "";
        mAllNames = "";
        mConsumptionRateAndProcessedCultures = "";
        mHarmfulOrganismOrDisease = "";
        mOperatingPrinciple = "";
        mDaysTillLastHarvest = 0;
        mTreatmentsMultiplicity = 0;
        mActiveSubstanceId = 0;
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

    public String getConsumptionRateAndProcessedCultures() {
        return mConsumptionRateAndProcessedCultures;
    }

    public void setConsumptionRateAndProcessedCultures(String consumptionRateAndProcessedCultures) {
        mConsumptionRateAndProcessedCultures = consumptionRateAndProcessedCultures;
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

    public int getActiveSubstanceId() {
        return mActiveSubstanceId;
    }

    public void setActiveSubstanceId(int activeSubstanceId) {
        mActiveSubstanceId = activeSubstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(mDaysTillLastHarvest, product.mDaysTillLastHarvest)
                .append(mTreatmentsMultiplicity, product.mTreatmentsMultiplicity)
                .append(mActiveSubstanceId, product.mActiveSubstanceId)
                .append(mName, product.mName)
                .append(mAllNames, product.mAllNames)
                .append(mConsumptionRateAndProcessedCultures, product.mConsumptionRateAndProcessedCultures)
                .append(mHarmfulOrganismOrDisease, product.mHarmfulOrganismOrDisease)
                .append(mOperatingPrinciple, product.mOperatingPrinciple)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(mName)
                .append(mAllNames)
                .append(mConsumptionRateAndProcessedCultures)
                .append(mHarmfulOrganismOrDisease)
                .append(mOperatingPrinciple)
                .append(mDaysTillLastHarvest)
                .append(mTreatmentsMultiplicity)
                .append(mActiveSubstanceId)
                .toHashCode();
    }


}
