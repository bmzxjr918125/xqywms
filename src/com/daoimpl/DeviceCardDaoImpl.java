package com.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.action.datatables.DataTables;
import com.base.dao.BaseDaoImpl;
import com.dao.DeviceCardDao;
import com.entity.device.DeviceCard;

@Repository("deviceCardDao")
public class DeviceCardDaoImpl extends BaseDaoImpl<DeviceCard> implements DeviceCardDao{

    public void getDataTablePage(DataTables dtJson) {
        StringBuffer hql = new StringBuffer(" from DeviceCard ");
       
        hql.append("where id != 0 and ");
        hql.append("(deviceInfo.deviceName like ? or ");
        hql.append("deviceInfo.deviceType like ? or ");
        hql.append("deviceInfo.supplier like ? or ");
        hql.append("deviceInfo.modelNum like ? or ");
        hql.append("deviceInfo.input like ? or ");
        hql.append("deviceInfo.output like ? or ");
        hql.append("deviceInfo.cryogenType like ? or ");
        hql.append("deviceInfo.charge like ? or ");
        hql.append("deviceInfo.description like ? )");

        //排序

        super.findByPage(hql.toString(), dtJson,
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%",
                "%"+dtJson.getSearch().getValue()+"%");
        
    }
    @SuppressWarnings("unchecked")
    public List<String> getCardNameAndIdList() {
        StringBuffer hql = new StringBuffer(" select new map(id as id,deviceInfo.deviceName as name) from DeviceCard ");
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql.toString()).list();
    }

   
}
