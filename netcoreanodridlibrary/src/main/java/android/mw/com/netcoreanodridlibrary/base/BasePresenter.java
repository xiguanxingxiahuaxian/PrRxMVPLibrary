package android.mw.com.netcoreanodridlibrary.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 项目名称：Basego
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2017/9/6 11:47
 * 修改备注
 */
public class BasePresenter<V extends BaseView> {

    public V view;

    public void addView(final V v){
        this.view= (V) Proxy.newProxyInstance(v.getClass().getClassLoader(), v.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if(view!=null){
                    method.invoke(v,objects);
                };
                return null;
            }
        });
    }
    public void detattch(){
        if(view!=null){
            view=null;
        }
    }
}
