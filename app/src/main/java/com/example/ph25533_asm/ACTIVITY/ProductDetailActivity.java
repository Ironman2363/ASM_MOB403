package com.example.ph25533_asm.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph25533_asm.DAO.UserDao;
import com.example.ph25533_asm.MODEL.Bill;
import com.example.ph25533_asm.MODEL.Cart;
import com.example.ph25533_asm.MODEL.Product;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.List;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    boolean checkFavourite = true;
    int soluongmua = 1;
    private Toolbar toolbar;
    private Button btn_buynow;
    private ImageButton btn_favourite, btn_addtocard;
    private TextView name_product, price_product, tv_chitiet_sp;
    private final ArrayList<Product> list = new ArrayList<>();
    private ImageView imgProduct;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        toolbar = findViewById(R.id.toolbar_productdetail);
        btn_buynow = findViewById(R.id.btn_buynow);
        btn_favourite = findViewById(R.id.btn_favourite);
        btn_addtocard = findViewById(R.id.btn_addtocard);
        name_product = findViewById(R.id.name_product);
        price_product = findViewById(R.id.price_product);
        imgProduct = findViewById(R.id.img_product);
        tv_chitiet_sp = findViewById(R.id.tv_chitiet_sp);

        // toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(" Chi tiết sản phẩm");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        product = (Product) getIntent().getSerializableExtra("postion");

        productInformation();
        onclickFavourite();

        // mua ngay
        btn_buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = getLayoutInflater().inflate(R.layout.layout_dialog_buynow, null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ProductDetailActivity.this);
                bottomSheetDialog.setContentView(v);

                ImageView img_product_dialog = bottomSheetDialog.findViewById(R.id.dialog_img_product);
                EditText ed_soluong_sp = bottomSheetDialog.findViewById(R.id.ed_soluong_sp);
                TextView tv_soluong = bottomSheetDialog.findViewById(R.id.tv_soluong);
                TextView tv_dialog_price = bottomSheetDialog.findViewById(R.id.tv_dialog_price);
                Button btn_Minus = bottomSheetDialog.findViewById(R.id.btnMinus);
                Button btn_Plus = bottomSheetDialog.findViewById(R.id.btnPlus);
                Button btn_buynow_dialog = bottomSheetDialog.findViewById(R.id.btn_buynow_dialog);


                Picasso.get().load(product.getImageFood())
                        .placeholder(R.drawable.ic_baseline_image_24)
                        .error(R.drawable.ic_baseline_error_24)
                        .into(img_product_dialog);
                DecimalFormat format = new DecimalFormat("###,###,###");
                tv_dialog_price.setText(format.format(product.getPrice()) + " vnđ");
                tv_soluong.setText("Số lượng còn: " + product.getAmount());
                // giảm số lượng
                btn_Minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soluongmua = Integer.parseInt(ed_soluong_sp.getText().toString().trim());
                        if (soluongmua > 1) {
                            soluongmua--;
                        } else if (soluongmua <= 1) {
                            Toast.makeText(ProductDetailActivity.this, "không thể nhỏ hơn 1!", Toast.LENGTH_SHORT).show();
                        }
                        ed_soluong_sp.setText(soluongmua + "");
                    }
                });
                // tăng số lượng
                btn_Plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soluongmua = Integer.parseInt(ed_soluong_sp.getText().toString().trim());
                        if (soluongmua < product.getAmount()) {
                            soluongmua++;
                        } else {
                            Toast.makeText(ProductDetailActivity.this, "số lượng sản phẩm chỉ có: " + product.getAmount(), Toast.LENGTH_SHORT).show();
                        }
                        ed_soluong_sp.setText(soluongmua + "");
                    }
                });

                //sự kiện mua hàng
                btn_buynow_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List.listBill.clear();
                        Bill bill = new Bill();
                        Intent intent = new Intent(ProductDetailActivity.this, BillActivity.class);
                        Log.d("aa", "" + product.getImageFood());
                        List.listBill.add(new Bill(product.getPrice(),
                                product.getPrice() * soluongmua,
                                product.getImageFood(), product.getName(), soluongmua, UserDao.idAccount));
                        startActivity(intent);
                    }
                });
                bottomSheetDialog.show();
            }
        });

        // thêm vào giỏ hành
        btn_addtocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                cart.setIdFood(product.getIdFood());
                cart.setSoluongsp(product.getAmount());
                cart.setNameFood(product.getName());
                cart.setImageFood(product.getImageFood());
                cart.setPrice(product.getPrice());
                List.listCart.add(cart);
                Toast.makeText(ProductDetailActivity.this, "Add to Cart Succesfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onclickFavourite() {
        btn_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFavourite == true) {
                    btn_favourite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    checkFavourite = false;
                } else {
                    btn_favourite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    checkFavourite = true;
                }
            }
        });
    }

    private void productInformation() {
        // lấy ảnh
        Picasso.get().load(product.getImageFood())
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(imgProduct);
        name_product.setText(product.getName());
        DecimalFormat format = new DecimalFormat("###,###,###");
        price_product.setText(format.format(product.getPrice()) + " vnđ");
        tv_chitiet_sp.setText(product.getMoTa());
    }
}