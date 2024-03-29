package com.mkandeel.gallery.ui;

import static com.mkandeel.gallery.Helper.Constants.*;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mkandeel.gallery.Adapter.PhotoAdapter;
import com.mkandeel.gallery.Helper.ClickListener;
import com.mkandeel.gallery.Helper.Helper;
import com.mkandeel.gallery.R;
import com.mkandeel.gallery.ViewModel.PhotosViewModel;
import com.mkandeel.gallery.data.Albums;
import com.mkandeel.gallery.data.Photos;
import com.mkandeel.gallery.data.user.UserModel;
import com.mkandeel.gallery.databinding.FragmentPhotosBinding;

import java.util.ArrayList;
import java.util.List;

public class PhotosFragment extends Fragment implements ClickListener {

    public PhotosFragment() {
        // Required empty public constructor
    }

    public static PhotosFragment newInstance(Bundle extras) {
        PhotosFragment fragment = new PhotosFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private FragmentPhotosBinding binding;
    private PhotoAdapter adapter;
    private List<Photos> list;
    private UserModel userModel;
    private Albums albums;
    private Helper helper;
    private PhotosViewModel photosViewModel;
    private boolean isVisible;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        helper = Helper.getInstance(this);
        isVisible = false;
        photosViewModel = new ViewModelProvider(this).get(PhotosViewModel.class);

        if (getArguments() != null) {
            userModel = getArguments().getParcelable(USER_DATA);
            albums = getArguments().getParcelable(ALBUM_DATA);

            binding.txtAlbumName.setText(albums.getTitle());

            helper.onBackPressed(R.id.profileFragment,getArguments());

            binding.btnBack.setOnClickListener(view -> {
                helper.navigate(R.id.profileFragment, getArguments());
            });

            list = new ArrayList<>();
            adapter = new PhotoAdapter(list, this, requireContext());
            binding.recyclerViewPhotos.setAdapter(adapter);

            binding.layoutDetails.setVisibility(View.GONE);
            binding.btnSearch.setImageResource(R.drawable.search);

            binding.btnSearch.setOnClickListener(view -> {
                if (isVisible) {
                    binding.layoutDetails.setVisibility(View.GONE);
                    isVisible = false;
                    binding.btnSearch.setImageResource(R.drawable.search);
                } else {
                    binding.layoutDetails.setVisibility(View.VISIBLE);
                    isVisible = true;
                    binding.btnSearch.setImageResource(R.drawable.close);
                }
            });

            binding.txtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString());
                }
            });

            getPhotos(albums.getId());
        }


        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String query) {
        List<Photos> mlist = new ArrayList<>();
        for (Photos photo : list) {
            if (photo.getTitle().toLowerCase().contains(query.toLowerCase())) {
                mlist.add(photo);
            }
        }
        adapter.filteredList(mlist);
    }

    private void getPhotos(int album_id) {
        photosViewModel.getAlbumPhotos(album_id);
        photosViewModel.getAllAlbumPhotos().observe(requireActivity(),
                new Observer<List<Photos>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onChanged(List<Photos> photos) {
                        list.addAll(photos);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onItemClickListener(int position, @Nullable Bundle extras) {
        extras.putParcelable(USER_DATA,userModel);
        extras.putParcelable(ALBUM_DATA,albums);
        helper.navigate(R.id.viewImageFragment,extras);
    }

    @Override
    public void onItemLongClickListener(int position, @Nullable Bundle extras) {

    }
}