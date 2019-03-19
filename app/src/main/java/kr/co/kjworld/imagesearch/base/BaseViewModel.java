package kr.co.kjworld.imagesearch.base;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public boolean addDisposable(Disposable disposable) {
        return mCompositeDisposable.add(disposable);
    }

     protected void onCleared() {
        mCompositeDisposable.clear();
        super.onCleared();
    }


}
