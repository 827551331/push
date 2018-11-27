package com.ewidecloud.init;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ewidecloud.dao.DataSourceConst;
import com.ewidecloud.dao.DynamicDataSource;

/**
 * @ClassName DruidPoolsConfig
 * @Description 数据库多数据源连接池初始化类
 * @author wang_yw
 * @Date 2018年8月3日 上午8:53:02
 * @version 1.0.0
 */
@Configuration
public class DruidPoolsInit {

	/**
	 * @Description: 初始化第一个数据源
	 * @author wang_yw
	 * @date 2018年8月3日 下午3:56:10
	 */
	@Primary
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties("spring.datasource.druid.one")
	public DataSource dataSourceOne() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * @Description: 初始化第二个数据源
	 * @author wang_yw
	 * @date 2018年8月3日 下午3:56:10
	 */
	@Bean(name = "secondaryDataSource")
	@ConfigurationProperties("spring.datasource.druid.two")
	public DataSource dataSourceTwo() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * @Description: 配置多数据源到路由
	 * @author wang_yw
	 * @date 2018年8月3日 下午3:56:39
	 */
	@Bean(name = "dynamicDataSource")
	public DynamicDataSource setDataSource(@Qualifier("primaryDataSource") DataSource dataSourceOne,
			@Qualifier("secondaryDataSource") DataSource dataSourceTwo) {

		DynamicDataSource dataSource = new DynamicDataSource();

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(DataSourceConst.one, dataSourceOne);
		targetDataSources.put(DataSourceConst.two, dataSourceTwo);

		// 配置多数据源进路由
		dataSource.setTargetDataSources(targetDataSources);

		// 设置默认数据源
		dataSource.setDefaultTargetDataSource(dataSourceOne);

		return dataSource;
	}

	@Bean
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean setFactory(
			@Qualifier("dynamicDataSource") DynamicDataSource dataSource) {
		LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
		// 1--> 设置动态数据源
		lcemf.setDataSource(dataSource);

		// 2
		HibernateJpaVendorAdapter hja = new HibernateJpaVendorAdapter();
		hja.setShowSql(true);
		lcemf.setJpaVendorAdapter(hja);

		// 3
		Map<String, String> jpaPropertyMap = new HashMap<String, String>();
		jpaPropertyMap.put("javax.persistence.schema-generation.database.action", "none");
		lcemf.setJpaPropertyMap(jpaPropertyMap);

		// 4
		lcemf.setPackagesToScan("com.ewidecloud.entity");

		return lcemf;
	}

}
