package com.dao;


import com.base.action.datatables.DataTables;
import com.base.dao.BaseDao;
import com.entity.trade.Trade;


public interface TradeDao extends BaseDao<Trade>{

	double countDayAmount(String feildName);

	void getTradeByPage(DataTables dtJson, int adminId);

	double countDayByAdminId(String name, int adminId);

	double countMonthByAdminId(String name, int adminId);

	double countAllByAdminId(String name, int adminId);

}
