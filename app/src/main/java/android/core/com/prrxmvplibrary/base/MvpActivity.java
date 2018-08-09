package android.core.com.prrxmvplibrary.base;

import android.os.Bundle;


import rx.Subscription;

/**
 * 项目名称：Basego
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2017/9/6 11:55
 * 修改备注
 */
public abstract class MvpActivity <P extends BasePresenter> extends BaseActivity implements BaseView{
    public  P presener;
    private Subscription subscription;

    @Override
    public void initBefore(Bundle savedInstanceState) {
        super.initBefore(savedInstanceState);
        presener =initPresener();
        presener.addView(this);
    }

    @Override
    public void onCancel(Subscription subscription) {
        this.subscription=subscription;
    }

    protected abstract P initPresener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        presener.detattch();
    }
}
