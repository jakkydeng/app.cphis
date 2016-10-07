/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.driveyounuts.hisnew;

/**
 *
 * @author 邓晖
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.sql.*;
import java.util.Vector;





public class hisConnection {
    

    
    public static String url="jdbc:oracle:thin:@172.16.0.10:1521:orcl";
   


     public static  String user="system";
      public static  String password="oracle";
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    public static Boolean flag=true;
    public static File f=new File("server");
    public static Vector columnHeads = new Vector();
    public static  Vector rows = new Vector();


    public static void main(String[] args) throws SQLException {

      
       }
    public  static void connect() {    
          
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
        } catch (ClassNotFoundException e) {
            System.out.println("数据库1错!");
        }
        // 建立连接
      try{ 
            con = DriverManager.getConnection(url,user,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            System.out.println("数据库连接成功!"); 
        } catch(SQLException e) {
          System.out.println("数据库"+e.toString());
      }
           
    }
    public static void close() throws SQLException{
    stmt.close();
    con.close();
    
    
    }
           
    public static String select(String sql) throws SQLException, JSONException {


        try {  
             
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd =rs.getMetaData();
            
        } catch (SQLException e) {  
            System.out.println(e);  
        }  


       String returnJason=resultSetToJson(rs);

        return returnJason;

    //"select 姓名 from  病人信息 where 在院=1"；



}




    public static String resultSetToJson(ResultSet rs) throws SQLException,JSONException
    {
        // json数组
        JSONArray array = new JSONArray();

        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.put(jsonObj);
        }

        return array.toString();
    }



}
    

    

  
    
    
    
    
    
    

