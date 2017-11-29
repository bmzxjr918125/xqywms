package com.base.action.convert;

import java.util.HashMap;
import java.util.Map;

import com.base.action.datatables.Columns;
import com.base.action.datatables.DataTables;
import com.base.action.datatables.Order;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

import net.sf.json.JSONObject;

/**
 * <p>Class:DataTablesConvert</p>
 * <p>Description: 将前端的DataTables插件转为Datatables对象</p>
 * @author XQL
 * @Date 2015年9月9日 下午5:39:39
 */
public class DataTablesConvert extends DefaultTypeConverter{

	@Override
	@SuppressWarnings("rawtypes")
	public Object convertValue(Map context, Object value, Class toType){
		
		String json = ((String[])value).length > 0 ? json = ((String[])value)[0] : ""; 

		DataTables dt = null;
		
		if(toType == DataTables.class){
			
			JSONObject jsonobj = JSONObject.fromObject(json);
			
			Map<String, Class> clsMap = new HashMap<String, Class>();
			
			clsMap.put("columns", Columns.class);
			
			clsMap.put("order", Order.class);
			
			dt = (DataTables)JSONObject.toBean(jsonobj,DataTables.class,clsMap);
		
		}
		
		return dt;
	}
}
