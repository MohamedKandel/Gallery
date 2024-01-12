package com.mkandeel.gallery.ViewModel;

import static com.mkandeel.gallery.Helper.Constants.BASE_URL;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mkandeel.gallery.Retrofit.APIService;
import com.mkandeel.gallery.Retrofit.RetrofitClient;
import com.mkandeel.gallery.data.Albums;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AlbumsViewModel extends AndroidViewModel {
    private MutableLiveData<List<Albums>> allAlbums = new MutableLiveData<>();
    private APIService apiService;

    public AlbumsViewModel(@NonNull Application application) {
        super(application);
        this.apiService = RetrofitClient.getClient(BASE_URL)
                .create(APIService.class);
    }

    public void getAllUserAlbums(int user_id) {
        Observable<List<Albums>> observable = apiService.getAlbums(user_id)
                .subscribeOn(Schedulers.io());
        Observer<List<Albums>> observer = new Observer<List<Albums>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Albums> albums) {
                allAlbums.postValue(albums);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public LiveData<List<Albums>> getAllAlbums() {
        return allAlbums;
    }
}
