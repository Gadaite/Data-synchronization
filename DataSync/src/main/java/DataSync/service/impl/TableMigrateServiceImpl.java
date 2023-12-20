package DataSync.service.impl;

import DataSync.dto.TableFilter;
import DataSync.mapper.*;
import DataSync.service.TableMigrateService;
import DataSync.tools.HiveDS;
import DataSync.tools.Result;
import DataSync.tools.ResultUtil;
import DataSync.vo.TableInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Resource
    private SparkSession sparkSession;


    @Override
    public Result<List<Map<String,Object>>> readTable(String sourceDatabase, String sourceDatabaseType, String sourceTable, Integer count) {
        List<TableInformation> tableInformational = allTableInformation();
        Result<String> result = new Result<>();
        try{
            List<TableInformation> tableInformationList1 = tableInformational.stream().filter(x ->
                    x.getDatabaseType().equalsIgnoreCase(sourceDatabaseType) && x.getDatabaseName().equalsIgnoreCase(sourceDatabase)
                            && x.getTableName().equalsIgnoreCase(sourceTable)
            ).collect(Collectors.toList());
            if (tableInformationList1.size() == 0){
                return new Result<>(400,"源数据不存在",null);
            }else {
                List<Map<String,Object>> sourceData = readDataFromDb(sourceDatabaseType, sourceDatabase, sourceTable, count);
                return new Result<List<Map<String,Object>>>(200,ResultUtil.success().getMsg(),sourceData);
            }
        }catch (Exception e){
            return new Result<>(400,e.getMessage(),null);
        }
    }

    @Override
    public Result<String> migrateTable(String sourceDatabase, String sourceDatabaseType, String sourceTable, String targetDatabase, String targetDatabaseType, String targetTable) {
        List<TableInformation> tableInformational = allTableInformation();
        try{
            List<TableInformation> tableInformationList1 = tableInformational.stream().filter(x ->
                    x.getDatabaseType().equalsIgnoreCase(sourceDatabaseType) && x.getDatabaseName().equalsIgnoreCase(sourceDatabase)
                            && x.getTableName().equalsIgnoreCase(sourceTable)
            ).collect(Collectors.toList());
            if (tableInformationList1.size() == 0){
                return new Result<>(400,"源数据不存在",null);
            }
            List<TableInformation> tableInformationList2 = tableInformational.stream().filter(x ->
                    x.getDatabaseType().equalsIgnoreCase(targetDatabaseType) && x.getDatabaseName().equalsIgnoreCase(targetDatabase)
            ).collect(Collectors.toList());
            if (tableInformationList2.size() == 0){
                return new Result<>(400,"目标数据库不存在",null);
            }else {
                long count = tableInformationList2.stream().filter(x ->
                        x.getTableName().equalsIgnoreCase(targetTable)
                ).count();
                if (count != (long) 0){
                    return new Result<>(400,"目标数据表已存在",null);
                }else {
                    getDataBySource(sourceDatabase,sourceDatabaseType,sourceTable);
                    System.out.println();
                }
            }
        }catch (Exception e){
            return new Result<>(400,e.getMessage(),null);
        }
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


    private List<Map<String,Object>> readDataFromDb(String databaseType,String database,String tableName,Integer count){
        if (databaseType.equalsIgnoreCase("hive")){
            if (database.equalsIgnoreCase("gis")){
                return hiveGisLocalMapper.readData(tableName,count);
            }else if (database.equalsIgnoreCase("personalinformation")){
                return hivePersonalizationLocalMapper.readData(tableName,count);
            }
        }else if (databaseType.equalsIgnoreCase("mysql")){
            if (database.equalsIgnoreCase("datasync")){
                return mysqlDatasyncLocalMapper.readData(tableName,count);
            }else if (database.equalsIgnoreCase("CETC10S")){
                return mysqlCetcLocalMapper.readData(tableName,count);
            }else if (database.equalsIgnoreCase("hive")){
                return mysqlHiveLocalMapper.readData(tableName,count);
            }
        }else if (databaseType.equalsIgnoreCase("postgres")){
            if (database.equalsIgnoreCase("weather")){
                return postgresWeatherLocalMapper.readData(tableName,count);
            }else if (database.equalsIgnoreCase("trajectory")){
                return postgresTrajectoryLocalMapper.readData(tableName,count);
            }else if (database.equalsIgnoreCase("Personal")){
                return postgresPersonalLocalMapper.readData(tableName,count);
            }else if (database.equalsIgnoreCase("Gadaite")){
                return postgresGadaiteLocalMapper.readData(tableName,count);
            }else {
                return postgresGisDataLocalMapper.readData(tableName,count);
            }
        }else {
            return null;
        }
        return null;
    }


    private DataSet<Row> getDataBySource(String sourceDatabase, String sourceDatabaseType, String sourceTable){
        if (sourceDatabaseType.equalsIgnoreCase("hive")){
            HiveDS hiveDS = new HiveDS();
            if (sourceDatabase.equalsIgnoreCase("gis")){
                DataSource dataSource = hiveDS.hiveGisLocalDataSource();
//                sparkJdbcRead(sparkSession,null,null,null,null)
                System.out.println("");
            }
        }
        return null;
    }

    private Dataset<Row> sparkJdbcRead(SparkSession spark, String url, String user, String password, String databasename, String sql_or_tablename){
        Dataset<Row> dataset = spark.read().format("jdbc")
                .option("driver","org.postgresql.Driver")
                .option("url", "jdbc:postgresql://" + url + "/" + databasename)
                .option("user", user)
                .option("password", password)
                .option("dbtable", "( " + sql_or_tablename + " ) t").load();
        return dataset;
    }
}
