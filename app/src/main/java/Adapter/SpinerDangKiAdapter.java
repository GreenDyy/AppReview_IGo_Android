package Adapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.igo.R;

import java.util.List;

import Model.*;

public class SpinerDangKiAdapter extends ArrayAdapter<model_itemSpiner_dangki> {
    public SpinerDangKiAdapter(@NonNull Context context, int resource, @NonNull List<model_itemSpiner_dangki> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spiner_selected, parent, false);
        TextView tv = convertView.findViewById(R.id.itemselectspiner);

        model_itemSpiner_dangki md = this.getItem(position);
        if (md != null){
            tv.setText(md.getName());
        }
        return convertView;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spiner, parent, false);
        TextView tv = convertView.findViewById(R.id.tvspiner);

        model_itemSpiner_dangki md = this.getItem(position);
        if (md != null){
            tv.setText(md.getName());
        }
        return convertView;
    }
}
