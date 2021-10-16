package com.bd.soc71bazaar.adminpart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.adapter.adminadapters.AdminUserDataViewAdapter;
import com.bd.soc71bazaar.adapter.adminadapters.BrandsDataViewAdapter;
import com.bd.soc71bazaar.adapter.adminadapters.CategoriesDataViewAdapter;
import com.bd.soc71bazaar.adapter.adminadapters.CountryDataViewAdapter;
import com.bd.soc71bazaar.adapter.adminadapters.MarketDataViewAdapter;
import com.bd.soc71bazaar.adapter.adminadapters.PasswordResetDataViewAdapter;
import com.bd.soc71bazaar.adapter.adminadapters.ProductTypetDataViewAdapter;
import com.bd.soc71bazaar.adapter.adminadapters.ProducttDataViewAdapter;
import com.bd.soc71bazaar.adapter.adminadapters.UserTypetDataViewAdapter;
import com.bd.soc71bazaar.httpclient.HttpClient;
import com.bd.soc71bazaar.model.admin.AdminModel;
import com.bd.soc71bazaar.model.admin.BrandsModel;
import com.bd.soc71bazaar.model.admin.CategoriesModel;
import com.bd.soc71bazaar.model.admin.CountryModel;
import com.bd.soc71bazaar.model.admin.MarketsModel;
import com.bd.soc71bazaar.model.admin.PasswordResetsModel;
import com.bd.soc71bazaar.model.admin.ProductModel;
import com.bd.soc71bazaar.model.admin.ProductTypesModel;
import com.bd.soc71bazaar.model.admin.SubCategoriesModel;
import com.bd.soc71bazaar.model.admin.UserTypesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.bd.soc71bazaar.adminpart.AdminDashBord.strRootUrl;

public class DashBordProscessData extends AppCompatActivity {
    String strType= "",strOpenType= "",strViewType= "",strStatusCode="";

    ListView ivDashBordData;
    EditText searchViewAdminHome;
    boolean show = false;

    ArrayList<AdminModel> adminModels = new ArrayList<>();
    AdminUserDataViewAdapter adminUserDataViewAdapter;

    ArrayList<BrandsModel> brandsModels = new ArrayList<>();
    BrandsDataViewAdapter brandsDataViewAdapter;

    ArrayList<CategoriesModel> categoriesModels = new ArrayList<>();
    CategoriesDataViewAdapter categoriesDataViewAdapter;

    ArrayList<SubCategoriesModel> subCategoriesModels = new ArrayList<>();
//    CategoriesDataViewAdapter categoriesDataViewAdapter;

    ArrayList<CountryModel> countryModels = new ArrayList<>();
    CountryDataViewAdapter countryDataViewAdapter;

    ArrayList<MarketsModel> marketsModels = new ArrayList<>();
    MarketDataViewAdapter marketDataViewAdapter;

    ArrayList<PasswordResetsModel> passwordResetsModels = new ArrayList<>();
    PasswordResetDataViewAdapter passwordResetDataViewAdapter;

    ArrayList<ProductTypesModel> productTypesModels = new ArrayList<>();
    ProductTypetDataViewAdapter productTypetDataViewAdapter;

    ArrayList<UserTypesModel> userTypesModels = new ArrayList<>();
    UserTypetDataViewAdapter userTypetDataViewAdapter;

    ArrayList<ProductModel> productModels = new ArrayList<>();
    ProducttDataViewAdapter producttDataViewAdapter;


    SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_bord_proscess_data);

        ivDashBordData = findViewById(R.id.ivDashBordData);
        searchViewAdminHome = findViewById(R.id.searchViewAdminHome);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            strType=bundle.getString("Name");
            strViewType=bundle.getString("Name");
            setTitle(strType);
            DashBordListData dashBordListData = new DashBordListData();
            dashBordListData.execute();
        }
        searchViewAdminHome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = searchViewAdminHome.getText().toString().toLowerCase(Locale.getDefault());
                if (strType.equals("View Admins")) {
                    adminUserDataViewAdapter.getFilter().filter(text);
                    adminUserDataViewAdapter.clearFilter();
                }else if(strType.equals("View Brands")){
                    brandsDataViewAdapter.getFilter().filter(text);
                    brandsDataViewAdapter.clearFilter();
                }else if(strType.equals("View Categories")){
                    categoriesDataViewAdapter.getFilter().filter(text);
                    categoriesDataViewAdapter.clearFilter();
                }else if(strType.equals("View Sub Categories")){
                    categoriesDataViewAdapter.getFilter().filter(text);
                    categoriesDataViewAdapter.clearFilter();
                }else if(strType.equals("View Country")){
                    countryDataViewAdapter.getFilter().filter(text);
                    countryDataViewAdapter.clearFilter();
                }else if(strType.equals("View Markets")){
                    marketDataViewAdapter.getFilter().filter(text);
                    marketDataViewAdapter.clearFilter();
                }else if(strType.equals("View Password Resets")){
                    passwordResetDataViewAdapter.getFilter().filter(text);
                    passwordResetDataViewAdapter.clearFilter();
                }else if(strType.equals("View Product Types")){
                    productTypetDataViewAdapter.getFilter().filter(text);
                    productTypetDataViewAdapter.clearFilter();
                }else if(strType.equals("View User Type")){
                    userTypetDataViewAdapter.getFilter().filter(text);
                    userTypetDataViewAdapter.clearFilter();
                }else if(strType.equals("View Products")){
                    producttDataViewAdapter.getFilter().filter(text);
                    producttDataViewAdapter.clearFilter();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (strType.equals("View Admins")) {
                    adminModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View Brands")){
                    brandsModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View Categories")){
                    categoriesModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View Sub Categories")){
                    categoriesModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View Country")){
                    countryModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View Markets")){
                    marketsModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View Password Resets")){
                    passwordResetsModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View Product Types")){
                    productTypesModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View User Type")){
                    userTypesModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if(strType.equals("View Products")){
                    productModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }
            }
        });


        ivDashBordData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (strType.equals("View Admins")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",adminModels.get(position).getAdmins_id());
                    bundle.putString("Name",adminModels.get(position).getName());
                    bundle.putString("AdminStatus",adminModels.get(position).getAdmins_status());
                    bundle.putString("Email",adminModels.get(position).getEmail());
                    bundle.putString("Password",adminModels.get(position).getPassword());
                    bundle.putString("UserName",adminModels.get(position).getUsername());
                    bundle.putString("UserType",adminModels.get(position).getUser_type_id());
                    bundle.putString("Phone",adminModels.get(position).getPhone());
                    bundle.putString("Image",adminModels.get(position).getShop_id());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                }

                else if(strType.equals("View Brands")){
                    Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",brandsModels.get(position).getBrands_id());
                    bundle.putString("Name",brandsModels.get(position).getBrands_name());
                    bundle.putString("Status",brandsModels.get(position).getBrands_status());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                }

                else if(strType.equals("View Categories")){
                    Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",categoriesModels.get(position).getCategories_id());
                    bundle.putString("Name",categoriesModels.get(position).getCategories_name());
                    bundle.putString("Status",categoriesModels.get(position).getCategories_status());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                }

                else if(strType.equals("View Sub Categories")){
                    Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",categoriesModels.get(position).getCategories_id());
                    bundle.putString("Name",categoriesModels.get(position).getCategories_name());
                    bundle.putString("Status",categoriesModels.get(position).getCategories_status());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                }

                else if(strType.equals("View Country")){
                    Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",countryModels.get(position).getId());
                    bundle.putString("Name",countryModels.get(position).getName());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                }

                else if(strType.equals("View Markets")){
                    Toast.makeText(DashBordProscessData.this,marketsModels.get(position).getMarkets_id(),Toast.LENGTH_LONG).show();
                }

                else if(strType.equals("View Password Resets")){
                    Toast.makeText(DashBordProscessData.this,passwordResetsModels.get(position).getToken(),Toast.LENGTH_LONG).show();
                }

                else if(strType.equals("View Product Types")){
                    Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",productTypesModels.get(position).getProduct_types_id());
                    bundle.putString("Name",productTypesModels.get(position).getProduct_types_name());
                    bundle.putString("Status",productTypesModels.get(position).getProduct_types_status());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                }

                else if(strType.equals("View User Type")){
                    Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",userTypesModels.get(position).getUser_type_id());
                    bundle.putString("Name",userTypesModels.get(position).getUser_type_name());
                    bundle.putString("Status",userTypesModels.get(position).getUser_type_status());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                }

                else if(strType.equals("View Products")){
                    Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",productModels.get(position).getProducts_id());
                    bundle.putString("Name",productModels.get(position).getName());
                    bundle.putString("ProductsStatus",productModels.get(position).getProducts_status());
                    bundle.putString("BrandsId",productModels.get(position).getBrands_id());
                    bundle.putString("CategoryId",productModels.get(position).getCategory_id());
                    bundle.putString("CountryId",productModels.get(position).getCountry_id());
                    bundle.putString("ProductTypesId",productModels.get(position).getProduct_types_id());
                    bundle.putString("Discount",productModels.get(position).getDiscount());
                    bundle.putString("Details",productModels.get(position).getDetails());
                    bundle.putString("Price",productModels.get(position).getPrice());
                    bundle.putString("Quantity",productModels.get(position).getQuantity());
                    bundle.putString("DiscountPrice",productModels.get(position).getDiscount_price());
                    bundle.putString("SortOrder",productModels.get(position).getSort_order());
                    bundle.putString("ImageOne",productModels.get(position).getImage_one());
                    bundle.putString("ImageTwo",productModels.get(position).getImage_two());
                    bundle.putString("ImageThree",productModels.get(position).getImage_three());
                    bundle.putString("ImageFour",productModels.get(position).getImage_four());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());

                }
            }
        });
    }

    private class DashBordListData extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(DashBordProscessData.this, "Getting Data Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            List<Pair<String, String>> postParameters = new ArrayList<>();
            postParameters.add(new Pair("Type",strType));
            String result = "";

            try {
                String response = HttpClient.execute(strRootUrl+"DataProsess.php", postParameters);
                result = response.toString();
                result = result.replaceAll("\n", "");

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }

            if (strType.equals("View Admins")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        AdminModel adminModel=new AdminModel(
                                jsonObject.getString("admins_id"),
                                jsonObject.getString("image"),
                                jsonObject.getString("user_type_id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("username"),
                                jsonObject.getString("phone"),
                                jsonObject.getString("email"),
                                jsonObject.getString("email_verified_at"),
                                jsonObject.getString("password"),
                                jsonObject.getString("remember_token"),
                                jsonObject.getString("admins_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        adminModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View Brands")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        BrandsModel adminModel=new BrandsModel(
                                jsonObject.getString("brands_id"),
                                jsonObject.getString("brands_name"),
                                jsonObject.getString("brands_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        brandsModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View Categories")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        CategoriesModel adminModel=new CategoriesModel(
                                jsonObject.getString("categories_id"),
                                jsonObject.getString("categories_name"),
                                jsonObject.getString("categories_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        categoriesModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View Sub Categories")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        SubCategoriesModel subCategoriesModel=new SubCategoriesModel(
                                jsonObject.getString("sub_categories_id"),
                                jsonObject.getString("categories_id"),
                                jsonObject.getString("sub_categories_name"),
                                jsonObject.getString("sub_categories_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        subCategoriesModels.add(subCategoriesModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View Country")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        CountryModel adminModel=new CountryModel(
                                jsonObject.getString("id"),
                                jsonObject.getString("iso"),
                                jsonObject.getString("name"),
                                jsonObject.getString("nicename"),
                                jsonObject.getString("iso3"),
                                jsonObject.getString("numcode"),
                                jsonObject.getString("phonecode")
                        );
                        countryModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View Markets")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        MarketsModel adminModel=new MarketsModel(
                                jsonObject.getString("markets_id"),
                                jsonObject.getString("products_id"),
                                jsonObject.getString("admins_id"),
                                jsonObject.getString("markets_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        marketsModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View Password Resets")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        PasswordResetsModel adminModel=new PasswordResetsModel(
                                jsonObject.getString("email"),
                                jsonObject.getString("token"),
                                jsonObject.getString("password_resets_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        passwordResetsModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View Product Types")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ProductTypesModel adminModel=new ProductTypesModel(
                                jsonObject.getString("product_types_id"),
                                jsonObject.getString("product_types_name"),
                                jsonObject.getString("product_types_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        productTypesModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View User Type")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        UserTypesModel adminModel=new UserTypesModel(
                                jsonObject.getString("user_type_id"),
                                jsonObject.getString("user_type_name"),
                                jsonObject.getString("user_type_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        userTypesModels.add(adminModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if(strType.equals("View Products")){
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ProductModel productModel=new ProductModel(
                                jsonObject.getString("products_id"),
                                jsonObject.getString("product_types_id"),
                                jsonObject.getString("category_id"),
                                jsonObject.getString("brands_id"),
                                jsonObject.getString("country_id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("details"),
                                jsonObject.getString("image_one"),
                                jsonObject.getString("image_two"),
                                jsonObject.getString("image_three"),
                                jsonObject.getString("image_four"),
                                jsonObject.getString("price"),
                                jsonObject.getString("discount_price"),
                                jsonObject.getString("discount"),
                                jsonObject.getString("quantity"),
                                jsonObject.getString("sort_order"),
                                jsonObject.getString("products_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info")
                        );
                        productModels.add(productModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            return strStatusCode;
        }

        @Override
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (!result.equals("null")||!result.equals(null)||!result.equals("")){
                if (strType.equals("View Admins")) {
                    strOpenType = "Add Admins";
                    adminUserDataViewAdapter = new AdminUserDataViewAdapter(DashBordProscessData.this, adminModels, strType);
                    ivDashBordData.setAdapter(adminUserDataViewAdapter);
                }else if(strType.equals("View Brands")){
                    strOpenType = "Add Brands";
                    brandsDataViewAdapter = new BrandsDataViewAdapter(DashBordProscessData.this, brandsModels, strType);
                    ivDashBordData.setAdapter(brandsDataViewAdapter);
                }else if(strType.equals("View Categories")){
                    strOpenType = "Add Categories";
                    categoriesDataViewAdapter = new CategoriesDataViewAdapter(DashBordProscessData.this, categoriesModels, strType);
                    ivDashBordData.setAdapter(categoriesDataViewAdapter);
                }else if(strType.equals("View Country")){
                    strOpenType = "Add Country";
                    countryDataViewAdapter = new CountryDataViewAdapter(DashBordProscessData.this, countryModels, strType);
                    ivDashBordData.setAdapter(countryDataViewAdapter);
                }else if(strType.equals("View Markets")){
                    strOpenType = "";
                    marketDataViewAdapter  = new MarketDataViewAdapter(DashBordProscessData.this, marketsModels, strType);
                    ivDashBordData.setAdapter(marketDataViewAdapter);
                }else if(strType.equals("View Password Resets")){
                    strOpenType = "";
                    passwordResetDataViewAdapter = new PasswordResetDataViewAdapter(DashBordProscessData.this, passwordResetsModels, strType);
                    ivDashBordData.setAdapter(passwordResetDataViewAdapter);
                }else if(strType.equals("View Product Types")){
                    strOpenType = "Add Product Types";
                    productTypetDataViewAdapter = new ProductTypetDataViewAdapter(DashBordProscessData.this, productTypesModels, strType);
                    ivDashBordData.setAdapter(productTypetDataViewAdapter);
                }else if(strType.equals("View User Type")){
                    strOpenType = "Add User Type";
                    userTypetDataViewAdapter  = new UserTypetDataViewAdapter(DashBordProscessData.this, userTypesModels, strType);
                    ivDashBordData.setAdapter(userTypetDataViewAdapter);
                }else if(strType.equals("View Products")){
                    strOpenType = "Add Products";
                    producttDataViewAdapter = new ProducttDataViewAdapter(DashBordProscessData.this, productModels, strType);
                    ivDashBordData.setAdapter(producttDataViewAdapter);
                }
            }else {
                Toast.makeText(DashBordProscessData.this,"Error",Toast.LENGTH_LONG);
            }

        }
    }
/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DashBordProscessData.this,AdminDashBord.class));
    }*/

    public void onResume() {
        super.onResume();
        if (strType.equals("View Admins")) {
             adminModels.clear();
            setTitle(strType);
        }else if(strType.equals("View Brands")){
            setTitle(strType);
            brandsModels.clear();
        }else if(strType.equals("View Categories")){
            setTitle(strType);
            categoriesModels.clear();
        }else if(strType.equals("View Country")){
            setTitle(strType);
            countryModels.clear();
        }else if(strType.equals("View Markets")){
            setTitle(strType);
            marketsModels.clear();
        }else if(strType.equals("View Password Resets")){
            setTitle(strType);
            passwordResetsModels.clear();
        }else if(strType.equals("View User Type")){
            setTitle(strType);
            userTypesModels.clear();
        }else if(strType.equals("View Products")){
            setTitle(strType);
            productModels.clear();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_bord_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_Add:
                Bundle bundle = new Bundle();
                bundle.putString("OpenType",strOpenType);
                bundle.putString("ViewType",strViewType);
                DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                toletDetailsFragment.setArguments(bundle);
                toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                return true;
            case R.id.menu_Search:
                if (show != true) {
                    searchViewAdminHome.setVisibility(View.VISIBLE);
                    show = true;
                }else {
                    searchViewAdminHome.setVisibility(View.GONE);
                    show = false;
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
