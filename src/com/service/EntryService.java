package com.service;

import com.base.action.datatables.DataTables;
import com.entity.common.Entry;



public interface EntryService {

    void getEntryDataTablePage(DataTables dtJson, int flag);

    Entry getById(int id);

    void update(Entry entry);

    void save(Entry entry);

    void delete(Entry entry);

	
}
