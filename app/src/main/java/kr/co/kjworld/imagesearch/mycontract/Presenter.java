package kr.co.kjworld.imagesearch.mycontract;

/**
 *  [nobrian3]
 *  Presenter
 *  View에서 이벤트를 전닯다아서 Model에 데이터를 요청하고
 *  Model에서 데이터를 받아 view의 RecyclerView에 뿌려줌
 */
public interface Presenter {
    void onDestroy();

    void requestDataFromServer();
}
