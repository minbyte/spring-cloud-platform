package com.mindasoft.cloud.admins.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 参数配置
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-28 13:35:34
 */
@TableName("tb_config")
public class ConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 键
	 */
	private String key;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 状态   0：隐藏   1：显示
	 */
	private Boolean status;
	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：键
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取：键
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置：值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：状态   0：隐藏   1：显示
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	/**
	 * 获取：状态   0：隐藏   1：显示
	 */
	public Boolean getStatus() {
		return status;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}
