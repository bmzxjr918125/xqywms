package com.entity.project;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import com.entity.user.User;
/**
 * <p>ClassName: ProjectUser</p>
 * <p>@Description: 项目对应甲方用户</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27下午2:40:58</p>
 */
@Entity
@Table(name = "project_user")
public class ProjectUser implements Serializable {
    private static final long serialVersionUID = -2104917686477576882L;
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
     * 对应客户
     */
    @OneToOne
    @JoinColumn(name="userId",nullable=false)
    private User user;
    /**
     * 加入日期
     */
    private Date createDate;
    /**
     * 是否是主负责人
     */
     @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
                parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.Boolean")})
    private com.entity.enumobj.Boolean isMain;
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
   
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public com.entity.enumobj.Boolean getIsMain() {
        return isMain;
    }
    public void setIsMain(com.entity.enumobj.Boolean isMain) {
        this.isMain = isMain;
    }
}
