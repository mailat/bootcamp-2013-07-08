package com.marakana.yamba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
	private static final String TAG = UpdaterService.class.getSimpleName();

	
	Context context;

	public DbHelper(Context context) {
		super(context, StatusContract.DB_NAME, null, StatusContract.DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//create the new table
		String sql ="create table " + StatusContract.TABLE + " ( " + 
				StatusContract.Column.C_ID + " INT PRIMARY KEY," +
				StatusContract.Column.C_CREATED_AT + " INT, " +
				StatusContract.Column.C_SOURCE + " TEXT, " +
				StatusContract.Column.C_USER + " TEXT, " +
				StatusContract.Column.C_TXT + " TEXT);";
		db.execSQL(sql);
		
		Log.d(TAG, "onCreate() with sql=" + sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// we drop at every update the old database
		db.execSQL("drop table if exists " + StatusContract.TABLE + ";");
		onCreate(db);
		
		Log.d(TAG, "onUpgrade()");
	}

}
