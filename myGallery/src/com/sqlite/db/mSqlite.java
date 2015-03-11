package com.sqlite.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class mSqlite {
	private static final String DATABASE_NAME = "airlines.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_USER = "user"; // �û���
	public static final String CREATE_TABLE_USER = "CREATE TABLE user (idNum INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, pwd TEXT NOT NULL)";
	public static final String INSERT_ADMIN_USER = "INSERT INTO user (name, pwd) VALUES ('ad', 'ad')";

	public static final String TABLE_PICPATH = "picPath"; // ͼƬ·��
	public static final String CREATE_TABLE_PICPATH = "CREATE TABLE picpath (idNum INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "userId TEXT NOT NULL, " + "picPath TEXT NOT NULL)";

	private final HospitalSqliteHelper dbHelper;

	public mSqlite(Context context) {
		dbHelper = new HospitalSqliteHelper(context);
	}

	public void close() {
		dbHelper.close();
	}

	// ��¼ϵͳ
	public boolean logonJudge(String logonNameString, String logonPassString) {
		boolean mBoolean = false;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "SELECT * FROM user WHERE name = ? AND pwd = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { logonNameString,
				logonPassString });
		if (cursor.moveToFirst() == true) {
			mBoolean = true;
		}
		cursor.close();
		return mBoolean;
	}

	// �޸���Ա����
	public boolean modPassword(String userIdNumString, String logonPassString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "UPDATE user SET pwd = ? WHERE idNum = ?";
		sdb.execSQL(sql, new String[] { logonPassString, userIdNumString });

		return true;
	}

	// ɾ����Ա
	public boolean delUser(String userIdNumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "DELETE FROM user WHERE idNum = ?";
		String[] bindArgs = { userIdNumString };
		sdb.execSQL(sql, bindArgs);
		return true; // ɾ���ɹ�
	}

	// �����Ա
	public boolean insertUser(String name, String pwd) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { name });
		if (cursor.moveToFirst() == true) {
			return false;
		}

		sql = "INSERT INTO user (name, pwd) VALUES (?, ?)";
		String[] bindArgs = { name, pwd };
		sdb.execSQL(sql, bindArgs);
		return true; // ��ӳɹ�
	}

	// ��ȡ��Աid
	public String getUserId(String logonNameString, String logonPassString) {
		String userIdNumString = new String("0");
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND pwd = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { logonNameString,
				logonPassString });
		if (cursor.moveToFirst() == true) {
			userIdNumString = cursor.getString(cursor.getColumnIndex("idNum"));
		}
		cursor.close();
		return userIdNumString;
	}

	// ���ͼƬ
	public boolean insertPicPath(String userId, String picPath) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "INSERT INTO picPath (userId, picPath) VALUES (?, ?)";
		String[] bindArgs = { userId, picPath };
		sdb.execSQL(sql, bindArgs);
		return true; // ��ӳɹ�
	}

	// ɾ��ͼƬ
	public boolean delPicPath(String userId, String picPath) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "DELETE FROM picPath WHERE userId = ? AND picPath = ?";
		String[] bindArgs = { userId, picPath };
		sdb.execSQL(sql, bindArgs);
		return true; // ɾ���ɹ�
	}

	// ��ѯͼƬ
	public List<String> getPicPath(String userId) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM picPath WHERE userId = ?";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql, new String[] { userId });
		while (cursor.moveToNext()) {
			resList.add(cursor.getString(cursor.getColumnIndex("picPath")));
		}
		cursor.close();
		return resList;
	}

	// ���ݿ������
	public class HospitalSqliteHelper extends SQLiteOpenHelper {

		public HospitalSqliteHelper(Context context) {
			// CursorFactory����Ϊnull,ʹ��Ĭ��ֵ
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// ���ݿ��һ�α�����ʱonCreate�ᱻ����
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_USER);
			db.execSQL(INSERT_ADMIN_USER);
			db.execSQL(CREATE_TABLE_PICPATH);

		}

		// ���DATABASE_VERSIONֵ����Ϊ2,ϵͳ�����������ݿ�汾��ͬ,�������onUpgrade
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICPATH);
			onCreate(db);
		}

	}
}