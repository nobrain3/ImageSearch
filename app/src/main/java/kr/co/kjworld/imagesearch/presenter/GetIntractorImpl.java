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
    int mCurPage;
    String KakaoAuth = "KakaoAK f3a3676ce605a55fa482f111aa67e2b4";
    public GetIntractorImpl() {
        mCurPage = 1;
    }
    @Override
    public void getImageSearchData(final OnFinishedListener onFinishedListener, String searchString, String sort, int page, int sizeInPage) {
        KakaoImageSearchService kakaoImageSearchService = RetrofitInstance.getRetrofitInstance().create(KakaoImageSearchService.class);
        //Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData("KakaoAK f3a3676ce605a55fa482f111aa67e2b4", "설현");
        Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData(KakaoAuth, searchString, sort , page, sizeInPage);
        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ImageSearchResponseData>() {
            @Override
            public void onResponse(Call<ImageSearchResponseData> call, Response<ImageSearchResponseData> response) {
                mCurPage++;
                onFinishedListener.onFinished(response.body().getMeta(), response.body().getDocuments());
            }

            @Override
            public void onFailure(Call<ImageSearchResponseData> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }
        });
    }
}
