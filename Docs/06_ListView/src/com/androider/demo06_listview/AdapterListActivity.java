package com.androider.demo06_listview;

import com.androider.demo06_listview.adapters.CustomArrayAdapter;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class AdapterListActivity extends ListActivity {
	
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		//seteaza titlul activitatii
		setTitle("Lista cu judetele Romaniei.");		

		//initializeaza o lista de test
		String[] valuesAvailable = getResources().getStringArray(R.array.counties_array);
		
		CustomArrayAdapter adapter = new CustomArrayAdapter(this, valuesAvailable);
		setListAdapter(adapter);
	}
    
	//reactioneaza la onListItemClick
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(getApplicationContext(), (String) getListAdapter().getItem(position), Toast.LENGTH_SHORT).show();
	}
}
