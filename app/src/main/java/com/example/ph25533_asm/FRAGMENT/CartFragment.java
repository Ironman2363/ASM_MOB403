package com.example.ph25533_asm.FRAGMENT;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ph25533_asm.ACTIVITY.BillActivity;
import com.example.ph25533_asm.ADAPTER.CartAdrapter;
import com.example.ph25533_asm.DAO.UserDao;
import com.example.ph25533_asm.MODEL.Bill;
import com.example.ph25533_asm.MODEL.Cart;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.InterFaceCart;
import com.example.ph25533_asm.UNTIL.List;

import java.text.DecimalFormat;

public class CartFragment extends Fragment implements InterFaceCart {

    private String mParam2;
    //ArrayList<Cart> listCart = new ArrayList<>();
    private int idFood, amount;
    private String nameFood, imageFood;
    private long price;
    private CartAdrapter cartAdrapter;
    private RecyclerView rccViewCart;
    private TextView tvTongtienthanhtoan;
    private Button btnThanhtoan;


    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view); //anh xa-------
        cartAdrapter.setData(List.listCart);
        cartAdrapter.notifyDataSetChanged();
        LinearLayoutManager managercart = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rccViewCart.setLayoutManager(managercart);
        rccViewCart.setAdapter(cartAdrapter);

        //thanh toan
        thanhToan();
    }

    private void  anhXa(View view){
        cartAdrapter = new CartAdrapter(view.getContext(),this::thanhtien);
        rccViewCart = view.findViewById(R.id.rccView_cart);
        tvTongtienthanhtoan = view.findViewById(R.id.cart_tv_tongtienthanhtoan);
        btnThanhtoan = view.findViewById(R.id.cart_btn_thanhtoan);
    }

    @Override
    public void onStart() {
        super.onStart();
        cartAdrapter.setData(List.listCart);
        cartAdrapter.notifyDataSetChanged();
        LinearLayoutManager managercart = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rccViewCart.setLayoutManager(managercart);
        rccViewCart.setAdapter(cartAdrapter);
    }

    @Override
    public void thanhtien() {
        int thanhtien = CartAdrapter.thanhtien;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### VNĐ");
        tvTongtienthanhtoan.setText("Tổng tiền: \n  "+decimalFormat.format(thanhtien));
    }


    private void  thanhToan(){
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add tuong san pham trong cart vao bill
                List.listBill.clear(); // xoa rong list bill cũ--
                for(int i=0; i<List.listCart.size();i++)
                {
                    Bill bill = new Bill();
                    Cart cart = List.listCart.get(i);
                    bill.setImg(cart.getImageFood());
                    bill.setName(cart.getNameFood());
                    bill.setPriceProduct((int) cart.getPrice());
                    bill.setTongTienSoSp(cart.getTongtien());
                    bill.setSoluongmua(cart.getSoluongmua());
                    bill.setMaAccount(UserDao.idAccount);
                    List.listBill.add(bill);
                }
                Intent intent = new Intent(getContext(), BillActivity.class);
                startActivity(intent);
                //xoa trong gio hang;
                List.listCart.clear();
            }
        });
    }

}