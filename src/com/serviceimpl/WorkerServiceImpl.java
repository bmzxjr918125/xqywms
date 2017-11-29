package com.serviceimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.base.action.datatables.DataTables;
import com.dao.WorkerDao;
import com.entity.common.Image;
import com.entity.enumobj.ImageType;
import com.entity.user.Worker;
import com.service.ImageService;
import com.service.WorkerService;

@Service("workerService")
public class WorkerServiceImpl implements WorkerService{
		private WorkerDao workerDao;
		private ImageService imageService;
        /**
         * 通过手机号码获取对应user
         */
		public Worker getByPhone(String phoneNum) {
			return workerDao.getByPhone(phoneNum);
		}
		public void update(Worker worker) {
		    workerDao.update(worker);
		}		

		
		/**
         * 分页获取会员列表数据
         */
        public void getDataTablePage(DataTables dtJson, String nickName,
                String phoneNumber, int status) {
            
            workerDao.getDataTablePage(dtJson, nickName, phoneNumber, status);
        }
        
        public Worker getById(Integer userId) {
            return workerDao.get(userId);
        }
        /**
         *创建人员信息
         */
        public void create(String phoneNumber, String pwd,String department,String job) { 
            Worker worker = new Worker();
            
            worker.create(phoneNumber,pwd,department,job);
            
            workerDao.save(worker);
       }
        
        public void updateHeaderImg(Worker worker, File file) {
            Image oldImage = worker.getHeaderImg();
            Image image = imageService.uploadImage(ImageType.XQ_HEADERIMAGE, file, worker,worker.getPhoneNumber(), true);
            worker.setHeaderImg(image);
            workerDao.update(worker);
            List<Image> list = new ArrayList<Image>();
            list.add(oldImage);
            imageService.deleteImages(list);
        }
        
        public ImageService getImageService() {
            return imageService;
        }
        @Resource(name="imageService")
        public void setImageService(ImageService imageService) {
            this.imageService = imageService;
        }
        public WorkerDao getWorkerDao() {
            return workerDao;
        }
        @Resource(name="workerDao")
        public void setWorkerDao(WorkerDao workerDao) {
            this.workerDao = workerDao;
        }
      
       
}
