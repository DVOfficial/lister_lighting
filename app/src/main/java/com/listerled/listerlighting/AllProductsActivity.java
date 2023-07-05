package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class AllProductsActivity extends AppCompatActivity {

    BottomNavigationView bottom_Navigation;

    RecyclerView latJobs_rv;
    adaptor_ListerData laJobs_Adaptor,registerAdaptorGovtHi;

    List<Class_ListerData> list_latJobs,list_lat2Jobs;

    //    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    SearchView searchView;
    SwipeRefreshLayout swipeRefreshLayout;
    String activity;
    ProgressDialog dialogEng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allproducts);


        Intent intent=getIntent();
        activity =intent.getStringExtra("position");

        Toast.makeText(this,"New Activity="+activity,Toast.LENGTH_SHORT).show();

        latJobs_rv =findViewById(R.id.userQuery_rv);
        searchView =findViewById(R.id.searchView);
        swipeRefreshLayout =findViewById(R.id.swipeRV);
//        latJobs_rv.setVisibility(View.VISIBLE);

        list_latJobs = new ArrayList<>();
//        list_lat2Jobs = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
//        latJobs_rv.setLayoutManager(linearLayoutManager);

//        latJobs_rv.setLayoutManager(new LinearLayoutManager(Main2_Govt_Job_Activity.this));
        laJobs_Adaptor = new adaptor_ListerData(this, list_latJobs);
//        laJobs_Adaptor = new adaptor_ListerData(this, list_lat2Jobs);
        latJobs_rv.setAdapter(laJobs_Adaptor);

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return false;
                }
            });
        }


        laJobs_Adaptor.setOnItemClickListener(new adaptor_ListerData.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String mTitle) {

            }

            @Override
            public void shareQues(String jobId) {

                Toast.makeText(AllProductsActivity.this, "JOBID"+jobId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AllProductsActivity.this,AllProductsActivity.class);
                intent.putExtra("jobId",jobId);
                startActivity(intent);

            }

            @Override
            public void editQues(String jobId) {
                Toast.makeText(AllProductsActivity.this, "JOBID"+jobId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AllProductsActivity.this,AllProductsActivity.class);
                intent.putExtra("jobId",jobId);
                startActivity(intent);
            }

        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
//                new GetDataTaskEng().execute(activity);
                getData();


            }
        });

        if (InternetConnection.checkConnection(getApplicationContext())) {
//            new GetDataTaskEng().execute(activity);
            getData();
        } else {
            Toast.makeText(this, "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
        }

        bottom_Navigation=findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_allproduct);
        bottom_Navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btm_home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_allproduct:

                        return true;
                    case R.id.btm_query:
                        startActivity(new Intent(getApplicationContext(),FetchProductDataActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_profile:
                        startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_settings:
                        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                }
                return false;            }
        });

    }
    private void getData() {
        dialogEng = new ProgressDialog(AllProductsActivity.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Latest Jobs");
        dialogEng.show();
//        String link="https://script.google.com/macros/s/AKfycbxbb1QBtEqMC5S1osrcBQ-ExWCS5UddScCQ96Gqw3-ptLw_YWaY/exec?action=getUAllJobs";
        String link="https://script.google.com/macros/s/AKfycbznBNmLV4H0A_ARDjjOYkaefeF3JTiAWjFVIU0dNuZavYS3sdBAhDTkNsal4Ep0tPQ/exec?action=getUAllJobs";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
    private void parseItems(String jsonResposnce) {
        int newScore;
        String Score1;
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("items");
            Class_ListerData class_LatGovtJob=new Class_ListerData();
            for (int i = 0; i < jarray.length(); i++) {

                JSONObject innerObject = jarray.getJSONObject(i);

//                String Name = jo.getString("Name");
//                String Mobile = jo.getString("Mobile");
//                String EmailId = jo.getString("EmailId");
//                String Date = jo.getString("Date");

//
//
//                class_LatGovtJob.setName(Name);
//                class_LatGovtJob.setPhoneno(Mobile);
//                class_LatGovtJob.setEmailid(EmailId);
//                class_LatGovtJob.setDatetime(Date);
//                Class_ListerData class_LatGovtJob=new Class_ListerData();
                /**
                 * Getting Inner Object from contacts array...
                 * and
                 * From that We will get Name of that Contact
                 *
                 */
//                JSONObject innerObject = array.getJSONObject(jIndexEng);

                String SrNo = innerObject.getString(Keys_secret.SrNo);
                String ModelName = innerObject.getString(Keys_secret.ModelName);
                String Color = innerObject.getString(Keys_secret.Color);
                String Brand = innerObject.getString(Keys_secret.Brand);

                String OpeningStock = innerObject.getString(Keys_secret.OpeningStock);
                String InQty = innerObject.getString(Keys_secret.InQty);
                String Out = innerObject.getString(Keys_secret.Out);
                String CurrentStock = innerObject.getString(Keys_secret.CurrentStock);

                String Location = innerObject.getString(Keys_secret.Location);
                String ImageLink = innerObject.getString(Keys_secret.ImageLink);
                String Date = innerObject.getString(Keys_secret.Date);

                class_LatGovtJob.setSno(SrNo);
                class_LatGovtJob.setModelName(ModelName);
                class_LatGovtJob.setCOLOR(Color);
                class_LatGovtJob.setBRAND(Brand);
                class_LatGovtJob.setOPENINGSTOCK(OpeningStock);
                class_LatGovtJob.setINQTY(InQty);
                class_LatGovtJob.setOut(Out);
                class_LatGovtJob.setCURRENTSTOCK(CurrentStock);
                class_LatGovtJob.setLocation(Location);
                class_LatGovtJob.setImageLink(ImageLink);
                class_LatGovtJob.setDate(Date);

//                                    class_LatGovtJob.setLogo(Logo);
                /**
                 * Adding name and phone concatenation in List...
                 */
                //list.add(model);
//                list_latJobs.add(class_LatGovtJob);

//                list.add(item);


                list_latJobs.add(class_LatGovtJob);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        adapter = new SimpleAdapter(this,list,R.layout.list_item,
//                new String[]{"Name","Mobile","EmailId","Date"},new int[]{R.id.name,R.id.mobile,R.id.emailid,R.id.datetime});
//        listview.setAdapter(adapter);

//        progressBarDel.setVisibility(View.GONE);


        if(list_latJobs.size() > 0) {

            laJobs_Adaptor.notifyDataSetChanged();
        } else {
//                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
            Toast.makeText(AllProductsActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
        dialogEng.dismiss();
    }
//
//    class GetDataTaskEng extends AsyncTask<String, Void, Void> {
//
//        ProgressDialog dialogEng;
//        int jIndexEng;
//        int xEng;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            /**
//             * Progress Dialog for User Interaction
//             */
//
////            x=list.size();
////
////            if(x==0)
////                jIndex=0;
////            else
////                jIndex=x;
//
//            dialogEng = new ProgressDialog(Main2_AllUser_GovtJob_Activity.this);
//            dialogEng.setTitle("Wait Please...");
//            dialogEng.setMessage("Loading All User Jobs");
//            dialogEng.show();
//        }
//
//        @Nullable
//        @Override
//        protected Void doInBackground(String... s) {
//            String s1 = s[0]; //="String1";
//
//            /**
//             * Getting JSON Object from Web Using okHttp
//             //             */
//            JSONObject jsonObject = null;
//            String key = null;
////            if (s1.equals("allgovtjobupdate")){
//                jsonObject = JSONParser.getDataFromUSERJOBUPDATE();
//                key=Keys.KEY_USER_JOB_UPDATE;
////            }else if (s1.equals("latjobUpdate")){
////                jsonObject = JSONParser.getLatestEnggDataWeb();
////                key=Keys.KEY_LATESTJOBS;
////            }
////            JSONObject jsonObject = JSONParser.getLatestEnggDataWeb();
//            try {
//                /**
//                 * Check Whether Its NULL???
//                 */
//                if (jsonObject != null) {
//                    /**
//                     * Check Length...
//                     */
//                    if(jsonObject.length() > 0) {
//                        /**
//                         * Getting Array named "contacts" From MAIN Json Object
//                         */
//                        JSONArray array = jsonObject.getJSONArray(key);
//
//                        /**
//                         * Check Length of Array...
//                         */
//                        int lenArray = array.length();
//                        if(lenArray > 0) {
//                            for( ; jIndexEng < lenArray; jIndexEng++) {
//                                /**
//                                 * Creating Every time New Object
//                                 * and
//                                 * Adding into List
//                                 */
////                                MyDataModel model = new MyDataModel();
//                                Class_ListerData class_LatGovtJob=new Class_ListerData();
//                                /**
//                                 * Getting Inner Object from contacts array...
//                                 * and
//                                 * From that We will get Name of that Contact
//                                 *
//                                 */
//                                JSONObject innerObject = array.getJSONObject(jIndexEng);
////                                UserName
////                                        NoOfPostCategory
////                                EducationCategory
////                                        StateCentralCategory
////                                DepartmentCategory
////                                        Job_Company_Name
////                                Post_Name_Short
////                                        Post_Name_Long
////                                No_of_Post
//
////                                String UserName = innerObject.getString(Keys.UserName);
////                                String Date = innerObject.getString(Keys.Date);
//                                String JobId = innerObject.getString(Keys.JobId);
////                                String NoOfPostCategory = innerObject.getString(Keys.NoOfPostCategory);
////                                String EducationCategory = innerObject.getString(Keys.EducationCategory);
////                                String StateCentralCategory = innerObject.getString(Keys.StateCentralCategory);
////                                String DepartmentCategory = innerObject.getString(Keys.DepartmentCategory);
//
////                                String Job_Company_Name = innerObject.getString(Keys.Job_Company_Name);
////                                String Job_Description = innerObject.getString(Keys.Job_Description);
////                                String Post_Name_Short = innerObject.getString(Keys.Post_Name_Short);
////                                String jobPostNameS = innerObject.getString(Keys.JOB_POSTNAMESHORT);
////                                String Post_Name_Long = innerObject.getString(Keys.Post_Name_Long);
////                                String No_of_Post = innerObject.getString(Keys.No_of_Post);
//
////                                Job_Title_Combined
////                                        Age_Limit
////                                Fees
////                                        Education_Short
////                                Education_Long
////                                        Experience_Required
////                                Selection_Process
////                                        Salary
//
//                                String Job_Title_Combined = innerObject.getString(Keys.Job_Title_Combined);
////                                String Age_Limit = innerObject.getString(Keys.Age_Limit);
////                                String Fees = innerObject.getString(Keys.Fees);
////                                String Education_Short = innerObject.getString(Keys.Education_Short);
////                                String  Education_Long= innerObject.getString(Keys.Education_Long);
////                                String Experience_Required = innerObject.getString(Keys.Experience_Required);
////                                String Selection_Process = innerObject.getString(Keys.Selection_Process);
////                                String Salary = innerObject.getString(Keys.Salary);
//////                                FFO_Posted_Date
//////                                        Registration_Start_Date
//////                                Registration_Last_Date
//////                                        Registration_Last_Date_Custom
//////                                Admit_Card_Date
//////                                        Exam_Date
//                                String FFO_Posted_Date = innerObject.getString(Keys.FFO_Posted_Date);
////                                String Registration_Start_Date = innerObject.getString(Keys.Registration_Start_Date);
//                                String Registration_Last_Date = innerObject.getString(Keys.Registration_Last_Date);
//                                String Registration_Last_Date_Custom = innerObject.getString(Keys.Registration_Last_Date_Custom);
////                                String Admit_Card_Date = innerObject.getString(Keys.Admit_Card_Date);
////                                String Exam_Date = innerObject.getString(Keys.Exam_Date);
//
////                                Official_Website
////                                        Notification_Link
////                                Registration_Link
////                                        Copy_Website_Link
////
////                                String Official_Website = innerObject.getString(Keys.Official_Website);
////                                String Notification_Link = innerObject.getString(Keys.Notification_Link);
////                                String Registration_Link = innerObject.getString(Keys.Registration_Link);
////                                String Copy_Website_Link = innerObject.getString(Keys.Copy_Website_Link);
//
//                                /**
//                                 * Getting Object from Object "phone"
//                                 */
//                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
//                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);
//
////                                model.setName(jobTitle+jobLastDate+jobofficialweb);
////                                model.setCountry(jobDesc+jobExamDate+jobOfficialNoti);
////                                UserName
////                                        NoOfPostCategory
////                                EducationCategory
////                                        StateCentralCategory
////                                DepartmentCategory
////                                        Job_Company_Name
////                                Post_Name_Short
////                                        Post_Name_Long
////                                No_of_Post
////                                        Job_Title_Combined
//
////                                class_LatGovtJob.setUserName(UserName);
////                                class_LatGovtJob.setDate(Date);
////                                class_LatGovtJob.setJobId(JobId);
////                                class_LatGovtJob.setNoOfPostCategory(NoOfPostCategory);
////                                class_LatGovtJob.setEducationCategory(EducationCategory);
////                                class_LatGovtJob.setStateCentralCategory(StateCentralCategory);
////                                class_LatGovtJob.setDepartmentCategory(DepartmentCategory);
////
////                                class_LatGovtJob.setJob_Company_Name(Job_Company_Name);
////                                class_LatGovtJob.setJob_Company_Name(Job_Description);
////                                class_LatGovtJob.setPost_Name_Short(Post_Name_Short);
////                                class_LatGovtJob.setPost_Name_Long(Post_Name_Long);
////                                class_LatGovtJob.setNo_of_Post(No_of_Post);
//
//                                class_LatGovtJob.setJob_Title_Combined(Job_Title_Combined);
//                                class_LatGovtJob.setJobId(JobId);
//                                class_LatGovtJob.setRegistration_Last_Date(Registration_Last_Date);
//                                class_LatGovtJob.setRegistration_Last_Date_Custom(Registration_Last_Date_Custom);
//
////                                Age_Limit
////                                        Fees
////                                Education_Short
////                                        Education_Long
////                                Experience_Required
////                                        Selection_Process
////                                Salary
//
////                                class_LatGovtJob.setAge_Limit(Age_Limit);
////                                class_LatGovtJob.setFees(Fees);
////                                class_LatGovtJob.setEducation_Short(Education_Short);
////                                class_LatGovtJob.setEducation_Long(Education_Long);
////                                class_LatGovtJob.setExperience_Required(Experience_Required);
////                                class_LatGovtJob.setSelection_Process(Selection_Process);
////                                class_LatGovtJob.setSalary(Salary);
////
//////                                FFO_Posted_Date
//////                                        Registration_Start_Date
//////                                Registration_Last_Date
//////                                        Registration_Last_Date_Custom
//////                                Admit_Card_Date
//////                                        Exam_Date
//////                                Official_Website
//////                                        Notification_Link
//////                                Registration_Link
//////                                        Copy_Website_Link
////
//                                class_LatGovtJob.setFFO_Posted_Date(FFO_Posted_Date);
////                                class_LatGovtJob.setRegistration_Start_Date(Registration_Start_Date);
////                                class_LatGovtJob.setRegistration_Last_Date(Registration_Last_Date);
////                                class_LatGovtJob.setRegistration_Last_Date_Custom(Registration_Last_Date_Custom);
////                                class_LatGovtJob.setAdmit_Card_Date(Admit_Card_Date);
////                                class_LatGovtJob.setExam_Date(Exam_Date);
////
////                                class_LatGovtJob.setOfficial_Website(Official_Website);
////                                class_LatGovtJob.setNotification_Link(Notification_Link);
////                                class_LatGovtJob.setRegistration_Link(Registration_Link);
////                                class_LatGovtJob.setCopy_Website_Link(Copy_Website_Link);
//
//                                /**
//                                 * Adding name and phone concatenation in List...
//                                 */
//                                //list.add(model);
//                                list_latJobs.add(class_LatGovtJob);
//                            }
//                        }
//                    }
//                }
//            } catch (JSONException je) {
//                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            dialogEng.dismiss();
//            /**
//             * Checking if List size if more than zero then
//             * Update ListView
//             */
//
////            if (s1.equals("allgovtjobupdate")){
////
////            }else if (s1.equals("latjobUpdate")){
////
////            }
//
//
//
//            if(list_latJobs.size() > 0) {
////            Date c = Calendar.getInstance().getTime();
////            SimpleDateFormat cd = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
////            String todayDate = cd.format(c);
////
////            @SuppressLint("SimpleDateFormat") SimpleDateFormat ld=new SimpleDateFormat("dd-MM-yyyy");
////            long diff=-1;
////            try{
////                Date ldate=ld.parse(reglastd);
////                Date cdate=ld.parse(todayDate);
////
////                diff=Math.round((ldate.getTime()-cdate.getTime())/(double)86400000);
////            } catch (ParseException e) {
////                e.printStackTrace();
////            }
////
////
////                startQuiz_List.clear();
//////                l = new ArrayList<>();
////
////                startQuiz_List=new ArrayList<>();
////                for (int i=1;i<list_latJobs.size();i++){
////                    list_lat2Jobs.add(new Class_ListerData());
////                }
////                laJobs_Adaptor = new adaptor_ListerData(Main2_Govt_Job_Activity.this, startQuiz_List);
////                latJobs_rv.setAdapter(laJobs_Adaptor);
//
//                laJobs_Adaptor.notifyDataSetChanged();
//            } else {
////                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
//                Toast.makeText(Main2_AllUser_GovtJob_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public void search(String newText) {
        ArrayList<Class_ListerData> listSearchQues=new ArrayList<>();
        for (Class_ListerData classUserSearch: list_latJobs){
            if (classUserSearch.getModelName().toLowerCase().contains(newText.toLowerCase())){
                listSearchQues.add(classUserSearch);
            }
        }
        adaptor_ListerData adapSearchJob= new adaptor_ListerData(this,listSearchQues);
        latJobs_rv.setAdapter(adapSearchJob);
    }


    public void VerifyDialogET() {

        Toast.makeText(AllProductsActivity.this, "Touch Successfully", Toast.LENGTH_LONG).show();
    }

}