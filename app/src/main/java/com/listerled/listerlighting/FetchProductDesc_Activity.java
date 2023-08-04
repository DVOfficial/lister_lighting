package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FetchProductDesc_Activity extends AppCompatActivity {
    //    private final String URLNew="https://script.google.com/macros/s/AKfycbxfzahcYCoiZ3otW-1r6rPwMiGYZJp-m49CNxEboN-1j_U_JVtsBYf_E0b5DPuSuWXWCA/exec";
    private final String URLNew="https://script.google.com/macros/s/AKfycbxfzahcYCoiZ3otW-1r6rPwMiGYZJp-m49CNxEboN-1j_U_JVtsBYf_E0b5DPuSuWXWCA/exec";
    String modelname,modelwatt;
    RecyclerView rv_ProductDesc;
    ProductsListAdapter adaptor_ProductDesc;
    List<DataDescModel> list_ProductDesc;
    BottomNavigationView bottom_Navigation;


    ProgressDialog dialogEng;



    String sr_No,product_Code,category,series, modelname1,shape,watt,power_factor,withstand_voltage,surge_protection,lumen,ip_rating,
            size,cut_out_size,housing_type,colour,dia,no_of_led,lumen_per_led,led_package,total_length,lumen_per_mtr,smd,finish_product_size,finish_product_weight,
            remarks,product_specification,output_voltage,output_current,thd,url1,url2,url3,url4;

    TextView tv_sr_No,tv_product_Code,tv_category,tv_series,tv_model,tv_shape,tv_watt,tv_power_factor,tv_withstand_voltage,tv_surge_protection,tv_lumen,tv_ip_rating,
            tv_size,tv_cut_out_size,tv_housing_type,tv_colour,tv_dia,tv_no_of_led,tv_lumen_per_led,tv_led_package,tv_total_length,tv_lumen_per_mtr,tv_smd,tv_finish_product_size,tv_finish_product_weight,
            tv_remarks,tv_product_specification,tv_output_voltage,tv_output_current,tv_thd,tv_title,tvt_watt,tvt_shape,tvt_series,

    tvt_model,tvt_power_factor,tvt_withstand_voltage,tvt_surge_protection,tvt_lumen,tvt_ip_rating,
            tvt_size,tvt_cut_out_size,tvt_housing_type,tvt_colour,tvt_dia,tvt_no_of_led,tvt_lumen_per_led,tvt_led_package,tvt_total_length,tvt_lumen_per_mtr,tvt_smd,tvt_finish_product_size,tvt_finish_product_weight,
            tvt_remarks,tvt_product_specification,tvt_output_voltage,tvt_output_current,tvt_thd,tvt_title;


    LinearLayout ll_Color,ll_shape,ll_power_factor,ll_withstand_voltage,ll_surge_protection,ll_lumen,ll_ip_rating,
            ll_size,ll_cut_out_size,ll_housing_type,ll_dia,ll_no_of_led,ll_lumen_per_led,ll_led_package,ll_total_length,ll_lumen_per_mtr,ll_finish_product_size,ll_finish_product_weight,
            ll_product_specification,ll_output_voltage,ll_output_current,ll_thd,ll_smd;

    View v_power_factor,v_withstand_voltage,v_surge_protection,v_lumen,v_ip_rating,v_thd,
            v_size,v_cut_out_size,v_housing_type,v_dia,v_no_of_led,v_lumen_per_led,v_led_package,v_total_length,v_lumen_per_mtr,v_finish_product_size,v_finish_product_weight,
            v_product_specification,v_output_voltage,v_output_current,v_smd;



    LinearLayout llh_pfwvsplovocthd,llh_nollpllpmlp,llh_SCOSFPSFPWHT, llh_IPR_D_TL,llh_W_S,llh_TL_S;

    ImageView iv_productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_product_desc);

        indit();


        Bundle bundle = getIntent().getExtras();
        modelname = bundle.getString("modelname", "");
        modelname1 = bundle.getString("modelname1", "");
        modelwatt = bundle.getString("watt", "");
        String model= modelname +"-"+ modelname1;

//        Toast.makeText(this, "productCode="+productCode, Toast.LENGTH_SHORT).show();

        productDesc(model, modelwatt);

        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(model+"-"+modelwatt);

//        rv_ProductDesc = findViewById(R.id.rv_ProductSubCat);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

//        rv_ProductDesc.setLayoutManager(gridLayoutManager);
//        list_ProductDesc = new ArrayList<>();



        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_query);
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



    private void productDesc(String productCodeA, String modelwattA) {
        indit();
        dialogEng = new ProgressDialog(FetchProductDesc_Activity.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Loading Product List");
        dialogEng.show();
        String URL_Products=URLNew+"?action=get&id="+productCodeA+"&watt="+modelwattA;
//        String URL_Products=URLNew+"?action=get&id="+productCodeA+"&watt="+modelwattA;
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
//                                String sno= product.getString("sno");
//                                String productcode= product.getString("productcode");
//                                String category= product.getString("category");
//                                String series= product.getString("series");
//                                String model= product.getString("model");
//                                String url= product.getString("url");
//                                String hotselling= product.getString("hotselling");
//                                String upcoming= product.getString("upcoming");
//                                String offer= product.getString("offer");

                                String sr_No1 = product.getString("sr_No");
                                String product_Code1 = product.getString("product_Code");
                                String category1 = product.getString("category");
                                String series1 = product.getString("series");
                                String model1 = product.getString("model");
                                String shape1 = product.getString("shape");
                                String watt1 = product.getString("watt");
                                String power_factor1 = product.getString("power_factor");
                                String withstand_voltage1 = product.getString("withstand_voltage");
                                String surge_protection1 = product.getString("surge_protection");
                                String lumen1 = product.getString("lumen");
                                String ip_rating1 = product.getString("ip_rating");
                                String size1 = product.getString("size");
                                String cut_out_size1 = product.getString("cut_out_size");
                                String housing_type1 = product.getString("housing_type");
                                String colour1 = product.getString("colour");
                                String dia1 = product.getString("dia");
                                String no_of_led1 = product.getString("no_of_led");
                                String lumen_per_led1 = product.getString("lumen_per_led");
                                String led_package1 = product.getString("led_package");
                                String total_length1 = product.getString("total_length");
                                String lumen_per_mtr1 = product.getString("lumen_per_mtr");
                                String smd1 = product.getString("smd");



                                String finish_product_size1 = product.getString("finish_product_size");
                                String finish_product_weight1 = product.getString("finish_product_weight");
                                String remarks1 = product.getString("remarks");
                                String product_specification1 = product.getString("product_specification");
                                String output_voltage1 = product.getString("output_voltage");
                                String output_current1 = product.getString("output_current");
                                String thd1 = product.getString("thd");
                                String combn1= product.getString("combn");
                                String url11 = product.getString("url1");
                                String url21 = product.getString("url2");
                                String url31 = product.getString("url3");
                                String url41 = product.getString("url4");



                                        subject(sr_No1,product_Code1,category1,series1,model1,shape1,watt1,power_factor1,withstand_voltage1,
                                                surge_protection1,lumen1, ip_rating1, size1, cut_out_size1, housing_type1,colour1,dia1,
                                                no_of_led1,lumen_per_led1,led_package1,total_length1,lumen_per_mtr1,smd1,finish_product_size1,
                                                finish_product_weight1,remarks1,product_specification1,output_voltage1,output_current1,thd1,combn1,url11,url21,url31,url41);
//                                DataModel dataModel=new DataModel(sno,modelname,color,brand,stock);
//                                myAdaptor.addModel(dataModel);


//                                int month=cal.get(Calendar.MONTH)+1;
//                                int year=cal.get(Calendar.YEAR);
//                                if (customMonth==month && custom_Year==year){
//                                Toast.makeText(FetchProductDesc_Activity.this, "c+"+product_Code+watt, Toast.LENGTH_SHORT).show();
//
//                                if (product_Code.equals(productCodeA) && watt.equals(modelwattA)) {

//                                    if () {

//                                        Toast.makeText(FetchProductDesc_Activity.this, "c+"+product_Code+category+series+model, Toast.LENGTH_SHORT).show();
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
//                                            thd,
//                                            combn,
//                                            url1,
//                                            url2,
//                                            url3,
//                                            url4));

//
//                                        tv_product_Code.setVisibility(View.VISIBLE);
//
//                                        if (!Objects.equals(product_Code, "")) {
//                                            tv_product_Code.setVisibility(View.VISIBLE);
//
//                                            tv_product_Code.setText(product_Code);
//                                        }
//
//
//                                        if (!Objects.equals(url1, "")) {
//                                            iv_productImage.setVisibility(View.VISIBLE);
//
//                                            Glide.with(getApplicationContext()).load(url1).into(iv_productImage);
//                                        }
//
//                                        if (!Objects.equals(category, "")) {
//                                            tv_category.setVisibility(View.VISIBLE);
//
//                                            tv_category.setText(category);
//                                        }
//                                        if (!Objects.equals(series, "")) {
//                                            tv_series.setVisibility(View.VISIBLE);
//                                            tvt_series.setVisibility(View.VISIBLE);
//
//                                            tv_series.setText(series);
//                                        }
//                                        if (!Objects.equals(model, "")) {
//                                            tv_model.setVisibility(View.VISIBLE);
//
//                                            tv_model.setText(model);
//                                        }
//
//
//                                        if (!Objects.equals(watt, "") || !Objects.equals(shape, "")) {
//                                            llh_W_S.setVisibility(View.VISIBLE);
//                                            if (!Objects.equals(shape, "")) {
//                                                tv_shape.setVisibility(View.VISIBLE);
//                                                tv_shape.setVisibility(View.VISIBLE);
//                                                tvt_shape.setVisibility(View.VISIBLE);
//                                                ll_shape.setVisibility(View.VISIBLE);
//
//                                                tv_shape.setText(shape);
//                                            }
//                                            if (!Objects.equals(watt, "")) {
//                                                tv_watt.setVisibility(View.VISIBLE);
//                                                tvt_watt.setVisibility(View.VISIBLE);
//
//                                                tv_watt.setText(watt);
//                                            }
//                                        }
//
//
////                    forth line - powerfactor - Withstand Voltage- surge protection -lumen
////                output voltage - output current - THD-->
//                                        ////////////////////////////////////////////////////////////////single row
//
//                                        if (!Objects.equals(power_factor, "") || !Objects.equals(withstand_voltage, "") || !Objects.equals(surge_protection, "") || !Objects.equals(lumen, "")
//                                                || !Objects.equals(output_voltage, "") ||
//                                                !Objects.equals(output_current, "") || !Objects.equals(thd, "")) {
//                                            llh_pfwvsplovocthd.setVisibility(View.VISIBLE);
//
//
//                                            if (!Objects.equals(power_factor, "")) {
//                                                tv_power_factor.setVisibility(View.VISIBLE);
//                                                tvt_power_factor.setVisibility(View.VISIBLE);
//                                                ll_power_factor.setVisibility(View.VISIBLE);
//
//
//                                                tv_power_factor.setText(power_factor);
//                                            }
////
//
//                                            //        Toast.makeText(this, "withstand :+"+withstand_voltage, Toast.LENGTH_SHORT).show();
//
//                                            if (Objects.equals(withstand_voltage, "")) {
//                                                tv_withstand_voltage.setVisibility(View.GONE);
//                                                tvt_withstand_voltage.setVisibility(View.GONE);
//                                                ll_withstand_voltage.setVisibility(View.GONE);
//                                                v_withstand_voltage.setVisibility(View.GONE);
//
//                                                tv_withstand_voltage.setText(withstand_voltage);
//                                            } else {
//                                                tv_withstand_voltage.setVisibility(View.VISIBLE);
//                                                tvt_withstand_voltage.setVisibility(View.VISIBLE);
//                                                ll_withstand_voltage.setVisibility(View.VISIBLE);
//                                                v_withstand_voltage.setVisibility(View.VISIBLE);
//
//                                                tv_withstand_voltage.setText(withstand_voltage);
//
//                                            }
//
//
//                                            if (!Objects.equals(surge_protection, "")) {
//                                                tv_surge_protection.setVisibility(View.VISIBLE);
//                                                tvt_surge_protection.setVisibility(View.VISIBLE);
//                                                ll_surge_protection.setVisibility(View.VISIBLE);
//                                                v_surge_protection.setVisibility(View.VISIBLE);
//
//                                                tv_surge_protection.setText(surge_protection);
//                                            }
//
//
//                                            if (!Objects.equals(lumen, "")) {
//                                                tv_lumen.setVisibility(View.VISIBLE);
//                                                tvt_lumen.setVisibility(View.VISIBLE);
//                                                ll_lumen.setVisibility(View.VISIBLE);
//                                                v_lumen.setVisibility(View.VISIBLE);
//
//                                                tv_lumen.setText(lumen);
//                                            }
//
//
//                                            if (!Objects.equals(output_voltage, "")) {
//                                                tv_output_voltage.setVisibility(View.VISIBLE);
//                                                tvt_output_voltage.setVisibility(View.VISIBLE);
//                                                ll_output_voltage.setVisibility(View.VISIBLE);
//                                                v_output_voltage.setVisibility(View.VISIBLE);
//
//                                                tv_output_voltage.setText(output_voltage);
//                                            }
//
//
//                                            if (!Objects.equals(output_current, "")) {
//                                                tv_output_current.setVisibility(View.VISIBLE);
//                                                ll_output_current.setVisibility(View.VISIBLE);
//                                                v_output_current.setVisibility(View.VISIBLE);
//                                                tvt_output_current.setVisibility(View.VISIBLE);
//
//                                                tv_output_current.setText(output_current);
//                                            }
//
//
//                                            //        Toast.makeText(this, "thd"+thd, Toast.LENGTH_SHORT).show();
//                                            if (!Objects.equals(thd, "")) {
//                                                tv_thd.setVisibility(View.VISIBLE);
//                                                tvt_thd.setVisibility(View.VISIBLE);
//                                                ll_thd.setVisibility(View.VISIBLE);
//                                                v_thd.setVisibility(View.VISIBLE);
//
//                                                tv_thd.setText(thd);
//                                            }
//                                        }
//
////        tv_output_voltage=findViewById(R.id.tv_output_voltage);
////        if(output_voltage!=""){
////            tv_output_voltage.setVisibility(View.VISIBLE);
////
////            tv_output_voltage.setText(output_voltage);
////        }
////        tv_output_current=findViewById(R.id.tv_output_current);
////        if(output_current!=""){
////            tv_output_current.setVisibility(View.VISIBLE);
////
////            tv_output_current.setText(output_current);
////        }
////        tv_thd=findViewById(R.id.tv_thd);
////        if(thd!=""){
////            tv_thd.setVisibility(View.VISIBLE);
////
////            tv_thd.setText(thd);
////        }
//
//
///////////////////////////////////////////////////////////////////////////////////
////        <!--                    //sixth line - size - cutoutsize- housing type
////                finish product size - finish product weight-->
//
//
//                                        if (!Objects.equals(size, "") || !Objects.equals(cut_out_size, "") || !Objects.equals(finish_product_size, "") || !Objects.equals(finish_product_weight, ""
//                                        )
//                                                || !Objects.equals(housing_type, "")) {
//                                            llh_SCOSFPSFPWHT.setVisibility(View.VISIBLE);
//
//
//
//                                            if (!Objects.equals(size, "")) {
//                                                tv_size.setVisibility(View.VISIBLE);
//                                                tvt_size.setVisibility(View.VISIBLE);
//                                                ll_size.setVisibility(View.VISIBLE);
//                                                v_size.setVisibility(View.VISIBLE);
//
//                                                tv_size.setText(size);
//                                            }
//
//
//                                            if (!Objects.equals(cut_out_size, "")) {
//                                                tv_cut_out_size.setVisibility(View.VISIBLE);
//                                                tvt_cut_out_size.setVisibility(View.VISIBLE);
//                                                ll_cut_out_size.setVisibility(View.VISIBLE);
//                                                v_cut_out_size.setVisibility(View.VISIBLE);
//
//                                                tv_cut_out_size.setText(cut_out_size);
//                                            }
//
//
//                                            if (!Objects.equals(finish_product_size, "")) {
//                                                tv_finish_product_size.setVisibility(View.VISIBLE);
//                                                tvt_finish_product_size.setVisibility(View.VISIBLE);
//                                                ll_finish_product_size.setVisibility(View.VISIBLE);
//                                                v_finish_product_size.setVisibility(View.VISIBLE);
//
//                                                tv_finish_product_size.setText(finish_product_size);
//                                            }
//
//                                            if (!Objects.equals(finish_product_weight, "")) {
//                                                tv_finish_product_weight.setVisibility(View.VISIBLE);
//                                                tvt_finish_product_weight.setVisibility(View.VISIBLE);
//                                                ll_finish_product_weight.setVisibility(View.VISIBLE);
//                                                v_finish_product_weight.setVisibility(View.VISIBLE);
//
//                                                tv_finish_product_weight.setText(finish_product_weight);
//                                            }
//
//                                            if (!Objects.equals(housing_type, "")) {
//                                                tv_housing_type.setVisibility(View.VISIBLE);
//                                                tvt_housing_type.setVisibility(View.VISIBLE);
//                                                ll_housing_type.setVisibility(View.VISIBLE);
//
//                                                tv_housing_type.setText(housing_type);
//                                            }
//                                        }
//                                        if (!Objects.equals(colour, "")) {
//                                            tv_colour.setVisibility(View.VISIBLE);
//                                            tvt_colour.setVisibility(View.VISIBLE);
//                                            ll_Color.setVisibility(View.VISIBLE);
//
//                                            tv_colour.setText(colour);
//                                        }
//
//
//                                        //fifth line - no of led - lumen per led- lumed per meter-  led package-->
//                                        ///////////////////signle line
//
//                                        if (!Objects.equals(no_of_led, "") || !Objects.equals(lumen_per_led, "") || !Objects.equals(lumen_per_mtr, "") || !Objects.equals(led_package, "")) {
//
//
//                                            llh_nollpllpmlp.setVisibility(View.VISIBLE);
//
//                                            llh_TL_S.setVisibility(View.VISIBLE);
//
//                                            if (!Objects.equals(no_of_led, "")) {
//                                                tv_no_of_led.setVisibility(View.VISIBLE);
//                                                ll_no_of_led.setVisibility(View.VISIBLE);
//                                                v_no_of_led.setVisibility(View.VISIBLE);
//                                                tvt_no_of_led.setVisibility(View.VISIBLE);
//
//                                                tv_no_of_led.setText(no_of_led);
//                                            }
//
//
//                                            if (!Objects.equals(lumen_per_led, "")) {
//                                                tv_lumen_per_led.setVisibility(View.VISIBLE);
//                                                tvt_lumen_per_led.setVisibility(View.VISIBLE);
//                                                ll_lumen_per_led.setVisibility(View.VISIBLE);
//                                                v_lumen_per_led.setVisibility(View.VISIBLE);
//
//                                                tv_lumen_per_led.setText(lumen_per_led);
//                                            }
//                                            if (!Objects.equals(lumen_per_mtr, "")) {
//                                                tv_lumen_per_mtr.setVisibility(View.VISIBLE);
//                                                tvt_lumen_per_mtr.setVisibility(View.VISIBLE);
//                                                ll_lumen_per_mtr.setVisibility(View.VISIBLE);
//                                                v_lumen_per_mtr.setVisibility(View.VISIBLE);
//
//                                                tv_lumen_per_mtr.setText(lumen_per_mtr);
//                                            }
//
//                                            //        v_led_package=findViewById(R.id.v_led_package);
//                                            if (!Objects.equals(led_package, "")) {
//                                                tv_led_package.setVisibility(View.VISIBLE);
//                                                tvt_led_package.setVisibility(View.VISIBLE);
//                                                ll_led_package.setVisibility(View.VISIBLE);
//
//                                                tv_led_package.setText(led_package);
//                                            }

//                                        }


                                        ////////////////////////////////////////


//////////////////////////////////////////////////
// seventh line iprating - dia - total length
//        Toast.makeText(this, "total length"+total_length, Toast.LENGTH_SHORT).show();
//        if(!Objects.equals(ip_rating, "") || !Objects.equals(dia, "")) {


//            llh_nollpllpmlp.setVisibility(View.VISIBLE);

                                        //            v_ip_rating = findViewById(R.id.v_ip_rating);
//        tvt_ip_rating=findViewById(R.id.tvt_ip_rating);
                                        if (!Objects.equals(ip_rating, "")) {
                                            tv_ip_rating.setVisibility(View.VISIBLE);
                                            ll_ip_rating.setVisibility(View.VISIBLE);
                                            tvt_ip_rating.setVisibility(View.VISIBLE);
//                v_ip_rating.setVisibility(View.VISIBLE);

                                            tv_ip_rating.setText(ip_rating);
                                        }

                                        if (!Objects.equals(dia, "")) {
                                            tv_dia.setVisibility(View.VISIBLE);
                                            tvt_dia.setVisibility(View.VISIBLE);
                                            ll_dia.setVisibility(View.VISIBLE);
                                            v_dia.setVisibility(View.VISIBLE);

                                            tv_dia.setText(dia);
//            }

                                        }


                                        if (!Objects.equals(total_length, "") || !Objects.equals(smd, "")) {




                                            if (!Objects.equals(total_length, "")) {
                                                tv_total_length.setVisibility(View.VISIBLE);
                                                tvt_total_length.setVisibility(View.VISIBLE);
                                                ll_total_length.setVisibility(View.VISIBLE);

                                                tv_total_length.setText(total_length);
                                            }

                                            if (!Objects.equals(smd, "")) {
                                                tv_smd.setVisibility(View.VISIBLE);
                                                tvt_smd.setVisibility(View.VISIBLE);
                                                ll_smd.setVisibility(View.VISIBLE);
                                                v_smd.setVisibility(View.VISIBLE);

                                                tv_smd.setText(smd);
                                            }

                                        }

                                        if (!Objects.equals(product_specification, "")) {
                                            tv_product_specification.setVisibility(View.VISIBLE);
                                            tvt_product_specification.setVisibility(View.VISIBLE);
                                            ll_product_specification.setVisibility(View.VISIBLE);

                                            tv_product_specification.setText(product_specification);
                                        }
///////////////////////////////////////////////



                                        if (!Objects.equals(smd, "")) {
                                            tv_smd.setVisibility(View.VISIBLE);

                                            tv_smd.setText(smd);
                                        }


                                    }
//                                }


//                                }
//                            }

                            //creating adapter object and setting it to recyclerview
//                            progressBarMum.setVisibility(View.GONE);
//                            myAdaptor = new myAdaptor(getApplicationContext(), productList);

//                            adaptor_ProductDesc = new ProductsListAdapter(getApplicationContext(), list_ProductDesc);
//                            rv_ProductDesc.setAdapter(adaptor_ProductDesc);
                            dialogEng.dismiss();
//
//                            myAdaptor = new MyAdaptor(getApplicationContext(), productList);
//                            recyclerView.setAdapter(productsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialogEng.dismiss();
                            Toast.makeText(FetchProductDesc_Activity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FetchProductDesc_Activity.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }

    private void subject(String sr_no1, String product_code1, String category1, String series1, String model1, String shape1, String watt1,
                         String power_factor1, String withstand_voltage1, String surge_protection1, String lumen1, String ip_rating1, String size1,
                         String cut_out_size1, String housing_type1, String colour1, String dia1, String no_of_led1, String lumen_per_led1,
                         String led_package1, String total_length1, String lumen_per_mtr1, String smd1, String finish_product_size1,
                         String finish_product_weight1, String remarks1, String product_specification1, String output_voltage1, String output_current1,
                         String thd1, String combn1, String url11, String url21, String url31, String url41) {

        indit();
        Toast.makeText(FetchProductDesc_Activity.this, "c+="+product_code1+watt1, Toast.LENGTH_SHORT).show();
        tv_product_Code.setVisibility(View.VISIBLE);
        tv_watt.setVisibility(View.VISIBLE);
//
        tv_product_Code.setText(product_code1);
        tv_watt.setText(watt1);


                                        tv_product_Code.setVisibility(View.VISIBLE);

                                        if (!Objects.equals(product_code1, "")) {
                                            tv_product_Code.setVisibility(View.VISIBLE);

                                            tv_product_Code.setText(product_code1);
                                        }

        Toast.makeText(this, "url"+url1, Toast.LENGTH_SHORT).show();
                                        if (!Objects.equals(url1, "")) {
                                            iv_productImage.setVisibility(View.VISIBLE);

                                            Glide.with(getApplicationContext()).load(url11).into(iv_productImage);
                                        }

                                        if (!Objects.equals(category1, "")) {
                                            tv_category.setVisibility(View.VISIBLE);

                                            tv_category.setText(category1);
                                        }
                                        if (!Objects.equals(series1, "")) {
                                            tv_series.setVisibility(View.VISIBLE);
                                            tvt_series.setVisibility(View.VISIBLE);

                                            tv_series.setText(series1);
                                        }
                                        if (!Objects.equals(model1, "")) {
                                            tv_model.setVisibility(View.VISIBLE);

                                            tv_model.setText(model1);
                                        }


                                        if (!Objects.equals(watt1, "") || !Objects.equals(shape1, "")) {
                                            llh_W_S.setVisibility(View.VISIBLE);
                                            if (!Objects.equals(shape1, "")) {
                                                tv_shape.setVisibility(View.VISIBLE);
                                                tv_shape.setVisibility(View.VISIBLE);
                                                tvt_shape.setVisibility(View.VISIBLE);
                                                ll_shape.setVisibility(View.VISIBLE);

                                                tv_shape.setText(shape1);
                                            }
                                            if (!Objects.equals(watt1, "")) {
                                                tv_watt.setVisibility(View.VISIBLE);
                                                tvt_watt.setVisibility(View.VISIBLE);

                                                tv_watt.setText(watt1);
                                            }
                                        }


//                    forth line - powerfactor - Withstand Voltage- surge protection -lumen
//                output voltage - output current - THD-->
                                        ////////////////////////////////////////////////////////////////single row

                                        if (!Objects.equals(power_factor1, "") || !Objects.equals(withstand_voltage1, "") || !Objects.equals(surge_protection, "") || !Objects.equals(lumen, "")
                                                || !Objects.equals(output_voltage1, "") ||
                                                !Objects.equals(output_current1, "") || !Objects.equals(thd1, "")) {
                                            llh_pfwvsplovocthd.setVisibility(View.VISIBLE);


                                            if (!Objects.equals(power_factor1, "")) {
                                                tv_power_factor.setVisibility(View.VISIBLE);
                                                tvt_power_factor.setVisibility(View.VISIBLE);
                                                ll_power_factor.setVisibility(View.VISIBLE);


                                                tv_power_factor.setText(power_factor1);
                                            }
//

                                            //        Toast.makeText(this, "withstand :+"+withstand_voltage, Toast.LENGTH_SHORT).show();

                                            if (Objects.equals(withstand_voltage1, "")) {
                                                tv_withstand_voltage.setVisibility(View.GONE);
                                                tvt_withstand_voltage.setVisibility(View.GONE);
                                                ll_withstand_voltage.setVisibility(View.GONE);
                                                v_withstand_voltage.setVisibility(View.GONE);

                                                tv_withstand_voltage.setText(withstand_voltage1);
                                            } else {
                                                tv_withstand_voltage.setVisibility(View.VISIBLE);
                                                tvt_withstand_voltage.setVisibility(View.VISIBLE);
                                                ll_withstand_voltage.setVisibility(View.VISIBLE);
                                                v_withstand_voltage.setVisibility(View.VISIBLE);

                                                tv_withstand_voltage.setText(withstand_voltage1);

                                            }


                                            if (!Objects.equals(surge_protection1, "")) {
                                                tv_surge_protection.setVisibility(View.VISIBLE);
                                                tvt_surge_protection.setVisibility(View.VISIBLE);
                                                ll_surge_protection.setVisibility(View.VISIBLE);
                                                v_surge_protection.setVisibility(View.VISIBLE);

                                                tv_surge_protection.setText(surge_protection1);
                                            }


                                            if (!Objects.equals(lumen1, "")) {
                                                tv_lumen.setVisibility(View.VISIBLE);
                                                tvt_lumen.setVisibility(View.VISIBLE);
                                                ll_lumen.setVisibility(View.VISIBLE);
                                                v_lumen.setVisibility(View.VISIBLE);

                                                tv_lumen.setText(lumen1);
                                            }


                                            if (!Objects.equals(output_voltage1, "")) {
                                                tv_output_voltage.setVisibility(View.VISIBLE);
                                                tvt_output_voltage.setVisibility(View.VISIBLE);
                                                ll_output_voltage.setVisibility(View.VISIBLE);
                                                v_output_voltage.setVisibility(View.VISIBLE);

                                                tv_output_voltage.setText(output_voltage1);
                                            }


                                            if (!Objects.equals(output_current1, "")) {
                                                tv_output_current.setVisibility(View.VISIBLE);
                                                ll_output_current.setVisibility(View.VISIBLE);
                                                v_output_current.setVisibility(View.VISIBLE);
                                                tvt_output_current.setVisibility(View.VISIBLE);

                                                tv_output_current.setText(output_current1);
                                            }


                                            //        Toast.makeText(this, "thd"+thd, Toast.LENGTH_SHORT).show();
                                            if (!Objects.equals(thd1, "")) {
                                                tv_thd.setVisibility(View.VISIBLE);
                                                tvt_thd.setVisibility(View.VISIBLE);
                                                ll_thd.setVisibility(View.VISIBLE);
                                                v_thd.setVisibility(View.VISIBLE);

                                                tv_thd.setText(thd1);
                                            }
                                        }

//        tv_output_voltage=findViewById(R.id.tv_output_voltage);
//        if(output_voltage!=""){
//            tv_output_voltage.setVisibility(View.VISIBLE);
//
//            tv_output_voltage.setText(output_voltage);
//        }
//        tv_output_current=findViewById(R.id.tv_output_current);
//        if(output_current!=""){
//            tv_output_current.setVisibility(View.VISIBLE);
//
//            tv_output_current.setText(output_current);
//        }
//        tv_thd=findViewById(R.id.tv_thd);
//        if(thd!=""){
//            tv_thd.setVisibility(View.VISIBLE);
//
//            tv_thd.setText(thd);
//        }


/////////////////////////////////////////////////////////////////////////////////
//        <!--                    //sixth line - size - cutoutsize- housing type
//                finish product size - finish product weight-->


                                        if (!Objects.equals(size1, "") || !Objects.equals(cut_out_size1, "") || !Objects.equals(finish_product_size1, "") || !Objects.equals(finish_product_weight1, ""
                                        )
                                                || !Objects.equals(housing_type1, "")) {
                                            llh_SCOSFPSFPWHT.setVisibility(View.VISIBLE);



                                            if (!Objects.equals(size1, "")) {
                                                tv_size.setVisibility(View.VISIBLE);
                                                tvt_size.setVisibility(View.VISIBLE);
                                                ll_size.setVisibility(View.VISIBLE);
                                                v_size.setVisibility(View.VISIBLE);

                                                tv_size.setText(size1);
                                            }


                                            if (!Objects.equals(cut_out_size1, "")) {
                                                tv_cut_out_size.setVisibility(View.VISIBLE);
                                                tvt_cut_out_size.setVisibility(View.VISIBLE);
                                                ll_cut_out_size.setVisibility(View.VISIBLE);
                                                v_cut_out_size.setVisibility(View.VISIBLE);

                                                tv_cut_out_size.setText(cut_out_size1);
                                            }


                                            if (!Objects.equals(finish_product_size1, "")) {
                                                tv_finish_product_size.setVisibility(View.VISIBLE);
                                                tvt_finish_product_size.setVisibility(View.VISIBLE);
                                                ll_finish_product_size.setVisibility(View.VISIBLE);
                                                v_finish_product_size.setVisibility(View.VISIBLE);

                                                tv_finish_product_size.setText(finish_product_size1);
                                            }

                                            if (!Objects.equals(finish_product_weight1, "")) {
                                                tv_finish_product_weight.setVisibility(View.VISIBLE);
                                                tvt_finish_product_weight.setVisibility(View.VISIBLE);
                                                ll_finish_product_weight.setVisibility(View.VISIBLE);
                                                v_finish_product_weight.setVisibility(View.VISIBLE);

                                                tv_finish_product_weight.setText(finish_product_weight1);
                                            }

                                            if (!Objects.equals(housing_type1, "")) {
                                                tv_housing_type.setVisibility(View.VISIBLE);
                                                tvt_housing_type.setVisibility(View.VISIBLE);
                                                ll_housing_type.setVisibility(View.VISIBLE);

                                                tv_housing_type.setText(housing_type1);
                                            }
                                        }
                                        if (!Objects.equals(colour1, "")) {
                                            tv_colour.setVisibility(View.VISIBLE);
                                            tvt_colour.setVisibility(View.VISIBLE);
                                            ll_Color.setVisibility(View.VISIBLE);

                                            tv_colour.setText(colour1);
                                        }


                                        //fifth line - no of led - lumen per led- lumed per meter-  led package-->
                                        ///////////////////signle line

                                        if (!Objects.equals(no_of_led1, "") || !Objects.equals(lumen_per_led1, "") || !Objects.equals(lumen_per_mtr1, "") || !Objects.equals(led_package1, "")) {


                                            llh_nollpllpmlp.setVisibility(View.VISIBLE);

                                            llh_TL_S.setVisibility(View.VISIBLE);

                                            if (!Objects.equals(no_of_led1, "")) {
                                                tv_no_of_led.setVisibility(View.VISIBLE);
                                                ll_no_of_led.setVisibility(View.VISIBLE);
                                                v_no_of_led.setVisibility(View.VISIBLE);
                                                tvt_no_of_led.setVisibility(View.VISIBLE);

                                                tv_no_of_led.setText(no_of_led1);
                                            }


                                            if (!Objects.equals(lumen_per_led1, "")) {
                                                tv_lumen_per_led.setVisibility(View.VISIBLE);
                                                tvt_lumen_per_led.setVisibility(View.VISIBLE);
                                                ll_lumen_per_led.setVisibility(View.VISIBLE);
                                                v_lumen_per_led.setVisibility(View.VISIBLE);

                                                tv_lumen_per_led.setText(lumen_per_led1);
                                            }
                                            if (!Objects.equals(lumen_per_mtr1, "")) {
                                                tv_lumen_per_mtr.setVisibility(View.VISIBLE);
                                                tvt_lumen_per_mtr.setVisibility(View.VISIBLE);
                                                ll_lumen_per_mtr.setVisibility(View.VISIBLE);
                                                v_lumen_per_mtr.setVisibility(View.VISIBLE);

                                                tv_lumen_per_mtr.setText(lumen_per_mtr1);
                                            }

                                            //        v_led_package=findViewById(R.id.v_led_package);
                                            if (!Objects.equals(led_package1, "")) {
                                                tv_led_package.setVisibility(View.VISIBLE);
                                                tvt_led_package.setVisibility(View.VISIBLE);
                                                ll_led_package.setVisibility(View.VISIBLE);

                                                tv_led_package.setText(led_package1);
                                            }

                                        }


                                        ////////////////////////////////////////


//////////////////////////////////////////////////
// seventh line iprating - dia - total length
//        Toast.makeText(this, "total length"+total_length, Toast.LENGTH_SHORT).show();
//        if(!Objects.equals(ip_rating, "") || !Objects.equals(dia, "")) {


//            llh_nollpllpmlp.setVisibility(View.VISIBLE);

                                        //            v_ip_rating = findViewById(R.id.v_ip_rating);
//        tvt_ip_rating=findViewById(R.id.tvt_ip_rating);
                                        if (!Objects.equals(ip_rating1, "")) {
                                            tv_ip_rating.setVisibility(View.VISIBLE);
                                            ll_ip_rating.setVisibility(View.VISIBLE);
                                            tvt_ip_rating.setVisibility(View.VISIBLE);
//                v_ip_rating.setVisibility(View.VISIBLE);

                                            tv_ip_rating.setText(ip_rating1);
                                        }

                                        if (!Objects.equals(dia1, "")) {
                                            tv_dia.setVisibility(View.VISIBLE);
                                            tvt_dia.setVisibility(View.VISIBLE);
                                            ll_dia.setVisibility(View.VISIBLE);
                                            v_dia.setVisibility(View.VISIBLE);

                                            tv_dia.setText(dia1);
//            }

                                        }


                                        if (!Objects.equals(total_length1, "") || !Objects.equals(smd, "")) {




                                            if (!Objects.equals(total_length1, "")) {
                                                tv_total_length.setVisibility(View.VISIBLE);
                                                tvt_total_length.setVisibility(View.VISIBLE);
                                                ll_total_length.setVisibility(View.VISIBLE);

                                                tv_total_length.setText(total_length1);
                                            }

                                            if (!Objects.equals(smd1, "")) {
                                                tv_smd.setVisibility(View.VISIBLE);
                                                tvt_smd.setVisibility(View.VISIBLE);
                                                ll_smd.setVisibility(View.VISIBLE);
                                                v_smd.setVisibility(View.VISIBLE);

                                                tv_smd.setText(smd1);
                                            }

                                        }

                                        if (!Objects.equals(product_specification1, "")) {
                                            tv_product_specification.setVisibility(View.VISIBLE);
                                            tvt_product_specification.setVisibility(View.VISIBLE);
                                            ll_product_specification.setVisibility(View.VISIBLE);

                                            tv_product_specification.setText(product_specification1);
                                        }
///////////////////////////////////////////////



                                        if (!Objects.equals(smd1, "")) {
                                            tv_smd.setVisibility(View.VISIBLE);

                                            tv_smd.setText(smd1);
                                        }
    }

    private void indit() {
        tv_product_specification=findViewById(R.id.tv_product_specification);
        tvt_product_specification=findViewById(R.id.tvt_product_specification);
        ll_product_specification=findViewById(R.id.ll_product_specification);
        tv_dia = findViewById(R.id.tv_dia);
        tvt_dia = findViewById(R.id.tvt_dia);
        ll_dia = findViewById(R.id.ll_dia);
        v_dia = findViewById(R.id.v_dia);
        llh_IPR_D_TL=findViewById(R.id.llh_IPR_D_TL);
        tv_ip_rating = findViewById(R.id.tv_ip_rating);
        tvt_ip_rating = findViewById(R.id.tvt_ip_rating);
        ll_ip_rating = findViewById(R.id.ll_ip_rating);
        tv_led_package=findViewById(R.id.tv_led_package);
        tvt_led_package=findViewById(R.id.tvt_led_package);
        ll_led_package=findViewById(R.id.ll_led_package);
        tv_lumen_per_led=findViewById(R.id.tv_lumen_per_led);
        tvt_lumen_per_led=findViewById(R.id.tvt_lumen_per_led);
        ll_lumen_per_led=findViewById(R.id.ll_lumen_per_led);
        v_lumen_per_led=findViewById(R.id.v_lumen_per_led);
        llh_nollpllpmlp=findViewById(R.id.llh_nollpllpmlp);
        tv_finish_product_weight = findViewById(R.id.tv_finish_product_weight);
        tvt_finish_product_weight = findViewById(R.id.tvt_finish_product_weight);
        ll_finish_product_weight = findViewById(R.id.ll_finish_product_weight);
        v_finish_product_weight = findViewById(R.id.v_finish_product_weight);
        tv_finish_product_size = findViewById(R.id.tv_finish_product_size);
        tvt_finish_product_size = findViewById(R.id.tvt_finish_product_size);
        ll_finish_product_size = findViewById(R.id.ll_finish_product_size);
        v_finish_product_size = findViewById(R.id.v_finish_product_size);
        tv_cut_out_size = findViewById(R.id.tv_cut_out_size);
        tvt_cut_out_size = findViewById(R.id.tvt_cut_out_size);
        ll_cut_out_size = findViewById(R.id.ll_cut_out_size);
        v_cut_out_size = findViewById(R.id.v_cut_out_size);
        llh_SCOSFPSFPWHT=findViewById(R.id.llh_SCOSFPSFPWHT);
        tv_thd=findViewById(R.id.tv_thd);
        tvt_thd=findViewById(R.id.tvt_thd);
        ll_thd=findViewById(R.id.ll_thd);
        v_thd=findViewById(R.id.v_thd);
        tv_output_current=findViewById(R.id.tv_output_current);
        tvt_output_current=findViewById(R.id.tvt_output_current);
        ll_output_current=findViewById(R.id.ll_output_current);
        v_output_current=findViewById(R.id.v_output_current);
        tv_output_voltage=findViewById(R.id.tv_output_voltage);
        tvt_output_voltage=findViewById(R.id.tvt_output_voltage);
        ll_output_voltage=findViewById(R.id.ll_output_voltage);
        v_output_voltage=findViewById(R.id.v_output_voltage);
        tv_lumen=findViewById(R.id.tv_lumen);
        ll_lumen=findViewById(R.id.ll_lumen);
        tvt_lumen=findViewById(R.id.tvt_lumen);
        v_lumen=findViewById(R.id.v_lumen);
        tv_surge_protection=findViewById(R.id.tv_surge_protection);
        tvt_surge_protection=findViewById(R.id.tvt_surge_protection);
        ll_surge_protection=findViewById(R.id.ll_surge_protection);
        v_surge_protection=findViewById(R.id.v_surge_protection);
        tv_withstand_voltage=findViewById(R.id.tv_withstand_voltage);
        tvt_withstand_voltage=findViewById(R.id.tvt_withstand_voltage);
        ll_withstand_voltage=findViewById(R.id.ll_withstand_voltage);
        v_withstand_voltage=findViewById(R.id.v_withstand_voltage);
        tv_power_factor=findViewById(R.id.tv_power_factor);
        tvt_power_factor=findViewById(R.id.tvt_power_factor);
        ll_power_factor=findViewById(R.id.ll_power_factor);
        llh_pfwvsplovocthd=findViewById(R.id.llh_pfwvsplovocthd);
        tv_product_Code=findViewById(R.id.tv_product_Code);
        iv_productImage=findViewById(R.id.iv_productImage);
        tv_title=findViewById(R.id.tv_title);
        tv_category=findViewById(R.id.tv_category);
        tv_series=findViewById(R.id.tv_series);
        tvt_series=findViewById(R.id.tvt_series);
        tv_model=findViewById(R.id.tv_model);
        llh_W_S=findViewById(R.id.llh_W_S);
        tv_shape=findViewById(R.id.tv_shape);
        ll_shape=findViewById(R.id.ll_shape);
        tvt_shape=findViewById(R.id.tvt_shape);
        tv_watt = findViewById(R.id.tv_watt);
        tvt_watt = findViewById(R.id.tvt_watt);

        tv_housing_type = findViewById(R.id.tv_housing_type);
        tvt_housing_type = findViewById(R.id.tvt_housing_type);
        ll_housing_type = findViewById(R.id.ll_housing_type);

        tv_colour=findViewById(R.id.tv_colour);
        tvt_colour=findViewById(R.id.tvt_colour);
        ll_Color=findViewById(R.id.ll_Color);

        tv_lumen_per_mtr=findViewById(R.id.tv_lumen_per_mtr);
        tvt_lumen_per_mtr=findViewById(R.id.tvt_lumen_per_mtr);
        ll_lumen_per_mtr=findViewById(R.id.ll_lumen_per_mtr);
        v_lumen_per_mtr=findViewById(R.id.v_lumen_per_mtr);
        tv_size = findViewById(R.id.tv_size);
        tvt_size = findViewById(R.id.tvt_size);
        tvt_size = findViewById(R.id.tvt_size);
        ll_size = findViewById(R.id.ll_size);
        v_size = findViewById(R.id.v_size);
        tv_no_of_led = findViewById(R.id.tv_no_of_led);
        tvt_no_of_led = findViewById(R.id.tvt_no_of_led);
        ll_no_of_led = findViewById(R.id.ll_no_of_led);
        v_no_of_led = findViewById(R.id.v_no_of_led);
        llh_TL_S = findViewById(R.id.llh_TL_S);
        tv_total_length = findViewById(R.id.tv_total_length);
        tvt_total_length = findViewById(R.id.tvt_total_length);
        ll_total_length = findViewById(R.id.ll_total_length);
        tv_smd = findViewById(R.id.tv_smd);
        tvt_smd = findViewById(R.id.tvt_smd);
        ll_smd = findViewById(R.id.ll_smd);
        v_smd = findViewById(R.id.v_smd);
        tv_smd = findViewById(R.id.tv_smd);

    }
}