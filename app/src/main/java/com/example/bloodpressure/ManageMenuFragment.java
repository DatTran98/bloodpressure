package com.example.bloodpressure;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.renderscript.Allocation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageMenuFragment extends Fragment {
    TextView textViewLabelTile, textViewLabelListOrDetail;
    ImageButton btnMoveListRoom, btnMoveListStatic;

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
        btnMoveListRoom = (ImageButton) view.findViewById(R.id.btn_move_list_room);
        int ruleId = 1;
        if (ruleId == 1) {
            textViewLabelTile.setText("Chào mừng đến với BloodPressure");
            btnMoveListRoom.setEnabled(false);
            textViewLabelListOrDetail.setText("Thông tin người dùng");
        } else {
            textViewLabelTile.setText("Chào mừng đến với trình quản lý BloodPressure");
        }
        view.findViewById(R.id.btn_move_list_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListPersonActivity.class);
                startActivity(intent);
            }
        });
    }
}