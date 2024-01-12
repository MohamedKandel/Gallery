package com.mkandeel.gallery.ui;

import static com.mkandeel.gallery.Helper.Constants.ALBUM_DATA;
import static com.mkandeel.gallery.Helper.Constants.USER_DATA;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mkandeel.gallery.Adapter.AlbumsAdapter;
import com.mkandeel.gallery.Helper.ClickListener;
import com.mkandeel.gallery.Helper.Helper;
import com.mkandeel.gallery.R;
import com.mkandeel.gallery.ViewModel.AlbumsViewModel;
import com.mkandeel.gallery.ViewModel.UsersViewModel;
import com.mkandeel.gallery.data.Albums;
import com.mkandeel.gallery.data.user.UserModel;
import com.mkandeel.gallery.databinding.FragmentProfileBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProfileFragment extends Fragment implements ClickListener {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentProfileBinding binding;
    private UsersViewModel usersViewModel;
    private AlbumsViewModel albumsViewModel;
    private List<Albums> list;
    private AlbumsAdapter albumsAdapter;
    private UserModel userModel;
    private Albums albums;
    private Helper helper;
    private int user_id;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        helper = Helper.getInstance(this);

        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        albumsViewModel = new ViewModelProvider(this).get(AlbumsViewModel.class);

        list = new ArrayList<>();
        albumsAdapter = new AlbumsAdapter(list, this);

        binding.recyclerViewAlbums.setAdapter(albumsAdapter);

        if (getArguments() == null) {
            getUserRandomly();
            getAlbumsForUser(user_id);
        } else {
            userModel = getArguments().getParcelable(USER_DATA);
            binding.txtName.setText(userModel.getName());
            binding.txtAddress.setText(userModel.getAddress().getSuite() + ", " +
                    userModel.getAddress().getStreet() + " - " +
                    userModel.getAddress().getCity());

            albums = getArguments().getParcelable(ALBUM_DATA);
            getAlbumsForUser(albums.getUserId());
        }

        onBackPressed();

        return binding.getRoot();
    }

    private void getAlbumsForUser(int userId) {
        albumsViewModel.getAllUserAlbums(userId);
        albumsViewModel.getAllAlbums().observe(requireActivity(),
                new Observer<List<Albums>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onChanged(List<Albums> albums) {
                        list.addAll(albums);
                        albumsAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void getUserRandomly() {
        user_id = 1 + (int) (Math.random() * ((10 - 1) + 1));
        usersViewModel.getUserById(user_id);
        usersViewModel.getUsersData().observe(requireActivity(),
                new Observer<UserModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onChanged(UserModel userModels) {
                        binding.txtName.setText(userModels.getName());
                        binding.txtAddress.setText(userModels.getAddress().getSuite() + ", " +
                                userModels.getAddress().getStreet() + " - " +
                                userModels.getAddress().getCity());
                        userModel = userModels;
                    }
                });
    }


    @Override
    public void onItemClickListener(int position, @Nullable Bundle extras) {
        albums = extras.getParcelable(ALBUM_DATA);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ALBUM_DATA, albums);
        bundle.putParcelable(USER_DATA, userModel);

        helper.navigate(R.id.photosFragment, bundle);
    }

    @Override
    public void onItemLongClickListener(int position, @Nullable Bundle extras) {

    }

    private void onBackPressed() {
        requireActivity().getOnBackPressedDispatcher()
                .addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        getActivity().finish();
                    }
                });
    }
}