package hospital.patient.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class patientDoctorActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;
	private ImageView avatarImageView;
	private File avatarFile;
	private String userIdNumString;
	private String patientIdNumString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usermanage);
		// ��ʼ�����ݿ�
		m_HospitalSqlite = new HospitalSqlite(this);

		// ��ʼ�������б�
		initDepartmentSpinner();

		// ��ʼ��ͷ��ť
		avatarImageView = ((ImageView) findViewById(R.id.avatarPic_view));

		// ��ʼ��ͷ��·��
		avatarFile = new File(getResources().getString(
				R.string.avatarDefaultPNG));

		avatarImageView.setImageBitmap(BitmapFactory.decodeFile(avatarFile
				.getPath()));

		// ��ʼ����Աid
		userIdNumString = new String("0");
		patientIdNumString = (String) this.getIntent().getSerializableExtra(
				"userIdNumString");

		fillDoctorInfo();

	}

	public void fillDoctorInfo() {
		// ���ҽ����Ϣ
		List<String> userInfoList = new ArrayList<String>();
		int listIndexInt = 0;
		userIdNumString = (m_HospitalSqlite
				.getPatientCaseInfoOnUserId(patientIdNumString)).get(1)
				.toString();
		if (true == userIdNumString.equals("0")) {
			Toast.makeText(getApplicationContext(), "���û�������",
					Toast.LENGTH_SHORT).show();
			return;
		}

		userInfoList = m_HospitalSqlite.getDoctorInfo(userIdNumString);

		((Spinner) findViewById(R.id.userManage_spinner)).setSelection(0);
		((EditText) findViewById(R.id.userName_edit)).setText(userInfoList.get(
				listIndexInt++).toString());
		((Spinner) findViewById(R.id.userSex_spinner)).setSelection(Integer
				.parseInt(userInfoList.get(listIndexInt++).toString()));
		avatarImageView.setImageBitmap(BitmapFactory.decodeFile((new File(
				userInfoList.get(listIndexInt++).toString())).getPath()));
		((EditText) findViewById(R.id.userIdNum_edit)).setText(userInfoList
				.get(listIndexInt++).toString());
		((EditText) findViewById(R.id.userPhone_edit)).setText(userInfoList
				.get(listIndexInt++).toString());
		((Spinner) findViewById(R.id.userDepartment_spinner))
				.setSelection(Integer.parseInt(userInfoList.get(listIndexInt++)
						.toString()));
		((EditText) findViewById(R.id.userDes_edit)).setText(userInfoList.get(
				listIndexInt++).toString());
		((EditText) findViewById(R.id.userLogonName_edit)).setText(userInfoList
				.get(listIndexInt++).toString());
		listIndexInt = 0;
	}

	public void initDepartmentSpinner() {
		// ��ʼ�������б�
		List<String> departmentList = new ArrayList<String>();
		departmentList = m_HospitalSqlite.getDepartment();

		ArrayAdapter<String> arrayAdapter;
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, departmentList);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) findViewById(R.id.userDepartment_spinner))
				.setAdapter(arrayAdapter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (m_HospitalSqlite != null) {
			m_HospitalSqlite.close();
			m_HospitalSqlite = null;
		}
	}

}
