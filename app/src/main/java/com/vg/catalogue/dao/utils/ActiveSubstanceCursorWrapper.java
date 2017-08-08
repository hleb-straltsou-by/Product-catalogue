package com.vg.catalogue.dao.utils;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.vg.catalogue.dao.entries.ActiveSubstanceEntry;
import com.vg.catalogue.model.ActiveSubstance;

public class ActiveSubstanceCursorWrapper extends CursorWrapper {

    public ActiveSubstanceCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public ActiveSubstance getActiveSubstance(){
        long id = getInt(getColumnIndex(ActiveSubstanceEntry.COLUMN_NAME_ENTRY_ID));
        String activeSubstanceName = getString(
                getColumnIndex(ActiveSubstanceEntry.COLUMN_NAME_ACTIVE_SUBSTANCE_NAME));

        ActiveSubstance substance = new ActiveSubstance();
        substance.setId(id);
        substance.setName(activeSubstanceName);

        return substance;
    }
}
