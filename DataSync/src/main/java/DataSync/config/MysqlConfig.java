package DataSync.config;

import DataSync.tools.DruidDataSourceTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MysqlConfig {
    //  mysql_cetc_local
    @Value("spring.datasource.dynamic.datasource.mysql_cetc_local.driver-class-name")
    private String mysql_cetc_local_driver;

    @Value("spring.datasource.dynamic.datasource.mysql_cetc_local.url")
    private String mysql_cetc_local_url;

    @Value("spring.datasource.dynamic.datasource.mysql_cetc_local.username")
    private String mysql_cetc_local_username;

    @Value("spring.datasource.dynamic.datasource.mysql_cetc_local.password")
    private String mysql_cetc_local_password;


    @Bean(name = "mysqlCetcLocalDataSource")
    public DataSource mysqlCetcLocalDataSource(){
        return DruidDataSourceTool.getDataSource(mysql_cetc_local_url,mysql_cetc_local_driver,mysql_cetc_local_username,mysql_cetc_local_password);
    }

    //  mysql_hive_local
    @Value("spring.datasource.dynamic.datasource.mysql_hive_local.driver-class-name")
    private String mysql_hive_local_driver;

    @Value("spring.datasource.dynamic.datasource.mysql_hive_local.url")
    private String mysql_hive_local_url;

    @Value("spring.datasource.dynamic.datasource.mysql_hive_local.username")
    private String mysql_hive_local_username;

    @Value("spring.datasource.dynamic.datasource.mysql_hive_local.password")
    private String mysql_hive_local_password;

    @Bean(name = "mysqlHiveLocalDataSource")
    public DataSource mysqlHiveLocalDataSource(){
        return DruidDataSourceTool.getDataSource(mysql_hive_local_url,mysql_hive_local_driver,mysql_hive_local_username,mysql_hive_local_password);
    }

    //  mysql_datasync_local
    @Value("spring.datasource.dynamic.datasource.mysql_datasync_local.driver-class-name")
    private String mysql_datasync_local_driver;

    @Value("spring.datasource.dynamic.datasource.mysql_datasync_local.url")
    private String mysql_datasync_local_url;

    @Value("spring.datasource.dynamic.datasource.mysql_datasync_local.username")
    private String mysql_datasync_local_username;

    @Value("spring.datasource.dynamic.datasource.mysql_datasync_local.password")
    private String mysql_datasync_local_password;

    @Bean(name = "mysqlDatasyncLocalDataSource")
    public DataSource mysqlDatasyncLocalDataSource(){
        return DruidDataSourceTool.getDataSource(mysql_datasync_local_url,mysql_datasync_local_driver,mysql_datasync_local_username,mysql_datasync_local_password);
    }
}
