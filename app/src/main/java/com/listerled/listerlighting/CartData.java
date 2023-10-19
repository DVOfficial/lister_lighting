package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.listerled.listerlighting.adaptor.ProductDao;
import com.listerled.listerlighting.adaptor.myadapter;
import com.listerled.listerlighting.model.AppDatabase;
import com.listerled.listerlighting.model.CartModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartData extends AppCompatActivity {


    RecyclerView recview;
    TextView rateview;
    Button btn_PlaceOrder;
    myadapter adapter;

    ProgressDialog dialogEng;

    BottomNavigationView bottom_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_data);


//        getSupportActionBar().hide();

        rateview=findViewById(R.id.rateview);
        btn_PlaceOrder=findViewById(R.id.btn_PlaceOrder);
        getroomdata();
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(R.drawable.back);

        // showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);

//        ////////
        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_home);
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


        btn_PlaceOrder.setOnClickListener(v -> {
        dialogEng = new ProgressDialog(CartData.this);
        dialogEng.setTitle("Wait Please... ");
        dialogEng.setMessage("Order is submitting");
        dialogEng.show();
            AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"cart_db").allowMainThreadQueries().build();
            ProductDao productDao=db.ProductDao();
            Handler handler1 = new Handler();
            List<CartModel> list_CartModel=productDao.getallproduct();
            for(CartModel cartModel:list_CartModel) {
                handler1.postDelayed(new Runnable() {
                    final String partyName=cartModel.getPartyname();
                    final String partyCity=cartModel.getCity();
                    final String partyAddress=cartModel.getAddress();
                    final String productID=cartModel.getProduct_code();
                    final String productWatt=cartModel.getWatt();
                    final String productType=cartModel.getPdttype();
                    final String productColor=cartModel.getColour();
                    final String productQty= String.valueOf(cartModel.getQty());
                    final String productRemarks=cartModel.getRemarks();
                    @Override
                    public void run() {
                        String url2 = "https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?";
                        url2 = url2 + "action=addNewOrder&vendorName=" + partyName + "&city=" + partyCity + "&address=" + partyAddress +
                                "&productcode_model=" + productID
                                + "&watt=" + productWatt + "&producttype" + productType + "&color=" + productColor + "&quantity=" + productQty + "&remarks=" + productRemarks;

//        Toast.makeText(this, "a"+itemName+itemWatt+color+quantity+remarks+username+usercity, Toast.LENGTH_SHORT).show();
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,

                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(CartData.this, "Order Submitted Successfully", Toast.LENGTH_SHORT).show();
                                        productDao.deleteCart();
                                        list_CartModel.clear();
//                                        getroomdata();

                                        dialogEng.dismiss();
                                        ShowDialog();
;

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(CartData.this, "Order Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                        );
                        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
                        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                        stringRequest.setRetryPolicy(retryPolicy);

                        RequestQueue queue = Volley.newRequestQueue(CartData.this);
                        queue.add(stringRequest);

                    }
                }, 3000 );


//                Toast.makeText(PlaceOrder1.this, "Color:"+color+"\nSelected State: "+ productID +"\nSelected District: "+ productWatt, Toast.LENGTH_LONG).show();
                //        String url2="https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?" +
//                "action=addNewOrder&vendorName=1&address=2&productcode_model=3&watt=4&color=5&quantity=6&remarks=7";

            }
        });

    }

    private void ShowDialog() {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        dialogBuilder.setCanceledOnTouchOutside(false);
        //dialogBuilder.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        //interstitialAdBuild(Desc);

        View dialogView = inflater.inflate(R.layout.dialog_reqverify, null);
        TextView title=dialogView.findViewById(R.id.textView);

//        final EditText inputFormRemarks = dialogView.findViewById(R.id.edt_FormRemarks);
//        final EditText inputFormName = dialogView.findViewById(R.id.edt_FormName);
//        inputFormName.setVisibility(View.GONE);
        Button submit = dialogView.findViewById(R.id.buttonSubmit);
        Button cancel = dialogView.findViewById(R.id.buttonCancel);

//        Calendar calenderCC=Calendar.getInstance();
//        SimpleDateFormat simpleDateFormatCC= new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
//        final String dateTimeCC=simpleDateFormatCC.format(calenderCC.getTime());

//        String TitleNew="Hi, You have selected \""+Desc+"\" Exam Form to be filled by us," +
//                "\nPlease ask any Query If you have?";
//        title.setText(TitleNew);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartData.this,HomePage_f.class));
                dialogBuilder.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartData.this,PlaceOrder1.class));
                dialogBuilder.dismiss();


            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    //    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    public void getroomdata()
    {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "cart_db").allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();

        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        List<CartModel> products=productDao.getallproduct();

        if (products.size()==0){
            Toast.makeText(CartData.this,"No product Found /n Please add product to cart",Toast.LENGTH_SHORT);

        }else{
            adapter=new myadapter(products, rateview);
            recview.setAdapter(adapter);

            int sum=0,i;
//        for(i=0;i< products.size();i++)
//            sum=sum+(products.getQty());
//            sum=sum+(products.get(i).getPrice()*products.get(i).getQty());

            rateview.setText("Total Product : "+products.size());

        }

    }
}