package DataSync.tools;

/**
 * 处理返回给客户端结果的工具类
 */
public class ResultUtil {
    /**
     * 处理请求成功,返回数据
     *
     * @param o 具体数据
     * @return 返回统一响应结果
     */
    public static Result<Object> success(Object o){
        Result<Object> girlResult = new Result<>();
        girlResult.setCode(1);
        girlResult.setMsg("success");
        girlResult.setData(o);
        return girlResult;
    }

    /**
     * 处理请求成功，无数据返回
     *
     * @return 返回统一响应结果
     */
    public static Result<Object> success(){
        return success(null);
    }

    /**
     * 失败请求
     *
     * @return 返回统一响应结果
     */
    public static Result<Object> error(Integer code,String msg){
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(code);
        objectResult.setMsg(msg);
        objectResult.setData(null);
        return objectResult;
    }
}
