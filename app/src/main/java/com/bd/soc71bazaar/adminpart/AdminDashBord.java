package com.bd.soc71bazaar.adminpart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bd.soc71bazaar.model.admin.AdminDashbordDataModel;
import com.bd.soc71bazaar.adapter.adminadapters.AdminDashbordDataAdapter;
import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.httpclient.HttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminDashBord extends AppCompatActivity {
    ListView listView;
    public static String strRootUrl="http://103.91.54.60/apps/SOC_71Bazaar/";
    public static String strImgRootUrl="http://103.91.54.60/SOCBazar/public/";
    ArrayList<AdminDashbordDataModel> listtofcustomer;
    String strType= "Dash Bord Menu",strUserType= "",strStatusCode="";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sharedPreferences = getSharedPreferences("SOC_71_Bazaar", MODE_PRIVATE);
        strUserType=sharedPreferences.getString("user_type_name","");
        listView =  (ListView) findViewById(R.id.list_item);

        listtofcustomer = new ArrayList<>();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminDashBord.this, DashBordProscessData.class);
                intent.putExtra("Name",listtofcustomer.get(position).getStatus());
                startActivity(intent);
            }
        });

        DashBordListData dashBordListData =new DashBordListData();
        dashBordListData.execute();
    }

    private class DashBordListData extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(AdminDashBord.this, "Getting Dash Bord Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            List<Pair<String, String>> postParameters = new ArrayList<>();
            postParameters.add(new Pair("Type",strType));
            postParameters.add(new Pair("UserType",strUserType));
            String result = "";
            try {
                String response = HttpClient.execute(strRootUrl+"DataProsess.php", postParameters);
                result = response.toString();
                result = result.replaceAll("\n", "");

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }

            try {
                JSONArray jsonArray=new JSONArray(result);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    AdminDashbordDataModel completeRequisitionList=new AdminDashbordDataModel(
                            jsonObject.getString("Name"),
                            jsonObject.getString("Type"),
                            jsonObject.getString("ICON")
                    );
                    listtofcustomer.add(completeRequisitionList);
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
            if (!result.equals("null")||!result.equals(null)||!result.equals("")){
                /*AdminDashBordAdapter categoriesAdapter = new AdminDashBordAdapter(AdminDashBord.this,dashbordModels);
                listView.setAdapter(categoriesAdapter);*/
                AdminDashbordDataAdapter adapter = new AdminDashbordDataAdapter(getApplicationContext(), R.layout.admin_dashbord_data_adapte, listtofcustomer);
                listView.setAdapter(adapter);

            }else {
                Toast.makeText(AdminDashBord.this,"Error",Toast.LENGTH_LONG);
            }

        }
    }
}
