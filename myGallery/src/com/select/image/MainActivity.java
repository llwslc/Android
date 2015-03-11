package com.select.image;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.gallery.R;
import com.sqlite.db.mSqlite;

public class MainActivity extends Activity {
	String imageString;
	private int window_width, window_height;// �ؼ����
	private DragImageView dragImageView;// �Զ���ؼ�
	private int state_height;// ״̬���ĸ߶�
	private mSqlite m_Sqlite;
	private String userIdNumString;

	private Matrix matrix;

	private int rotateInt;

	private ViewTreeObserver viewTreeObserver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.imageselect);

		// ��ʼ��ͼƬ·��
		imageString = (String) this.getIntent().getSerializableExtra(
				"imageString");

		// ��ʼ�����ݿ�
		m_Sqlite = new mSqlite(this);

		// ��ʼ����Աid
		userIdNumString = (String) this.getIntent().getSerializableExtra(
				"userIdNumString");

		// ��ʼ����ת
		rotateInt = 0;

		/** ��ȡ��Ҋ����߶� **/
		WindowManager manager = getWindowManager();
		window_width = manager.getDefaultDisplay().getWidth();
		window_height = manager.getDefaultDisplay().getHeight();

		dragImageView = (DragImageView) findViewById(R.id.div_main);
		// Bitmap bmp = BitmapUtil.ReadBitmapById(this, R.drawable.huoying,
		// window_width, window_height);
		Bitmap bitmap = BitmapFactory.decodeFile(imageString);
		// ����ͼƬ
		dragImageView.setImageBitmap(bitmap);
		dragImageView.setmActivity(this);// ע��Activity.
		/** ����״̬���߶� **/
		viewTreeObserver = dragImageView.getViewTreeObserver();
		viewTreeObserver
				.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						if (state_height == 0) {
							// ��ȡ״�����߶�
							Rect frame = new Rect();
							getWindow().getDecorView()
									.getWindowVisibleDisplayFrame(frame);
							state_height = frame.top;
							dragImageView.setScreen_H(window_height
									- state_height);
							dragImageView.setScreen_W(window_width);
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// ��Ӳ˵�
		int base = Menu.FIRST;
		int i = 0;

		menu.add(base, base + (i++), Menu.NONE, "ɾ��ͼƬ");
		menu.add(base, base + (i++), Menu.NONE, "������ת");
		menu.add(base, base + (i++), Menu.NONE, "�����ת");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// �˵�ѡ��
		Bitmap bitmap;
		switch (item.getItemId()) {
		case 1: // ɾ��ͼƬ
			boolean delFlag = false;
			delFlag = m_Sqlite.delPicPath(userIdNumString, imageString);

			if (true == delFlag) {
				setResult(RESULT_OK);
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "ɾ��ʧ��",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case 2: // ������ת
			rotateInt++;
			matrix = new Matrix(); // Matrix Ϊ import android.graphics.Matrix;
			matrix.setRotate(rotateInt * 90.0f); // ��matrix����������ת�Ƕ�

			bitmap = BitmapFactory.decodeFile(imageString);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			// ����ͼƬ
			dragImageView.setImageBitmap(bitmap);
			break;
		case 3: // �����ת
			rotateInt--;
			matrix = new Matrix(); // Matrix Ϊ import android.graphics.Matrix;
			matrix.setRotate(rotateInt * 90.0f); // ��matrix����������ת�Ƕ�

			bitmap = BitmapFactory.decodeFile(imageString);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			// ����ͼƬ
			dragImageView.setImageBitmap(bitmap);
			break;
		default:
			Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return true;
	}

	/**
	 * ��ȡ������Դ��ͼƬ
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap ReadBitmapById(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// ��ȡ��ԴͼƬ
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (m_Sqlite != null) {
			m_Sqlite.close();
			m_Sqlite = null;
		}
	}
}