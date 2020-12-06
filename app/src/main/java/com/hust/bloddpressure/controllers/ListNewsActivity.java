package com.hust.bloddpressure.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hust.bloddpressure.R;
import com.hust.bloddpressure.model.entities.News;

import java.util.ArrayList;

public class ListNewsActivity extends AppCompatActivity {
    ArrayList<News> listNews;
    ListViewNewsAdapter listViewNewAdapter;
    ListView listViewNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        News news = new News();
        news.setNewId(12);
        news.setTitleNew("Thông báo");
        news.setContentNew("\t\n"+
                "\n" +
                " \n" +
                "\n" +
                "Trung tâm Y tế thành phố Móng Cái được hợp nhất bởi 02 đơn vị: Bệnh viện đa khoa khu vực Móng Cái và Trung tâm Y tế thành phố Móng Cái tại quyết định số: 1330/QĐ-UBND ngày 29/4/2016 của UBND tỉnh Quảng Ninh về việc thành lập Trung tâm Y tế thành phố Móng Cái trực thuộc Sở Y tế trên cơ sở hợp nhất Trung tâm Y tế thành phố Móng Cái với\n" +
                "\n" +
                "Bệnh viện đa khoa khu vực Móng Cái.");
        News news1 = new News();
        news1.setNewId(12);
        news1.setTitleNew("Thông báo");
        news1.setContentNew("\t\n"+

                "Trung tâm Y tế thành phố Móng Cái được hợp nhất bởi 02 đơn vị: Bệnh viện đa khoa khu vực Móng Cái và Trung tâm Y tế thành phố Móng Cái tại quyết định số: 1330/QĐ-UBND ngày 29/4/2016 của UBND tỉnh Quảng Ninh về việc thành lập Trung tâm Y tế thành phố Móng Cái trực thuộc Sở Y tế trên cơ sở hợp nhất Trung tâm Y tế thành phố Móng Cái với\n" +
                "\n" +
                "Bệnh viện đa khoa khu vực Móng Cái.");
        listNews = new ArrayList<>();
        listNews.add(news);
        listNews.add(news1);
        listViewNewAdapter = new ListViewNewsAdapter(listNews);
        listViewNews = findViewById(R.id.list_news);
        listViewNews.setAdapter(listViewNewAdapter);
        listViewNewAdapter.notifyDataSetChanged();
        listViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListNewsActivity.this, "Cám ơn bạn đã đọc tin", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_add_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListNewsActivity.this, AddNewsActivity.class);
                startActivity(intent);
            }
        });

    }
}