package kr.co.kjworld.imagesearch.model.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  nobrain3
 *  Retrofit instacne를 리턴.
 */
public class RetrofitInstance {
    private static final String BASE_URL = "https://dapi.kakao.com";

    private static Retrofit retrofit;
    private static String searchString;
    private static OkHttpClient client;


    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofit;
    }

    public static String getSearchString() {
        return searchString;
    }

    public static void setSearchString(String aSearchString) {
        searchString = aSearchString;
    }
}
