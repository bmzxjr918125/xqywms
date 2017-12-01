package com.entity.project;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import com.entity.enumobj.WorkerType;
import com.entity.user.Worker;
/**
 * <p>ClassName: ProjectWorker</p>
 * <p>@Description: 项目对应工作人员</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27下午2:41:24</p>
 */
@Entity
@Table(name = "project_worker")
public class ProjectWorker implements Serializable {
    private static final long serialVersionUID = 2815514699582678537L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 对应项目
     */
    @ManyToOne
    @JoinColumn(name="projectDeviceId",nullable=false)
    private ProjectDevice projectDeviceId;
    /**
     * 对应工作人员
     */
    @ManyToOne
    @JoinColumn(name="workerId",nullable=false)
    private Worker worker;
    /**
     * 加入日期
     */
    private Date createDate;
    /**
     * 所属团队类型
     */
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.WorkerType")})
    private WorkerType workerType;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public WorkerType getWorkerType() {
        return workerType;
    }
    public void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
    }
    public ProjectDevice getProjectDeviceId() {
        return projectDeviceId;
    }
    public void setProjectDeviceId(ProjectDevice projectDeviceId) {
        this.projectDeviceId = projectDeviceId;
    }
}
