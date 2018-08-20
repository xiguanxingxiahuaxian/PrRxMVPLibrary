package android.mw.com.netcoreanodridlibrary.view.activity.impl;


import android.mw.com.netcoreanodridlibrary.api.ExceptionHandle;
import android.mw.com.netcoreanodridlibrary.base.BaseView;

/**
 * 项目名称：Basego
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2017/9/6 11:53
 * 修改备注
 */
public interface SimpleView extends BaseView {
    void  onSuccess(Object object);
    void  onFail(ExceptionHandle.ResponeThrowable t);

}
