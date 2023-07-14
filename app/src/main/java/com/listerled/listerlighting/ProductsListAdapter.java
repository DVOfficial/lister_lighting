package com.listerled.listerlighting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<DataDescModel> productDescList;

    private onRecyclerViewClickListener listener;


    public interface onRecyclerViewClickListener{
        void onItemClick(int position);
    }


    public void onRecyclerViewClickListener(onRecyclerViewClickListener listener) {
        this.listener = listener;
    }



    public ProductsListAdapter(Context mCtx, List<DataDescModel> productDescList) {
        this.mCtx = mCtx;
        this.productDescList = productDescList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.datadesc_row, null);
        return new ProductViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        DataDescModel product = productDescList.get(position);
//
//        String sno=product.getSr_No().trim();
//        String productcode=product.getProduct_Code().trim();
//        String series=product.getSeries().trim();
//        String model=product.getModel().trim();
//        String url=product.getuUrl().trim();
//        String hotselling=product.getHotSelling();
//        String upcoming=product.getUpcoming();
//        String offer=product.getOffer();

        String sr_No=product.getSr_No().trim();
        String product_Code=product.getProduct_Code().trim();
        String category=product.getCategory().trim();
        String series=product.getSeries().trim();
        String model=product.getModel().trim();
        String shape=product.getShape().trim();
        String watt=product.getWatt().trim();
        String power_factor=product.getPower_factor().trim();
        String withstand_voltage=product.getWithstand_voltage().trim();
        String surge_protection=product.getSurge_protection().trim();
        String lumen=product.getLumen().trim();
        String ip_rating=product.getIp_rating().trim();
        String size=product.getSize().trim();
        String cut_out_size=product.getCut_out_size().trim();
        String housing_type=product.getHousing_type().trim();
        String colour=product.getColour().trim();
        String dia=product.getDia().trim();
        String no_of_led=product.getNo_of_led().trim();
        String lumen_per_led=product.getLumen_per_led().trim();
        String led_package=product.getLed_package().trim();
        String total_length=product.getTotal_length().trim();
        String lumen_per_mtr=product.getLumen_per_mtr().trim();
        String smd=product.getSmd().trim();
        String finish_product_size=product.getFinish_product_size().trim();
        String finish_product_weight=product.getFinish_product_weight().trim();
        String remarks=product.getRemarks().trim();
        String product_specification=product.getProduct_specification().trim();
        String output_voltage=product.getOutput_voltage().trim();
        String output_current=product.getOutput_current().trim();
        String thd=product.getThd().trim();


//        holder.product_Code.setText(product_Code);
        holder.brand.setText(watt);
//        holder.category.setText(category);
        holder.model.setText(model);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, Product_Details.class);

                Bundle bundle = new Bundle();
                bundle.putString("housing_type", housing_type);
                bundle.putString("cut_out_size", cut_out_size);
                bundle.putString("size", size);
                bundle.putString("ip_rating", ip_rating);
                bundle.putString("lumen", lumen);
                bundle.putString("surge_protection", surge_protection);
                bundle.putString("withstand_voltage", withstand_voltage);
                bundle.putString("power_factor", power_factor);
                bundle.putString("watt", watt);
                bundle.putString("shape", shape);
                bundle.putString("model", model);
                bundle.putString("series", series);
                bundle.putString("category", category);
                bundle.putString("product_Code", product_Code);
                bundle.putString("sr_No", sr_No);
                bundle.putString("thd", thd);
                bundle.putString("output_current", output_current);
                bundle.putString("output_voltage", output_voltage);
                bundle.putString("product_specification", product_specification);
                bundle.putString("remarks", remarks);
                bundle.putString("finish_product_weight", finish_product_weight);
                bundle.putString("finish_product_size", finish_product_size);
                bundle.putString("smd", smd);
                bundle.putString("lumen_per_mtr", lumen_per_mtr);
                bundle.putString("total_length", total_length);
                bundle.putString("led_package", led_package);
                bundle.putString("lumen_per_led", lumen_per_led);
                bundle.putString("no_of_led", no_of_led);
                bundle.putString("dia", dia);
                bundle.putString("colour", colour);

                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });


//        holder.series.setText(series);
//        holder.shape.setText(shape);
//        Glide.with(mCtx).load(url).into(holder.imageview);

//        holder.model.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent=new Intent(mCtx,ProductDetails.class);
////                intent.putExtra("brand", series);
////                intent.putExtra("model", model);
////                intent.putExtra("productcode", product_Code);
////
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                mCtx.startActivity(intent);
//
//                Toast.makeText(mCtx, "model"+model, Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return productDescList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView product_Code,category,series,model,shape,brand;
        private ImageView imageview;
        private CardView card;


        public ProductViewHolder(View itemView, onRecyclerViewClickListener listener) {
            super(itemView);

//            product_Code=itemView.findViewById(R.id.tv_product_Code);
//            category=itemView.findViewById(R.id.tv_category);
            model=itemView.findViewById(R.id.modelname);
//            series=itemView.findViewById(R.id.tv_series);
            brand=itemView.findViewById(R.id.brand);
//            shape=itemView.findViewById(R.id.tv_shape);
            imageview=itemView.findViewById(R.id.imageview);
            card=itemView.findViewById(R.id.card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });



//            card.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if (mListener != null) {
//
//                            int position = getAdapterPosition();
//
//                            //String offWeb=offWebSite.getText().toString();
//                            DataModel1 govtJob = productList.get(getAdapterPosition());
//                            String title = govtJob.getProductCode();
//                            //String moreInfoLink=moreInfo.getText().toString();
//                            if (position != RecyclerView.NO_POSITION) {
//                                mListener.gotoProductDetails(position,title);
//                            }
////                        selectListener.onItemClicked(productList.get(position));
//                            //mListener.likeAns(position, tag);
//
//                    }
//
//                }
//            });


        }
    }
}
