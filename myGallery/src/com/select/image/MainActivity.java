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
	private int window_width, window_height;// 控件宽度
	private DragImageView dragImageView;// 自定义控件
	private int state_height;// 状态栏的高度
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

		// 初始化图片路径
		imageString = (String) this.getIntent().getSerializableExtra(
				"imageString");

		// 初始化数据库
		m_Sqlite = new mSqlite(this);

		// 初始化人员id
		userIdNumString = (String) this.getIntent().getSerializableExtra(
				"userIdNumString");

		// 初始化旋转
		rotateInt = 0;

		/** 获取可見区域高度 **/
		WindowManager manager = getWindowManager();
		window_width = manager.getDefaultDisplay().getWidth();
		window_height = manager.getDefaultDisplay().getHeight();

		dragImageView = (DragImageView) findViewById(R.id.div_main);
		// Bitmap bmp = BitmapUtil.ReadBitmapById(this, R.drawable.huoying,
		// window_width, window_height);
		Bitmap bitmap = BitmapFactory.decodeFile(imageString);
		// 设置图片
		dragImageView.setImageBitmap(bitmap);
		dragImageView.setmActivity(this);// 注入Activity.
		/** 测量状态栏高度 **/
		viewTreeObserver = dragImageView.getViewTreeObserver();
		viewTreeObserver
				.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						if (state_height == 0) {
							// 获取状况栏高度
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
		// 添加菜单
		int base = Menu.FIRST;
		int i = 0;

		menu.add(base, base + (i++), Menu.NONE, "删除图片");
		menu.add(base, base + (i++), Menu.NONE, "向左旋转");
		menu.add(base, base + (i++), Menu.NONE, "向后旋转");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// 菜单选择
		Bitmap bitmap;
		switch (item.getItemId()) {
		case 1: // 删除图片
			boolean delFlag = false;
			delFlag = m_Sqlite.delPicPath(userIdNumString, imageString);

			if (true == delFlag) {
				setResult(RESULT_OK);
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "删除失败",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case 2: // 向左旋转
			rotateInt++;
			matrix = new Matrix(); // Matrix 为 import android.graphics.Matrix;
			matrix.setRotate(rotateInt * 90.0f); // 对matrix对象设置旋转角度

			bitmap = BitmapFactory.decodeFile(imageString);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			// 设置图片
			dragImageView.setImageBitmap(bitmap);
			break;
		case 3: // 向后旋转
			rotateInt--;
			matrix = new Matrix(); // Matrix 为 import android.graphics.Matrix;
			matrix.setRotate(rotateInt * 90.0f); // 对matrix对象设置旋转角度

			bitmap = BitmapFactory.decodeFile(imageString);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			// 设置图片
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
	 * 读取本地资源的图片
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
		// 获取资源图片
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