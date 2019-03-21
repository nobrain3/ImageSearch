package kr.co.kjworld.imagesearch.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import kr.co.kjworld.imagesearch.model.KakaoImageSearchService;
import kr.co.kjworld.imagesearch.model.network.RetrofitInstance;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * [nobrain3]
 * 페이징 할때 데이터를 얻어오는 작업.
 * PageKeyedDataSource를 상속받아서 페이지 단위로 요청한다.
 */
public class ImageDataSource extends PageKeyedDataSource<Integer, ImageSearchResponseData.Document> {
    public static final int PAGE_SIZE = 30;
    private static final int FIRST_PAGE = 1;
    private static final String SORT = "accuracy";
    private static final String AUTH = "KakaoAK f3a3676ce605a55fa482f111aa67e2b4";

    // 초기 데이터를 로드한다.
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, ImageSearchResponseData.Document> callback) {
        KakaoImageSearchService kakaoImageSearchService = RetrofitInstance.getRetrofitInstance().create(KakaoImageSearchService.class);
        String searchString = RetrofitInstance.getSearchString();
        //Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData("KakaoAK f3a3676ce605a55fa482f111aa67e2b4", "설현");
        Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData(AUTH, searchString, SORT , FIRST_PAGE, PAGE_SIZE);
        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ImageSearchResponseData>() {
            @Override
            public void onResponse(Call<ImageSearchResponseData> call, Response<ImageSearchResponseData> response) {
                if (response.body() !=  null) {
                    callback.onResult(response.body().getDocuments(), null, FIRST_PAGE + 1);;
                }
            }

            @Override
            public void onFailure(Call<ImageSearchResponseData> call, Throwable t) {

            }
        });
    }

    // 이전 페이지 로드
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ImageSearchResponseData.Document> callback) {
        KakaoImageSearchService kakaoImageSearchService = RetrofitInstance.getRetrofitInstance().create(KakaoImageSearchService.class);
        String searchString = RetrofitInstance.getSearchString();
        //Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData("KakaoAK f3a3676ce605a55fa482f111aa67e2b4", "설현");
        Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData(AUTH, searchString, SORT , params.key, PAGE_SIZE);
        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ImageSearchResponseData>() {
            @Override
            public void onResponse(Call<ImageSearchResponseData> call, Response<ImageSearchResponseData> response) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.body() != null) {
                    callback.onResult(response.body().getDocuments(), adjacentKey);
                }
            }

            @Override
            public void onFailure(Call<ImageSearchResponseData> call, Throwable t) {

            }
        });
    }

    //다음 페이지 로드.
    String prevString;
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ImageSearchResponseData.Document> callback) {
        KakaoImageSearchService kakaoImageSearchService = RetrofitInstance.getRetrofitInstance().create(KakaoImageSearchService.class);
        String searchString = RetrofitInstance.getSearchString();
        //Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData("KakaoAK f3a3676ce605a55fa482f111aa67e2b4", "설현");
        Call<ImageSearchResponseData> call = kakaoImageSearchService.getImageData(AUTH, searchString, SORT , params.key, PAGE_SIZE);
        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ImageSearchResponseData>() {
            @Override
            public void onResponse(Call<ImageSearchResponseData> call, Response<ImageSearchResponseData> response) {
                if (response.body() != null && !response.body().getMeta().is_end()) {
                    prevString = RetrofitInstance.getSearchString();
                    callback.onResult(response.body().getDocuments(), params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<ImageSearchResponseData> call, Throwable t) {

            }
        });
    }
}
