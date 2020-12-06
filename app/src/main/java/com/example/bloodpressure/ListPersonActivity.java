package com.example.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListPersonActivity extends AppCompatActivity {
    ArrayList<Accounts> listAccounts;
    AccountsListViewAdapter accountsListViewAdapter;
    ListView listViewAccounts;
    public ListPersonActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_person);

        listAccounts = new ArrayList<>();
        Accounts accounts1 = new Accounts(1,"account1","2345");

        Accounts accounts2 = new Accounts(2,"account2","2345");
        Accounts accounts3 = new Accounts(3,"account3","2345");
        Accounts accounts4 = new Accounts(4,"account4","2345");
        Accounts accounts5 = new Accounts(5,"account5","2345");
        Accounts accounts6 = new Accounts(6,"account6","2345");
        Accounts accounts7 = new Accounts(7,"account7","2345");
        Accounts accounts8 = new Accounts(8,"account8","2345");

        listAccounts.add(accounts1);
        listAccounts.add(accounts2);
        listAccounts.add(accounts3);
        listAccounts.add(accounts4);
        listAccounts.add(accounts5);
        listAccounts.add(accounts6);
        listAccounts.add(accounts7);
        listAccounts.add(accounts8);
        accountsListViewAdapter = new AccountsListViewAdapter(listAccounts);
        listViewAccounts = findViewById(R.id.list_accounts);
        listViewAccounts.setAdapter(accountsListViewAdapter);
        accountsListViewAdapter.notifyDataSetChanged();
        listViewAccounts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Accounts accounts = (Accounts)accountsListViewAdapter.getItem(position);
                String fullName = accounts.getUsername();
                // Tạo activity hiển thị chi tiết s
                Intent intent = new Intent(ListPersonActivity.this, DetailUserActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("fullName", fullName);
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listAccounts.size()>0){
                    int pos=0;
                    listAccounts.remove(pos);
                    accountsListViewAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}