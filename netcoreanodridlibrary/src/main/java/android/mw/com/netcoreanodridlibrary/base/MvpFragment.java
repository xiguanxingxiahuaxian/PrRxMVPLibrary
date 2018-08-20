package android.mw.com.netcoreanodridlibrary.base;

import android.os.Bundle;


import rx.Subscription;

/**
 * 项目名称：Basego
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2017/9/6 12:48
 * 修改备注
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView{
    public  P presener;
    private Subscription subscription;
    @Override
    public void initBefore(Bundle savedInstanceState) {
        super.initBefore(savedInstanceState);
        presener=initPresenter();
        presener.addView(this);
    }

    protected abstract P initPresenter();

    @Override
    public void onCancel(Subscription subscription) {
        this.subscription=subscription;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        presener.detattch();
    }
}
