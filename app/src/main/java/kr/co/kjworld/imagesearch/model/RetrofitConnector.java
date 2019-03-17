package kr.co.kjworld.imagesearch.model;

import retrofit2.Retrofit;

public class RetrofitConnector {
    public void connect()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dapi.kakao.com")
                .build();

        KakaoImageSearchService imageSearchService = retrofit.create(KakaoImageSearchService.class);
    }
}
