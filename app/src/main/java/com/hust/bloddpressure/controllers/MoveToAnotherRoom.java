package com.hust.bloddpressure.controllers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.model.entities.Room;
import com.hust.bloddpressure.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class MoveToAnotherRoom extends AppCompatActivity {
    AppCompatSpinner spinnerRoom;
    SpinnerRoomAdapter spinnerRoomAdapter;
    TextView selectRoomId;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_to_another_room);
        // Get Id từ bundle để chuyển phòng
        // Tìm id cho các view
        findViewByIdForView();
        // Set data cho dropdown và set text khi chọn item
        setDataForDropdown();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
//                int userId = intent.getExtras().getInt("userId");
                selectRoomId.setText("1");
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void findViewByIdForView() {
        selectRoomId = findViewById(R.id.room_id_edit);
        spinnerRoom = findViewById(R.id.spinner_room);
        btnSave = findViewById(R.id.btn_save);
    }

    private void setDataForDropdown() {
        final List<Room> listRoom = new ArrayList<>();
        final Room room = new Room();
        room.setRoomId(-1);
        room.setRoomName("Chọn phòng");
        listRoom.add(room);
        // THỰC HIỆN TRUY VẤN DB ĐỂ LẤY RA PHÒNG
        for (int i = 1; i < 5; i++) {
            Room room1 = new Room();
            room1.setRoomId(i);
            room1.setRoomName("Phong " + i);
            listRoom.add(room1);
        }
        // Tạo mơi adapter và set giá trị cho spiner
        spinnerRoomAdapter = new SpinnerRoomAdapter(listRoom);
        spinnerRoom.setAdapter(spinnerRoomAdapter);
        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Lấy ra phòng đã chọn
                Room roomItem = (Room) spinnerRoomAdapter.getItem(i);
                int roomId = roomItem.getRoomId();
                String roomName = roomItem.getRoomName();
                // Lấy id phòng và kiểm tra hợp lệ
                if (roomId > 0) {
                    selectRoomId.setHint(roomId + "");
                    selectRoomId.setText(roomName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectRoomId.setText("");
            }
        });
    }
}