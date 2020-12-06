package com.hust.bloddpressure.controllers;

import android.os.AsyncTask;
import android.widget.Toast;

import com.hust.bloddpressure.model.base.impl.TblUserImpl;
import com.hust.bloddpressure.model.entities.UserInfor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectSQL extends AsyncTask<String, Void, String> {
    String res = "";
    protected Connection conn;
    // Tạo đối tượng ps truy vấn
    protected PreparedStatement ps;
    // Tạo đối tượng rs để trỏ đến một hàng của một bảng
    protected ResultSet rs;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://117.7.111.178:3306/DATN", "root", "HH1a3110@a");
            UserInfor userInfor = getUserByUsername("admin");
            res = userInfor.getRule() + "";
        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }
        return res;
    }

    private UserInfor getUserByUsername(String username) throws ClassNotFoundException, SQLException {
        // Tạo một đối trường tbluser để trả về
        UserInfor user = new UserInfor();
        // Tạo một câu truy vấn
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("SELECT a.username, a.password, u.salt, u.rule ");
        sqlSelect.append("FROM tbl_accounts a INNER JOIN tbl_user u ");
        sqlSelect.append("ON a.userid = u.userid ");
        sqlSelect.append("WHERE a.username = ?;");
        try {
            // Kiểm tra tên khác rỗng
            if (!username.isEmpty()) {
                // Tạo một đối tượng và lấy danh sách tên các user có trong db
                ArrayList<String> listUserName = getListUsername();
                // Kiểm tra xem tên user nhập vào có trong db không
                boolean containUser = listUserName.contains(username.toLowerCase());
                // nếu có
                if (containUser) {
                    if (conn != null) {
                        // Tạo đối tượng truy vấn và lấy câu truy vấn
                        ps = conn.prepareStatement(sqlSelect.toString());
                        // Thiết lập điều kiện truy vấn
                        int index = 1;
                        ps.setString(index++, username);
                        // Thực hiện truy vấn
                        rs = ps.executeQuery();
                        // Lấy kết quả tìm được
                        while (rs.next()) {
                            user.setUsername(rs.getString("username"));
                            user.setPassword(rs.getString("password"));
                            user.setSalt(rs.getString("salt"));
                            user.setRule(rs.getInt("rule"));
                        }
                    }

                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(TblUserImpl.class.getName()).log(Level.SEVERE, null, e);// ghi log
        }
        // Trả về một user sau khi tìm kiếm
        return user;
    }

    private ArrayList<String> getListUsername() throws ClassNotFoundException, SQLException {
        // Tạo một đối tượng listUserName để chứa danh sách tên các user
        ArrayList<String> listUserName = new ArrayList<String>();
        try {
            if (conn != null) {
                // Tạo câu lệnh select
                StringBuilder sqlSelectName = new StringBuilder();
                sqlSelectName.append("SELECT username FROM tbl_accounts;");
                // Thực hiện lấy câu lệnh truy vấn
                ps = (PreparedStatement) conn.prepareStatement(sqlSelectName.toString());
                // thực hiện truy vấn
                rs = ps.executeQuery();
                // lấy danh sách kết quả
                while (rs.next()) {
                    // Lấy kết quả theo trường login_name
                    String loginName = rs.getString("username");
                    // thêm vào danh sách tên các user
                    listUserName.add(loginName.toLowerCase());
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(TblUserImpl.class.getName()).log(Level.SEVERE, null, e);// ghi log
            // Ném một ngoại lệ ra ngoài để hàm gọi tới xử lý
        }
        // Trả về một danh sách chứa các tên user đã tìm được
        return listUserName;
    }
}