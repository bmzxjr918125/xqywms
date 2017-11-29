package com.base.action.datatables;

/**
 * <p>Class:Search</p>
 * <p>Description: 包装DataTables查询信息</p>
 * @author XQL
 * @Date 2015年9月9日 下午6:11:54
 */
public class Search {
	private String value;
	private boolean regex;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isRegex() {
		return regex;
	}
	public void setRegex(boolean regex) {
		this.regex = regex;
	}
	
	@Override
	public String toString() {
		return "Search [value=" + value + ", regex=" + regex + "]";
	}
	
}
