package com.bd.soc71bazaar.fragmentpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.SingInPage;

public class AccountFragment extends Fragment {
    Button btnSingIn;
    SharedPreferences sharedPreferences;
    String strUserId="";
    LinearLayout lvSingok,lvSingIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("SOC_71_Bazaar", Context.MODE_PRIVATE);
        strUserId = sharedPreferences.getString("user_id","");
        getActivity().setTitle( "My Account");

        btnSingIn = view.findViewById(R.id.btnSingIn);
        lvSingok=view.findViewById(R.id.lvSingok);
        lvSingIn=view.findViewById(R.id.lvSingIn);

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SingInPage.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        if (strUserId.equals("")){
            lvSingIn.setVisibility(View.VISIBLE);
            lvSingok.setVisibility(View.GONE);
        }else {
            lvSingIn.setVisibility(View.GONE);
            lvSingok.setVisibility(View.VISIBLE);
        }


        return view;
    }



}