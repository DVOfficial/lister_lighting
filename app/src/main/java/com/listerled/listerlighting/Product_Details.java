package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Product_Details extends AppCompatActivity {


    String sr_No,product_Code,category,series,model,shape,watt,power_factor,withstand_voltage,surge_protection,lumen,ip_rating,
            size,cut_out_size,housing_type,colour,dia,no_of_led,lumen_per_led,led_package,total_length,lumen_per_mtr,smd,finish_product_size,finish_product_weight,
            remarks,product_specification,output_voltage,output_current,thd;

    TextView tv_sr_No,tv_product_Code,tv_category,tv_series,tv_model,tv_shape,tv_watt,tv_power_factor,tv_withstand_voltage,tv_surge_protection,tv_lumen,tv_ip_rating,
            tv_size,tv_cut_out_size,tv_housing_type,tv_colour,tv_dia,tv_no_of_led,tv_lumen_per_led,tv_led_package,tv_total_length,tv_lumen_per_mtr,tv_smd,tv_finish_product_size,tv_finish_product_weight,
            tv_remarks,tv_product_specification,tv_output_voltage,tv_output_current,tv_thd,tv_title,tvt_watt,tvt_shape,tvt_series,

    tvt_model,tvt_power_factor,tvt_withstand_voltage,tvt_surge_protection,tvt_lumen,tvt_ip_rating,
            tvt_size,tvt_cut_out_size,tvt_housing_type,tvt_colour,tvt_dia,tvt_no_of_led,tvt_lumen_per_led,tvt_led_package,tvt_total_length,tvt_lumen_per_mtr,tvt_smd,tvt_finish_product_size,tvt_finish_product_weight,
            tvt_remarks,tvt_product_specification,tvt_output_voltage,tvt_output_current,tvt_thd,tvt_title;


    LinearLayout ll_power_factor,ll_withstand_voltage,ll_surge_protection,ll_lumen,ll_ip_rating,
            ll_size,ll_cut_out_size,ll_housing_type,ll_dia,ll_no_of_led,ll_lumen_per_led,ll_led_package,ll_total_length,ll_lumen_per_mtr,ll_finish_product_size,ll_finish_product_weight,
            ll_product_specification,ll_output_voltage,ll_output_current,ll_thd;
    
    View v_power_factor,v_withstand_voltage,v_surge_protection,v_lumen,v_ip_rating,
    v_size,v_cut_out_size,v_housing_type,v_dia,v_no_of_led,v_lumen_per_led,v_led_package,v_total_length,v_lumen_per_mtr,v_finish_product_size,v_finish_product_weight,
    v_product_specification,v_output_voltage,v_output_current,v_thd;

    BottomNavigationView bottom_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        Bundle bundle = getIntent().getExtras();


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
                return false;
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


//        String title= productCode +" "+model;power_factor
        tv_product_Code=findViewById(R.id.tv_product_Code);
        tv_title=findViewById(R.id.tv_title);
        tv_product_Code.setVisibility(View.VISIBLE);

//        tv_product_Code.setText("check now"+product_Code+"\n"+category+"\n"+series+"\n"+model+"\n"+shape+"\n"+watt+"\n"+power_factor+"\n"+withstand_voltage+"\n"+surge_protection+
//                        "\n"+lumen+"\n"+ip_rating+"\n"+size+
//                "\n"+cut_out_size+"\n"+housing_type+"\n"+colour+"\n"+dia+"\n"+no_of_led+"\n"+lumen_per_led+"\n"+led_package+"\n"+total_length+"\n"+lumen_per_mtr+
//                "\n"+smd+"\n"+finish_product_size+"\n"+finish_product_weight+"\n"+remarks+"\n"+product_specification+
//        "\n"+output_voltage+"\n"+output_current+"\n"+thd);



        tv_title.setText(product_Code);

        if(product_Code!=null){
            tv_product_Code.setVisibility(View.VISIBLE);

            tv_product_Code.setText(product_Code);
        }
        tv_category=findViewById(R.id.tv_category);
        if(category!=null){
            tv_category.setVisibility(View.VISIBLE);

            tv_category.setText(category);
        }
        tv_series=findViewById(R.id.tv_series);
        tvt_series=findViewById(R.id.tvt_series);
        if(series!=null){
            tv_series.setVisibility(View.VISIBLE);
            tvt_series.setVisibility(View.VISIBLE);

            tv_series.setText(series);
        }
        tv_model=findViewById(R.id.tv_model);
        if(model!=null){
            tv_model.setVisibility(View.VISIBLE);

            tv_model.setText(model);
        }
        tv_shape=findViewById(R.id.tv_shape);
        tvt_shape=findViewById(R.id.tvt_shape);
        if(shape!=null){
            tv_shape.setVisibility(View.VISIBLE);
            tvt_shape.setVisibility(View.VISIBLE);

            tv_shape.setText(shape);
        }
        tv_watt=findViewById(R.id.tv_watt);
        tvt_watt=findViewById(R.id.tvt_watt);
        if(watt!=null){
            tv_watt.setVisibility(View.VISIBLE);
            tvt_watt.setVisibility(View.VISIBLE);

            tv_watt.setText(watt);
        }


        ////////////////////////////////////////////////////////////////single row
        tv_power_factor=findViewById(R.id.tv_power_factor);
        tvt_power_factor=findViewById(R.id.tvt_power_factor);
        ll_power_factor=findViewById(R.id.ll_power_factor);
        v_power_factor=findViewById(R.id.v_power_factor);
        if(power_factor!=null){
            tv_power_factor.setVisibility(View.VISIBLE);
            tvt_power_factor.setVisibility(View.VISIBLE);
            ll_power_factor.setVisibility(View.VISIBLE);
            v_power_factor.setVisibility(View.VISIBLE);

            tv_power_factor.setText(power_factor);
        }

        tv_withstand_voltage=findViewById(R.id.tv_withstand_voltage);
        tvt_withstand_voltage=findViewById(R.id.tvt_withstand_voltage);
        ll_withstand_voltage=findViewById(R.id.ll_withstand_voltage);
        v_withstand_voltage=findViewById(R.id.v_withstand_voltage);
        if(withstand_voltage!=null){
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
        if(surge_protection!=null){
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
        if(lumen!=null){
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
        if(output_voltage!=null){
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
        if(output_current!=null){
            tv_output_current.setVisibility(View.VISIBLE);
            ll_output_current.setVisibility(View.VISIBLE);
            v_output_current.setVisibility(View.VISIBLE);
            tvt_output_current.setVisibility(View.VISIBLE);

            tv_output_current.setText(output_current);
        }

        tv_thd=findViewById(R.id.tv_thd);
        tvt_thd=findViewById(R.id.tvt_thd);
        ll_thd=findViewById(R.id.ll_thd);
        if(thd!=null){
            tv_thd.setVisibility(View.VISIBLE);
            tvt_thd.setVisibility(View.VISIBLE);
            ll_thd.setVisibility(View.VISIBLE);

            tv_thd.setText(thd);
        }
//        tv_output_voltage=findViewById(R.id.tv_output_voltage);
//        if(output_voltage!=null){
//            tv_output_voltage.setVisibility(View.VISIBLE);
//
//            tv_output_voltage.setText(output_voltage);
//        }
//        tv_output_current=findViewById(R.id.tv_output_current);
//        if(output_current!=null){
//            tv_output_current.setVisibility(View.VISIBLE);
//
//            tv_output_current.setText(output_current);
//        }
//        tv_thd=findViewById(R.id.tv_thd);
//        if(thd!=null){
//            tv_thd.setVisibility(View.VISIBLE);
//
//            tv_thd.setText(thd);
//        }


/////////////////////////////////////////////////////////////////////////////////

        tv_size=findViewById(R.id.tv_size);
        tvt_size=findViewById(R.id.tvt_size);
        tvt_size=findViewById(R.id.tvt_size);
        ll_size=findViewById(R.id.ll_size);
        v_size=findViewById(R.id.v_size);
        if(tv_size!=null){
            tv_size.setVisibility(View.VISIBLE);
            tvt_size.setVisibility(View.VISIBLE);
            ll_size.setVisibility(View.VISIBLE);
            v_size.setVisibility(View.VISIBLE);

            tv_size.setText(size);
        }
        tv_led_package=findViewById(R.id.tv_led_package);
        tvt_led_package=findViewById(R.id.tvt_led_package);
        ll_led_package=findViewById(R.id.ll_led_package);
//        v_led_package=findViewById(R.id.v_led_package);
        if(led_package!=null){
            tv_led_package.setVisibility(View.VISIBLE);
            tvt_led_package.setVisibility(View.VISIBLE);
            ll_led_package.setVisibility(View.VISIBLE);

            tv_led_package.setText(led_package);
        }

        tv_cut_out_size=findViewById(R.id.tv_cut_out_size);
        tvt_cut_out_size=findViewById(R.id.tvt_cut_out_size);
        ll_cut_out_size=findViewById(R.id.ll_cut_out_size);
        v_cut_out_size=findViewById(R.id.v_cut_out_size);
        if(cut_out_size!=null){
            tv_cut_out_size.setVisibility(View.VISIBLE);
            tvt_cut_out_size.setVisibility(View.VISIBLE);
            ll_cut_out_size.setVisibility(View.VISIBLE);
            v_cut_out_size.setVisibility(View.VISIBLE);

            tv_cut_out_size.setText(cut_out_size);
        }

        tv_finish_product_size=findViewById(R.id.tv_finish_product_size);
        tvt_finish_product_size=findViewById(R.id.tvt_finish_product_size);
        ll_finish_product_size=findViewById(R.id.ll_finish_product_size);
        v_finish_product_size=findViewById(R.id.v_finish_product_size);
        if(finish_product_size!=null){
            tv_finish_product_size.setVisibility(View.VISIBLE);
            tvt_finish_product_size.setVisibility(View.VISIBLE);
            ll_finish_product_size.setVisibility(View.VISIBLE);
            v_finish_product_size.setVisibility(View.VISIBLE);

            tv_finish_product_size.setText(finish_product_size);
        }
        tv_finish_product_weight=findViewById(R.id.tv_finish_product_weight);
        tvt_finish_product_weight=findViewById(R.id.tvt_finish_product_weight);
        ll_finish_product_weight=findViewById(R.id.ll_finish_product_weight);
        v_finish_product_weight=findViewById(R.id.v_finish_product_weight);
        if(finish_product_weight!=null){
            tv_finish_product_weight.setVisibility(View.VISIBLE);
            tvt_finish_product_weight.setVisibility(View.VISIBLE);
            ll_finish_product_weight.setVisibility(View.VISIBLE);
            v_finish_product_weight.setVisibility(View.VISIBLE);

            tv_finish_product_weight.setText(finish_product_weight);
        }



        tv_housing_type=findViewById(R.id.tv_housing_type);
        tvt_housing_type=findViewById(R.id.tvt_housing_type);
        ll_housing_type=findViewById(R.id.ll_housing_type);
        if(housing_type!=null){
            tv_housing_type.setVisibility(View.VISIBLE);
            tvt_housing_type.setVisibility(View.VISIBLE);
            ll_housing_type.setVisibility(View.VISIBLE);

            tv_housing_type.setText(housing_type);
        }
        tv_colour=findViewById(R.id.tv_colour);
        tvt_colour=findViewById(R.id.tvt_colour);
        if(colour!=null){
            tv_colour.setVisibility(View.VISIBLE);
            tvt_colour.setVisibility(View.VISIBLE);

            tv_colour.setText(colour);
        }



        ///////////////////signle line
        tv_no_of_led=findViewById(R.id.tv_no_of_led);
        tvt_no_of_led=findViewById(R.id.tvt_no_of_led);
        ll_no_of_led=findViewById(R.id.ll_no_of_led);
        v_no_of_led=findViewById(R.id.v_no_of_led);
        if(no_of_led!=null){
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
        if(lumen_per_led!=null){
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
        if(lumen_per_mtr!=null){
            tv_lumen_per_mtr.setVisibility(View.VISIBLE);
            tvt_lumen_per_mtr.setVisibility(View.VISIBLE);
            ll_lumen_per_mtr.setVisibility(View.VISIBLE);
            v_lumen_per_mtr.setVisibility(View.VISIBLE);

            tv_lumen_per_mtr.setText(lumen_per_mtr);
        }



        ////////////////////////////////////////




//////////////////////////////////////////////////
        tv_ip_rating=findViewById(R.id.tv_ip_rating);
        tvt_ip_rating=findViewById(R.id.tvt_ip_rating);
        ll_ip_rating=findViewById(R.id.ll_ip_rating);
        v_ip_rating=findViewById(R.id.v_ip_rating);
//        tvt_ip_rating=findViewById(R.id.tvt_ip_rating);
        if(ip_rating!=null){
            tv_ip_rating.setVisibility(View.VISIBLE);
            ll_ip_rating.setVisibility(View.VISIBLE);
            tvt_ip_rating.setVisibility(View.VISIBLE);
            v_ip_rating.setVisibility(View.VISIBLE);

            tv_ip_rating.setText(ip_rating);
        }
        tv_dia=findViewById(R.id.tv_dia);
        tvt_dia=findViewById(R.id.tvt_dia);
        ll_dia=findViewById(R.id.ll_dia);
       v_dia=findViewById(R.id.v_dia);
        if(dia!=null){
            tv_dia.setVisibility(View.VISIBLE);
            tvt_dia.setVisibility(View.VISIBLE);
            ll_dia.setVisibility(View.VISIBLE);
            v_dia.setVisibility(View.VISIBLE);

            tv_dia.setText(dia);
        }
        tv_total_length=findViewById(R.id.tv_total_length);
        tvt_total_length=findViewById(R.id.tvt_total_length);
        ll_total_length=findViewById(R.id.ll_total_length);
        if(total_length!=null){
            tv_total_length.setVisibility(View.VISIBLE);
            tvt_total_length.setVisibility(View.VISIBLE);
            ll_total_length.setVisibility(View.VISIBLE);

            tv_total_length.setText(total_length);
        }

        tv_product_specification=findViewById(R.id.tv_product_specification);
        tvt_product_specification=findViewById(R.id.tvt_product_specification);
        ll_product_specification=findViewById(R.id.ll_product_specification);
        if(product_specification!=null){
            tv_product_specification.setVisibility(View.VISIBLE);
            tvt_product_specification.setVisibility(View.VISIBLE);
            ll_product_specification.setVisibility(View.VISIBLE);

            tv_product_specification.setText(product_specification);
        }
///////////////////////////////////////////////


//        tv_smd=findViewById(R.id.tv_smd);
//        if(smd!=null){
//            tv_smd.setVisibility(View.VISIBLE);
//
//            tv_smd.setText(smd);
//        }



//        tv_remarks=findViewById(R.id.tv_remarks);
//        if(remarks!=null){
//            tv_remarks.setVisibility(View.VISIBLE);
//
//            tv_remarks.setText(remarks);
//        }















//        Toast.makeText(this, "productCode="+product_Code+category, Toast.LENGTH_SHORT).show();






    }
}