package com.example.dushan.crimeandmissingrepoter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DbConnection {
    public static Connection c;
    public static String usernameloadingz;
    public static String emailzloadingz;
    public static String load_usertypez;
    public static String load_user_passwordz;
    public static String load_user_single_id;
    public static String search_text;
    public static String single_inquid;
    public static String doctorid_load;

    public static Connection createCon() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
       // c = DriverManager.getConnection("jdbc:mysql://192.168.43.200:3307/finalhadcraft", "root", "123");
        //c = DriverManager.getConnection("jdbc:mysql://192.168.42.157:3307/finalhadcraft", "root", "123");
        System.out.println("Sql errorz" +"Call Unaa");
        c = DriverManager.getConnection("jdbc:mysql://192.168.1.10:3307/doctorminers", "root", "123");

        //c = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorminers", "root", "");
        return c;

    }

    public static void iud(String sql) throws Exception {
        System.out.println("Sql errorz" +"Call insert");
        if (c == null) {
            createCon();
        }
        c.createStatement().executeUpdate(sql);
    }

    public static ResultSet search(String sql) throws Exception {
        System.out.println("Sql errorz" +"Call Unaa searc");
        if (c == null) {
            createCon();
        }
        return c.createStatement().executeQuery(sql);
    }

}
