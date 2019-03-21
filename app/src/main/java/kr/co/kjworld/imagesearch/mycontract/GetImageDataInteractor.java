package kr.co.kjworld.imagesearch.mycontract;

import java.util.ArrayList;

import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

/**
 * [nobrain3]
 * Model
 * Presenter에서 받은 요청데로 데이터를 수집 후 다시 onFinished를 통해 Presenter에 전달.
 */
public interface GetImageDataInteractor {
    interface OnFinishedListener {
        void onFinished(ImageSearchResponseData.Meta meta, ArrayList<ImageSearchResponseData.Document> documentArrayList);
        void onFailure(Throwable t);
    }

    void getImageSearchData(OnFinishedListener onFinishedListener);
}
