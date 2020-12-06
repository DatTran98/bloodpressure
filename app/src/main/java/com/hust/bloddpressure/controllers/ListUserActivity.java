package com.hust.bloddpressure.controllers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.model.entities.UserInfor;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity {
    ArrayList<UserInfor> listUsers;
    ListViewUserAdapter listViewUserAdapter;
    ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        // Thưc hiện lấy thông tin tất cả user từ db
        listUsers = new ArrayList<>();
        UserInfor userInfor = new UserInfor();
        userInfor.setUserId(1);
        userInfor.setUsername("Tran Ba Dat");
        userInfor.setAge(23);
        UserInfor userInfor1 = new UserInfor();
        userInfor1.setUserId(2);
        userInfor1.setUsername("Tran Ba Dat");
        userInfor1.setAge(23);
        listUsers.add(userInfor);
        listUsers.add(userInfor1);
        // Tạo apdapter để gửi dữ liệu lên view
        listViewUserAdapter = new ListViewUserAdapter(listUsers);
        listViewUsers = findViewById(R.id.list_users);
        listViewUsers.setAdapter(listViewUserAdapter);
        listViewUserAdapter.notifyDataSetChanged();

        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy thông tin user vưa được chọn
                UserInfor userInfor = (UserInfor) listViewUserAdapter.getItem(position);
                // Lấy ra id để gửi cho màn hình detail get thông tin user từ db theo id
                int userId = userInfor.getUserId();
                // Tạo activity hiển thị chi tiết
                Intent intent = new Intent(ListUserActivity.this, DetailUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("userId", userId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //Tạo mới interface dialog
                AlertDialog.Builder confirm = new AlertDialog.Builder(adapterView.getContext());
                // thiết lập thông tin
                setUpDialog(confirm, position);
                //Tạo dialog
                AlertDialog dialogConfirm = confirm.create();
                dialogConfirm.show();
                return true;
            }
        });
    }

    // thiết lập các thông tin cho dialog
    private void setUpDialog(AlertDialog.Builder confirm, final int position) {
        confirm.setTitle("Xác nhận");
        confirm.setMessage("Bạn có chắc chắn xóa người dùng này không?");
        confirm.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = listUsers.get(position).getFullname();
                listUsers.remove(position);
                // Phải thực hiện xóa trong db
                listViewUserAdapter.notifyDataSetChanged();
                Toast.makeText(ListUserActivity.this, "Đã xóa thông tin bệnh nhân " + name, Toast.LENGTH_SHORT).show();
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