package kr.co.kjworld.imagesearch.mycontract;

import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

public interface ImageItemClickListener {
    void onItemClick(ImageSearchResponseData.Document document);
}
