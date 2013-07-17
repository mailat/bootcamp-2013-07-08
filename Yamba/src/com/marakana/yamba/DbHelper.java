package com.marakana.yamba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
	private static final String TAG = UpdaterService.class.getSimpleName();
	static final String DB_NAME = "timeline.db";
	static final int DB_VERSION = 1;
	
	//table name and table fields
	static final String TABLE = "timeline";
	static final String C_ID = BaseColumns._ID;
	static final String C_CREATED_AT = "created_at";
	static final String C_SOURCE = "source";
	static final String C_USER = "user";
	static final String C_TXT = "txt";
	
	Context context;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//create the new table
		String sql ="create table " + TABLE + " ( " + 
		C_ID + " INT PRIMARY KEY," +
		C_CREATED_AT + " INT, " +
		C_SOURCE + " TEXT, " +
		C_USER + " TEXT, " +
		C_TXT + " TEXT);";
		db.execSQL(sql);
		
		Log.d(TAG, "onCreate() with sql=" + sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// we drop at every update the old database
		db.execSQL("drop table if exists timeline;");
		onCreate(db);
		
		Log.d(TAG, "onUpgrade()");
	}

}
