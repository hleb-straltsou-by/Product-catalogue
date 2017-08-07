package com.vg.catalogue.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ActiveSubstance {

    private long mId;

    private String name;

    public ActiveSubstance(){
        this.mId = 0;
        this.name = "";
    }

    public ActiveSubstance(long id) {
        this.mId = id;
        this.name = "";
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ActiveSubstance that = (ActiveSubstance) o;

        return new EqualsBuilder()
                .append(mId, that.mId)
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(mId)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mId", mId)
                .append("name", name)
                .toString();
    }
}
