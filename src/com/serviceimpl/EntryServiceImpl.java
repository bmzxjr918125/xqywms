package com.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.EntryDao;
import com.entity.common.Entry;
import com.service.EntryService;

@Service("entryService")
public class EntryServiceImpl implements EntryService{
    private EntryDao entryDao;
   public Entry getById(int id) {
       
        return entryDao.get(id);
    }

    public void update(Entry entry) {
        entryDao.update(entry);
    }

    public void save(Entry entry) {
        entryDao.save(entry);
    }

    public void delete(Entry entry) {
        entryDao.delete(entry);
    }
   
      public void getEntryDataTablePage(DataTables dtJson, int flag) {
      
          entryDao.getEntryDataTablePage(dtJson,flag);
      
      }
      
   public EntryDao getEntryDao() {
        return entryDao;
    }
    @Resource(name="entryDao")
    public void setEntryDao(EntryDao entryDao) {
        this.entryDao = entryDao;
    }
  
}
