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
import com.example.ph25533_asm.ADAPTER.AllProductAdapter;
import com.example.ph25533_asm.ADAPTER.ProductAdapter;
import com.example.ph25533_asm.ADAPTER.SearchAdapter;
import com.example.ph25533_asm.MODEL.Product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodDao {
    private ArrayList<Product> listFood = new ArrayList<>();
    private ArrayList<Product> listDrink = new ArrayList<>();
    private ArrayList<Product> listsearch = new ArrayList<>();
    private ArrayList<Product> listall = new ArrayList<>();

    // đò ăn
    public void getFoods(Context context, String link, ProductAdapter apdater){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listFood.clear();
                int maDoan, gia , soluong, maLoai,checkfav;
                String avatar,name, mota;
                if (response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            maDoan = jsonObject.getInt("maDoan");
                            maLoai = jsonObject.getInt("maLoai");
                            name = jsonObject.getString("tenDoan");
                            avatar = jsonObject.getString("avatar");
                            gia = jsonObject.getInt("gia");
                            soluong = jsonObject.getInt("soLuong");
                            mota = jsonObject.getString("moTa");
                            checkfav = jsonObject.getInt("chkFavourite");

                            listFood.add(new Product(maDoan,maLoai, gia, soluong, avatar, name, mota, checkfav));
                        }

                        apdater.getData(listFood);
                        apdater.notifyDataSetChanged();
                    }catch (Exception e){
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

    // dồ uống
    public void getDrinks(Context context, String link, ProductAdapter apdater){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listDrink.clear();
                int maDoan, gia , soluong, maLoai,checkfav;
                String avatar,name, mota;
                if (response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            maDoan = jsonObject.getInt("maDoan");
                            maLoai = jsonObject.getInt("maLoai");
                            name = jsonObject.getString("tenDoan");
                            avatar = jsonObject.getString("avatar");
                            gia = jsonObject.getInt("gia");
                            soluong = jsonObject.getInt("soLuong");
                            mota = jsonObject.getString("moTa");
                            checkfav = jsonObject.getInt("chkFavourite");

                            listDrink.add(new Product(maDoan,maLoai, gia, soluong, avatar, name, mota, checkfav));
                        }

                        apdater.getData(listDrink);
                        apdater.notifyDataSetChanged();
                    }catch (Exception e){
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

    // tất cả
    public void getall(Context context, String link, AllProductAdapter apdater){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listall.clear();
                int maDoan, gia , soluong, maLoai,checkfav;
                String avatar,name, mota;
                if (response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            maDoan = jsonObject.getInt("maDoan");
                            maLoai = jsonObject.getInt("maLoai");
                            name = jsonObject.getString("tenDoan");
                            avatar = jsonObject.getString("avatar");
                            gia = jsonObject.getInt("gia");
                            soluong = jsonObject.getInt("soLuong");
                            mota = jsonObject.getString("moTa");
                            checkfav = jsonObject.getInt("chkFavourite");

                            listall.add(new Product(maDoan,maLoai, gia, soluong, avatar, name, mota, checkfav));
                        }

                        apdater.getData(listall);
                        apdater.notifyDataSetChanged();
                    }catch (Exception e){
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

    // search
    public void getsearch(Context context, String link, String value , SearchAdapter apdater){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listsearch.clear();
                int maDoan, gia , soluong, maLoai,checkfav;
                String avatar,name, mota;
                if (response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            maDoan = jsonObject.getInt("maDoan");
                            maLoai = jsonObject.getInt("maLoai");
                            name = jsonObject.getString("tenDoan");
                            avatar = jsonObject.getString("avatar");
                            gia = jsonObject.getInt("gia");
                            soluong = jsonObject.getInt("soLuong");
                            mota = jsonObject.getString("moTa");
                            checkfav = jsonObject.optInt("chkFavourite");

                            listsearch.add(new Product(maDoan,maLoai, gia, soluong, avatar, name, mota, checkfav));
                        }
                        apdater.getData(listsearch);
                        apdater.notifyDataSetChanged();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap1 = new HashMap<>();
                hashMap1.put("search",value);
                return hashMap1;
            }
        };


        requestQueue.add(stringRequest);
    }

}
