package com.listerled.listerlighting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class adaptor_LatestAdminJobs extends RecyclerView.Adapter<adaptor_LatestAdminJobs.MyViewHolder> {

    private Context context;
    private List<Class_GovtJob> mDatalistNew;
    private OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position, String mtextModelName);

        void shareQues(String jobId);

        void editQues(String jobId);
        //void fillbyOfficialLink(int position, String offWeb);

//        void shareQues(int position, String question);

//        void shareQues(int position, String offWeb, String offnotflink, String moreInfoLink, String jobName, String jobPostName, String jobNoOfPost, String jobAgeLimit, String jobFees, String jobEduReq, String jobExpReq, String jobSelProc, String reglastd, String jobPostDate, String examdate, String admitcdate);


//        void onWebLinkClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public adaptor_LatestAdminJobs(Context context, List<Class_GovtJob> mDatalistNew) {
        this.context = context;
        this.mDatalistNew = mDatalistNew;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.list_item_jobs_drive1, parent, false);
        return new  MyViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Class_GovtJob govtJob = mDatalistNew.get(position);

        String textModelName = govtJob.getDate();

        String color= govtJob.getJobId();
//        String jobdisplaylastd= govtJob.getRegistration_Last_Date_Custom();
        String brand= govtJob.getUserName();
        String quantity= govtJob.getDepartmentCategory();


//        String admitcdate= govtJob.getAdmitcarddate();
//
//        String offWeb=govtJob.getOfficialweb();
//        String offnotflink=govtJob.getOffnotflink();
//        String moreInfoLink=govtJob.getOffnotflink();
//
//        String jobName = govtJob.getJobname();
//        String jobPostName = govtJob.getJobpostname();
//        String jobNoOfPost = govtJob.getNoofpost();
//
//        String jobAgeLimit = govtJob.getAgelimit();
//        String jobFees = govtJob.getFees();
//        String jobEduReq = govtJob.getEducation();
//        String jobExpReq = govtJob.getExperience();
//        String jobSelProc = govtJob.getSelectionprocess();

//        Date c = Calendar.getInstance().getTime();
//        SimpleDateFormat cd = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//        String todayDate = cd.format(c);

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
//        final String userID=mCurrentUser.getUid();

//        Boolean isExpanded=mDatalistNew.get(position).isExpanded();
//        holder.expandLL.setVisibility(isExpanded?View.VISIBLE:View.GONE);

        if (textModelName != null) {
            holder.textModelName.setPaintFlags(holder.textModelName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.textModelName.setText(textModelName);
        }else{
            holder.textModelName.setVisibility(View.GONE);
        }


        if (color != null) {
//            String boldText = "Fees : ";
//
//            SpannableString str = new SpannableString(boldText + fees);
//            str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
            holder.tv_Color.setPaintFlags(holder.tv_Color.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.tv_Color.setText(color );
        }else{
            holder.tv_Brand.setVisibility(View.GONE);
        }

        if (brand != null) {
//            String boldText = "Fees : ";
//
//            SpannableString str = new SpannableString(boldText + fees);
//            str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
            holder.tv_Brand.setPaintFlags(holder.tv_Brand.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.tv_Brand.setText(brand );
        }else{
            holder.tv_Color.setVisibility(View.GONE);
        }
        if (quantity != null) {
//            String boldText = "Fees : ";
//
//            SpannableString str = new SpannableString(boldText + fees);
//            str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
            holder.tv_CurrStock.setPaintFlags(holder.tv_CurrStock.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.tv_Color.setText(quantity );
        }else{
            holder.tv_Color.setVisibility(View.GONE);
        }


//
//        if (desc != null) {
//            holder.Desc.setText("Recruitment of "+desc);
//        }else{
//            holder.Desc.setVisibility(View.GONE);
//        }
//        if (post != null) {
//            holder.textPost.setPaintFlags(holder.textPost.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            holder.textPost.setText("No of Posts : "+post);
//        }else{
//            holder.textPost.setVisibility(View.GONE);
//        }
//
////
////        if (jobPostDate != null) {
////            holder.jobPostDate.setText(jobPostDate);
////        }else{
////            holder.jobPostDate.setVisibility(View.GONE);
////        }
//
//        if (education != null) {
//            String boldText = "Education : ";
//
//            SpannableString str = new SpannableString(boldText + education);
//            str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            holder.tv_Educ.setText(str);
////            holder.tv_Educ.setText(educt);
//        }else{
//            holder.tv_Educ.setVisibility(View.GONE);
//        }
//
//        if (fees != null) {
//            String boldText = "Fees : ";
//
//            SpannableString str = new SpannableString(boldText + fees);
//            str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            holder.tv_Fees.setText(str);
//        }else{
//            holder.tv_Fees.setVisibility(View.GONE);
//        }

//        if (reglastd != null) {
//            holder.RegLDate.setPaintFlags(holder.RegLDate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            holder.RegLDate.setText(reglastd);
//        }else{
//            holder.RegLDate.setVisibility(View.GONE);
//        }

//        if (examdate != null) {
//            holder.ExamDate.setText(examdate);
//        }else{
//            holder.ExamDate.setVisibility(View.GONE);
//            holder.l3.setVisibility(View.GONE);
//        }
//        holder.tv_countDays.setText(diff+" Days\nRemaining");
//            holder.offWebSite.setText(Html.fromHtml("<b>Click on link below to Apply from official website\n</b>"+ offWeb));
//        if (moreInfoLink != null) {
//            holder.moreInfo.setText(moreInfoLink);
//        }else{
//            holder.moreInfo.setVisibility(View.GONE);
//        }

//        holder.SavedQStatus(position,userID);
//        holder.save_ib.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mListener != null) {
//                    if (position != RecyclerView.NO_POSITION) {
//                        //mListener.dislikeAns();
//                        //String tag = String.valueOf(v.getTag());
//                        // int id = (int) dislike_ib.getTag();
//                        if (holder.save_ib.getTag().equals("save")) {
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("Admin")
//                                    .child("SavedGovtJob1")
//                                    .child(String.valueOf(position))
//                                    .child(userID).setValue(true);
//
//                        } else {
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("Admin")
//                                    .child("SavedGovtJob1")
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

    }

    @Override
    public int getItemCount() {
        return mDatalistNew.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textModelName, Desc,textPost, RegLDate, AdmitCDate, ExamDate, offWebSite, tv_Educ, tv_Fees;
        TextView moreInfo, withOfficialLink, tv_CurrStock,jobPostDate, tv_Brand, tv_Color;
        LinearLayout l1, l3, l4, expandLL, touchExpandll;
//        ImageButton save_ib, like_ib;
//        ImageView imageV;
//        Button ib_Edit;
//        CardView cv_joblayout;
//        ImageButton ib_share;



        public MyViewHolder(View itemView) {
            super(itemView);

            textModelName = itemView.findViewById(R.id.textModelName);

            tv_Brand = itemView.findViewById(R.id.textBrand);


            tv_Color = itemView.findViewById(R.id.textColor);

            tv_CurrStock = itemView.findViewById(R.id.textCurrStock);

            //            RegLDate = itemView.findViewById(R.id.tv_jobLastDate);
//            jobPostDate = itemView.findViewById(R.id.tv_jobPostDate);
            //            ExamDate = itemView.findViewById(R.id.textexamdate);
//            offWebSite = itemView.findViewById(R.id.textoffweb);
//            moreInfo = itemView.findViewById(R.id.tv_moreinfo);
            //withOfficialLink =itemView.findViewById(R.id.tv_fillofficial);
//            cv_joblayout = itemView.findViewById(R.id.cv_joblayout);
//            save_ib = itemView.findViewById(R.id.ib_save);
//            ib_share = itemView.findViewById(R.id.ib_share);
//            ib_Edit = itemView.findViewById(R.id.ib_Edit);
//            imageV=itemView.findViewById(R.id.imageV);

//            tv_countDays = itemView.findViewById(R.id.tv_countDays);

//            expandLL = itemView.findViewById(R.id.expandableLL);
//            touchExpandll = itemView.findViewById(R.id.touchll);
//            l1 = itemView.findViewById(R.id.ll1);
//            l3 = itemView.findViewById(R.id.ll3);
//            l4 = itemView.findViewById(R.id.ll4);
//            save_ib.setTag("save");
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(mListener!=null){
//                        int position=getAdapterPosition();
//                        String newtextModelName=textModelName.getText().toString();
//                        String newDesc=Desc.getText().toString();
//
//
//                        if (position!=RecyclerView.NO_POSITION){
//                            mListener.onItemClick(position,newtextModelName,newDesc);
//                        }
//                    }
//                }
//            });

//            moreInfo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Class_GovtJob user = mDatalistNew.get(getAdapterPosition());
////                    user.setExpanded(!user.isExpanded());
////                    notifyItemChanged(getAdapterPosition());
//                }
//            });
//
//            withOfficialLink.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(mListener!=null){
//                        int position=getAdapterPosition();
//                        //String offWeb=offWebSite.getText().toString();
//                        class_UserData user = mDatalistNew.get(getAdapterPosition());
//
//                        //String moreInfoLink=moreInfo.getText().toString();
//                        String offwebsite=user.getFormname();
//                        if (position!=RecyclerView.NO_POSITION){
//                            mListener.fillbyOfficialLink(position,offwebsite);
//                        }
//                    }
//                }
//            });

//            touchExpandll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        Class_GovtJob govtJob = mDatalistNew.get(getAdapterPosition());
//
////                        String jobCtextModelName = govtJob.getJob_textModelName_Combined();
////                        String jobInputDate = govtJob.getDate();
//                        String jobId = govtJob.getJobId();
////
////                        String jobName = govtJob.getJob_Company_Name();
////                        String jobPostNameS = govtJob.getPost_Name_Short();
////                        String jobPostNameL = govtJob.getPost_Name_Long();
////                        String jobNoOfPost = govtJob.getNo_of_Post();
////
////                        String jobAgeLimit = govtJob.getAge_Limit();
////                        String jobFees = govtJob.getFees();
////                        String jobEduReqS = govtJob.getEducation_Short();
////                        String jobEduReqL = govtJob.getEducation_Long();
////                        String jobExpReq = govtJob.getExperience_Required();
////                        String jobSelProc = govtJob.getSelection_Process();
////                        String jobSal = govtJob.getSalary();
////
////                        String jobFFOPostedDate= govtJob.getFFO_Posted_Date();
////                        String jobRegSDate=govtJob.getRegistration_Start_Date();
////                        String jobRegEDateS= govtJob.getRegistration_Last_Date();
////                        String jobRegEDateL= govtJob.getRegistration_Last_Date_Custom();
////                        String jobAdmitCDate= govtJob.getAdmit_Card_Date();
////                        String jobExamDate= govtJob.getExam_Date();
////
////                        String offWeb=govtJob.getOfficial_Website();
////                        String offnotflink=govtJob.getNotification_Link();
////                        String moreInfoLink=govtJob.getRegistration_Link();
//
//                        //String offWeb=offWebSite.getText().toString();
//                        //String moreInfoLink=moreInfo.getText().toString();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.shareQues(jobId);
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
//                        String newtextModelName = textModelName.getText().toString();
////                        String newDesc = Desc.getText().toString();
//
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position, newtextModelName);
//                        }
//                    }
//
//                }
//            });

//
//            ib_share.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        Class_GovtJob govtJob = mDatalistNew.get(getAdapterPosition());
//
////                        String jobCtextModelName = govtJob.getJob_textModelName_Combined();
////                        String jobInputDate = govtJob.getDate();
//                        String jobId = govtJob.getJobId();
////
////                        String jobName = govtJob.getJob_Company_Name();
////                        String jobPostNameS = govtJob.getPost_Name_Short();
////                        String jobPostNameL = govtJob.getPost_Name_Long();
////                        String jobNoOfPost = govtJob.getNo_of_Post();
////
////                        String jobAgeLimit = govtJob.getAge_Limit();
////                        String jobFees = govtJob.getFees();
////                        String jobEduReqS = govtJob.getEducation_Short();
////                        String jobEduReqL = govtJob.getEducation_Long();
////                        String jobExpReq = govtJob.getExperience_Required();
////                        String jobSelProc = govtJob.getSelection_Process();
////                        String jobSal = govtJob.getSalary();
////
////                        String jobFFOPostedDate= govtJob.getFFO_Posted_Date();
////                        String jobRegSDate=govtJob.getRegistration_Start_Date();
////                        String jobRegEDateS= govtJob.getRegistration_Last_Date();
////                        String jobRegEDateL= govtJob.getRegistration_Last_Date_Custom();
////                        String jobAdmitCDate= govtJob.getAdmit_Card_Date();
////                        String jobExamDate= govtJob.getExam_Date();
////
////                        String offWeb=govtJob.getOfficial_Website();
////                        String offnotflink=govtJob.getNotification_Link();
////                        String moreInfoLink=govtJob.getRegistration_Link();
//
//                        //String offWeb=offWebSite.getText().toString();
//                        //String moreInfoLink=moreInfo.getText().toString();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.shareQues(jobId);
//                        }
//                    }
//                }
//            });
//
//            ib_Edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        Class_GovtJob govtJob = mDatalistNew.get(getAdapterPosition());
//
////                        String jobCtextModelName = govtJob.getJob_textModelName_Combined();
////                        String jobInputDate = govtJob.getDate();
//                        String jobId = govtJob.getJobId();
////
////                        String jobName = govtJob.getJob_Company_Name();
////                        String jobPostNameS = govtJob.getPost_Name_Short();
////                        String jobPostNameL = govtJob.getPost_Name_Long();
////                        String jobNoOfPost = govtJob.getNo_of_Post();
////
////                        String jobAgeLimit = govtJob.getAge_Limit();
////                        String jobFees = govtJob.getFees();
////                        String jobEduReqS = govtJob.getEducation_Short();
////                        String jobEduReqL = govtJob.getEducation_Long();
////                        String jobExpReq = govtJob.getExperience_Required();
////                        String jobSelProc = govtJob.getSelection_Process();
////                        String jobSal = govtJob.getSalary();
////
////                        String jobFFOPostedDate= govtJob.getFFO_Posted_Date();
////                        String jobRegSDate=govtJob.getRegistration_Start_Date();
////                        String jobRegEDateS= govtJob.getRegistration_Last_Date();
////                        String jobRegEDateL= govtJob.getRegistration_Last_Date_Custom();
////                        String jobAdmitCDate= govtJob.getAdmit_Card_Date();
////                        String jobExamDate= govtJob.getExam_Date();
////
////                        String offWeb=govtJob.getOfficial_Website();
////                        String offnotflink=govtJob.getNotification_Link();
////                        String moreInfoLink=govtJob.getRegistration_Link();
//
//                        //String offWeb=offWebSite.getText().toString();
//                        //String moreInfoLink=moreInfo.getText().toString();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.editQues(jobId);
//                        }
//                    }
//                }
//            });

        }

//        public void SavedQStatus(int position, final String user) {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
//                    .child("Admin")
//                    .child("SavedGovtJob1")
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
    }
}


