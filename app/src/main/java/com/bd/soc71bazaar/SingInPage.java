package com.bd.soc71bazaar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bd.soc71bazaar.adminpart.AdminDashBord;
import com.bd.soc71bazaar.httpclient.HttpClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.bd.soc71bazaar.adminpart.AdminDashBord.strRootUrl;

public class SingInPage extends AppCompatActivity {
    EditText edtUserName, edtUserPassword;
    Button btnLogin, btnGoogleSignInId, btnPhoneSignInId;
    TextView txtLoginSignUpId;
    SignInButton signInButton;
    private final static int RC_SIGN_IN = 123;
    String str_Email = "", str_Password = "", result = "", status_code = "", msg = "", values = ""
            , str_send_type = "", strName = "",strImage = "", strPhone = "", strEmail = "",
            strPassword = "", logintype = "";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    public static String url = "http://103.91.54.60/apps/SOC_ToletService/";
    SharedPreferences sharedPreferences;
    ProgressDialog pd2 = null;
    boolean googleLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_in_page);
        sharedPreferences = getSharedPreferences("SOC_71_Bazaar", MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();
        edtUserName = findViewById(R.id.edtUserName);
        edtUserPassword = findViewById(R.id.edtUserPassword);

        btnLogin = findViewById(R.id.btnLogin);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_Email = edtUserName.getText().toString();
                str_Password = edtUserPassword.getText().toString();

                if (!str_Email.equals("")) {
                    if (!str_Password.equals("")) {
                        str_send_type="SingIn";
                        Login login = new Login();
                        login.execute();
                    } else {
                        Toast.makeText(getApplication(), "Enter Valid Info", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplication(), "Enter Valid Info", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLogin = true;
                signIn();
            }
        });

    }

    private class Login extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(SingInPage.this, "Verifying Information", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", "User LogIn"));
                if (googleLogin){
                    postParameters.add(new Pair("USER_ID", strEmail));
                    postParameters.add(new Pair("USER_PASSWORD", strPassword));
                }else {
                    postParameters.add(new Pair("USER_ID", str_Email));
                    postParameters.add(new Pair("USER_PASSWORD", str_Password));
                }
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
                    JSONObject jsonObject1 = new JSONObject(values);
                    if (status_code.equals("200")) {
                        if (jsonObject1.getString("user_type_name").equals("Users")) {
                            logintype="User";
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user_id", jsonObject1.getString("admins_id"));
                            editor.putString("user_type_name", jsonObject1.getString("user_type_name"));
                            editor.putString("user_type_id", jsonObject1.getString("user_type_id"));
                            editor.putString("name", jsonObject1.getString("name"));
                            editor.putString("username", jsonObject1.getString("username"));
                            editor.putString("image", jsonObject1.getString("image"));
                            editor.putString("phone", jsonObject1.getString("phone"));
                            editor.putString("email", jsonObject1.getString("email"));
                            editor.putString("password", jsonObject1.getString("password"));
                            editor.commit();
                        } else {
                            logintype="Admin";
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("admins_id", jsonObject1.getString("admins_id"));
                            editor.putString("user_type_name", jsonObject1.getString("user_type_name"));
                            editor.putString("user_type_id", jsonObject1.getString("user_type_id"));
                            editor.putString("name", jsonObject1.getString("name"));
                            editor.putString("username", jsonObject1.getString("username"));
                            editor.putString("image", jsonObject1.getString("image"));
                            editor.putString("phone", jsonObject1.getString("phone"));
                            editor.putString("email", jsonObject1.getString("email"));
                            editor.putString("password", jsonObject1.getString("password"));
                            editor.commit();
                        }


                    }

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
                if (logintype.equals("Admin")){
                    successAlert(msg,msg);
                }else{
                    startActivity(new Intent(SingInPage.this,MainActivity.class));
                }
            } else if (status_code.equals("")) {
                errorAlert("Failed", "Connection Failed !!");
            } else {
                errorAlert(msg, values);
            }

        }

    }

    private class SingUp extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(SingInPage.this, "Verifying Information", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", "Add Admins"));
                postParameters.add(new Pair("image", "No Image"));
                postParameters.add(new Pair("user_type_id", "29"));
                postParameters.add(new Pair("name", strName));
                postParameters.add(new Pair("phone", "0000"));
                postParameters.add(new Pair("email", strEmail));
                postParameters.add(new Pair("password", strPassword));
                postParameters.add(new Pair("admins_status", "A"));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {

                    JSONObject obj = new JSONObject(result);
                    status_code = obj.getString("status_code");
                    msg = obj.getString("msg");
                    values = obj.getString("values");


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
                Login login= new Login();
                login.execute();
            } else if (status_code.equals("")) {
                errorAlert("Failed", "Connection Failed !!");
            } else {
                errorAlert(msg, values);
            }

        }

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String token) {
        AuthCredential credential = GoogleAuthProvider.getCredential(token, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            pd2 = ProgressDialog.show(SingInPage.this, "Google Id Verifying Success", "Please wait few Second ..\nYour Data Is Processing");

                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            strEmail=user.getEmail();
                            strName=user.getDisplayName();
                            strImage=user.getPhotoUrl().toString();
                            if (pd2 != null) {
                                pd2.dismiss();
                            }
                            //strImage = getByteArrayFromImageURL(strImage);
                            strPassword = getSaltString(strName);
                            SingUp singUp= new SingUp();
                            singUp.execute();
                        } else {
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SingInPage.this, "signInWithCredential:failure==" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    protected String getSaltString(String Name) {
        String SALTCHARS = Name;
        SALTCHARS = SALTCHARS+"1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    private void successAlert(String msg, String values) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(msg);
        dialog.setMessage(values).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent i = new Intent(SingInPage.this, AdminDashBord.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        dialog.show();
    }

    private void errorAlert(String msg, String values) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(msg);
        dialog.setMessage(values).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }

}