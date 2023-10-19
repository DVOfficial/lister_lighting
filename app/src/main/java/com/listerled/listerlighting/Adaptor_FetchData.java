package com.listerled.listerlighting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adaptor_FetchData extends RecyclerView.Adapter<Adaptor_FetchData.MyViewHolder> {

    private Context context;
    private List<Class_ListerCounter> mDatalistUser;
    private OnItemClickListener mListener;


    public Adaptor_FetchData(Context context, List<Class_ListerCounter> mDatalistUser) {
        this.context = context;
        this.mDatalistUser = mDatalistUser;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public void filterlist(ArrayList<Class_ListerCounter> listSearchQues) {
        mDatalistUser=listSearchQues;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onDelete(int Position);

        void sendWelcomeMsz(int position, String title);

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.list_lister_rectangular, parent, false);
        return new  MyViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Class_ListerCounter user = mDatalistUser.get(position);

        String model_name =user.getModel_Name();
        String color =user.getCOLOR();
        String brand =user.getBRAND();
        String current_stock =user.getCURRENT_STOCK();
        String date =user.getDate();
        String vUrl =user.getImageLink();







//        String vuserid =user.getUserid();
//        String vpassword =user.getPassword();
//        String vtoken =user.getToken();
//        String vStatus =user.getStatus();




//        if (!vStatus.equals("")){
//            holder.tv_JoiningSts.setText("Messages Succesfully send");
//        }else{
//            holder.btn_pending.setVisibility(View.VISIBLE);
//        }


                if (model_name != null){
                    holder.tv_ModelID_Name.setText(model_name);
                }
                else{
                    holder.tv_ModelID_Name.setVisibility(View.GONE);
                }
//                if (color != null){
//                    switch(color){
//                        case "WW" : holder.tv_Color.setText(color);
//                        holder.tv_Color.setBackgroundColor(ContextCompat.getColor(context, R.color.WW));
//                        break;
//                        case "CW" : holder.tv_Color.setText(color);
//                            holder.tv_Color.setBackgroundColor(ContextCompat.getColor(context, R.color.CW));
//                            break;
//                        case "NW" : holder.tv_Color.setText(color);
//                            holder.tv_Color.setBackgroundColor(ContextCompat.getColor(context, R.color.NW));
//                            break;
//                        case "R" : holder.tv_Color.setText(color);
//                            holder.tv_Color.setBackgroundColor(ContextCompat.getColor(context, R.color.R));
//                            break;
//
//                    }
////                    holder.tv_Color.setText(color);
//                }
//                else{
//                    holder.tv_Color.setVisibility(View.GONE);
//                }
//                if (brand != null){
//                    holder.tv_Series.setText(brand);
//                }
//                else{
//                    holder.tv_Series.setVisibility(View.GONE);
//                }
                if (current_stock != null){
                    if (current_stock.equals("Login")){
//                        holder.btn_PlaceOrder.setText("Login");
                        holder.tv_CurrentStock.setVisibility(View.GONE);
                        holder.iv_loginstatus.setVisibility(View.VISIBLE);
                    }else{
                        holder.tv_CurrentStock.setVisibility(View.VISIBLE);
                        holder.iv_loginstatus.setVisibility(View.GONE);
                        holder.tv_CurrentStock.setText(current_stock);
//                        holder.btn_PlaceOrder.setText("Place Order");

                    }
                }
                else{
                    holder.tv_CurrentStock.setVisibility(View.GONE);

                }

        String [] arr = model_name.split("\\s+");
//        holder.ll_ProductInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent
//
//                    intent = new Intent(context, FetchProductDesc_Activity.class);
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("modelname", arr[0]);
//                    bundle.putString("modelname1", arr[1]);
//                    bundle.putString("watt", arr[2]);
//
//                    intent.putExtras(bundle);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
////                context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });

//        holder.ll_placeOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SessionManagement sessionManagement = new SessionManagement(context);
//                int userID = sessionManagement.getSession();
//                Intent intent;
//                if (userID==-1) {
//
//                    //user id logged in and so move to mainActivity
//                    intent = new Intent(context, Login1Activity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                } else {
//                    intent = new Intent(context, PlaceOrder.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                }
//                context.startActivity(intent);
//            }
//        });
//holder.btn_PlaceOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SessionManagement sessionManagement = new SessionManagement(context);
//                String userID = sessionManagement.getSSession();
//                Intent intent;
//                if (userID.equals("")) {
//
//                    //user id logged in and so move to mainActivity
//                    intent = new Intent(context, Login1Activity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                } else {
//                    intent = new Intent(context, PlaceOrder.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                }
//                context.startActivity(intent);
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return mDatalistUser.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Color, tv_Series, tv_CurrentStock,
        tvdatetime, tv_ModelID_Name;
//        Button Edit, MoveUp,MoveDown,Delete,btn_PlaceOrder;
        ImageView iv_loginstatus;
        LinearLayout ll_ProductInfo,ll_placeOrder;
        CardView lv_card;


        Button btn_pending;
//        Button tv_Send;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_ModelID_Name = itemView.findViewById(R.id.tv_ModelID_Name);
//                    tv_Color = itemView.findViewById(R.id.tv_color);
//                    tv_Series = itemView.findViewById(R.id.tv_series1);
                    tv_CurrentStock = itemView.findViewById(R.id.tv_CurrentStock);
            lv_card= itemView.findViewById(R.id.lv_card);
            iv_loginstatus= itemView.findViewById(R.id.iv_loginstatus);
//            btn_PlaceOrder= itemView.findViewById(R.id.btn_PlaceOrder);
            ll_ProductInfo= itemView.findViewById(R.id.ll_ProductInfo);
            ll_placeOrder= itemView.findViewById(R.id.ll_placeOrder);
//                    tvToken= itemView.findViewById(R.id.formname);


//            tv_JoiningSts=itemView.findViewById(R.id.tv_JoiningSts);
//            btn_pending=itemView.findViewById(R.id.btn_pending);

//            btn_pending.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        Class_User govtJob = mDatalistUser.get(getAdapterPosition());
//
//                        if (position != RecyclerView.NO_POSITION) {
//
//
//                        }
//                    }
//
//                }
//            });

//
//            btn_pending.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//
//                        //String offWeb=offWebSite.getText().toString();
//                        Class_User govtJob = mDatalistUser.get(getAdapterPosition());
//                        String phoneno = govtJob.getPhoneno();
//                        //String moreInfoLink=moreInfo.getText().toString();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.sendWelcomeMsz(position,phoneno);
//
//
//                        }
//                    }
//
//                }
//            });
//




        }
    }
}


