package com.hust.bloddpressure.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.util.Common;
import com.hust.bloddpressure.util.Constant;

public class DetailUserActivity extends AppCompatActivity {
    TextView textViewIdUser, textViewUsername, textViewFullName, textViewAge, textViewDisease, textViewTel, textViewRoom, textViewMessage;
    private int tabMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        getIdElementView();
        // Phải lấy ra id được truyền từ màn hình list từ intent
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            // PHẢI HIỆN THỊ ĐÚNG THÔNG BÁO
            textViewMessage.setText("Không có dữ liệu");
        } else {
            // Lấy ra giá trị userId được truyền từ list user
            int userId = bundle.getInt("userId");
            // Set id lên view chính để 2 tab basic và history sử dụng
            textViewIdUser.setText(userId + "");
            textViewMessage.setText("Thông tin bệnh nhân");
            TabBasicDetailUserFragment tabBasicDetailUserFragment = new TabBasicDetailUserFragment();
            // Gửi bundle chứ id này qua fragment( không cần thiết vi đã set view)
            tabBasicDetailUserFragment.setArguments(bundle);
            // Set fragment cho view, frame cần hiển thị fragment
            setFragmentByManagerFragment(R.id.content_detail, tabBasicDetailUserFragment);
            // Set mode tab
            tabMode = Constant.MODE_BASIC;
        }

        findViewById(R.id.btn_history_pressure).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // get id user truyền data sang tab history để get thông tin hiển thị
                Bundle bundle = getIdUserMain();
                TabHistoryPressureFragment tabHistoryPressureFragment = new TabHistoryPressureFragment();
                tabHistoryPressureFragment.setArguments(bundle);
                setFragmentByManagerFragment(R.id.content_detail, tabHistoryPressureFragment);
                tabMode = Constant.MODE_HISTORY;

            }
        });
        findViewById(R.id.btn_basic_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // truyền dữ liệu là id user
                Bundle bundle = getIdUserMain();
                TabBasicDetailUserFragment tabBasicDetailFragment = new TabBasicDetailUserFragment();
                tabBasicDetailFragment.setArguments(bundle);
                setFragmentByManagerFragment(R.id.content_detail, tabBasicDetailFragment);
                tabMode = Constant.MODE_BASIC;
            }
        });
        findViewById(R.id.btn_edit_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabMode == Constant.MODE_BASIC) {
                    // Lấy id để truyền qua activity, thực hiện get thông tin user tại acitvity
                    Intent intent = new Intent(DetailUserActivity.this, EditUserActivity.class);
                    // Tìm id của các view trong viewgroup
                    getIdElementView();
                    Bundle bundle = new Bundle();
                // Thực hiện get giá trị và truyền vào bundle
                    setValuesForBundle(bundle);
                // Put bundle vào intent để truyền sang activity khác
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailUserActivity.this, "Chuyển qua tab thông tin để chỉnh sửa", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_move_to_another_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // truyền dữ liệu là id user
                Intent intent = new Intent(DetailUserActivity.this, MoveToAnotherRoom.class);
                int userId = Common.convertToInt(textViewIdUser.getText().toString(), 0);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        // truyền data khi vào màn hình: rule user đang đăng nhập, thông tin bệnh nhân
        // tạo mới tab thông tin bệnh nhân
        // Thực hiện check quyền user và manager
        // tạo mới tab button riêng cho quyền

    }

    private Bundle getIdUserMain() {
        Bundle bundle = new Bundle();
        textViewIdUser = findViewById(R.id.user_id_main);
        bundle.putInt("userId", Common.convertToInt(textViewIdUser.getText().toString(), 0));
        return bundle;
    }

    /**
     * @param idFrameContent  id của frame cần set
     * @param fragmentRequire fragment cần set vào layout
     */
    private void setFragmentByManagerFragment(int idFrameContent, Fragment fragmentRequire) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(idFrameContent, fragmentRequire);
        fragmentTransaction.commit();
    }

    /**
     * Tìm id view trong layout viewgroup
     */
    private void getIdElementView() {

        textViewMessage = findViewById(R.id.message);
        textViewIdUser = findViewById(R.id.user_id_main);
        textViewUsername = findViewById(R.id.username);
        textViewFullName = findViewById(R.id.full_name);
        textViewAge = findViewById(R.id.age);
        textViewDisease = findViewById(R.id.disease);
        textViewTel = findViewById(R.id.tel);
        textViewRoom = findViewById(R.id.room);
    }

    /**
     * Set cac gia tri lấy từ view vào bundle
     *
     * @param bundle
     */
    private void setValuesForBundle(Bundle bundle) {
        String userId = textViewIdUser.getText().toString();
        String username = textViewUsername.getText().toString();
        String fullName = textViewFullName.getText().toString();
        String age = textViewAge.getText().toString();
        String disease = textViewDisease.getText().toString();
        String tel = textViewTel.getText().toString();
        String room = textViewRoom.getText().toString();

        bundle.putString("userId", userId);
        bundle.putString("username", username);
        bundle.putString("fullName", fullName);
        bundle.putString("age", age);
        bundle.putString("tel", tel);
        bundle.putString("room", room);
        bundle.putString("disease", disease);
    }
}