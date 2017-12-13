package com.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.base.action.datatables.DataTables;
import com.entity.common.Entry;
import com.entity.enumobj.EntryType;



public interface EntryService {

    void getEntryDataTablePage(DataTables dtJson, int flag);

    Entry getById(int id);

    void update(Entry entry);

    void save(Entry entry);

    void delete(Entry entry);

    List<Entry> getEntryListByType(EntryType type);

    JSONArray getJAByIds(String entryIds);

    JSONArray updateJAByIds(String entryIds, String oldEntryJaStr);

	
}
