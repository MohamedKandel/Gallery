package com.mkandeel.gallery.Retrofit;

import com.mkandeel.gallery.data.Albums;
import com.mkandeel.gallery.data.Photos;
import com.mkandeel.gallery.data.user.UserModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("users/{user_id}")
    Observable<UserModel> getUsers(@Path("user_id") int user_id);

    @GET("albums")
    Observable<List<Albums>> getAlbums(@Query("userId") int user_id);
    @GET("photos")
    Observable<List<Photos>> getAllPhotos(@Query("albumId") int album_id);
}
