package com.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
/**
 * 
 * <p>ClassName: MySQLLocalDialect</p>
 * <p>@Description: hql不支持utf-8的汉字按拼音排序，需要转换成gbk,这就要扩展数据库方言</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:05:46</p>
 */
public class MySQLLocalDialect extends MySQLDialect{
    
	/**
	 * 
	 * <p>Description:
	       同时修改数据库方言配置： 方言配置方法
	        <property name="hibernateProperties">
			<props>
			<prop key="hibernate.dialect">
			<!-- org.hibernate.dialect.MySQLDialect-->
			com.jbms.util.MySQLLocalDialect
			</prop>
			<prop key="hibernate.show_sql">true</prop>
			</props>
			</property>
	   </p>
	 * <p></p>
	 */
	public MySQLLocalDialect() {
		super();
		registerFunction("convert", new SQLFunctionTemplate(
				org.hibernate.Hibernate.STRING, "convert(?1 using ?2)"));
		registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
	}

}
