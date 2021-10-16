package com.bd.soc71bazaar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bd.soc71bazaar.ProductViewPage;
import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.model.FeaturedProductsModel;

import java.util.ArrayList;

public class FeaturedProductsAdapter extends RecyclerView.Adapter<FeaturedProductsAdapter.MyViewHolder> {

    private ArrayList<FeaturedProductsModel> moviesList;
    Context context;
    String vieType;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCategoriesName;
        public LinearLayout llFetureLayout;

        public MyViewHolder(View view) {
            super(view);
            tvCategoriesName = view.findViewById(R.id.tvCategoriesName);
            llFetureLayout = view.findViewById(R.id.llFetureLayout);
        }
    }


    public FeaturedProductsAdapter(ArrayList<FeaturedProductsModel> moviesList, Context context, String vieType) {
        this.moviesList = moviesList;
        this.context = context;
        this.vieType = vieType;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (vieType.equals("AllView")){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_view_adapeter_all, parent, false);
            return new MyViewHolder(itemView);

        }else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_adapter, parent, false);
            return new MyViewHolder(itemView);

        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        FeaturedProductsModel movie = moviesList.get(position);

        holder.llFetureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductViewPage.class);
                intent.putExtra("ViewType","Brand");
                intent.putExtra("ViewName",moviesList.get(position).getGenre());
                intent.putExtra("ViewId",moviesList.get(position).getTitle());
                context.startActivity(intent);
            }
        });
        holder.tvCategoriesName.setText(movie.getGenre());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}