package io.github.bluething.spring.reactive.joshbook.bootstrap.harcoded;

import io.github.bluething.spring.reactive.joshbook.bootstrap.BaseCustomerService;
import io.github.bluething.spring.reactive.joshbook.bootstrap.DataSourceUtils;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class DevelopmentOnlyCustomerService extends BaseCustomerService {
    protected DevelopmentOnlyCustomerService() {
        super(buildDataSource());
    }

    //create datasource using an embedded H2
    //the datasource will pass into super constructor
    //ideally we don't implement it like this
    private static DataSource buildDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
        return DataSourceUtils.initializeDdl(dataSource);
    }
}
