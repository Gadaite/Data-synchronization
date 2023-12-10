package DataSync.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInformation implements Serializable {

    private String databaseType;
    private String databaseName;
    private String tableName;
}
