package com.androider.demo06_listview.adapters;

import com.androider.demo06_listview.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public CustomArrayAdapter(Context context, String[] values) {
		super(context, R.layout.complex_row, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int[] colors = new int[] { Color.parseColor("#284b91"), Color.parseColor("#6d87c1")};
		int[] icons = new int[] { R.drawable.ro_round, R.drawable.ro_square};
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.complex_row, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.labelJudet);
		textView.setText(values[position]);

		// Change the icon for Windows and iPhone
		//set alternative colors
		int colorPos = position % colors.length;
		rowView.setBackgroundColor( colors[colorPos] );
		
		ImageView imageView = (ImageView) rowView.findViewById(R.id.iconJudet);
		imageView.setImageResource(icons[colorPos]);
		return rowView;
	}
}
