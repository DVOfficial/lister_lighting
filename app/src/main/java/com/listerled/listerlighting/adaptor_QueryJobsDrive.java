//package com.listerled.listerlighting;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//
//public class adaptor_QueryJobsDrive extends RecyclerView.Adapter<adaptor_QueryJobsDrive.MyViewHolder> {
//
//    private Context context;
//    private List<Class_GovtJob> mDatalistNew;
//    private OnItemClickListener mListener;
//
//    public interface  OnItemClickListener{
//        void onItemClick(int position, String mTitle);
//        //void fillbyOfficialLink(int position, String offWeb);
//
//        void shareQues(int position, String question);
//
//        void onMoreInfoClick(int position, String offWeb, String offnotflink, String moreInfoLink, String jobName, String jobPostName, String jobNoOfPost, String jobAgeLimit, String jobFees, String jobEduReq, String jobExpReq, String jobSelProc, String reglastd, String jobPostDate, String examdate, String admitcdate);
//
////        void onWebLinkClick(int position);
//    }
//    public void setOnItemClickListener(OnItemClickListener listener){
//        mListener=listener;
//    }
//
//    public adaptor_QueryJobsDrive(Context context, List<Class_GovtJob> mDatalistNew) {
//        this.context = context;
//        this.mDatalistNew = mDatalistNew;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View rootview = LayoutInflater.from(context).inflate(R.layout.list_item_jobs_drive, parent, false);
//        return new  MyViewHolder(rootview);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Class_GovtJob govtJob = mDatalistNew.get(position);
//
//        String title = govtJob.getJobtitle();
////        String desc = govtJob.getJobdesc();
//
//        String reglastd= govtJob.getLastdate();
//        String jobPostDate=govtJob.getPosteddate();
//        String examdate= govtJob.getExamdate();
////        String admitcdate= govtJob.getAdmitcarddate();
////
////        String offWeb=govtJob.getOfficialweb();
////        String offnotflink=govtJob.getOffnotflink();
////        String moreInfoLink=govtJob.getOffnotflink();
////
////        String jobName = govtJob.getJobname();
////        String jobPostName = govtJob.getJobpostname();
////        String jobNoOfPost = govtJob.getNoofpost();
////
////        String jobAgeLimit = govtJob.getAgelimit();
////        String jobFees = govtJob.getFees();
////        String jobEduReq = govtJob.getEducation();
////        String jobExpReq = govtJob.getExperience();
////        String jobSelProc = govtJob.getSelectionprocess();
//
//
//        Date c = Calendar.getInstance().getTime();
//        SimpleDateFormat cd = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//        String todayDate = cd.format(c);
//
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat ld=new SimpleDateFormat("dd-MM-yyyy");
//        long diff=-1;
//        try{
//            Date ldate=ld.parse(reglastd);
//            Date cdate=ld.parse(todayDate);
//
//            diff=Math.round((ldate.getTime()-cdate.getTime())/(double)86400000);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        FirebaseUser mCurrentUser= FirebaseAuth.getInstance().getCurrentUser();
//        String userID=mCurrentUser.getUid();
//
////        Boolean isExpanded=mDatalistNew.get(position).isExpanded();
////        holder.expandLL.setVisibility(isExpanded?View.VISIBLE:View.GONE);
//
//        if (title != null) {
//            holder.Title.setText(title);
//        }else{
//        holder.Title.setVisibility(View.GONE);
//        }
//
////        if (desc != null) {
////            holder.Desc.setText(desc);
////        }else{
////            holder.Desc.setVisibility(View.GONE);
////        }
//        if (jobPostDate != null) {
//            holder.jobPostDate.setText(jobPostDate);
//        }else{
//            holder.jobPostDate.setVisibility(View.GONE);
//        }
//
//
//        if (reglastd != null) {
//            holder.RegLDate.setText(reglastd);
//        }else{
//            holder.RegLDate.setVisibility(View.GONE);
//        }
////        if (examdate != null) {
////            holder.ExamDate.setText(examdate);
////        }else{
////            holder.ExamDate.setVisibility(View.GONE);
////            holder.l3.setVisibility(View.GONE);
////        }
//        holder.tv_countDays.setText(diff+" Days\nRemaining");
////            holder.offWebSite.setText(Html.fromHtml("<b>Click on link below to Apply from official website\n</b>"+ offWeb));
////        if (moreInfoLink != null) {
////            holder.moreInfo.setText(moreInfoLink);
////        }else{
////            holder.moreInfo.setVisibility(View.GONE);
////        }
//
//        holder.SavedQStatus(position,userID);
//        holder.save_ib.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mListener != null) {
//                    if (position != RecyclerView.NO_POSITION) {
//                        //mListener.dislikeAns();
//                        //String tag = String.valueOf(v.getTag());
//                        // int id = (int) dislike_ib.getTag();
//                        if (holder.save_ib.getTag().equals("save")){
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("Admin")
//                                    .child("SavedGovtJob")
//                                    .child(String.valueOf(position))
//                                    .child(userID).setValue(true);
//                        }else{
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("Admin")
//                                    .child("SavedGovtJob")
//                                    .child(String.valueOf(position))
//                                    .child(userID).removeValue();
//
//                        }
//                        //mListener.likeAns(position, tag);
//                    }
//                }
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDatalistNew.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView Title, Desc, RegLDate, AdmitCDate, ExamDate, offWebSite;
//        TextView moreInfo, withOfficialLink, withUs,jobPostDate,tv_countDays;
//        LinearLayout l1, l3, l4, expandLL, touchExpandll;
//        ImageButton save_ib, like_ib, share_ib;
//
//
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            Title = itemView.findViewById(R.id.texttitle);
////            Desc = itemView.findViewById(R.id.textdesc);
//            RegLDate = itemView.findViewById(R.id.tv_jobLastDate);
//            jobPostDate = itemView.findViewById(R.id.tv_jobPostDate);
////            ExamDate = itemView.findViewById(R.id.textexamdate);
////            offWebSite = itemView.findViewById(R.id.textoffweb);
////            moreInfo = itemView.findViewById(R.id.tv_moreinfo);
//            //withOfficialLink =itemView.findViewById(R.id.tv_fillofficial);
//            withUs = itemView.findViewById(R.id.tv_fillbyusd);
//            save_ib=itemView.findViewById(R.id.ib_save);
//            share_ib=itemView.findViewById(R.id.ib_share);
//            tv_countDays=itemView.findViewById(R.id.tv_countDays);
//
////            expandLL = itemView.findViewById(R.id.expandableLL);
//            touchExpandll = itemView.findViewById(R.id.touchll);
////            l1 = itemView.findViewById(R.id.ll1);
////            l3 = itemView.findViewById(R.id.ll3);
////            l4 = itemView.findViewById(R.id.ll4);
////            save_ib.setTag("save");
////            itemView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    if(mListener!=null){
////                        int position=getAdapterPosition();
////                        String newTitle=Title.getText().toString();
////                        String newDesc=Desc.getText().toString();
////
////
////                        if (position!=RecyclerView.NO_POSITION){
////                            mListener.onItemClick(position,newTitle,newDesc);
////                        }
////                    }
////                }
////            });
//
////            moreInfo.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
//////                    Class_GovtJob user = mDatalistNew.get(getAdapterPosition());
//////                    user.setExpanded(!user.isExpanded());
//////                    notifyItemChanged(getAdapterPosition());
////                }
////            });
////
////            withOfficialLink.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    if(mListener!=null){
////                        int position=getAdapterPosition();
////                        //String offWeb=offWebSite.getText().toString();
////                        class_UserData user = mDatalistNew.get(getAdapterPosition());
////
////                        //String moreInfoLink=moreInfo.getText().toString();
////                        String offwebsite=user.getFormname();
////                        if (position!=RecyclerView.NO_POSITION){
////                            mListener.fillbyOfficialLink(position,offwebsite);
////                        }
////                    }
////                }
////            });
//
//            touchExpandll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        Class_GovtJob govtJob = mDatalistNew.get(getAdapterPosition());
//
//                        String reglastd= govtJob.getLastdate();
//                        String jobPostDate=govtJob.getPosteddate();
//                        String examdate= govtJob.getExamdate();
//                        String admitcdate= govtJob.getAdmitcarddate();
//
//                        String offWeb=govtJob.getOfficialweb();
//                        String offnotflink=govtJob.getOffnotflink();
//                        String moreInfoLink=govtJob.getRegnlink();
//
//                        String jobName = govtJob.getJobname();
//                        String jobPostName = govtJob.getJobpostname();
//                        String jobNoOfPost = govtJob.getNoofpost();
//
//                        String jobAgeLimit = govtJob.getAgelimit();
//                        String jobFees = govtJob.getFees();
//                        String jobEduReq = govtJob.getEducation();
//                        String jobExpReq = govtJob.getExperience();
//                        String jobSelProc = govtJob.getSelectionprocess();
//
//                        //String moreInfoLink=moreInfo.getText().toString();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onMoreInfoClick(position,offWeb,offnotflink,moreInfoLink,
//                                    jobName,jobPostName,jobNoOfPost,
//                                    jobAgeLimit,jobFees,jobEduReq,jobExpReq,jobSelProc,
//                                    reglastd,jobPostDate,examdate,admitcdate);
//                        }
//                    }
//
//                }
//            });
//            withUs.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        String newTitle = Title.getText().toString();
////                        String newDesc = Desc.getText().toString();
//
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position, newTitle);
//                        }
//                    }
//
//                }
//            });
//            share_ib.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//
//                        //String offWeb=offWebSite.getText().toString();
//                        Class_GovtJob govtJob = mDatalistNew.get(getAdapterPosition());
//                        String title = govtJob.getJobtitle();
//                        //String moreInfoLink=moreInfo.getText().toString();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.shareQues(position,title);
//                        }
//                    }
//
//                }
//            });
//
//        }
//
//
//        public void SavedQStatus(int position,String user) {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
//                    .child("Admin")
//                    .child("SavedGovtJob")
//                    .child(String.valueOf(position));
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.child(user).exists()) {
//                        MyViewHolder.this.save_ib.setTag("save_hover");
//                        MyViewHolder.this.save_ib.setImageResource(R.drawable.save_hover);
//
//                    } else {
//                        MyViewHolder.this.save_ib.setTag("save");
//                        MyViewHolder.this.save_ib.setImageResource(R.drawable.save);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//    }
//}
//
//
