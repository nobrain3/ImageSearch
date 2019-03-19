package kr.co.kjworld.imagesearch.presenter;

import java.util.ArrayList;

import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

public interface MainView {
    void showProgress();

    void hideProgress();

    void setDataToRecyclerView(ArrayList<ImageSearchResponseData.Document> documentArrayList);

    void onResponseFailure(Throwable throwable);
}
