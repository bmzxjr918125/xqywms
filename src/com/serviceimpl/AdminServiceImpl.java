package com.serviceimpl;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.base.action.datatables.DataTables;
import com.dao.AdminDao;
import com.entity.admin.Admin;
import com.service.AdminService;

import java.util.Date;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;

	public Admin getAdminByName(String param,String value){

		return adminDao.getAdminByName(param, value);
	}

	public AdminDao getadminDao() {
		return adminDao;
	}

	@Resource(name="adminDao")
	public void setadminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public Admin getById(int id) {
		return adminDao.get(id);
	}

	public void saveOrUpdate(Admin l_Admin) {
		adminDao.saveOrUpdate(l_Admin);
	}

	/**
	 * 后台管理员列表
	 */
	public void getByPage(DataTables dt) {
		adminDao.getByPage(dt);
	}

	/**
	 * 根据用户名查询单个用户信息
	 */
	public Admin getOnly(String username){
		return adminDao.findByOnly(username);
	}

	/**
	 * 保存编辑信息
	 */
	public boolean saveEdit(Admin ad){
		Admin admin = adminDao.get(ad.getId());
		if(admin != null ){
			admin.setUpdateDate(new Date());
			admin.setRole_name(ad.getRole_name());
			adminDao.saveOrUpdate(admin);

			return true;
		}

		return false;
	}

	
}
