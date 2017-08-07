package com.vg.catalogue.dao.entries;

import android.provider.BaseColumns;

public abstract class ProductEntry implements BaseColumns {

    public static final String TABLE_NAME_HERBICIDES = "herbicides";

    public static final String TABLE_NAME_FUNGICIDES = "fungicides";

    public static final String TABLE_NAME_INSECTICIDES = "insecticides";

    public static final String TABLE_NAME_GROWTH_REGULATORS = "growth_regulators";

    public static final String TABLE_NAME_HERBICIDES_PAV = "herbicides_pav";

    public static final String COLUMN_NAME_ENTRY_ID = "product_id";

    public static final String COLUMN_NAME_NAME = "name";

    public static final String COLUMN_NAME_ALL_NAMES = "all_names";

    public static final String COLUMN_NAME_CONSUMPTION_RATE = "consumption_rate";

    public static final String COLUMN_NAME_PROCESSED_CULTURES = "processed_cultures";

    public static final String COLUMN_NAME_HARMFUL_ORGANISM_DISEASE = "harmful_organism_disease";

    public static final String COLUMN_NAME_OPERATING_PRINCIPLE = "operating_principle";

    public static final String COLUMN_NAME_DAYS_TILL_LAST_HARVEST = "days_till_last_harvest";

    public static final String COLUMN_NAME_TREATMENTS_MULTIPLICITY = "treatments_multiplicity";

    public static final String COLUMN_NAME_ACTIVE_SUBSTANCE_ID = "active_substance_id";
}