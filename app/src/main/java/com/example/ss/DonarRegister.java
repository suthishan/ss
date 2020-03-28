package com.example.ss;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ss.Http.HttpCommunication;
import com.example.ss.url.Defines;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DonarRegister extends AppCompatActivity implements LocationListener
{
   EditText editname;

    EditText editblood;

    EditText editphone;

    EditText etheight;

    EditText etweight;

    EditText etlastdonate;

    EditText etaddress;

    JSONArray jarr = null;
    JSONObject json;

    String name,blood,phone,gcell,organ,height,weight,lastdonate,address;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private Context mContext;
    private Location mLocation;
    private LocationManager locationManager;
    private PendingIntent intent;
    private Location cLocation;


    private static long distance;
    private static long minutes;

    //public LocationManager locationManager;
    private String provider,provider_info;

    public String latitude;

    public String longtitude;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_register);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editname = (EditText)findViewById(R.id.editname);
        editblood = (EditText)findViewById(R.id.editblood);
        editphone = (EditText)findViewById(R.id.editphone);
        etheight = (EditText)findViewById(R.id.etheight);
        etweight = (EditText)findViewById(R.id.etweight);
        etlastdonate = (EditText)findViewById(R.id.etlastdonate);
        etaddress = (EditText)findViewById(R.id.etaddress);

        this.mContext=getApplicationContext();
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider_info = LocationManager.NETWORK_PROVIDER;
        // provider = locationManager.getBestProvider(criteria, false);
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PERMISSION_GRANTED)
        {
            Location location = locationManager.getLastKnownLocation(provider_info);
            // onLocationChanged(location);
        }




    }


    public void Saves(View v)
    {
        name=editname.getText().toString().trim();
        blood=editblood.getText().toString().trim();
        phone=editphone.getText().toString().trim();
        height=etheight.getText().toString().trim();
        weight=etweight.getText().toString().trim();
        lastdonate=etlastdonate.getText().toString().trim();
        address=etaddress.getText().toString().trim();


        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("name", name));
        params1.add(new BasicNameValuePair("blood", blood));
        params1.add(new BasicNameValuePair("phone", phone));
        params1.add(new BasicNameValuePair("height", height));
        params1.add(new BasicNameValuePair("weight", weight));
        params1.add(new BasicNameValuePair("lastdonate", lastdonate));
        params1.add(new BasicNameValuePair("address", address));

        params1.add(new BasicNameValuePair("lattitude", latitude));
        params1.add(new BasicNameValuePair("longtitude", longtitude));


        json = HttpCommunication.makeHttpRequest(Defines.TAG_DONAR_REGISTER,"GET", params1,getApplicationContext());

        try {

           // Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {

                String message = json.getString(TAG_MESSAGE);
                int success = json.getInt(TAG_SUCCESS);
                if(success==1)
                {
                    Toast.makeText(getApplicationContext(), "Data Detail Registered Succesfull", Toast.LENGTH_LONG).show();

                }
                if(success==0)
                {
                    Toast.makeText(getApplicationContext(), "Data Details not Register", Toast.LENGTH_LONG).show();

                }
                Log.i("success status : " + success, message);
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Erorr"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
            e.getMessage();
        }
   }



    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(provider_info, 400, 0, this);
        }
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location)
    {

        Log.i("C Loc",""+location.getLatitude()+" , "+location.getLongitude());
        Criteria criteria = new Criteria();
        provider_info = LocationManager.NETWORK_PROVIDER;
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PERMISSION_GRANTED) {
            cLocation = locationManager.getLastKnownLocation(provider_info);
            Log.i("last loc  ",""+cLocation);
        }

        latitude= String.valueOf(location.getLatitude());
        longtitude= String.valueOf(location.getLongitude());

        Toast.makeText(mContext, ""+location.getLatitude()+" , "+location.getLongitude(), Toast.LENGTH_LONG).show();
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
