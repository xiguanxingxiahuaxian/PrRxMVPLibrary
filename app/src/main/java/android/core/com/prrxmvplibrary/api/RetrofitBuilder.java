package android.core.com.prrxmvplibrary.api;

import android.app.Application;
import android.core.com.prrxmvplibrary.base.BaseApp;
import android.core.com.prrxmvplibrary.utils.CatcheInterceptor;
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
 * 项目名称：Ieptproject
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/6/22 9:55
 * 修改备注
 */
public class RetrofitBuilder {
    Retrofit retrofit;

    // construct this for a new builder of retrofit
    public RetrofitBuilder(NewBuilder newBuilder) {
        this.retrofit = newBuilder.retrofit;
    }

    //return back retrofit
    public Retrofit getRetrofit() {
        return retrofit;
    }

    //inner class
    public static class NewBuilder {
        private String baseUrl;
        private Retrofit retrofit;
        private RetrofitBuilder retrofitBuilder;


        public NewBuilder() {
        }

        ;

        // get baseurl
        public NewBuilder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        // init retrofit
        public NewBuilder initRetrofit() {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(defaultHttpClient(null))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson()))
                    .build();
            return this;
        }

        public NewBuilder initRetrofit(Application application) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(defaultHttpClient(application))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson()))
                    .build();
            return this;
        }

        //apply build
        public RetrofitBuilder Build() {
            if (retrofitBuilder == null) {
                // wander, you have a retrofitBuilder , not need  ohter  one of this
                retrofitBuilder = new RetrofitBuilder(this);
            }
            return retrofitBuilder;
        }

        //gson
        private Gson gson() {
            return new GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create();
        }

        //okhttp add net interceptor and set new client
        private OkHttpClient defaultHttpClient(Application application) {
            // in develope, you can print log
            HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("OkHttpClient", "OkHttpMessage:" + message);
                }
            });
            loggingInterceptor.setLevel(level);
            if (application == null) {
                return new OkHttpClient.Builder().cache(cache()).addNetworkInterceptor(new CatcheInterceptor()).addInterceptor(loggingInterceptor)
                        .build();
            } else {
                return new OkHttpClient.Builder().addNetworkInterceptor(new CatcheInterceptor()).addInterceptor(loggingInterceptor)
                        .build();
            }
        }

        // method of cache
        public Cache cache(Application application) {
            File file = application.getExternalFilesDir(AppConfig.CATCHE_DIRECTORY);
            return new Cache(file, AppConfig.CATCHE_SIZE);
        }

        public Cache cache() {
            File file = BaseApp.getInstance().getExternalFilesDir(AppConfig.CATCHE_DIRECTORY);
            return new Cache(file, AppConfig.CATCHE_SIZE);
        }
    }
}

