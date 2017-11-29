package com.base.dao;

import java.util.List;

import com.base.action.datatables.DataTables;


/**
 * <p>ClassName: BaseDao</p>
 * <p>@Description: 数据层基础方法</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午4:08:08</p>
 */
public interface BaseDao<T> {
	
	/**
	 * <p>@Description: 保存对象</p>
	 * <p>@param @param t</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:09:51</p>
	 */
	void save(T t);

	/**
	 * <p>@Description:删除对象</p>
	 * <p>@param @param t</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:10:20</p>
	 */
	void delete(T t);
	
	/**
	 * <p>@Description:通过主建删除对象</p>
	 * <p>@param @param id</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:10:35</p>
	 */
	void deleteById(int id);
	
	/**
	 * <p>@Description:更新对象</p>
	 * <p>@param @param t</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:11:17</p>
	 */
	void update(T t);
	
	void merge(T t);
   
	/**
     * <p>@Description:通过对象主键集合获取对象集合</p>
     * <p>@param @param idList
     * <p>@param @return</p>   
     * <p>@return List<T></p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-13下午4:11:38</p>
     */
	List<T> getByIdList(List<Integer> idList);
    
	/**
     * <p>@Description:延迟加载对象</p>
     * <p>@param @param id
     * <p>@param @return</p>   
     * <p>@return T</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-13下午4:12:13</p>
     */
	T load(int id);
    
	/**
     * <p>@Description:查询所有对象集合</p>
     * <p>@param @return</p>   
     * <p>@return List<T></p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-13下午4:12:38</p>
     */
	List<T> findAll();
   
	/**
     * <p>@Description:统计所有对象条数</p>
     * <p>@param @return</p>   
     * <p>@return int</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-13下午4:13:20</p>
     */
	public int count();
   
	/**
     * <p>@Description:插入或更新操作</p>
     * <p>@param @param t</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-13下午4:13:57</p>
     */
	void saveOrUpdate(T t);
    
	/**
     * <p>@Description:通过主键集合批量删除</p>
     * <p>@param @param ids</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-13下午4:14:21</p>
     */
	void delete(List<Integer> ids);
   
	/**
     * <p>@Description:通过主键获取对象</p>
     * <p>@param @param id
     * <p>@param @return</p>   
     * <p>@return T</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-6-13下午4:14:42</p>
     */
	T get(int id);
	
	/**
	 * <p>@Description: 获取对应int字段名称对应值集合</p>
	 * <p>@param @param fieldName 字段名
	 * <p>@param @param fieldValue 字段值
	 * <p>@param @return</p>   
	 * <p>@return List<T></p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:16:38</p>
	 */
	public List<T> findByName(String fieldName, int fieldValue);

	/**
	 * <p>@Description: 获取对应String字段名称对应值集合</p>
	 * <p>@param @param fieldName
	 * <p>@param @param fieldValue
	 * <p>@param @return</p>   
	 * <p>@return List<T></p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:19:21</p>
	 */
	public List<T> findByName(String fieldName, String fieldValue);

	public List<T> findByIntAndStr(String intFieldName,int intValue,String strFieldName,String strValue);
	
	public List<T> findByIntAndInt(String intFieldName1,int intValue1,String intFieldName2,int intValue2);
	
	/**
	 * <p>@Description: dataTable 分页获取数据</p>
	 * <p>@param @param displayStart 起始记录条数
	 * <p>@param @param displayLength 每页显示记录条数
	 * <p>@param @param hql 
	 * <p>@param @return</p>   
	 * <p>@return List<T></p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:23:06</p>
	 */
	public List<T> findDataTablePage(int displayStart,int displayLength,String hql);
	
	/**
	 * <p>@Description: ataTable 分页计算总记录条数</p>
	 * <p>@param @param hql
	 * <p>@param @return</p>   
	 * <p>@return int</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:25:13</p>
	 */
	public int countDataTablePage(String hql);

	/**
	 * <p>@Description: 通用HQL分页查询返回DataTables对象</p>
	 * <p>@param @param hql HQL语句
	 * <p>@param @param dt DataTables对象
	 * <p>@param @param params HQL语句参数集</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午4:25:48</p>
	 */
	public void findByPage(String hql, DataTables dt,Object... params);
	public void deleteById(long id);
	public T get(long id);

}
