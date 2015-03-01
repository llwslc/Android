package hospital.sqlite.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HospitalSqlite {
	private static final String DATABASE_NAME = "hospital.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_USER = "user"; // 用户表
	public static final String CREATE_TABLE_USER = "CREATE TABLE user (idNum INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, pwd TEXT NOT NULL, role INTEGER NOT NULL)";
	public static final String INSERT_ADMIN_USER = "INSERT INTO user (name, pwd, role) VALUES ('ad', 'ad', 3)";
	public static final String INSERT_DOCTOR_USER = "INSERT INTO user (name, pwd, role) VALUES ('doc', '', 0)";
	public static final String INSERT_NURSE_USER = "INSERT INTO user (name, pwd, role) VALUES ('nur', '', 1)";
	public static final String INSERT_PATIENT_USER = "INSERT INTO user (name, pwd, role) VALUES ('pat', '', 2)";

	public static final String TABLE_DEPARTMENT = "department"; // 科室表
	public static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE department (idNum INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, phone TEXT NOT NULL)";
	public static final String INSERT_INFO_DEPARTMENT01 = "INSERT INTO department (name, phone) VALUES ('儿科', '84850001')";
	public static final String INSERT_INFO_DEPARTMENT02 = "INSERT INTO department (name, phone) VALUES ('眼科', '84850002')";
	public static final String INSERT_INFO_DEPARTMENT03 = "INSERT INTO department (name, phone) VALUES ('口腔科', '84850003')";
	public static final String INSERT_INFO_DEPARTMENT04 = "INSERT INTO department (name, phone) VALUES ('皮肤性病科', '84850004')";
	public static final String INSERT_INFO_DEPARTMENT05 = "INSERT INTO department (name, phone) VALUES ('妇科', '84850005')";
	public static final String INSERT_INFO_DEPARTMENT06 = "INSERT INTO department (name, phone) VALUES ('产科', '84850006')";
	public static final String INSERT_INFO_DEPARTMENT07 = "INSERT INTO department (name, phone) VALUES ('内分泌科', '84850007')";
	public static final String INSERT_INFO_DEPARTMENT08 = "INSERT INTO department (name, phone) VALUES ('骨科', '84850008')";
	public static final String INSERT_INFO_DEPARTMENT09 = "INSERT INTO department (name, phone) VALUES ('肝胆外科', '84850009')";
	public static final String INSERT_INFO_DEPARTMENT10 = "INSERT INTO department (name, phone) VALUES ('泌尿外科', '84850010')";
	public static final String INSERT_INFO_DEPARTMENT11 = "INSERT INTO department (name, phone) VALUES ('耳鼻喉科', '84850011')";
	public static final String INSERT_INFO_DEPARTMENT12 = "INSERT INTO department (name, phone) VALUES ('头颈外科', '84850012')";
	public static final String INSERT_INFO_DEPARTMENT13 = "INSERT INTO department (name, phone) VALUES ('心血管内科', '84850013')";
	public static final String INSERT_INFO_DEPARTMENT14 = "INSERT INTO department (name, phone) VALUES ('神经内科', '84850014')";
	public static final String INSERT_INFO_DEPARTMENT15 = "INSERT INTO department (name, phone) VALUES ('肛肠外科', '84850015')";
	public static final String INSERT_INFO_DEPARTMENT16 = "INSERT INTO department (name, phone) VALUES ('乳腺甲状腺外科', '84850016')";
	public static final String INSERT_INFO_DEPARTMENT17 = "INSERT INTO department (name, phone) VALUES ('整形激光美容外科', '84850017')";
	public static final String INSERT_INFO_DEPARTMENT18 = "INSERT INTO department (name, phone) VALUES ('神经外科', '84850018')";
	public static final String INSERT_INFO_DEPARTMENT19 = "INSERT INTO department (name, phone) VALUES ('心胸外科', '84850019')";
	public static final String INSERT_INFO_DEPARTMENT20 = "INSERT INTO department (name, phone) VALUES ('消化内科', '84850020')";
	public static final String INSERT_INFO_DEPARTMENT21 = "INSERT INTO department (name, phone) VALUES ('呼吸内科', '84850021')";
	public static final String INSERT_INFO_DEPARTMENT22 = "INSERT INTO department (name, phone) VALUES ('血液肿瘤科', '84850022')";
	public static final String INSERT_INFO_DEPARTMENT23 = "INSERT INTO department (name, phone) VALUES ('肾内科', '84850023')";
	public static final String INSERT_INFO_DEPARTMENT24 = "INSERT INTO department (name, phone) VALUES ('核医学科', '84850024')";
	public static final String INSERT_INFO_DEPARTMENT25 = "INSERT INTO department (name, phone) VALUES ('检验科', '84850025')";
	public static final String INSERT_INFO_DEPARTMENT26 = "INSERT INTO department (name, phone) VALUES ('病理科', '84850026')";
	public static final String INSERT_INFO_DEPARTMENT27 = "INSERT INTO department (name, phone) VALUES ('放射科', '84850027')";
	public static final String INSERT_INFO_DEPARTMENT28 = "INSERT INTO department (name, phone) VALUES ('高压氧科', '84850028')";
	public static final String INSERT_INFO_DEPARTMENT29 = "INSERT INTO department (name, phone) VALUES ('麻醉科', '84850029')";
	public static final String INSERT_INFO_DEPARTMENT30 = "INSERT INTO department (name, phone) VALUES ('护理部', '84850030')";
	public static final String INSERT_INFO_DEPARTMENT31 = "INSERT INTO department (name, phone) VALUES ('肠道科', '84850031')";
	public static final String INSERT_INFO_DEPARTMENT32 = "INSERT INTO department (name, phone) VALUES ('预防保健科', '84850032')";
	public static final String INSERT_INFO_DEPARTMENT33 = "INSERT INTO department (name, phone) VALUES ('康复理疗科', '84850033')";
	public static final String INSERT_INFO_DEPARTMENT34 = "INSERT INTO department (name, phone) VALUES ('药剂科', '84850034')";

	public static final String TABLE_DOCTOR = "doctor"; // 医生信息表
	public static final String CREATE_TABLE_DOCTOR = "CREATE TABLE doctor (idNum INTEGER PRIMARY KEY AUTOINCREMENT, userid TEXT NOT NULL, name TEXT NOT NULL, sex INTEGER, pic TEXT, idnumstr TEXT NOT NULL, phone TEXT NOT NULL, departmentid INTEGER, des TEXT NOT NULL)";
	public static final String INSERT_DEFAULT_DOCTOR = "INSERT INTO doctor (userid, name, sex, pic, idnumstr, phone, departmentid, des) VALUES ('2', '医生', '0', 'mnt/sdcard/hospital/avatar/avatar.png', '123456789012345678', '12345678901', '0', '医生')";

	public static final String TABLE_NURSE = "nurse"; // 护士信息表
	public static final String CREATE_TABLE_NURSE = "CREATE TABLE nurse (idNum INTEGER PRIMARY KEY AUTOINCREMENT, userid TEXT NOT NULL, name TEXT NOT NULL, sex INTEGER, pic TEXT, idnumstr TEXT NOT NULL, phone TEXT NOT NULL, departmentid INTEGER, des TEXT NOT NULL)";
	public static final String INSERT_DEFAULT_NURSE = "INSERT INTO nurse (userid, name, sex, pic, idnumstr, phone, departmentid, des) VALUES ('3', '护士', '1', 'mnt/sdcard/hospital/avatar/avatar.png', '123456789012345678', '12345678901', '0', '护士')";

	public static final String TABLE_PATIENT = "patient"; // 病人信息表
	public static final String CREATE_TABLE_PATIENT = "CREATE TABLE patient (idNum INTEGER PRIMARY KEY AUTOINCREMENT, userid TEXT NOT NULL, name TEXT NOT NULL, sex INTEGER, pic TEXT, idnumstr TEXT NOT NULL, phone TEXT NOT NULL, departmentid INTEGER, des TEXT NOT NULL)";
	public static final String INSERT_DEFAULT_PATIENT = "INSERT INTO patient (userid, name, sex, pic, idnumstr, phone, departmentid, des) VALUES ('4', '病人', '0', 'mnt/sdcard/hospital/avatar/avatar.png', '123456789012345678', '12345678901', '0', '病人')";

	public static final String TABLE_PATCASE = "patcase"; // 病例信息表
	public static final String CREATE_TABLE_PATCASE = "CREATE TABLE patcase (idNum INTEGER PRIMARY KEY AUTOINCREMENT, patientid INTEGER NOT NULL, des TEXT NOT NULL, doctorId INTEGER NOT NULL, departmentId INTEGER NOT NULL, bedId TEXT NOT NULL, admission DATETIME, discharge DATETIME, allcosts INTEGER)";
	public static final String INSERT_DEFAULT_PATCASE = "INSERT INTO patcase (patientid, des, doctorId, departmentId, bedId, admission, discharge, allcosts) VALUES ('4', '咳嗽', '2', '0', '12楼2床', '2013-01-01', '2013-02-01', '100')";

	public static final String TABLE_Drug = "drug"; // 药物信息表
	public static final String CREATE_TABLE_Drug = "CREATE TABLE drug (idNum INTEGER PRIMARY KEY AUTOINCREMENT, drugcode TEXT NOT NULL, drugname TEXT NOT NULL, des TEXT NOT NULL, drugunit INTEGER NOT NULL, drugprice INTEGER NOT NULL, stocknum INTEGER NOT NULL)";
	public static final String INSERT_DEFAULT_Drug = "INSERT INTO drug (drugcode, drugname, des, drugunit, drugprice, stocknum) VALUES ('H10940250', '扑热息痛', '药效学本品为乙酰苯胺类解热镇痛药', '1', '10', '1000')";

	private final HospitalSqliteHelper dbHelper;

	public HospitalSqlite(Context context) {
		dbHelper = new HospitalSqliteHelper(context);
	}

	public void close() {
		dbHelper.close();
	}

	// 登录系统
	public boolean logonJudge(String logonNameString, String logonPassString,
			String logonRoleString) {
		boolean mBoolean = false;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "SELECT * FROM user WHERE name = ? AND pwd = ? AND role = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { logonNameString,
				logonPassString, logonRoleString });
		if (cursor.moveToFirst() == true) {
			mBoolean = true;
		}
		cursor.close();
		return mBoolean;
	}

	// 获取人员id
	public String getUserId(String logonNameString, String logonPassString,
			String logonRoleString) {
		String userIdNumString = new String("0");
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND pwd = ? AND role = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { logonNameString,
				logonPassString, logonRoleString });
		if (cursor.moveToFirst() == true) {
			userIdNumString = cursor.getString(cursor.getColumnIndex("idNum"));
		}
		cursor.close();
		return userIdNumString;
	}

	// 修改人员密码
	public boolean modPassword(String userIdNumString, String logonPassString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "UPDATE user SET pwd = ? WHERE idNum = ?";
		sdb.execSQL(sql, new String[] { logonPassString, userIdNumString });

		return true;
	}

	// 查询科室
	public List<String> getDepartment() {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM department";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql, new String[] {});
		while (cursor.moveToNext()) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		cursor.close();
		return resList;
	}

	// 查询医生账号
	public List<String> getDoctorLogonName() {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE role = 0";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql, new String[] {});
		while (cursor.moveToNext()) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		cursor.close();
		return resList;
	}

	// 查询病人账号
	public List<String> getPatientLogonName() {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE role = 2";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql, new String[] {});
		while (cursor.moveToNext()) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		cursor.close();
		return resList;
	}

	// 查询医生信息用登录名
	public List<String> getDoctorInfoOnLogon(String doctorLogonNameString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = 0";
		List<String> resList = new ArrayList<String>();
		System.out.println(doctorLogonNameString);
		Cursor cursor = sdb.rawQuery(sql,
				new String[] { doctorLogonNameString });
		while (cursor.moveToNext()) {
			resList.add(cursor.getString(cursor.getColumnIndex("idNum")));
		}

		cursor.close();
		sql = "SELECT * FROM doctor WHERE userid = ?";
		cursor = sdb.rawQuery(sql, new String[] { resList.get(0).toString() });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
			resList.add(cursor.getString(cursor.getColumnIndex("sex")));
			resList.add(cursor.getString(cursor.getColumnIndex("pic")));
			resList.add(cursor.getString(cursor.getColumnIndex("idnumstr")));
			resList.add(cursor.getString(cursor.getColumnIndex("phone")));
			resList.add(cursor.getString(cursor.getColumnIndex("departmentid")));
			resList.add(cursor.getString(cursor.getColumnIndex("des")));
		}

		cursor.close();
		return resList;
	}

	// 查询病人信息用登录名
	public List<String> getPatientInfoOnLogon(String patientLogonNameString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = 2";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql,
				new String[] { patientLogonNameString });
		while (cursor.moveToNext()) {
			resList.add(cursor.getString(cursor.getColumnIndex("idNum")));
		}

		cursor.close();
		sql = "SELECT * FROM patient WHERE userid = ?";
		cursor = sdb.rawQuery(sql, new String[] { resList.get(0).toString() });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
			resList.add(cursor.getString(cursor.getColumnIndex("sex")));
			resList.add(cursor.getString(cursor.getColumnIndex("pic")));
			resList.add(cursor.getString(cursor.getColumnIndex("idnumstr")));
			resList.add(cursor.getString(cursor.getColumnIndex("phone")));
			resList.add(cursor.getString(cursor.getColumnIndex("departmentid")));
			resList.add(cursor.getString(cursor.getColumnIndex("des")));
		}

		cursor.close();
		return resList;
	}

	// 查询病人病历用登录名
	public List<String> getPatientCaseInfo(String patientLogonNameString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = 2";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql,
				new String[] { patientLogonNameString });
		while (cursor.moveToNext()) {
			resList.add(cursor.getString(cursor.getColumnIndex("idNum")));
		}

		cursor.close();
		sql = "SELECT * FROM patcase WHERE patientid = ?";
		cursor = sdb.rawQuery(sql, new String[] { resList.get(0).toString() });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("des")));
			resList.add(cursor.getString(cursor.getColumnIndex("doctorId")));
			resList.add(cursor.getString(cursor.getColumnIndex("departmentId")));
			resList.add(cursor.getString(cursor.getColumnIndex("bedId")));
			resList.add(cursor.getString(cursor.getColumnIndex("admission")));
			resList.add(cursor.getString(cursor.getColumnIndex("discharge")));
			resList.add(cursor.getString(cursor.getColumnIndex("allcosts")));
		}

		cursor.close();
		sql = "SELECT * FROM user WHERE idNum = ?";
		cursor = sdb.rawQuery(sql, new String[] { resList.get(2).toString() });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
		}

		cursor.close();
		return resList;
	}

	// 查询病人病历用用户id
	public List<String> getPatientCaseInfoOnUserId(String patientIdNumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		List<String> resList = new ArrayList<String>();

		String sql = "SELECT * FROM patcase WHERE patientid = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { patientIdNumString });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("des")));
			resList.add(cursor.getString(cursor.getColumnIndex("doctorId")));
			resList.add(cursor.getString(cursor.getColumnIndex("departmentId")));
			resList.add(cursor.getString(cursor.getColumnIndex("bedId")));
			resList.add(cursor.getString(cursor.getColumnIndex("admission")));
			resList.add(cursor.getString(cursor.getColumnIndex("discharge")));
			resList.add(cursor.getString(cursor.getColumnIndex("allcosts")));
		}

		cursor.close();
		sql = "SELECT * FROM user WHERE idNum = ?";
		cursor = sdb.rawQuery(sql, new String[] { resList.get(2).toString() });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
		}

		cursor.close();
		return resList;
	}

	// 添加病例
	public boolean addPatientCase(String patientLogonNameString,
			String caseDeString, String doctorLogonNameString,
			String departmentIdString, String bedIdString,
			String addmissionString, String dischargeString,
			String allcostsString) {

		String patientIdString = new String("0");
		String doctorIdString = new String("0");
		boolean mBoolean = false;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = 2";
		Cursor cursor = sdb.rawQuery(sql,
				new String[] { patientLogonNameString });
		if (cursor.moveToFirst() == true) {
			mBoolean = false;
			patientIdString = cursor.getString(cursor.getColumnIndex("idNum"));
		}
		cursor.close();

		sql = "SELECT * FROM user WHERE name = ? AND role = 0";
		cursor = sdb.rawQuery(sql, new String[] { doctorLogonNameString });
		if (cursor.moveToFirst() == true) {
			mBoolean = false;
			doctorIdString = cursor.getString(cursor.getColumnIndex("idNum"));
		}
		cursor.close();

		sql = "SELECT * FROM patcase WHERE patientid = ?";
		cursor = sdb.rawQuery(sql, new String[] { patientIdString });
		if (cursor.moveToFirst() == true) {
			mBoolean = false;
		} else {
			sql = "INSERT INTO patcase (patientid, des, doctorId, departmentId, bedId, admission, discharge, allcosts) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			sdb.execSQL(sql, new String[] { patientIdString, caseDeString,
					doctorIdString, departmentIdString, bedIdString,
					addmissionString, dischargeString, allcostsString });
			mBoolean = true;
		}

		cursor.close();
		return mBoolean;
	}

	// 修改病例
	public boolean modPatientCase(String patientLogonNameString,
			String caseDeString, String doctorLogonNameString,
			String departmentIdString, String bedIdString,
			String addmissionString, String dischargeString,
			String allcostsString) {

		String patientIdString = new String("0");
		String doctorIdString = new String("0");
		boolean mBoolean = false;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = 2";
		Cursor cursor = sdb.rawQuery(sql,
				new String[] { patientLogonNameString });
		if (cursor.moveToFirst() == true) {
			mBoolean = false;
			patientIdString = cursor.getString(cursor.getColumnIndex("idNum"));
		}
		cursor.close();

		sql = "SELECT * FROM user WHERE name = ? AND role = 0";
		cursor = sdb.rawQuery(sql, new String[] { doctorLogonNameString });
		if (cursor.moveToFirst() == true) {
			mBoolean = false;
			doctorIdString = cursor.getString(cursor.getColumnIndex("idNum"));
		}
		cursor.close();

		sql = "SELECT * FROM patcase WHERE patientid = ?";
		cursor = sdb.rawQuery(sql, new String[] { patientIdString });
		if (cursor.moveToFirst() == true) {
			sql = "DELETE FROM patcase WHERE patientId = ?";
			sdb.execSQL(sql, new String[] { patientIdString });

			sql = "INSERT INTO patcase (patientid, des, doctorId, departmentId, bedId, admission, discharge, allcosts) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			sdb.execSQL(sql, new String[] { patientIdString, caseDeString,
					doctorIdString, departmentIdString, bedIdString,
					addmissionString, dischargeString, allcostsString });
			mBoolean = true;
		} else {
			mBoolean = false;
		}

		cursor.close();
		return mBoolean;
	}

	// 添加科室
	public boolean addDepartment(String departmentNameString,
			String departmentPhoneString) {
		boolean mBoolean = false;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM department WHERE name = ?";
		Cursor cursor = sdb
				.rawQuery(sql, new String[] { departmentNameString });
		if (cursor.moveToFirst() == true) {
			mBoolean = false;
		} else {
			sql = "INSERT INTO department (name, phone) VALUES (?, ?)";
			sdb.execSQL(sql, new String[] { departmentNameString,
					departmentPhoneString });
			mBoolean = true;
		}

		cursor.close();
		return mBoolean;
	}

	// 修改科室
	public boolean modDepartment(String departmentIdNumsString,
			String departmentNameString, String departmentPhoneString) {
		boolean mBoolean = false;
		String departmentTempNameString = new String("");
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM department WHERE idNum = ?";
		Cursor cursor = sdb.rawQuery(sql,
				new String[] { departmentIdNumsString });
		if (cursor.moveToFirst() == true) {
			departmentTempNameString = cursor.getString(cursor
					.getColumnIndex("name"));
			mBoolean = true;

			if (true == departmentTempNameString
					.equalsIgnoreCase(departmentNameString)) {
				sql = "UPDATE department SET name = ? , phone = ? WHERE idNum = ?";
				sdb.execSQL(sql, new String[] { departmentNameString,
						departmentPhoneString, departmentIdNumsString });
				mBoolean = true;
			} else {
				sql = "SELECT * FROM department WHERE name = ?";
				cursor = sdb.rawQuery(sql,
						new String[] { departmentNameString });
				if (cursor.moveToFirst() == true) {
					mBoolean = false;
				} else {
					sql = "UPDATE department SET name = ? , phone = ? WHERE idNum = ?";
					sdb.execSQL(sql, new String[] { departmentNameString,
							departmentPhoneString, departmentIdNumsString });
					mBoolean = true;
				}
			}
		} else {
			mBoolean = false;
		}

		cursor.close();
		return mBoolean;
	}

	// 获取科室电话
	public String getDepartmentPhone(String departmentIdNumsString) {
		String departmentPhoneString = new String("00000000");
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM department WHERE idNum = ?";
		Cursor cursor = sdb.rawQuery(sql,
				new String[] { departmentIdNumsString });
		while (cursor.moveToNext()) {
			departmentPhoneString = cursor.getString(cursor
					.getColumnIndex("phone"));
		}
		cursor.close();
		return departmentPhoneString;
	}

	// 添加人员
	public boolean addUser(String logonNameString, String logonPassString,
			String logonRoleString) {
		Boolean mBoolean = false;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = ?";
		String[] bindArgs = { logonNameString, logonRoleString };
		Cursor cursor = sdb.rawQuery(sql, bindArgs);
		if (cursor.moveToFirst() == true) {
			mBoolean = false; // 用户名已存在
		} else {
			sql = "INSERT INTO user (name, pwd, role) VALUES (?, ?, ?)";
			bindArgs = new String[] { logonNameString, logonPassString,
					logonRoleString };
			sdb.execSQL(sql, bindArgs);
			mBoolean = true;
		}
		cursor.close();
		return mBoolean;
	}

	// 添加医生
	public boolean addDoctorInfo(String logonNameString,
			String logonRoleString, String name, String sex, String pic,
			String idnumstr, String phone, String departmentid, String des) {
		Boolean mBoolean = false;
		String idNum = "0";
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = ?";
		String[] bindArgs = { logonNameString, logonRoleString };
		Cursor cursor = sdb.rawQuery(sql, bindArgs);
		if (cursor.moveToFirst() == true) {
			idNum = cursor.getString(cursor.getColumnIndex("idNum"));
		} else {
			mBoolean = false;
		}

		sql = "INSERT INTO doctor (userid, name, sex, pic, idnumstr, phone, departmentid, des) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		bindArgs = new String[] { idNum, name, sex, pic, idnumstr, phone,
				departmentid, des };
		sdb.execSQL(sql, bindArgs);
		mBoolean = true;
		cursor.close();
		return mBoolean;
	}

	// 添加护士
	public boolean addNurseInfo(String logonNameString, String logonRoleString,
			String name, String sex, String pic, String idnumstr, String phone,
			String departmentid, String des) {
		Boolean mBoolean = false;
		String idNum = "0";
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = ?";
		String[] bindArgs = { logonNameString, logonRoleString };
		Cursor cursor = sdb.rawQuery(sql, bindArgs);
		if (cursor.moveToFirst() == true) {
			idNum = cursor.getString(cursor.getColumnIndex("idNum"));
		} else {
			mBoolean = false;
		}

		sql = "INSERT INTO nurse (userid, name, sex, pic, idnumstr, phone, departmentid, des) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		bindArgs = new String[] { idNum, name, sex, pic, idnumstr, phone,
				departmentid, des };
		sdb.execSQL(sql, bindArgs);
		mBoolean = true;
		cursor.close();
		return mBoolean;
	}

	// 添加病人
	public boolean addPatientInfo(String logonNameString,
			String logonRoleString, String name, String sex, String pic,
			String idnumstr, String phone, String departmentid, String des) {
		Boolean mBoolean = false;
		String idNum = "0";
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = ?";
		String[] bindArgs = { logonNameString, logonRoleString };
		Cursor cursor = sdb.rawQuery(sql, bindArgs);
		if (cursor.moveToFirst() == true) {
			idNum = cursor.getString(cursor.getColumnIndex("idNum"));
		} else {
			mBoolean = false;
		}

		sql = "INSERT INTO patient (userid, name, sex, pic, idnumstr, phone, departmentid, des) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		bindArgs = new String[] { idNum, name, sex, pic, idnumstr, phone,
				departmentid, des };
		sdb.execSQL(sql, bindArgs);
		mBoolean = true;
		cursor.close();
		return mBoolean;
	}

	// 删除人员
	public boolean delUser(String userIdNumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "DELETE FROM user WHERE idNum = ?";
		String[] bindArgs = { userIdNumString };
		sdb.execSQL(sql, bindArgs);
		return true; // 删除成功
	}

	// 删除医生
	public boolean delDoctorInfo(String userIdNumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "DELETE FROM doctor WHERE userid = ?";
		String[] bindArgs = { userIdNumString };
		sdb.execSQL(sql, bindArgs);
		return true;
	}

	// 删除护士
	public boolean delNurseInfo(String userIdNumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "DELETE FROM nurse WHERE userid = ?";
		String[] bindArgs = { userIdNumString };
		sdb.execSQL(sql, bindArgs);
		return true;
	}

	// 删除病人
	public boolean delPatientInfo(String userIdNumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "DELETE FROM patient WHERE userid = ?";
		String[] bindArgs = { userIdNumString };
		sdb.execSQL(sql, bindArgs);
		return true;
	}

	// 用户名获取人员id
	public String getUserIdOnName(String logonNameString, String logonRoleString) {
		String userIdNumString = new String("0");
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM user WHERE name = ? AND role = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { logonNameString,
				logonRoleString });
		if (cursor.moveToFirst() == true) {
			userIdNumString = cursor.getString(cursor.getColumnIndex("idNum"));
		}
		cursor.close();
		return userIdNumString;
	}

	// 身份证获取人员id
	public String getUserIdOnIdNum(String idnumString, String logonRoleString) {
		String userIdNumString = new String("0");
		String sql = new String("0");
		Cursor cursor;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		switch (Integer.parseInt(logonRoleString)) {
		case 0:
			sql = "SELECT * FROM doctor WHERE idnumstr = ?";
			cursor = sdb.rawQuery(sql, new String[] { idnumString });
			if (cursor.moveToFirst() == true) {
				userIdNumString = cursor.getString(cursor
						.getColumnIndex("userid"));
				cursor.close();
			}
			break;
		case 1:
			sql = "SELECT * FROM nurse WHERE idnumstr = ?";
			cursor = sdb.rawQuery(sql, new String[] { idnumString });
			if (cursor.moveToFirst() == true) {
				userIdNumString = cursor.getString(cursor
						.getColumnIndex("userid"));
				cursor.close();
			}
			break;
		case 2:
			sql = "SELECT * FROM patient WHERE idnumstr = ?";
			cursor = sdb.rawQuery(sql, new String[] { idnumString });
			if (cursor.moveToFirst() == true) {
				userIdNumString = cursor.getString(cursor
						.getColumnIndex("userid"));
				cursor.close();
			}
			break;
		default:
			break;
		}

		return userIdNumString;
	}

	// 获取医生信息
	public List<String> getDoctorInfo(String idnumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM doctor WHERE userid = ?";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql, new String[] { idnumString });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
			resList.add(cursor.getString(cursor.getColumnIndex("sex")));
			resList.add(cursor.getString(cursor.getColumnIndex("pic")));
			resList.add(cursor.getString(cursor.getColumnIndex("idnumstr")));
			resList.add(cursor.getString(cursor.getColumnIndex("phone")));
			resList.add(cursor.getString(cursor.getColumnIndex("departmentid")));
			resList.add(cursor.getString(cursor.getColumnIndex("des")));
			cursor.close();
		}

		sql = "SELECT * FROM user WHERE idNum = ?";
		cursor = sdb.rawQuery(sql, new String[] { idnumString });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
			cursor.close();
		}
		return resList;
	}

	// 获取护士信息
	public List<String> getNurseInfo(String idnumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM nurse WHERE userid = ?";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql, new String[] { idnumString });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
			resList.add(cursor.getString(cursor.getColumnIndex("sex")));
			resList.add(cursor.getString(cursor.getColumnIndex("pic")));
			resList.add(cursor.getString(cursor.getColumnIndex("idnumstr")));
			resList.add(cursor.getString(cursor.getColumnIndex("phone")));
			resList.add(cursor.getString(cursor.getColumnIndex("departmentid")));
			resList.add(cursor.getString(cursor.getColumnIndex("des")));
			cursor.close();
		}

		sql = "SELECT * FROM user WHERE idNum = ?";
		cursor = sdb.rawQuery(sql, new String[] { idnumString });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
			cursor.close();
		}
		return resList;
	}

	// 获取病人信息
	public List<String> getPatientInfo(String idnumString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM patient WHERE userid = ?";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql, new String[] { idnumString });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
			resList.add(cursor.getString(cursor.getColumnIndex("sex")));
			resList.add(cursor.getString(cursor.getColumnIndex("pic")));
			resList.add(cursor.getString(cursor.getColumnIndex("idnumstr")));
			resList.add(cursor.getString(cursor.getColumnIndex("phone")));
			resList.add(cursor.getString(cursor.getColumnIndex("departmentid")));
			resList.add(cursor.getString(cursor.getColumnIndex("des")));
			cursor.close();
		}

		sql = "SELECT * FROM user WHERE idNum = ?";
		cursor = sdb.rawQuery(sql, new String[] { idnumString });
		if (cursor.moveToFirst() == true) {
			resList.add(cursor.getString(cursor.getColumnIndex("name")));
			cursor.close();
		}
		return resList;
	}

	// 修改人员
	public boolean modUser(String userIdNumString, String logonNameString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "UPDATE user SET name = ? WHERE idNum = ?";
		String[] bindArgs = { logonNameString, userIdNumString };
		sdb.execSQL(sql, bindArgs);
		return true; // 修改成功
	}

	// 查询药物
	public List<String> getDrug(String drugCodeString) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM Drug WHERE drugcode = ?";
		List<String> resList = new ArrayList<String>();
		Cursor cursor = sdb.rawQuery(sql, new String[] { drugCodeString });
		while (cursor.moveToNext()) {
			resList.add(cursor.getString(cursor.getColumnIndex("drugname")));
			resList.add(cursor.getString(cursor.getColumnIndex("des")));
			resList.add(cursor.getString(cursor.getColumnIndex("drugunit")));
			resList.add(cursor.getString(cursor.getColumnIndex("drugprice")));
			resList.add(cursor.getString(cursor.getColumnIndex("stocknum")));
			resList.add(cursor.getString(cursor.getColumnIndex("idNum")));
		}
		cursor.close();
		return resList;
	}

	// 添加药物
	public boolean addDrug(String drugCodeString, String drugNameString,
			String drugDesString, String drugUnitString,
			String drugPriceString, String stockNumString) {
		boolean mBoolean = false;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM Drug WHERE drugcode = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { drugCodeString });
		if (cursor.moveToFirst() == true) {
			mBoolean = false;
		} else {
			sql = "INSERT INTO Drug (drugcode, drugname, des, drugunit, drugprice, stocknum) VALUES (?, ?, ?, ?, ?, ?)";
			sdb.execSQL(sql, new String[] { drugCodeString, drugNameString,
					drugDesString, drugUnitString, drugPriceString,
					stockNumString });
			mBoolean = true;
		}

		cursor.close();
		return mBoolean;
	}

	// 修改药物
	public boolean modDrug(String drugIdNumString, String drugCodeString,
			String drugNameString, String drugDesString, String drugUnitString,
			String drugPriceString, String stockNumString) {
		boolean mBoolean = false;
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "UPDATE Drug SET drugcode = ? , drugname = ? , des = ? , drugunit = ? , drugprice = ? , stocknum = ? WHERE idNum = ?";
		sdb.execSQL(sql, new String[] { drugCodeString, drugNameString,
				drugDesString, drugUnitString, drugPriceString, stockNumString,
				drugIdNumString });
		mBoolean = true;

		return mBoolean;
	}

	// 查询病人从医生
	public List<List<String>> getPatientInfoOnDoctor(String doctorIdNumString) {
		String patientIdString = new String("0");
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM patcase WHERE doctorId = ?";
		List<List<String>> resList = new ArrayList<List<String>>();
		List<String> patList = new ArrayList<String>();
		Cursor patCursor = null;
		Cursor resCursor = sdb
				.rawQuery(sql, new String[] { doctorIdNumString });
		while (resCursor.moveToNext()) {
			patList = new ArrayList<String>();
			patientIdString = resCursor.getString(resCursor
					.getColumnIndex("patientid"));
			patList.add(resCursor.getString(resCursor.getColumnIndex("des")));

			// 病人登录名
			sql = "SELECT * FROM user WHERE idNum = ?";
			patCursor = sdb.rawQuery(sql, new String[] { patientIdString });
			if (patCursor.moveToFirst() == true) {
				patList.add(patCursor.getString(patCursor
						.getColumnIndex("name")));
				patCursor.close();
			} else {
				break;
			}

			// 病人名字以及头像
			sql = "SELECT * FROM patient WHERE userid = ?";
			patCursor = sdb.rawQuery(sql, new String[] { patientIdString });
			if (patCursor.moveToFirst() == true) {
				patList.add(patCursor.getString(patCursor
						.getColumnIndex("name")));
				patList.add(patCursor.getString(patCursor.getColumnIndex("pic")));
				patCursor.close();
			} else {
				break;
			}

			// 医生登录名
			sql = "SELECT * FROM user WHERE idNum = ?";
			patCursor = sdb.rawQuery(sql, new String[] { doctorIdNumString });
			if (patCursor.moveToFirst() == true) {
				patList.add(patCursor.getString(patCursor
						.getColumnIndex("name")));
				patCursor.close();
			} else {
				break;
			}

			resList.add(patList);
		}

		resCursor.close();
		return resList;
	}

	// 查询病人从护士
	public List<List<String>> getPatientInfoOnNurse(String nurseIdNumString) {
		String patientIdString = new String("0");
		String departmentIdString = new String("0");
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		String sql = "SELECT * FROM nurse WHERE idnumstr = ?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { nurseIdNumString });
		if (cursor.moveToFirst() == true) {
			departmentIdString = cursor.getString(cursor
					.getColumnIndex("departmentid"));
		}
		cursor.close();

		sql = "SELECT * FROM patcase WHERE departmentId = ?";
		List<List<String>> resList = new ArrayList<List<String>>();
		List<String> patList = new ArrayList<String>();
		Cursor patCursor = null;
		Cursor resCursor = sdb.rawQuery(sql,
				new String[] { departmentIdString });
		while (resCursor.moveToNext()) {
			patList = new ArrayList<String>();
			patientIdString = resCursor.getString(resCursor
					.getColumnIndex("patientid"));
			patList.add(resCursor.getString(resCursor.getColumnIndex("des")));

			// 病人登录名
			sql = "SELECT * FROM user WHERE idNum = ?";
			patCursor = sdb.rawQuery(sql, new String[] { patientIdString });
			if (patCursor.moveToFirst() == true) {
				patList.add(patCursor.getString(patCursor
						.getColumnIndex("name")));
				patCursor.close();
			} else {
				break;
			}

			// 病人名字以及头像
			sql = "SELECT * FROM patient WHERE userid = ?";
			patCursor = sdb.rawQuery(sql, new String[] { patientIdString });
			if (patCursor.moveToFirst() == true) {
				patList.add(patCursor.getString(patCursor
						.getColumnIndex("name")));
				patList.add(patCursor.getString(patCursor.getColumnIndex("pic")));
				patCursor.close();
			} else {
				break;
			}

			resList.add(patList);
		}

		resCursor.close();
		return resList;
	}

	// 数据库帮助类
	public class HospitalSqliteHelper extends SQLiteOpenHelper {

		public HospitalSqliteHelper(Context context) {
			// CursorFactory设置为null,使用默认值
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// 数据库第一次被创建时onCreate会被调用
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_USER);
			db.execSQL(INSERT_ADMIN_USER);
			db.execSQL(INSERT_DOCTOR_USER);
			db.execSQL(INSERT_NURSE_USER);
			db.execSQL(INSERT_PATIENT_USER);

			db.execSQL(CREATE_TABLE_DEPARTMENT);
			db.execSQL(INSERT_INFO_DEPARTMENT01);
			db.execSQL(INSERT_INFO_DEPARTMENT02);
			db.execSQL(INSERT_INFO_DEPARTMENT03);
			db.execSQL(INSERT_INFO_DEPARTMENT04);
			db.execSQL(INSERT_INFO_DEPARTMENT05);
			db.execSQL(INSERT_INFO_DEPARTMENT06);
			db.execSQL(INSERT_INFO_DEPARTMENT07);
			db.execSQL(INSERT_INFO_DEPARTMENT08);
			db.execSQL(INSERT_INFO_DEPARTMENT09);
			db.execSQL(INSERT_INFO_DEPARTMENT10);
			db.execSQL(INSERT_INFO_DEPARTMENT11);
			db.execSQL(INSERT_INFO_DEPARTMENT12);
			db.execSQL(INSERT_INFO_DEPARTMENT13);
			db.execSQL(INSERT_INFO_DEPARTMENT14);
			db.execSQL(INSERT_INFO_DEPARTMENT15);
			db.execSQL(INSERT_INFO_DEPARTMENT16);
			db.execSQL(INSERT_INFO_DEPARTMENT17);
			db.execSQL(INSERT_INFO_DEPARTMENT18);
			db.execSQL(INSERT_INFO_DEPARTMENT19);
			db.execSQL(INSERT_INFO_DEPARTMENT20);
			db.execSQL(INSERT_INFO_DEPARTMENT21);
			db.execSQL(INSERT_INFO_DEPARTMENT22);
			db.execSQL(INSERT_INFO_DEPARTMENT23);
			db.execSQL(INSERT_INFO_DEPARTMENT24);
			db.execSQL(INSERT_INFO_DEPARTMENT25);
			db.execSQL(INSERT_INFO_DEPARTMENT26);
			db.execSQL(INSERT_INFO_DEPARTMENT27);
			db.execSQL(INSERT_INFO_DEPARTMENT28);
			db.execSQL(INSERT_INFO_DEPARTMENT29);
			db.execSQL(INSERT_INFO_DEPARTMENT30);
			db.execSQL(INSERT_INFO_DEPARTMENT31);
			db.execSQL(INSERT_INFO_DEPARTMENT32);
			db.execSQL(INSERT_INFO_DEPARTMENT33);
			db.execSQL(INSERT_INFO_DEPARTMENT34);

			db.execSQL(CREATE_TABLE_DOCTOR);
			db.execSQL(INSERT_DEFAULT_DOCTOR);

			db.execSQL(CREATE_TABLE_NURSE);
			db.execSQL(INSERT_DEFAULT_NURSE);

			db.execSQL(CREATE_TABLE_PATIENT);
			db.execSQL(INSERT_DEFAULT_PATIENT);

			db.execSQL(CREATE_TABLE_PATCASE);
			db.execSQL(INSERT_DEFAULT_PATCASE);

			db.execSQL(CREATE_TABLE_Drug);
			db.execSQL(INSERT_DEFAULT_Drug);

		}

		// 如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATCASE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_Drug);
			onCreate(db);
		}

	}
}