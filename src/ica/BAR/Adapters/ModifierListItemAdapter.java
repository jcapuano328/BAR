package ica.BAR.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import ica.BAR.R;
import ica.BAR.Core.*;

import java.util.*;

public class ModifierListItemAdapter extends ArrayAdapter<Modifier> {
    private final Context context;
    private final List<Modifier> values;

    public ModifierListItemAdapter(Context context, List<Modifier> values) {
        super(context, R.layout.modifierlistitem, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Modifier mod = values.get(position);

        View rowView = inflater.inflate(R.layout.modifierlistitem, parent, false);
        
        CheckBox chkModifier = (CheckBox) rowView.findViewById(R.id.chkModifier);
        chkModifier.setChecked(mod.Count > 0);
        chkModifier.setText(mod.Name);

        return rowView;
    }
}