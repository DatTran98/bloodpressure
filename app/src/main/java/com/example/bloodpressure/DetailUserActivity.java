package com.example.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DetailUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        findViewById(R.id.btn_history_pressure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // truyền data
                TabHistoryPressureFragment tabHistoryPressureFragment = new TabHistoryPressureFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_detail, tabHistoryPressureFragment);
                fragmentTransaction.commit();
            }
        });
        findViewById(R.id.btn_basic_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // truyền dữ liệu
                TabBasicDetailFragment tabBasicDetailFragment = new TabBasicDetailFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_detail, tabBasicDetailFragment);
                fragmentTransaction.commit();
            }
        });
        findViewById(R.id.btn_edit_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailUserActivity.this, "Edituser", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_delete_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailUserActivity.this, "Deleteuser", Toast.LENGTH_SHORT).show();
            }
        });
        // truyền data khi vào màn hình: rule user đang đăng nhập, thông tin bệnh nhân
        // tạo mới tab thông tin bệnh nhân
        // Thực hiện check quyền user và manager
        // tạo mới tab button riêng cho quyền
        TabBasicDetailFragment tabBasicDetailFragment = new TabBasicDetailFragment();

//        Intent intent = this.getIntent();
//        Bundle bundle = intent.getExtras();
        Bundle bundle = new Bundle();
        bundle.putString("fullname", "fullname");
        tabBasicDetailFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_detail, tabBasicDetailFragment);
        fragmentTransaction.commit();
    }
}