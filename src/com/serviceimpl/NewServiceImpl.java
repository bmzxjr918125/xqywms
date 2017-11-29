package com.serviceimpl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.NewDao;
import com.entity.common.New;
import com.service.NewService;

@Service("newService")
public class NewServiceImpl implements NewService{
  
    private NewDao newDao;

    public void getDataTablePage(DataTables dtJson) {
        
        newDao.getDataTablePage(dtJson);
    }

    public void save(New new_) {
       
        newDao.save(new_);
    }

    public void update(New new_) {
        newDao.update(new_);
    }

    public List<New> getNewList(int currentNum, int perPageNum) {
      
        return newDao.getNewList(currentNum,perPageNum);
    }

    public int count() {
        return newDao.count();
    }
    

    public New getById(int newId) {
        return newDao.get(newId);
    }
    
    
    
    public NewDao getNewDao() {
        return newDao;
    }
    @Resource(name="newDao")
    public void setNewDao(NewDao newDao) {
        this.newDao = newDao;
    }
}
