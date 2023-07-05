package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FetchProductDataActivity extends AppCompatActivity {

    BottomNavigationView bottom_Navigation;

    RecyclerView latJobs_rv;

    adaptor_LatestAdminJobs laJobs_Adaptor,registerAdaptorGovtHi;

    List<Class_GovtJob> list_latJobs,list_lat2Jobs;

    //    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    SearchView searchView;
    SwipeRefreshLayout swipeRefreshLayout;
    String activity;
    ProgressDialog dialogEng;

//    ActivityFetchProductDataBinding binding;
    private listeradaptor lstadaptor;
//    binding = FragmentHousesBinding.inflate(inflater, container, false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchproductdata);

//        binding = FetchProductDataActivity.inflate(inflater, container, false);

        Intent intent=getIntent();
        activity =intent.getStringExtra("position");

        Toast.makeText(this,"New Activity="+activity,Toast.LENGTH_SHORT).show();

        latJobs_rv =findViewById(R.id.userQuery_rv);
        searchView =findViewById(R.id.searchView);
        swipeRefreshLayout =findViewById(R.id.swipeRV);
        latJobs_rv.setVisibility(View.VISIBLE);

        list_latJobs = new ArrayList<>();
//        list_lat2Jobs = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        latJobs_rv.setLayoutManager(linearLayoutManager);

//        latJobs_rv.setLayoutManager(new LinearLayoutManager(Main2_Govt_Job_Activity.this));
        laJobs_Adaptor = new adaptor_LatestAdminJobs(this, list_latJobs);
//        laJobs_Adaptor = new adaptor_LatestAdminJobs(this, list_lat2Jobs);
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


        laJobs_Adaptor.setOnItemClickListener(new adaptor_LatestAdminJobs.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String mTitle) {

            }

            @Override
            public void shareQues(String jobId) {

                Toast.makeText(FetchProductDataActivity.this, "JOBID"+jobId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(FetchProductDataActivity.this,FetchProductDataActivity.class);
                intent.putExtra("jobId",jobId);
                startActivity(intent);

            }

            @Override
            public void editQues(String jobId) {
                Toast.makeText(FetchProductDataActivity.this, "JOBID"+jobId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(FetchProductDataActivity.this,FetchProductDataActivity.class);
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
        bottom_Navigation.setSelectedItemId(R.id.btm_query);
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
                        startActivity(new Intent(getApplicationContext(),AllProductsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_query:

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

        dialogEng = new ProgressDialog(FetchProductDataActivity.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Latest Jobs");
        dialogEng.show();
        String link="https://script.google.com/macros/s/AKfycbxbb1QBtEqMC5S1osrcBQ-ExWCS5UddScCQ96Gqw3-ptLw_YWaY/exec?action=get";

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Sno = jsonObject.getString(Keys.KEY_Sno);
                        String ModelName = jsonObject.getString(Keys.KEY_ModelName);
                        String COLOR = jsonObject.getString(Keys.KEY_COLOR);
                        String BRAND = jsonObject.getString(Keys.KEY_BRAND);

                        String OPENINGSTOCK = jsonObject.getString(Keys.KEY_OPENINGSTOCK);
                        String INQTY = jsonObject.getString(Keys.KEY_INQTY);
                        String Out = jsonObject.getString(Keys.KEY_Out);
                        String CURRENTSTOCK = jsonObject.getString(Keys.KEY_CURRENTSTOCK);

                        String Location = jsonObject.getString(Keys.KEY_Location);
                        String ImageLink = jsonObject.getString(Keys.KEY_ImageLink);
                        String Date = jsonObject.getString(Keys.KEY_Date);

                        Class_ListerData class_listerData=new Class_ListerData(Sno,ModelName,COLOR,BRAND,OPENINGSTOCK,
                                INQTY,Out, CURRENTSTOCK,Location,ImageLink,Date);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

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
            Class_GovtJob class_LatGovtJob=new Class_GovtJob();
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
//                Class_GovtJob class_LatGovtJob=new Class_GovtJob();
                /**
                 * Getting Inner Object from contacts array...
                 * and
                 * From that We will get Name of that Contact
                 *
                 */
//                JSONObject innerObject = array.getJSONObject(jIndexEng);

                String SrNo = innerObject.getString(Keys.SrNo);
                String Date = innerObject.getString(Keys.Date);
                String JobId = innerObject.getString(Keys.JobId);
                String UserName = innerObject.getString(Keys.UserName);

                String NoOfPostCategory = innerObject.getString(Keys.NoOfPostCategory);
                String EducationCategory = innerObject.getString(Keys.EducationCategory);
                String StateCentralCategory = innerObject.getString(Keys.StateCentralCategory);
                String DepartmentCategory = innerObject.getString(Keys.DepartmentCategory);

                String Job_Company_Name = innerObject.getString(Keys.Job_Company_Name);
                String Job_Description = innerObject.getString(Keys.Job_Description);
                String Post_Name_Short = innerObject.getString(Keys.Post_Name_Short);
//                                String jobPostNameS = innerObject.getString(Keys.JOB_POSTNAMESHORT);
                String Post_Name_Long = innerObject.getString(Keys.Post_Name_Long);
                String No_of_Post = innerObject.getString(Keys.No_of_Post);

//                                Job_Title_Combined
//                                        Age_Limit
//                                Fees
//                                        Education_Short
//                                Education_Long
//                                        Experience_Required
//                                Selection_Process
//                                        Salary

                String Job_Title_Combined = innerObject.getString(Keys.Job_Title_Combined);
                String Age_Limit = innerObject.getString(Keys.Age_Limit);
                String Fees = innerObject.getString(Keys.Fees);
                String Education_Short = innerObject.getString(Keys.Education_Short);
                String  Education_Long= innerObject.getString(Keys.Education_Long);
                String Experience_Required = innerObject.getString(Keys.Experience_Required);
                String Selection_Process = innerObject.getString(Keys.Selection_Process);
                String Salary = innerObject.getString(Keys.Salary);
                String Who_Can_Apply = innerObject.getString(Keys.Who_Can_Apply);
//                                FFO_Posted_Date
//                                        Registration_Start_Date
//                                Registration_Last_Date
//                                        Registration_Last_Date_Custom
//                                Admit_Card_Date
//                                        Exam_Date
                String FFO_Posted_Date = innerObject.getString(Keys.FFO_Posted_Date);
                String Registration_Start_Date = innerObject.getString(Keys.Registration_Start_Date);
                String Registration_Last_Date = innerObject.getString(Keys.Registration_Last_Date);
                String Registration_Last_Date_Custom = innerObject.getString(Keys.Registration_Last_Date_Custom);
                String Admit_Card_Date = innerObject.getString(Keys.Admit_Card_Date);
                String Exam_Date = innerObject.getString(Keys.Exam_Date);

//                                Official_Website
//                                        Notification_Link
//                                Registration_Link
//                                        Copy_Website_Link
//
                String Official_Website = innerObject.getString(Keys.Official_Website);
                String Notification_Link = innerObject.getString(Keys.Notification_Link);
                String Registration_Link = innerObject.getString(Keys.Registration_Link);
                String Registration_Link2 = innerObject.getString(Keys.Registration_Link2);
                String Copy_Website_Link = innerObject.getString(Keys.Copy_Website_Link);

                String CompanyImage_Link = innerObject.getString(Keys.CompanyImage_Link);
                String Status = innerObject.getString(Keys.Status);
//                                    String Logo = innerObject.getString(Keys.Logo);

                /**
                 * Getting Object from Object "phone"
                 */
                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                //String phone = phoneObject.getString(Keys.KEY_MOBILE);

//                                model.setName(jobTitle+jobLastDate+jobofficialweb);
//                                model.setCountry(jobDesc+jobExamDate+jobOfficialNoti);


                class_LatGovtJob.setSrNo(SrNo);
                class_LatGovtJob.setDate(Date);
                class_LatGovtJob.setJobId(JobId);
                class_LatGovtJob.setUserName(UserName);

                class_LatGovtJob.setNoOfPostCategory(NoOfPostCategory);
                class_LatGovtJob.setEducationCategory(EducationCategory);
                class_LatGovtJob.setStateCentralCategory(StateCentralCategory);
                class_LatGovtJob.setDepartmentCategory(DepartmentCategory);

                class_LatGovtJob.setJob_Company_Name(Job_Company_Name);
                class_LatGovtJob.setJob_Description(Job_Description);
                class_LatGovtJob.setPost_Name_Short(Post_Name_Short);
                class_LatGovtJob.setPost_Name_Long(Post_Name_Long);
                class_LatGovtJob.setNo_of_Post(No_of_Post);
                class_LatGovtJob.setJob_Title_Combined(Job_Title_Combined);

//                                Age_Limit
//                                        Fees
//                                Education_Short
//                                        Education_Long
//                                Experience_Required
//                                        Selection_Process
//                                Salary

                class_LatGovtJob.setAge_Limit(Age_Limit);
                class_LatGovtJob.setFees(Fees);
                class_LatGovtJob.setEducation_Short(Education_Short);
                class_LatGovtJob.setEducation_Long(Education_Long);
                class_LatGovtJob.setExperience_Required(Experience_Required);
                class_LatGovtJob.setSelection_Process(Selection_Process);
                class_LatGovtJob.setSalary(Salary);
                class_LatGovtJob.setWho_Can_Apply(Who_Can_Apply);

//                                FFO_Posted_Date
//                                        Registration_Start_Date
//                                Registration_Last_Date
//                                        Registration_Last_Date_Custom
//                                Admit_Card_Date
//                                        Exam_Date
//                                Official_Website
//                                        Notification_Link
//                                Registration_Link
//                                        Copy_Website_Link

                class_LatGovtJob.setFFO_Posted_Date(FFO_Posted_Date);
                class_LatGovtJob.setRegistration_Start_Date(Registration_Start_Date);
                class_LatGovtJob.setRegistration_Last_Date(Registration_Last_Date);
                class_LatGovtJob.setRegistration_Last_Date_Custom(Registration_Last_Date_Custom);
                class_LatGovtJob.setAdmit_Card_Date(Admit_Card_Date);
                class_LatGovtJob.setExam_Date(Exam_Date);

                class_LatGovtJob.setOfficial_Website(Official_Website);
                class_LatGovtJob.setNotification_Link(Notification_Link);
                class_LatGovtJob.setRegistration_Link(Registration_Link);
                class_LatGovtJob.setRegistration_Link2(Registration_Link2);
                class_LatGovtJob.setCopy_Website_Link(Copy_Website_Link);
                class_LatGovtJob.setCompanyImage_Link(CompanyImage_Link);
                class_LatGovtJob.setStatus(Status);
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
            Toast.makeText(FetchProductDataActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
        dialogEng.dismiss();
    }


    public void search(String newText) {
        ArrayList<Class_GovtJob> listSearchQues=new ArrayList<>();
        for (Class_GovtJob classUserSearch: list_latJobs){
            if (classUserSearch.getJob_Title_Combined().toLowerCase().contains(newText.toLowerCase())){
                listSearchQues.add(classUserSearch);
            }
        }
        adaptor_LatestAdminJobs adapSearchJob= new adaptor_LatestAdminJobs(this,listSearchQues);
        latJobs_rv.setAdapter(adapSearchJob);
    }


    public void VerifyDialogET() {

        Toast.makeText(FetchProductDataActivity.this, "Touch Successfully", Toast.LENGTH_LONG).show();
    }

}