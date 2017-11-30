package com.dao;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.common.Entry;


public interface EntryDao extends BaseDao<Entry>{


    void getEntryDataTablePage(DataTables dtJson, int flag);

  
}
