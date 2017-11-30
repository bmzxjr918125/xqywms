package com.entity.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.entity.enumobj.EntryType;

/**
 * <p>ClassName: Entry</p>
 * <p>@Description: 词条信息</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27上午11:06:35</p>
 */
@Entity
@Table(name = "entry")
public class Entry implements Serializable {
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    private static final long serialVersionUID = -5766244424708058311L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	/**
	 * 词条名称
	 */
    @JoinColumn(nullable = false)
    @Column(length = 100)
	private String value;
    
    @Type(type = "com.entity.enumobj.base.IntegerValuedEnumType",
            parameters = {@Parameter(name = "enum" , value = "com.entity.enumobj.EntryType")})
    private EntryType entryType;
    /**
     * 创建时间
     */
    private Date createDate;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public EntryType getEntryType() {
        return entryType;
    }
    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }
}
