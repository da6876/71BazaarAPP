package com.bd.soc71bazaar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bd.soc71bazaar.adapter.ImageAdapter;
import com.bd.soc71bazaar.adapter.TopRatedProductsAdapter;
import com.bd.soc71bazaar.adminpart.AdminDashBord;
import com.bd.soc71bazaar.fragmentpage.AddToCardFragments;
import com.bd.soc71bazaar.httpclient.HttpClient;
import com.bd.soc71bazaar.model.TopRatedModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.bd.soc71bazaar.adminpart.AdminDashBord.strRootUrl;

public class ProductViewSingle extends AppCompatActivity {
    TextView tv_Product_name,tv_Product_namesT,tv_tolet_details,tv_ProductType,tv_ProductBrand,tv_ProductCategore,tv_price,tv_size,tv_Discountprice;
    RecyclerView recyclerviewRelatedProduct,recyclerviewProductReview;
    LinearLayout tvAddToCard,tvBuynow;
    ViewPager viewPageProductImage;
    private String[] imageUrls = new String[10];
    ImageAdapter adapterView;
    String strStatusCode="";
    TextView tvBuynowT,tvProductReview;

    String strId="",strName="",strPrice="";

    private TopRatedProductsAdapter topProductAdapter;
    private ArrayList<TopRatedModel> topRatedModels = new ArrayList<>();
    private ArrayList<TopRatedModel> ReviewModel = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view_single);

        tv_Product_name = findViewById(R.id.tv_Product_name);
        tv_Product_namesT = findViewById(R.id.tv_Product_namesT);
        tv_tolet_details = findViewById(R.id.tv_tolet_details);
        tv_ProductType = findViewById(R.id.tv_ProductType);
        tv_ProductCategore = findViewById(R.id.tv_ProductCategore);
        tv_price = findViewById(R.id.tv_price);
        tv_size = findViewById(R.id.tv_size);
        tv_Discountprice = findViewById(R.id.tv_Discountprice);
        tv_ProductBrand = findViewById(R.id.tv_ProductBrand);
        recyclerviewRelatedProduct = findViewById(R.id.recyclerviewRelatedProduct);
        recyclerviewProductReview = findViewById(R.id.recyclerviewProductReview);
        tvAddToCard = findViewById(R.id.tvAddToCard);
        tvBuynow = findViewById(R.id.tvBuynow);
        tvBuynowT = findViewById(R.id.tvBuynowT);
        tvProductReview = findViewById(R.id.tvProductReview);

        viewPageProductImage = findViewById(R.id.viewPageProductImage);

        Bundle bundle= getIntent().getExtras();
        if (bundle!=null){
            strId=bundle.getString("ID");
            strName=bundle.getString("Name");
            strPrice=bundle.getString("Price");
            tv_Product_name.setText( bundle.getString("Name"));
            tv_Product_namesT.setText( bundle.getString("Name"));
            tv_tolet_details.setText(bundle.getString("Details"));
            tv_price.setText("৳ "+bundle.getString("Price"));
            tv_size.setText(bundle.getString("Sort_order"));
            tv_Discountprice.setText("৳ "+bundle.getString("Discount"));
            tv_ProductCategore.setText(bundle.getString("Categories_name"));
            tv_ProductType.setText(bundle.getString("Product_types_name"));
            tv_ProductBrand.setText(bundle.getString("Brands_name"));
            tvBuynowT.setText("Buy Now \nTk "+bundle.getString("Price"));
            imageUrls[0] = AdminDashBord.strImgRootUrl+bundle.getString("Image_one");
            imageUrls[1] = AdminDashBord.strImgRootUrl+bundle.getString("Image_two");
            imageUrls[2] = AdminDashBord.strImgRootUrl+bundle.getString("Image_three");
            imageUrls[3] = AdminDashBord.strImgRootUrl+bundle.getString("Image_four");
            adapterView = new ImageAdapter(ProductViewSingle.this,imageUrls);
            viewPageProductImage.setAdapter(adapterView);
        }


        tvAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("OpenType", "");
                bundle.putString("ID", strId);
                bundle.putString("Name", strName);
                bundle.putString("Price", strPrice);
                AddToCardFragments toletDetailsFragment = new AddToCardFragments();
                toletDetailsFragment.setArguments(bundle);
                toletDetailsFragment.show(ProductViewSingle.this.getSupportFragmentManager(), toletDetailsFragment.getTag());
            }
        });
        tvBuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("OpenType", "");
                bundle.putString("ID", strId);
                bundle.putString("Name", strName);
                bundle.putString("Price", strPrice);
                AddToCardFragments toletDetailsFragment = new AddToCardFragments();
                toletDetailsFragment.setArguments(bundle);
                toletDetailsFragment.show(ProductViewSingle.this.getSupportFragmentManager(), toletDetailsFragment.getTag());
            }
        });

        DashBordListData dashBordListData= new DashBordListData();
        dashBordListData.execute();
    }


    private class DashBordListData extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            //pd = ProgressDialog.show(ProductViewSingle.this, "Getting Data Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            List<Pair<String, String>> postParameters = new ArrayList<>();
            postParameters.add(new Pair("Type", "View Home Page Data"));
            String result = "";

            try {
                String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                result = response.toString();
                result = result.replaceAll("\n", "");

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }
            String Products="";
            try {
                JSONObject jsonObject= new JSONObject(result);
                Products=jsonObject.getString("Products");



                try {
                    JSONArray jsonArray3 = new JSONArray(Products);
                    for (int i = 0; i < jsonArray3.length(); i++) {
                        JSONObject jsonObjectProducts = jsonArray3.getJSONObject(i);
                        TopRatedModel adminModel = new TopRatedModel(
                                jsonObjectProducts.getString("products_id"),
                                jsonObjectProducts.getString("product_types_id"),
                                jsonObjectProducts.getString("product_types_name"),
                                jsonObjectProducts.getString("category_id"),
                                jsonObjectProducts.getString("country_name"),
                                jsonObjectProducts.getString("categories_name"),
                                jsonObjectProducts.getString("brands_id"),
                                jsonObjectProducts.getString("brands_name"),
                                jsonObjectProducts.getString("country_id"),
                                jsonObjectProducts.getString("name"),
                                jsonObjectProducts.getString("details"),
                                jsonObjectProducts.getString("image_one"),
                                jsonObjectProducts.getString("image_two"),
                                jsonObjectProducts.getString("image_three"),
                                jsonObjectProducts.getString("image_four"),
                                jsonObjectProducts.getString("price"),
                                jsonObjectProducts.getString("discount_price"),
                                jsonObjectProducts.getString("discount"),
                                jsonObjectProducts.getString("quantity"),
                                jsonObjectProducts.getString("sort_order"),
                                jsonObjectProducts.getString("products_status")
                        );
                        topRatedModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }


            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }




            return strStatusCode;
        }

        @Override
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (!result.equals("null") || !result.equals(null) || !result.equals("")) {
                topProductAdapter = new TopRatedProductsAdapter(topRatedModels,ProductViewSingle.this);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductViewSingle.this, 2);
                recyclerviewRelatedProduct.setLayoutManager(gridLayoutManager);
                recyclerviewRelatedProduct.setItemAnimator(new DefaultItemAnimator());
                recyclerviewRelatedProduct.setAdapter(topProductAdapter);

                if (ReviewModel.size()>0){
                    tvProductReview.setVisibility(View.VISIBLE);
                }else{

                }

            } else {
                Toast.makeText(ProductViewSingle.this, "Error", Toast.LENGTH_LONG);
            }

        }
    }
}