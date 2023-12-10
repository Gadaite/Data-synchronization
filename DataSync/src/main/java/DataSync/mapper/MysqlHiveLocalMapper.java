package DataSync.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@DS("mysql_hive_local")
public interface MysqlHiveLocalMapper {

    @Select("show tables")
    List<String> showTables();
}
