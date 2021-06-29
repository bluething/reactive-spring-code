package io.github.bluething.spring.bootstrap.basiccdi;

import io.github.bluething.spring.bootstrap.BaseCustomerService;

import javax.sql.DataSource;

class DataSourceCustomerService extends BaseCustomerService {
    //parametrize the datasource, more flexible
    DataSourceCustomerService(DataSource ds) {
        super(ds);
    }
}
