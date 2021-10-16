package com.bd.soc71bazaar.fragmentpage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.SingInPage;
import com.bd.soc71bazaar.adminpart.AdminDashBord;
import com.bd.soc71bazaar.httpclient.HttpClient;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddToCardFragments extends BottomSheetDialogFragment {

    MaterialButton btn_AddTOCardContinew;
    TextView tv_price,tv_Product_name,tv_Total,tv_tolet_name;
    EditText tv_Quantity;
    String strQuantity="",strId="",strUserId="",strName="",strPrice="";
    int Quantity=0,Price=0,TotalPrice=0,TotalCard=0;
    String str_Email = "", str_Password = "", result = "", status_code = "", msg = "", values = "", str_tolet_type_id = "";
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_to_card, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("SOC_71_Bazaar", Context.MODE_PRIVATE);
        TotalCard = sharedPreferences.getInt("Cardinfo",0);
        strUserId = sharedPreferences.getString("user_id","");

        tv_price= v.findViewById(R.id.tv_price);
        tv_Product_name= v.findViewById(R.id.tv_Product_name);
        tv_tolet_name= v.findViewById(R.id.tv_tolet_name);
        tv_Total= v.findViewById(R.id.tv_Total);
        btn_AddTOCardContinew= v.findViewById(R.id.btn_AddTOCardContinew);
        tv_Quantity= v.findViewById(R.id.tv_Quantity);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            strId=bundle.getString("ID");
            strName=bundle.getString("Name");
            strPrice=bundle.getString("Price");

            tv_tolet_name.setText("Add To Card ( "+strName+" )");
            tv_Product_name.setText(strName);
            tv_price.setText(strPrice);
            tv_Total.setText(strPrice);
        }

        btn_AddTOCardContinew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strQuantity= tv_Quantity.getText().toString().trim();
                if (!strUserId.equals("")){
                    AddTOCard addTOCard=new AddTOCard();
                    addTOCard.execute();
                }else{
                    startActivity(new Intent(getActivity(),SingInPage.class));
                }
            }
        });
        tv_Quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strQuantity= tv_Quantity.getText().toString().trim();
                if (strQuantity.equals("")){
                    Toast.makeText(getActivity(),"Quantity Can't be Empty",Toast.LENGTH_LONG).show();
                    tv_Quantity.setText("1");
                    Quantity=0;
                }else {
                    Quantity = Integer.valueOf(strQuantity);
                    if (Quantity>=1) {
                        Price = Integer.valueOf(strPrice);
                        try {
                            TotalPrice = Quantity * Price;
                        }catch (Exception e){
                            Log.d("Error Math",e.toString());
                        }
                        tv_Total.setText(String.valueOf(TotalPrice));
                    }else {
                        Toast.makeText(getActivity(),"Quantity Can't be 0",Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;

    }

    private class AddTOCard extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(getActivity(), "Verifying Information", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            List<Pair<String, String>> postParameters = new ArrayList<>();
            postParameters.add(new Pair("Type", "Add Markets"));
            postParameters.add(new Pair("products_id", strId));
            postParameters.add(new Pair("admins_id", strUserId));
            postParameters.add(new Pair("markets_status", "P"));
            postParameters.add(new Pair("quantity", strQuantity));

            try {
                String response = HttpClient.execute(AdminDashBord.strRootUrl + "DataProsess.php", postParameters);
                result = response.toString();
                result = result.replaceAll("\n", "");

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!! >>> " + e.toString());
            }

            try {
                JSONObject jsonObject = new JSONObject(result);
                status_code = jsonObject.getString("status_code");
                msg = jsonObject.getString("msg");
                values = jsonObject.getString("values");



            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }

            return status_code;

        }

        @Override
        protected void onPostExecute(String status_code) {
            if (pd != null) {
                pd.dismiss();
            }
            if (status_code.equals("200")) {
                Toast.makeText(getActivity(),strName+" Add To Card Successfully",Toast.LENGTH_LONG).show();
                dismiss();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                TotalCard= TotalCard+1;
                editor.putInt("Cardinfo",TotalCard);
                editor.commit();
            } else {
                Toast.makeText(getActivity(),msg+", "+values,Toast.LENGTH_LONG).show();
            }

        }

    }

}
