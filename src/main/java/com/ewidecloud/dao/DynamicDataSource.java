package com.ewidecloud.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

/**
 * @ClassName DynamicDataSource
 * @Description 动态数据源类
 * @author wang_yw
 * @Date 2018年8月8日 下午12:04:57
 * @version 1.0.0
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }

}
