package com.dao;


import java.util.List;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.common.New;


public interface NewDao extends BaseDao<New>{

    void getDataTablePage(DataTables dtJson);

    List<New> getNewList(int currentNum, int perPageNum);
  
}
