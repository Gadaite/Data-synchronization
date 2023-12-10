package DataSync.entity;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DS("mysql_datasync_local")
@TableName("record")
public class Record implements Serializable {
    @TableField(value = "sourceUrl")
    private String sourceUrl;

    @TableField(value = "sourceDatabase")
    private String sourceDatabase;

    @TableField(value = "sourceTable")
    private String sourceTable;

    @TableField(value = "targetUrl")
    private String targetUrl;

    @TableField(value = "targetDatabase")
    private String targetDatabase;

    @TableField(value = "targetTable")
    private String targetTable;

    @TableField(value = "eventTime")
    private Timestamp eventTime;

    @TableField(value = "result")
    private Boolean result;
}
