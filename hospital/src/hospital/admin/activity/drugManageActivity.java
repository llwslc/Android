package hospital.admin.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class drugManageActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;
	private String drugIdNumString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drugmanage);
		// 初始化数据库
		m_HospitalSqlite = new HospitalSqlite(this);

		// 初始化药物编号
		drugIdNumString = new String("0");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// 添加菜单
		int base = Menu.FIRST;
		int i = 0;

		menu.add(base, base + (i++), Menu.NONE, "添加");
		menu.add(base, base + (i++), Menu.NONE, "修改");
		menu.add(base, base + (i++), Menu.NONE, "按编号查询");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// 菜单选择
		String drugCodeString = ((EditText) findViewById(R.id.drugCode_edit))
				.getText().toString().trim();
		String drugNameString = ((EditText) findViewById(R.id.drugName_edit))
				.getText().toString().trim();
		String drugDesString = ((EditText) findViewById(R.id.drugDes_edit))
				.getText().toString().trim();
		String drugUnitString = ((EditText) findViewById(R.id.drugUnit_edit))
				.getText().toString().trim();
		String drugPriceString = ((EditText) findViewById(R.id.drugPrice_edit))
				.getText().toString().trim();
		String stockNumString = ((EditText) findViewById(R.id.stockNum_edit))
				.getText().toString().trim();
		List<String> drugInfoList = new ArrayList<String>();
		int listIndexInt = 0;
		boolean successFlag = false;

		if (true == drugCodeString.equals("")) {
			Toast.makeText(getApplicationContext(), "请填写药品编号",
					Toast.LENGTH_SHORT).show();
			return false;
		}

		switch (item.getItemId()) {
		case 1:
			successFlag = m_HospitalSqlite.addDrug(drugCodeString,
					drugNameString, drugDesString, drugUnitString,
					drugPriceString, stockNumString);
			if (true == successFlag) {
				Toast.makeText(getApplicationContext(), "添加成功",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "药物编号重复",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case 2:
			successFlag = m_HospitalSqlite.modDrug(drugIdNumString,
					drugCodeString, drugNameString, drugDesString,
					drugUnitString, drugPriceString, stockNumString);
			if (true == successFlag) {
				Toast.makeText(getApplicationContext(), "修改成功",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "修改失败",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case 3:
			drugInfoList = m_HospitalSqlite.getDrug(drugCodeString);
			if (0 == drugInfoList.size()) {
				Toast.makeText(getApplicationContext(), "不存在该药品",
						Toast.LENGTH_SHORT).show();
			} else {
				((EditText) findViewById(R.id.drugName_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				((EditText) findViewById(R.id.drugDes_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				((EditText) findViewById(R.id.drugUnit_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				((EditText) findViewById(R.id.drugPrice_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				((EditText) findViewById(R.id.stockNum_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				drugInfoList.get(listIndexInt++).toString();
				Toast.makeText(getApplicationContext(), "查询成功",
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
