package com.service;

import com.base.action.datatables.DataTables;
import com.entity.trade.Trade;



public interface TradeService {

	void save(Trade trade);

	double countDayAmount(String feildName);

	void getTradeByPage(DataTables dtJson, int adminId);

	double countDayByAdminId(String string, int adminId);

	double countMonthByAdminId(String string, int adminId);

	double countAllByAdminId(String string, int adminId);

	
}
