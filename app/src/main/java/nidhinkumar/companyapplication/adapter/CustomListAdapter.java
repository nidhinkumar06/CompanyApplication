package nidhinkumar.companyapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import nidhinkumar.companyapplication.DepartmentAct;
import nidhinkumar.companyapplication.DescriptionAct;
import nidhinkumar.companyapplication.R;
import nidhinkumar.companyapplication.model.Company;

/**
 * Created by user on 19-05-2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Company> companyItems;
    Company m;
    Context context;
    public CustomListAdapter(Activity activity, List<Company> companyItems) {
        this.activity = activity;
        this.companyItems = companyItems;
    }

    @Override
    public int getCount() {
        return companyItems.size();
    }

    @Override
    public Object getItem(int location) {
        return companyItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.companyprimaryitems, null);

        TextView companyid=(TextView)convertView.findViewById(R.id.companyid);
        TextView companyname = (TextView) convertView.findViewById(R.id.companyname);
        TextView companyowner = (TextView) convertView.findViewById(R.id.companyowner);
        TextView companystartdate = (TextView) convertView.findViewById(R.id.comapnystartdate);
        final TextView comapnydesc = (TextView) convertView.findViewById(R.id.companydesc);
        final TextView companylists=(TextView)convertView.findViewById(R.id.comapanylists);
        TextView aboutcompany=(TextView)convertView.findViewById(R.id.about);
        TextView depts=(TextView)convertView.findViewById(R.id.dept);
         m = companyItems.get(position);



        companyid.setText(String.valueOf(m.getCompanyid()));
        companyname.setText(m.getCompanyname());
        companyowner.setText(m.getCompanyowner());
        companystartdate.setText(m.getStartdate());
        comapnydesc.setText(m.getDescription());
        companylists.setText(m.getDepartments());
        aboutcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passingdata=comapnydesc.getText().toString();
                Intent i=new Intent(activity,DescriptionAct.class);
                Bundle b=new Bundle();
                b.putString("key",passingdata);
                i.putExtras(b);
                activity.startActivity(i);

            }
        });
        depts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passingdata=companylists.getText().toString();
                Intent i=new Intent(activity,DepartmentAct.class);
                Bundle b=new Bundle();
                b.putString("key",passingdata);
                i.putExtras(b);
                activity.startActivity(i);
            }
        });



        return convertView;


    }
    public void setFilter(List<Company> mcompanyItems){
        companyItems=new ArrayList<>();
        companyItems.addAll(mcompanyItems);
        notifyDataSetChanged();
    }
}
