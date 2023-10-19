package com.listerled.listerlighting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<DataModel1> productList;
//    private SelectListener selectListener;

//    private OnItemClickListener mListener;


//    public interface  OnItemClickListener{
//
//        void gotoProductDetails(int position, String title);
//    }

//    public void setOnItemClickListener(OnItemClickListener listener){
//        mListener=listener;
//    }
    public ProductsAdapter(Context mCtx, List<DataModel1> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.data_row, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        DataModel1 product = productList.get(position);

        String sno=product.getSno().trim();
        String productcode=product.getProductCode().trim();
        String series=product.getSeries().trim();
        String model=product.getModel().trim();
        String url=product.getUrl().trim();
        String hotselling=product.getHotSelling();
        String upcoming=product.getUpcoming();
        String offer=product.getOffer();


//        holder.sno.setText(sno);
        holder.brand.setText(series);
//        holder.color.setText(color);
        holder.model.setText(productcode+" "+model);
//        holder.stock.setText(stock);
//        holder.url.setText(image);
        Glide.with(mCtx).load(url).into(holder.imageview);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, ProductList.class);
                intent.putExtra("brand", series);
                intent.putExtra("model", model);
                intent.putExtra("productcode", productcode);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView sno,model,color,brand,stock;
        private ImageView imageview;
        private CardView card;


        public ProductViewHolder(View itemView) {
            super(itemView);

//            sno=itemView.findViewById(R.id.sno);
//            url=itemView.findViewById(R.id.url);
            model=itemView.findViewById(R.id.modelname);
//            color=itemView.findViewById(R.id.color);
            brand=itemView.findViewById(R.id.brand);
//            stock=itemView.findViewById(R.id.stock);
            imageview=itemView.findViewById(R.id.imageview);
            card=itemView.findViewById(R.id.card);


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
