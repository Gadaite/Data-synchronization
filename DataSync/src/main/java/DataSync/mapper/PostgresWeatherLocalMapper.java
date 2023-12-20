package DataSync.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
@DS("postgres_weather_local")
public interface PostgresWeatherLocalMapper {

    @Select("SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema'")
    List<String> showTables();

    @Select("select * from ${tableName} limit ${count}")
    List<Map<String,Object>> readData(String tableName, Integer Count);
}
