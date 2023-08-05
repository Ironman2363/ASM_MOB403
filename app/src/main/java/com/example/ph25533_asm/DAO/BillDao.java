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
import com.example.ph25533_asm.ADAPTER.BillAdrapter;
import com.example.ph25533_asm.MODEL.Bill;
import com.example.ph25533_asm.UNTIL.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BillDao {
    public void getBill(Context context, String link, int id, BillAdrapter billAdrapter)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id,pirceProduct,tongTienProduct,maAccount,soluongmua;
                String nameProduct,imgProduct;
                if(response != null)
                {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i< jsonArray.length();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            nameProduct=jsonObject.getString("nameProduct");
                            imgProduct=jsonObject.getString("imgProduct");
                            pirceProduct=jsonObject.getInt("pirceProduct");
                            tongTienProduct=jsonObject.getInt("tongTienProduct");
                            maAccount=jsonObject.getInt("maAccount");
                            soluongmua=jsonObject.getInt("soluong");
                            List.listBill.add(new Bill(id,pirceProduct,tongTienProduct,maAccount,imgProduct,nameProduct,soluongmua));
                        }
                        billAdrapter.setData(List.listBill);
                    } catch (JSONException e) {
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
                HashMap<String,String> hashMapID = new HashMap<>();
                hashMapID.put("maAccount", String.valueOf(id));
                return hashMapID;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void insertData(Context context, String link)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List.listBill.clear();//insert xong thi xoa trang listbill
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                JSONArray jsonArray = new JSONArray();
                for (int i=0;i< List.listBill.size();i++)
                {
                    Bill bill = List.listBill.get(i);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("nameProduct",bill.getName());
                        jsonObject.put("imgProduct",bill.getImg());
                        jsonObject.put("pirceProduct",bill.getPriceProduct());
                        jsonObject.put("tongTienProduct",bill.getTongTienSoSp());
                        jsonObject.put("maAccount",bill.getMaAccount());
                        jsonObject.put("soluong",bill.getSoluongmua());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("jsonBill",jsonArray.toString());
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
