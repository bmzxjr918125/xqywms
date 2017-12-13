package com.service;

import java.util.List;

import com.entity.order.RepairOrder;

public interface RepairOrderService {

    List<RepairOrder> getByProjectDeviceId(Integer id);

}
