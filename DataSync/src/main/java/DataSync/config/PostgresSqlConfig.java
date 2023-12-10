package DataSync.config;

import DataSync.tools.DruidDataSourceTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class PostgresSqlConfig {

    //  postgres_gisData_local
    @Value("spring.datasource.dynamic.datasource.postgres_gisData_local.driver-class-name")
    private String postgres_gisData_local_driver;

    @Value("spring.datasource.dynamic.datasource.postgres_gisData_local.url")
    private String postgres_gisData_local_url;

    @Value("spring.datasource.dynamic.datasource.postgres_gisData_local.username")
    private String postgres_gisData_local_username;

    @Value("spring.datasource.dynamic.datasource.postgres_gisData_local.password")
    private String postgres_gisData_local_password;

    @Bean(name = "postgresGisDataLocalDataSource")
    public DataSource postgresGisDataLocalDataSource(){
        return DruidDataSourceTool.getDataSource(postgres_gisData_local_url,postgres_gisData_local_driver,postgres_gisData_local_username,postgres_gisData_local_password);
    }

    //  postgres_Gadaite_local
    @Value("spring.datasource.dynamic.datasource.postgres_Gadaite_local.driver-class-name")
    private String postgres_Gadaite_local_driver;

    @Value("spring.datasource.dynamic.datasource.postgres_Gadaite_local.url")
    private String postgres_Gadaite_local_url;

    @Value("spring.datasource.dynamic.datasource.postgres_Gadaite_local.username")
    private String postgres_Gadaite_local_username;

    @Value("spring.datasource.dynamic.datasource.postgres_Gadaite_local.password")
    private String postgres_Gadaite_local_password;

    @Bean(name = "postgresGadaiteLocalDataSource")
    public DataSource postgresGadaiteLocalDataSource(){
        return DruidDataSourceTool.getDataSource(postgres_Gadaite_local_url,postgres_Gadaite_local_driver,postgres_Gadaite_local_username,postgres_Gadaite_local_password);
    }

    //  postgres_Personal_local
    @Value("spring.datasource.dynamic.datasource.postgres_Personal_localpostgres_Personal_local.driver-class-name")
    private String postgres_Personal_local_driver;

    @Value("spring.datasource.dynamic.datasource.postgres_Personal_local.url")
    private String postgres_Personal_local_url;

    @Value("spring.datasource.dynamic.datasource.postgres_Personal_local.username")
    private String postgres_Personal_local_username;

    @Value("spring.datasource.dynamic.datasource.postgres_Personal_local.password")
    private String postgres_Personal_local_password;

    @Bean(name = "postgresPersonalLocalDataSource")
    public DataSource postgresPersonalLocalDataSource(){
        return DruidDataSourceTool.getDataSource(postgres_Personal_local_url,postgres_Personal_local_driver,postgres_Personal_local_username,postgres_Personal_local_password);
    }

    //  postgres_trajectory_local
    @Value("spring.datasource.dynamic.datasource.postgres_trajectory_local.driver-class-name")
    private String postgres_trajectory_local_driver;

    @Value("spring.datasource.dynamic.datasource.postgres_trajectory_local.url")
    private String postgres_trajectory_local_url;

    @Value("spring.datasource.dynamic.datasource.postgres_trajectory_local.username")
    private String postgres_trajectory_local_username;

    @Value("spring.datasource.dynamic.datasource.postgres_trajectory_local.password")
    private String postgres_trajectory_local;

    @Bean(name = "postgresTrajectoryLocalDataSource")
    public DataSource postgresTrajectoryLocalDataSource(){
        return DruidDataSourceTool.getDataSource(postgres_trajectory_local_url,postgres_trajectory_local_driver,postgres_trajectory_local_username,postgres_trajectory_local);
    }

    //  postgres_weather_local
    @Value("spring.datasource.dynamic.datasource.postgres_weather_local.driver-class-name")
    private String postgres_weather_local_driver;

    @Value("spring.datasource.dynamic.datasource.postgres_weather_local.url")
    private String postgres_weather_local_url;

    @Value("spring.datasource.dynamic.datasource.postgres_weather_local.username")
    private String postgres_weather_local_username;

    @Value("spring.datasource.dynamic.datasource.postgres_weather_local.password")
    private String postgres_weather_local_password;

    @Bean(name = "postgresWeatherLocalDataSource")
    public DataSource postgresWeatherLocalDataSource(){
        return DruidDataSourceTool.getDataSource(postgres_weather_local_url,postgres_weather_local_driver,postgres_weather_local_username,postgres_weather_local_password);
    }

}
