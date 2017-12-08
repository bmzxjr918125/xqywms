package com.bizservice;

import java.io.File;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.datatables.DataTables;
import com.entity.common.Entry;
import com.entity.common.Image;
import com.entity.common.New;
import com.entity.enumobj.EntryType;


/**
 * <p>ClassName: BizCommonService</p>
 * <p>@Description: 公共</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-23上午10:53:05</p>
 */
public interface BizCommonService {

    void getNewDataTablePage(DataTables dtJson);

    void addSaveNew(String title, String content, String description, List<File> ylfile);

    JSONObject getNewList(int currentNum, int perPageNum);

    JSONObject getNewById(int newId);

    New getNewObjById(int id);

    void updateSaveNew(int newId, String title, String description,
            String content, String deleteylIds, List<File> ylfile);

    List<Image> getImageByNameAndVal(String name, int value);

    void getEntryDataTablePage(DataTables dtJson, int flag);

    void addSaveEntry(String name, int flag);

    void updateSaveEntry(int id, String name);

    void deleteEntryByIds(String ids);

    List<Entry> getEntryListByType(EntryType depart);

    JSONArray getProvinceList();

    JSONArray getCityList(int provinceId);

    JSONArray getAreaList(int cityId);
   

}
