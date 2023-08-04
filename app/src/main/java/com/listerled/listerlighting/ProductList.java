package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import java.util.List;

public class ProductList extends AppCompatActivity  {

//    private final String URLNew="https://script.google.com/macros/s/AKfycbxfzahcYCoiZ3otW-1r6rPwMiGYZJp-m49CNxEboN-1j_U_JVtsBYf_E0b5DPuSuWXWCA/exec";
    private final String URLNew="https://script.google.com/macros/s/AKfycbxfzahcYCoiZ3otW-1r6rPwMiGYZJp-m49CNxEboN-1j_U_JVtsBYf_E0b5DPuSuWXWCA/exec";
    String productCode,model;
    RecyclerView rv_ProductDesc;
    ProductsListAdapter adaptor_ProductDesc;
    List<DataDescModel> list_ProductDesc;
    BottomNavigationView bottom_Navigation;
    TextView tv_title,tv_category,tv_series,tv_model,tv_shape;

    ProgressDialog dialogEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Bundle bundle = getIntent().getExtras();
        productCode = bundle.getString("productcode", "");
        model = bundle.getString("model", "");
        String title= productCode +" "+model;

//        Toast.makeText(this, "productCode="+productCode, Toast.LENGTH_SHORT).show();

        productDesc(productCode);
//
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);

        rv_ProductDesc = findViewById(R.id.rv_ProductSubCat);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        rv_ProductDesc.setLayoutManager(gridLayoutManager);
        list_ProductDesc = new ArrayList<>();



        bottom_Navigation=findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_home);
        bottom_Navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btm_home:

                        return true;

//                    case R.id.btm_allproduct:
//                        startActivity(new Intent(getApplicationContext(),AllProductsActivity.class));
//                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                        finish();
//                        return true;
                    case R.id.btm_query:
                        startActivity(new Intent(getApplicationContext(), FetchProductActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_profile:
                        startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
//                    case R.id.btm_settings:
//                        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
//                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                        finish();
//                        return true;

                }
                return false;
            }
        });


    }

    private void productDesc(String productCode) {
        dialogEng = new ProgressDialog(ProductList.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Product List");
        dialogEng.show();
        String URL_Products=URLNew+"?action=getDataDes";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array  = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_ProductDesc = new ArrayList<>();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                String sr_No=product.getString("sr_No");
                                String product_Code=product.getString("product_Code");
                                String category=product.getString("category");
                                String series=product.getString("series");
                                String model=product.getString("model");
                                String shape=product.getString("shape");
                                String watt=product.getString("watt");
                                String power_factor=product.getString("power_factor");
                                String withstand_voltage=product.getString("withstand_voltage");
                                String surge_protection=product.getString("surge_protection");
                                String lumen=product.getString("lumen");
                                String ip_rating=product.getString("ip_rating");
                                String size=product.getString("size");
                                String cut_out_size=product.getString("cut_out_size");
                                String housing_type=product.getString("housing_type");
                                String colour=product.getString("colour");
                                String dia=product.getString("dia");
                                String no_of_led=product.getString("no_of_led");
                                String lumen_per_led=product.getString("lumen_per_led");
                                String led_package=product.getString("led_package");
                                String total_length=product.getString("total_length");
                                String lumen_per_mtr=product.getString("lumen_per_mtr");
                                String smd=product.getString("smd");
                                String finish_product_size=product.getString("finish_product_size");
                                String finish_product_weight=product.getString("finish_product_weight");
                                String remarks=product.getString("remarks");
                                String product_specification=product.getString("product_specification");
                                String output_voltage=product.getString("output_voltage");
                                String output_current=product.getString("output_current");
                                String thd=product.getString("thd");
                                String combn=product.getString("combn");
                                String url1=product.getString("url1");
                                String url2=product.getString("url2");
                                String url3=product.getString("url3");
                                String url4=product.getString("url4");


//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){

                                if (product_Code.equals(productCode)){
                                    list_ProductDesc.add(new DataDescModel(sr_No,
                                            product_Code,
                                            category,
                                            series,
                                            model,
                                            shape,
                                            watt,
                                            power_factor,
                                            withstand_voltage,
                                            surge_protection,
                                            lumen,
                                            ip_rating,
                                            size,
                                            cut_out_size,
                                            housing_type,
                                            colour,
                                            dia,
                                            no_of_led,
                                            lumen_per_led,
                                            led_package,
                                            total_length,
                                            lumen_per_mtr,
                                            smd,
                                            finish_product_size,
                                            finish_product_weight,
                                            remarks,
                                            product_specification,
                                            output_voltage,
                                            output_current,
                                            thd,
                                            combn,
                                            url1,
                                            url2,
                                            url3,
                                            url4));

                                }
//                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

                            adaptor_ProductDesc = new ProductsListAdapter(getApplicationContext(), list_ProductDesc);
                            rv_ProductDesc.setAdapter(adaptor_ProductDesc);
                            dialogEng.dismiss();
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialogEng.dismiss();
                            Toast.makeText(ProductList.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductList.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }

}