package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView userRegisterQuery_rv;
    ListView featured_listview;
    ListAdapter adapter;
    ProgressDialog dialogEng;

    BottomNavigationView bottom_Navigation;

    //    RecyclerView latJobs_rv;
    Adaptor_User laJobs_Adaptor,registerAdaptorGovtHi;

    List<Class_ListerDesc> list_latJobs;
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

    ImageSlider imageSlider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSlider=findViewById(R.id.image_slider_header);
        featured_listview=findViewById(R.id.featured_listview);
        final List<SlideModel> remoteImages=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("BannerImageSlider").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    remoteImages.add(new SlideModel(
                            data.child("url").getValue().toString(),
                            data.child("title").getValue().toString(),
                            ScaleTypes.FIT));
                }
                imageSlider.setImageList(remoteImages,ScaleTypes.FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (InternetConnection.checkConnection(getApplicationContext())) {
//            new GetAdminDataTaskEng().execute(activity);

            getFeaturedData();
        } else {
            Toast.makeText(this,
                    "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
        }


//
////        userRegisterQuery_rv=findViewById(R.id.userRegisterQuery_rv);
//        listview=findViewById(R.id.listview);
////
////        ActivityCompat.requestPermissions(Register_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
////        ActivityCompat.requestPermissions(Register_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//
////        Intent intent = getIntent();
////        activity = intent.getStringExtra("position");
//
//
////        userRegisterQuery_rv = findViewById(R.id.adminQuery_rv);
////        userRegisterQuery_rv.setVisibility(View.VISIBLE);
//
////
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
////        linearLayoutManager.setReverseLayout(true);
////        linearLayoutManager.setStackFromEnd(true);
////        latJobs_rv.setLayoutManager(linearLayoutManager);
//
//
//        list_latJobs = new ArrayList<>();
//
//
////        userRegisterQuery_rv.setLayoutManager(new LinearLayoutManager(Home.this));
//
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
////        linearLayoutManager.setReverseLayout(true);
////        linearLayoutManager.setStackFromEnd(true);
////        userRegisterQuery_rv.setLayoutManager(linearLayoutManager);
//
////        laJobs_Adaptor = new Adaptor_User(this, list_latJobs);
////        userRegisterQuery_rv.setAdapter(laJobs_Adaptor);
//
//        if (searchView != null) {
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    search(newText);
//                    return false;
//                }
//            });
//        }
//        if (InternetConnection.checkConnection(getApplicationContext())) {
////            new GetAdminDataTaskEng().execute(activity);
//
//            getData();
//        } else {
//            Toast.makeText(this,
//                    "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
//        }
//
//
////        laJobs_Adaptor.setOnItemClickListener(new Adaptor_User.OnItemClickListener() {
////            @Override
////            public void onDelete(int Position) {
////
////            }
////
////            @Override
////            public void sendWelcomeMsz(int position, String title) {
////
////                PackageManager pm=getPackageManager();
////                try {
////
////                    Intent waIntent = new Intent(Intent.ACTION_SEND);
////                    waIntent.setType("text/plain");
////                    String text = "YOUR TEXT HERE";
////
////                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
////                    //Check if package exists or not. If not then code
////                    //in catch block will be called
////                    waIntent.setPackage("com.whatsapp");
////
////                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
////                    startActivity(Intent.createChooser(waIntent, "Share with"));
////
////                } catch (PackageManager.NameNotFoundException e) {
////                    Toast.makeText(Home.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
////                            .show();
////                }
////
////
//////                try {
//////                    String mobile = "+91 "+title;
//////                    String msg = "I am facing problem while Register, Please support?";
//////
//////                    String wlcmmsz="Hello,\n" +
//////                            "Thank you for Downloading the *Fill Form Online -All Exam Registration Form App*.\n" +
//////                            "\n" +
//////                            "We are here to *help you to fill all types of forms* like government exams, entrance exams, school/college admission, government schemes, etc.\n" +
//////                            "\n" +
//////                            "Also, you can *ask any questions from our experts* related to any exam, \n" +
//////                            "As well as, you can *Fill out any of your Forms through us* from your home without going anywhere.\n" +
//////                            "\n" +
//////                            "Kindly *save my number for any support* and get the Latest Govt Jobs updates and Entrance Exam information whenever you want.\n" +
//////                            "\n" +
//////                            "Thanks,\n" +
//////                            "Team FillFormOnline\n" +
//////                            "-----------------------------------\n" +
//////                            "नमस्ते,\n" +
//////                            "*फिल फॉर्म ऑनलाइन - सभी परीक्षा पंजीकरण फॉर्म ऐप* डाउनलोड करने के लिए धन्यवाद।\n" +
//////                            "\n" +
//////                            "हम सभी प्रकार के फॉर्म जैसे सरकारी परीक्षा, प्रवेश परीक्षा, स्कूल/कॉलेज में प्रवेश, सरकारी योजनाओं आदि को भरने में *आपकी मदद करने के लिए यहां हैं*।\n" +
//////                            "\n" +
//////                            "साथ ही, किसी भी परीक्षा से संबंधित हमारे *विशेषज्ञों से आप कोई भी प्रश्न पूछ सकते हैं*,\n" +
//////                            "साथ ही, आप बिना कहीं जाए *अपने घर से हमारे द्वारा अपना कोई भी फॉर्म भरा सकते हैं*।\n" +
//////                            "\n" +
//////                            "कृपया किसी भी सहायता के लिए *मेरा नंबर सेव करें* और जब चाहें नवीनतम सरकारी नौकरी अपडेट और प्रवेश परीक्षा की जानकारी प्राप्त करें।\n" +
//////                            "\n" +
//////                            "धन्यवाद,\n" +
//////                            "टीम फिलफॉर्मऑनलाइन";
//////
////////                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + wlcmmsz)));
//////
//////                } catch (Exception e) {
////                    //whatsapp app not install
////
//////                }
////            }
////
////        });
//

        bottom_Navigation=findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_settings);
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

    }

    private void getFeaturedData() {

        dialogEng = new ProgressDialog(Home.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading uSER dATA");
        dialogEng.show();

//api_listertest
//        String link="https://script.google.com/macros/s/AKfycbyQm1hbnQsezmmt3osWUAFblMsbie5wLuPfQZG_oOfR9qKW88g/exec?action=getUsers";
//        String link="https://script.google.com/macros/s/AKfycbzZr-qh-rRn-b1im1JPFkiD91RH_FDaWWOH_Ibn6fSgcBLxTm3d_ICt0SbOjaRG0b0q/exec?action=getUsers";
        String link="https://script.google.com/macros/s/AKfycbw8gC2dlMA-dMbKouGxwShcxntjgb7H2foRHs9oUlTrd9yi0dyhsE_jgeudSmPoaq53/exec?action=get";


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
            Class_ListerDesc class_listerDesc=new Class_ListerDesc();
            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String SR_NO = jo.getString("SR. NO.");
                String PRODUCT_CODE = jo.getString("PRODUCT CODE");
                String SERIES = jo.getString("SERIES");
                String MODEL = jo.getString("MODEL");
                String SHAPE = jo.getString("SHAPE");
                String WATT = jo.getString("WATT");
                String POWER_FACTOR = jo.getString("POWER FACTOR");
                String SURGE_PROTECTION = jo.getString("SURGE PROTECTION");
                String LUMEN_lm = jo.getString("LUMEN (lm)");
                String IP_RATING = jo.getString("IP - RATING");
                String CUT_OUT_SIZE_mm = jo.getString("CUT-OUT SIZE (mm)");
                String HOUSING_TYPE = jo.getString("HOUSING TYPE");
                String COLOUR = jo.getString("COLOUR");
                String REMARKS = jo.getString("REMARKS");
                String Sale = jo.getString("Sale");
                String Featured = jo.getString("Featured");
                String New_Product = jo.getString("New Product");

//                if(Featured.equals('a')) {

                    HashMap<String, String> item = new HashMap<>();
                    item.put("SR_NO", SR_NO);
                    item.put("PRODUCT_CODE", PRODUCT_CODE);
                    item.put("SERIES", SERIES);
                    item.put("MODEL", MODEL);
                    item.put("SHAPE", SHAPE);
                    item.put("WATT", WATT);
                    item.put("POWER_FACTOR", POWER_FACTOR);
                    item.put("SURGE_PROTECTION", SURGE_PROTECTION);
                    item.put("LUMEN_lm", LUMEN_lm);
                    item.put("IP_RATING", IP_RATING);
                    item.put("CUT_OUT_SIZE_mm", CUT_OUT_SIZE_mm);
                    item.put("HOUSING_TYPE", HOUSING_TYPE);
                    item.put("COLOUR", COLOUR);
                    item.put("REMARKS", REMARKS);
                    item.put("Sale", Sale);
                    item.put("Featured", Featured);
                    item.put("New_Product", New_Product);
//
                    class_listerDesc.setSR_NO(SR_NO);
                    class_listerDesc.setPRODUCT_CODE(PRODUCT_CODE);
                    class_listerDesc.setSERIES(SERIES);
                    class_listerDesc.setMODEL(MODEL);
                    class_listerDesc.setSHAPE(SHAPE);
                    class_listerDesc.setWATT(WATT);
                    class_listerDesc.setPOWER_FACTOR(POWER_FACTOR);
                    class_listerDesc.setSURGE_PROTECTION(SURGE_PROTECTION);
                    class_listerDesc.setLUMEN_lm(LUMEN_lm);
                    class_listerDesc.setIP_RATING(IP_RATING);
                    class_listerDesc.setCUT_OUT_SIZE_mm(CUT_OUT_SIZE_mm);
                    class_listerDesc.setHOUSING_TYPE(HOUSING_TYPE);
                    class_listerDesc.setCOLOUR(COLOUR);
                    class_listerDesc.setREMARKS(REMARKS);
                    class_listerDesc.setSale(Sale);
                    class_listerDesc.setFeatured(Featured);
                    class_listerDesc.setNew_Product(New_Product);


                    list.add(item);
//}

//                    list_latJobs.add(class_listerDesc);
//                }
            }
//            list_latJobs.add(class_LatGovtJob);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new SimpleAdapter(this,list,R.layout.list_lister_circle,
//                new String[]{"PRODUCT_CODE","MODEL","SERIES","SHAPE","WATT"},new int[]{R.id.user,R.id.name,R.id.mobile,R.id.emailid,R.id.datetime});
                new String[]{"PRODUCT_CODE"+" "+"MODEL","SERIES"},new int[]{R.id.user,R.id.mobile});
        featured_listview.setAdapter(adapter);

//        progressBarDel.setVisibility(View.GONE);


//        if(list_latJobs.size() > 0) {
////            laJobs_Adaptor = new Adaptor_User(this, list_latJobs);
////            userRegisterQuery_rv.setAdapter(laJobs_Adaptor);
//                laJobs_Adaptor.notifyDataSetChanged();
//        } else {
////                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
//            Toast.makeText(Home.this, "No Data Found", Toast.LENGTH_SHORT).show();
//        }
        dialogEng.dismiss();
    }
    }
//}