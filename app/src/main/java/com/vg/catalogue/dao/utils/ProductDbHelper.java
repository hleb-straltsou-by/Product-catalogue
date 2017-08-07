package com.vg.catalogue.dao.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vg.catalogue.dao.entries.ActiveSubstanceEntry;
import com.vg.catalogue.dao.entries.ProductEntry;
import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.ActiveSubstance;
import com.vg.catalogue.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;

    public static final String DATABASE_NAME = "product.catalogue.db";

    private static ProductDbHelper INSTANCE;

    private SQLiteDatabase mDatabase;

    private static final int INITIAL_ACTIVE_SUBSTANCE_ID = 1;

    public static ProductDbHelper getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new ProductDbHelper(context.getApplicationContext());
        }
        return INSTANCE;
    }

    private ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mDatabase = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        // creating of active_substances_herbicides table
        db.execSQL("CREATE TABLE " + ActiveSubstanceEntry.TABLE_NAME_HERBICIDES + " (" +
                ActiveSubstanceEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                ActiveSubstanceEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_NAME + " TEXT " +
                " )");

        // fill her with test data
        ActiveSubstance substance = new ActiveSubstance(INITIAL_ACTIVE_SUBSTANCE_ID);
        ContentValues values = getContentValues(substance);
        db.insert(ActiveSubstanceEntry.TABLE_NAME_HERBICIDES, null, values);

        // creating of herbicides table
        db.execSQL("CREATE TABLE " + ProductEntry.TABLE_NAME_HERBICIDES + " (" +
                ProductEntry.COLUMN_NAME_ENTRY_ID + " TEXT, " +
                ProductEntry.COLUMN_NAME_NAME + " TEXT, " +
                ProductEntry.COLUMN_NAME_ALL_NAMES + " TEXT, " +
                ProductEntry.COLUMN_NAME_CONSUMPTION_RATE + " TEXT, " +
                ProductEntry.COLUMN_NAME_PROCESSED_CULTURES + " TEXT, " +
                ProductEntry.COLUMN_NAME_HARMFUL_ORGANISM_DISEASE + " TEXT, " +
                ProductEntry.COLUMN_NAME_OPERATING_PRINCIPLE + " TEXT, " +
                ProductEntry.COLUMN_NAME_DAYS_TILL_LAST_HARVEST + " INTEGER, " +
                ProductEntry.COLUMN_NAME_TREATMENTS_MULTIPLICITY + " INTEGER, " +
                ProductEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_ID + " INTEGER, " +
                "FOREIGN KEY(" + ProductEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_ID + ") REFERENCES " +
                ActiveSubstanceEntry.TABLE_NAME_HERBICIDES + "(" + ActiveSubstanceEntry.COLUMN_NAME_ENTRY_ID + ")" +
                " )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + ActiveSubstanceEntry.TABLE_NAME_HERBICIDES);
        db.execSQL("DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME_HERBICIDES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertProduct(Product product, ProductCategoryEnum categoryEnum){
        ContentValues values = getContentValues(product);
        long id = mDatabase.insert(categoryEnum.getTableName(), null, values);
        return id;
    }

    public void deleteProduct(UUID id, ProductCategoryEnum categoryEnum){
        // Define 'where' part of query.
        String selection = ProductEntry.COLUMN_NAME_ENTRY_ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id.toString()) };
        // Issue SQL statement.
        mDatabase.delete(categoryEnum.getTableName(), selection, selectionArgs);

    }

    public void deleteAllProducts(ProductCategoryEnum categoryEnum){
        // Issue SQL statement.
        mDatabase.delete(categoryEnum.getTableName(), null, null);

    }

    public int updateProduct(UUID id, Product product, ProductCategoryEnum categoryEnum){
        // New values for one row
        ContentValues values = getContentValues(product);

        // Which row to update, based on the ID
        String selection = ProductEntry.COLUMN_NAME_ENTRY_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        int count = mDatabase.update(
                categoryEnum.getTableName(),
                values,
                selection,
                selectionArgs
        );

        return count;
    }

    public Product getProduct(UUID id, ProductCategoryEnum categoryEnum){
        String whereClause = ProductEntry.COLUMN_NAME_ENTRY_ID + " = ?";
        String whereArgs[] = { id.toString() };
        ProductCursorWrapper cursor = queryProducts(whereClause, whereArgs, categoryEnum);
        List<Product> products = parseProducts(cursor);
        if(products.size() == 0){
            return new Product();
        }
        return products.get(0);
    }

    public List<Product> getProducts(ProductCategoryEnum categoryEnum) {
        ProductCursorWrapper cursor = queryProducts(null, null, categoryEnum);
        return parseProducts(cursor);
    }

    private ProductCursorWrapper queryProducts(String whereClause, String[] whereArgs,
                                               ProductCategoryEnum categoryEnum) {
        Cursor cursor = mDatabase.query(
                categoryEnum.getTableName(),
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new ProductCursorWrapper(cursor);
    }

    private ContentValues getContentValues(Product product){
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_NAME_ENTRY_ID, product.getId().toString());
        values.put(ProductEntry.COLUMN_NAME_NAME, product.getName());
        values.put(ProductEntry.COLUMN_NAME_ALL_NAMES, product.getAllNames());
        values.put(ProductEntry.COLUMN_NAME_CONSUMPTION_RATE, product.getConsumptionRate());
        values.put(ProductEntry.COLUMN_NAME_PROCESSED_CULTURES, product.getProcessedCultures());
        values.put(ProductEntry.COLUMN_NAME_HARMFUL_ORGANISM_DISEASE, product.getHarmfulOrganismOrDisease());
        values.put(ProductEntry.COLUMN_NAME_OPERATING_PRINCIPLE, product.getOperatingPrinciple());
        values.put(ProductEntry.COLUMN_NAME_DAYS_TILL_LAST_HARVEST, product.getDaysTillLastHarvest());
        values.put(ProductEntry.COLUMN_NAME_TREATMENTS_MULTIPLICITY, product.getTreatmentsMultiplicity());
        values.put(ProductEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_ID, product.getActiveSubstanceId());

        return values;
    }

    private ContentValues getContentValues(ActiveSubstance substance){
        ContentValues values = new ContentValues();
        values.put(ActiveSubstanceEntry.COLUMN_NAME_ENTRY_ID, substance.getId());
        values.put(ActiveSubstanceEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_NAME, substance.getName());

        return values;
    }

    private List<Product> parseProducts(ProductCursorWrapper cursor){
        List<Product> products = new ArrayList<>();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                products.add(cursor.getProduct());
                cursor.moveToNext();
            }
        } finally {
            cursor.close(); // never forger to close cursor
        }
        return products;
    }
}
