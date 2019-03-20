package kr.co.kjworld.imagesearch.presenter;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import kr.co.kjworld.imagesearch.model.KakaoImageSearchService;
import kr.co.kjworld.imagesearch.model.network.RetrofitInstance;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetIntractorImpl implements GetImageDataInteractor {
    public static final int PAGE_SIZE = 30;
    private static final int FIRST_PAGE = 1;
    private static final String SORT = "accuracy";
    private static final String AUTH = "KakaoAK f3a3676ce605a55fa482f111aa67e2b4";

    @Override
    public void getImageSearchData(final OnFinishedListener onFinishedListener) {
        KakaoImageSearchService kakaoImageSearchService = RetrofitInstance.getRetrofitInstance().create(KakaoImageSearchService.class);
        String searchString = RetrofitInstance.getSearchString();
        //Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData("KakaoAK f3a3676ce605a55fa482f111aa67e2b4", "설현");
        Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData(AUTH, searchString, SORT , FIRST_PAGE, PAGE_SIZE);
        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ImageSearchResponseData>() {
            @Override
            public void onResponse(Call<ImageSearchResponseData> call, Response<ImageSearchResponseData> response) {
                onFinishedListener.onFinished(response.body().getMeta(), response.body().getDocuments());
            }

            @Override
            public void onFailure(Call<ImageSearchResponseData> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }
        });
    }
}
