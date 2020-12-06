package com.hust.bloddpressure.controllers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hust.bloddpressure.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabBasicDetailUserFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabBasicDetailUserFragment extends Fragment {
    TextView textViewFullName, textViewUserId, textViewAge, textViewUserName, textViewTel, textViewDisease, textViewRoom;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_basic_detail_user, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        int userId = getArguments().getInt("userId");

        // Kiểm tra userId và lấy các thông tin từ DB và set cho các textView
        textViewUserId = (TextView) view.findViewById(R.id.user_id);
        textViewFullName = (TextView) view.findViewById(R.id.full_name);
        textViewAge = (TextView) view.findViewById(R.id.age);
        textViewUserName = (TextView) view.findViewById(R.id.username);
        textViewTel = (TextView) view.findViewById(R.id.tel);
        textViewDisease = (TextView) view.findViewById(R.id.disease);
        textViewRoom = (TextView) view.findViewById(R.id.room);
        // Set gia trị cho các text view
        textViewUserId.setText(userId + "");
        textViewFullName.setText("Trần Bá Đạt");
        textViewAge.setText("23");
        textViewUserName.setText("tranbadat");
        textViewTel.setText("0977553752");
        textViewDisease.setText("Bệnh tim,,mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        textViewRoom.setText("Phòng 2");
    }
}