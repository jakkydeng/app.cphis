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
    

    

    public static String url="jdbc:oracle:thin:@221.2.220.182:1521:orcl";


   


    public static  String user="system";
    public static  String password="oracle";
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;




    public static void main(String[] args) throws SQLException {

      
       }
    public  static String connect() {
          String ex="";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
        } catch (ClassNotFoundException e) {
            System.out.println("连接数据库错误!");
            ex="数据库异常"+e.toString();
        }
        // 建立连接
      try{ 
            con = DriverManager.getConnection(url,user,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //System.out.println("数据库连接成功!");
        } catch(SQLException e) {
          System.out.println("数据库异常"+e.toString());
          ex="数据库异常"+e.toString();

      }
        return ex;
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
    

    

  
    
    
    
    
    
    

