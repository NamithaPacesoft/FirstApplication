package com.example.user.myapplication.adaptors.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.data.GroceryStoreItem;

import java.util.List;

/**
 * Created by User on 2017-06-17.
 */

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.RecylerViewHolder> {

    List<GroceryStoreItem> lstGroceryData;
    LayoutInflater inflater;
    int count =0;
    int binderCount =0;

    public RecyclerViewAdaptor(List<GroceryStoreItem> lstData,Context context){
        this.inflater= LayoutInflater.from(context);
        this.lstGroceryData=lstData;

    }

    @Override
    public RecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.layout_recycler_view_item,parent,false);
        Log.i(getClass().getSimpleName(),"Count :" + (++count));
        return new RecylerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecylerViewHolder holder, int position) {
        Log.i(getClass().getSimpleName(),"Binder count  :" + (++binderCount));
        GroceryStoreItem item  =lstGroceryData.get(getItemCount() - position -1);
        holder.tvProductName.setText(item.getProductName());
        holder.tvPrice.setText( Float.toString( item.getPrice()));
        holder.tvQuantity.setText(Integer.toString(item.getQuantity()) );
        holder.tvDescription.setText(item.getDescription());

    }

    @Override
    public int getItemCount() {
        return lstGroceryData.size();
    }

    class RecylerViewHolder extends RecyclerView.ViewHolder{

        private TextView tvProductName;
        private TextView tvPrice;
        private TextView tvQuantity;
        private TextView tvDescription;

        public RecylerViewHolder(View itemView) {
            super(itemView);

            tvProductName =(TextView) itemView.findViewById(R.id.tv_product_name);
            tvPrice =(TextView) itemView.findViewById(R.id.tv_price);
            tvQuantity =(TextView) itemView.findViewById(R.id.tv_quantity);
            tvDescription =(TextView) itemView.findViewById(R.id.tv_description);
        }
    }
}
