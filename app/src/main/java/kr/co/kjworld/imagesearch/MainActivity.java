package kr.co.kjworld.imagesearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.kjworld.imagesearch.adapter.ImageItemAdapter;
import kr.co.kjworld.imagesearch.datasource.viewmodel.ImageViewModel;
import kr.co.kjworld.imagesearch.model.network.RetrofitInstance;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;
import kr.co.kjworld.imagesearch.mycontract.GetIntractorImpl;
import kr.co.kjworld.imagesearch.mycontract.ImageItemClickListener;
import kr.co.kjworld.imagesearch.mycontract.MainPresenterImpl;
import kr.co.kjworld.imagesearch.mycontract.MainView;
import kr.co.kjworld.imagesearch.mycontract.Presenter;


import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MainView {
    ProgressBar mProgressBar;
    RecyclerView mRecyclerView;

    Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRecyclerView();
        initProgressBar();

        mPresenter = new MainPresenterImpl(this, new GetIntractorImpl());
        //mPresenter.requestDataFromServer();
    }

    private void initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.image_search_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    private void initProgressBar() {
        mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        mProgressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(mProgressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mProgressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }

    private ImageItemClickListener imageItemClickListener = new ImageItemClickListener() {
        @Override
        public void onItemClick(ImageSearchResponseData.Document document) {

        }
    };

    // 입력 완료 시간 체크.
    private Timer timer = new Timer();
    private String prevString;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_image_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_word));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                timer.cancel();
                timer = new Timer();
                // 입력 후 1초 후 string 변화가 있으면 presenter에 알림.
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (!newText.isEmpty() && !newText.equals(prevString)) {
                            RetrofitInstance.setSearchString(newText);
                            mPresenter.requestDataFromServer();
                            prevString = newText;
                        }
                    }
                };
                timer.schedule(timerTask, 1000);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }


    /**
     * [nobrain3]
     * RecyclerView에서 adapter를 셋팅함.
     */
    ImageViewModel imageViewModel;
    @Override
    public void setDataToRecyclerView(ArrayList<ImageSearchResponseData.Document> noticeArrayList) {
        if (imageViewModel == null)
            imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        else
            imageViewModel.init();

        final ImageItemAdapter adapter = new ImageItemAdapter(this);

        //isChangeString = true;
        imageViewModel.imagePagedList.observe(this, new Observer<PagedList<ImageSearchResponseData.Document>>() {
            @Override
            public void onChanged(PagedList<ImageSearchResponseData.Document> documents) {
                adapter.submitList(documents);
            }
        });


        //ImageSearchAdapter adapter = new ImageSearchAdapter(this, noticeArrayList, imageItemClickListener);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "접속 에러 : " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}
