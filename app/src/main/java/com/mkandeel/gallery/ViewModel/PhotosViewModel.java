package com.mkandeel.gallery.ViewModel;

import static com.mkandeel.gallery.Helper.Constants.BASE_URL;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mkandeel.gallery.Retrofit.APIService;
import com.mkandeel.gallery.Retrofit.RetrofitClient;
import com.mkandeel.gallery.data.Photos;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PhotosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Photos>> allAlbumPhotos = new MutableLiveData<>();
    private APIService apiService;
    public PhotosViewModel(@NonNull Application application) {
        super(application);
        this.apiService = RetrofitClient.getClient(BASE_URL)
                .create(APIService.class);
    }

    public void getAlbumPhotos(int album_id) {
        Observable<List<Photos>> observable = apiService.getAllPhotos(album_id)
                .subscribeOn(Schedulers.io());

        Observer<List<Photos>> observer = new Observer<List<Photos>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Photos> photos) {
                allAlbumPhotos.postValue(photos);
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

    public LiveData<List<Photos>> getAllAlbumPhotos() {
        return allAlbumPhotos;
    }
}
