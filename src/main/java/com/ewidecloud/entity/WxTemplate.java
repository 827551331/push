package com.ewidecloud.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_wx_template")
public class WxTemplate implements Serializable {

	private static final long serialVersionUID = 7995857276589551725L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Integer tenantid;
	
	private String create_name;
	
	private Date create_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTenantid() {
		return tenantid;
	}

	public void setTenantid(Integer tenantid) {
		this.tenantid = tenantid;
	}

	public String getCreate_name() {
		return create_name;
	}

	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n id:"+id);
		sb.append("\n tenantid:"+tenantid);
		sb.append("\n create_name:"+create_name);
		sb.append("\n create_date:"+create_date);
		return sb.toString();
	}
	
    
}
