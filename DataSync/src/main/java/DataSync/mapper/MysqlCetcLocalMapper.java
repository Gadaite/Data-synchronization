package DataSync.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
@DS("mysql_cetc_local")
public interface MysqlCetcLocalMapper {

    @Select("show tables")
    List<String> showTables();

    @Select("select * from ${tableName} limit ${count}")
    List<Map<String,Object>> readData(String tableName, Integer Count);
}
