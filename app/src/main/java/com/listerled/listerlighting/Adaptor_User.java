package com.listerled.listerlighting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptor_User extends RecyclerView.Adapter<Adaptor_User.MyViewHolder> {

    private Context context;
    private List<Class_User> mDatalistUser;
    private OnItemClickListener mListener;


    public Adaptor_User(Context context, List<Class_User> mDatalistUser) {
        this.context = context;
        this.mDatalistUser = mDatalistUser;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
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

        final Class_User user = mDatalistUser.get(position);

        String vUser =user.getUserid();
        String vName =user.getName();
        String vPhone =user.getPhoneno();
        String vEmailid =user.getToken();
        String vdatetime =user.getDatetime();
//        String vuserid =user.getUserid();
//        String vpassword =user.getPassword();
//        String vtoken =user.getToken();
//        String vStatus =user.getStatus();




//        if (!vStatus.equals("")){
//            holder.tv_JoiningSts.setText("Messages Succesfully send");
//        }else{
//            holder.btn_pending.setVisibility(View.VISIBLE);
//        }


                if (vUser != null){
                    holder.tvuserid.setText(vUser);
                }
                else{
                    holder.tvuserid.setVisibility(View.GONE);
                }
                if (vName != null){
                    holder.tvName.setText(vName);
                }
                else{
                    holder.tvName.setVisibility(View.GONE);
                }
                if (vPhone != null){
                    holder.tvPhone.setText(vPhone);
                }
                else{
                    holder.tvPhone.setVisibility(View.GONE);
                }
                if (vEmailid != null){
                    holder.tvEmailid.setText(vEmailid);
                }
                else{
                    holder.tvEmailid.setVisibility(View.GONE);
                }
//                if (vtoken != null){
//                    holder.tvToken.setText("Token:"+ vtoken);
//                }
//                else{
//                    holder.tvToken.setVisibility(View.GONE);
//                }

//                if (vdatetime != null){
//                    holder.tvdatetime.setText("Date & Time:" + vdatetime);
//                }
//                else{
//                    holder.tvdatetime.setVisibility(View.GONE);
//                }
//
//                if (vuserid != null){
//                    holder.tvuserid.setText("Userid:"+vuserid);
//                }
//                else{
//                    holder.tvuserid.setVisibility(View.GONE);
//                }

//                if (vpassword != null){
//                    holder.tvPassword.setText("Password:"+vpassword);
//                }
//                else{
//                    holder.tvPassword.setVisibility(View.GONE);
//                }

//
//                holder.Delete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDatalistUser.remove(position);
//                        notifyDataSetChanged();
//                    }
//                });



    }

    @Override
    public int getItemCount() {
        return mDatalistUser.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPhone,tvEmailid,
        tvdatetime,tvuserid;
        Button Edit, MoveUp,MoveDown,Delete;


        Button btn_pending;
//        Button tv_Send;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvuserid= itemView.findViewById(R.id.user);
                    tvName= itemView.findViewById(R.id.name);
                    tvPhone= itemView.findViewById(R.id.mobile);
                    tvEmailid= itemView.findViewById(R.id.emailid);
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


