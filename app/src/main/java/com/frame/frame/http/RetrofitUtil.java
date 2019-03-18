package com.frame.frame.http;


import com.frame.frame.http.cookie.CookiesManager;
import com.frame.frame.init.AppInit;
import com.frame.frame.url.APPUrl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
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
    private static RetrofitUtil mInstance;
    private Retrofit mRetrofit;
    private APIService mApiService;
    /**
     * Okhttp拦截器
     */
    private Interceptor cacheNetInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            boolean networkAvailable = NetworkUtils.isNetworkAvailable(AppInit.getContext());
            if (networkAvailable) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            } else {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (networkAvailable) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        // 有网络时 设置缓存超时时间1个小时
                        .header("Cache-Control", "public, max-age=" + 60 * 60)
                        .build();

            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        // 无网络时，设置超时为1周
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 7 * 24 * 60 * 60)
                        .build();
            }
            return response;
        }

    };

    /**
     * 私有构造方法
     * private RetrofitUtil() {
     */
    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cookieJar(new CookiesManager(AppInit.getContext()))
                .cache(new Cache(new File(AppInit.getContext().getExternalCacheDir(),
                        "app"), 5 * 2024 * 1024))
                .addNetworkInterceptor(cacheNetInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                //多个服务器时启用这个方法
               /* .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //获取request
                        Request request = chain.request();
                        //获取request的创建者builder
                        Request.Builder builder = request.newBuilder();
                        //从request中获取headers，通过给定的键url_name
                        List<String> headerValues = request.headers("urlname");
                        if (headerValues != null && headerValues.size() > 0) {
                            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
                            builder.removeHeader("urlname");

                            //匹配获得新的BaseUrl
                            String headerValue = headerValues.get(0);
                            HttpUrl newBaseUrl = null;
                            if ("kangso".equals(headerValue)) {
                                newBaseUrl = HttpUrl.parse(APPUrl.URL);
                            } else if ("search".equals(headerValue)) {
                                newBaseUrl = HttpUrl.parse(APPUrl.KANGSO_URL);
                            } else if ("assistant".equals(headerValue)) {
                                newBaseUrl = HttpUrl.parse(APPUrl.KANGSO_DIAGNOSIS_ASSISTANT);
                            }
                            //从request中获取原有的HttpUrl实例oldHttpUrl
                            HttpUrl oldHttpUrl = request.url();
                            //重建新的HttpUrl，修改需要修改的url部分
                            HttpUrl newFullUrl = oldHttpUrl
                                    .newBuilder()
                                    .scheme(newBaseUrl.scheme())
                                    .host(newBaseUrl.host())
                                    .port(newBaseUrl.port())
                                    .build();

                            //重建这个request，通过builder.url(newFullUrl).build()；
                            //然后返回一个response至此结束修改
                            return chain.proceed(builder.url(newFullUrl).build());
                        } else {
                            return chain.proceed(request);
                        }
                    }
                }).build()*/;

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                //单个服务器时直接用这个就可以，（ps:不管单个还是多个的时候这里必须有一个默认的服务器地址）
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

    //释放资源
    public void destroy() {
        mApiService = null;
        mInstance = null;
        mRetrofit = null;
    }
}



