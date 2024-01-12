package com.mkandeel.gallery.ViewModel;

import static com.mkandeel.gallery.Helper.Constants.BASE_URL;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mkandeel.gallery.Retrofit.APIService;
import com.mkandeel.gallery.Retrofit.RetrofitClient;
import com.mkandeel.gallery.data.user.UserModel;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UsersViewModel extends AndroidViewModel {
    private static UsersViewModel usersViewModel;
    private APIService apiService;
    private MutableLiveData<UserModel> usersData = new MutableLiveData<>();

    public UsersViewModel(Application application) {
        super(application);
        this.apiService = RetrofitClient.getClient(BASE_URL)
                .create(APIService.class);
    }

    public void getUserById(int user_id) {
        Observable<UserModel> observable = apiService.getUsers(user_id)
                .subscribeOn(Schedulers.io());
        Observer<UserModel> observer = new Observer<UserModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull UserModel userModels) {
                usersData.postValue(userModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("USER DATA", "onError: ", e);
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public LiveData<UserModel> getUsersData() {
        return usersData;
    }
}
