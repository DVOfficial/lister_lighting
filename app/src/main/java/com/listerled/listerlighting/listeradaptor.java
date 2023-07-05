package com.listerled.listerlighting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class listeradaptor extends RecyclerView.Adapter<listeradaptor.MyViewHolder>{

    private Context context;
    private List<Class_ListerData> listerDataList;

    public listeradaptor(Context context) {
        this.context = context;
        listerDataList=new ArrayList<>();
    }

    public void addLister(Class_ListerData class_listerData){
        listerDataList.add(class_listerData);
        notifyDataSetChanged();

    }
    public void clear(){
        listerDataList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_lister_drive,parent,false);




        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bindViews(listerDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return listerDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textModelName,textColor,textBrand,textCurrStock;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textModelName = itemView.findViewById(R.id.textModelName);
            textColor = itemView.findViewById(R.id.textColor);
            textBrand = itemView.findViewById(R.id.textBrand);
            textCurrStock = itemView.findViewById(R.id.textCurrStock);
        }

        public void bindViews(Class_ListerData class_listerData) {
            textModelName.setText(class_listerData.getModelName());
            textColor.setText(class_listerData.getCOLOR());
            textBrand.setText(class_listerData.getBRAND());
            textCurrStock.setText(class_listerData.getCURRENTSTOCK());
        }
    }
}
