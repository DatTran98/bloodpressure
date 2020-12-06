/**
 * Copyright(C) 2020 Hust
 * Constant.java Oct 24, 2020 Trần Bá Đạt
 */
package com.hust.bloddpressure.util;


/**
 * Class chứa các biến hằng của project
 * 
 * @author Trần Bá Đạt
 *
 */
public class Constant {

	// Biến quyền ứng dụng
	public static final int ADMINRULE = 0;
	public static final int USER_RULE = 1;
	// Biến static cho DB
//	public static final String JDBC_SQL = DatabaseProperties.getValueByKey("JDBC_SQL");
//	public static final String USER_SQL = DatabaseProperties.getValueByKey("USER_SQL");
//	public static final String PASS_SQL = DatabaseProperties.getValueByKey("PASS_SQL");
//	public static final String URL_SQL = DatabaseProperties.getValueByKey("URL_SQL");
	public static final String JDBC_SQL = "com.mysql.jdbc.Driver";
	public static final String USER_SQL = "root";
	public static final String PASS_SQL = "HH1a3110@";
	public static final String URL_SQL = "jdbc:mysql://42.117.189.29:3306/DATN";
	public static final String PROPERTIES_DATABASE_PATH = "classes/database_ja.properties";
	public static final String CONFIG_PATH = "config.properties";
	
	// Biến static sử dụng cho common
	public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz"; // a-z
	public static final String ALPHAUPPERCASE = ALPHA.toUpperCase(); // A-Z
	public static final String DIGIST = "0123456789"; // 0-9
	public static final String SPECHAR = "%^/!|;.,$@#&*()?";
	public static final String ALPHA_NUMBERIC = ALPHA + ALPHAUPPERCASE + DIGIST;
	
	public static final String ACTION_SORT = "sort";
	public static final String ACTION_SEARCH = "search";
	public static final String SHA_1 = "SHA-1";

    public static final int MODE_EDIT = 2;
	public static final int MODE_ADD = 1;
    public static final int MIN_LENGTH = 6;
	public static final int MAX_LENGTH = 20;
    public static final int MIN_AGE = 5;
    public static final int INT_VALUE_DEFAULT = 0;
	public static final String SELECT_ROOM = "Chọn phòng";
	public static final String LABEL_CHART_MAX = "Áp huyết cao";
    public static final int MODE_BASIC = 0;
	public static final int MODE_HISTORY=1;
	public static final String LABEL_CHART_MIN = "Áp huyết thấp";
}
