package com.daoimpl;

import org.springframework.stereotype.Repository;
import com.base.dao.BaseDaoImpl;
import com.dao.RepairOrderDao;
import com.entity.order.RepairOrder;

@Repository("repairOrderDao")
public class RepairOrderDaoImpl extends BaseDaoImpl<RepairOrder> implements RepairOrderDao{

   
}
