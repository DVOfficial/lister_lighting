package com.listerled.listerlighting.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.listerled.listerlighting.R;
import com.listerled.listerlighting.model.AppDatabase;
import com.listerled.listerlighting.model.CartModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    List<CartModel> products;
    TextView rateview;
    public myadapter(List<CartModel> products, TextView rateview) {
        this.products = products;
        this.rateview= rateview;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myviewholder holder, int position) {


       holder.recid.setText(String.valueOf(products.get(position).getProduct_code()));
       holder.recpwatt.setText(products.get(position).getWatt());
       holder.recpcolor.setText(products.get(position).getColour());
//       holder.recpprice.setText(String.valueOf(products.get(position).getColour()));
       holder.recqnt.setText(String.valueOf(products.get(position).getQty()));
       holder.recremarks.setText("Remarks: "+String.valueOf(products.get(position).getRemarks()));

       holder.delbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AppDatabase db = Room.databaseBuilder(holder.recid.getContext(),
               AppDatabase.class, "cart_db").allowMainThreadQueries().build();
               ProductDao productDao = db.ProductDao();

               productDao.deleteById(products.get(position).getProductcode_watt());
               products.remove(position);
               notifyItemRemoved(position);
               updateprice();
           }
       });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView recid, recpwatt,recqnt,recpcolor, recpprice,recremarks;
        ImageButton delbtn;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            recid=itemView.findViewById(R.id.recid);
            recpwatt =itemView.findViewById(R.id.recpwatt);
            recpcolor=itemView.findViewById(R.id.recpcolor);
            recqnt=itemView.findViewById(R.id.recqnt);
            delbtn=itemView.findViewById(R.id.delbtn);
            recremarks=itemView.findViewById(R.id.recremarks);
        }
    }

    public void updateprice()
    {
        int sum=0,i;
//        for(i=0;i< products.size();i++)
//            sum=sum+(products.get(i).getPrice()*products.get(i).getQnt());

        rateview.setText("Total Item Count: "+products.size());
    }

}
