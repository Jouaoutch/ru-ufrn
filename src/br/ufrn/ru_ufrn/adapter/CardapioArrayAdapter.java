package br.ufrn.ru_ufrn.adapter;

import br.ufrn.ru_ufrn.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CardapioArrayAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
	private final int resource;

	public CardapioArrayAdapter(Context context, 
			int resource, String[] values) {
		super(context, resource, values);
		this.context = context;
		this.values = values;
		this.resource = resource;
	}
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	View rowView = inflater.inflate(this.resource, parent, false);
    	
    	TextView textView = (TextView) rowView.findViewById(R.id.label);
    	textView.setText(values[position]);
    	
    	return rowView;
    }

}
