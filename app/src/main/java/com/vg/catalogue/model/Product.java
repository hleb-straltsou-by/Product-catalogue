package com.vg.catalogue.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class Product {

    private UUID mId;

    private String mTitle;

    private String mInfo;

    public Product(){
        mId = UUID.randomUUID();
        mTitle = "";
        mInfo = "";
    }

    public Product(UUID id){
        mId = id;
        mTitle = "";
        mInfo = "";
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(mId, product.mId)
                .append(mTitle, product.mTitle)
                .append(mInfo, product.mInfo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(mId)
                .append(mTitle)
                .append(mInfo)
                .toHashCode();
    }
}
