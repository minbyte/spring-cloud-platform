package com.mindasoft.cloud.security.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户端应用
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-27 12:57:15
 */
@TableName("oauth_client_details")
public class ClientDetailsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 应用标识
	 */
	private String clientId;
	/**
	 * 资源限定串(逗号分割)
	 */
	private String resourceIds;
	/**
	 * 应用密钥(bcyt) 加密
	 */
	private String clientSecret;
	/**
	 * 应用密钥(明文)
	 */
	private String clientSecretStr;
	/**
	 * 范围
	 */
	private String scope;
	/**
	 * 5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)
	 */
	private String authorizedGrantTypes;
	/**
	 * 回调地址 
	 */
	private String webServerRedirectUri;
	/**
	 * 权限
	 */
	private String authorities;
	/**
	 * access_token有效期
	 */
	private Integer accessTokenValidity;
	/**
	 * refresh_token有效期
	 */
	private Integer refreshTokenValidity;
	/**
	 * {}
	 */
	private String additionalInformation;
	/**
	 * 是否自动授权 是-true
	 */
	private String autoapprove;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：应用标识
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * 获取：应用标识
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * 设置：资源限定串(逗号分割)
	 */
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	/**
	 * 获取：资源限定串(逗号分割)
	 */
	public String getResourceIds() {
		return resourceIds;
	}
	/**
	 * 设置：应用密钥(bcyt) 加密
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	/**
	 * 获取：应用密钥(bcyt) 加密
	 */
	public String getClientSecret() {
		return clientSecret;
	}
	/**
	 * 设置：应用密钥(明文)
	 */
	public void setClientSecretStr(String clientSecretStr) {
		this.clientSecretStr = clientSecretStr;
	}
	/**
	 * 获取：应用密钥(明文)
	 */
	public String getClientSecretStr() {
		return clientSecretStr;
	}
	/**
	 * 设置：范围
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * 获取：范围
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * 设置：5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)
	 */
	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}
	/**
	 * 获取：5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)
	 */
	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}
	/**
	 * 设置：回调地址 
	 */
	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}
	/**
	 * 获取：回调地址 
	 */
	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}
	/**
	 * 设置：权限
	 */
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	/**
	 * 获取：权限
	 */
	public String getAuthorities() {
		return authorities;
	}
	/**
	 * 设置：access_token有效期
	 */
	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}
	/**
	 * 获取：access_token有效期
	 */
	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}
	/**
	 * 设置：refresh_token有效期
	 */
	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}
	/**
	 * 获取：refresh_token有效期
	 */
	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}
	/**
	 * 设置：{}
	 */
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	/**
	 * 获取：{}
	 */
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	/**
	 * 设置：是否自动授权 是-true
	 */
	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}
	/**
	 * 获取：是否自动授权 是-true
	 */
	public String getAutoapprove() {
		return autoapprove;
	}
}
