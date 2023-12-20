package DataSync.service;

import DataSync.dto.TableFilter;
import DataSync.tools.Result;
import DataSync.vo.TableInformation;

import java.util.List;
import java.util.Map;


public interface TableMigrateService {
    Result<String> migrateTable(String sourceDatabase,String sourceDatabaseType, String sourceTable, String targetDatabase, String targetDatabaseType,String targetTable);

    Result<List<TableInformation>> showTables(TableFilter tableFilter);

    Result<List<Map<String,Object>>> readTable(String sourceDatabase, String sourceDatabaseType, String sourceTable, Integer count);
}
