package kr.co.kjworld.imagesearch.datasource.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import kr.co.kjworld.imagesearch.datasource.ImageDataSource;
import kr.co.kjworld.imagesearch.datasource.ImageDataSourceFactory;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

public class ImageViewModel extends ViewModel {
    public LiveData<PagedList<ImageSearchResponseData.Document>> imagePagedList;
    LiveData<PageKeyedDataSource<Integer, ImageSearchResponseData.Document>> liveDataSource;

    public ImageViewModel() {
        ImageDataSourceFactory imageDataSourceFactory = new ImageDataSourceFactory();
        liveDataSource = imageDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ImageDataSource.PAGE_SIZE).build();

        imagePagedList = (new LivePagedListBuilder(imageDataSourceFactory, pagedListConfig))
                .build();
    }
}
