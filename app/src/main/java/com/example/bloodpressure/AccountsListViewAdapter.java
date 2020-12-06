package com.example.bloodpressure;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountsListViewAdapter extends BaseAdapter {
    final ArrayList<Accounts> listAccounts;

    public AccountsListViewAdapter(ArrayList<Accounts> listAccounts) {
        this.listAccounts = listAccounts;
    }

    @Override
    public int getCount() {
        return listAccounts.size();
    }

    @Override
    public Object getItem(int position) {
        return listAccounts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listAccounts.get(position).getAccountId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View accView;
        if (convertView == null) {
            accView = View.inflate(parent.getContext(), R.layout.accounts_view, null);
        } else accView = convertView;
        Accounts accounts = (Accounts) getItem(position);
        System.out.println("acc" + accounts.getAccountId());
        ((TextView) accView.findViewById(R.id.id_account)).setText(String.format("Mã bệnh nhân: %d",  accounts.getAccountId()));
        ((TextView) accView.findViewById(R.id.username)).setText(String.format("Tên bệnh nhân: %s",accounts.getUsername()));
        ((TextView) accView.findViewById(R.id.password)).setText(String.format("Mật khẩu: %s",accounts.getPassword()));
        return accView;
    }
}
