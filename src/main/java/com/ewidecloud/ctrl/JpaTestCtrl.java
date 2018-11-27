package com.ewidecloud.ctrl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ewidecloud.dao.DataSourceConst;
import com.ewidecloud.dao.DataSourceContextHolder;
import com.ewidecloud.dao.repository.WxTemplateRepository;
import com.ewidecloud.entity.WxTemplate;

@RestController
@RequestMapping("/jpa-test")
public class JpaTestCtrl {

	private static final Logger logger = Logger.getLogger(JpaTestCtrl.class);

	@Autowired
	private WxTemplateRepository wxTemplateRepository;

	@RequestMapping("/1")
	String test() {

		// 配置数据源
		DataSourceContextHolder.setDataSourceType(DataSourceConst.one);

		List<WxTemplate> resultList = wxTemplateRepository.findAll();

		// 清除数据源
		DataSourceContextHolder.clearDataSourceType();

		return resultList.toString();
	}

	@RequestMapping("/2")
	String testTwo() {

		// 配置数据源
		DataSourceContextHolder.setDataSourceType(DataSourceConst.two);

		List<WxTemplate> resultList = wxTemplateRepository.findAll();

		// 清除数据源
		DataSourceContextHolder.clearDataSourceType();

		return resultList.toString();
	}

}
