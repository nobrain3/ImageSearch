package kr.co.kjworld.imagesearch.mycontract;

import java.util.ArrayList;

import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

/**
 * [nobrain3]
 * View
 * 실제 사용자의 이벤트를 받아 Presenter에 전달.
 */
public interface MainView {
    void showProgress();

    void hideProgress();

    void setDataToRecyclerView(ArrayList<ImageSearchResponseData.Document> documentArrayList);

    void onResponseFailure(Throwable throwable);
}
