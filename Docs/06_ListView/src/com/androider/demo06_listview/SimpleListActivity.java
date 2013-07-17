package com.androider.demo06_listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleListActivity extends ListActivity {
	
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		//seteaza titlul activitatii
		setTitle("Lista cu judetele Romaniei.");		

		//initializeaza o lista de test
		String[] valuesAvailable = getResources().getStringArray(R.array.counties_array);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valuesAvailable));
				
	    //reactioneaza la onItemClick prin interfata OnItemClickListener
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
			      Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		   
		});

	}
}
