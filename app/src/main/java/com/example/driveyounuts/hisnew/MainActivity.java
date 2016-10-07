package com.example.driveyounuts.hisnew;

import android.os.Bundle;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;


import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public GridView gdv;
    firstAdapter fap;
    List<Person> l=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gdv= (GridView) findViewById(R.id.showItem);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            hisConnection.connect();
            String json=hisConnection.select("select 姓名 as name,性别 as gender from 病人信息 where 在院=1");
            Gson gson=new Gson();
            l=gson.fromJson(json, new TypeToken<List<Person>>(){}.getType());

            hisConnection.close();
        } catch (SQLException e) {
           System.out.print(e.toString());
        } catch (JSONException e) {
            System.out.print(e.toString());
        }



        fap= new firstAdapter(this,l);
        gdv.setAdapter(fap);
        fap.notifyDataSetChanged();





    }

}
