package kr.co.kjworld.imagesearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.kjworld.imagesearch.adapter.ImageSearchAdapter;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;
import kr.co.kjworld.imagesearch.presenter.GetIntractorImpl;
import kr.co.kjworld.imagesearch.presenter.ImageItemClickListener;
import kr.co.kjworld.imagesearch.presenter.MainPresenterImpl;
import kr.co.kjworld.imagesearch.presenter.MainView;
import kr.co.kjworld.imagesearch.presenter.Presenter;


import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;

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
        mPresenter.requestDataFromServer();
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
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<ImageSearchResponseData.Document> noticeArrayList) {
        ImageSearchAdapter adapter = new ImageSearchAdapter(this, noticeArrayList, imageItemClickListener);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "접속 에러 : " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}
