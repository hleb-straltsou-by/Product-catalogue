package com.vg.catalogue.enums;

import com.vg.catalogue.R;
import com.vg.catalogue.dao.entries.ActiveSubstanceEntry;
import com.vg.catalogue.dao.entries.ProductEntry;

public enum ProductCategoryEnum {

    HERBICIDES {
        {
            tableName = ProductEntry.TABLE_NAME_HERBICIDES;
            initialDataScriptId = R.raw.herbicides_initial_data_sql_script;
        }
    }, FUNGICIDES {
        {
            tableName = ProductEntry.TABLE_NAME_FUNGICIDES;
        }
    }, INSECTICIDES {
        {
            tableName = ProductEntry.TABLE_NAME_INSECTICIDES;
        }
    }, GROWTH_REGULATORS {
        {
            tableName = ProductEntry.TABLE_NAME_GROWTH_REGULATORS;
        }
    }, HERBICIDES_PAV {
        {
            tableName = ProductEntry.TABLE_NAME_HERBICIDES_PAV;
        }
    }, ACTIVE_SUBSTANCES_HERBICIDES {
        {
            tableName = ActiveSubstanceEntry.TABLE_NAME_HERBICIDES;
            initialDataScriptId = R.raw.active_substance_herbicides_initial_data_sql_script;
        }
    }, ACTIVE_SUBSTANCES_FUNGICIDES {
        {
            tableName = ActiveSubstanceEntry.TABLE_NAME_FUNGICIDES;
        }
    }, ACTIVE_SUBSTANCES_INSECTICIDES {
        {
            tableName = ActiveSubstanceEntry.TABLE_NAME_INSECTICIDES;
        }
    }, ACTIVE_SUBSTANCES_GROWTH_REGULATORS {
        {
            tableName = ActiveSubstanceEntry.TABLE_NAME_GROWTH_REGULATORS;
        }
    }, ACTIVE_SUBSTANCES_HERBICIDES_PAV {
        {
            tableName = ActiveSubstanceEntry.TABLE_NAME_HERBICIDES_PAV;
        }
    };

    String tableName;

    int initialDataScriptId;

    public String getTableName(){
        return tableName;
    }

    public int getInitialDataScriptId(){
        return initialDataScriptId;
    }
}
