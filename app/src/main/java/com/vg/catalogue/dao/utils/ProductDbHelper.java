package com.vg.catalogue.dao.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vg.catalogue.dao.entries.ActiveSubstanceEntry;
import com.vg.catalogue.dao.entries.ProductEntry;
import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.ActiveSubstance;
import com.vg.catalogue.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProductDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 42;

    public static final String DATABASE_NAME = "product.catalogue.db";

    private SQLiteDatabase mDatabase;

    private Context mContext;

    private String TAG = "PPODUCT_DB_HELPER";

    public static ProductDbHelper getInstance(Context context){
        return new ProductDbHelper(context.getApplicationContext());
    }

    private ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mDatabase = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        ProductCategoryEnum[] categories = ProductCategoryEnum.values();

        // init of tables and data in all categories
//        for(ProductCategoryEnum category : categories){
//            createCategoryTablesAndInitData(db, category);
//        }
        createCategoryTablesAndInitData(db, ProductCategoryEnum.HERBICIDES);
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

    public void deleteProduct(String name, ProductCategoryEnum categoryEnum){
        // Define 'where' part of query.
        String selection = ProductEntry.COLUMN_NAME_NAME + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { name.toLowerCase() };
        // Issue SQL statement.
        mDatabase.delete(categoryEnum.getTableName(), selection, selectionArgs);

    }

    public void deleteAllProducts(ProductCategoryEnum categoryEnum){
        // Issue SQL statement.
        mDatabase.delete(categoryEnum.getTableName(), null, null);

    }

    public ActiveSubstance getActiveSubstance(long id, ProductCategoryEnum categoryEnum){
        String whereClause = ActiveSubstanceEntry.COLUMN_NAME_ENTRY_ID + " = ?";
        String whereArgs[] = {String.valueOf(id)};
        ActiveSubstanceCursorWrapper cursor = queryActiveSubstances(
                whereClause, whereArgs, categoryEnum);
        List<ActiveSubstance> substances = parseActiveSubstances(cursor);
        if(substances.size() == 0){
            return new ActiveSubstance();
        }
        return substances.get(0);
    }

    public List<Product> getProduct(String name, ProductCategoryEnum categoryEnum){
        String whereClause = ProductEntry.COLUMN_NAME_NAME + " = ?";
        String whereArgs[] = { name.toLowerCase()};
        ProductCursorWrapper cursor = queryProducts(whereClause, whereArgs, categoryEnum);
        List<Product> products = parseProducts(cursor);
        if(products.size() == 0){
            products.add(new Product());
        }
        return products;
    }

    public List<Product> findProducts(String patternName, ProductCategoryEnum categoryEnum){
        String whereClause = "LOWER(" + ProductEntry.COLUMN_NAME_NAME + ") LIKE ?";
        if(patternName == null || patternName.equals("")){
            patternName = "%";
        } else {
            patternName = "%" + patternName.toLowerCase() + "%";
        }
        String whereArgs[] = { patternName };
        ProductCursorWrapper cursor = queryProducts(whereClause, whereArgs, categoryEnum);
        return parseProducts(cursor);
    }

    public List<Product> findProducts(String culture, String harmfulOrganism, String allNames,
                                      String activeSubstanceName, ProductCategoryEnum categoryEnum) {
        String selectionArgs[] = {activeSubstanceName.toLowerCase(), culture.toLowerCase(),
                harmfulOrganism.toLowerCase(), allNames.toLowerCase()};
        String sql = "SELECT * from " + categoryEnum.getTableName() + " p JOIN " +
                categoryEnum.getActiveSubstanceTableName() + " a  ON a." +
                ActiveSubstanceEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_NAME + " LIKE ? AND p." +
                ProductEntry.COLUMN_NAME_CONSUMPTION_RATE_AND_PROCESSED_CULTURES + " LIKE ? AND p." +
                ProductEntry.COLUMN_NAME_HARMFUL_ORGANISM_DISEASE + " LIKE ? AND p." +
                ProductEntry.COLUMN_NAME_ALL_NAMES + " LIKE ?";

        ProductCursorWrapper cursor =
                new ProductCursorWrapper(mDatabase.rawQuery(sql, selectionArgs));
        List<Product> products = parseProducts(cursor);
        if(products.size() == 0){
            products.add(new Product());
        }
        return products;
    }

    public List<Product> getProducts(ProductCategoryEnum categoryEnum) {
        ProductCursorWrapper cursor = queryProducts(null, null, categoryEnum);
        return parseProducts(cursor);
    }

    public List<ActiveSubstance> getActiveSubstances(ProductCategoryEnum categoryEnum) {
        ActiveSubstanceCursorWrapper cursor = queryActiveSubstances(null, null, categoryEnum);
        return parseActiveSubstances(cursor);
    }

    public int[] findActiveSubstanceIdsByName(String name, ProductCategoryEnum categoryEnum){
        if(name.equals("%")){
            return new int[]{0};
        }
        String sql = "SELECT * from " + categoryEnum.getActiveSubstanceTableName() +
                " WHERE LOWER(" + ActiveSubstanceEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_NAME + ") " +
                "LIKE LOWER(\"" + name.toLowerCase() + "\")";

        List<ActiveSubstance> substances =
                parseActiveSubstances(new ActiveSubstanceCursorWrapper(
                        mDatabase.rawQuery(sql, null)));
        int[] correspondIds = new int[substances.size()];
        for(int i = 0 ; i < substances.size(); i++){
            correspondIds[i] = (int)substances.get(i).getId();
        }
        if(correspondIds.length == 0){
            return new int[]{0};
        }
        return correspondIds;
    }

    private ProductCursorWrapper queryProducts(String whereClause, String[] whereArgs,
                                               ProductCategoryEnum categoryEnum) {
        Cursor cursor = getCursor(
                whereClause, whereArgs, categoryEnum.getTableName());
        return new ProductCursorWrapper(cursor);
    }

    private ContentValues getContentValues(Product product){
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_NAME_NAME, product.getName().toLowerCase());
        values.put(ProductEntry.COLUMN_NAME_ALL_NAMES, product.getAllNames().toLowerCase());
        values.put(ProductEntry.COLUMN_NAME_CONSUMPTION_RATE_AND_PROCESSED_CULTURES,
                product.getConsumptionRateAndProcessedCultures().toLowerCase());
        values.put(ProductEntry.COLUMN_NAME_HARMFUL_ORGANISM_DISEASE,
                product.getHarmfulOrganismOrDisease().toLowerCase());
        values.put(ProductEntry.COLUMN_NAME_OPERATING_PRINCIPLE,
                product.getOperatingPrinciple().toLowerCase());
        values.put(ProductEntry.COLUMN_NAME_DAYS_TILL_LAST_HARVEST,
                product.getDaysTillLastHarvest());
        values.put(ProductEntry.COLUMN_NAME_TREATMENTS_MULTIPLICITY,
                product.getTreatmentsMultiplicity());
        values.put(ProductEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_ID,
                product.getActiveSubstanceId());

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

    private ActiveSubstanceCursorWrapper queryActiveSubstances(String whereClause, String[] whereArgs,
                                                              ProductCategoryEnum categoryEnum){
        Cursor cursor = getCursor(
                whereClause, whereArgs, categoryEnum.getActiveSubstanceTableName());
        return new ActiveSubstanceCursorWrapper(cursor);
    }

    private List<ActiveSubstance> parseActiveSubstances(ActiveSubstanceCursorWrapper cursor){
        List<ActiveSubstance> substances = new ArrayList<>();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                substances.add(cursor.getActiveSubstance());
                cursor.moveToNext();
            }
        } finally {
            cursor.close(); // never forger to close cursor
        }
        return substances;
    }

    private Cursor getCursor(String whereClause, String[] whereArgs,
                             String tableName) {
        return mDatabase.query(
                tableName,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
    }

    private void createCategoryTablesAndInitData(SQLiteDatabase db, ProductCategoryEnum categoryEnum){
        // create of active substance table
        executeSqlScriptFromResource(db, categoryEnum.getTableActiveSubstanceScriptId());
        // init data for active substance table
        executeSqlScriptFromResource(db, categoryEnum.getInitialDataActiveSubstanceScriptId());
        // create of product table
        executeSqlScriptFromResource(db, categoryEnum.getTableScriptId());
        // init data for product table
        executeSqlScriptFromResource(db, categoryEnum.getInitialDataScriptId());
    }

    private void executeSqlScriptFromResource(SQLiteDatabase db, int resourceId){
        BufferedReader reader;
        try {
            // Open the resource
            InputStream insertsStream = mContext.getResources().
                    openRawResource(resourceId);
            reader = new BufferedReader(new InputStreamReader(insertsStream));

            // Iterate through lines
            StringBuilder insertStmt = new StringBuilder();
            while (reader.ready()) {
                insertStmt.append(reader.readLine().toLowerCase());
            }
            String insertScript = insertStmt.toString();
            db.execSQL(insertScript);
            reader.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
