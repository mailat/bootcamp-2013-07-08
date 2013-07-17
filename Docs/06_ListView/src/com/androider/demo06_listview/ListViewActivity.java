package com.androider.demo06_listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    //redirecteaza catre activitatea de testare SimpleListActivity
	public void testSimpleListActivity(View view) {
		Intent intent = new Intent(ListViewActivity.this, SimpleListActivity.class);
		startActivity(intent);
	}
	
    //redirecteaza catre activitatea de testare ComplexListActivity
	public void testComplexListActivity(View view) {
		Intent intent = new Intent(ListViewActivity.this, ComplexListActivity.class);
		startActivity(intent);
	}
	
    //redirecteaza catre activitatea de testare AdapterListActivity
	public void testAdapterListActivity(View view) {
		Intent intent = new Intent(ListViewActivity.this, AdapterListActivity.class);
		startActivity(intent);
	}
	
}