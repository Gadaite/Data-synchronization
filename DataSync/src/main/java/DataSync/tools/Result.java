package DataSync.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
 * 统一响应结果类
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7557258753883492883L;
    /** 状态码  */
    private Integer code;

    /** 提示信息  */
    private String msg;

    /** 返回数据  */
    private T data;
}
