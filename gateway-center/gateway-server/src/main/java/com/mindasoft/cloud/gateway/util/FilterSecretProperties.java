package com.mindasoft.cloud.gateway.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dodoing
 * @email: dodoing@sina.com
 * @date: 2018/6/16 20:24
 * @version: 1.0.0
 */
@ConditionalOnExpression("!'${secret}'.isEmpty()")
@ConfigurationProperties(prefix = "secret")
public class FilterSecretProperties {

	private String key;

	private List<String> urls = new ArrayList<>();

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}