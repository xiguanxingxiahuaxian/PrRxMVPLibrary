package android.mw.com.netcoreanodridlibrary.net;

import android.content.Context;
import android.mw.com.netcoreanodridlibrary.impl.netStateImpl;
import android.widget.Toast;


public class DefaultNetChangeObserver extends NetChangeObserver {
    private netStateImpl netStawe;
    Context context;

    public DefaultNetChangeObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onConnect(NetWorkUtil.NetType type) {
        super.onConnect(type);
        switch (type) {
            case NETWORK_2_G:
                break;
            case NETWORK_WIFI:
                if(netStawe!=null){
                    netStawe.netState(true);
                }
                Toast.makeText(context, "网络已连接", Toast.LENGTH_SHORT).show();
                break;
            case NETWORK_4_G:
                break;
            case NETWORK_3_G:
                break;
            default:
                break;

        }

    }

    @Override
    public void onDisConnect() {
        super.onDisConnect();
        Toast.makeText(context, "网络已断开", Toast.LENGTH_SHORT).show();
        if(netStawe!=null){
            netStawe.netState(false);
        }
    }

    public void  getNetState(netStateImpl netStawe){
        this.netStawe=netStawe;
    }
}
