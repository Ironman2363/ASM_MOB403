package com.example.ph25533_asm.ADAPTER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph25533_asm.MODEL.Cart;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.InterFaceCart;
import com.example.ph25533_asm.UNTIL.List;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdrapter extends  RecyclerView.Adapter<CartAdrapter.CartViewholder>{

    InterFaceCart interFaceCart;
    private Context context;

    public CartAdrapter(Context context, InterFaceCart interFaceCart) {
        this.context = context;
        this.interFaceCart = interFaceCart;
    }

    public void setData(ArrayList<Cart> listCart){
        List.listCart = listCart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent ,false);
        return new CartViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewholder holder, int position) {
        Cart cart = List.listCart.get(position);
        if(cart==null){
            return;
        }
        Picasso.get().load(cart.getImageFood()).
                placeholder(com.example.ph25533_asm.R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.imgavatar);
        holder.tvName.setText(cart.getNameFood());

        //cộng trừ sản phầm----
        Congtru(holder,position);
        //xoa san pham trong cart
        holder.lnItemCart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("xoa");
                builder.setMessage("Ban muon xoa ?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List.listCart.remove(cart);
                        Toast.makeText(context, "Xoa thanh conG!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        interFaceCart.thanhtien();
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // code something
                    }
                });
                builder.show();

                return false;
            }
        });

        //holder.chkBuy.setOnClickListener(); ->
    }

    @Override
    public int getItemCount() {
        if (List.listCart != null) return List.listCart.size();
        return 0;
    }

    public class CartViewholder extends RecyclerView.ViewHolder{

        private ImageView imgavatar;
        private TextView tvName;
        private Button btnMinus,btnPlus;
        private EditText edSoluong;
        private LinearLayout lnItemCart;

        public CartViewholder(@NonNull View itemView) {
            super(itemView);
            imgavatar = itemView.findViewById(R.id.cart_img_avatar);
            tvName= itemView.findViewById(R.id.cart_tv_namefood);;
            btnMinus= itemView.findViewById(R.id.cart_btnMinus);
            btnPlus= itemView.findViewById(R.id.cart_btnPlus);
            edSoluong = itemView.findViewById(R.id.cart_ed_soluong);
            lnItemCart = itemView.findViewById(R.id.item_ln_cart);
        }
    }

    public static int thanhtien;//biến tĩnh

    private void forthanhtien() //vong lap cong tong so tien;
    {
        for(int i=0; i<List.listCart.size();i++)
        {
            Cart cart = List.listCart.get(i);
            thanhtien += cart.getTongtien();
            interFaceCart.thanhtien();
        }
    }
    private void Congtru(CartViewholder holder,int position) //cộng trừ số lượng---
    {
        Cart cart = List.listCart.get(position);
        //
        holder.btnMinus.setOnClickListener(new View.OnClickListener() //trừ
        {
            @Override
            public void onClick(View v) {
                cart.setSoluongmua(Integer.parseInt(holder.edSoluong.getText().toString().trim()));
                if(cart.getSoluongmua() > 1) {
                    cart.setSoluongmua(cart.getSoluongmua()-1);
                }else
                {
                    Toast.makeText(context,
                            "Số lượng mua không nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                }
                holder.edSoluong.setText(cart.getSoluongmua()+"");
                cart.setTongtien((int) (cart.getSoluongmua() * cart.getPrice()));
                forthanhtien();
                interFaceCart.thanhtien();//interface tương tác với activity hoặc fragment
                thanhtien=0;//trả về số ban đầu sau khi đã set thanhtien để chạy vòng for load lại dữ liệu
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() //cộng
        {
            @Override
            public void onClick(View v) {
                cart.setSoluongmua(Integer.parseInt(holder.edSoluong.getText().toString().trim())); //set số lượng sau khi đã
                if(cart.getSoluongmua() <= cart.getSoluongsp())
                {
                    cart.setSoluongmua(cart.getSoluongmua()+1);
                }else
                {
                    Toast.makeText(context,
                            "Không vượt quá số lượng trong kho!", Toast.LENGTH_SHORT).show();
                }
                holder.edSoluong.setText(cart.getSoluongmua()+"");
                cart.setTongtien((int) (cart.getSoluongmua() * cart.getPrice()));
                forthanhtien();
                interFaceCart.thanhtien();//interface tương tác với activity hoặc fragment
                thanhtien=0;//trả về số ban đầu sau khi đã set thanhtien để chạy vòng for load lại dữ liệu
            }
        });
    }
}
