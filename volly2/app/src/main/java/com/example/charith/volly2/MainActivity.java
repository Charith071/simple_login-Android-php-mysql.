package com.example.charith.volly2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText username_txt,password_txt;
    Button log_btn;
    private RequestQueue requestQueue;
    private StringRequest request;
    //=== copy  your loging.php path to here====
    private static final String url="http://192.168.43.228/prac/anlog/loging.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_txt=findViewById(R.id.usernametxt_id);
        password_txt=findViewById(R.id.pwdtxt_id);
        log_btn=findViewById(R.id.logbtn_id);
        requestQueue= Volley.newRequestQueue(this);
        set_logbtn_litner();
    }

    public void set_logbtn_litner(){
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.names().get(0).equals("log_status")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("log_status"),Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                                startActivity(intent);


                            }else {
                                Toast.makeText(getApplicationContext(),"Faild!!",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap=new HashMap<String, String>();
                        hashMap.put("username",username_txt.getText().toString());
                        hashMap.put("password",password_txt.getText().toString());
                        return  hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}
