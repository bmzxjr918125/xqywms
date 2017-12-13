package com.entity.project;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import net.sf.json.JSONArray;
import com.entity.device.Device;
import com.entity.vo.CountVo;
/**
 * <p>ClassName: ProjectDevice</p>
 * <p>@Description: 项目对应设备</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27下午2:40:39</p>
 */
@Entity
@Table(name = "project_device")
public class ProjectDevice implements Serializable {
    private static final long serialVersionUID = -4011617171146777044L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 对应项目
	 */
	@ManyToOne
	@JoinColumn(name="projectId",nullable=false)
	private Project project;
	/**
	 * 对应设备
	 */
	@OneToOne
	@JoinColumn(name="deviceId",nullable=false)
	private Device device;
	/**
	 * 安装人员 
	 */
	@Column(nullable = true)
	private String installWorker;
	/**
     * 所在位置
     */
    private String position;
    /**
     * 统计数据
     */
    @Embedded
    private CountVo count;
	/**
	 * 安装日期
	 */
    @Column(nullable = false , columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createDate;
    /**
     * 巡检词条 jsonArray字符串
     * [{"id":1,"name":"是否运行正常","desc":""},
     *  {"id":2,"name":"是否制冷正常","desc":""},
     *  {"id":3,"name":"是否运行正常","desc":""}]
     *  LongText
     */
    @Column(length = 16777216)
    private String checkEntryJa;
	
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
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public Device getDevice() {
        return device;
    }
    public void setDevice(Device device) {
        this.device = device;
    }
    public String getInstallWorker() {
        return installWorker;
    }
    public void setInstallWorker(String installWorker) {
        this.installWorker = installWorker;
    }
   
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
 
    public String getCheckEntryJa() {
        return checkEntryJa;
    }
    public void setCheckEntryJa(String checkEntryJa) {
        this.checkEntryJa = checkEntryJa;
    }
    public CountVo getCount() {
        return count;
    }
    public void setCount(CountVo count) {
        this.count = count;
    }
    /**
     * <p>@Description: 获取巡检词条的JSONArray数据</p>
     * <p>@param @return</p>   
     * <p>@return JSONArray</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-29上午10:42:42</p>
     */
    public JSONArray getJSONArrayEntry(){
      
        return JSONArray.fromObject(this.checkEntryJa);
    }
    /**
     * <p>@Description: 设置巡检词条的JSONArray数据到对应属性</p>
     * <p>@param @param checkEntryJSONArray</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-29上午10:42:46</p>
     */
    public void setJSONArrayEntry(JSONArray checkEntryJSONArray){
        this.checkEntryJa = checkEntryJSONArray.toString();
    }
    public void create(Project project2, Device device2, JSONArray entryJa,
            String installWorker2, String position2) {
        this.project = project2;
        this.device = device2;
        this.setJSONArrayEntry(entryJa);
        this.installWorker = installWorker2;
        this.createDate = new Date();
        this.position = position2;
        this.count = new CountVo();
    }
}
