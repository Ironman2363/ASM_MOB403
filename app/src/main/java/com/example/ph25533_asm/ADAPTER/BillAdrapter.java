package com.example.ph25533_asm.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ph25533_asm.MODEL.Bill;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.List;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BillAdrapter extends BaseAdapter {

    //private ArrayList<Bill> listBill;
    private Context context;

    public BillAdrapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Bill> listBill){
        List.listBill = listBill;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(List.listBill != null)
            return List.listBill.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolderBill{
        private ImageView imgProduct;
        private TextView tvName, tvPrice, tvSoluongmua, tvTongtien,tvNgaymua;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //dag viet tiep /////
        Bill bill = List.listBill.get(position);
        ViewHolderBill holder = null;
        if(convertView == null){
            holder = new ViewHolderBill();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(com.example.ph25533_asm.R.layout.item_bill,null);
            holder.tvName = convertView.findViewById(R.id.itembill_tv_name_product);
            holder.imgProduct = convertView.findViewById(R.id.itembill_image_product);
            holder.tvPrice = convertView.findViewById(R.id.itembill_tv_price_product);
            holder.tvSoluongmua = convertView.findViewById(R.id.itembill_tv_soluongsp);
            holder.tvTongtien = convertView.findViewById(R.id.itembill_tv_tongtien1_product);
            holder.tvNgaymua = convertView.findViewById(R.id.itembill_tv_ngaymua);
            convertView.setTag(holder);
        }else
            holder = (ViewHolderBill) convertView.getTag();

        Picasso.get().load(bill.getImg()).
                placeholder(R.drawable.ic_baseline_image_24).
                error(R.drawable.ic_baseline_error_24).
                into(holder.imgProduct);
        holder.tvName.setText(bill.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### VND");
        holder.tvPrice.setText(decimalFormat.format(bill.getPriceProduct()));
        holder.tvSoluongmua.setText("Tổng số tiền ("+bill.getSoluongmua()+" sản phẩm)");
        holder.tvTongtien.setText(decimalFormat.format(bill.getTongTienSoSp()));

        return convertView;
    }
}
