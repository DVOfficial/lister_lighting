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
import android.widget.Button;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity {

    Button logoutButton;
    TextView textView;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    BottomNavigationView bottom_Navigation;


//    BottomNavigationView bottom_Navigation;

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
        setContentView(R.layout.activity_home);

        logoutButton=findViewById(R.id.logoutButton);
        textView=findViewById(R.id.textView);
        bottom_Navigation=findViewById(R.id.bottom_Navigation);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser==null){
            Intent intent =new Intent(Home.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }
//        else{
//        textView.setText(firebaseUser.getEmail());
//        }




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
        laJobs_Adaptor = new adaptor_ListerData(this, list_latJobs);
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


        laJobs_Adaptor.setOnItemClickListener(new adaptor_ListerData.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String mTitle) {

            }

            @Override
            public void shareQues(String jobId) {

                Toast.makeText(Home.this, "JOBID"+jobId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Home.this,FetchProductDataActivity.class);
                intent.putExtra("jobId",jobId);
                startActivity(intent);

            }

            @Override
            public void editQues(String jobId) {
                Toast.makeText(Home.this, "JOBID"+jobId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Home.this,FetchProductDataActivity.class);
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

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(Home.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bottom_Navigation=findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_home);
        bottom_Navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btm_home:
                        return true;

                    case R.id.btm_allproduct:
                        startActivity(new Intent(getApplicationContext(),AllProductsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
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


//        bottom_Navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.btm_home:
//                        Toast.makeText(Home.this, "Home", Toast.LENGTH_SHORT).show();
//                        Intent intent =new Intent(Home.this,Home.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.btm_allproduct:
//                        Toast.makeText(Home.this, "All Products", Toast.LENGTH_SHORT).show();
//                        intent =new Intent(Home.this,AllProductsActivity.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.btm_query:
//                        Toast.makeText(Home.this, "Fetch Query", Toast.LENGTH_SHORT).show();
//                        intent =new Intent(Home.this,FetchProductDataActivity.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.btm_profile:
//                        Toast.makeText(Home.this, "Profile", Toast.LENGTH_SHORT).show();
//                        intent =new Intent(Home.this,UserProfileActivity.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.btm_settings:
//                        Toast.makeText(Home.this, "Settings", Toast.LENGTH_SHORT).show();
//                        intent =new Intent(Home.this,SettingActivity.class);
//                        startActivity(intent);
//                        break;
//
//                }
//                return true;
//            }
//        });
    }
    private void getData() {
        dialogEng = new ProgressDialog(Home.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Latest Jobs");
        dialogEng.show();
        String link="https://script.google.com/macros/s/AKfycbznBNmLV4H0A_ARDjjOYkaefeF3JTiAWjFVIU0dNuZavYS3sdBAhDTkNsal4Ep0tPQ/exec?action=get";
//        https://script.google.com/macros/s/AKfycbyMRz4K46lpadNitG8w2GpwYPDGprOBWrKMLMxMgn0C2JzL2ms/exec
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
//        int newScore;
//        String Score1;
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("items");
            Class_ListerData class_LatGovtJob=new Class_ListerData();

            
            for (int i = 0; i < jarray.length(); i++) {

                JSONObject innerObject = jarray.getJSONObject(i);

                /**
                 * Getting Inner Object from contacts array...
                 * and
                 * From that We will get Name of that Contact
                 *
                 */
//                JSONObject innerObject = array.getJSONObject(jIndexEng);

                String Sno = innerObject.getString(Keys.KEY_Sno);
                String ModelName = innerObject.getString(Keys.KEY_ModelName);
                String COLOR = innerObject.getString(Keys.KEY_COLOR);
                String BRAND = innerObject.getString(Keys.KEY_BRAND);

                String OPENINGSTOCK = innerObject.getString(Keys.KEY_OPENINGSTOCK);
                String INQTY = innerObject.getString(Keys.KEY_INQTY);
                String Out = innerObject.getString(Keys.KEY_Out);
                String CURRENTSTOCK = innerObject.getString(Keys.KEY_CURRENTSTOCK);

                String Location = innerObject.getString(Keys.KEY_Location);
                String ImageLink = innerObject.getString(Keys.KEY_ImageLink);
                String Date = innerObject.getString(Keys.KEY_Date);

                /**
                 * Getting Object from Object "phone"
                 */

                class_LatGovtJob.setSno(Sno);
                class_LatGovtJob.setModelName(ModelName);
                class_LatGovtJob.setCOLOR(COLOR);
                class_LatGovtJob.setBRAND(BRAND);

                class_LatGovtJob.setOPENINGSTOCK(OPENINGSTOCK);
                class_LatGovtJob.setINQTY(INQTY);
                class_LatGovtJob.setOut(Out);
                class_LatGovtJob.setCURRENTSTOCK(CURRENTSTOCK);

                class_LatGovtJob.setLocation(Location);
                class_LatGovtJob.setImageLink(ImageLink);
                class_LatGovtJob.setDate(Date);
//                class_LatGovtJob.setPost_Name_Long(Post_Name_Long);
//                class_LatGovtJob.setNo_of_Post(No_of_Post);
//                class_LatGovtJob.setJob_Title_Combined(Job_Title_Combined);
//
////                                Age_Limit
////                                        Fees
////                                Education_Short
////                                        Education_Long
////                                Experience_Required
////                                        Selection_Process
////                                Salary
//
//                class_LatGovtJob.setAge_Limit(Age_Limit);
//                class_LatGovtJob.setFees(Fees);
//                class_LatGovtJob.setEducation_Short(Education_Short);
//                class_LatGovtJob.setEducation_Long(Education_Long);
//                class_LatGovtJob.setExperience_Required(Experience_Required);
//                class_LatGovtJob.setSelection_Process(Selection_Process);
//                class_LatGovtJob.setSalary(Salary);
//                class_LatGovtJob.setWho_Can_Apply(Who_Can_Apply);
//
////                                FFO_Posted_Date
////                                        Registration_Start_Date
////                                Registration_Last_Date
////                                        Registration_Last_Date_Custom
////                                Admit_Card_Date
////                                        Exam_Date
////                                Official_Website
////                                        Notification_Link
////                                Registration_Link
////                                        Copy_Website_Link
//
//                class_LatGovtJob.setFFO_Posted_Date(FFO_Posted_Date);
//                class_LatGovtJob.setRegistration_Start_Date(Registration_Start_Date);
//                class_LatGovtJob.setRegistration_Last_Date(Registration_Last_Date);
//                class_LatGovtJob.setRegistration_Last_Date_Custom(Registration_Last_Date_Custom);
//                class_LatGovtJob.setAdmit_Card_Date(Admit_Card_Date);
//                class_LatGovtJob.setExam_Date(Exam_Date);
//
//                class_LatGovtJob.setOfficial_Website(Official_Website);
//                class_LatGovtJob.setNotification_Link(Notification_Link);
//                class_LatGovtJob.setRegistration_Link(Registration_Link);
//                class_LatGovtJob.setRegistration_Link2(Registration_Link2);
//                class_LatGovtJob.setCopy_Website_Link(Copy_Website_Link);
//                class_LatGovtJob.setCompanyImage_Link(CompanyImage_Link);
//                class_LatGovtJob.setStatus(Status);
////                                    class_LatGovtJob.setLogo(Logo);
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
            Toast.makeText(Home.this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
        dialogEng.dismiss();
    }

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

        Toast.makeText(Home.this, "Touch Successfully", Toast.LENGTH_LONG).show();
    }
}