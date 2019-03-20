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
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (!newText.isEmpty() && !newText.equals(prevString)) {
                            mPresenter.requestDataFromServer(newText);
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
