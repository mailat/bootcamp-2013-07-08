package com.marakana.yamba;

import android.provider.BaseColumns;

public class StatusContract {
	
	public static final String DB_NAME = "timeline.db";
	public static final int DB_VERSION = 1;
	public static final String TABLE = "timeline";
	
	class Column
	{
		//table name and table fields
		public static final String C_ID = BaseColumns._ID;
		public static final String C_CREATED_AT = "created_at";
		public static final String C_SOURCE = "source";
		public static final String C_USER = "user";
		public static final String C_TXT = "txt";
	}
	
}
