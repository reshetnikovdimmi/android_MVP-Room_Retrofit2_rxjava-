package com.fragment.android_suz.ui.Authorization;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.fragment.android_suz.R;
import com.fragment.android_suz.model.promo.Promo;

import java.util.HashMap;
import java.util.List;


public class ListAdapter extends BaseExpandableListAdapter {
    private final RV_promo_fragment rv_promo;
    private final Context context;
    private final HashMap<String, List<Promo>> listChild;
    private final List<String> listHeader;

    public ListAdapter(RV_promo_fragment rv_promo, Context context, HashMap<String, List<Promo>> listChild, List<String> listHeader) {
        this.rv_promo = rv_promo;
        this.context = context;
        this.listChild = listChild;
        this.listHeader = listHeader;
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return listChild.get(listHeader.get(groupPosition)).size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(listHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) rv_promo.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);

        }

        TextView textView = convertView.findViewById(R.id.list_headr);
        textView.setText(headerTitle);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Promo childTitle = (Promo) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) rv_promo.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_items, null);

        }

        TextView brand = convertView.findViewById(R.id.list_child);
        TextView model = convertView.findViewById(R.id.tv_model);
        TextView beginning = convertView.findViewById(R.id.tv_beginning);
        TextView end = convertView.findViewById(R.id.end);
        TextView price = convertView.findViewById(R.id.tv_price);

        brand.setText(childTitle.getBrend());
        model.setText(childTitle.getModels());
        beginning.setText(childTitle.getStartPromo());
        end.setText(childTitle.getEndPromo());
        price.setText(childTitle.getPricePromo());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

