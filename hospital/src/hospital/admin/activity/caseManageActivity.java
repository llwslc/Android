package hospital.admin.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class caseManageActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;
	private Spinner patientLogonNameSpinner;
	private Spinner patientDoctorNameSpinner;
	private Spinner patientDepartmentNameSpinner;
	private DatePicker preDatePicker;
	private DatePicker nxtDatePicker;
	private Integer yearInteger;
	private Integer monthInteger;
	private Integer dayInteger;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.casermanage);

		// 初始化数据库
		m_HospitalSqlite = new HospitalSqlite(this);

		// 初始化时间拾取器
		preDatePicker = (DatePicker) findViewById(R.id.casePatientAdmission_date);
		nxtDatePicker = (DatePicker) findViewById(R.id.casePatientDischarge_date);

		// 初始化下拉列表
		initDepartmentSpinner();

		patientLogonNameSpinner = (Spinner) findViewById(R.id.casePatientLogonName_spinner);
		patientDoctorNameSpinner = (Spinner) findViewById(R.id.casePatientDoctor_spinner);
		patientDepartmentNameSpinner = (Spinner) findViewById(R.id.casePatientDepartment_spinner);

		// 下拉列表监听事件
		patientLogonNameSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						((EditText) findViewById(R.id.casePatientName_edit)).setText((m_HospitalSqlite
								.getPatientInfoOnLogon(patientLogonNameSpinner
										.getSelectedItem().toString())).get(1)
								.toString());

						List<String> stringList = new ArrayList<String>();
						stringList = m_HospitalSqlite
								.getPatientCaseInfo(patientLogonNameSpinner
										.getSelectedItem().toString());

						java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
								"yyyy-MM-dd");
						java.util.Date currDate = new java.util.Date();

						if (1 == stringList.size()) {
							// NULL
						} else {
							((EditText) findViewById(R.id.casePatientDes_edit))
									.setText(stringList.get(1).toString());
							for (int j = 0; j < patientDoctorNameSpinner
									.getCount(); j++) {
								if (true == patientDoctorNameSpinner
										.getItemAtPosition(j)
										.toString()
										.equalsIgnoreCase(
												stringList.get(8).toString())) {
									patientDoctorNameSpinner.setSelection(j);
									break;
								}
							}
							patientDepartmentNameSpinner.setSelection(Integer
									.valueOf(stringList.get(3).toString()));
							((EditText) findViewById(R.id.casePatientBed_edit))
									.setText(stringList.get(4).toString());

							try {
								currDate = formatter.parse(stringList.get(5)
										.toString());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							yearInteger = currDate.getYear() + 1900;
							monthInteger = currDate.getMonth() + 1;
							dayInteger = currDate.getDate();
							System.out.println(yearInteger + "." + monthInteger
									+ "." + dayInteger);
							preDatePicker.updateDate(yearInteger, monthInteger,
									dayInteger);

							try {
								currDate = formatter.parse(stringList.get(6)
										.toString());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							yearInteger = currDate.getYear() + 1900;
							monthInteger = currDate.getMonth() + 1;
							dayInteger = currDate.getDate();
							nxtDatePicker.updateDate(yearInteger, monthInteger,
									dayInteger);

							((EditText) findViewById(R.id.casePatientCost_edit))
									.setText(stringList.get(7).toString());
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		patientDoctorNameSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						((EditText) findViewById(R.id.casePatientDoctorName_edit)).setText((m_HospitalSqlite
								.getDoctorInfoOnLogon(patientDoctorNameSpinner
										.getSelectedItem().toString())).get(1)
								.toString());
						patientDepartmentNameSpinner.setSelection(Integer
								.valueOf((m_HospitalSqlite
										.getDoctorInfoOnLogon(
												patientDoctorNameSpinner
														.getSelectedItem()
														.toString()).get(6)
										.toString())));
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		patientDepartmentNameSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// 添加菜单
		int base = Menu.FIRST;
		int i = 0;

		menu.add(base, base + (i++), Menu.NONE, "添加");
		menu.add(base, base + (i++), Menu.NONE, "修改");

		return true;
	}

	public void initDepartmentSpinner() {
		// 初始化下拉列表
		List<String> stringList = new ArrayList<String>();
		ArrayAdapter<String> arrayAdapter;

		stringList = m_HospitalSqlite.getPatientLogonName();
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, stringList);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) findViewById(R.id.casePatientLogonName_spinner))
				.setAdapter(arrayAdapter);

		stringList = m_HospitalSqlite.getDoctorLogonName();
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, stringList);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) findViewById(R.id.casePatientDoctor_spinner))
				.setAdapter(arrayAdapter);

		stringList = m_HospitalSqlite.getDepartment();
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, stringList);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) findViewById(R.id.casePatientDepartment_spinner))
				.setAdapter(arrayAdapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// 菜单选择
		String patientLogonNameString = patientLogonNameSpinner
				.getSelectedItem().toString();
		String caseDeString = ((EditText) findViewById(R.id.casePatientDes_edit))
				.getText().toString().trim();
		String doctorLogonNameString = patientDoctorNameSpinner
				.getSelectedItem().toString();
		String departmentIdString = patientDepartmentNameSpinner
				.getSelectedItemPosition() + "";
		String bedIdString = ((EditText) findViewById(R.id.casePatientBed_edit))
				.getText().toString().trim();
		String admissionString = preDatePicker.getYear() + "-"
				+ preDatePicker.getMonth() + "-"
				+ preDatePicker.getDayOfMonth();
		String dischargeString = nxtDatePicker.getYear() + "-"
				+ nxtDatePicker.getMonth() + "-"
				+ nxtDatePicker.getDayOfMonth();
		String allcostsString = ((EditText) findViewById(R.id.casePatientCost_edit))
				.getText().toString().trim();
		boolean successFlag = false;

		if (true == caseDeString.equals("")) {
			Toast.makeText(getApplicationContext(), "请填写症状描述",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if (true == bedIdString.equals("")) {
			Toast.makeText(getApplicationContext(), "请填写床位", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		switch (item.getItemId()) {
		case 1:
			successFlag = m_HospitalSqlite.addPatientCase(
					patientLogonNameString, caseDeString,
					doctorLogonNameString, departmentIdString, bedIdString,
					admissionString, dischargeString, allcostsString);
			if (true == successFlag) {
				Toast.makeText(getApplicationContext(), "添加病历成功",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "病历已存在",
						Toast.LENGTH_SHORT).show();
			}
			successFlag = false;
			break;
		case 2:
			successFlag = m_HospitalSqlite.modPatientCase(
					patientLogonNameString, caseDeString,
					doctorLogonNameString, departmentIdString, bedIdString,
					admissionString, dischargeString, allcostsString);
			if (true == successFlag) {
				Toast.makeText(getApplicationContext(), "修改病历成功",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "修改失败",
						Toast.LENGTH_SHORT).show();
			}
			successFlag = false;
			break;
		default:
			break;
		}

		return true;
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
