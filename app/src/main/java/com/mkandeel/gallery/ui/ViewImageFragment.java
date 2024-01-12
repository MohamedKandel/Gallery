package com.mkandeel.gallery.ui;

import static com.mkandeel.gallery.Helper.Constants.IMAGE_DATA;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mkandeel.gallery.Helper.Helper;
import com.mkandeel.gallery.R;
import com.mkandeel.gallery.data.Photos;
import com.mkandeel.gallery.databinding.FragmentViewImageBinding;

public class ViewImageFragment extends Fragment {

    public ViewImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentViewImageBinding binding;
    private Helper helper;
    boolean isVisible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentViewImageBinding.inflate(inflater, container, false);
        helper = Helper.getInstance(this);

        if (getArguments() != null) {
            Photos photo = getArguments().getParcelable(IMAGE_DATA);

            Glide.with(requireContext())
                    .load(photo.getUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.img);

            binding.layoutDetails.setVisibility(View.GONE);
            isVisible = false;

            binding.img.setOnClickListener(view -> {
                if (isVisible) {
                    binding.layoutDetails.setVisibility(View.GONE);
                    isVisible = false;
                } else {
                    binding.layoutDetails.setVisibility(View.VISIBLE);
                    binding.txtName.setText(photo.getTitle());
                    isVisible = true;
                }
            });

            binding.btnBack.setOnClickListener(view -> {
                helper.navigate(R.id.photosFragment, getArguments());
            });

            helper.onBackPressed(R.id.photosFragment, getArguments());
        }

        return binding.getRoot();
    }
}