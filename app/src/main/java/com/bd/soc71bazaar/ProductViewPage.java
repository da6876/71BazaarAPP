package com.bd.soc71bazaar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bd.soc71bazaar.adapter.TopRatedProductsAdapter;
import com.bd.soc71bazaar.httpclient.HttpClient;
import com.bd.soc71bazaar.model.TopRatedModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.bd.soc71bazaar.adminpart.AdminDashBord.strRootUrl;

public class ProductViewPage extends AppCompatActivity {
    TextView tvViewType;
    private RecyclerView recyclerviewProducts;
    String strViewType="",strViewName="",strViewId="";
    String strStatusCode = "";
    private ArrayList<TopRatedModel> topRatedModels = new ArrayList<>();
    private TopRatedProductsAdapter topProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view_page);

        tvViewType= findViewById(R.id.tvViewType);
        recyclerviewProducts= findViewById(R.id.recyclerviewProducts);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            strViewType=bundle.getString("ViewType");
            strViewName=bundle.getString("ViewName");
            strViewId=bundle.getString("ViewId");

            tvViewType.setText(strViewType +" ( "+strViewName+" ) ");
        }

        ProductListData productListData= new ProductListData();
        productListData.execute();
    }


    private class ProductListData extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(ProductViewPage.this, "Getting Product Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            List<Pair<String, String>> postParameters = new ArrayList<>();
            postParameters.add(new Pair("Type", "View Product By Type"));
            postParameters.add(new Pair("ViewType", strViewType));
            postParameters.add(new Pair("ViewName", strViewName));
            postParameters.add(new Pair("ViewId", strViewId));
            String Products = "";

            try {
                String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                Products = response.toString();
                Products = Products.replaceAll("\n", "");

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
                                jsonObjectProducts.getString("categories_name"),
                                jsonObjectProducts.getString("brands_id"),
                                jsonObjectProducts.getString("brands_name"),
                                jsonObjectProducts.getString("country_id"),
                                jsonObjectProducts.getString("country_name"),
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








            return strStatusCode;
        }

        @Override
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (!result.equals("null") || !result.equals(null) || !result.equals("")) {


                topProductAdapter = new TopRatedProductsAdapter(topRatedModels,ProductViewPage.this);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductViewPage.this, 2);
                recyclerviewProducts.setLayoutManager(gridLayoutManager);
                recyclerviewProducts.setItemAnimator(new DefaultItemAnimator());
                recyclerviewProducts.setAdapter(topProductAdapter);

            } else {
                Toast.makeText(ProductViewPage.this, "Error", Toast.LENGTH_LONG);
            }

        }
    }
}