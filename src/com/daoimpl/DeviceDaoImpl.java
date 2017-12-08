package com.daoimpl;

import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.DeviceDao;
import com.entity.device.Device;

@Repository("deviceDao")
public class DeviceDaoImpl extends BaseDaoImpl<Device> implements DeviceDao{

   public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from Device as d ");
        hql.append(" where d.id != 0 and ");
        hql.append("(  d.deviceInfo.deviceName like ? or ");
        hql.append(" d.deviceInfo.deviceType like ? or ");
        hql.append(" d.deviceInfo.modelNum like ? or ");
       // hql.append(" ( IFNULL(d.projectDevice.project.projectName,'') like ? ) or ");
        //hql.append(" ( IFNULL(d.projectDevice.position,'') like ? ) or ");
        hql.append(" d.deviceNo like ? )  ");
       
        
        //排序
        super.findByPage(hql.toString(), dtJson,
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
               // "%"+dtJson.getSearch().getValue()+"%",
                //"%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%");
    }

    /*public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from Device ");
        hql.append("where id != 0 ");
       
        //排序
        super.findByPage(hql.toString(), dtJson);
    }*/
}
