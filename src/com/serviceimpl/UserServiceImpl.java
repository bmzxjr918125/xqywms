package com.serviceimpl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.base.action.datatables.DataTables;
import com.dao.UserDao;
import com.entity.user.User;
import com.service.ImageService;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
		private UserDao userDao;
		private ImageService imageService;
        /**
         * 通过手机号码获取对应user
         */
		public User getByPhone(String phoneNum) {
			return userDao.getByPhone(phoneNum);
		}
		public void update(User user) {
			userDao.update(user);
			
		}		
		 public void getDataTablePage(DataTables dtJson, String nikeName, String phoneNumber) {
		     userDao.getDataTablePage(dtJson,nikeName,phoneNumber);
	     }
	
		 public void delete(User user) {
		     userDao.delete(user);
	        }
		 
		 
        public User getById(Integer userId) {
            return userDao.get(userId);
        }
        public String getNextCode() {
            
            return userDao.getNextCode();
        }
        public int countUserNumber(String formatDate) {
            
            return userDao.countUserNumber(formatDate);
        }
		public UserDao getUserDao() {
			return userDao;
		}
        @Resource(name="userDao")
		public void setUserDao(UserDao userDao) {
			this.userDao = userDao;
		}
        public ImageService getImageService() {
            return imageService;
        }
        @Resource(name="imageService")
        public void setImageService(ImageService imageService) {
            this.imageService = imageService;
        }
}
