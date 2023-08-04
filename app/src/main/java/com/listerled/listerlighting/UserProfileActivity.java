package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {
    ProgressDialog dialogEng;
    BottomNavigationView bottom_Navigation;
    Button btn_Login,btn_Logout,btn_NewOrder;
    TextView tv_PartyName,tv_Address,tv_City,tv_State,tv_OrderHistory;

    LinearLayout ll_userProfile;
    String urlNew="https://script.google.com/macros/s/AKfycbzpBQHf3aq3zZMJaVtKYtUfnb_T77i5xdtLV2GFrISN54Hwb2Nz2W6HM6IbyZaHYB1I/exec";
//    String urlNew="https://script.google.com/macros/s/AKfycbzpBQHf3aq3zZMJaVtKYtUfnb_T77i5xdtLV2GFrISN54Hwb2Nz2W6HM6IbyZaHYB1I/exec";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        btn_Login=findViewById(R.id.btn_Login);
        btn_NewOrder=findViewById(R.id.btn_NewOrder);
        btn_Logout=findViewById(R.id.btn_Logout);

        tv_PartyName=findViewById(R.id.tv_PartyName);
        tv_Address=findViewById(R.id.tv_Address);
        tv_City=findViewById(R.id.tv_City);
        tv_State=findViewById(R.id.tv_State);
        tv_OrderHistory=findViewById(R.id.tv_OrderHistory);

        ll_userProfile=findViewById(R.id.ll_userProfile);


//        Intent intent;
        SessionManagement sessionManagement = new SessionManagement(this);
        int userId = sessionManagement.getSession();
        String userName = sessionManagement.getSSession();
        String userCity = sessionManagement.getSSSession();
        String data=userName+"_"+userCity;
        Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();

        if (userId==(-1)) {
            btn_Login.setVisibility(View.VISIBLE);
            btn_NewOrder.setVisibility(View.GONE);
            ll_userProfile.setVisibility(View.GONE);
            tv_OrderHistory.setVisibility(View.GONE);
            //user id logged in and so move to mainActivity


        } else {
            btn_Login.setVisibility(View.GONE);
            tv_OrderHistory.setVisibility(View.VISIBLE);
            btn_NewOrder.setVisibility(View.VISIBLE);
            ll_userProfile.setVisibility(View.VISIBLE);
            GetUserData(userId);

        }

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, Login1Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, HomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                sessionManagement.removeSession();
                sessionManagement.removeSSession();
                sessionManagement.removeSSSession();

                finish();

            }
        });
        btn_NewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, PlaceOrder.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


                finish();

            }
        });
        tv_OrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, PlaceOrder.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });




        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_profile);
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
                        startActivity(new Intent(getApplicationContext(), Home2.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_query:
                        startActivity(new Intent(getApplicationContext(), FetchProductActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                    case R.id.btm_profile:

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

    private void GetUserData(int ida) {
        dialogEng = new ProgressDialog(UserProfileActivity.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Profile Data");
        dialogEng.show();
//        Toast.makeText(this, "a"+ida, Toast.LENGTH_SHORT).show();
        String URL_Products = urlNew + "?action=getId&id="+ida;
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
                                String sr_No = product.getString("sr_No");
                                String category = product.getString("category");
                                String party_name = product.getString("party_name");
                                String city = product.getString("city");
                                String state = product.getString("state");
                                String address = product.getString("address");

//                                Toast.makeText(UserProfileActivity.this,party_name+city+state+address,Toast.LENGTH_SHORT).show();
                                tv_PartyName.setText(party_name);
                                        tv_Address.setText(address);
                                        tv_City.setText(city);
                                        tv_State.setText(state);



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
                            Toast.makeText(UserProfileActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserProfileActivity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        dialogEng.dismiss();

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
}