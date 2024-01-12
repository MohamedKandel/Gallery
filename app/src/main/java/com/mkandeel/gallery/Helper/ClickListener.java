package com.mkandeel.gallery.Helper;

import android.os.Bundle;

import androidx.annotation.Nullable;

public interface ClickListener {
    void onItemClickListener(int position, @Nullable Bundle extras);
    void onItemLongClickListener(int position, @Nullable Bundle extras);
}
