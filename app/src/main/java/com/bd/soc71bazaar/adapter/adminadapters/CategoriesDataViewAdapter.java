package com.bd.soc71bazaar.adapter.adminadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bd.soc71bazaar.R;
import com.bd.soc71bazaar.model.admin.BrandsModel;
import com.bd.soc71bazaar.model.admin.CategoriesModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDataViewAdapter extends BaseAdapter {

    private Context context;
    private ItemFilter mFilter;
    ArrayList<CategoriesModel> list;
    ArrayList<CategoriesModel> completeAllocationListModels;
    String strType;
    public CategoriesDataViewAdapter(@NonNull Context context, ArrayList<CategoriesModel> contactListtModels, String strType) {
        this.context = context;
        this.strType = strType;
        this.completeAllocationListModels = contactListtModels;
        this.list = new ArrayList<CategoriesModel>();
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
        completeAllocationListModels = (ArrayList<CategoriesModel>) list;

    }
    private static class ViewHolder{
        TextView tv_name,status,table_id,tableDataOthers,tvDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

        holder.tv_name.setText(completeAllocationListModels.get(position).getCategories_name());
        holder.status.setText("Status : "+completeAllocationListModels.get(position).getCategories_status());
        holder.table_id.setText("Categorie id : "+completeAllocationListModels.get(position).getCategories_id());
        holder.tvDate.setText("Create Data :" +completeAllocationListModels.get(position).getCreate_info()+"/ Update Data :" +completeAllocationListModels.get(position).getUpdate_info());
        holder.tableDataOthers.setVisibility(View.GONE);

        return result;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterableString = "";

            String filterString = constraint.toString();

            FilterResults results = new FilterResults();

            final List<CategoriesModel> divisModels = completeAllocationListModels;

            int count = divisModels.size();
            final ArrayList<CategoriesModel> nlist = new ArrayList<CategoriesModel>(count);


            for (int i = 0; i < count; i++) {
                filterableString = divisModels.get(i).getCategories_name();
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

            completeAllocationListModels = (ArrayList<CategoriesModel>) results.values;
            notifyDataSetChanged();
        }

    }
}
