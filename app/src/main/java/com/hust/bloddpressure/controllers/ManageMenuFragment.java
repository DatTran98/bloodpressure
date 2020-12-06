package com.hust.bloddpressure.controllers;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageMenuFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageMenuFragment extends Fragment {
    TextView textViewLabelTile, textViewLabelListOrDetail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewLabelTile = (TextView) view.findViewById(R.id.label_title);
        textViewLabelListOrDetail = (TextView) view.findViewById(R.id.label_move_list_or_detail_user);

        int ruleId = 0;
        if (ruleId == Constant.USER_RULE) {
            textViewLabelTile.setText("Welcome to BloodPressure User");

            textViewLabelListOrDetail.setText("Thông tin người dùng");
        } else {
            textViewLabelTile.setText("Welcome to BloodPressure Manager");
        }
        view.findViewById(R.id.btn_move_list_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListUserActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.btn_move_list_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListNewsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.btn_move_list_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListRoomActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.btn_move_list_static).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // GỬI User Id để lấy dữ liệu
                Intent intent = new Intent(getActivity(), AnalysisActivity.class);
                startActivity(intent);
            }
        });
    }
}