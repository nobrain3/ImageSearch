package kr.co.kjworld.imagesearch.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

public class ImageDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, ImageSearchResponseData.Document>> mImageLiveDataSource = new MutableLiveData<>();

    private String mSearchString;
    @NonNull
    @Override
    public DataSource<Integer, ImageSearchResponseData.Document> create() {
        ImageDataSource imageDataSource = new ImageDataSource("설현");
        mImageLiveDataSource.postValue(imageDataSource);
        return imageDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, ImageSearchResponseData.Document>> getItemLiveDataSource() {
        return mImageLiveDataSource;
    }

    public void setSearchString(String mSearchString) {
        this.mSearchString = mSearchString;
    }
}
