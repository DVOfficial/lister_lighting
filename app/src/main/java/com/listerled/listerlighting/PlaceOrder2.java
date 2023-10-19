package com.listerled.listerlighting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceOrder2 extends AppCompatActivity {


    RadioGroup rg_color_whitergvp,rg_pdttype;
    RadioButton rb_color_cw,rb_color_ww,rb_color_nw,rb_color_r,rb_color_g,rb_color_b,rb_color_p,rb_rd,rb_sq;
    private String productWatt, productID;                 //vars to hold the values of selected State and District
    private TextView tv_ProductId, tv_ProductWatt, tv_Color, tv_ProductType,tv_address, tv_partyName, tv_city;             //declaring TextView to show the errors
    private Spinner stateSpinner, districtSpinner;
    private String color="",userName,userCity;//Spinners
    private String pdtType="";//Spinners
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;

    private EditText et_Quantity, et_Remarks;

    private LinearLayout ll_pdttype,ll_colors,ll_remarks,ll_quantity;
    Button btn_Login1;
    private CardView cv_neworder;
    private String urlUser ="https://script.google.com/macros/s/AKfycbzpBQHf3aq3zZMJaVtKYtUfnb_T77i5xdtLV2GFrISN54Hwb2Nz2W6HM6IbyZaHYB1I/exec";
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order2);


        //State Spinner Initialisation
        stateSpinner = findViewById(R.id.spinner_indian_states);    //Finds a view that was identified by the android:id attribute in xml
        rg_color_whitergvp = findViewById(R.id.rg_color_whitergvp);    //Finds a view that was identified by the android:id attribute in xml
        rg_pdttype = findViewById(R.id.rg_pdttype);    //Finds a view that was identified by the android:id attribute in xml
        tv_Color = findViewById(R.id.tv_Color);    //Finds a view that was identified by the android:id attribute in xml
        ll_colors = findViewById(R.id.ll_colors);    //Finds a view that was identified by the android:id attribute in xml
        tv_ProductType = findViewById(R.id.tv_ProductType);    //Finds a view that was identified by the android:id attribute in xml
        ll_pdttype = findViewById(R.id.ll_pdttype);
        ll_remarks = findViewById(R.id.ll_remarks);
        ll_quantity = findViewById(R.id.ll_quantity);
        //Finds a view that was identified by the android:id attribute in xml
        et_Quantity  = findViewById(R.id.et_Quantity);    //Finds a view that was identified by the android:id attribute in xml
        et_Remarks = findViewById(R.id.et_Remarks);    //Finds a view that was identified by the android:id attribute in xml
        btn_Login1 = findViewById(R.id.btn_Login1);    //Finds a view that was identified by the android:id attribute in xml
//        btn_Login = findViewById(R.id.btn_Login);    //Finds a view that was identified by the android:id attribute in xml
        tv_address = findViewById(R.id.tv_address);    //Finds a view that was identified by the android:id attribute in xml
        tv_partyName = findViewById(R.id.tv_PartyName);    //Finds a view that was identified by the android:id attribute in xml
        tv_city = findViewById(R.id.tv_City);    //Finds a view that was identified by the android:id attribute in xml
        cv_neworder = findViewById(R.id.cv_neworder);    //Finds a view that was identified by the android:id attribute in xml
//        rg_color_rgvp = findViewById(R.id.rg_color_rgvp);

        rb_rd = findViewById(R.id.rb_rd);
        rb_sq = findViewById(R.id.rb_sq);
        rb_color_r = findViewById(R.id.rb_color_r);
        rb_color_g = findViewById(R.id.rb_color_g);
        rb_color_b = findViewById(R.id.rb_color_b);
        rb_color_p = findViewById(R.id.rb_color_p);
        rb_color_cw = findViewById(R.id.rb_color_cw);
        rb_color_ww = findViewById(R.id.rb_color_ww);
        rb_color_nw = findViewById(R.id.rb_color_nw);

        submitButton = findViewById(R.id.button_AddtoCart);
        tv_ProductId = findViewById(R.id.textView_indian_states);
        tv_ProductWatt = findViewById(R.id.textView_indian_districts);

        checkUserStatus();
        //Populate ArrayAdapter using string array and a spinner layout that we will define
        stateAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_indian_states, R.layout.spinner_layout);

        // Specify the layout to use when the list of choices appear
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stateSpinner.setAdapter(stateAdapter);            //Set the adapter to the spinner to populate the State Spinner

        //When any item of the stateSpinner uis selected
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Define City Spinner but we will populate the options through the selected state
                districtSpinner = findViewById(R.id.spinner_indian_districts);
                productID = stateSpinner.getSelectedItem().toString();
                //Obtain the selected State
                int parentID = parent.getId();
                if (parentID == R.id.spinner_indian_states){
                    switch (productID){
                        case "Select Your Product": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_default_districts, R.layout.spinner_layout);
                            break;
                        case "AD-101": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_101_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);

                            tv_ProductType.setVisibility(View.VISIBLE);
                            rg_pdttype.setVisibility(View.VISIBLE);
                            rb_rd.setVisibility(View.VISIBLE);
                            rb_sq.setVisibility(View.VISIBLE);
                            break;
                        case "AD-102": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_102_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            tv_ProductType.setVisibility(View.VISIBLE);
                            ll_pdttype.setVisibility(View.VISIBLE);
                            rg_pdttype.setVisibility(View.VISIBLE);
                            rb_rd.setVisibility(View.VISIBLE);
                            rb_sq.setVisibility(View.VISIBLE);
                            break;
                        case "AD-106": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_106_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            tv_ProductType.setVisibility(View.VISIBLE);
                            ll_pdttype.setVisibility(View.VISIBLE);
                            rg_pdttype.setVisibility(View.VISIBLE);
                            rb_rd.setVisibility(View.VISIBLE);
                            rb_sq.setVisibility(View.VISIBLE);
                            break;
                        case "AD-107": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_107_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            tv_ProductType.setVisibility(View.VISIBLE);
                            ll_pdttype.setVisibility(View.VISIBLE);
                            rg_pdttype.setVisibility(View.VISIBLE);
                            rb_sq.setVisibility(View.VISIBLE);
                            break;
                        case "AD-108": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_108_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            tv_ProductType.setVisibility(View.VISIBLE);
                            ll_pdttype.setVisibility(View.VISIBLE);
                            rg_pdttype.setVisibility(View.VISIBLE);
                            rb_rd.setVisibility(View.VISIBLE);
                            rb_sq.setVisibility(View.VISIBLE);
                            break;
                        case "AD-109": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_109_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            tv_ProductType.setVisibility(View.VISIBLE);
                            ll_pdttype.setVisibility(View.VISIBLE);
                            rg_pdttype.setVisibility(View.VISIBLE);
                            rb_rd.setVisibility(View.VISIBLE);
                            rb_sq.setVisibility(View.VISIBLE);
                            break;
                        case "AD-110": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_110_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            tv_ProductType.setVisibility(View.VISIBLE);
                            ll_pdttype.setVisibility(View.VISIBLE);
                            rg_pdttype.setVisibility(View.VISIBLE);
                            rb_rd.setVisibility(View.VISIBLE);
                            rb_sq.setVisibility(View.VISIBLE);
                            break;
                        case "AD-112": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_112_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            tv_ProductType.setVisibility(View.VISIBLE);
                            ll_pdttype.setVisibility(View.VISIBLE);
                            rg_pdttype.setVisibility(View.VISIBLE);
                            rb_rd.setVisibility(View.VISIBLE);
                            rb_sq.setVisibility(View.VISIBLE);
                            break;
                        case "AD-206": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_206_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            pdtType="none";
                            break;
                        case "AD-207": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_207_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            break;
                        case "AD-208": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_208_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-209": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_209_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-211": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_211_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;

                        case "AD-212": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_212_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-301": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_301_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-302": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_302_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-303": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_303_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-401": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_401_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-402": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_402_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-403": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_403_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-404": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_404_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-501": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_501_productid, R.layout.spinner_layout);
                            color="none";
                            pdtType="none";

                            break;
                        case "AD-502": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_502_productid, R.layout.spinner_layout);
                            color="none";
                            pdtType="none";

                            break;
                        case "AD-503": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_503_productid, R.layout.spinner_layout);
                            color="none";
                            pdtType="none";

                            break;
                        case "AD-505": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_505_productid, R.layout.spinner_layout);
                            color="none";
                            pdtType="none";

                            break;
                        case "AD-506": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_506_productid, R.layout.spinner_layout);
                            color="none";
                            pdtType="none";

                            break;
                        case "AD-507": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_507_productid, R.layout.spinner_layout);
                            color="none";
                            pdtType="none";

                            break;
                        case "AD-525": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_525_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-601": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_601_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-602": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_602_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-603": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_603_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-604": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_604_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-605": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_605_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-606": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_606_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-607": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_607_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-614": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_614_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-617": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_617_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-701": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_701_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            rb_color_r.setVisibility(View.VISIBLE);
                            rb_color_g.setVisibility(View.VISIBLE);
                            rb_color_b.setVisibility(View.VISIBLE);
                            rb_color_p.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-801": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_801_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-802": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_802_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-803": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_803_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-805": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_805_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-901": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_901_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            rb_color_nw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        case "AD-059": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ad_059_productid, R.layout.spinner_layout);
                            rg_color_whitergvp.setVisibility(View.VISIBLE);
                            rb_color_ww.setVisibility(View.VISIBLE);
                            rb_color_cw.setVisibility(View.VISIBLE);
                            pdtType="none";
                            tv_Color.setVisibility(View.VISIBLE);
                            ll_colors.setVisibility(View.VISIBLE);
                            break;
                        default:  break;
                    }

                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     // Specify the layout to use when the list of choices appears
                    districtSpinner.setAdapter(districtAdapter);        //Populate the list of Districts in respect of the State selected

                    //To obtain the selected District from the spinner
                    districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            productWatt = districtSpinner.getSelectedItem().toString();


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        if (!selectedState.equals("Select Your State")) {
//
//
//
//        }



        rb_color_ww.setOnClickListener(v -> {
            color = "Warm White";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_color_ww.setChecked(true);
            rb_color_cw.setChecked(false);
            rb_color_nw.setChecked(false);
            rb_color_r.setChecked(false);
            rb_color_g.setChecked(false);
            rb_color_b.setChecked(false);
            rb_color_p.setChecked(false);

        });
        rb_color_cw.setOnClickListener(v -> {
            color = "Cool White";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_color_ww.setChecked(false);
            rb_color_cw.setChecked(true);
            rb_color_nw.setChecked(false);
            rb_color_r.setChecked(false);
            rb_color_g.setChecked(false);
            rb_color_b.setChecked(false);
            rb_color_p.setChecked(false);

        });
        rb_color_nw.setOnClickListener(v -> {
            color = "Natural White";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_color_ww.setChecked(false);
            rb_color_cw.setChecked(false);
            rb_color_nw.setChecked(true);
            rb_color_r.setChecked(false);
            rb_color_g.setChecked(false);
            rb_color_b.setChecked(false);
            rb_color_p.setChecked(false);

        });
        rb_color_r.setOnClickListener(v -> {
            color = "Red";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_color_ww.setChecked(false);
            rb_color_cw.setChecked(false);
            rb_color_nw.setChecked(false);
            rb_color_r.setChecked(true);
            rb_color_g.setChecked(false);
            rb_color_b.setChecked(false);
            rb_color_p.setChecked(false);

        });
        rb_color_g.setOnClickListener(v -> {
            color = "Green";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_color_ww.setChecked(false);
            rb_color_cw.setChecked(false);
            rb_color_nw.setChecked(false);
            rb_color_r.setChecked(false);
            rb_color_g.setChecked(true);
            rb_color_b.setChecked(false);
            rb_color_p.setChecked(false);

        });

        rb_color_b.setOnClickListener(v -> {
            color = "Blue";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_color_ww.setChecked(false);
            rb_color_cw.setChecked(false);
            rb_color_nw.setChecked(false);
            rb_color_r.setChecked(false);
            rb_color_g.setChecked(false);
            rb_color_b.setChecked(true);
            rb_color_p.setChecked(false);

        });
        rb_color_p.setOnClickListener(v -> {
            color = "Purple";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_color_ww.setChecked(false);
            rb_color_cw.setChecked(false);
            rb_color_nw.setChecked(false);
            rb_color_r.setChecked(false);
            rb_color_g.setChecked(false);
            rb_color_b.setChecked(false);
            rb_color_p.setChecked(true);

        });
        rb_rd.setOnClickListener(v -> {
            pdtType = "Round";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_sq.setChecked(false);
            rb_rd.setChecked(true);

        });
        rb_sq.setOnClickListener(v -> {
            pdtType = "Square";
//            Toast.makeText(PlaceOrder1.this,color,Toast.LENGTH_SHORT).show();
            rb_sq.setChecked(true);
            rb_rd.setChecked(false);

        });


        //To display the selected State and District


        submitButton.setOnClickListener(v -> {
            if (productID.equals("Select Your Product")) {
                Toast.makeText(PlaceOrder2.this, "Please select your Product from the list", Toast.LENGTH_LONG).show();
                tv_ProductId.setError("Product is required!");      //To set error on TextView
                tv_ProductId.requestFocus();
            } else if (productWatt.equals("Select Your Watt")) {
                Toast.makeText(PlaceOrder2.this, "Please select your Watt from the list", Toast.LENGTH_LONG).show();
                tv_ProductWatt.setError("Watt is required!");
                tv_ProductWatt.requestFocus();
                tv_ProductId.setError(null);                      //To reove error from stateSpinner
            } else if (color.equals("")){
                Toast.makeText(PlaceOrder2.this, "Please select Color", Toast.LENGTH_LONG).show();
                tv_Color.setError("Color is required!");
                tv_Color.requestFocus();
                tv_Color.setError(null);                      //To reove error from stateSpinner

            }else if (pdtType.equals("")){
                Toast.makeText(PlaceOrder2.this, "Please select Product Type", Toast.LENGTH_LONG).show();
                tv_ProductType.setError("Product Type is required!");
                tv_ProductType.requestFocus();
                tv_ProductType.setError(null);                      //To reove error from stateSpinner

            }
            else
            {
                tv_ProductId.setError(null);
                tv_ProductWatt.setError(null);
                tv_Color.setError(null);
                String quantity= et_Quantity.getText().toString().trim();
                String remarks= et_Remarks.getText().toString().trim();
                String address= tv_address.getText().toString().trim();
                String partyname= tv_partyName.getText().toString().trim();
                String city= tv_city.getText().toString().trim();




//                Toast.makeText(PlaceOrder1.this, "Color:"+color+"\nSelected State: "+ productID +"\nSelected District: "+ productWatt, Toast.LENGTH_LONG).show();
                //        String url2="https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?" +
//                "action=addNewOrder&vendorName=1&address=2&productcode_model=3&watt=4&color=5&quantity=6&remarks=7";
                String url2="https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?";
                url2=url2+"action=addNewOrder&vendorName="+partyname+"&city="+city+"&address="+address+
                        "&productcode_model="+productID
                        +"&watt="+productWatt+"&producttype"+pdtType+"&color="+color+"&quantity="+quantity+"&remarks="+remarks;

//        Toast.makeText(this, "a"+itemName+itemWatt+color+quantity+remarks+username+usercity, Toast.LENGTH_SHORT).show();
                StringRequest stringRequest = new StringRequest(Request.Method.GET,url2,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(PlaceOrder2.this, "Order Submitted Successfully", Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(PlaceOrder2.this, "Order Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                ) {
                };

                int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
                RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                stringRequest.setRetryPolicy(retryPolicy);

                RequestQueue queue = Volley.newRequestQueue(PlaceOrder2.this);
                queue.add(stringRequest);
                ClearData() ;

            }
        });
    }

    private void ClearData() {
        rb_color_ww.setChecked(false);
        rb_color_cw.setChecked(false);
        rb_color_nw.setChecked(false);
        rb_color_r.setChecked(false);
        rb_color_g.setChecked(false);
        rb_color_b.setChecked(false);
        rb_color_p.setChecked(false);

//
//        String url2="https://script.google.com/macros/s/AKfycbyGakc775GFqx1tTEN9XJeHMqNG9AX0LuR8dTUDiZFFyuJ4RbFCg6fB5rHezbqNoAas/exec?";
//        url2=url2+"action=addNewOrder&vendorName="+partyname+"&city="+city+"&address="+address+"&productcode_model="+productID
//                +"&watt="+productWatt+"&producttype"+tv_ProductType+"&color="+color+"&quantity="+quantity+"&remarks="+remarks;

        color="";

        et_Quantity.setText("");
        et_Remarks.setText("");
        pdtType="";
        rb_sq.setChecked(false);
        rb_rd.setChecked(false);


    }

    public void checkUserStatus() {
//        btn_Login1 = findViewById(R.id.btn_Login1);    //Finds a view that was identified by the android:id attribute in xml
////
        SessionManagement sessionManagement = new SessionManagement(PlaceOrder2.this);
        int userId = sessionManagement.getSession();
        userName = sessionManagement.getSSession();
        userCity = sessionManagement.getSSSession();
        Toast.makeText(this, "show"+userId, Toast.LENGTH_SHORT).show();
        String id=userName+"_"+userCity;
        if (userId==(-1)) {

//            current_stock="Login";
            btn_Login1.setVisibility(View.VISIBLE);
            cv_neworder.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            btn_Login1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlaceOrder2.this, Login1Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });
            //user id logged in and so move to mainActivity
//                                    moveToMainActivity();

        }else{
            btn_Login1.setVisibility(View.GONE);
            cv_neworder.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
//            fetchProductList();
            GetUserData(userId);
        }
    }


    private void GetUserData(int id) {
//        dialogEng = new ProgressDialog(PlaceOrder.this);
//        dialogEng.setTitle("Wait Please... ");
//        dialogEng.setMessage("Loading Profile Data");
//        dialogEng.show();
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
//
                                tv_partyName.setText(party_name);
                                tv_address.setText(address);
                                tv_city.setText(city);
//                                tv_State.setText(state);



//                                }


                            }

//                            dialogEng.dismiss();
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
//                            dialogEng.dismiss();
                            Toast.makeText(PlaceOrder2.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlaceOrder2.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();
//                        dialogEng.dismiss();

                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
//        dialogEng.dismiss();
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
}