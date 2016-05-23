package nidhinkumar.companyapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import nidhinkumar.companyapplication.R;

/**
 * Created by user on 22-05-2016.
 */
public class DepartListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] listNumNames;


    public DepartListAdapter(Context cont, String[] listNumNames)
    {
        super(cont, R.layout.department_frag, listNumNames);
        this.context = (Activity) cont;
        this.listNumNames = listNumNames;

    }

    static class ViewHolder {
        public TextView txtTitle;

    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        ViewHolder holder = null;

        if (rowView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.department_frag, parent, false);
            holder.txtTitle = (TextView) rowView.findViewById(R.id.tvdepartments);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        holder.txtTitle.setText(listNumNames[position]);

        return rowView;
    }
}
