package com.serviceimpl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dao.RepairOrderDao;
import com.entity.order.RepairOrder;
import com.service.RepairOrderService;

@Service("repairOrderService")
public class RepairOrderServiceImpl implements RepairOrderService{
    private RepairOrderDao repairOrderDao;

    public List<RepairOrder> getByProjectDeviceId(Integer projectDeviceId) {
        return repairOrderDao.findByName("projectDevice.id", projectDeviceId);
    }
    
    public RepairOrderDao getRepairOrderDao() {
        return repairOrderDao;
    }
    @Resource(name="repairOrderDao")   
    public void setRepairOrderDao(RepairOrderDao repairOrderDao) {
        this.repairOrderDao = repairOrderDao;
    }
}
