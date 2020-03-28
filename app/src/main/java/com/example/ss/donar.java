package com.example.ss;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ss.Http.HttpCommunication;
import com.example.ss.url.Defines;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class donar extends AppCompatActivity {
    private RecyclerView recyclerview;
    private String[] names;
    private String[] emails;
    private String[] phones;

    String[] splace;
    String[] dplace;
    String[] stime;
    String[] dtime;
    String[] latt;
    String[] longt;
    private List<TrackDataDetail> memberList;

    String semail, spass;

    JSONArray jarr = null;
    JSONObject json;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String resultdata = "data";

    String name,driver,phone,sourcetime,desttime,sourceplace,destplace,dates;

    TrackDataDetail objtrack;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        memberList=new ArrayList<TrackDataDetail>();
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(donar.this);
        //StaggeredGridLayoutManager;
        //GridLayoutManager gridLayoutManager

       // objtrack=new TrackBusDetail();
        recyclerview.setLayoutManager(layoutManager);
        DonarList();
    }


    public void DonarList()
    {
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
       // params1.add(new BasicNameValuePair("driver_name", strName));
       // params1.add(new BasicNameValuePair("phone", strMobile));

        json = HttpCommunication.makeHttpRequest(Defines.TAG_DONAR_TRACK_LIST, "GET", params1,getApplicationContext());

        try {

            // Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {
                jarr = json.getJSONArray(resultdata);

                Log.i("array", "" + jarr.length());

                for (int i = 0; i < jarr.length(); i++) {

                    JSONObject c = jarr.getJSONObject(i);
                    String name,blood,phone,gcell,organ,height,weight,lastdonate,address,longtitude,lattitude;
                    name= c.getString("name");
                    blood= c.getString("blood");
                    phone= c.getString("phone");
                     height=c.getString("height");
                    weight=c.getString("weight");

                    lastdonate=c.getString("lastdonate");
                    address=c.getString("address");

                    lattitude=c.getString("lattitude");
                    longtitude=c.getString("longtitude");



                    Log.i("data",name+","+blood+","+phone+","+lattitude+","+longtitude+","+height+","+weight+","+lastdonate+","+address);
                    objtrack=new TrackDataDetail(name,blood,phone,lattitude,longtitude,height,weight,lastdonate,address);

                    memberList.add(objtrack);

                }

            }

            TrakListAdapter adapter = new TrakListAdapter(memberList, donar.this);
            recyclerview.setAdapter(adapter);

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Erorr"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
            e.getMessage();
        }


    }


}
