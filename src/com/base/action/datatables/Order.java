package com.base.action.datatables;

/**
 * <p>Class:Order</p>
 * <p>Description: 包装DataTables排序信息</p>
 * @author XQL
 * @Date 2015年9月9日 下午6:11:17
 */
public class Order {
	private int column;
	private String dir;
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	@Override
	public String toString() {
		return "Order [column=" + column + ", dir=" + dir + "]";
	}
	
}
