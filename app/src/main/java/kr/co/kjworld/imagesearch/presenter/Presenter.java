package kr.co.kjworld.imagesearch.presenter;

public interface Presenter {
    void onDestroy();

    void onRefreshButtonClick();

    void requestDataFromServer(String searchString, String sort, int page, int sizeInPage);
}
