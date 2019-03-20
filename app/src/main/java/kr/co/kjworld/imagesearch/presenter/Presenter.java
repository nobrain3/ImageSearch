package kr.co.kjworld.imagesearch.presenter;

public interface Presenter {
    void onDestroy();

    void requestDataFromServer(String searchString, String sort, int page, int sizeInPage);
}
