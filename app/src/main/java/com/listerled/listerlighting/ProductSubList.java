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

public class ProductSubList extends AppCompatActivity {

//    private final String URLNew="https://script.google.com/macros/s/AKfycbxfzahcYCoiZ3otW-1r6rPwMiGYZJp-m49CNxEboN-1j_U_JVtsBYf_E0b5DPuSuWXWCA/exec";
    String category, subcategory;
    RecyclerView rv_ProductSubCat;
    ProductsAdapter adaptor_ProductSubCat;
    List<DataModel1> list_ProductSubCat;
    BottomNavigationView bottom_Navigation;
    TextView tv_title,tv_category,tv_series,tv_model,tv_shape;

    ProgressDialog dialogEng;
    private final String URLNew = "https://script.google.com/macros/s/AKfycbyqK43fBc8lCt4smuLrFhjVEUR9ArYT9vyaRZvm2V6ChmZ7FuBuNNWLrybO0IOFpSY8/exec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_sub_list);


        Bundle bundle = getIntent().getExtras();
        category = bundle.getString("category", "");
        subcategory = bundle.getString("subcategory", "");
        String title= category +" - "+ subcategory;

//        Toast.makeText(this, "productCode="+productCode, Toast.LENGTH_SHORT).show();

        productDesc(subcategory, URLNew);
//
        tv_title = findViewById(R.id.tv_title);
//        tv_category = findViewById(R.id.tv_category);
//        tv_series = findViewById(R.id.tv_series);
//        tv_model = findViewById(R.id.tv_model);
//        tv_shape = findViewById(R.id.tv_shape);
        tv_title.setText(title);

        rv_ProductSubCat = findViewById(R.id.rv_ProductSubCat);

//        LinearLayoutManager llm_HotSelling = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        rv_ProductSubCat.setLayoutManager(gridLayoutManager);
        list_ProductSubCat = new ArrayList<>();
        adaptor_ProductSubCat = new ProductsAdapter(getApplicationContext(), list_ProductSubCat);
        rv_ProductSubCat.setAdapter(adaptor_ProductSubCat);


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

    private void productDesc(String productCode, String URLN) {
        dialogEng = new ProgressDialog(ProductSubList.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Product List");
        dialogEng.show();
        String URL_Products=URLN+"?action=get";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONObject object = new JSONObject(response);
                            JSONArray array  = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
                            list_ProductSubCat = new ArrayList<>();
                            // traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                // getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                String sno= product.getString("sno");
                                String productcode= product.getString("productcode");
                                String category= product.getString("category");
                                String subcategory= product.getString("subcategory");
                                String series= product.getString("series");
                                String model= product.getString("model");
                                String url= product.getString("url");
                                String hotselling= product.getString("hotselling");
                                String upcoming= product.getString("upcoming");
                                String offer= product.getString("offer");
//
//                                String sr_No=product.getString("sr_No");
//                                String product_Code=product.getString("product_Code");
//                                String category=product.getString("category");
//                                String series=product.getString("series");
//                                String model=product.getString("model");
//                                String shape=product.getString("shape");
//                                String watt=product.getString("watt");
//                                String power_factor=product.getString("power_factor");
//                                String withstand_voltage=product.getString("withstand_voltage");
//                                String surge_protection=product.getString("surge_protection");
//                                String lumen=product.getString("lumen");
//                                String ip_rating=product.getString("ip_rating");
//                                String size=product.getString("size");
//                                String cut_out_size=product.getString("cut_out_size");
//                                String housing_type=product.getString("housing_type");
//                                String colour=product.getString("colour");
//                                String dia=product.getString("dia");
//                                String no_of_led=product.getString("no_of_led");
//                                String lumen_per_led=product.getString("lumen_per_led");
//                                String led_package=product.getString("led_package");
//                                String total_length=product.getString("total_length");
//                                String lumen_per_mtr=product.getString("lumen_per_mtr");
//                                String smd=product.getString("smd");
//                                String finish_product_size=product.getString("finish_product_size");
//                                String finish_product_weight=product.getString("finish_product_weight");
//                                String remarks=product.getString("remarks");
//                                String product_specification=product.getString("product_specification");
//                                String output_voltage=product.getString("output_voltage");
//                                String output_current=product.getString("output_current");
//                                String thd=product.getString("thd");

                                if (subcategory.equals(productCode)) {
                                    list_ProductSubCat.add(new DataModel1(sno, productcode, category, subcategory, series, model, url, hotselling, upcoming, offer));
                                }

//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){

//                                if (product_Code.equals(productCode)){
//                                    list_ProductDesc.add(new DataDescModel(sr_No,
//                                            product_Code,
//                                            category,
//                                            series,
//                                            model,
//                                            shape,
//                                            watt,
//                                            power_factor,
//                                            withstand_voltage,
//                                            surge_protection,
//                                            lumen,
//                                            ip_rating,
//                                            size,
//                                            cut_out_size,
//                                            housing_type,
//                                            colour,
//                                            dia,
//                                            no_of_led,
//                                            lumen_per_led,
//                                            led_package,
//                                            total_length,
//                                            lumen_per_mtr,
//                                            smd,
//                                            finish_product_size,
//                                            finish_product_weight,
//                                            remarks,
//                                            product_specification,
//                                            output_voltage,
//                                            output_current,
//                                            thd));
//
//                                }
//                                }


                            }


                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), ProductSubList);

                            adaptor_ProductSubCat = new ProductsAdapter(getApplicationContext(), list_ProductSubCat);
                            rv_ProductSubCat.setAdapter(adaptor_ProductSubCat);
                            dialogEng.dismiss();
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), ProductSubList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialogEng.dismiss();
                            Toast.makeText(ProductSubList.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductSubList.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }


}