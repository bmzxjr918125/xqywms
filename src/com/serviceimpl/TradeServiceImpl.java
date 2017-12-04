package com.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.TradeDao;
import com.entity.trade.Trade;
import com.service.TradeService;

@Service("tradeService")
public class TradeServiceImpl implements TradeService{
	   private TradeDao tradeDao;

	   public void save(Trade trade) {
			tradeDao.save(trade);
		}
	   
	   public double countDayAmount(String feildName) {
			
			return tradeDao.countDayAmount(feildName);
		}
	   public void getTradeByPage(DataTables dtJson, int adminId) {
		   tradeDao.getTradeByPage(dtJson,adminId);
		}
	   
	public TradeDao getTradeDao() {
		return tradeDao;
	}
    @Resource(name="tradeDao")
	public void setTradeDao(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

	public double countDayByAdminId(String name, int adminId) {
		return tradeDao.countDayByAdminId(name,adminId);
	}

	public double countMonthByAdminId(String name, int adminId) {
		return tradeDao.countMonthByAdminId(name,adminId);
	}

	public double countAllByAdminId(String name, int adminId) {
		return tradeDao.countAllByAdminId(name,adminId);
	}

	
}
