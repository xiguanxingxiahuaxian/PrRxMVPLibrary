package android.mw.com.netcoreanodridlibrary.bean;

/**
 * 项目名称：DdyyPayProject
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/10/23 9:12
 * 修改备注
 */
public class CodeAndMessageDTO {

    /**
     * code : -1109
     * message : 501392当前状态不允许下单
     * path : null
     * timestamp : 1540256827623
     */

    private String code;
    private String message;
    private Object path;
    private long timestamp;
    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPath() {
        return path;
    }

    public void setPath(Object path) {
        this.path = path;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
