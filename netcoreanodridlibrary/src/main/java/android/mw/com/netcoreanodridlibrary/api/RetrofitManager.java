package android.mw.com.netcoreanodridlibrary.api;

import android.mw.com.netcoreanodridlibrary.base.BaseApp;
import android.mw.com.netcoreanodridlibrary.utils.CatcheInterceptor;
import android.mw.com.netcoreanodridlibrary.utils.sslsocketclient;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名称：Basego
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2017/8/30 16:23
 * 修改备注
 */
public class RetrofitManager {

  private volatile static RetrofitManager  retrofitManager;
  private Retrofit retrofit;


  public RetrofitManager(){
    initRetrofitManager();
  }

  private void initRetrofitManager() {
    retrofit= new Retrofit.Builder().baseUrl(UrlContant.BASEURL).client(defaultHttpClient())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .build();

  }
  public static RetrofitManager getSingleton(){
    if(retrofitManager==null){
      synchronized (RetrofitManager.class){
        retrofitManager =new RetrofitManager();
      }
    }
    return retrofitManager;
  }
  //gson
  private Gson gson(){
    return new GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create();
  }
  //okhttp添加网络拦截以及缓存
  private  OkHttpClient defaultHttpClient(){
    //开发环境中，打印日志 发布版不在打印
    HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
    //新建log拦截器
    HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
      @Override
      public void log(String message) {
        Log.i("OkHttpClient","OkHttpMessage:"+message);
      }
    });
    loggingInterceptor.setLevel(level);
    return  new OkHttpClient.Builder().cache(cache()).addNetworkInterceptor(new CatcheInterceptor()).addInterceptor(loggingInterceptor)
            .sslSocketFactory(sslsocketclient.getSSLSocketFactory()).build();
  }

  public Cache cache(){
    File file = BaseApp.getInstance().getExternalFilesDir(AppConfig.CATCHE_DIRECTORY);
     return  new Cache(file,AppConfig.CATCHE_SIZE);
  }
}
