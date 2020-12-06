package com.hust.bloddpressure.controllers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.model.entities.BloodPressureInfor;
import com.hust.bloddpressure.util.Common;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabHistoryPressureFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabHistoryPressureFragment extends Fragment {
    TextView textViewId;
    ListView listViewPressure;
    ArrayList<BloodPressureInfor> listPressure;
    ListViewPressureAdapter listViewPressureAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_histor_pressure, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            int userId = getArguments().getInt("userId");
            // TRUY VẤN DB LẤY DỮ LIỆU THEO ID
            BloodPressureInfor bloodPressureInfor = new BloodPressureInfor();
            bloodPressureInfor.setBloodPressureId(2);
            bloodPressureInfor.setPressureMax(120);
            bloodPressureInfor.setPressureMin(80);
            bloodPressureInfor.setTime(new Date(11 / 30 / 2020));
            listPressure = new ArrayList<>();
            listPressure.add(bloodPressureInfor);
            listViewPressureAdapter = new ListViewPressureAdapter(listPressure);
            listViewPressure = view.findViewById(R.id.list_result_pressure);
            listViewPressure.setAdapter(listViewPressureAdapter);
            listViewPressureAdapter.notifyDataSetChanged();
        } else {
            String message = "Không có dữ liệu";
            Common.showToast(getActivity(), message);
        }
    }
}