package com.service;

import java.util.List;

import com.base.action.datatables.DataTables;
import com.entity.common.New;


public interface NewService {

    void getDataTablePage(DataTables dtJson);

    void save(New new_);

    void update(New new_);

    List<New> getNewList(int currentPage, int perPageNum);

    int count();

    New getById(int newId);
    
	
}
