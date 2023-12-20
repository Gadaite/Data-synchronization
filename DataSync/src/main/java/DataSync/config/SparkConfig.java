package DataSync.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Value("${spark.cores}")
    private String cores;

    @Value("${spark.appName}")
    private String appName;

    @Value("${spark.logLevel}")
    private String logLevel;

    @Value("${spark.ui.port}")
    private Integer port;

    @Bean(name = "sparkSession")
    public SparkSession sparkSession(){
        SparkSession session = SparkSession.builder().config("spark.ui.showConsoleProgress", "false")
                .config("spark.ui.port",port)
                .master(cores).appName(appName).enableHiveSupport().getOrCreate();
        session.sparkContext().setLogLevel(logLevel);
        return session;
    }
}
