package com.bd.soc71bazaar.adminpart;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Pair;

import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.httpclient.HttpClient;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.bd.soc71bazaar.adminpart.AdminDashBord.strRootUrl;


public class DashBordDataAddFragment extends BottomSheetDialogFragment {

    String strViewType = "",strViewType1 = "", status_code = "", msg = "", values = "", result = "";

    TextView tv_name;
    EditText edt_User_Type_Name, edt_User_Type_Name_E, edt_Product_Types_Name, edt_Product_Types_Name_E,
            edt_Categories_Name, edt_Categories_Name_E, edt_Brand_Name, edt_Brand_Name_E,
            edt_Name, edt_Phone, edt_Email, edt_Password, edit_Image,
            edt_Name_E, edt_Phone_E, edt_Email_E, edt_Password_E,
            edt_Pro_Name, edt_Details, edt_Price, edt_discount, edt_quantity,
            edt_discount_price, edt_sort_order, edit_Image1, edit_Image2, edit_Image3, edit_Image4,
            edt_Pro_Name_E, edt_Details_E, edt_Price_E, edt_discount_E, edt_quantity_E,
            edt_discount_price_E, edt_sort_order_E;
    Button btn_Add, btn_add_Image, btn_add_Image_E, btn_add_Image1, btn_add_Image2, btn_add_Image3, btn_add_Image4,
            btn_chn_Image1, btn_chn_Image2, btn_chn_Image3, btn_chn_Image4;
    Spinner spinner_Status, spinner_UserType, spinner_UserType_E,
            spinner_ProductType, spinner_Brand, spinner_Category, spinner_Country,
            spinner_ProductType_E, spinner_Brand_E, spinner_Category_E, spinner_Country_E;
    ImageView iv_UserImage, Image1, Image2, Image3, Image4;
    ImageButton btn_Delete, btn_cancle, btn_Edit;
    LinearLayout ll_UserTypeAdd, ll_UserTypeEdit, ll_AdminUserEdit, ll_BrandeAdd, ll_CategoriesAdd, ll_ProductTypesAdd,
            ll_AdminUserAdd, ll_ProductAdd, ll_ProductEdit, ll_BrandeEdit, ll_CategoriesEdit, ll_ProductTypesEdit;

    String strUserTypeName = "", strBrandName = "", strCategoriesName = "", strProductTypesName = "",
            strID = "", strName = "", strUserName = "", strPhone = "", strImageUser = "", strEmail = "", strPassword = "", strImage = "", strUserType = "",
            strPro_Name = "", strDetails = "", strPrice = "", strdiscount = "", strquantity = "", strCategoriesId = "", strBrandId = "", strProductTypeId = "", strCountryId = "",
            strdiscount_price = "", strsort_order = "", strImage1 = "", strImage2 = "", strImage3 = "", strImage4 = "",
            strStatus = "";

    private static final int GalleryPick = 1;
    Uri imageUri;
    ArrayList<String> usertypeid = new ArrayList<>();
    ArrayList<String> usertypename = new ArrayList<>();

    ArrayList<String> brands_id = new ArrayList<>();
    ArrayList<String> brands_name = new ArrayList<>();

    ArrayList<String> categories_id = new ArrayList<>();
    ArrayList<String> categories_name = new ArrayList<>();

    ArrayList<String> country_id = new ArrayList<>();
    ArrayList<String> country_name = new ArrayList<>();

    ArrayList<String> product_types_id = new ArrayList<>();
    ArrayList<String> product_types_name = new ArrayList<>();
    boolean pick = false, pick1 = false, pick2 = false, pick3 = false, pick4 = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dash_bord_data_add_fragment, container, false);

        tv_name = v.findViewById(R.id.tv_name);

        ll_UserTypeAdd = v.findViewById(R.id.ll_UserTypeAdd);
        edt_User_Type_Name = v.findViewById(R.id.edt_User_Type_Name);

        ll_UserTypeEdit = v.findViewById(R.id.ll_UserTypeEdit);
        edt_User_Type_Name_E = v.findViewById(R.id.edt_User_Type_Name_E);

        ll_BrandeAdd = v.findViewById(R.id.ll_BrandeAdd);
        edt_Brand_Name = v.findViewById(R.id.edt_Brand_Name);

        ll_BrandeEdit = v.findViewById(R.id.ll_BrandeEdit);
        edt_Brand_Name_E = v.findViewById(R.id.edt_Brand_Name_E);

        ll_CategoriesAdd = v.findViewById(R.id.ll_CategoriesAdd);
        edt_Categories_Name = v.findViewById(R.id.edt_Categories_Name);

        ll_CategoriesEdit = v.findViewById(R.id.ll_CategoriesEdit);
        edt_Categories_Name_E = v.findViewById(R.id.edt_Categories_Name_E);

        ll_ProductTypesAdd = v.findViewById(R.id.ll_ProductTypesAdd);
        edt_Product_Types_Name = v.findViewById(R.id.edt_Product_Types_Name);

        ll_ProductTypesEdit = v.findViewById(R.id.ll_ProductTypesEdit);
        edt_Product_Types_Name_E = v.findViewById(R.id.edt_Product_Types_Name_E);

        ll_AdminUserAdd = v.findViewById(R.id.ll_AdminUserAdd);
        edt_Name = v.findViewById(R.id.edt_Name);
        edt_Phone = v.findViewById(R.id.edt_Phone);
        edt_Email = v.findViewById(R.id.edt_Email);
        edt_Password = v.findViewById(R.id.edt_Password);
        edit_Image = v.findViewById(R.id.edit_Image);
        btn_add_Image = v.findViewById(R.id.btn_add_Image);
        spinner_UserType = v.findViewById(R.id.spinner_UserType);

        ll_AdminUserEdit = v.findViewById(R.id.ll_AdminUserEdit);
        edt_Name_E = v.findViewById(R.id.edt_Name_E);
        edt_Phone_E = v.findViewById(R.id.edt_Phone_E);
        edt_Email_E = v.findViewById(R.id.edt_Email_E);
        edt_Password_E = v.findViewById(R.id.edt_Password_E);
        btn_add_Image_E = v.findViewById(R.id.btn_add_Image_E);
        spinner_UserType_E = v.findViewById(R.id.spinner_UserType_E);
        iv_UserImage = v.findViewById(R.id.iv_UserImage);


        ll_ProductAdd = v.findViewById(R.id.ll_ProductAdd);
        edt_Pro_Name = v.findViewById(R.id.edt_Pro_Name);
        edt_Details = v.findViewById(R.id.edt_Details);
        edt_Price = v.findViewById(R.id.edt_Price);
        edt_discount = v.findViewById(R.id.edt_discount);
        edt_quantity = v.findViewById(R.id.edt_quantity);
        edt_discount_price = v.findViewById(R.id.edt_discount_price);
        edt_sort_order = v.findViewById(R.id.edt_sort_order);
        edit_Image1 = v.findViewById(R.id.edit_Image1);
        edit_Image2 = v.findViewById(R.id.edit_Image2);
        edit_Image3 = v.findViewById(R.id.edit_Image3);
        edit_Image4 = v.findViewById(R.id.edit_Image4);
        btn_add_Image1 = v.findViewById(R.id.btn_add_Image1);
        btn_add_Image2 = v.findViewById(R.id.btn_add_Image2);
        btn_add_Image3 = v.findViewById(R.id.btn_add_Image3);
        btn_add_Image4 = v.findViewById(R.id.btn_add_Image4);
        spinner_ProductType = v.findViewById(R.id.spinner_ProductType);
        spinner_Brand = v.findViewById(R.id.spinner_Brand);
        spinner_Category = v.findViewById(R.id.spinner_Category);
        spinner_Country = v.findViewById(R.id.spinner_Country);

        ll_ProductEdit = v.findViewById(R.id.ll_ProductEdit);
        edt_Pro_Name_E = v.findViewById(R.id.edt_Pro_Name_E);
        edt_Details_E = v.findViewById(R.id.edt_Details_E);
        edt_Price_E = v.findViewById(R.id.edt_Price_E);
        edt_discount_E = v.findViewById(R.id.edt_discount_E);
        edt_quantity_E = v.findViewById(R.id.edt_quantity_E);
        edt_discount_price_E = v.findViewById(R.id.edt_discount_price_E);
        edt_sort_order_E = v.findViewById(R.id.edt_sort_order_E);
        Image1 = v.findViewById(R.id.Image1);
        Image2 = v.findViewById(R.id.Image2);
        Image3 = v.findViewById(R.id.Image3);
        Image4 = v.findViewById(R.id.Image4);
        btn_chn_Image1 = v.findViewById(R.id.btn_chn_Image1);
        btn_chn_Image2 = v.findViewById(R.id.btn_chn_Image2);
        btn_chn_Image3 = v.findViewById(R.id.btn_chn_Image3);
        btn_chn_Image4 = v.findViewById(R.id.btn_chn_Image4);
        spinner_ProductType_E = v.findViewById(R.id.spinner_ProductType_E);
        spinner_Brand_E = v.findViewById(R.id.spinner_Brand_E);
        spinner_Category_E = v.findViewById(R.id.spinner_Category_E);
        spinner_Country_E = v.findViewById(R.id.spinner_Country_E);

        spinner_Status = v.findViewById(R.id.spinner_Status);
        btn_Add = v.findViewById(R.id.btn_Add);

        btn_Edit = v.findViewById(R.id.btn_Edit);
        btn_Delete = v.findViewById(R.id.btn_Delete);
        btn_cancle = v.findViewById(R.id.btn_cancle);

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAlertDialog(getActivity(),"Message !","Are You Sure Want To Delete It?");
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            strViewType = bundle.getString("OpenType");
            strViewType1 = bundle.getString("ViewType");

            tv_name.setText(strViewType);

            if (strViewType.equals("Add Admins")) {
                ll_AdminUserAdd.setVisibility(View.VISIBLE);
                GetTypeDataList getTypeDataList = new GetTypeDataList();
                getTypeDataList.execute();
            }

            else if (strViewType.equals("Edit Add Admins")) {
                ll_AdminUserEdit.setVisibility(View.VISIBLE);
                btn_Delete.setVisibility(View.VISIBLE);
                GetTypeDataList getTypeDataList = new GetTypeDataList();
                getTypeDataList.execute();
                strID = bundle.getString("ID");
                strName = bundle.getString("Name");
                strUserType = bundle.getString("UserType");
                strStatus = bundle.getString("AdminStatus");
                strEmail = bundle.getString("Email");
                strPassword = bundle.getString("Password");
                strUserName = bundle.getString("UserName");
                strPhone = bundle.getString("Phone");
                strImageUser = bundle.getString("Image");
                edt_Name_E.setText(strName);
                edt_Email_E.setText(strEmail);
                edt_Password_E.setText(strPassword);
                edt_Phone_E.setText(strPhone);
                try {
                    if (strImageUser.equals("")) {
                        Picasso.get().load("http://103.91.54.60/apps/SOC_71Bazaar/UserImage/dummy.jpg").into(iv_UserImage);
                    } else {
                        Picasso.get().load(strRootUrl + strImageUser).into(iv_UserImage);
                    }
                } catch (Exception e) {
                    Log.d("Image Load", e.toString());
                }
                spinner_UserType_E.setSelection(Integer.valueOf(strUserType));
                btn_Add.setText("Update Info");

            }

            else if (strViewType.equals("Add Brands")) {
                ll_BrandeAdd.setVisibility(View.VISIBLE);
            }

            else if (strViewType.equals("Edit Add Brands")) {
                strID = bundle.getString("ID");
                btn_Delete.setVisibility(View.VISIBLE);
                ll_BrandeEdit.setVisibility(View.VISIBLE);
                strBrandName = bundle.getString("Name");
                edt_Brand_Name_E.setText(strBrandName);
                btn_Add.setText("Update Info");
            }

            else if (strViewType.equals("Add Categories")) {
                ll_CategoriesAdd.setVisibility(View.VISIBLE);
            }

            else if (strViewType.equals("Edit Add Categories")) {
                strID = bundle.getString("ID");
                ll_CategoriesEdit.setVisibility(View.VISIBLE);
                btn_Delete.setVisibility(View.VISIBLE);
                strCategoriesName = bundle.getString("Name");
                edt_Categories_Name_E.setText(strCategoriesName);
                btn_Add.setText("Update Info");
            }

            else if (strViewType.equals("Add Product Types")) {
                ll_ProductTypesAdd.setVisibility(View.VISIBLE);
            }

            else if (strViewType.equals("Edit Add Product Types")) {
                strID = bundle.getString("ID");
                btn_Delete.setVisibility(View.VISIBLE);
                ll_ProductTypesEdit.setVisibility(View.VISIBLE);
                strProductTypesName = bundle.getString("Name");
                edt_Product_Types_Name_E.setText(strProductTypesName);
                btn_Add.setText("Update Info");
            }

            else if (strViewType.equals("Add User Type")) {
                ll_UserTypeAdd.setVisibility(View.VISIBLE);
            }

            else if (strViewType.equals("Edit Add User Type")) {
                strID = bundle.getString("ID");
                btn_Delete.setVisibility(View.VISIBLE);
                ll_UserTypeEdit.setVisibility(View.VISIBLE);
                strUserName = bundle.getString("Name");
                edt_User_Type_Name_E.setText(strUserName);
                btn_Add.setText("Update Info");
            }

            else if (strViewType.equals("Add Products")) {
                GetTypeDataList getTypeDataList = new GetTypeDataList();
                getTypeDataList.execute();
                ll_ProductAdd.setVisibility(View.VISIBLE);
            }

            else if (strViewType.equals("Edit Add Products")) {
                ll_ProductEdit.setVisibility(View.VISIBLE);
                btn_Delete.setVisibility(View.VISIBLE);
                strID = bundle.getString("ID");
                strPro_Name = bundle.getString("Name");
                strStatus = bundle.getString("ProductsStatus");
                strBrandId = bundle.getString("BrandsId");
                strCategoriesId = bundle.getString("CategoryId");
                strCountryId = bundle.getString("CountryId");
                strProductTypeId = bundle.getString("ProductTypesId");
                strdiscount = bundle.getString("Discount");
                strDetails = bundle.getString("Details");
                strPrice = bundle.getString("Price");
                strquantity = bundle.getString("Quantity");
                strdiscount_price = bundle.getString("DiscountPrice");
                strsort_order = bundle.getString("SortOrder");
                strImage1 = bundle.getString("ImageOne");
                strImage2 = bundle.getString("ImageTwo");
                strImage3 = bundle.getString("ImageThree");
                strImage4 = bundle.getString("ImageFour");

                edt_Pro_Name_E.setText(strPro_Name);
                edt_Details_E.setText(strDetails);
                edt_discount_E.setText(strdiscount);
                edt_discount_price_E.setText(strdiscount_price);
                edt_sort_order_E.setText(strsort_order);
                edt_Price_E.setText(strPrice);
                edt_quantity_E.setText(strquantity);


                try {
                    if (strImage1.equals("")) {
                        Picasso.get().load("http://103.91.54.60/apps/SOC_71Bazaar/UserImage/dummy.jpg").into(Image1);
                    } else {
                        Picasso.get().load(strRootUrl + strImage1).into(Image1);
                    }
                } catch (Exception e) {
                    Log.d("Image Load", e.toString());
                }

                try {
                    if (strImage2.equals("")) {
                        Picasso.get().load("http://103.91.54.60/apps/SOC_71Bazaar/UserImage/dummy.jpg").into(Image2);
                    } else {
                        Picasso.get().load(strRootUrl + strImage2).into(Image2);
                    }
                } catch (Exception e) {
                    Log.d("Image Load", e.toString());
                }
                try {
                    if (strImage3.equals("")) {
                        Picasso.get().load("http://103.91.54.60/apps/SOC_71Bazaar/UserImage/dummy.jpg").into(Image3);
                    } else {
                        Picasso.get().load(strRootUrl + strImage3).into(Image3);
                    }
                } catch (Exception e) {
                    Log.d("Image Load", e.toString());
                }
                try {
                    if (strImage4.equals("")) {
                        Picasso.get().load("http://103.91.54.60/apps/SOC_71Bazaar/UserImage/dummy.jpg").into(Image4);
                    } else {
                        Picasso.get().load(strRootUrl + strImage4).into(Image4);
                    }
                } catch (Exception e) {
                    Log.d("Image Load", e.toString());
                }

                btn_Add.setText("Update Info");


                GetTypeDataList getTypeDataList = new GetTypeDataList();
                getTypeDataList.execute();
            }
        }

        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Status));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Status.setAdapter(aa);


        spinner_Status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_UserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strUserType = usertypeid.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_Brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strBrandId = brands_id.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCategoriesId = categories_id.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCountryId = country_id.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_ProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProductTypeId = product_types_id.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strViewType.equals("Add Admins")) {
                    strName = edt_Name.getText().toString();
                    strEmail = edt_Email.getText().toString();
                    strPhone = edt_Phone.getText().toString();
                    strPassword = edt_Password.getText().toString();

                    if (!strName.equals("")) {
                        if (!strEmail.equals("")) {
                            if (!strPhone.equals("")) {
                                if (!strPassword.equals("")) {
                                    if (!strImage.equals("")) {
                                        if (!strUserType.equals("0000000")) {
                                            DashBordListData dashBordListData = new DashBordListData();
                                            dashBordListData.execute();
                                        } else {
                                            Toast.makeText(getActivity(), "Select User Type", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Select Image", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Enter Phone No", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_LONG).show();
                    }

                }

                if (strViewType.equals("Edit Add Admins")) {
                    strName = edt_Name.getText().toString();
                    strEmail = edt_Email.getText().toString();
                    strPhone = edt_Phone.getText().toString();
                    strPassword = edt_Password.getText().toString();

                    if (!strName.equals("")) {
                        if (!strEmail.equals("")) {
                            if (!strPhone.equals("")) {
                                if (!strPassword.equals("")) {
                                    if (!strImage.equals("")) {
                                        if (!strUserType.equals("0000000")) {
                                            DashBordListData dashBordListData = new DashBordListData();
                                            dashBordListData.execute();
                                        } else {
                                            Toast.makeText(getActivity(), "Select User Type", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Select Image", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Enter Phone No", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_LONG).show();
                    }

                } else if (strViewType.equals("Add Brands")) {
                    strBrandName = edt_Brand_Name.getText().toString().trim();

                    if (!strBrandName.equals("")) {
                        DashBordListData dashBordListData = new DashBordListData();
                        dashBordListData.execute();
                    }
                } else if (strViewType.equals("Add Categories")) {
                    strCategoriesName = edt_Categories_Name.getText().toString().trim();

                    if (!strCategoriesName.equals("")) {
                        DashBordListData dashBordListData = new DashBordListData();
                        dashBordListData.execute();
                    }
                } else if (strViewType.equals("Add Product Types")) {
                    strProductTypesName = edt_Product_Types_Name.getText().toString().trim();

                    if (!strProductTypesName.equals("")) {
                        DashBordListData dashBordListData = new DashBordListData();
                        dashBordListData.execute();
                    }
                } else if (strViewType.equals("Add User Type")) {
                    strUserTypeName = edt_User_Type_Name.getText().toString().trim();

                    if (!strUserTypeName.equals("")) {
                        DashBordListData dashBordListData = new DashBordListData();
                        dashBordListData.execute();
                    }
                } else if (strViewType.equals("Add Products")) {
                    strPro_Name = edt_Pro_Name.getText().toString();
                    strDetails = edt_Details.getText().toString();
                    strPrice = edt_Price.getText().toString();
                    strdiscount = edt_discount.getText().toString();
                    strquantity = edt_quantity.getText().toString();
                    strdiscount_price = edt_discount_price.getText().toString();
                    strsort_order = edt_sort_order.getText().toString();

                    if (!strPro_Name.equals("")) {
                        if (!strDetails.equals("")) {
                            if (!strPrice.equals("")) {
                                if (!strdiscount.equals("")) {
                                    if (!strquantity.equals("")) {
                                        if (!strdiscount_price.equals("")) {
                                            if (!strsort_order.equals("")) {
                                                if (!strImage1.equals("")) {
                                                    if (!strImage2.equals("")) {
                                                        if (!strImage3.equals("")) {
                                                            if (!strImage4.equals("")) {
                                                                DashBordListData dashBordListData = new DashBordListData();
                                                                dashBordListData.execute();
                                                            } else {
                                                                Toast.makeText(getActivity(), "Image Four", Toast.LENGTH_LONG).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(getActivity(), "Image Three", Toast.LENGTH_LONG).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(getActivity(), "Image Two", Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    Toast.makeText(getActivity(), "Image One", Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                Toast.makeText(getActivity(), "Short Description", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Toast.makeText(getActivity(), "Discount Count Price", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Quantity Price", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Discount Name", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Price Name", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Product Details", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Product Name", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        btn_add_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick = true;
                pick1 = false;
                pick2 = false;
                pick3 = false;
                pick4 = false;
                openGallryImage();
            }
        });

        btn_add_Image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick = false;
                pick1 = true;
                pick2 = false;
                pick3 = false;
                pick4 = false;
                openGallryImage();
            }
        });

        btn_add_Image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick = false;
                pick1 = false;
                pick2 = true;
                pick3 = false;
                pick4 = false;
                openGallryImage();
            }
        });

        btn_add_Image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick = false;
                pick1 = false;
                pick2 = false;
                pick3 = true;
                pick4 = false;
                openGallryImage();
            }
        });

        btn_add_Image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick = false;
                pick1 = false;
                pick2 = false;
                pick3 = false;
                pick4 = true;
                openGallryImage();
            }
        });

        return v;
    }


    private class GetTypeDataList extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(getActivity(), "Getting Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            if (strViewType.equals("Add Admins")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", "View User Type"));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
                try {
                    JSONArray jsonArray = new JSONArray(result);

                    usertypeid.clear();
                    usertypename.clear();
                    usertypeid.add("0000000");
                    usertypename.add("Select User Type");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        usertypeid.add(jsonObject.getString("user_type_id"));
                        usertypename.add(jsonObject.getString("user_type_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Edit Add Admins")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", "View User Type"));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
                try {
                    JSONArray jsonArray = new JSONArray(result);

                    usertypeid.clear();
                    usertypename.clear();
                    usertypeid.add("0000000");
                    usertypename.add("Select User Type");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        usertypeid.add(jsonObject.getString("user_type_id"));
                        usertypename.add(jsonObject.getString("user_type_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Add Products")) {
                String result1 = "", result2 = "", result3 = "", result4 = "";

                List<Pair<String, String>> postParameters1 = new ArrayList<>();
                postParameters1.add(new Pair("Type", "View Product Types"));

                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters1);
                    result1 = response.toString();
                    result1 = result1.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result1);

                    product_types_id.clear();
                    product_types_name.clear();
                    product_types_id.add("0000000");
                    product_types_name.add("Select Product Types");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        product_types_id.add(jsonObject.getString("product_types_id"));
                        product_types_name.add(jsonObject.getString("product_types_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters2 = new ArrayList<>();
                postParameters2.add(new Pair("Type", "View Brands"));

                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters2);
                    result2 = response.toString();
                    result2 = result2.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result2);

                    brands_id.clear();
                    brands_name.clear();
                    brands_id.add("0000000");
                    brands_name.add("Select Brands");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        brands_id.add(jsonObject.getString("brands_id"));
                        brands_name.add(jsonObject.getString("brands_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters3 = new ArrayList<>();
                postParameters3.add(new Pair("Type", "View Categories"));

                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters3);
                    result3 = response.toString();
                    result3 = result3.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result3);

                    categories_id.clear();
                    categories_name.clear();
                    categories_id.add("0000000");
                    categories_name.add("Select Categories");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        categories_id.add(jsonObject.getString("categories_id"));
                        categories_name.add(jsonObject.getString("categories_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters4 = new ArrayList<>();
                postParameters4.add(new Pair("Type", "View Country"));

                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters4);
                    result4 = response.toString();
                    result4 = result4.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result4);

                    country_id.clear();
                    country_name.clear();
                    country_id.add("0000000");
                    country_name.add("Select Country");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        country_id.add(jsonObject.getString("id"));
                        country_name.add(jsonObject.getString("name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Edit Add Products")) {
                String result1 = "", result2 = "", result3 = "", result4 = "";

                List<Pair<String, String>> postParameters1 = new ArrayList<>();
                postParameters1.add(new Pair("Type", "View Product Types"));

                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters1);
                    result1 = response.toString();
                    result1 = result1.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result1);

                    product_types_id.clear();
                    product_types_name.clear();
                    product_types_id.add("0000000");
                    product_types_name.add("Select Product Types");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        product_types_id.add(jsonObject.getString("product_types_id"));
                        product_types_name.add(jsonObject.getString("product_types_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters2 = new ArrayList<>();
                postParameters2.add(new Pair("Type", "View Brands"));

                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters2);
                    result2 = response.toString();
                    result2 = result2.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result2);

                    brands_id.clear();
                    brands_name.clear();
                    brands_id.add("0000000");
                    brands_name.add("Select Brands");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        brands_id.add(jsonObject.getString("brands_id"));
                        brands_name.add(jsonObject.getString("brands_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters3 = new ArrayList<>();
                postParameters3.add(new Pair("Type", "View Categories"));

                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters3);
                    result3 = response.toString();
                    result3 = result3.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result3);

                    categories_id.clear();
                    categories_name.clear();
                    categories_id.add("0000000");
                    categories_name.add("Select Categories");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        categories_id.add(jsonObject.getString("categories_id"));
                        categories_name.add(jsonObject.getString("categories_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters4 = new ArrayList<>();
                postParameters4.add(new Pair("Type", "View Country"));

                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters4);
                    result4 = response.toString();
                    result4 = result4.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result4);

                    country_id.clear();
                    country_name.clear();
                    country_id.add("0000000");
                    country_name.add("Select Country");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        country_id.add(jsonObject.getString("id"));
                        country_name.add(jsonObject.getString("name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }


            return status_code;
        }

        @Override
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (!result.equals("null") || !result.equals(null) || !result.equals("")) {
                if (strViewType.equals("Add Admins")) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, usertypename);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_UserType.setAdapter(arrayAdapter);
                } else if (strViewType.equals("Edit Add Admins")) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, usertypename);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_UserType_E.setAdapter(arrayAdapter);
                } else if (strViewType.equals("Add Products")) {
                    ArrayAdapter arrayAdapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories_name);
                    arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Category.setAdapter(arrayAdapter1);

                    ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, country_name);
                    arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Country.setAdapter(arrayAdapter2);

                    ArrayAdapter arrayAdapter3 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, brands_name);
                    arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Brand.setAdapter(arrayAdapter3);

                    ArrayAdapter arrayAdapter4 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, product_types_name);
                    arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_ProductType.setAdapter(arrayAdapter4);
                } else if (strViewType.equals("Edit Add Products")) {
                    ArrayAdapter arrayAdapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories_name);
                    arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Category.setAdapter(arrayAdapter1);

                    ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, country_name);
                    arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Country.setAdapter(arrayAdapter2);

                    ArrayAdapter arrayAdapter3 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, brands_name);
                    arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Brand.setAdapter(arrayAdapter3);

                    ArrayAdapter arrayAdapter4 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, product_types_name);
                    arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_ProductType.setAdapter(arrayAdapter4);
                }
            } else {
                alertDialog(getActivity(), msg, values);
            }

        }
    }

    private class DashBordListData extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(getActivity(), "Sending Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            if (strViewType.equals("Add User Type")) {
                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("user_type_name", strUserTypeName));
                postParameters.add(new Pair("user_type_status", strStatus));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if (strViewType.equals("Add Product Types")) {
                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("product_types_name", strProductTypesName));
                postParameters.add(new Pair("product_types_status", strStatus));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Add Categories")) {
                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("categories_name", strCategoriesName));
                postParameters.add(new Pair("categories_status", strStatus));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Add Brands")) {
                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("brands_name", strBrandName));
                postParameters.add(new Pair("brands_status", strStatus));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Add Admins")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("image", strImage));
                postParameters.add(new Pair("user_type_id", strUserType));
                postParameters.add(new Pair("name", strName));
                postParameters.add(new Pair("phone", strPhone));
                postParameters.add(new Pair("email", strEmail));
                postParameters.add(new Pair("password", strPassword));
                postParameters.add(new Pair("admins_status", strStatus));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Add Products")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("product_types_id", strProductTypeId));
                postParameters.add(new Pair("category_id", strCategoriesId));
                postParameters.add(new Pair("brands_id", strBrandId));
                postParameters.add(new Pair("country_id", strCountryId));
                postParameters.add(new Pair("name", strPro_Name));
                postParameters.add(new Pair("details", strDetails));
                postParameters.add(new Pair("image_one", strImage1));
                postParameters.add(new Pair("image_two", strImage2));
                postParameters.add(new Pair("image_three", strImage3));
                postParameters.add(new Pair("image_four", strImage4));
                postParameters.add(new Pair("price", strPrice));
                postParameters.add(new Pair("discount_price", strdiscount_price));
                postParameters.add(new Pair("discount", strdiscount));
                postParameters.add(new Pair("quantity", strquantity));
                postParameters.add(new Pair("sort_order", strsort_order));
                postParameters.add(new Pair("products_status", strStatus));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Delete User Type")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("ID", strID));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Delete Categories")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("ID", strID));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Delete Admins")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("ID", strID));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Delete Brands")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("ID", strID));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Delete Products")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("ID", strID));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Delete Product Types")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("ID", strID));
                try {
                    String response = HttpClient.execute(strRootUrl + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

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
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (status_code.equals("200")) {
                alertDialog1( msg, values);
            } else {
                alertDialog(getActivity(), msg, values);
            }

        }
    }

    private void openGallryImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            File filePath = new File(getRealPathFromURI(imageUri));
            String fileName = filePath.getName();
            String extension = filePath.getAbsolutePath().substring(filePath.getAbsolutePath().lastIndexOf(".") + 1);

            if (pick1) {
                edit_Image1.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage1 = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (pick2) {
                edit_Image2.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage2 = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (pick3) {
                edit_Image3.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage3 = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (pick4) {
                edit_Image4.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage4 = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (pick) {
                edit_Image.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                alertDialog(getActivity(), "Error", "No Picture Is Selected");
            }

        }

    }

    private String getRealPathFromURI(Uri contentURI) {
        String result = "";
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public void alertDialog(android.content.Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setCancelable(false)
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public void alertDialog1( String title, String message) {
        new AlertDialog.Builder(getActivity()).setTitle(title).setMessage(message).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {

                        Intent intent = new Intent(getActivity(),DashBordProscessData.class);
                        intent.putExtra("Name",strViewType1);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                        dialog.dismiss();
                        dismiss();
                    }
                }).show();
    }

    public void deleteAlertDialog(android.content.Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        if (strViewType.equals("Edit Add Admins")) {
                            strViewType = "Delect Admins";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Brands")) {
                            strViewType = "Delete Admins";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Categories")) {
                            strViewType = "Delete Categories";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Brands")) {
                            strViewType = "Delete Brands";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Product Types")) {
                            strViewType = "Delete Product Types";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add User Type")) {
                            strViewType = "Delete User Type";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Products")) {
                            strViewType = "Delete Products";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity()
                .getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

}
