package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
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
import java.util.List;

public class FetchProductActivity extends AppCompatActivity {

    RecyclerView rv_FetchProduct;

    Adaptor_FetchData adaptor_fetchdata;
    ProgressDialog dialogEng;

    BottomNavigationView bottom_Navigation;
    String URLOld="https://script.google.com/macros/s/AKfycbzZr-qh-rRn-b1im1JPFkiD91RH_FDaWWOH_Ibn6fSgcBLxTm3d_ICt0SbOjaRG0b0q/exec";
    private final String URLFetchData ="https://script.google.com/macros/s/AKfycbznBNmLV4H0A_ARDjjOYkaefeF3JTiAWjFVIU0dNuZavYS3sdBAhDTkNsal4Ep0tPQ/exec";
    //    RecyclerView latJobs_rv;
    Adaptor_FetchData laJobs_Adaptor,registerAdaptorGovtHi;

    List<Class_ListerCounter> list_FetchData;
    Button btn_NewOrder;
    SwipeRefreshLayout swipeRefreshLayout;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    SearchView searchView;
    String activity;
    private static final int WRITE_EXTERNAL_STORAGE_CODES=1;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchproduct);

        rv_FetchProduct =findViewById(R.id.rv_FetchProduct);
        searchView=findViewById(R.id.searchView);
        btn_NewOrder=findViewById(R.id.btn_NewOrder);

//        listview=findViewById(R.id.listview);
//
//        ActivityCompat.requestPermissions(Register_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//        ActivityCompat.requestPermissions(Register_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

//        Intent intent = getIntent();
//        activity = intent.getStringExtra("position");


//        userRegisterQuery_rv = findViewById(R.id.adminQuery_rv);
//        userRegisterQuery_rv.setVisibility(View.VISIBLE);

//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        latJobs_rv.setLayoutManager(linearLayoutManager);


        list_FetchData = new ArrayList<>();


        rv_FetchProduct.setLayoutManager(new LinearLayoutManager(FetchProductActivity.this));

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        userRegisterQuery_rv.setLayoutManager(linearLayoutManager);

        laJobs_Adaptor = new Adaptor_FetchData(this, list_FetchData);
        rv_FetchProduct.setAdapter(laJobs_Adaptor);

        if (searchView != null) {
//            rv_FetchProduct.setVisibility(View.VISIBLE);
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
        if (InternetConnection.checkConnection(getApplicationContext())) {
//            new GetAdminDataTaskEng().execute(activity);

//            getData();
            getUpdatedData();
        } else {
            Toast.makeText(this,
                    "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
        }

        btn_NewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FetchProductActivity.this, PlaceOrder1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//        laJobs_Adaptor.setOnItemClickListener(new Adaptor_User.OnItemClickListener() {
//            @Override
//            public void onDelete(int Position) {
//
//            }
//
//            @Override
//            public void sendWelcomeMsz(int position, String title) {
//
//                PackageManager pm=getPackageManager();
//                try {
//
//                    Intent waIntent = new Intent(Intent.ACTION_SEND);
//                    waIntent.setType("text/plain");
//                    String text = "YOUR TEXT HERE";
//
//                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//                    //Check if package exists or not. If not then code
//                    //in catch block will be called
//                    waIntent.setPackage("com.whatsapp");
//
//                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
//                    startActivity(Intent.createChooser(waIntent, "Share with"));
//
//                } catch (PackageManager.NameNotFoundException e) {
//                    Toast.makeText(Home.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
//                            .show();
//                }
//
//
////                try {
////                    String mobile = "+91 "+title;
////                    String msg = "I am facing problem while Register, Please support?";
////
////                    String wlcmmsz="Hello,\n" +
////                            "Thank you for Downloading the *Fill Form Online -All Exam Registration Form App*.\n" +
////                            "\n" +
////                            "We are here to *help you to fill all types of forms* like government exams, entrance exams, school/college admission, government schemes, etc.\n" +
////                            "\n" +
////                            "Also, you can *ask any questions from our experts* related to any exam, \n" +
////                            "As well as, you can *Fill out any of your Forms through us* from your home without going anywhere.\n" +
////                            "\n" +
////                            "Kindly *save my number for any support* and get the Latest Govt Jobs updates and Entrance Exam information whenever you want.\n" +
////                            "\n" +
////                            "Thanks,\n" +
////                            "Team FillFormOnline\n" +
////                            "-----------------------------------\n" +
////                            "नमस्ते,\n" +
////                            "*फिल फॉर्म ऑनलाइन - सभी परीक्षा पंजीकरण फॉर्म ऐप* डाउनलोड करने के लिए धन्यवाद।\n" +
////                            "\n" +
////                            "हम सभी प्रकार के फॉर्म जैसे सरकारी परीक्षा, प्रवेश परीक्षा, स्कूल/कॉलेज में प्रवेश, सरकारी योजनाओं आदि को भरने में *आपकी मदद करने के लिए यहां हैं*।\n" +
////                            "\n" +
////                            "साथ ही, किसी भी परीक्षा से संबंधित हमारे *विशेषज्ञों से आप कोई भी प्रश्न पूछ सकते हैं*,\n" +
////                            "साथ ही, आप बिना कहीं जाए *अपने घर से हमारे द्वारा अपना कोई भी फॉर्म भरा सकते हैं*।\n" +
////                            "\n" +
////                            "कृपया किसी भी सहायता के लिए *मेरा नंबर सेव करें* और जब चाहें नवीनतम सरकारी नौकरी अपडेट और प्रवेश परीक्षा की जानकारी प्राप्त करें।\n" +
////                            "\n" +
////                            "धन्यवाद,\n" +
////                            "टीम फिलफॉर्मऑनलाइन";
////
//////                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + wlcmmsz)));
////
////                } catch (Exception e) {
//                    //whatsapp app not install
//
////                }a
//            }
//
//        });



        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_query);
        bottom_Navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btm_home:
                        startActivity(new Intent(getApplicationContext(), HomePage_f.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                    case R.id.btm_AboutUs:
                        startActivity(new Intent(getApplicationContext(), AboutUs.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_AllProducts:
                        startActivity(new Intent(getApplicationContext(), AllProducts.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_query:

                        return true;

                    case R.id.btm_profile:
                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

//                    case R.id.btm_settings:
//                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
//                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                        finish();
//                        return true;

                }
                return false;
            }
        });

    }

//    private void getData() {
//        dialogEng = new ProgressDialog(FetchProductDataActivity.this);
//        dialogEng.setTitle("Wait Please... ");
//        dialogEng.setMessage("Fetching Product Count");
//        dialogEng.show();
//
////api_listertest
////        String link="https://script.google.com/macros/s/AKfycbyQm1hbnQsezmmt3osWUAFblMsbie5wLuPfQZG_oOfR9qKW88g/exec?action=getUsers";
////        String link="https://script.google.com/macros/s/AKfycbzZr-qh-rRn-b1im1JPFkiD91RH_FDaWWOH_Ibn6fSgcBLxTm3d_ICt0SbOjaRG0b0q/exec?action=getUsers";
//        String link="https://script.google.com/macros/s/AKfycbzZr-qh-rRn-b1im1JPFkiD91RH_FDaWWOH_Ibn6fSgcBLxTm3d_ICt0SbOjaRG0b0q/exec?action=getUsers";
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, link,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        parseItems(response);
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//
//        int socketTimeOut = 50000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        stringRequest.setRetryPolicy(policy);
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//
//    }
//    private void parseItems(String jsonResposnce) {
//        int newScore;
//        String Score1;
//        ArrayList<HashMap<String, String>> list = new ArrayList<>();
//        try {
//            JSONObject jobj = new JSONObject(jsonResposnce);
//            JSONArray jarray = jobj.getJSONArray("items");
//            Class_User class_LatGovtJob=new Class_User();
//            for (int i = 0; i < jarray.length(); i++) {
//
//                JSONObject jo = jarray.getJSONObject(i);
//
//                String User = jo.getString("User");
//                String Name = jo.getString("Name");
//                String Mobile = jo.getString("Mobile");
//                String Token = jo.getString("Token");
//                String Date = jo.getString("Date");
//
//
//                HashMap<String, String> item = new HashMap<>();
//                item.put("User", User);
//                item.put("Name", Name);
//                item.put("Mobile", Mobile);
//                item.put("Token", Token);
//                item.put("Date", Date);
////
//                class_LatGovtJob.setUserid(User);
//                class_LatGovtJob.setName(Name);
//                class_LatGovtJob.setPhoneno(Mobile);
//                class_LatGovtJob.setToken(Token);
//                class_LatGovtJob.setDatetime(Date);
//
//
//                list.add(item);
////
//
//                list_latJobs.add(new Class_ListerCounter());
//
//            }
////            list_latJobs.add(class_LatGovtJob);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        adapter = new SimpleAdapter(this,list,R.layout.list_lister_rectangular,
//                new String[]{"User","Name","Mobile","Token","Date"},new int[]{R.id.user,R.id.name,R.id.mobile,R.id.emailid,R.id.datetime});
//        listview.setAdapter(adapter);
//
//
//
////        progressBarDel.setVisibility(View.GONE);
//
//
////        if(list_latJobs.size() > 0) {
//////            laJobs_Adaptor = new Adaptor_User(this, list_latJobs);
//////            userRegisterQuery_rv.setAdapter(laJobs_Adaptor);
////                laJobs_Adaptor.notifyDataSetChanged();
////        } else {
//////                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
////            Toast.makeText(Home.this, "No Data Found", Toast.LENGTH_SHORT).show();
////        }
//        dialogEng.dismiss();
//    }

    private void getUpdatedData() {

        dialogEng = new ProgressDialog(FetchProductActivity.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Fetching Product Count");
        dialogEng.show();
        String URL_Products = URLFetchData + "?action=get";
        Class_User class_LatGovtJob=new Class_User();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_FetchData = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
//                                String sno = product.getString("sno");
//                                String productcode = product.getString("productcode");
//                                String category = product.getString("category");
//                                String subcategory = product.getString("subcategory");
//                                String series = product.getString("series");
//                                String model = product.getString("model");
//                                String url = product.getString("url");
//                                String hotselling = product.getString("hotselling");
//                                String upcoming = product.getString("upcoming");
//                                String offer = product.getString("offer");

                                String model_name = product.getString("Model Name");
                                String color = product.getString("COLOR");
                                String brand = product.getString("BRAND");
                                String current_stock;
                                String imageLink = product.getString("ImageLink");
                                String Date = product.getString("Date");

                                SessionManagement sessionManagement = new SessionManagement(FetchProductActivity.this);
                                int userID = sessionManagement.getSession();
//                                Toast.makeText(FetchProductActivity.this, "c"+userID, Toast.LENGTH_SHORT).show();
                                if (userID==(-1)) {

                                    current_stock="Login";
                                    //user id logged in and so move to mainActivity
//                                    moveToMainActivity();

                                }else{
                                    current_stock = product.getString("CURRENT STOCK");
                                }


                                    list_FetchData.add(new Class_ListerCounter(model_name, color, brand, current_stock, imageLink,Date));

//                                }
//                                list_UpcomingProducts.add(new DataModel1(sno,productcode,series,model,url,hotselling,upcoming,offer));
//                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

                            adaptor_fetchdata = new Adaptor_FetchData(getApplicationContext(), list_FetchData);
                            rv_FetchProduct.setAdapter(adaptor_fetchdata);
                            dialogEng.dismiss();
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialogEng.dismiss();
//                            Toast.makeText(FetchProductActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(FetchProductActivity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
    public void search(String newText) {
        ArrayList<Class_ListerCounter> list_SearchQuery=new ArrayList<>();
        for (Class_ListerCounter class_FetchData: list_FetchData){
            if (class_FetchData.getModel_Name().toLowerCase().contains(newText.toLowerCase())){
                list_SearchQuery.add(class_FetchData);
            }
        }

        adaptor_fetchdata.filterlist(list_SearchQuery);

//        Adaptor_User adapSearchJob= new Adaptor_User(this,list_SearchQuery);
//        userRegisterQuery_rv.setAdapter(adapSearchJob);
    }
    public void VerifyDialogET() {

//        Toast.makeText(FetchProductActivity.this, "Touch Successfully", Toast.LENGTH_LONG).show();
    }

//    private void getData1() {
//        dialogEng = new ProgressDialog(Home.this);
//        dialogEng.setTitle("Wait Please... ");
//        dialogEng.setMessage("Loading Latest Jobs");
//        dialogEng.show();
//        String link="https://script.google.com/macros/s/AKfycbzZr-qh-rRn-b1im1JPFkiD91RH_FDaWWOH_Ibn6fSgcBLxTm3d_ICt0SbOjaRG0b0q/exec?action=getUsers";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, link,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        parseItems1(response);
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//
//        int socketTimeOut = 50000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        stringRequest.setRetryPolicy(policy);
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//
//    }
//    private void parseItems1(String jsonResposnce) {
//        int newScore;
//        String Score1;
//        ArrayList<HashMap<String, String>> list = new ArrayList<>();
//        try {
//            JSONObject jobj = new JSONObject(jsonResposnce);
//            JSONArray jarray = jobj.getJSONArray("items");
//            Class_User class_LatGovtJob=new Class_User();
//            for (int i = 0; i < jarray.length(); i++) {
//
////                JSONObject innerObject = jarray.getJSONObject(i);
//
//                JSONObject jo = jarray.getJSONObject(i);
//
//                String User = jo.getString("User");
//                String Name = jo.getString("Name");
//                String Mobile = jo.getString("Mobile");
//                String Token = jo.getString("Token");
//                String Date = jo.getString("Date");
//
//
////                HashMap<String, String> item = new HashMap<>();
////                item.put("User", User);
////                item.put("Name", Name);
////                item.put("Mobile", Mobile);
////                item.put("Token", Token);
////                item.put("Date", Date);
////
//                class_LatGovtJob.setUserid(User);
//                class_LatGovtJob.setName(Name);
//                class_LatGovtJob.setPhoneno(Mobile);
//                class_LatGovtJob.setToken(Token);
//                class_LatGovtJob.setDatetime(Date);
////
////                String Name = jo.getString("Name");
////                String Mobile = jo.getString("Mobile");
////                String EmailId = jo.getString("EmailId");
////                String Date = jo.getString("Date");
////
//////
////
////                class_LatGovtJob.setName(Name);
////                class_LatGovtJob.setPhoneno(Mobile);
////                class_LatGovtJob.setEmailid(EmailId);
////                class_LatGovtJob.setDatetime(Date);
////                Class_GovtJob class_LatGovtJob=new Class_GovtJob();
//                /**
//                 * Getting Inner Object from contacts array...
//                 * and
//                 * From that We will get Name of that Contact
//                 *
//                 */
////                JSONObject innerObject = array.getJSONObject(jIndexEng);
//
////                String SrNo = innerObject.getString(Keys.SrNo);
////                String Date = innerObject.getString(Keys.Date);
////                String JobId = innerObject.getString(Keys.JobId);
////                String UserName = innerObject.getString(Keys.UserName);
////
////                String NoOfPostCategory = innerObject.getString(Keys.NoOfPostCategory);
////                String EducationCategory = innerObject.getString(Keys.EducationCategory);
////                String StateCentralCategory = innerObject.getString(Keys.StateCentralCategory);
////                String DepartmentCategory = innerObject.getString(Keys.DepartmentCategory);
////
////                String Job_Company_Name = innerObject.getString(Keys.Job_Company_Name);
////                String Job_Description = innerObject.getString(Keys.Job_Description);
////                String Post_Name_Short = innerObject.getString(Keys.Post_Name_Short);
//////                                String jobPostNameS = innerObject.getString(Keys.JOB_POSTNAMESHORT);
////                String Post_Name_Long = innerObject.getString(Keys.Post_Name_Long);
////                String No_of_Post = innerObject.getString(Keys.No_of_Post);
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
////                String Job_Title_Combined = innerObject.getString(Keys.Job_Title_Combined);
////                String Age_Limit = innerObject.getString(Keys.Age_Limit);
////                String Fees = innerObject.getString(Keys.Fees);
////                String Education_Short = innerObject.getString(Keys.Education_Short);
////                String  Education_Long= innerObject.getString(Keys.Education_Long);
////                String Experience_Required = innerObject.getString(Keys.Experience_Required);
////                String Selection_Process = innerObject.getString(Keys.Selection_Process);
////                String Salary = innerObject.getString(Keys.Salary);
////                String Who_Can_Apply = innerObject.getString(Keys.Who_Can_Apply);
////                                FFO_Posted_Date
////                                        Registration_Start_Date
////                                Registration_Last_Date
////                                        Registration_Last_Date_Custom
////                                Admit_Card_Date
////                                        Exam_Date
////                String FFO_Posted_Date = innerObject.getString(Keys.FFO_Posted_Date);
////                String Registration_Start_Date = innerObject.getString(Keys.Registration_Start_Date);
////                String Registration_Last_Date = innerObject.getString(Keys.Registration_Last_Date);
////                String Registration_Last_Date_Custom = innerObject.getString(Keys.Registration_Last_Date_Custom);
////                String Admit_Card_Date = innerObject.getString(Keys.Admit_Card_Date);
////                String Exam_Date = innerObject.getString(Keys.Exam_Date);
////
//////                                Official_Website
//////                                        Notification_Link
//////                                Registration_Link
//////                                        Copy_Website_Link
//////
////                String Official_Website = innerObject.getString(Keys.Official_Website);
////                String Notification_Link = innerObject.getString(Keys.Notification_Link);
////                String Registration_Link = innerObject.getString(Keys.Registration_Link);
////                String Registration_Link2 = innerObject.getString(Keys.Registration_Link2);
////                String Copy_Website_Link = innerObject.getString(Keys.Copy_Website_Link);
////
////                String CompanyImage_Link = innerObject.getString(Keys.CompanyImage_Link);
////                String Status = innerObject.getString(Keys.Status);
////                                    String Logo = innerObject.getString(Keys.Logo);
//
//                /**
//                 * Getting Object from Object "phone"
//                 */
//                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
//                //String phone = phoneObject.getString(Keys.KEY_MOBILE);
//
////                                model.setName(jobTitle+jobLastDate+jobofficialweb);
////                                model.setCountry(jobDesc+jobExamDate+jobOfficialNoti);
//
////
////                class_LatGovtJob.setSrNo(SrNo);
////                class_LatGovtJob.setDate(Date);
////                class_LatGovtJob.setJobId(JobId);
////                class_LatGovtJob.setUserName(UserName);
////
////                class_LatGovtJob.setNoOfPostCategory(NoOfPostCategory);
////                class_LatGovtJob.setEducationCategory(EducationCategory);
////                class_LatGovtJob.setStateCentralCategory(StateCentralCategory);
////                class_LatGovtJob.setDepartmentCategory(DepartmentCategory);
////
////                class_LatGovtJob.setJob_Company_Name(Job_Company_Name);
////                class_LatGovtJob.setJob_Description(Job_Description);
////                class_LatGovtJob.setPost_Name_Short(Post_Name_Short);
////                class_LatGovtJob.setPost_Name_Long(Post_Name_Long);
////                class_LatGovtJob.setNo_of_Post(No_of_Post);
////                class_LatGovtJob.setJob_Title_Combined(Job_Title_Combined);
////
//////                                Age_Limit
//////                                        Fees
//////                                Education_Short
//////                                        Education_Long
//////                                Experience_Required
//////                                        Selection_Process
//////                                Salary
////
////                class_LatGovtJob.setAge_Limit(Age_Limit);
////                class_LatGovtJob.setFees(Fees);
////                class_LatGovtJob.setEducation_Short(Education_Short);
////                class_LatGovtJob.setEducation_Long(Education_Long);
////                class_LatGovtJob.setExperience_Required(Experience_Required);
////                class_LatGovtJob.setSelection_Process(Selection_Process);
////                class_LatGovtJob.setSalary(Salary);
////                class_LatGovtJob.setWho_Can_Apply(Who_Can_Apply);
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
////                class_LatGovtJob.setFFO_Posted_Date(FFO_Posted_Date);
////                class_LatGovtJob.setRegistration_Start_Date(Registration_Start_Date);
////                class_LatGovtJob.setRegistration_Last_Date(Registration_Last_Date);
////                class_LatGovtJob.setRegistration_Last_Date_Custom(Registration_Last_Date_Custom);
////                class_LatGovtJob.setAdmit_Card_Date(Admit_Card_Date);
////                class_LatGovtJob.setExam_Date(Exam_Date);
////
////                class_LatGovtJob.setOfficial_Website(Official_Website);
////                class_LatGovtJob.setNotification_Link(Notification_Link);
////                class_LatGovtJob.setRegistration_Link(Registration_Link);
////                class_LatGovtJob.setRegistration_Link2(Registration_Link2);
////                class_LatGovtJob.setCopy_Website_Link(Copy_Website_Link);
////                class_LatGovtJob.setCompanyImage_Link(CompanyImage_Link);
////                class_LatGovtJob.setStatus(Status);
////                                    class_LatGovtJob.setLogo(Logo);
//                /**
//                 * Adding name and phone concatenation in List...
//                 */
//                //list.add(model);
////                list_latJobs.add(class_LatGovtJob);
//
////                list.add(item);
//
//
//                list_latJobs.add(class_LatGovtJob);
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
////        adapter = new SimpleAdapter(this,list,R.layout.list_item,
////                new String[]{"Name","Mobile","EmailId","Date"},new int[]{R.id.name,R.id.mobile,R.id.emailid,R.id.datetime});
////        listview.setAdapter(adapter);
//
////        progressBarDel.setVisibility(View.GONE);
//
//
//        if(list_latJobs.size() > 0) {
//
//            laJobs_Adaptor.notifyDataSetChanged();
//        } else {
////                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
//            Toast.makeText(Home.this, "No Data Found", Toast.LENGTH_SHORT).show();
//        }
//        dialogEng.dismiss();
//    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity
        SessionManagement sessionManagement = new SessionManagement(FetchProductActivity.this);
        int userID = sessionManagement.getSession();
        if (userID==(-1)) {
            //user id logged in and so move to mainActivity
            moveToMainActivity();
        }
    }
    private void moveToMainActivity() {
        Intent intent = new Intent(FetchProductActivity.this, AllProducts.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}