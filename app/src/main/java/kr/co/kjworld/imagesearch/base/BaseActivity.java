package kr.co.kjworld.imagesearch.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity extends AppCompatActivity {
    ViewDataBinding mViewDataBinding;

    Integer layoutResourceId;

    BaseViewModel mViewModel;

    abstract void initStartView();
    abstract void initDataBinding();
    abstract void initAfterBinding();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewDataBinding= DataBindingUtil.setContentView(this, layoutResourceId);

        initStartView();
        initDataBinding();
        initAfterBinding();
    }
}
