package com.vg.catalogue.enums;

import android.content.Context;

import com.vg.catalogue.R;
import com.vg.catalogue.dao.entries.ActiveSubstanceEntry;
import com.vg.catalogue.dao.entries.ProductEntry;

import java.io.Serializable;

public enum ProductCategoryEnum implements Serializable{

    HERBICIDES {
        {
            tableName = ProductEntry.TABLE_NAME_HERBICIDES;
            initialDataScriptId = R.raw.herbicides_initial_data_sql_script;
            tableScriptId = R.raw.herbicides_table_sql_script;
            activeSubstanceTableName = ActiveSubstanceEntry.TABLE_NAME_HERBICIDES;
            tableActiveSubstanceScriptId = R.raw.active_substance_herbicides_table_sql_script;
            initialDataActiveSubstanceScriptId
                    = R.raw.active_substance_herbicides_initial_data_sql_script;
        }
    }, FUNGICIDES {
        {
            tableName = ProductEntry.TABLE_NAME_FUNGICIDES;
            initialDataScriptId = R.raw.fungicides_initial_data_sql_script;
            tableScriptId = R.raw.fungicides_table_sql_script;
            activeSubstanceTableName = ActiveSubstanceEntry.TABLE_NAME_FUNGICIDES;
            tableActiveSubstanceScriptId = R.raw.active_substance_fungicides_table_sql_script;
            initialDataActiveSubstanceScriptId
                    = R.raw.active_substance_fungicides_initial_data_sql_script;
        }
    }, INSECTICIDES {
        {
            tableName = ProductEntry.TABLE_NAME_INSECTICIDES;
            initialDataScriptId = R.raw.insecticides_initial_data_sql_script;
            tableScriptId = R.raw.insecticides_table_sql_script;
            activeSubstanceTableName = ActiveSubstanceEntry.TABLE_NAME_INSECTICIDES;
            tableActiveSubstanceScriptId = R.raw.active_substance_insecticides_table_sql_script;
            initialDataActiveSubstanceScriptId
                    = R.raw.active_substance_insecticides_initial_data_sql_script;
        }
    }, GROWTH_REGULATORS {
        {
            tableName = ProductEntry.TABLE_NAME_GROWTH_REGULATORS;
            initialDataScriptId = R.raw.growth_regulators_initial_data_sql_script;
            tableScriptId = R.raw.growth_regularors_table_sql_script;
            activeSubstanceTableName = ActiveSubstanceEntry.TABLE_NAME_GROWTH_REGULATORS;
            tableActiveSubstanceScriptId = R.raw.active_substance_growth_regulators_table_sql_script;
            initialDataActiveSubstanceScriptId
                    = R.raw.active_substance_growth_regulators_initial_data_sql_script;
        }
    }, HERBICIDES_PAV {
        {
            tableName = ProductEntry.TABLE_NAME_HERBICIDES_PAV;
            initialDataScriptId = R.raw.herbicides_pav_initial_data_sql_script;
            tableScriptId = R.raw.herbicides_pav_table_sql_script;
            activeSubstanceTableName = ActiveSubstanceEntry.TABLE_NAME_HERBICIDES_PAV;
            tableActiveSubstanceScriptId = R.raw.active_substance_herbicides_pav_table_sql_script;
            initialDataActiveSubstanceScriptId
                    = R.raw.active_substance_herbicides_pav_initial_data_sql_script;
        }
    }, EMPTY {
        {
            tableName = "";
            initialDataScriptId = 0;
            tableScriptId = 0;
            activeSubstanceTableName = "";
            tableActiveSubstanceScriptId = 0;
            initialDataActiveSubstanceScriptId = 0;
        }
    };

    String tableName;

    String activeSubstanceTableName;

    int tableScriptId;

    int initialDataScriptId;

    int tableActiveSubstanceScriptId;

    int initialDataActiveSubstanceScriptId;

    public String getTableName(){
        return tableName;
    }

    public String getActiveSubstanceTableName() {
        return activeSubstanceTableName;
    }

    public int getInitialDataScriptId(){
        return initialDataScriptId;
    }

    public int getTableScriptId(){
        return tableScriptId;
    }

    public int getTableActiveSubstanceScriptId() {
        return tableActiveSubstanceScriptId;
    }

    public int getInitialDataActiveSubstanceScriptId() {
        return initialDataActiveSubstanceScriptId;
    }

    public static String[] getProductCategoriesStrings(Context context){
        String[] categories = {
                context.getString(R.string.herbicides_ru),
                context.getString(R.string.fungicides_ru),
                context.getString(R.string.inseticides_ru),
                context.getString(R.string.growth_regulators_ru),
                context.getString(R.string.herbicides_pav_ru)
        };
        return categories;
    }

    public static ProductCategoryEnum getProductCategoryAccordingSpinnerPossition(int position){
        ProductCategoryEnum category = EMPTY;
        switch (position){
            case 0: category = HERBICIDES; break;
            case 1: category = FUNGICIDES; break;
            case 2: category = INSECTICIDES; break;
            case 3: category = GROWTH_REGULATORS; break;
            case 4: category = HERBICIDES_PAV; break;
        }
        return category;
    }
}
