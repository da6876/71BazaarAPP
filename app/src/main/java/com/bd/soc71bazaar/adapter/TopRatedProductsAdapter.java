package com.bd.soc71bazaar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bd.soc71bazaar.ProductViewSingle;
import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.model.TopRatedModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.bd.soc71bazaar.adminpart.AdminDashBord.strImgRootUrl;
import static com.bd.soc71bazaar.adminpart.AdminDashBord.strRootUrl;

public class TopRatedProductsAdapter  extends RecyclerView.Adapter<TopRatedProductsAdapter.MyViewHolder> {

    private List<TopRatedModel> moviesList;
    private static View mRootView;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProductName,tvShortDetails,tvProductPrice;
        public ImageView ivProductImage;
        public LinearLayout lvcard;

        public MyViewHolder(View view) {
            super(view);
            tvProductName = view.findViewById(R.id.tvProductName);
            tvShortDetails = view.findViewById(R.id.tvShortDetails);
            tvProductPrice = view.findViewById(R.id.tvProductPrice);
            ivProductImage = view.findViewById(R.id.ivProductImage);
            lvcard = view.findViewById(R.id.lvcard);
        }
    }


    public TopRatedProductsAdapter(List<TopRatedModel> moviesList,Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRootView = recyclerView.getRootView();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TopRatedModel movie = moviesList.get(position);
        holder.lvcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductViewSingle.class);
                intent.putExtra("ID",moviesList.get(position).getProducts_id());
                intent.putExtra("Name",moviesList.get(position).getName());
                intent.putExtra("Discount",moviesList.get(position).getDiscount());
                intent.putExtra("Discount_price",moviesList.get(position).getDiscount_price());
                intent.putExtra("Sort_order",moviesList.get(position).getSort_order());
                intent.putExtra("Price",moviesList.get(position).getPrice());
                intent.putExtra("Details",moviesList.get(position).getDetails());
                intent.putExtra("country_name",moviesList.get(position).getcountry_name());
                intent.putExtra("Categories_name",moviesList.get(position).getCategories_name());
                intent.putExtra("Product_types_name",moviesList.get(position).getProduct_types_name());
                intent.putExtra("Brands_name",moviesList.get(position).getBrands_name());
                intent.putExtra("Image_one",moviesList.get(position).getImage_one());
                intent.putExtra("Image_two",moviesList.get(position).getImage_two());
                intent.putExtra("Image_three",moviesList.get(position).getImage_three());
                intent.putExtra("Image_four",moviesList.get(position).getImage_four());
                context.startActivity(intent);
            }
        });
//        holder.llCatagory.setBackgroundColor(Color.parseColor(movie.getCategories_name()));
        Picasso.get().load(strImgRootUrl + movie.getImage_one()).into( holder.ivProductImage);

        holder.tvProductName.setText(movie.getName());
        holder.tvShortDetails.setText(movie.getDetails());
        holder.tvProductPrice.setText("৳ "+movie.getPrice());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}