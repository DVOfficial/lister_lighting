package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class Product_Details extends AppCompatActivity {
    ProgressDialog dialogEng;

    String sr_No,product_Code,category,series,model,shape,watt,power_factor,withstand_voltage,surge_protection,lumen,ip_rating,
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

    BottomNavigationView bottom_Navigation;
    
    LinearLayout llh_pfwvsplovocthd,llh_nollpllpmlp,llh_SCOSFPSFPWHT, llh_IPR_D_TL,llh_W_S,llh_TL_S;

    ImageView iv_productImage;
    Button btn_PlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        Bundle bundle = getIntent().getExtras();


        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        btn_PlaceOrder = findViewById(R.id.btn_PlaceOrder);
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


        btn_PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PlaceOrder.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
        sr_No = bundle.getString("sr_No", "");
        product_Code = bundle.getString("product_Code", "");
        category = bundle.getString("category", "");
        series = bundle.getString("series", "");
        model = bundle.getString("model", "");
        shape = bundle.getString("shape", "");
        watt = bundle.getString("watt", "");
        power_factor = bundle.getString("power_factor", "");
        withstand_voltage = bundle.getString("withstand_voltage", "");
        surge_protection = bundle.getString("surge_protection", "");
        lumen = bundle.getString("lumen", "");
        ip_rating = bundle.getString("ip_rating", "");
        size = bundle.getString("size", "");
        cut_out_size = bundle.getString("cut_out_size", "");
        housing_type = bundle.getString("housing_type", "");
        colour = bundle.getString("colour", "");
        dia = bundle.getString("dia", "");
        no_of_led = bundle.getString("no_of_led", "");
        lumen_per_led = bundle.getString("lumen_per_led", "");
        led_package = bundle.getString("led_package", "");
        total_length = bundle.getString("total_length", "");
        lumen_per_mtr = bundle.getString("lumen_per_mtr", "");
        smd = bundle.getString("smd", "");
        finish_product_size = bundle.getString("finish_product_size", "");
        finish_product_weight = bundle.getString("finish_product_weight", "");
        remarks = bundle.getString("remarks", "");
        product_specification = bundle.getString("product_specification", "");
        output_voltage = bundle.getString("output_voltage", "");
        output_current = bundle.getString("output_current", "");
        thd = bundle.getString("thd", "");
        url1 = bundle.getString("url1", "");
        url2 = bundle.getString("url2", "");
        url3 = bundle.getString("url3", "");
        url4 = bundle.getString("url4", "");


//        String title= productCode +" "+model;power_factor
        tv_product_Code=findViewById(R.id.tv_product_Code);
        iv_productImage=findViewById(R.id.iv_productImage);
        tv_title=findViewById(R.id.tv_title);
        tv_product_Code.setVisibility(View.VISIBLE);

//        tv_product_Code.setText("check now"+product_Code+"\n"+category+"\n"+series+"\n"+model+"\n"+shape+"\n"+watt+"\n"+power_factor+"\n"+withstand_voltage+"\n"+surge_protection+
//                        "\n"+lumen+"\n"+ip_rating+"\n"+size+
//                "\n"+cut_out_size+"\n"+housing_type+"\n"+colour+"\n"+dia+"\n"+no_of_led+"\n"+lumen_per_led+"\n"+led_package+"\n"+total_length+"\n"+lumen_per_mtr+
//                "\n"+smd+"\n"+finish_product_size+"\n"+finish_product_weight+"\n"+remarks+"\n"+product_specification+
//        "\n"+output_voltage+"\n"+output_current+"\n"+thd);



        tv_title.setText(product_Code);

        if(!Objects.equals(product_Code, "")){
            tv_product_Code.setVisibility(View.VISIBLE);

            tv_product_Code.setText(product_Code);
        }




        if(!Objects.equals(url1, "")){
            iv_productImage.setVisibility(View.VISIBLE);

            Glide.with(this).load(url1).into(iv_productImage);
        }


        tv_category=findViewById(R.id.tv_category);
        if(!Objects.equals(category, "")){
            tv_category.setVisibility(View.VISIBLE);

            tv_category.setText(category);
        }
        tv_series=findViewById(R.id.tv_series);
        tvt_series=findViewById(R.id.tvt_series);
        if(!Objects.equals(series, "")){
            tv_series.setVisibility(View.VISIBLE);
            tvt_series.setVisibility(View.VISIBLE);

            tv_series.setText(series);
        }
        tv_model=findViewById(R.id.tv_model);
        if(!Objects.equals(model, "")){
            tv_model.setVisibility(View.VISIBLE);

            tv_model.setText(model);
        }


        llh_W_S=findViewById(R.id.llh_W_S);
        tv_shape=findViewById(R.id.tv_shape);
        ll_shape=findViewById(R.id.ll_shape);
        tvt_shape=findViewById(R.id.tvt_shape);

        if(!Objects.equals(watt, "") || !Objects.equals(shape, "")  ) {
            llh_W_S.setVisibility(View.VISIBLE);
            if (!Objects.equals(shape, "")) {
                tv_shape.setVisibility(View.VISIBLE);
                tv_shape.setVisibility(View.VISIBLE);
                tvt_shape.setVisibility(View.VISIBLE);
                ll_shape.setVisibility(View.VISIBLE);

                tv_shape.setText(shape);
            }

            tv_watt = findViewById(R.id.tv_watt);
            tvt_watt = findViewById(R.id.tvt_watt);
            if (!Objects.equals(watt, "")) {
                tv_watt.setVisibility(View.VISIBLE);
                tvt_watt.setVisibility(View.VISIBLE);

                tv_watt.setText(watt);
            }
        }


//                    forth line - powerfactor - Withstand Voltage- surge protection -lumen
//                output voltage - output current - THD-->
        ////////////////////////////////////////////////////////////////single row
        llh_pfwvsplovocthd=findViewById(R.id.llh_pfwvsplovocthd);

        if(!Objects.equals(power_factor, "") || !Objects.equals(withstand_voltage, "") ||!Objects.equals(surge_protection, "") ||!Objects.equals(lumen, "")
                ||!Objects.equals(output_voltage, "") ||
                !Objects.equals(output_current, "") ||!Objects.equals(thd, "") ){
            llh_pfwvsplovocthd.setVisibility(View.VISIBLE);
        
        tv_power_factor=findViewById(R.id.tv_power_factor);
        tvt_power_factor=findViewById(R.id.tvt_power_factor);
        ll_power_factor=findViewById(R.id.ll_power_factor);

        if(!Objects.equals(power_factor, "")){
            tv_power_factor.setVisibility(View.VISIBLE);
            tvt_power_factor.setVisibility(View.VISIBLE);
            ll_power_factor.setVisibility(View.VISIBLE);


            tv_power_factor.setText(power_factor);
        }
//
        tv_withstand_voltage=findViewById(R.id.tv_withstand_voltage);
        tvt_withstand_voltage=findViewById(R.id.tvt_withstand_voltage);
        ll_withstand_voltage=findViewById(R.id.ll_withstand_voltage);
        v_withstand_voltage=findViewById(R.id.v_withstand_voltage);
//        Toast.makeText(this, "withstand :+"+withstand_voltage, Toast.LENGTH_SHORT).show();

        if(Objects.equals(withstand_voltage, "")){
            tv_withstand_voltage.setVisibility(View.GONE);
            tvt_withstand_voltage.setVisibility(View.GONE);
            ll_withstand_voltage.setVisibility(View.GONE);
            v_withstand_voltage.setVisibility(View.GONE);

            tv_withstand_voltage.setText(withstand_voltage);
        }else{
            tv_withstand_voltage.setVisibility(View.VISIBLE);
            tvt_withstand_voltage.setVisibility(View.VISIBLE);
            ll_withstand_voltage.setVisibility(View.VISIBLE);
            v_withstand_voltage.setVisibility(View.VISIBLE);

            tv_withstand_voltage.setText(withstand_voltage);

        }

        tv_surge_protection=findViewById(R.id.tv_surge_protection);
        tvt_surge_protection=findViewById(R.id.tvt_surge_protection);
        ll_surge_protection=findViewById(R.id.ll_surge_protection);
        v_surge_protection=findViewById(R.id.v_surge_protection);
        if(!Objects.equals(surge_protection, "")){
            tv_surge_protection.setVisibility(View.VISIBLE);
            tvt_surge_protection.setVisibility(View.VISIBLE);
            ll_surge_protection.setVisibility(View.VISIBLE);
            v_surge_protection.setVisibility(View.VISIBLE);

            tv_surge_protection.setText(surge_protection);
        }

        tv_lumen=findViewById(R.id.tv_lumen);
        ll_lumen=findViewById(R.id.ll_lumen);
        tvt_lumen=findViewById(R.id.tvt_lumen);
        v_lumen=findViewById(R.id.v_lumen);
        if(!Objects.equals(lumen, "")){
            tv_lumen.setVisibility(View.VISIBLE);
            tvt_lumen.setVisibility(View.VISIBLE);
            ll_lumen.setVisibility(View.VISIBLE);
            v_lumen.setVisibility(View.VISIBLE);

            tv_lumen.setText(lumen);
        }

        tv_output_voltage=findViewById(R.id.tv_output_voltage);
        tvt_output_voltage=findViewById(R.id.tvt_output_voltage);
        ll_output_voltage=findViewById(R.id.ll_output_voltage);
        v_output_voltage=findViewById(R.id.v_output_voltage);
        if(!Objects.equals(output_voltage, "")){
            tv_output_voltage.setVisibility(View.VISIBLE);
            tvt_output_voltage.setVisibility(View.VISIBLE);
            ll_output_voltage.setVisibility(View.VISIBLE);
            v_output_voltage.setVisibility(View.VISIBLE);

            tv_output_voltage.setText(output_voltage);
        }

        tv_output_current=findViewById(R.id.tv_output_current);
        tvt_output_current=findViewById(R.id.tvt_output_current);
        ll_output_current=findViewById(R.id.ll_output_current);
        v_output_current=findViewById(R.id.v_output_current);
        if(!Objects.equals(output_current, "")){
            tv_output_current.setVisibility(View.VISIBLE);
            ll_output_current.setVisibility(View.VISIBLE);
            v_output_current.setVisibility(View.VISIBLE);
            tvt_output_current.setVisibility(View.VISIBLE);

            tv_output_current.setText(output_current);
        }

        tv_thd=findViewById(R.id.tv_thd);
        tvt_thd=findViewById(R.id.tvt_thd);
        ll_thd=findViewById(R.id.ll_thd);
        v_thd=findViewById(R.id.v_thd);
//        Toast.makeText(this, "thd"+thd, Toast.LENGTH_SHORT).show();
        if(!Objects.equals(thd, "")){
            tv_thd.setVisibility(View.VISIBLE);
            tvt_thd.setVisibility(View.VISIBLE);
            ll_thd.setVisibility(View.VISIBLE);
            v_thd.setVisibility(View.VISIBLE);

            tv_thd.setText(thd);
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

        llh_SCOSFPSFPWHT=findViewById(R.id.llh_SCOSFPSFPWHT);
        if(!Objects.equals(size, "") || !Objects.equals(cut_out_size, "") ||!Objects.equals(finish_product_size, "") ||!Objects.equals(finish_product_weight, ""
        )
                ||!Objects.equals(housing_type, "")  ) {
            llh_SCOSFPSFPWHT.setVisibility(View.VISIBLE);

            tv_size = findViewById(R.id.tv_size);
            tvt_size = findViewById(R.id.tvt_size);
            tvt_size = findViewById(R.id.tvt_size);
            ll_size = findViewById(R.id.ll_size);
            v_size = findViewById(R.id.v_size);
            if (!Objects.equals(size, "")) {
                tv_size.setVisibility(View.VISIBLE);
                tvt_size.setVisibility(View.VISIBLE);
                ll_size.setVisibility(View.VISIBLE);
                v_size.setVisibility(View.VISIBLE);

                tv_size.setText(size);
            }

            tv_cut_out_size = findViewById(R.id.tv_cut_out_size);
            tvt_cut_out_size = findViewById(R.id.tvt_cut_out_size);
            ll_cut_out_size = findViewById(R.id.ll_cut_out_size);
            v_cut_out_size = findViewById(R.id.v_cut_out_size);
            if (!Objects.equals(cut_out_size, "")) {
                tv_cut_out_size.setVisibility(View.VISIBLE);
                tvt_cut_out_size.setVisibility(View.VISIBLE);
                ll_cut_out_size.setVisibility(View.VISIBLE);
                v_cut_out_size.setVisibility(View.VISIBLE);

                tv_cut_out_size.setText(cut_out_size);
            }

            tv_finish_product_size = findViewById(R.id.tv_finish_product_size);
            tvt_finish_product_size = findViewById(R.id.tvt_finish_product_size);
            ll_finish_product_size = findViewById(R.id.ll_finish_product_size);
            v_finish_product_size = findViewById(R.id.v_finish_product_size);
            if (!Objects.equals(finish_product_size, "")) {
                tv_finish_product_size.setVisibility(View.VISIBLE);
                tvt_finish_product_size.setVisibility(View.VISIBLE);
                ll_finish_product_size.setVisibility(View.VISIBLE);
                v_finish_product_size.setVisibility(View.VISIBLE);

                tv_finish_product_size.setText(finish_product_size);
            }
            tv_finish_product_weight = findViewById(R.id.tv_finish_product_weight);
            tvt_finish_product_weight = findViewById(R.id.tvt_finish_product_weight);
            ll_finish_product_weight = findViewById(R.id.ll_finish_product_weight);
            v_finish_product_weight = findViewById(R.id.v_finish_product_weight);
            if (!Objects.equals(finish_product_weight, "")) {
                tv_finish_product_weight.setVisibility(View.VISIBLE);
                tvt_finish_product_weight.setVisibility(View.VISIBLE);
                ll_finish_product_weight.setVisibility(View.VISIBLE);
                v_finish_product_weight.setVisibility(View.VISIBLE);

                tv_finish_product_weight.setText(finish_product_weight);
            }


            tv_housing_type = findViewById(R.id.tv_housing_type);
            tvt_housing_type = findViewById(R.id.tvt_housing_type);
            ll_housing_type = findViewById(R.id.ll_housing_type);
            if (!Objects.equals(housing_type, "")) {
                tv_housing_type.setVisibility(View.VISIBLE);
                tvt_housing_type.setVisibility(View.VISIBLE);
                ll_housing_type.setVisibility(View.VISIBLE);

                tv_housing_type.setText(housing_type);
            }
        }

        tv_colour=findViewById(R.id.tv_colour);
        tvt_colour=findViewById(R.id.tvt_colour);
        ll_Color=findViewById(R.id.ll_Color);
        if(!Objects.equals(colour, "")){
            tv_colour.setVisibility(View.VISIBLE);
            tvt_colour.setVisibility(View.VISIBLE);
            ll_Color.setVisibility(View.VISIBLE);

            tv_colour.setText(colour);
        }


        //fifth line - no of led - lumen per led- lumed per meter-  led package-->
        ///////////////////signle line
        llh_nollpllpmlp=findViewById(R.id.llh_nollpllpmlp);
        if(!Objects.equals(no_of_led, "") || !Objects.equals(lumen_per_led, "") ||!Objects.equals(lumen_per_mtr, "") ||!Objects.equals(led_package, "")){


            llh_nollpllpmlp.setVisibility(View.VISIBLE);
        tv_no_of_led=findViewById(R.id.tv_no_of_led);
        tvt_no_of_led=findViewById(R.id.tvt_no_of_led);
        ll_no_of_led=findViewById(R.id.ll_no_of_led);
        v_no_of_led=findViewById(R.id.v_no_of_led);
        if(!Objects.equals(no_of_led, "")){
            tv_no_of_led.setVisibility(View.VISIBLE);
            ll_no_of_led.setVisibility(View.VISIBLE);
            v_no_of_led.setVisibility(View.VISIBLE);
            tvt_no_of_led.setVisibility(View.VISIBLE);

            tv_no_of_led.setText(no_of_led);
        }



        tv_lumen_per_led=findViewById(R.id.tv_lumen_per_led);
        tvt_lumen_per_led=findViewById(R.id.tvt_lumen_per_led);
        ll_lumen_per_led=findViewById(R.id.ll_lumen_per_led);
        v_lumen_per_led=findViewById(R.id.v_lumen_per_led);
        if(!Objects.equals(lumen_per_led, "")){
            tv_lumen_per_led.setVisibility(View.VISIBLE);
            tvt_lumen_per_led.setVisibility(View.VISIBLE);
            ll_lumen_per_led.setVisibility(View.VISIBLE);
            v_lumen_per_led.setVisibility(View.VISIBLE);

            tv_lumen_per_led.setText(lumen_per_led);
        }

        tv_lumen_per_mtr=findViewById(R.id.tv_lumen_per_mtr);
        tvt_lumen_per_mtr=findViewById(R.id.tvt_lumen_per_mtr);
        ll_lumen_per_mtr=findViewById(R.id.ll_lumen_per_mtr);
        v_lumen_per_mtr=findViewById(R.id.v_lumen_per_mtr);
        if(!Objects.equals(lumen_per_mtr, "")){
            tv_lumen_per_mtr.setVisibility(View.VISIBLE);
            tvt_lumen_per_mtr.setVisibility(View.VISIBLE);
            ll_lumen_per_mtr.setVisibility(View.VISIBLE);
            v_lumen_per_mtr.setVisibility(View.VISIBLE);

            tv_lumen_per_mtr.setText(lumen_per_mtr);
        }
            tv_led_package=findViewById(R.id.tv_led_package);
            tvt_led_package=findViewById(R.id.tvt_led_package);
            ll_led_package=findViewById(R.id.ll_led_package);
//        v_led_package=findViewById(R.id.v_led_package);
            if(!Objects.equals(led_package, "")){
                tv_led_package.setVisibility(View.VISIBLE);
                tvt_led_package.setVisibility(View.VISIBLE);
                ll_led_package.setVisibility(View.VISIBLE);

                tv_led_package.setText(led_package);
            }

        }


        ////////////////////////////////////////




//////////////////////////////////////////////////
// seventh line iprating - dia - total length
//        Toast.makeText(this, "total length"+total_length, Toast.LENGTH_SHORT).show();
        llh_IPR_D_TL=findViewById(R.id.llh_IPR_D_TL);
//        if(!Objects.equals(ip_rating, "") || !Objects.equals(dia, "")) {


//            llh_nollpllpmlp.setVisibility(View.VISIBLE);

            tv_ip_rating = findViewById(R.id.tv_ip_rating);
            tvt_ip_rating = findViewById(R.id.tvt_ip_rating);
            ll_ip_rating = findViewById(R.id.ll_ip_rating);
//            v_ip_rating = findViewById(R.id.v_ip_rating);
//        tvt_ip_rating=findViewById(R.id.tvt_ip_rating);
            if (!Objects.equals(ip_rating, "")) {
                tv_ip_rating.setVisibility(View.VISIBLE);
                ll_ip_rating.setVisibility(View.VISIBLE);
                tvt_ip_rating.setVisibility(View.VISIBLE);
//                v_ip_rating.setVisibility(View.VISIBLE);

                tv_ip_rating.setText(ip_rating);
            }
            tv_dia = findViewById(R.id.tv_dia);
            tvt_dia = findViewById(R.id.tvt_dia);
            ll_dia = findViewById(R.id.ll_dia);
            v_dia = findViewById(R.id.v_dia);
            if (!Objects.equals(dia, "")) {
                tv_dia.setVisibility(View.VISIBLE);
                tvt_dia.setVisibility(View.VISIBLE);
                ll_dia.setVisibility(View.VISIBLE);
                v_dia.setVisibility(View.VISIBLE);

                tv_dia.setText(dia);
//            }

        }
        llh_TL_S=findViewById(R.id.llh_TL_S);
        if(!Objects.equals(total_length, "") || !Objects.equals(smd, "")) {


            llh_TL_S.setVisibility(View.VISIBLE);
            tv_total_length = findViewById(R.id.tv_total_length);
            tvt_total_length = findViewById(R.id.tvt_total_length);
            ll_total_length = findViewById(R.id.ll_total_length);
            if (!Objects.equals(total_length, "")) {
                tv_total_length.setVisibility(View.VISIBLE);
                tvt_total_length.setVisibility(View.VISIBLE);
                ll_total_length.setVisibility(View.VISIBLE);

                tv_total_length.setText(total_length);
            }
            tv_smd = findViewById(R.id.tv_smd);
            tvt_smd = findViewById(R.id.tvt_smd);
            ll_smd = findViewById(R.id.ll_smd);
            v_smd = findViewById(R.id.v_smd);
            if (!Objects.equals(smd, "")) {
                tv_smd.setVisibility(View.VISIBLE);
                tvt_smd.setVisibility(View.VISIBLE);
                ll_smd.setVisibility(View.VISIBLE);
                v_smd.setVisibility(View.VISIBLE);

                tv_smd.setText(smd);
            }

        }
        tv_product_specification=findViewById(R.id.tv_product_specification);
        tvt_product_specification=findViewById(R.id.tvt_product_specification);
        ll_product_specification=findViewById(R.id.ll_product_specification);
        if(!Objects.equals(product_specification, "")){
            tv_product_specification.setVisibility(View.VISIBLE);
            tvt_product_specification.setVisibility(View.VISIBLE);
            ll_product_specification.setVisibility(View.VISIBLE);

            tv_product_specification.setText(product_specification);
        }
///////////////////////////////////////////////


        tv_smd=findViewById(R.id.tv_smd);
        if(!Objects.equals(smd, "")){
            tv_smd.setVisibility(View.VISIBLE);

            tv_smd.setText(smd);
        }



//        tv_remarks=findViewById(R.id.tv_remarks);
//        if(remarks!=""){
//            tv_remarks.setVisibility(View.VISIBLE);
//
//            tv_remarks.setText(remarks);
//        }















//        Toast.makeText(this, "productCode="+product_Code+category, Toast.LENGTH_SHORT).show();






    }
}