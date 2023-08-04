package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Home2 extends AppCompatActivity {


    private  final  static int REQUEST_CODE=100;
    FusedLocationProviderClient fusedLocationProviderClient;

    BottomNavigationView bottom_Navigation;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    SearchView searchView;
    ProgressDialog dialogEng;
//

    RecyclerView rv_HotSellingProducts, rv_UpcomingProducts, rv_Offers, rv_OutdoorProducts, rv_IndoorProducts, rv_SMPSDrivers;
    private ProductsAdapter adaptor_HotSellingProducts, adaptor_UpcomingProducts, adaptor_Offers;
    private ProductsSubCatAdapter adaptor_OutdoorProducts, adaptor_IndoorProducts, adaptor_SMPSDrivers;
    List<DataModel1> list_hotSellingProducts, list_UpcomingProducts, list_Offers, list_OutdoorProducts, list_IndoorProducts, list_SMPSDrivers;
    ImageSlider imageSlider, image_slider_mid;

    //    private final String URLNew = "https://script.google.com/macros/s/AKfycbz1YtHbsHWqPVBCtOczH257qJ_RKVp2aSX27BObwKq0-XnjBOIAWRFWqEqeIwgYuYG7/exec";
    private final String URLNew = "https://script.google.com/macros/s/AKfycbyqK43fBc8lCt4smuLrFhjVEUR9ArYT9vyaRZvm2V6ChmZ7FuBuNNWLrybO0IOFpSY8/exec";
    private final String URL_Subcat = "https://script.google.com/macros/s/AKfycbyo2cnGTttv69en0LS1kaacieGZobOma-COpnuAeSPlM9yw-s8XGMtnHEmh5T8TluHdnA/exec";
    //    private String url_HotSellingProduct = "https://script.google.com/macros/s/AKfycbz1YtHbsHWqPVBCtOczH257qJ_RKVp2aSX27BObwKq0-XnjBOIAWRFWqEqeIwgYuYG7/exec?action=get";
    private final String URLFetchData = "https://script.google.com/macros/s/AKfycbznBNmLV4H0A_ARDjjOYkaefeF3JTiAWjFVIU0dNuZavYS3sdBAhDTkNsal4Ep0tPQ/exec";

//    BottomNavigationView bottom_Navigation;

    RecyclerView rv_FetchProduct;
    Adaptor_FetchData adaptor_fetchdata;
    List<Class_ListerCounter> list_FetchData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);



        getDeviceInfo();
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);


        rv_FetchProduct = findViewById(R.id.rv_FetchProduct);
        searchView = findViewById(R.id.searchView);
        list_FetchData = new ArrayList<>();


        rv_FetchProduct.setLayoutManager(new LinearLayoutManager(Home2.this));
        adaptor_fetchdata = new Adaptor_FetchData(this, list_FetchData);
        rv_FetchProduct.setAdapter(adaptor_fetchdata);


        imageSlider = findViewById(R.id.image_slider_header);
//        image_slider_mid=findViewById(R.id.image_slider_mid);
//        featured_listview=findViewById(R.id.featured_listview);
        final List<SlideModel> remoteImages = new ArrayList<>();

//        userRegisterQuery_rv=findViewById(R.id.userRegisterQuery_rv);
//        listview=findViewById(R.id.listview);

//        dialogEng = new ProgressDialog(Home2.this);
//        dialogEng.setTitle("Wait Please... ");
//        dialogEng.setMessage("Loading Image");
//        dialogEng.show();


        FirebaseDatabase.getInstance().getReference().child("BannerImageSlider").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    remoteImages.add(new SlideModel(
                            data.child("url").getValue().toString(),
                            data.child("title").getValue().toString(),
                            ScaleTypes.FIT));
                }
//                dialogEng.dismiss();

                imageSlider.setImageList(remoteImages, ScaleTypes.FIT);
//                image_slider_mid.setImageList(remoteImages,ScaleTypes.FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                dialogEng.dismiss();

            }
        });
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


//        list_latJobs = new ArrayList<>();


//        userRegisterQuery_rv.setLayoutManager(new LinearLayoutManager(Home.this));

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        userRegisterQuery_rv.setLayoutManager(linearLayoutManager);

//        laJobs_Adaptor = new Adaptor_User(this, list_latJobs);
//        userRegisterQuery_rv.setAdapter(laJobs_Adaptor);

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


        rv_HotSellingProducts = findViewById(R.id.rv_HotSellingProducts);
        rv_UpcomingProducts = findViewById(R.id.rv_UpcomingProducts);
        rv_Offers = findViewById(R.id.rv_Offers);
        rv_OutdoorProducts = findViewById(R.id.rv_Outdoor_Products);
        rv_IndoorProducts = findViewById(R.id.rv_Indoor_Products);
        rv_SMPSDrivers = findViewById(R.id.rv_SMPSDrivers);

        LinearLayoutManager llm_HotSelling = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_HotSellingProducts.setLayoutManager(llm_HotSelling);
        list_hotSellingProducts = new ArrayList<>();
        adaptor_HotSellingProducts = new ProductsAdapter(getApplicationContext(), list_hotSellingProducts);
        rv_HotSellingProducts.setAdapter(adaptor_HotSellingProducts);

        LinearLayoutManager llm_Offers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_Offers.setLayoutManager(llm_Offers);
        list_Offers = new ArrayList<>();
        adaptor_Offers = new ProductsAdapter(getApplicationContext(), list_Offers);
        rv_Offers.setAdapter(adaptor_Offers);

        LinearLayoutManager llm_Upcoming = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_UpcomingProducts.setLayoutManager(llm_Upcoming);
        list_UpcomingProducts = new ArrayList<>();
        adaptor_UpcomingProducts = new ProductsAdapter(getApplicationContext(), list_UpcomingProducts);
        rv_UpcomingProducts.setAdapter(adaptor_UpcomingProducts);

        LinearLayoutManager llm_Outdoor = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_OutdoorProducts.setLayoutManager(llm_Outdoor);
        list_OutdoorProducts = new ArrayList<>();
        adaptor_OutdoorProducts = new ProductsSubCatAdapter(getApplicationContext(), list_OutdoorProducts);
        rv_OutdoorProducts.setAdapter(adaptor_OutdoorProducts);


        GridLayoutManager glm_Indoor = new GridLayoutManager(this, 2);
//        LinearLayoutManager llm_Indoor = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_IndoorProducts.setLayoutManager(glm_Indoor);
        list_IndoorProducts = new ArrayList<>();
        adaptor_IndoorProducts = new ProductsSubCatAdapter(getApplicationContext(), list_IndoorProducts);
        rv_IndoorProducts.setAdapter(adaptor_IndoorProducts);

        LinearLayoutManager llm_SMPS = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_SMPSDrivers.setLayoutManager(llm_SMPS);
        list_SMPSDrivers = new ArrayList<>();
        adaptor_SMPSDrivers = new ProductsSubCatAdapter(getApplicationContext(), list_SMPSDrivers);
        rv_SMPSDrivers.setAdapter(adaptor_SMPSDrivers);


        if (InternetConnection.checkConnection(getApplicationContext())) {
//            new GetAdminDataTaskEng().execute(activity);
            fetchHotSellingProducts();
            fetchUpcomingProducts();
            fetchLatestOffers();

            fetchOutdoorCategory();
            fetchIndoorCategory();
            fetchSMPSDrivers();
            getUpdatedData();

//            getData();
        } else {
            Toast.makeText(this,
                    "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
        }


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
////                }
//            }
//
//        });



        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_AllProducts);
        bottom_Navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btm_home:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                    case R.id.btm_AboutUs:
                        startActivity(new Intent(getApplicationContext(), AboutUs.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_AllProducts:

                        return true;
                    case R.id.btm_query:
                        startActivity(new Intent(getApplicationContext(), FetchProductActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
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

    public void search(String newText) {
        rv_FetchProduct.setVisibility(View.VISIBLE);
        ArrayList<Class_ListerCounter> list_SearchQuery = new ArrayList<>();
        for (Class_ListerCounter class_FetchData : list_FetchData) {
            if (class_FetchData.getModel_Name().toLowerCase().contains(newText.toLowerCase())) {
                list_SearchQuery.add(class_FetchData);
            }
        }

        adaptor_fetchdata.filterlist(list_SearchQuery);
        rv_FetchProduct.setVisibility(View.GONE);

//        Adaptor_User adapSearchJob= new Adaptor_User(this,list_SearchQuery);
//        userRegisterQuery_rv.setAdapter(adapSearchJob);
    }

//    public void search(String newText) {
//        ArrayList<Class_User> listSearchQues = new ArrayList<>();
//        for (Class_User classUserSearch : listSearchQues) {
//            if (classUserSearch.getUserid().toLowerCase().contains(newText.toLowerCase())) {
//                listSearchQues.add(classUserSearch);
//            }
//        }
//        Adaptor_User adapSearchJob = new Adaptor_User(this, listSearchQues);
//        rv_HotSellingProducts.setAdapter(adapSearchJob);
//    }

    private void fetchHotSellingProducts() {

        String URL_Products = URLNew + "?action=get";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_hotSellingProducts = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                String sno = product.getString("sno");
                                String productcode = product.getString("productcode");
                                String category = product.getString("category");
                                String subcategory = product.getString("subcategory");
                                String series = product.getString("series");
                                String model = product.getString("model");
                                String url = product.getString("url");
                                String hotselling = product.getString("hotselling");
                                String upcoming = product.getString("upcoming");
                                String offer = product.getString("offer");


//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){

                                if (hotselling.equals("yes")) {
                                    list_hotSellingProducts.add(new DataModel1(sno, productcode, category, subcategory, series, model, url, hotselling, upcoming, offer));

                                }
//                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

                            adaptor_HotSellingProducts = new ProductsAdapter(getApplicationContext(), list_hotSellingProducts);
                            rv_HotSellingProducts.setAdapter(adaptor_HotSellingProducts);
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Home2.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home2.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

//        adaptor_HotSellingProducts.setOnItemClickListener(new ProductsAdapter.OnItemClickListener() {
//            @Override
//            public void gotoProductDetails(int position, String title) {
//                Intent intent = new Intent(Home2.this, ProductDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("productcode", title);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

    private void fetchLatestOffers() {
        String URL_Products = URLNew + "?action=get";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_Offers = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                String sno = product.getString("sno");
                                String productcode = product.getString("productcode");
                                String category = product.getString("category");
                                String subcategory = product.getString("subcategory");
                                String series = product.getString("series");
                                String model = product.getString("model");
                                String url = product.getString("url");
                                String hotselling = product.getString("hotselling");
                                String upcoming = product.getString("upcoming");
                                String offer = product.getString("offer");


//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){
//                                list_Offers.add(new DataModel1(sno,productcode,series,model,url,hotselling,upcoming,offer));
//                                }
                                if (offer.equals("yes")) {
                                    list_Offers.add(new DataModel1(sno, productcode, category, subcategory, series, model, url, hotselling, upcoming, offer));

                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

                            adaptor_Offers = new ProductsAdapter(getApplicationContext(), list_Offers);
                            rv_Offers.setAdapter(adaptor_Offers);
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Home2.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home2.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    private void fetchUpcomingProducts() {
        String URL_Products = URLNew + "?action=get";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_UpcomingProducts = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                String sno = product.getString("sno");
                                String productcode = product.getString("productcode");
                                String category = product.getString("category");
                                String subcategory = product.getString("subcategory");
                                String series = product.getString("series");
                                String model = product.getString("model");
                                String url = product.getString("url");
                                String hotselling = product.getString("hotselling");
                                String upcoming = product.getString("upcoming");
                                String offer = product.getString("offer");


//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){

                                if (upcoming.equals("yes")) {
                                    list_UpcomingProducts.add(new DataModel1(sno, productcode, category, subcategory, series, model, url, hotselling, upcoming, offer));

                                }
//                                list_UpcomingProducts.add(new DataModel1(sno,productcode,series,model,url,hotselling,upcoming,offer));
//                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

                            adaptor_UpcomingProducts = new ProductsAdapter(getApplicationContext(), list_UpcomingProducts);
                            rv_UpcomingProducts.setAdapter(adaptor_UpcomingProducts);
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Home2.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home2.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    private void fetchOutdoorCategory() {
        list_OutdoorProducts.clear();
        String URL_Products = URL_Subcat + "?action=getsub";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_OutdoorProducts = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                String sno = product.getString("sno");
                                String category = product.getString("category");
                                String subcategory = product.getString("subcategory");
                                String url = product.getString("url");


//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){

                                if (category.equals("Outdoor")) {
                                    list_OutdoorProducts.add(new DataModel1(sno, category, subcategory, url));

                                }
//                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

                            adaptor_OutdoorProducts = new ProductsSubCatAdapter(getApplicationContext(), list_OutdoorProducts);
                            rv_OutdoorProducts.setAdapter(adaptor_OutdoorProducts);
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Home2.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home2.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

//        adaptor_HotSellingProducts.setOnItemClickListener(new ProductsAdapter.OnItemClickListener() {
//            @Override
//            public void gotoProductDetails(int position, String title) {
//                Intent intent = new Intent(Home2.this, ProductDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("productcode", title);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

    private void fetchIndoorCategory() {
        list_IndoorProducts.clear();
        String URL_Products = URL_Subcat + "?action=getsub";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_IndoorProducts = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                String sno = product.getString("sno");
                                String category = product.getString("category");
                                String subcategory = product.getString("subcategory");
                                String url = product.getString("url");


//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){

                                if (category.equals("Indoor")) {
                                    list_IndoorProducts.add(new DataModel1(sno, category, subcategory, url));

                                }
//                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

                            adaptor_IndoorProducts = new ProductsSubCatAdapter(getApplicationContext(), list_IndoorProducts);
                            rv_IndoorProducts.setAdapter(adaptor_IndoorProducts);
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Home2.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home2.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

//        adaptor_HotSellingProducts.setOnItemClickListener(new ProductsAdapter.OnItemClickListener() {
//            @Override
//            public void gotoProductDetails(int position, String title) {
//                Intent intent = new Intent(Home2.this, ProductDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("productcode", title);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

    private void fetchSMPSDrivers() {
        String URL_Products = URL_Subcat + "?action=getsub";
        list_SMPSDrivers.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            list_SMPSDrivers.clear();
                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_SMPSDrivers = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                String sno = product.getString("sno");
                                String category = product.getString("category");
                                String subcategory = product.getString("subcategory");
                                String url = product.getString("url");


//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){

                                if (category.equals("SMPS Driver")) {
                                    list_SMPSDrivers.add(new DataModel1(sno, category, subcategory, url));

                                }
//                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

                            adaptor_SMPSDrivers = new ProductsSubCatAdapter(getApplicationContext(), list_SMPSDrivers);
                            rv_SMPSDrivers.setAdapter(adaptor_SMPSDrivers);
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Home2.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home2.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

//        adaptor_HotSellingProducts.setOnItemClickListener(new ProductsAdapter.OnItemClickListener() {
//            @Override
//            public void gotoProductDetails(int position, String title) {
//                Intent intent = new Intent(Home2.this, ProductDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("productcode", title);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

    private void getUpdatedData() {

        dialogEng = new ProgressDialog(Home2.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Fetching Product Count");
        dialogEng.show();

        String URL_Products = URLFetchData + "?action=get";
        Class_User class_LatGovtJob = new Class_User();
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
                                String current_stock = product.getString("CURRENT STOCK");
                                String imageLink = product.getString("ImageLink");
                                String Date = product.getString("Date");


                                list_FetchData.add(new Class_ListerCounter(model_name, color, brand, current_stock, imageLink, Date));

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
                            Toast.makeText(Home2.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home2.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }




    private void askPermission() {
        ActivityCompat.requestPermissions(Home2.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }
            else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }






        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getDeviceInfo() {



//
//
//        final HashMap<String,Object> map= new HashMap<>();
////        map.put("action","addItem");
//        map.put("manufacturer", manufacturer);
//        map.put("brand", brand);
//        map.put("product", product);
//        map.put("model", model);
//        map.put("sdk", sdk);
//        map.put("versioncode", versioncode);
////                return parmas;
//
//        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("user_data");
//
//
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
////                                            long jobno= dataSnapshot.getChildrenCount();
//
//                reference.child("deviceInfo").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        Toast.makeText(Home2.this, "Device Info submitted successfully", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Home2.this, "Error: "+e.toString(),Toast.LENGTH_LONG).show();
//
//                    }
//                });

//
//                User_Class userClass = dataSnapshot.getValue(User_Class.class);
//                username.setText(userClass.getUsername());
//                if (userClass.getImageURL().equals("default")){
//                    profile_image.setImageResource(R.mipmap.ic_launcher);
//                } else {
//
//                    //change this
//                    Glide.with(getApplicationContext()).load(userClass.getImageURL()).into(profile_image);
////                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//
//        reference.child(FFO_Posted_Date+"_"+).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                Toast.makeText(Home2.this, "Job for: "+Job_C_Name+" submitted successfully", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


        String manufacturer = Build.MANUFACTURER;
        String brand        = Build.BRAND;
        String product      = Build.PRODUCT;
        String model        = Build.MODEL;
        String sdk        = String.valueOf(Build.VERSION.SDK_INT);
        String versioncode        = Build.VERSION.RELEASE;

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://script.google.com/macros/s/AKfycbyrZzg4U5dwsLwLuDqmGovaW6f3OtnuEHHgOCUEejxdIV0JKz0Vi4ZFzx1xkFQMJOrs/exec?",

                response -> Toast.makeText(Home2.this, "DeviceID Submitted Successfully1", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(Home2.this, "DeviceID Error", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
//                String uuser= firebaseAuth.getUid();
                //here we pass params
                parmas.put("action","addDDetails");
                parmas.put("manufacturer", manufacturer);
                parmas.put("brand", brand);
                parmas.put("product", product);
                parmas.put("model", model);
                parmas.put("sdk", sdk);
                parmas.put("versioncode", versioncode);


                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(Home2.this);
        queue.add(stringRequest);
//        ClearData() ;
//        Toast.makeText(this, "Job Submitted Successfully", Toast.LENGTH_SHORT).show();
//        Toast.makeText(Home2.this, "DeviceID submitted successfully", Toast.LENGTH_SHORT).show();

//                                    Toast.makeText(Home2.this, "Lagitude :" +addresses.get(0).getLatitude()+"\n"+"Longitude :"+addresses.get(0).getLongitude()
//                                            +"\n"+"Address :"+addresses.get(0).getAddressLine(0)+"\n"+"City :"+addresses.get(0).getLocality()+"\n"+"Country :"+addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();





    }
    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location !=null){
                                Geocoder geocoder=new Geocoder(Home2.this, Locale.getDefault());
                                List<Address> addresses= null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    String latitude=""+addresses.get(0).getLatitude();
                                    String longitude=""+addresses.get(0).getLongitude();
                                    String address=""+addresses.get(0).getAddressLine(0);
                                    String city=""+addresses.get(0).getLocality();
                                    String country=""+addresses.get(0).getCountryName();

                                    final HashMap<String,Object> map= new HashMap<>();
//        map.put("action","addItem");
                                    map.put("latitude", latitude);

                                    map.put("longitude", longitude);
                                    map.put("address", address);
                                    map.put("city", city);
                                    map.put("country", country);
//                return parmas;

                                    final DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("user_data");


                                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                                            long jobno= dataSnapshot.getChildrenCount();

                                            reference.child("location").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    Toast.makeText(Home2.this, "Location submitted successfully", Toast.LENGTH_SHORT).show();


                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(Home2.this, "Error: "+e.toString(),Toast.LENGTH_LONG).show();

                                                }
                                            });

//
//                User_Class userClass = dataSnapshot.getValue(User_Class.class);
//                username.setText(userClass.getUsername());
//                if (userClass.getImageURL().equals("default")){
//                    profile_image.setImageResource(R.mipmap.ic_launcher);
//                } else {
//
//                    //change this
//                    Glide.with(getApplicationContext()).load(userClass.getImageURL()).into(profile_image);
//                }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

//
//        reference.child(FFO_Posted_Date+"_"+).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                Toast.makeText(Home2.this, "Job for: "+Job_C_Name+" submitted successfully", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });




                                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                                            "https://script.google.com/macros/s/AKfycbxuhuClqRcUCk8MEhIMEaErsqxS9wcrk1rIIn4SZ1m1zOO0llKS/exec",
//                                            "https://script.google.com/macros/s/AKfycbyrZzg4U5dwsLwLuDqmGovaW6f3OtnuEHHgOCUEejxdIV0JKz0Vi4ZFzx1xkFQMJOrs/exec?",
                                            "https://script.google.com/macros/s/AKfycbyrZzg4U5dwsLwLuDqmGovaW6f3OtnuEHHgOCUEejxdIV0JKz0Vi4ZFzx1xkFQMJOrs/exec?",

                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    Toast.makeText(Home2.this, "Location Submitted Successfully1", Toast.LENGTH_SHORT).show();

                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(Home2.this, "Location Error", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                    ) {
                                        @Override
                                        protected Map<String, String> getParams() {
                                            Map<String, String> parmas = new HashMap<>();
//                String uuser= firebaseAuth.getUid();
                                            //here we pass params
                                            parmas.put("action","addLDetails");
                                            parmas.put("latitude", latitude);
                                            parmas.put("longitude", longitude);
                                            parmas.put("address", address);
                                            parmas.put("city", city);
                                            parmas.put("country", country);


                                            return parmas;
                                        }
                                    };

                                    int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
                                    RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                                    stringRequest.setRetryPolicy(retryPolicy);

                                    RequestQueue queue = Volley.newRequestQueue(Home2.this);
                                    queue.add(stringRequest);
//        ClearData() ;
//        Toast.makeText(this, "Job Submitted Successfully", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Home2.this, "Location submitted successfully", Toast.LENGTH_SHORT).show();

//                                    Toast.makeText(Home2.this, "Lagitude :" +addresses.get(0).getLatitude()+"\n"+"Longitude :"+addresses.get(0).getLongitude()
//                                            +"\n"+"Address :"+addresses.get(0).getAddressLine(0)+"\n"+"City :"+addresses.get(0).getLocality()+"\n"+"Country :"+addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }

                        }
                    });


        }else
        {

            askPermission();

        }
    }

}


