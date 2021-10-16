package com.bd.soc71bazaar.fragmentpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bd.soc71bazaar.AllViewPage;
import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.adapter.CategoriesAdapter;
import com.bd.soc71bazaar.adapter.FeaturedProductsAdapter;
import com.bd.soc71bazaar.adapter.ImageAdapter;
import com.bd.soc71bazaar.adapter.TopRatedProductsAdapter;
import com.bd.soc71bazaar.httpclient.HttpClient;
import com.bd.soc71bazaar.model.admin.CategoriesModel;
import com.bd.soc71bazaar.model.FeaturedProductsModel;
import com.bd.soc71bazaar.model.TopRatedModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.bd.soc71bazaar.adminpart.AdminDashBord.strRootUrl;


public class HomeFragment extends Fragment {

    private ArrayList<CategoriesModel> categoriesModels = new ArrayList<>();
    private ArrayList<FeaturedProductsModel> featuredProductsModels = new ArrayList<>();
    private ArrayList<TopRatedModel> topRatedModels = new ArrayList<>();

    private RecyclerView recyclerviewCategories, recyclerviewFeaturedProducts, recyclerviewTopProduct;

    private CategoriesAdapter categoriesAdapter;
    private FeaturedProductsAdapter featuredProductsAdapter;
    private TopRatedProductsAdapter topProductAdapter;

    boolean isLoading = false;

    String strStatusCode = "";
    private String[] imageUrls = new String[10];
    ViewPager mViewPager;
    ImageAdapter adapterView;

    TextView tvCategories,tvBrands,tvProducts,tvSeeMore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Home");


        mViewPager = (ViewPager) view.findViewById(R.id.viewPage);


        recyclerviewCategories = (RecyclerView) view.findViewById(R.id.recyclerviewCategories);
        recyclerviewFeaturedProducts = (RecyclerView) view.findViewById(R.id.recyclerviewFeaturedProducts);
        recyclerviewTopProduct = (RecyclerView) view.findViewById(R.id.recyclerviewTopProduct);
        tvCategories =  view.findViewById(R.id.tvCategories);
        tvBrands =  view.findViewById(R.id.tvBrands);
        tvProducts =  view.findViewById(R.id.tvProducts);
        tvSeeMore =  view.findViewById(R.id.tvSeeMore);

        tvCategories.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent= new Intent(getActivity(), AllViewPage.class);
                intent.putExtra("ViewType","Categories");
                startActivity(intent);
                return false;
            }
        });

        tvBrands.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent= new Intent(getActivity(), AllViewPage.class);
                intent.putExtra("ViewType","Brands");
                startActivity(intent);
                return false;
            }
        });

        tvProducts.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent= new Intent(getActivity(), AllViewPage.class);
                intent.putExtra("ViewType","Products");
                startActivity(intent);
                return false;
            }
        });

        tvSeeMore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent= new Intent(getActivity(), AllViewPage.class);
                intent.putExtra("ViewType","Products");
                startActivity(intent);
                return false;
            }
        });

        DashBordListData dashBordListData= new DashBordListData();
        dashBordListData.execute();

        return view;
    }


    private class DashBordListData extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(getActivity(), "Getting Data Data", "Please wait...");
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
            String Brand="",Categories="",SliderImage="",Products="";
            try {
                JSONObject jsonObject= new JSONObject(result);
                Brand=jsonObject.getString("Brand");
                Categories=jsonObject.getString("Categories");
                SliderImage=jsonObject.getString("SliderImage");
                Products=jsonObject.getString("Products");

                JSONArray jsonArray1 = new JSONArray(Brand);
                for (int i = 0; i < jsonArray1.length(); i++) {
                    JSONObject jsonObjectBrand = jsonArray1.getJSONObject(i);
                    FeaturedProductsModel adminModel = new FeaturedProductsModel(
                            jsonObjectBrand.getString("brands_id"),
                            jsonObjectBrand.getString("brands_name"),
                            jsonObjectBrand.getString("brands_name")
                    );
                    featuredProductsModels.add(adminModel);
                }

                try {
                    JSONArray jsonArray2 = new JSONArray(Categories);
                    for (int i = 0; i < jsonArray2.length(); i++) {
                        JSONObject jsonObjectCategories = jsonArray2.getJSONObject(i);
                        CategoriesModel adminModel = new CategoriesModel(
                                jsonObjectCategories.getString("categories_id"),
                                jsonObjectCategories.getString("categories_name"),
                                jsonObjectCategories.getString("categories_status"),
                                jsonObjectCategories.getString("create_info"),
                                jsonObjectCategories.getString("update_info")
                        );
                        categoriesModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

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

                try {
                    JSONArray jsonArray4 = new JSONArray(SliderImage);
                    for (int i = 0; i < jsonArray4.length(); i++) {
                        JSONObject jsonObjectSliderImage = jsonArray4.getJSONObject(i);
                        imageUrls[i]=jsonObjectSliderImage.getString("slider_image");

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
                categoriesAdapter = new CategoriesAdapter(categoriesModels,getActivity(),"HomePage");
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerviewCategories.setLayoutManager(mLayoutManager);
                recyclerviewCategories.setItemAnimator(new DefaultItemAnimator());
                recyclerviewCategories.setAdapter(categoriesAdapter);

                featuredProductsAdapter = new FeaturedProductsAdapter(featuredProductsModels,getActivity(),"HomePage");
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerviewFeaturedProducts.setLayoutManager(mLayoutManager1);
                recyclerviewFeaturedProducts.setItemAnimator(new DefaultItemAnimator());
                recyclerviewFeaturedProducts.setAdapter(featuredProductsAdapter);

                topProductAdapter = new TopRatedProductsAdapter(topRatedModels,getActivity());

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                recyclerviewTopProduct.setLayoutManager(gridLayoutManager);
                recyclerviewTopProduct.setItemAnimator(new DefaultItemAnimator());
                recyclerviewTopProduct.setAdapter(topProductAdapter);

                adapterView = new ImageAdapter(getActivity(),imageUrls);
                mViewPager.setAdapter(adapterView);
            } else {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG);
            }

        }
    }
}