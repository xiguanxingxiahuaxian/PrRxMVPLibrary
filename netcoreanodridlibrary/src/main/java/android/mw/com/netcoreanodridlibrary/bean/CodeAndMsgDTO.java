package android.mw.com.netcoreanodridlibrary.bean;

/**
 * 项目名称：Retrofit-mvp-rxjava
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/5/10 22:26
 * 修改备注
 */
public class CodeAndMsgDTO {

    /**
     * msg : 账号或密码不正确
     * code : 500
     */

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
