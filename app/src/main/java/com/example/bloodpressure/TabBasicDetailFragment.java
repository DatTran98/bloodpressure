package com.example.bloodpressure;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabBasicDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabBasicDetailFragment extends Fragment {
    TextView textViewFullName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_basic_detail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        String fullName = this.getArguments().getString("fullname");
        textViewFullName = (TextView) view.findViewById(R.id.full_name);
        textViewFullName.setText("fullName");
    }
}