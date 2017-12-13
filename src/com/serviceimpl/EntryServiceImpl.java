package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.EntryDao;
import com.entity.common.Entry;
import com.entity.enumobj.EntryType;
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
      

      public List<Entry> getEntryListByType(EntryType type) {
           return entryDao.findByName("entryType", type.getIndex());
      }
      
      public JSONArray getJAByIds(String entryIds) {
          String entryId[] = entryIds.split(",");
          List<Integer> idList = new ArrayList<Integer>();
          for(int i=0;i<entryId.length;i++){
              idList.add(Integer.parseInt(entryId[i]));
          }
          
          List<Entry> entryList = entryDao.getByIdList(idList);
          JSONArray ja = new JSONArray();
          JSONObject jo;
          for(Entry entry:entryList){
              jo = new JSONObject();
              jo.put("id", entry.getId());
              jo.put("name", entry.getValue());
              jo.put("desc","");
              ja.add(jo);
          }
          return ja;
      }
      
      public JSONArray updateJAByIds(String entryIds, String oldEntryJaStr) {
          JSONArray oldJa = JSONArray.fromObject(oldEntryJaStr);
          
          String entryId[] = entryIds.split(",");
          List<Integer> idList = new ArrayList<Integer>();
          for(int i=0;i<entryId.length;i++){
              idList.add(Integer.parseInt(entryId[i]));
          }
          
          List<Entry> entryList = entryDao.getByIdList(idList);
          JSONArray ja = new JSONArray();
          JSONObject jo;
         
          for(Entry entry:entryList){
              idList.remove(idList.indexOf(entry.getId()));
              
              jo = new JSONObject();
              jo.put("id", entry.getId());
              jo.put("name", entry.getValue());
              jo.put("desc","");
              ja.add(jo);
          }
          
          if(idList != null && idList.size()>0){
              for(Integer i:idList){
                  for(int k=0;k<oldJa.size();k++){
                      if(oldJa.getJSONObject(k).getInt("id") == i){
                          ja.add(oldJa.getJSONObject(k));
                          continue;
                      }
                  }
              }
              
          }
          return ja;
      }
      
   public EntryDao getEntryDao() {
        return entryDao;
    }
    @Resource(name="entryDao")
    public void setEntryDao(EntryDao entryDao) {
        this.entryDao = entryDao;
    }
}
