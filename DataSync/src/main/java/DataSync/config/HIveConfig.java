package DataSync.config;

import DataSync.tools.DruidDataSourceTool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

public class HIveConfig {
    @Value("spring.datasource.dynamic.datasource.hive_gis_local.driver-class-name")
    private String hive_gis_local_driver;

    @Value("spring.datasource.dynamic.datasource.hive_gis_local.url")
    private String hive_gis_local_url;

    @Value("spring.datasource.dynamic.datasource.hive_gis_local.username")
    private String hive_gis_local_username;

    @Value("spring.datasource.dynamic.datasource.hive_gis_local.password")
    private String hive_gis_local_password;

    @Value("spring.datasource.dynamic.datasource.hive_personalization_local.driver-class-name")
    private String hive_personalization_local_driver;

    @Value("spring.datasource.dynamic.datasource.hive_personalization_local.url")
    private String hive_personalization_local_url;

    @Value("spring.datasource.dynamic.datasource.hive_personalization_local.username")
    private String hive_personalization_local_username;

    @Value("spring.datasource.dynamic.datasource.hive_personalization_local.password")
    private String hive_personalization_local_password;

    @Bean(name = "hiveGisLocalDataSource")
    @Qualifier("hiveGisLocalDataSource")
    @Primary
    public DataSource hiveGisLocalDataSource(){
        return DruidDataSourceTool.getDataSource(hive_gis_local_url, hive_gis_local_driver, hive_gis_local_username, hive_gis_local_password);
    }


    @Bean(name = "hivePersonalizationLocalDataSource")
    @Qualifier("hivePersonalizationLocalDataSource")
    @Primary
    public DataSource hivePersonalizationLocalDataSource(){
        return DruidDataSourceTool.getDataSource(hive_personalization_local_url, hive_personalization_local_driver, hive_personalization_local_username, hive_personalization_local_password);
    }

}
