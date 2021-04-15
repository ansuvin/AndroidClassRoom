package com.example.pra03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPractice extends Fragment {

    public static FragmentPractice newInstance(int number) {
        FragmentPractice fp = new FragmentPractice();
        Bundle bundle = new Bundle();
        bundle.putInt("number", number);
        fp.setArguments(bundle);
        return fp;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            int num = getArguments().getInt("number");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return LayoutInflater.from(inflater.getContext()).inflate(R.layout.frg,container,false);
    }

}
