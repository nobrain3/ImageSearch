package kr.co.kjworld.imagesearch.mycontract;

import java.util.ArrayList;

import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;


public interface GetImageDataInteractor {
    interface OnFinishedListener {
        void onFinished(ImageSearchResponseData.Meta meta, ArrayList<ImageSearchResponseData.Document> documentArrayList);
        void onFailure(Throwable t);
    }

    void getImageSearchData(OnFinishedListener onFinishedListener);
}
