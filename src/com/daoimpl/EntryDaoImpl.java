package com.daoimpl;

import org.springframework.stereotype.Repository;
import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.EntryDao;
import com.entity.common.Entry;

@Repository("entryDao")
public class EntryDaoImpl extends BaseDaoImpl<Entry> implements EntryDao{
    
    
    public void getEntryDataTablePage(DataTables dtJson,int flag) {
        StringBuffer hql = new StringBuffer(" from Entry ");
        if(flag != 0){
            hql.append(" where entryType = "+flag);
        }
        super.findByPage(hql.toString(), dtJson);
    }
   
}
