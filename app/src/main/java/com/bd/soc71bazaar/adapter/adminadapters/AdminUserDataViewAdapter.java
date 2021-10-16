package com.bd.soc71bazaar.adapter.adminadapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.adminpart.DashBordDataAddFragment;
import com.bd.soc71bazaar.model.admin.AdminModel;


import java.util.ArrayList;
import java.util.List;

public class AdminUserDataViewAdapter extends BaseAdapter {

    private Context context;
    private ItemFilter mFilter;
    ArrayList<AdminModel> list;
    ArrayList<AdminModel> completeAllocationListModels;
    String strType;
    public AdminUserDataViewAdapter(@NonNull Context context, ArrayList<AdminModel> contactListtModels, String strType) {
        this.context = context;
        this.strType = strType;
        this.completeAllocationListModels = contactListtModels;
        this.list = new ArrayList<AdminModel>();
        this.list.addAll(contactListtModels);
        mFilter = new ItemFilter();
    }


    public int getCount() {
        return completeAllocationListModels.size();
    }

    public Object getItem(int position) {
        return completeAllocationListModels.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public Filter getFilter() {
        return mFilter;
    }

    public void clearFilter() {
        completeAllocationListModels = (ArrayList<AdminModel>) list;

    }
    private static class ViewHolder{
        TextView tv_name,status,table_id,tableDataOthers,tvDate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        final View result;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.dash_bord_view_adapter,parent,false);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            holder.table_id = (TextView) convertView.findViewById(R.id.table_id);
            holder.tableDataOthers = (TextView) convertView.findViewById(R.id.tableDataOthers);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            result = convertView;
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        holder.tv_name.setText(completeAllocationListModels.get(position).getName()+"("+completeAllocationListModels.get(position).getUsername()+")");
        holder.status.setText("Status : "+completeAllocationListModels.get(position).getAdmins_status());
        holder.table_id.setText("Id : "+completeAllocationListModels.get(position).getUser_type_id()+
                " User Type Id : "+completeAllocationListModels.get(position).getUser_type_id()+
                " Shop Id : "+completeAllocationListModels.get(position).getShop_id());
        holder.tableDataOthers.setText("Phone : "+completeAllocationListModels.get(position).getPhone()+
                "Email : "+completeAllocationListModels.get(position).getEmail());
        holder.tvDate.setText("Create Data :" +completeAllocationListModels.get(position).getCreate_info()+"/ Update Data :" +completeAllocationListModels.get(position).getUpdate_info());


        return result;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterableString = "";

            String filterString = constraint.toString();

            FilterResults results = new FilterResults();

            final List<AdminModel> divisModels = completeAllocationListModels;

            int count = divisModels.size();
            final ArrayList<AdminModel> nlist = new ArrayList<AdminModel>(count);


            for (int i = 0; i < count; i++) {
                filterableString = divisModels.get(i).getName();
                if (!filterableString.equals("")) {
                    if (filterableString.toLowerCase().contains(filterString)) {
                        nlist.add(divisModels.get(i));
                    }
                }

            }


            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            completeAllocationListModels = (ArrayList<AdminModel>) results.values;
            notifyDataSetChanged();
        }

    }
}
