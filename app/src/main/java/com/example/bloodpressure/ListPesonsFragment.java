package com.example.bloodpressure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPesonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPesonsFragment extends ListFragment implements OnItemClickListener {
    ArrayList<Accounts> listAccounts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_pesons, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listAccounts = new ArrayList<Accounts>();
        Accounts accounts1 = new Accounts();
        accounts1.setUsername("account1");
        accounts1.setPassword("!22345");
        Accounts accounts2 = new Accounts();
        accounts2.setUsername("account2");
        accounts2.setPassword("!22345");
        listAccounts.add(accounts1);
        listAccounts.add(accounts2);
        AccountsListViewAdapter listViewAdapter = new AccountsListViewAdapter(listAccounts);
        setListAdapter(listViewAdapter);
        getListView().setOnItemClickListener(this);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Planets , android.R.layout.simple_list_item_1);
//        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);
//        ArrayAdapter adapter = ArrayAdapter.c
//        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}