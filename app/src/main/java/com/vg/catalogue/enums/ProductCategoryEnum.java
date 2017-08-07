package com.vg.catalogue.enums;

import com.vg.catalogue.dao.entries.ProductEntry;

public enum ProductCategoryEnum {

    HERBICIDES {
        {
            tableName = ProductEntry.TABLE_NAME_HERBICIDES;
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
    };

    String tableName;

    public String getTableName(){
        return tableName;
    }
}
