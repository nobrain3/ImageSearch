package kr.co.kjworld.imagesearch.mycontract;

import java.util.ArrayList;

import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;


public class MainPresenterImpl implements Presenter, GetImageDataInteractor.OnFinishedListener {
    MainView mMainView;
    GetImageDataInteractor mImageDataInteractor;

    public MainPresenterImpl (MainView aMainView, GetImageDataInteractor aImageDataInteractor)
    {
        mMainView = aMainView;
        mImageDataInteractor = aImageDataInteractor;
    }


    @Override
    public void onFinished(ImageSearchResponseData.Meta meta, ArrayList<ImageSearchResponseData.Document> documentArrayList) {
        if (mMainView != null) {
            mMainView.setDataToRecyclerView(documentArrayList);
            mMainView.hideProgress();
        }


    }

    @Override
    public void onFailure(Throwable t) {
        if(mMainView != null){
            mMainView.onResponseFailure(t);
            mMainView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        mMainView = null;
    }


    @Override
    public void requestDataFromServer() {
        mImageDataInteractor.getImageSearchData(this);

        if (mMainView != null)
            mMainView.showProgress();
    }
}
