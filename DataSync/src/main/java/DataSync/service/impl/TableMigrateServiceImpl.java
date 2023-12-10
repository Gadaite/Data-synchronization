package DataSync.service.impl;

import DataSync.dto.TableFilter;
import DataSync.mapper.*;
import DataSync.service.TableMigrateService;
import DataSync.tools.Result;
import DataSync.tools.ResultUtil;
import DataSync.vo.TableInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableMigrateServiceImpl implements TableMigrateService {

    @Autowired
    private HiveGisLocalMapper hiveGisLocalMapper;

    @Resource
    private HivePersonalizationLocalMapper hivePersonalizationLocalMapper;

    @Resource
    private MysqlCetcLocalMapper mysqlCetcLocalMapper;

    @Resource
    private MysqlDatasyncLocalMapper mysqlDatasyncLocalMapper;

    @Resource
    private MysqlHiveLocalMapper mysqlHiveLocalMapper;

    @Resource
    private PostgresGadaiteLocalMapper postgresGadaiteLocalMapper;

    @Resource
    private PostgresGisDataLocalMapper postgresGisDataLocalMapper;

    @Resource
    private PostgresPersonalLocalMapper postgresPersonalLocalMapper;

    @Resource
    private PostgresTrajectoryLocalMapper postgresTrajectoryLocalMapper;

    @Resource
    private PostgresWeatherLocalMapper postgresWeatherLocalMapper;


    @Override
    public Result<String> migrateTable(String sourceDatabase, String sourceDatabaseType, String sourceTable, String targetDatabase, String targetDatabaseType, String targetTable) {
        return null;
    }

    @Override
    public Result<List<TableInformation>> showTables(TableFilter tableFilter) {
        try{
            List<TableInformation> tableInformationalAll = allTableInformation();
            List<TableInformation> tableInformation = new ArrayList<TableInformation>();
            if (tableFilter.getDatabaseName() ==null && tableFilter.getDatabaseType() ==null){
                tableInformation = tableInformationalAll;
            }else if (tableFilter.getDatabaseName() != null && tableFilter.getDatabaseType() == null){
                tableInformation = tableInformationalAll.stream()
                        .filter(x -> x.getDatabaseName().equalsIgnoreCase(tableFilter.getDatabaseName())).collect(Collectors.toList());
            }else if (tableFilter.getDatabaseName() == null && tableFilter.getDatabaseType() != null){
                tableInformation = tableInformationalAll.stream()
                        .filter(x -> x.getDatabaseType().equalsIgnoreCase(tableFilter.getDatabaseType())).collect(Collectors.toList());
            }else {
                tableInformation = tableInformationalAll.stream()
                        .filter(x -> x.getDatabaseName().equalsIgnoreCase(tableFilter.getDatabaseName())
                                && x.getDatabaseType().equalsIgnoreCase(tableFilter.getDatabaseType())).collect(Collectors.toList());
            }
            return new Result<List<TableInformation>>(200, ResultUtil.success().getMsg(), tableInformation);
        }catch (Exception e){
            return new Result<List<TableInformation>>(400,e.getMessage(),null);
        }
    }


    private List<TableInformation> allTableInformation(){
        List<TableInformation> tables = new ArrayList<>();
        hiveGisLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("hive","gis",x)));
        hivePersonalizationLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("hive","personalinformation",x)));
        mysqlCetcLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("mysql","CETC10S",x)));
        mysqlDatasyncLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("mysql","datasync",x)));
        mysqlHiveLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("mysql","hive",x)));
        postgresGadaiteLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("postgres","Gadaite",x)));
        postgresGisDataLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("postgres","gisdata",x)));
        postgresPersonalLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("postgres","Personal",x)));
        postgresTrajectoryLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("postgres","trajectory",x)));
        postgresWeatherLocalMapper.showTables().forEach(x-> tables.add(new TableInformation("postgres","weather",x)));
        return tables;
    }

}
