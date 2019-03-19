package kr.co.kjworld.imagesearch.model;

import java.util.List;

import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface KakaoImageSearchService {
    @GET("/v2/search/image")
    Call<ImageSearchResponseData> getImageData(@Header("Authorization") String auth, @Query("query") String query);
    //Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("/v2/search/image")
    Call<ImageSearchResponseData> getImageData(@Header("Authorization") String auth, @Query("query") String query, @Query("page") int page, @Query("size") int size);

    @GET("/v2/search/image")
    Call<ImageSearchResponseData> getImageData(@Header("Authorization") String auth, @Query("query") String query, @Query("sort") String sort, @Query("page") int page, @Query("size") int size);
}
