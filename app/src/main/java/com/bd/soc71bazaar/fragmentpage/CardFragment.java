package com.bd.soc71bazaar.fragmentpage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.SingInPage;
import com.bd.soc71bazaar.adapter.CategoriesAdapter;
import com.bd.soc71bazaar.adapter.TopRatedProductsAdapter;
import com.bd.soc71bazaar.adapter.ViewCardProductsAdapter;
import com.bd.soc71bazaar.httpclient.HttpClient;
import com.bd.soc71bazaar.model.CardProductsModel;
import com.bd.soc71bazaar.model.TopRatedModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.bd.soc71bazaar.adminpart.AdminDashBord.strRootUrl;

public class CardFragment extends Fragment {

    Button btnSingIn;
    String strUserId="",strStatusCode="";
    LinearLayout lvSingok,lvSingIn;
    private RecyclerView recyclerviewCardsProducts;
    SharedPreferences sharedPreferences;

    private ViewCardProductsAdapter viewCardProductsAdapter;
    private ArrayList<CardProductsModel> cardProductsModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_card, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("SOC_71_Bazaar", Context.MODE_PRIVATE);
        strUserId = sharedPreferences.getString("user_id","");
        getActivity().setTitle( "My Card");


        lvSingok=view.findViewById(R.id.lvSingok);
        lvSingIn=view.findViewById(R.id.lvSingIn);

        recyclerviewCardsProducts=view.findViewById(R.id.recyclerviewCardsProducts);

        if (strUserId.equals("")){
            lvSingIn.setVisibility(View.VISIBLE);
            lvSingok.setVisibility(View.GONE);
        }else {
            lvSingIn.setVisibility(View.GONE);
            lvSingok.setVisibility(View.VISIBLE);
            DashBordListData dashBordListData=new DashBordListData();
            dashBordListData.execute();
        }

        btnSingIn = view.findViewById(R.id.btnSingIn);

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingInPage.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });


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
            postParameters.add(new Pair("Type", "View User Card Info"));
            postParameters.add(new Pair("admins_id", strUserId));
            String result = "";

            try {
                String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                result = response.toString();
                result = result.replaceAll("\n", "");

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }

                try {
                    JSONArray jsonArray3 = new JSONArray(result);
                    for (int i = 0; i < jsonArray3.length(); i++) {
                        JSONObject jsonObjectProducts = jsonArray3.getJSONObject(i);
                        CardProductsModel adminModel = new CardProductsModel(
                                jsonObjectProducts.getString("markets_id"),
                                jsonObjectProducts.getString("products_id"),
                                jsonObjectProducts.getString("admins_id"),
                                jsonObjectProducts.getString("quantity"),
                                jsonObjectProducts.getString("markets_status"),
                                jsonObjectProducts.getString("product_types_id"),
                                jsonObjectProducts.getString("category_id"),
                                jsonObjectProducts.getString("brands_id"),
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
                                jsonObjectProducts.getString("sort_order"),
                                jsonObjectProducts.getString("products_status")
                        );
                        cardProductsModels.add(adminModel);
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
                viewCardProductsAdapter = new ViewCardProductsAdapter(cardProductsModels,getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerviewCardsProducts.setLayoutManager(mLayoutManager);
                recyclerviewCardsProducts.setItemAnimator(new DefaultItemAnimator());
                recyclerviewCardsProducts.setAdapter(viewCardProductsAdapter);

            } else {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG);
            }

        }
    }
}