package hospital.admin.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class departmentManageActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;
	private Spinner departmentSpinner;
	private String departmentIdNumsString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.departmentmanage);
		// 初始化数据库
		m_HospitalSqlite = new HospitalSqlite(this);

		// 初始化科室列表
		departmentIdNumsString = new String("0");
		initDepartmentSpinner();
		departmentSpinner = (Spinner) findViewById(R.id.departmentManage_spinner);

		// 科室列表事件
		departmentSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						departmentIdNumsString = departmentSpinner
								.getSelectedItemPosition() + 1 + "";

						((EditText) findViewById(R.id.departmentManageName_edit))
								.setText(departmentSpinner.getSelectedItem()
										.toString());

						((EditText) findViewById(R.id.departmentManagePhone_edit)).setText(m_HospitalSqlite
								.getDepartmentPhone(departmentIdNumsString));
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
		List<String> departmentList = new ArrayList<String>();
		departmentList = m_HospitalSqlite.getDepartment();

		ArrayAdapter<String> arrayAdapter;
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, departmentList);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) findViewById(R.id.departmentManage_spinner))
				.setAdapter(arrayAdapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// 菜单选择
		String departmentNameString = ((EditText) findViewById(R.id.departmentManageName_edit))
				.getText().toString().trim();
		String departmentPhoneString = ((EditText) findViewById(R.id.departmentManagePhone_edit))
				.getText().toString().trim();
		boolean successFlag = false;

		if (true == departmentNameString.equals("")) {
			Toast.makeText(getApplicationContext(), "请填写科室名",
					Toast.LENGTH_SHORT).show();
			return false;
		}

		switch (item.getItemId()) {
		case 1:
			successFlag = m_HospitalSqlite.addDepartment(departmentNameString,
					departmentPhoneString);
			if (true == successFlag) {
				Toast.makeText(getApplicationContext(), "添加成功",
						Toast.LENGTH_SHORT).show();
				initDepartmentSpinner();
			} else {
				Toast.makeText(getApplicationContext(), "科室名称重复",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case 2:
			successFlag = m_HospitalSqlite.modDepartment(
					departmentIdNumsString, departmentNameString,
					departmentPhoneString);
			if (true == successFlag) {
				Toast.makeText(getApplicationContext(), "修改成功",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "科室名称重复",
						Toast.LENGTH_SHORT).show();
			}
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