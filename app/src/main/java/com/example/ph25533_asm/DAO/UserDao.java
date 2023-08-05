package com.example.ph25533_asm.DAO;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ph25533_asm.MODEL.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
    public static ArrayList<User> list = new ArrayList<>();
    public static int index,idAccount;

    // lấy data user
    public void getData (Context context, String link){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest  = new StringRequest(link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                list.clear();
                int id;
                String username, fullname, email, phoneNumber, address, password;
                if (response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            username = jsonObject.getString("userName");
                            fullname = jsonObject.getString("fullName");
                            email = jsonObject.getString("email");
                            phoneNumber = jsonObject.getString("phoneNumber");
                            address = jsonObject.getString("address");
                            password = jsonObject.getString("password");
                            list.add(new User(id, username, fullname, email,phoneNumber, address, password));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }

    private String data="";

    // insert tài khoản
    public void insertData(Context context, String link, HashMap<String, String> hashMap){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data=response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void updateData(Context context , String link , HashMap<String, String> hashMap){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    // lấy id theo user
    public void getID (Context context, String link, String user){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest  = new StringRequest(link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id;
                String nameUser;
                if (response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            nameUser = jsonObject.getString("userName");
                            if (user.equals(nameUser)){
                                UserDao.idAccount = id;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }

    public ArrayList<User> getList() {
        return list;
    }

    public static void setList(ArrayList<User> list) {
        UserDao.list = list;
    }
}
