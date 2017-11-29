package com.base.action.datatables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Class:DataTables</p>
 * <p>Description: DataTables插件参数对象</p>
 * @author XQL
 * @Date 2015年9月9日 下午5:40:33
 */
public class DataTables {

	//请求传递参数
	private int draw = 0;
	private List<Columns> columns = new ArrayList<Columns>();
	private List<Order> order = new ArrayList<Order>();
	private int start = 0;
	private int length = 10;
	private Search search = new Search();

	//相应传递参数
	private Object data;
	private int recordsTotal = 0;
	private int recordsFiltered = 0;
	private Map<String,Object> params = new HashMap<String, Object>();
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public List<Columns> getColumns() {
		return columns;
	}
	public void setColumns(List<Columns> columns) {
		this.columns = columns;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "DataTables [draw=" + draw + ", columns=" + columns.get(0).toString() + ", order=" + order.get(0).toString() + ", start=" + start
				+ ", length=" + length + ", search=" + search + ", data=" + data + ", recordsTotal=" + recordsTotal
				+ ", recordsFiltered=" + recordsFiltered + "]";
	}
}
