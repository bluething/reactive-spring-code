package io.github.bluething.spring.reactive.joshbook.bootstrap.basiccdi;

import io.github.bluething.spring.reactive.joshbook.bootstrap.BaseCustomerService;

import javax.sql.DataSource;

class DataSourceCustomerService extends BaseCustomerService {
    //parametrize the datasource, more flexible
    DataSourceCustomerService(DataSource ds) {
        super(ds);
    }
}
