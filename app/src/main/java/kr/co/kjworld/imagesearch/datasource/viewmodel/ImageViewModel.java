package kr.co.kjworld.imagesearch.datasource.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import kr.co.kjworld.imagesearch.datasource.ImageDataSource;
import kr.co.kjworld.imagesearch.datasource.ImageDataSourceFactory;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;

/**
 * [nobrain3]
 * Factory에서 생성한 dataSource를 정의하고 빌드함.
 * LiveData에서 page의 속성과 DataSource가 셋팅된 Pagedlist를 실시간 모니터링.
 */
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

    public void init()
    {
        ImageDataSourceFactory imageDataSourceFactory = new ImageDataSourceFactory();
        liveDataSource = imageDataSourceFactory.getItemLiveDataSource();


        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ImageDataSource.PAGE_SIZE).build();

        imagePagedList = (new LivePagedListBuilder(imageDataSourceFactory, pagedListConfig))
                .build();
    }
}
