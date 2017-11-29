package com.entity.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * <p>ClassName: Entry</p>
 * <p>@Description: 词条信息</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-27上午11:06:35</p>
 */
@Entity
@Table(name = "entry")
public class Entry implements Serializable {
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
}
