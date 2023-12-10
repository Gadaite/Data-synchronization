package DataSync.controller;

import DataSync.dto.TableFilter;
import DataSync.service.TableMigrateService;
import DataSync.tools.Result;
import DataSync.vo.TableInformation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/tableMigrate")
@Api(value = "TableMigrateController",tags = "01.tableMigrate")
public class TableMigrateController {

    @Resource
    private TableMigrateService tableMigrateService;

    @GetMapping("/migrateTable")
    @ApiOperation("迁移表(从一个数据库到另一个数据库)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceDatabase",value = "源数据库",dataType = "String",required = true),
            @ApiImplicitParam(name = "sourceDatabaseType",value = "源数据库类型",dataType = "String",required = true),
            @ApiImplicitParam(name = "sourceTable",value = "源数据库表",dataType = "String",required = true),
            @ApiImplicitParam(name = "targetDatabase",value = "目标数据库",dataType = "String",required = true),
            @ApiImplicitParam(name = "targetDatabaseType",value = "目标数据库类型",dataType = "String",required = true),
            @ApiImplicitParam(name = "targetTable",value = "目标数据库表",dataType = "String",required = true)
    })
    public Result<String> migrateTable(HttpServletRequest request,String sourceDatabase,String sourceDatabaseType,String sourceTable,String targetDatabase,String targetDatabaseType,String targetTable){
        System.out.println(request.getRequestURL());
        return tableMigrateService.migrateTable(sourceDatabase,sourceDatabaseType,sourceTable,targetDatabase,targetDatabaseType,targetTable);
    }

    @PostMapping("/showTables")
    @ApiOperation("过滤显示所有表")
    public Result<List<TableInformation>> showTables(HttpServletRequest request, @RequestBody TableFilter tableFilter){
        System.out.println(request.getRequestURL());
        return tableMigrateService.showTables(tableFilter);
    }
}
