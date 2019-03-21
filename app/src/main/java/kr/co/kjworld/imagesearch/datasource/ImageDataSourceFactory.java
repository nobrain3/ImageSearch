package kr.co.kjworld.imagesearch.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

/**
 * [nobrain3]
 * 생성한 DataSource를 생성하여 반환.
 */
public class ImageDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, ImageSearchResponseData.Document>> mImageLiveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, ImageSearchResponseData.Document> create() {
        ImageDataSource imageDataSource = new ImageDataSource();
        mImageLiveDataSource.postValue(imageDataSource);
        return imageDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, ImageSearchResponseData.Document>> getItemLiveDataSource() {
        return mImageLiveDataSource;
    }


}
