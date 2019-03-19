package kr.co.kjworld.imagesearch.presenter;

import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

public interface ImageItemClickListener {
    void onItemClick(ImageSearchResponseData.Document document);
}
