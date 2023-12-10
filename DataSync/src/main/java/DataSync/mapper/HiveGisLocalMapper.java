package DataSync.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@DS("hive_gis_local")
public interface HiveGisLocalMapper {

    @Select("show tables")
    List<String> showTables();
}
