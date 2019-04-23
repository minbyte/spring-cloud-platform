package com.mindasoft.cloud.admins.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-28 13:35:34
 */
@TableName("tb_dict")
public class DictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 数据值
	 */
	private String value;
	/**
	 * 标签名
	 */
	private String label;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 排序（升序）
	 */
	private Integer sort;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 状态，1正常 0禁用
	 */
	private Boolean status;

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：数据值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：数据值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：标签名
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * 获取：标签名
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：排序（升序）
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序（升序）
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：状态，1正常 0禁用
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	/**
	 * 获取：状态，1正常 0禁用
	 */
	public Boolean getStatus() {
		return status;
	}
}
