package com.example.ss;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ss.Http.HttpCommunication;
import com.example.ss.url.Defines;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class PeopleLogin extends AppCompatActivity {
    Button bt1,bt2;
    EditText ed1,ed2;
    String n,p;

    JSONArray jarr = null;
    JSONObject json;
    ArrayList<String> useral = new ArrayList<String>();
    ArrayList<String> passal = new ArrayList<String>();
    String strUser;
    String strPass;
    String Tutor_Days="";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ed1=(EditText) findViewById(R.id.na);
        ed2=(EditText) findViewById(R.id.na1);
    }

    public void SignIn(View v)
    {
        n=ed1.getText().toString();
        p=ed2.getText().toString();
        setData(n,p);

    }

    public void Register(View v)
    {
        Intent nextScreen = new Intent(PeopleLogin.this, PeopleRegister.class);
        startActivity(nextScreen);

    }

    public void setData(String username, String password)
    {

        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("username", username));
        params1.add(new BasicNameValuePair("password", password));

        json = HttpCommunication.makeHttpRequest(Defines.TAG_DONAR_LOGIN, "GET", params1, getBaseContext());
        try {
            if (json != null) {
                //  Log.i("Jsonconvert",getPostDataString(json));
                String message = json.getString(TAG_MESSAGE);
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Toast.makeText(getApplicationContext(), " Login Succesfull", Toast.LENGTH_LONG).show();
                    Intent i1 = new Intent(PeopleLogin.this, HomeMenu.class);
                    startActivity(i1);
                    finish();
                }
                if (success == 0) {
                    Toast.makeText(getApplicationContext(), " not Login", Toast.LENGTH_LONG).show();

                }

            }
        } catch (Exception e) {
            Log.i("Error", e.getMessage());
        }

    }

}


