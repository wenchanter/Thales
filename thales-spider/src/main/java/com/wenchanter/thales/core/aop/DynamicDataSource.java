package com.wenchanter.thales.core.aop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceSwitcher.getDataSource();
    }

//	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//		return null;
//	}

}