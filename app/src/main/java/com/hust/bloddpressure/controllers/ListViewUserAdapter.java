package com.hust.bloddpressure.controllers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.model.entities.UserInfor;

import java.util.ArrayList;

public class ListViewUserAdapter extends BaseAdapter {
    final ArrayList<UserInfor> listUsers;

    public ListViewUserAdapter(ArrayList<UserInfor> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return listUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listUsers.get(i).getUserId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View accView;
        if (view == null) {
            accView = View.inflate(viewGroup.getContext(), R.layout.user_item_view, null);
        } else accView = view;
        UserInfor accounts = (UserInfor) getItem(i);
        ((TextView) accView.findViewById(R.id.user_id)).setText(String.format("Mã bệnh nhân: %d", accounts.getUserId()));
        ((TextView) accView.findViewById(R.id.username)).setText(String.format("Tên bệnh nhân: %s", accounts.getUsername()));
        ((TextView) accView.findViewById(R.id.age)).setText(String.format("Tuổi: %s", accounts.getAge()));
        return accView;
    }
}
