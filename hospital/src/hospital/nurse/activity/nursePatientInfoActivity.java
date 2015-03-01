package hospital.nurse.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class nursePatientInfoActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;
	private Spinner patientLogonNameSpinner;
	private Spinner patientDoctorNameSpinner;
	private Spinner patientDepartmentNameSpinner;
	private DatePicker preDatePicker;
	private DatePicker nxtDatePicker;
	private Integer yearInteger;
	private Integer monthInteger;
	private Integer dayInteger;

	private String patientLogonNameString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.casermanage);

		// 初始化数据库
		m_HospitalSqlite = new HospitalSqlite(this);

		// 初始化登录名
		patientLogonNameString = (String) this.getIntent()
				.getSerializableExtra("patientLogonNameString");

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

	public void initDepartmentSpinner() {
		// 初始化下拉列表
		List<String> stringList = new ArrayList<String>();
		ArrayAdapter<String> arrayAdapter;

		stringList.add(patientLogonNameString);
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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (m_HospitalSqlite != null) {
			m_HospitalSqlite.close();
			m_HospitalSqlite = null;
		}
	}
}
