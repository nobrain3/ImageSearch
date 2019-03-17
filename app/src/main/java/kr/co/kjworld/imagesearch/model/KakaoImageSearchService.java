package kr.co.kjworld.imagesearch.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KakaoImageSearchService {
    @GET("/v2/search/image")
    Call<Document> repo(@Query("query") String query);
    //Call<List<Repo>> listRepos(@Path("user") String user);
}
