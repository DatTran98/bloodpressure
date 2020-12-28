package com.hust.bloddpressure.controllers;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.model.MyService;
import com.hust.bloddpressure.model.entities.UserInfor;
import com.hust.bloddpressure.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity {
    ArrayList<UserInfor> listUsers;
    ListViewUserAdapter listViewUserAdapter;
    ListView listViewUsers;
    private int rule;
    ProgressDialog pDialog;
    View viewChoose, viewInformation, userItem;
    Button btnCancel, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        // get all user information from db
        listUsers = new ArrayList<>();
        GetListUser getListUser = new GetListUser();
        getListUser.execute();

    }

    /**
     * Background Get List User
     */
    class GetListUser extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            MyService jsonParser = new MyService();
            String json = jsonParser.callService(Constant.URL_LIST_USER, MyService.GET);
            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray jsonArrayUser = jsonObj.getJSONArray(Constant.OBJECT_JSON_LIST_USER);
                        for (int i = 0; i < jsonArrayUser.length(); i++) {
                            JSONObject obj = (JSONObject) jsonArrayUser.get(i);
                            int age = obj.getInt(Constant.AGE);
                            String fullName = obj.getString(Constant.FULL_NAME);
                            String diseaseName = obj.getString(Constant.DISEASE_NAME);
                            String userId = obj.getString(Constant.USER_ID);
                            UserInfor userInfor = new UserInfor(userId, fullName, age, diseaseName);
                            listUsers.add(userInfor);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e(Constant.LOG_JSON, Constant.MSG_JSON);
            }
            return null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ListUserActivity.this);
            pDialog.setMessage(Constant.MESSAGE_LOADING);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (pDialog.isShowing())
                pDialog.dismiss();
            getData();
        }

        private void getData() {
            if (listUsers.size() == 0) {
                UserInfor userInfor = new UserInfor(Constant.MESAGE_NO_DATA, Constant.EMPTY, Constant.INT_VALUE_DEFAULT, Constant.EMPTY);
                listUsers.add(userInfor);
            }
            listViewUsers = findViewById(R.id.list_users);
            // Create adapter to sent the data on the view
            listViewUserAdapter = new ListViewUserAdapter(listUsers);
            listViewUsers.setAdapter(listViewUserAdapter);
            listViewUserAdapter.notifyDataSetChanged();
            listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get user information that just have selected
                    UserInfor userInfor = (UserInfor) listViewUserAdapter.getItem(position);
                    // Get id of user and send to detail screen
                    String userId = userInfor.getUserId();
                    // Create detail activity
                    Intent intent = new Intent(ListUserActivity.this, DetailUserActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.USER_ID, userId);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                    userItem = view.findViewById(R.id.user_item);
                    viewChoose = view.findViewById(R.id.btn_choose);
                    viewInformation = view.findViewById(R.id.information);
                    btnCancel = view.findViewById(R.id.btn_cancel);
                    btnDel = view.findViewById(R.id.btn_delete);
                    userItem.setBackgroundResource(R.color.choose_item);
                    int visibleStatus = viewChoose.getVisibility();
                    if (visibleStatus == View.INVISIBLE) {
                        viewChoose.setVisibility(View.VISIBLE);
                    } else {
                        viewChoose.setVisibility(View.INVISIBLE);
                    }
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            backNormal(position);
                        }
                    });
                    btnDel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Create interface dialog
                            AlertDialog.Builder confirm = new AlertDialog.Builder(adapterView.getContext());
                            // Set information for dialog
                            setUpDialog(confirm, position);
                            //Create dialog
                            AlertDialog dialogConfirm = confirm.create();
                            dialogConfirm.show();
                        }
                    });

                    return true;
                }
            });
        }

        /**
         * Set information for dialog
         *
         * @param confirm
         * @param position
         */
        private void setUpDialog(AlertDialog.Builder confirm, final int position) {
            confirm.setTitle(Constant.CONFIRM_TITLE);
            confirm.setMessage(Constant.CONFIRM_USER);
            confirm.setPositiveButton(Constant.YES, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String name = listUsers.get(position).getFullname();
                    listUsers.remove(position);
                    // Do delete in DB
                    listViewUserAdapter.notifyDataSetChanged();
                    backNormal(position);
                    Toast.makeText(ListUserActivity.this, Constant.DELETED_USER + name, Toast.LENGTH_SHORT).show();
                }
            });
            confirm.setNegativeButton(Constant.NO, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    backNormal(position);
                    dialogInterface.cancel();

                }
            });
        }

        private void backNormal(int position) {
            if (position % 2 == 0) {
                userItem.setBackgroundResource(R.color.odd_item);
            } else {
                userItem.setBackgroundResource(R.color.even);
            }
            viewChoose.setVisibility(View.INVISIBLE);
        }
    }
}