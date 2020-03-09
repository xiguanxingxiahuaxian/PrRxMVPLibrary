package android.mw.com.netcoreanodridlibrary.base;

import java.lang.ref.WeakReference;
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
    private WeakReference<V> mViewReference;

    public void addView(V vi){
        // this.view=v;

        this.mViewReference = new WeakReference<V>(vi) ;
        // 用代理对象 动态代理
        view = (V) Proxy.newProxyInstance(vi.getClass().getClassLoader(), vi.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 动态代理每次都会执行这个方法，调用的是被代理的对象（就是mView）
                if (mViewReference == null || mViewReference.get() == null) {
                    return null ;
                }else{
                    return method.invoke(mViewReference.get(), args);
                }
            }
        });
    }
    public void detattch(){
        this.mViewReference.clear();
        this.mViewReference = null ;
    }
}
