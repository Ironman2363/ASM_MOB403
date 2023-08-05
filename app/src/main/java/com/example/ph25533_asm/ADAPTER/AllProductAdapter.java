package com.example.ph25533_asm.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph25533_asm.ACTIVITY.ProductDetailActivity;
import com.example.ph25533_asm.MODEL.Product;
import com.example.ph25533_asm.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProducViewHolder> {
    private ArrayList<Product> list;
    private Context context;

    public AllProductAdapter(Context context) {
        this.context = context;
    }

    public void getData(ArrayList<Product> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AllProducViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.ph25533_asm.R.layout.layout_item_all_product, parent , false);
        return new AllProducViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProducViewHolder holder, int position) {
        Product obj = list.get(position);
        if (obj == null){
            return;
        }

        Picasso.get().load(obj.getImageFood()).
                placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.img);
        holder.name.setText(obj.getName());
        holder.name.setMaxLines(1);
        holder.name.setEllipsize(TextUtils.TruncateAt.END);
        DecimalFormat format = new DecimalFormat("###,###,###");
        holder.price.setText(format.format(obj.getPrice())+" vnđ");

        // click chi tiết sản phẩm
        holder.product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                intent.putExtra("postion",list.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public class AllProducViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name, price;
        private LinearLayout product;

        public AllProducViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product_all);
            name = itemView.findViewById(R.id.name_product_all);
            price = itemView.findViewById(R.id.price_product_all);
            product = itemView.findViewById(R.id.item_product_all);
        }
    }
}
