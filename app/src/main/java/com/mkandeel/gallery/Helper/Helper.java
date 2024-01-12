package com.mkandeel.gallery.Helper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mkandeel.gallery.R;

public class Helper {
    private static Helper helper;
    private Fragment fragment;
    private Activity activity;
    private Context context;

    private Helper(Fragment fragment) {
        this.fragment = fragment;
        this.activity = fragment.requireActivity();
        this.context = fragment.requireContext();
    }

    public static Helper getInstance(Fragment fragment) {
        if (helper == null) {
            helper = new Helper(fragment);
        }
        return helper;
    }

    public void navigate(int layoutId, @Nullable Bundle bundle) {
        NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
        if (navController != null) {
            if (bundle == null) {
                navController.navigate(layoutId);
            } else {
                navController.navigate(layoutId, bundle);
            }
        } else {
            Toast.makeText(context, "an error occurred please navigate again", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed(int layoutID, @Nullable Bundle extra) {
        fragment.requireActivity().getOnBackPressedDispatcher()
                .addCallback(fragment.requireActivity(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        navigate(layoutID, extra);
                    }
                });
    }

}
