package com.mindasoft.cloud.security.config;

import com.mindasoft.cloud.security.oauth2.RedisClusterTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
* @author owen 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
* 类说明 
* token存储配置
*/
public class TokenStoreConfig {

	@Resource
	private DataSource dataSource ;
	
	@Autowired(required=false)
	private RedisTemplate<String, Object> redisTemplate ;

	/**
	 * 内存存储
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(prefix="security.oauth2.token",name="storeType" ,havingValue="inMemory" ,matchIfMissing=false)
	public InMemoryTokenStore inMemoryTokenStore() {
		return new InMemoryTokenStore();
	}

	/**
	 * JDBC存储，需要创建 oauth_access_token oauth_refresh_token 创建两张表
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(prefix="security.oauth2.token",name="storeType" ,havingValue="jdbc" ,matchIfMissing=false)
	public JdbcTokenStore jdbcTokenStore(){
		return new JdbcTokenStore( dataSource ) ;
	}

	/**
	 * 单台redis服务器
	 */
	@Bean
	@ConditionalOnProperty(prefix="security.oauth2.token",name="storeType" ,havingValue="redis" ,matchIfMissing=true)
	public RedisTokenStore redisTokenStore(){
		Assert.state(redisTemplate != null, "RedisTemplate must be provided");
		return new RedisTokenStore( redisTemplate.getConnectionFactory() ) ;
	}

	/**
	 * 集群redis
	 */
	@Bean
	@ConditionalOnProperty(prefix="security.oauth2.token",name="storeType" ,havingValue="redisCluster" ,matchIfMissing=false)
	public RedisClusterTokenStore clusterRedisTokenStore(){
		Assert.state(redisTemplate != null, "RedisTemplate must be provided");
		RedisClusterTokenStore redisTemplateStore = new RedisClusterTokenStore()  ;
		redisTemplateStore.setRedisTemplate(redisTemplate);
		return redisTemplateStore ;
	}
	
	/**
	 * 使用jwt替换原有的uuid生成token方式
	 */
	@Configuration
	@ConditionalOnProperty(prefix="security.oauth2.token",name="storeType" ,havingValue="jwt" ,matchIfMissing=false)
	public static class JWTTokenConfig {
		@Bean
		public JwtTokenStore jwtTokenStore(){
			return new JwtTokenStore( jwtAccessTokenConverter() ) ;
		}
		
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter(){
			JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
			accessTokenConverter.setSigningKey("mindasoft");
			return accessTokenConverter ;
		}
	}
	
	
	
}
