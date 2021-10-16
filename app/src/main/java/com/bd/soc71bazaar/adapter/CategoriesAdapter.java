package com.bd.soc71bazaar.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bd.soc71bazaar.ProductViewPage;
import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.model.admin.CategoriesModel;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private List<CategoriesModel> moviesList;
    private static View mRootView;
    Context context;
    String viewPage;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView grid_item_label;
        LinearLayout llCatagory;

        public MyViewHolder(View view) {
            super(view);
            grid_item_label = view.findViewById(R.id.grid_item_label);
            llCatagory = view.findViewById(R.id.llCatagory);
        }
    }


    public CategoriesAdapter(List<CategoriesModel> moviesList, Context context, String viewPage) {
        this.moviesList = moviesList;
        this.context = context;
        this.viewPage = viewPage;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRootView = recyclerView.getRootView();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewPage.equals("HomePage")){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            return new MyViewHolder(itemView);
        }else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_view_adapter, parent, false);
            return new MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        CategoriesModel movie = moviesList.get(position);
        holder.llCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductViewPage.class);
                intent.putExtra("ViewType","Categories");
                intent.putExtra("ViewName",moviesList.get(position).getCategories_name());
                intent.putExtra("ViewId",moviesList.get(position).getCategories_id());
                context.startActivity(intent);
            }
        });
        holder.grid_item_label.setText(movie.getCategories_name());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}