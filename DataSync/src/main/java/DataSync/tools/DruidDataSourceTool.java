package DataSync.tools;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public class DruidDataSourceTool {
    public static DataSource getDataSource(String url, String driver, String username, String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setValidationQuery("select 1");
        return druidDataSource;
    }
}
