package com.frame.frame.http;


import com.frame.frame.http.cookie.CookiesManager;
import com.frame.frame.init.AppInit;
import com.frame.frame.url.APPUrl;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * ===================================
 * describe:retrofit工具类
 * author:zhuang
 * ===================================
 */
public class RetrofitUtil {
    public static final int DEFAULT_TIMEOUT = 1000;
    private Retrofit mRetrofit;
    private APIService mApiService;
    private static RetrofitUtil mInstance;

    /**
     * 私有构造方法
     * private RetrofitUtil() {
     */
    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cookieJar(new CookiesManager(AppInit.getContext()))
                .cache(new Cache(new File(AppInit.getContext().getExternalCacheDir(),
                        "app"), 5 * 2024 * 1024))
                .addInterceptor(cacheNetInterceptor)
                .addNetworkInterceptor(cacheNetInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(APPUrl.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(APIService.class);
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    /**
     * Okhttp拦截器
     */
    private Interceptor cacheNetInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (NetworkUtils.isNetworkAvailable(AppInit.getContext())) {
                Response response = chain.proceed(request);
                // read from cache for 0 ratingbar_view  有网络不会使用缓存数据
                int maxAge = 10;
                String cacheControl = request.cacheControl().toString();
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                //无网络时强制使用缓存数据
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Response response = chain.proceed(request);
                int maxStale = 60 * 60 * 24 * 3;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }

    };
    //释放资源
    public void destroy() {
        mApiService = null;
        mInstance = null;
        mRetrofit = null;
    }
}



