package com.entity.project;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import com.entity.enumobj.CheckType;
import com.entity.enumobj.ProjectType;
import com.entity.user.User;
import com.entity.vo.AddressVo;

/**
 * <p>ClassName: Project</p>
 * <p>@Description: 项目表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27下午2:29:46</p>
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = -599473606683190696L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目编号
     */
    @Column(unique = true, nullable = false, length = 30)
    private String projectNo;
    /**
     * 项目类型
     */
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.ProjectType")})
    private ProjectType projectType;
    /**
     * 是否收费维保
     */
    private boolean isChargeRepair;
    /**
     * 维保开始时间
     */
    private Date repairDateStart;
    /**
     * 维保截止时间
     */
    private Date repairDateEnd;
    /**
     * 项目巡检周期
     */
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.CheckType")})
    private CheckType checkType;
   
    /**
     * 项目地址
     */
    @Embedded
    /*@AttributeOverrides({
        @AttributeOverride(name = "province", column = @Column( name = "province")),
        @AttributeOverride(name = "city", column = @Column( name = "city")),
        @AttributeOverride(name = "area", column = @Column( name = "area")),
        @AttributeOverride(name = "address", column = @Column( name = "address"))
    })*/
    private AddressVo address;
    /**
     * 项目甲方指定负责人
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
   /**
    * 项目概况
    */
    private String description;
    /**
     * 创建时间
     */
    private Date createDate;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public boolean isChargeRepair() {
        return isChargeRepair;
    }

    public void setChargeRepair(boolean isChargeRepair) {
        this.isChargeRepair = isChargeRepair;
    }

    public Date getRepairDateStart() {
        return repairDateStart;
    }

    public void setRepairDateStart(Date repairDateStart) {
        this.repairDateStart = repairDateStart;
    }

    public Date getRepairDateEnd() {
        return repairDateEnd;
    }

    public void setRepairDateEnd(Date repairDateEnd) {
        this.repairDateEnd = repairDateEnd;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }

    public AddressVo getAddress() {
        return address;
    }

    public void setAddress(AddressVo address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    /**
     * <p>@Description: 生成项目号</p>
     * <p>@param @param phoneNumber
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2017-11-27下午2:44:00</p>
     */
      public static String createProjectNo(String phoneNumber) {

          // log.info(new Date() + "开始生成订单号");
          String no = "";
          if (phoneNumber != null && phoneNumber.length() > 6) {
              Random random = new Random();
              no = random.nextInt(Integer.parseInt(phoneNumber.substring(
                      phoneNumber.length() - 7, phoneNumber.length() - 1))) + "";
          }

          String orderNumber = "100" + no + System.currentTimeMillis();
          // log.info(new Date() + "生成订单号：" + orderNumber);
          return orderNumber;
      }
      
      
    
}
