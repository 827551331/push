package com.ewidecloud.ctrl;

import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ewidecloud.dao.DataSourceConst;
import com.ewidecloud.dao.DataSourceContextHolder;

@RestController
@RequestMapping("/CollectExceptionInfo")
public class CollectExceptionInfo {

    private static final Logger logger = Logger.getLogger(CollectExceptionInfo.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${param.uuid}")
    private String uuid;

    @RequestMapping("/{type}")
    String test(@PathVariable String type) {
        logger.infof("-->测试{}", "type");
        return "type:[" + type + "]+";
    }

    @RequestMapping("/{type}/one")
    String testInfo(@PathVariable String type) {
        // 切换为数据源一
        DataSourceContextHolder.setDataSourceType(DataSourceConst.one);

        String sql = "SELECT 1 FROM DUAL";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        logger.infof("-->测试info{}", result);

        // 使用完毕之后清空
        DataSourceContextHolder.clearDataSourceType();
        return result.toString();
    }

    @RequestMapping("/{type}/two")
    String testError(@PathVariable String type) {
        // 切换为数据源二
        DataSourceContextHolder.setDataSourceType(DataSourceConst.two);

        String sql = "SELECT 2 FROM DUAL";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        logger.infof("-->测试info{}", result);

        // 使用完毕之后清空
        DataSourceContextHolder.clearDataSourceType();
        return result.toString();
    }

}
