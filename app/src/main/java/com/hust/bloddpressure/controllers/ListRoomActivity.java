package com.hust.bloddpressure.controllers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.model.entities.Room;

import java.util.ArrayList;

public class ListRoomActivity extends AppCompatActivity {
    ArrayList<Room> listRoom;
    ListViewRoomAdapter listViewRoomAdapter;
    ListView listViewRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        // Lay danh sach cac phong
        // add cac phong vao list
        listRoom = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Room room1 = new Room();
            room1.setRoomId(i);
            room1.setRoomName("Phong " + i);
            listRoom.add(room1);
        }
        listViewRoomAdapter = new ListViewRoomAdapter(listRoom);
        listViewRoom = findViewById(R.id.list_rooms);
        listViewRoom.setAdapter(listViewRoomAdapter);
        listViewRoomAdapter.notifyDataSetChanged();
        listViewRoom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Tạo mới interface dialog
                AlertDialog.Builder confirm = new AlertDialog.Builder(adapterView.getContext());
                // thiết lập thông tin
                setUpDialog(confirm, i);
                //Tạo dialog
                AlertDialog dialogConfirm = confirm.create();
                dialogConfirm.show();
//                btnDelete = findViewById(R.id.btn_delete);
//                btnDelete.setVisibility(View.VISIBLE);
                return true;
            }
        });
        findViewById(R.id.btn_add_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRoomActivity.this, AddRoomActivity.class);
                startActivity(intent);
            }
        });

    }

    // thiết lập các thông tin cho dialog
    private void setUpDialog(AlertDialog.Builder confirm, final int position) {
        confirm.setTitle("Xác nhận");
        confirm.setMessage("Bạn có chắc chắn xóa phòng này không?");
        confirm.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String name = listRoom.get(position).getRoomName();
                listRoom.remove(position);

                // Phải thực hiện xóa trong db
                listViewRoomAdapter.notifyDataSetChanged();
                Toast.makeText(ListRoomActivity.this, "Đã xóa thông tin phòng " + name, Toast.LENGTH_SHORT).show();
            }
        });
        confirm.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
    }
}