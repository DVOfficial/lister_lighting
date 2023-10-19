package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaceOrder extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    ProgressDialog dialogEng;
    Spinner spinner_ProductID, spinner_ProductWatt, spinnerSeries;
    ArrayList<String> countryList = new ArrayList<>();
    ArrayList<String> cityList = new ArrayList<>();
    ArrayAdapter<String> countryAdapter;
    ArrayAdapter<String> cityAdapter;
    RequestQueue requestQueue;
    TextView tv_color,tvt_color,tv_PartyName,tv_Address,tv_City,tvt_watt;
    EditText et_ColorChooser,et_Quantity,et_Remarks,et_ProductType;
    Button btn_submitOrder,btn_Login;
    String userName;
    String userCity;
    String url = "https://script.google.com/macros/s/AKfycbwmIluM6NWscmZkVtkzicrmz5ZTvIK99ela2ZDS2Welf79w8D0X8VYr822yUyJRje99Eg/exec";
    String urlUser ="https://script.google.com/macros/s/AKfycbzpBQHf3aq3zZMJaVtKYtUfnb_T77i5xdtLV2GFrISN54Hwb2Nz2W6HM6IbyZaHYB1I/exec";

    BottomNavigationView bottom_Navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder);

        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_AllProducts);
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


        requestQueue = Volley.newRequestQueue(this);
        spinner_ProductID = findViewById(R.id.spinner_ProductID);
        spinner_ProductWatt = findViewById(R.id.spinner_ProductWatt);
        tv_color = findViewById(R.id.tv_color);
        tvt_color = findViewById(R.id.tvt_color);
        tv_PartyName = findViewById(R.id.tv_PartyName);
        tv_City = findViewById(R.id.tv_City);
        tv_Address   = findViewById(R.id.tv_Address);
        tvt_watt   = findViewById(R.id.tvt_watt);
        et_ColorChooser = findViewById(R.id.et_ColorChooser);
        et_Quantity = findViewById(R.id.et_Quantity);
        et_Remarks = findViewById(R.id.et_Remarks);
        btn_submitOrder = findViewById(R.id.btn_submitOrder);
        btn_Login = findViewById(R.id.btn_Login);


        spinner_ProductID.setPrompt("Click here to select Product!");
        spinner_ProductWatt.setPrompt("Click here to select watt!");




        spinner_ProductID.setOnItemSelectedListener(this);

        btn_submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partyName = tv_PartyName.getText().toString().trim();
                String partyAddress = tv_Address.getText().toString().trim();
                String partyCity = tv_City.getText().toString().trim();
                String itemName = spinner_ProductID.getSelectedItem().toString();
                String itemWatt = spinner_ProductWatt.getSelectedItem().toString();
                String color=et_ColorChooser.getText().toString().trim();
                String quantity=et_Quantity.getText().toString().trim();
                String remarks = et_Remarks.getText().toString().trim();
                String productType = et_ProductType.getText().toString().trim();

                SubmitOrder(color,quantity,itemName,itemWatt,remarks,partyName,partyCity,partyAddress,productType);
//                Toast.makeText(PlaceOrder.this,color+quantity+itemName+itemWatt,Toast.LENGTH_SHORT).show();
            }
        });

//        spinnerCountry.setOnItemSelectedListener(this);



    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }

    private void checkUserStatus() {
        SessionManagement sessionManagement = new SessionManagement(PlaceOrder.this);
        int userId = sessionManagement.getSession();
        userName = sessionManagement.getSSession();
        userCity = sessionManagement.getSSSession();
        Toast.makeText(this, "show"+userId, Toast.LENGTH_SHORT).show();
        String id=userName+"_"+userCity;
        if (userId==(-1)) {

//            current_stock="Login";
            btn_Login.setVisibility(View.VISIBLE);
            btn_Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlaceOrder.this, Login1Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });
            //user id logged in and so move to mainActivity
//                                    moveToMainActivity();

        }else{
            btn_Login.setVisibility(View.GONE);
            fetchProductList();
            GetUserData(userId);
        }
    }

    private void SubmitOrder(String color, String quantity, String itemName, String itemWatt, String remarks, String username, String usercity, String useraddress, String productType) {


//        String url2="https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?" +
//                "action=addNewOrder&vendorName=1&address=2&productcode_model=3&watt=4&color=5&quantity=6&remarks=7";
        String url2="https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?";
        url2=url2+"action=addNewOrder&vendorName="+username+"&city="+usercity+"&address="+useraddress+"&productcode_model="+
                itemName+"&watt="+itemWatt+"&producttype="+productType+"&color="+color+"&quantity="+quantity+"&remarks="+remarks;

//        Toast.makeText(this, "a"+itemName+itemWatt+color+quantity+remarks+username+usercity, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url2,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PlaceOrder.this, "Order Submitted Successfully", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlaceOrder.this, "Order Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(PlaceOrder.this);
        queue.add(stringRequest);
        ClearData() ;
//        Toast.makeText(this, "Job Submitted Successfully", Toast.LENGTH_SHORT).show();
//        Toast.makeText(PlaceOrder.this, "Order submitted successfully", Toast.LENGTH_SHORT).show();

    }

    private void ClearData() {

        et_ColorChooser.setText("");
        et_Quantity.setText("");
        et_Remarks.setText("");

    }
//    private void SubmitOrder(String color, String quantity, String itemName, String itemWatt, String remarks, String username, String usercity) {
//
//
////        Toast.makeText(this, "a"+itemName+itemWatt+color+quantity+remarks, Toast.LENGTH_SHORT).show();
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                "https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?",
////                String url2="https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?";
////        url2=url2+"action=addNewOrder&vendorName="+username+"&address="+usercity+"&productcode_model="+
////                itemName+"&watt="+itemWatt+"&color="+color+"&quantity="+quantity+"&remarks="+remarks;
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(PlaceOrder.this, "Order Submitted Successfully1", Toast.LENGTH_SHORT).show();
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(PlaceOrder.this, "Order Error", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parmas = new HashMap<>();
////                String uuser= firebaseAuth.getUid();
//                //here we pass params
//                parmas.put("action","addNewOrder");
//                parmas.put("vendorName",username);
//                parmas.put("address",usercity);
//                parmas.put("productcode_model", itemName);
//                parmas.put("watt", itemWatt);
//                parmas.put("color", color);
//                parmas.put("quantity", quantity);
//                parmas.put("remarks", remarks);
//                return parmas;
//            }
//        };
//
//        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
//        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(retryPolicy);
//
//        RequestQueue queue = Volley.newRequestQueue(PlaceOrder.this);
//        queue.add(stringRequest);
////        ClearData() ;
////        Toast.makeText(this, "Job Submitted Successfully", Toast.LENGTH_SHORT).show();
////        Toast.makeText(PlaceOrder.this, "Order submitted successfully", Toast.LENGTH_SHORT).show();
//
//    }

    private void fetchProductList() {
        dialogEng = new ProgressDialog(PlaceOrder.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Profile Data");
        dialogEng.show();
        String URL_Products = url + "?action=get";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

                            countryList = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject product = array.getJSONObject(i);
                                String productcode_model = product.getString("productcode_model");

                                countryList.add(productcode_model);
                                countryAdapter = new ArrayAdapter<>(PlaceOrder.this,
                                        android.R.layout.simple_spinner_item, countryList);
                                countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner_ProductID.setAdapter(countryAdapter);

//                                }


                            }
                            dialogEng.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialogEng.dismiss();
                            Toast.makeText(PlaceOrder.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlaceOrder.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        dialogEng.dismiss();

    }

    private void GetUserData(int id) {
        dialogEng = new ProgressDialog(PlaceOrder.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Profile Data");
        dialogEng.show();
        String URL_Products = urlUser + "?action=getId&id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
//                            list_hotSellingProducts = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
//                                String sr_No = product.getString("sr_No");
//                                String category = product.getString("category");
                                String party_name = product.getString("party_name");
                                String city = product.getString("city");
//                                String state = product.getString("state");
                                String address = product.getString("address");

                                tv_PartyName.setText(party_name);
                                tv_Address.setText(address);
                                tv_City.setText(city);
//                                tv_State.setText(state);



//                                }


                            }

                            dialogEng.dismiss();
                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

//                            adaptor_HotSellingProducts = new ProductsAdapter(getApplicationContext(), list_hotSellingProducts);
//                            rv_HotSellingProducts.setAdapter(adaptor_HotSellingProducts);
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialogEng.dismiss();
                            Toast.makeText(PlaceOrder.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlaceOrder.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        dialogEng.dismiss();

                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        dialogEng.dismiss();
//        adaptor_HotSellingProducts.setOnItemClickListener(new ProductsAdapter.OnItemClickListener() {
//            @Override
//            public void gotoProductDetails(int position, String title) {
//                Intent intent = new Intent(AllProducts.this, ProductDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("productcode", title);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        String selectedCountry = adapterView.getSelectedItem().toString();
        Toast.makeText(this, ""+selectedCountry, Toast.LENGTH_SHORT).show();
        if(adapterView.getId() == R.id.spinner_ProductID){
            spinner_ProductWatt.setVisibility(View.VISIBLE);
            tvt_watt.setVisibility(View.VISIBLE);
            cityList.clear();
            dialogEng = new ProgressDialog(PlaceOrder.this);
            dialogEng.setTitle("Wait Please... ");
            dialogEng.setMessage("Loading Product-Watt");
            dialogEng.show();
            String url = "https://script.google.com/macros/s/AKfycbyJ4KNkLNQVrvuI8M7MUemojs1LvTegFAKuI6oCKRtnmVztbcC7JD2D58f1A2C1fcB_/exec?action=getS1Data&id="+selectedCountry;
//            String url = "https://script.google.com/macros/s/AKfycbyJ4KNkLNQVrvuI8M7MUemojs1LvTegFAKuI6oCKRtnmVztbcC7JD2D58f1A2C1fcB_/exec?action=getS1Data&id="+selectedCountry;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                //converting the string to json array object

                                JSONObject object = new JSONObject(response);
                                JSONArray array = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
//                                cityList = new ArrayList<>();
                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject product = array.getJSONObject(i);
                                    String cityName = product.getString("watt");
                                    String color = product.getString("colour");

                                    cityList.add(cityName);
                                    cityAdapter = new ArrayAdapter<>(PlaceOrder.this,
                                            android.R.layout.simple_spinner_item, cityList);
                                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinner_ProductWatt.setAdapter(cityAdapter);
                                    if (!color.equals("")){
                                        tvt_color.setVisibility(View.VISIBLE);
                                        tv_color.setVisibility(View.VISIBLE);
                                        tv_color.setText(color);
                                    }
//                                }
                                    dialogEng.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                dialogEng.dismiss();
                                Toast.makeText(PlaceOrder.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PlaceOrder.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}